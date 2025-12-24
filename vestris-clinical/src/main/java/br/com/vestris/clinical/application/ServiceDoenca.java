package br.com.vestris.clinical.application;

import br.com.vestris.clinical.domain.model.Doenca;
import br.com.vestris.clinical.domain.repository.RepositorioDoenca;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.species.application.ServiceEspecie;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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
        if (!serviceEspecie.existePorId(especieId)) {
            throw new ExceptionRecursoNaoEncontrado("Espécie", especieId.toString());
        }
        // 2. Se existe, busca as doenças
        return repositorio.findAllByEspecieId(especieId);
    }

    public Doenca buscarPorId(UUID id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Doença", id.toString()));
    }

    public Doenca atualizar(UUID id, Doenca dados) {
        Doenca existente = buscarPorId(id);

        // Regra: Se mudar o nome, verificar duplicidade de novo
        if (!existente.getNome().equalsIgnoreCase(dados.getNome()) &&
                repositorio.existsByNomeAndEspecieId(dados.getNome(), existente.getEspecieId())) {
            throw new ExcecaoRegraNegocio("Já existe outra doença com este nome para esta espécie.");
        }

        existente.setNome(dados.getNome());
        existente.setNomeCientifico(dados.getNomeCientifico());
        existente.setSintomas(dados.getSintomas());
        // Nota: Geralmente não deixamos mudar a Espécie (especieId) de uma doença existente,
        // pois quebraria os protocolos. Se quiser permitir, tem que validar se a nova espécie existe.

        return repositorio.save(existente);
    }

    public void deletar(UUID id) {
        if (!repositorio.existsById(id)) {
            throw new ExceptionRecursoNaoEncontrado("Doença", id.toString());
        }
        try {
            repositorio.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ExcecaoRegraNegocio("Não é possível remover esta doença pois existem protocolos vinculados a ela.");
        }
    }
}
