package br.com.vestris.vaccination.application;

import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.vaccination.domain.model.Vacina;
import br.com.vestris.vaccination.domain.repository.RepositorioVacina;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public Vacina buscarPorId(UUID id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Vacina", id.toString()));
    }

    public Vacina atualizar(UUID id, Vacina dados) {
        Vacina existente = buscarPorId(id);

        // Verifica duplicidade de nome apenas se o nome mudou
        if (!existente.getNome().equalsIgnoreCase(dados.getNome()) && repositorio.existsByNome(dados.getNome())) {
            throw new ExcecaoRegraNegocio("Já existe outra vacina com este nome.");
        }

        existente.setNome(dados.getNome());
        existente.setFabricante(dados.getFabricante());
        existente.setTipoVacina(dados.getTipoVacina());
        existente.setDescricao(dados.getDescricao());
        existente.setDoencaAlvo(dados.getDoencaAlvo());

        return repositorio.save(existente);
    }

    public void deletar(UUID id) {
        if (!repositorio.existsById(id)) {
            throw new ExceptionRecursoNaoEncontrado("Vacina", id.toString());
        }
        try {
            repositorio.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ExcecaoRegraNegocio("Não é possível remover esta vacina pois ela está sendo usada em protocolos vacinais.");
        }
    }
}
