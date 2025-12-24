package br.com.vestris.species.application;

import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.species.domain.Especie;
import br.com.vestris.species.domain.repository.RepositorioEspecie;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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

    // ATUALIZAR
    public Especie atualizar(UUID id, Especie dadosAtualizados) {
        Especie existente = repositorio.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Espécie", id.toString()));

        // Atualiza os campos (Exceto ID e Datas)
        existente.setNomePopular(dadosAtualizados.getNomePopular());
        existente.setNomeCientifico(dadosAtualizados.getNomeCientifico());
        existente.setFamiliaTaxonomica(dadosAtualizados.getFamiliaTaxonomica());
        existente.setDescricao(dadosAtualizados.getDescricao());

        return repositorio.save(existente);
    }

    // DELETAR
    public void deletar(UUID id) {
        if (!repositorio.existsById(id)) {
            throw new ExceptionRecursoNaoEncontrado("Espécie", id.toString());
        }

        try {
            repositorio.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            // Captura erro do banco se tentar apagar algo que tem filhos (FK)
            throw new ExcecaoRegraNegocio("Não é possível remover esta espécie pois ela possui registros vinculados (Doenças, Protocolos, etc).");
        }
    }
}
