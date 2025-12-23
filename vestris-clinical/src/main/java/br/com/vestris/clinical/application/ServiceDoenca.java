package br.com.vestris.clinical.application;

import br.com.vestris.clinical.domain.model.Doenca;
import br.com.vestris.clinical.domain.repository.RepositorioDoenca;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.species.application.ServiceEspecie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceDoenca {

    private final RepositorioDoenca repositorio;
    private final ServiceEspecie serviceEspecie; // <--- INJEÇÃO DO MÓDULO VIZINHO

    public Doenca criar(Doenca novaDoenca) {
        // 1. VALIDAÇÃO DE INTEGRIDADE: A Espécie existe?
        if (!serviceEspecie.existePorId(novaDoenca.getEspecieId())) {
            throw new ExcecaoRegraNegocio("Não foi possível cadastrar a doença. A espécie informada não existe.");
        }

        // 2. Validação de Duplicidade
        if (repositorio.existsByNomeAndEspecieId(novaDoenca.getNome(), novaDoenca.getEspecieId())) {
            throw new ExcecaoRegraNegocio("Esta doença já está cadastrada para esta espécie.");
        }

        return repositorio.save(novaDoenca);
    }

    public List<Doenca> listarTodas() {
        return repositorio.findAll();
    }

    public List<Doenca> listarPorEspecie(UUID especieId) {
        return repositorio.findAllByEspecieId(especieId);
    }
}
