package br.com.vestris.pharmacology.application;

import br.com.vestris.pharmacology.domain.model.Medicamento;
import br.com.vestris.pharmacology.domain.model.PrincipioAtivo;
import br.com.vestris.pharmacology.domain.repository.RepositorioMedicamento;
import br.com.vestris.pharmacology.domain.repository.RepositorioPrincipioAtivo;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceFarmacologia {
    private final RepositorioPrincipioAtivo repoPrincipio;
    private final RepositorioMedicamento repoMedicamento;


    // PRINCÍPIOS ATIVOS
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

    public PrincipioAtivo atualizarPrincipio(UUID id, PrincipioAtivo dados) {
        PrincipioAtivo existente = buscarPrincipioPorId(id);

        // Se mudar o nome, verifica duplicidade
        if (!existente.getNome().equalsIgnoreCase(dados.getNome()) && repoPrincipio.existsByNome(dados.getNome())) {
            throw new ExcecaoRegraNegocio("Já existe outro princípio ativo com este nome.");
        }

        existente.setNome(dados.getNome());
        existente.setDescricao(dados.getDescricao());
        existente.setGrupoFarmacologico(dados.getGrupoFarmacologico());

        return repoPrincipio.save(existente);
    }

    public void deletarPrincipio(UUID id) {
        if (!repoPrincipio.existsById(id)) {
            throw new ExceptionRecursoNaoEncontrado("Princípio Ativo", id.toString());
        }
        try {
            repoPrincipio.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            // O banco travou porque tem medicamento usando este princípio
            throw new ExcecaoRegraNegocio("Não é possível remover este princípio ativo pois existem medicamentos vinculados a ele.");
        }
    }


    // MEDICAMENTOS

    public Medicamento criarMedicamento(Medicamento novo, UUID principioAtivoId) {
        PrincipioAtivo pa = buscarPrincipioPorId(principioAtivoId);
        novo.setPrincipioAtivo(pa);
        return repoMedicamento.save(novo);
    }

    public List<Medicamento> listarMedicamentos() {
        return repoMedicamento.findAll();
    }

    public Medicamento buscarMedicamentoPorId(UUID id) {
        return repoMedicamento.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Medicamento", id.toString()));
    }

    public Medicamento atualizarMedicamento(UUID id, Medicamento dados, UUID novoPrincipioId) {
        Medicamento existente = buscarMedicamentoPorId(id);

        // Se o ID do princípio ativo mudou, buscamos o novo
        if (!existente.getPrincipioAtivo().getId().equals(novoPrincipioId)) {
            PrincipioAtivo novoPa = buscarPrincipioPorId(novoPrincipioId);
            existente.setPrincipioAtivo(novoPa);
        }

        existente.setNome(dados.getNome());
        existente.setConcentracao(dados.getConcentracao());
        existente.setFabricante(dados.getFabricante());
        existente.setFormaFarmaceutica(dados.getFormaFarmaceutica());

        return repoMedicamento.save(existente);
    }

    public void deletarMedicamento(UUID id) {
        if (!repoMedicamento.existsById(id)) {
            throw new ExceptionRecursoNaoEncontrado("Medicamento", id.toString());
        }
        try {
            repoMedicamento.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            // O banco travou porque tem Contraindicação ou Protocolo (em outro módulo se houver FK real, mas aqui é provavel Contraindicação)
            throw new ExcecaoRegraNegocio("Não é possível remover este medicamento pois ele está sendo usado em contraindicações ou protocolos.");
        }
    }

    // Método auxiliar para outros módulos
    public boolean existeMedicamentoPorId(UUID id) {
        return repoMedicamento.existsById(id);
    }
}
