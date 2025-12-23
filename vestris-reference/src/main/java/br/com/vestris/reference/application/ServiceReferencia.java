package br.com.vestris.reference.application;

import br.com.vestris.reference.domain.model.ReferenciaBibliografica;
import br.com.vestris.reference.domain.repository.RepositorioReferencia;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import lombok.RequiredArgsConstructor;
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
}
