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

    // ATUALIZAR (Mapeamento Completo)
    public Especie atualizar(UUID id, Especie dados) {
        Especie existente = buscarPorId(id);

        existente.setNomePopular(dados.getNomePopular());
        existente.setNomeCientifico(dados.getNomeCientifico());
        existente.setFamiliaTaxonomica(dados.getFamiliaTaxonomica());
        existente.setGrupo(dados.getGrupo());

        existente.setResumoClinico(dados.getResumoClinico());
        existente.setParametrosFisiologicos(dados.getParametrosFisiologicos());
        existente.setExpectativaVida(dados.getExpectativaVida());
        existente.setPesoAdulto(dados.getPesoAdulto());

        existente.setTipoDieta(dados.getTipoDieta());
        existente.setManejoInfos(dados.getManejoInfos());

        existente.setAlertasClinicos(dados.getAlertasClinicos());
        existente.setPontosExameFisico(dados.getPontosExameFisico());

        existente.setHabitat(dados.getHabitat());
        existente.setDistribuicaoGeografica(dados.getDistribuicaoGeografica());
        existente.setStatusConservacao(dados.getStatusConservacao());

        existente.setBibliografiaBase(dados.getBibliografiaBase());

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
            throw new ExcecaoRegraNegocio("Não é possível remover esta espécie pois ela possui registros vinculados.");
        }
    }
}
