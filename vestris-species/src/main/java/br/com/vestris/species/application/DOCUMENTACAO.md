## src\main\java\br\com\vestris\species\application

### ServiceEspecie.java

```java
// src\main\java\br\com\vestris\species\application\ServiceEspecie.java
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

```

### ServiceModeloExame.java

```java
// src\main\java\br\com\vestris\species\application\ServiceModeloExame.java
package br.com.vestris.species.application;

import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.species.domain.ModeloExameFisico;
import br.com.vestris.species.domain.repository.RepositorioModeloExame;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceModeloExame {
    private final RepositorioModeloExame repositorio;
    private final ServiceEspecie serviceEspecie; // Para validar se a espécie existe

    // --- BUSCAR ---
    public ModeloExameFisico buscarPorEspecie(UUID especieId) {
        // Valida se a espécie existe antes de buscar o modelo
        if (!serviceEspecie.existePorId(especieId)) {
            throw new ExceptionRecursoNaoEncontrado("Espécie", especieId.toString());
        }

        return repositorio.findByEspecieId(especieId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Modelo de Exame Físico para a espécie", especieId.toString()));
    }

    // --- CRIAR ---
    @Transactional
    public ModeloExameFisico criar(UUID especieId, String textoBase) {
        if (!serviceEspecie.existePorId(especieId)) {
            throw new ExceptionRecursoNaoEncontrado("Espécie", especieId.toString());
        }

        // Regra: Uma espécie só pode ter UM modelo ativo
        if (repositorio.findByEspecieId(especieId).isPresent()) {
            throw new ExcecaoRegraNegocio("Já existe um modelo de exame físico cadastrado para esta espécie. Use a atualização (PUT).");
        }

        ModeloExameFisico novo = new ModeloExameFisico();
        novo.setEspecieId(especieId);
        novo.setTextoBase(textoBase);

        return repositorio.save(novo);
    }

    // --- ATUALIZAR ---
    @Transactional
    public ModeloExameFisico atualizar(UUID especieId, String novoTextoBase) {
        ModeloExameFisico existente = buscarPorEspecie(especieId); // Já valida existência

        existente.setTextoBase(novoTextoBase);

        return repositorio.save(existente);
    }

    // --- DELETAR ---
    @Transactional
    public void deletar(UUID especieId) {
        ModeloExameFisico existente = buscarPorEspecie(especieId);
        repositorio.delete(existente);
    }

    // --- LISTAR TODOS ---
    public List<ModeloExameFisico> listarTodos() {
        return repositorio.findAll();
    }
}

```

