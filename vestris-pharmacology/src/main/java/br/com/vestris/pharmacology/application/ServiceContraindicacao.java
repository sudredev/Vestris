package br.com.vestris.pharmacology.application;

import br.com.vestris.pharmacology.domain.model.Contraindicacao;
import br.com.vestris.pharmacology.domain.model.Medicamento;
import br.com.vestris.pharmacology.domain.repository.RepositorioContraindicacao;
import br.com.vestris.pharmacology.domain.repository.RepositorioMedicamento;
import br.com.vestris.reference.application.ServiceReferencia;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.species.application.ServiceEspecie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceContraindicacao {
    private final RepositorioContraindicacao repoContraindicacao;
    private final RepositorioMedicamento repoMedicamento;

    // Injeção dos outros módulos
    private final ServiceEspecie serviceEspecie;
    private final ServiceReferencia serviceReferencia;

    public Contraindicacao criar(UUID medicamentoId, UUID especieId, UUID referenciaId,
                                 Contraindicacao.Gravidade gravidade, String descricao) {

        // 1. Validar Medicamento (Interno)
        Medicamento med = repoMedicamento.findById(medicamentoId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Medicamento", medicamentoId.toString()));

        // 2. Validar Espécie (Externo)
        if (!serviceEspecie.existePorId(especieId)) {
            throw new ExcecaoRegraNegocio("Espécie não encontrada.");
        }

        // 3. Validar Referência (Externo)
        if (!serviceReferencia.existePorId(referenciaId)) { // Certifique-se de criar este método lá no módulo Reference
            throw new ExcecaoRegraNegocio("Referência bibliográfica não encontrada.");
        }

        // 4. Validar Duplicidade
        if (repoContraindicacao.existsByMedicamentoIdAndEspecieId(medicamentoId, especieId)) {
            throw new ExcecaoRegraNegocio("Já existe uma contraindicação deste medicamento para esta espécie.");
        }

        Contraindicacao nova = new Contraindicacao();
        nova.setMedicamento(med);
        nova.setEspecieId(especieId);
        nova.setReferenciaId(referenciaId);
        nova.setGravidade(gravidade);
        nova.setDescricao(descricao);

        return repoContraindicacao.save(nova);
    }

    public List<Contraindicacao> listarPorMedicamento(UUID medicamentoId) {
        return repoContraindicacao.findByMedicamentoId(medicamentoId);
    }
}
