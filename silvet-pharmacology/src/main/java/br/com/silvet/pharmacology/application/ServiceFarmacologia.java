package br.com.silvet.pharmacology.application;

import br.com.silvet.pharmacology.domain.model.Medicamento;
import br.com.silvet.pharmacology.domain.model.PrincipioAtivo;
import br.com.silvet.pharmacology.domain.repository.RepositorioMedicamento;
import br.com.silvet.pharmacology.domain.repository.RepositorioPrincipioAtivo;
import br.com.silvet.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.silvet.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceFarmacologia {
    private final RepositorioPrincipioAtivo repoPrincipio;
    private final RepositorioMedicamento repoMedicamento;

    // --- Princípios Ativos ---

    public PrincipioAtivo criarPrincipio(PrincipioAtivo novo) {
        if (repoPrincipio.existsByNome(novo.getNome())) {
            throw new ExcecaoRegraNegocio("Princípio ativo já cadastrado: " + novo.getNome());
        }
        return repoPrincipio.save(novo);
    }

    public List<PrincipioAtivo> listarPrincipios() {
        return repoPrincipio.findAll();
    }

    public PrincipioAtivo buscarPrincipioPorId(UUID id) {
        return repoPrincipio.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Princípio Ativo", id.toString()));
    }

    // --- Medicamentos ---

    public Medicamento criarMedicamento(Medicamento novo, UUID principioAtivoId) {
        // 1. Busca o Princípio Ativo (Lança erro 404 se não achar)
        PrincipioAtivo pa = buscarPrincipioPorId(principioAtivoId);

        // 2. Vincula
        novo.setPrincipioAtivo(pa);

        // 3. Salva
        return repoMedicamento.save(novo);
    }

    public List<Medicamento> listarMedicamentos() {
        return repoMedicamento.findAll();
    }
}
