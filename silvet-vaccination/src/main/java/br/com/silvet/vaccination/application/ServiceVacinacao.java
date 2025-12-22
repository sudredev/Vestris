package br.com.silvet.vaccination.application;

import br.com.silvet.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.silvet.vaccination.domain.model.Vacina;
import br.com.silvet.vaccination.domain.repository.RepositorioVacina;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceVacinacao {
    private final RepositorioVacina repositorio;

    public Vacina criar(Vacina novaVacina) {
        if (repositorio.existsByNome(novaVacina.getNome())) {
            throw new ExcecaoRegraNegocio("Já existe uma vacina cadastrada com este nome: " + novaVacina.getNome());
        }
        return repositorio.save(novaVacina);
    }

    public List<Vacina> listarTodas() {
        return repositorio.findAll();
    }
}
