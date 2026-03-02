package br.com.vestris.pharmacology.application;

import br.com.vestris.pharmacology.domain.model.Contraindicacao;
import br.com.vestris.pharmacology.domain.model.Medicamento;
import br.com.vestris.pharmacology.domain.repository.RepositorioContraindicacao;
import br.com.vestris.pharmacology.domain.repository.RepositorioMedicamento;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceSegurancaFarmacologica {
    private final RepositorioContraindicacao repoContra;
    private final RepositorioMedicamento repoMedicamento;

    public List<Contraindicacao> validarMedicamentoParaEspecie(UUID medicamentoId, UUID especieId) {
        // 1. Achar o medicamento
        Medicamento med = repoMedicamento.findById(medicamentoId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Medicamento", medicamentoId.toString()));

        // 2. Descobrir o Princípio Ativo (A regra é ligada à molécula)
        if (med.getPrincipioAtivo() == null) {
            return List.of(); // Sem princípio cadastrado, sem validação automática
        }

        UUID principioId = med.getPrincipioAtivo().getId();

        // 3. Buscar riscos no banco
        return repoContra.encontrarRiscos(principioId, especieId);
    }
}
