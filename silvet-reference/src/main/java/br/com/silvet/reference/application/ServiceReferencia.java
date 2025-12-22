package br.com.silvet.reference.application;

import br.com.silvet.reference.domain.model.ReferenciaBibliografica;
import br.com.silvet.reference.domain.repository.RepositorioReferencia;
import br.com.silvet.shared.domain.exceptions.ExcecaoRegraNegocio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
