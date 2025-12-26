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

    // Serviços Externos
    private final ServiceReferencia serviceReferencia;
    private final ServiceFarmacologia serviceFarmacologia;

    @Transactional
    public Protocolo criar(Protocolo protocolo, UUID doencaId, List<Dosagem> itens) {
        // 1. Validar Doença
        Doenca doenca = repoDoenca.findById(doencaId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Doença", doencaId.toString()));

        // 2. Validar Referência
        if (!serviceReferencia.existePorId(protocolo.getReferenciaId())) {
            throw new ExcecaoRegraNegocio("A Referência Bibliográfica informada não existe.");
        }

        // 3. Validar Medicamentos
        if (itens != null && !itens.isEmpty()) {
            for (Dosagem item : itens) {
                if (!serviceFarmacologia.existeMedicamentoPorId(item.getMedicamentoId())) {
                    throw new ExcecaoRegraNegocio("O Medicamento informado (ID: " + item.getMedicamentoId() + ") não existe.");
                }
                protocolo.adicionarDosagem(item);
            }
        } else {
            throw new ExcecaoRegraNegocio("O protocolo deve conter pelo menos uma dosagem.");
        }

        protocolo.setDoenca(doenca);
        return repoProtocolo.save(protocolo);
    }

    public List<Protocolo> listarPorDoenca(UUID doencaId) {
        if (!repoDoenca.existsById(doencaId)) {
            throw new ExceptionRecursoNaoEncontrado("Doença", doencaId.toString());
        }

        List<Protocolo> protocolos = repoProtocolo.findByDoencaId(doencaId);

        if (protocolos.isEmpty()) {
            // Retorna 400 (Bad Request) com mensagem amigável, ou remova se quiser retornar lista vazia 200
            // throw new ExcecaoRegraNegocio("Ainda não há protocolos definidos para esta doença."); 
            // Sugestão: Retornar vazio é melhor para o front não dar erro, mas mantive sua lógica anterior se preferir
        }
        return protocolos;
    }

    public Protocolo buscarPorId(UUID id) {
        return repoProtocolo.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Protocolo", id.toString()));
    }

    // --- NOVO MÉTODO PARA A VISÃO COMPLETA ---
    public ProtocoloCompletoDTO montarProtocoloCompleto(UUID especieId, UUID doencaId) {
        // 1. Busca Doença
        Doenca doenca = repoDoenca.findById(doencaId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Doença", doencaId.toString()));

        // 2. Valida vínculo com Espécie
        if (!doenca.getEspecieId().equals(especieId)) {
            throw new ExcecaoRegraNegocio("Esta doença não pertence à espécie informada.");
        }

        // 3. Busca Protocolos
        List<Protocolo> protocolos = repoProtocolo.findByDoencaId(doencaId);

        return ProtocoloCompletoDTO.builder()
                .doenca(doenca)
                .protocolos(protocolos)
                .build();
    }

    @Transactional
    public Protocolo atualizar(UUID id, Protocolo dadosNovos, List<Dosagem> novasDosagens) {
        Protocolo existente = buscarPorId(id);

        existente.setTitulo(dadosNovos.getTitulo());
        existente.setObservacoes(dadosNovos.getObservacoes());

        if (!existente.getReferenciaId().equals(dadosNovos.getReferenciaId())) {
            if (!serviceReferencia.existePorId(dadosNovos.getReferenciaId())) {
                throw new ExcecaoRegraNegocio("Nova Referência Bibliográfica não encontrada.");
            }
            existente.setReferenciaId(dadosNovos.getReferenciaId());
        }

        existente.getDosagens().clear();

        if (novasDosagens != null) {
            for (Dosagem d : novasDosagens) {
                if (!serviceFarmacologia.existeMedicamentoPorId(d.getMedicamentoId())) {
                    throw new ExcecaoRegraNegocio("Medicamento inválido na atualização: " + d.getMedicamentoId());
                }
                existente.adicionarDosagem(d);
            }
        }

        return repoProtocolo.save(existente);
    }

    public void deletar(UUID id) {
        if (!repoProtocolo.existsById(id)) {
            throw new ExceptionRecursoNaoEncontrado("Protocolo", id.toString());
        }
        try {
            repoProtocolo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ExcecaoRegraNegocio("Não é possível remover este protocolo pois ele está em uso.");
        }
    }
}
