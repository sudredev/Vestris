package br.com.vestris.vaccination.application;

import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.vaccination.domain.model.Vacina;
import br.com.vestris.vaccination.domain.repository.RepositorioVacina;
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
