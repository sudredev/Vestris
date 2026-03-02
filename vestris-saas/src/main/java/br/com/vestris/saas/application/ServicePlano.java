package br.com.vestris.saas.application;

import br.com.vestris.saas.domain.model.Plano;
import br.com.vestris.saas.domain.repository.RepositorioPlano;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ServicePlano {
    private final RepositorioPlano repositorio;

    public List<Plano> listarTodos() {
        return repositorio.findAll();
    }

    public Plano buscarPorId(UUID id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Plano", id.toString()));
    }

    // Método para Admin criar planos (futuro)
    public Plano criar(Plano plano) {
        return repositorio.save(plano);
    }
}
