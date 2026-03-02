## src\main\java\br\com\vestris\reference\application

### ServiceReferencia.java

```java
// src\main\java\br\com\vestris\reference\application\ServiceReferencia.java
package br.com.vestris.reference.application;

import br.com.vestris.reference.domain.model.ReferenciaBibliografica;
import br.com.vestris.reference.domain.repository.RepositorioReferencia;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceReferencia {

    private final RepositorioReferencia repositorio;

    public ReferenciaBibliografica criar(ReferenciaBibliografica nova) {
        if (repositorio.existsByTituloAndAutores(nova.getTitulo(), nova.getAutores())) {
            throw new ExcecaoRegraNegocio("Esta referência já está cadastrada.");
        }
        return repositorio.save(nova);
    }

    public List<ReferenciaBibliografica> listarTodas() {
        return repositorio.findAll();
    }

    public boolean existePorId(UUID id) {
        return repositorio.existsById(id);
    }

    public ReferenciaBibliografica buscarPorId(UUID id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Referência", id.toString()));
    }

    public ReferenciaBibliografica atualizar(UUID id, ReferenciaBibliografica dados) {
        ReferenciaBibliografica existente = buscarPorId(id);

        existente.setTitulo(dados.getTitulo());
        existente.setAutores(dados.getAutores());
        existente.setAno(dados.getAno());
        existente.setFonte(dados.getFonte());
        existente.setUrl(dados.getUrl());

        return repositorio.save(existente);
    }

    public void deletar(UUID id) {
        if (!repositorio.existsById(id)) {
            throw new ExceptionRecursoNaoEncontrado("Referência", id.toString());
        }
        try {
            repositorio.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ExcecaoRegraNegocio("Esta referência não pode ser removida pois embasa protocolos ou vacinas no sistema.");
        }
    }

    public String buscarCitacaoPorId(UUID id) {
        return repositorio.findById(id)
                .map(r -> r.getAutores() + " (" + r.getAno() + ") - " + r.getTitulo())
                .orElse("Referência não identificada");
    }
}

```

