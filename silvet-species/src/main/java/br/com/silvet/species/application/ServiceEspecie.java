package br.com.silvet.species.application;

import br.com.silvet.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.silvet.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.silvet.species.domain.Especie;
import br.com.silvet.species.domain.repository.RepositorioEspecie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceEspecie {

    private final RepositorioEspecie repositorio;

    public Especie criar(Especie novaEspecie) {
        // Regra: Nome científico deve ser único
        if (repositorio.existsByNomeCientifico(novaEspecie.getNomeCientifico())) {
            throw new ExcecaoRegraNegocio("Já existe uma espécie cadastrada com o nome científico: " + novaEspecie.getNomeCientifico());
        }
        return repositorio.save(novaEspecie);
    }

    public List<Especie> listarTodas(){
        return repositorio.findAll();
    }

    public Especie buscarPorId(UUID id){
        return repositorio.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Especie", id.toString()));
    }

    public boolean existePorId(UUID id) {
        return repositorio.existsById(id);
    }
}
