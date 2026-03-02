package br.com.vestris.pharmacology.application;

import br.com.vestris.pharmacology.domain.model.Contraindicacao;
import br.com.vestris.pharmacology.domain.model.Medicamento;
import br.com.vestris.pharmacology.domain.model.PrincipioAtivo;
import br.com.vestris.pharmacology.domain.repository.RepositorioContraindicacao;
import br.com.vestris.pharmacology.domain.repository.RepositorioMedicamento;
import br.com.vestris.pharmacology.domain.repository.RepositorioPrincipioAtivo;
import br.com.vestris.reference.application.ServiceReferencia;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.species.application.ServiceEspecie;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceContraindicacao {
    private final RepositorioContraindicacao repoContraindicacao;
    private final RepositorioMedicamento repoMedicamento;
    private final RepositorioPrincipioAtivo repoPrincipio;
    private final ServiceEspecie serviceEspecie;

    @Transactional
    public Contraindicacao criar(UUID medicamentoId, UUID principioAtivoId, UUID especieId, String referenciaTexto,
                                 Contraindicacao.Gravidade gravidade, String descricao) {

        PrincipioAtivo principio = null;

        // 1. Tenta pelo Princípio Ativo (Prioridade para o Admin)
        if (principioAtivoId != null) {
            principio = repoPrincipio.findById(principioAtivoId)
                    .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Princípio Ativo", principioAtivoId.toString()));
        }
        // 2. Fallback: Tenta pelo Medicamento (Legado)
        else if (medicamentoId != null) {
            Medicamento med = repoMedicamento.findById(medicamentoId)
                    .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Medicamento", medicamentoId.toString()));
            principio = med.getPrincipioAtivo();
        } else {
            throw new ExcecaoRegraNegocio("Informe o Medicamento ou o Princípio Ativo.");
        }

        // 2. Validar Espécie
        if (!serviceEspecie.existePorId(especieId)) {
            throw new ExcecaoRegraNegocio("Espécie não encontrada.");
        }

        // 3. Validar Duplicidade
        if (repoContraindicacao.existsByPrincipioAtivoIdAndEspecieId(principio.getId(), especieId)) {
            throw new ExcecaoRegraNegocio("Já existe uma contraindicação deste princípio ativo para esta espécie.");
        }

        Contraindicacao nova = new Contraindicacao();
        nova.setPrincipioAtivo(principio);
        nova.setEspecieId(especieId);
        nova.setReferenciaTexto(referenciaTexto);
        nova.setGravidade(gravidade);
        nova.setDescricao(descricao);

        return repoContraindicacao.save(nova);
    }

    public List<Contraindicacao> listarPorMedicamento(UUID medicamentoId) {
        Medicamento med = repoMedicamento.findById(medicamentoId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Medicamento", medicamentoId.toString()));

        // Busca contraindicações ligadas ao princípio ativo deste medicamento
        return repoContraindicacao.findByPrincipioAtivoId(med.getPrincipioAtivo().getId());
    }

    public Contraindicacao buscarPorId(UUID id) {
        return repoContraindicacao.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Contraindicação", id.toString()));
    }

    @Transactional
    public Contraindicacao atualizar(UUID id, UUID novoEspecieId, String novaReferencia,
                                     Contraindicacao.Gravidade novaGravidade, String novaDescricao) {

        Contraindicacao existente = buscarPorId(id);

        // 1. Se mudou a Espécie
        if (!existente.getEspecieId().equals(novoEspecieId)) {
            if (!serviceEspecie.existePorId(novoEspecieId)) {
                throw new ExcecaoRegraNegocio("A nova Espécie informada não existe.");
            }
            if (repoContraindicacao.existsByPrincipioAtivoIdAndEspecieId(existente.getPrincipioAtivo().getId(), novoEspecieId)) {
                throw new ExcecaoRegraNegocio("Já existe uma contraindicação para a nova espécie selecionada.");
            }
            existente.setEspecieId(novoEspecieId);
        }

        // 2. Atualiza dados simples
        existente.setReferenciaTexto(novaReferencia);
        existente.setGravidade(novaGravidade);
        existente.setDescricao(novaDescricao);

        return repoContraindicacao.save(existente);
    }

    public void deletar(UUID id) {
        if (!repoContraindicacao.existsById(id)) {
            throw new ExceptionRecursoNaoEncontrado("Contraindicação", id.toString());
        }
        repoContraindicacao.deleteById(id);
    }
}
