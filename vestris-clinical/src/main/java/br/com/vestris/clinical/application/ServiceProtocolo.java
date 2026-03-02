package br.com.vestris.clinical.application;

import br.com.vestris.clinical.domain.model.Doenca;
import br.com.vestris.clinical.domain.model.Dosagem;
import br.com.vestris.clinical.domain.model.Protocolo;
import br.com.vestris.clinical.domain.model.ProtocoloCompletoDTO;
import br.com.vestris.clinical.domain.repository.RepositorioDoenca;
import br.com.vestris.clinical.domain.repository.RepositorioProtocolo;

import br.com.vestris.pharmacology.application.ServiceFarmacologia;
import br.com.vestris.reference.application.ServiceReferencia;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.shared.infrastructure.helper.HelperAuditoria;
import br.com.vestris.user.application.ServiceAuditoria;
import br.com.vestris.user.application.ServiceUsuario;
import br.com.vestris.user.domain.model.AcaoAuditoria;
import br.com.vestris.user.domain.model.EntidadeAuditoria;
import br.com.vestris.user.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceProtocolo {
    private final RepositorioProtocolo repoProtocolo;
    private final RepositorioDoenca repoDoenca;
    private final ServiceFarmacologia serviceFarmacologia;
    private final ServiceReferencia serviceReferencia;
    private final ServiceAuditoria servicoAuditoria;
    private final ServiceUsuario servicoUsuario;
    private final HelperAuditoria helperAuditoria;

    @Transactional
    public Protocolo criar(Protocolo protocolo, UUID doencaIdInput, List<Dosagem> itens) {

        // 1. Lógica Híbrida para Doença
        if (doencaIdInput != null) {
            Doenca doenca = repoDoenca.findById(doencaIdInput)
                    .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Doença", doencaIdInput.toString()));
            protocolo.setDoenca(doenca);
        } else if (protocolo.getDoencaTextoLivre() == null || protocolo.getDoencaTextoLivre().isBlank()) {
            throw new ExcecaoRegraNegocio("É necessário informar uma doença (selecione da lista ou digite o nome).");
        }

        // 2. Validação de Origem e Referência
        if (protocolo.getOrigem() == Protocolo.OrigemProtocolo.OFICIAL) {
            if (protocolo.getReferenciaId() == null) {
                throw new ExcecaoRegraNegocio("Protocolos oficiais exigem ID de referência.");
            }
            if (!serviceReferencia.existePorId(protocolo.getReferenciaId())) {
                throw new ExcecaoRegraNegocio("Referência ID inválida.");
            }
        } else {
            // Se for próprio, exige autor
            if (protocolo.getAutorId() == null) {
                throw new ExcecaoRegraNegocio("Protocolos próprios exigem um autor vinculado.");
            }
            // Se não tem nem ID nem Texto de referência
            if (protocolo.getReferenciaId() == null && (protocolo.getReferenciaTexto() == null || protocolo.getReferenciaTexto().isBlank())) {
                protocolo.setReferenciaTexto("Autoria Própria / Experiência Clínica"); // Fallback
            }
        }

        // 3. Processar Dosagens Híbridas
        if (itens != null && !itens.isEmpty()) {
            for (Dosagem item : itens) {
                if (item.getMedicamentoId() != null) {
                    // Se mandou ID, valida
                    if (!serviceFarmacologia.existeMedicamentoPorId(item.getMedicamentoId())) {
                        throw new ExcecaoRegraNegocio("Medicamento ID inválido: " + item.getMedicamentoId());
                    }
                } else if (item.getMedicamentoTextoLivre() == null || item.getMedicamentoTextoLivre().isBlank()) {
                    throw new ExcecaoRegraNegocio("Todo item da prescrição precisa de um medicamento (ID ou Nome).");
                }
                protocolo.adicionarDosagem(item);
            }
        } else {
            throw new ExcecaoRegraNegocio("Adicione pelo menos um item ao protocolo.");
        }

        Protocolo salvo = repoProtocolo.save(protocolo);

        // --- LOG DE AUDITORIA ---
        try {
            UUID clinicaId = null;
            if (protocolo.getOrigem() == Protocolo.OrigemProtocolo.PROPRIO ||
                protocolo.getOrigem() == Protocolo.OrigemProtocolo.INSTITUCIONAL) {
                Usuario autor = servicoUsuario.buscarPorId(protocolo.getAutorId());
                if (autor.getClinica() != null) {
                    clinicaId = autor.getClinica().getId();
                }
            }

            if (clinicaId != null) {
                var metadados = helperAuditoria.montarMetadadosProtocolo(
                    salvo.getTitulo(),
                    protocolo.getOrigem().name(),
                    protocolo.getAutorId(),
                    "doenca", (protocolo.getDoenca() != null ? protocolo.getDoenca().getNome() : protocolo.getDoencaTextoLivre()),
                    "totalDosagens", String.valueOf(salvo.getDosagens().size())
                );
                servicoAuditoria.registrar(
                    clinicaId,
                    protocolo.getAutorId(),
                    AcaoAuditoria.PROTOCOLO_CRIADO,
                    EntidadeAuditoria.PROTOCOLO,
                    salvo.getId(),
                    "Protocolo criado: " + salvo.getTitulo(),
                    metadados
                );
            }
        } catch (Exception e) {
            System.err.println("Erro ao auditar criação de protocolo: " + e.getMessage());
        }
        // -------------------------

        return salvo;
    }

    public List<Protocolo> listarPorDoenca(UUID doencaId) {
        // Se vier ID, busca exato.
        // TODO: Futuramente implementar busca por texto livre da doença também
        if (!repoDoenca.existsById(doencaId)) {
            throw new ExceptionRecursoNaoEncontrado("Doença", doencaId.toString());
        }
        return repoProtocolo.findByDoencaId(doencaId);
    }

    // Lista TODOS (para o filtro "TODAS" funcionar no front, se desejar)
    public List<Protocolo> listarTodos() {
        return repoProtocolo.findAll();
    }

    public Protocolo buscarPorId(UUID id) {
        return repoProtocolo.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Protocolo", id.toString()));
    }

    public ProtocoloCompletoDTO montarProtocoloCompleto(UUID especieId, UUID doencaId) {
        Doenca doenca = repoDoenca.findById(doencaId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Doença", doencaId.toString()));

        if (!doenca.getEspecieId().equals(especieId)) {
            throw new ExcecaoRegraNegocio("Doença não pertence à espécie.");
        }

        List<Protocolo> protocolos = repoProtocolo.findByDoencaId(doencaId);

        return ProtocoloCompletoDTO.builder()
                .doenca(doenca)
                .protocolos(protocolos)
                .build();
    }

    public List<Protocolo> listarAcessiveis(UUID doencaId, UUID clinicaId, UUID usuarioId) {
        if (!repoDoenca.existsById(doencaId)) {
            throw new ExceptionRecursoNaoEncontrado("Doença", doencaId.toString());
        }

        // Se não vier clinicaId ou usuarioId (ex: acesso público ou erro),
        // a query JPQL lida com null, mas idealmente deveríamos garantir que não venham nulos do controller.
        // No caso de null, a comparação (p.clinicaId = null) retornaria falso no SQL padrão para valores preenchidos.

        return repoProtocolo.listarAcessiveis(doencaId, clinicaId, usuarioId);
    }

    @Transactional
    public Protocolo atualizar(UUID id, Protocolo dados, List<Dosagem> novasDosagens) {
        Protocolo existente = buscarPorId(id);

        existente.setTitulo(dados.getTitulo());
        existente.setObservacoes(dados.getObservacoes());
        existente.setReferenciaTexto(dados.getReferenciaTexto());
        existente.setReferenciaId(dados.getReferenciaId());

        // Atualiza campos de doença se necessário (geralmente não muda, mas ok)
        if(dados.getDoenca() != null) existente.setDoenca(dados.getDoenca());
        if(dados.getDoencaTextoLivre() != null) existente.setDoencaTextoLivre(dados.getDoencaTextoLivre());

        existente.getDosagens().clear();
        if (novasDosagens != null) {
            for (Dosagem d : novasDosagens) {
                // Mesma validação híbrida
                if (d.getMedicamentoId() != null) {
                    if (!serviceFarmacologia.existeMedicamentoPorId(d.getMedicamentoId())) {
                        throw new ExcecaoRegraNegocio("Medicamento inválido na edição.");
                    }
                }
                existente.adicionarDosagem(d);
            }
        }
        Protocolo salvo = repoProtocolo.save(existente);

        // --- LOG DE AUDITORIA ---
        try {
            UUID clinicaId = null;
            if (existente.getOrigem() == Protocolo.OrigemProtocolo.PROPRIO ||
                existente.getOrigem() == Protocolo.OrigemProtocolo.INSTITUCIONAL) {
                Usuario autor = servicoUsuario.buscarPorId(existente.getAutorId());
                if (autor.getClinica() != null) {
                    clinicaId = autor.getClinica().getId();
                }
            }

            if (clinicaId != null) {
                var metadados = helperAuditoria.montarMetadadosProtocolo(
                    salvo.getTitulo(),
                    existente.getOrigem().name(),
                    existente.getAutorId(),
                    "totalDosagens", String.valueOf(salvo.getDosagens().size())
                );
                servicoAuditoria.registrar(
                    clinicaId,
                    existente.getAutorId(),
                    AcaoAuditoria.PROTOCOLO_EDITADO,
                    EntidadeAuditoria.PROTOCOLO,
                    salvo.getId(),
                    "Protocolo atualizado: " + salvo.getTitulo(),
                    metadados
                );
            }
        } catch (Exception e) {
            System.err.println("Erro ao auditar edição de protocolo: " + e.getMessage());
        }
        // -------------------------

        return salvo;
    }

    public void deletar(UUID id) {
        if (!repoProtocolo.existsById(id)) {
            throw new ExceptionRecursoNaoEncontrado("Protocolo", id.toString());
        }

        Protocolo protocolo = buscarPorId(id);

        try {
            repoProtocolo.deleteById(id);

            // --- LOG DE AUDITORIA ---
            try {
                UUID clinicaId = null;
                if (protocolo.getOrigem() == Protocolo.OrigemProtocolo.PROPRIO ||
                    protocolo.getOrigem() == Protocolo.OrigemProtocolo.INSTITUCIONAL) {
                    Usuario autor = servicoUsuario.buscarPorId(protocolo.getAutorId());
                    if (autor.getClinica() != null) {
                        clinicaId = autor.getClinica().getId();
                    }
                }

                if (clinicaId != null) {
                    var metadados = helperAuditoria.montarMetadadosProtocolo(
                        protocolo.getTitulo(),
                        protocolo.getOrigem().name(),
                        protocolo.getAutorId()
                    );
                    servicoAuditoria.registrar(
                        clinicaId,
                        protocolo.getAutorId(),
                        AcaoAuditoria.PROTOCOLO_REMOVIDO,
                        EntidadeAuditoria.PROTOCOLO,
                        id,
                        "Protocolo deletado: " + protocolo.getTitulo(),
                        metadados
                    );
                }
            } catch (Exception e) {
                System.err.println("Erro ao auditar deleção de protocolo: " + e.getMessage());
            }
            // -------------------------
        } catch (DataIntegrityViolationException e) {
            throw new ExcecaoRegraNegocio("Protocolo em uso.");
        }
    }
}
