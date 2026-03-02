## src\main\java\br\com\vestris\species\interfaces\delegate

### ApiDelegateEspecies.java

```java
// src\main\java\br\com\vestris\species\interfaces\delegate\ApiDelegateEspecies.java
package br.com.vestris.species.interfaces.delegate;

import br.com.vestris.species.application.ServiceEspecie;
import br.com.vestris.species.domain.Especie;
import br.com.vestris.species.interfaces.api.EspeciesApiDelegate;
import br.com.vestris.species.interfaces.dto.ApiResponseEspecie;
import br.com.vestris.species.interfaces.dto.EspecieRequest;
import br.com.vestris.species.interfaces.dto.EspecieResponse;
import br.com.vestris.species.interfaces.dto.ApiResponseListaEspecie;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Component // O Delegate deve ser um Componente Spring
@RequiredArgsConstructor
public class ApiDelegateEspecies implements EspeciesApiDelegate {
    private final ServiceEspecie servico;

    @Override
    public ResponseEntity<ApiResponseEspecie> criarEspecie(EspecieRequest request) {
        Especie entidade = converterParaEntidade(request);
        Especie salva = servico.criar(entidade);

        return ResponseEntity.ok(criarResponse(salva, "Espécie criada com sucesso."));
    }

    @Override
    public ResponseEntity<ApiResponseListaEspecie> listarEspecies(){
        List<EspecieResponse> listaDto = servico.listarTodas().stream()
                .map(this::converterParaResponse)
                .collect(Collectors.toList());

        ApiResponseListaEspecie wrapper = new ApiResponseListaEspecie();
        wrapper.setSucesso(true);
        wrapper.setDados(listaDto);

        return ResponseEntity.ok(wrapper);
    }

    @Override
    public ResponseEntity<ApiResponseEspecie> buscarEspeciePorId(UUID id) {
        Especie encontrada = servico.buscarPorId(id);
        return ResponseEntity.ok(criarResponse(encontrada, null));
    }

    @Override
    public ResponseEntity<ApiResponseEspecie> atualizarEspecie(UUID id, EspecieRequest request) {
        Especie dados = converterParaEntidade(request);
        Especie atualizada = servico.atualizar(id, dados);
        return ResponseEntity.ok(criarResponse(atualizada, "Atualizado com sucesso."));
    }

    @Override
    public ResponseEntity<Void> deletarEspecie(UUID id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // --- CONVERSORES ---

    private ApiResponseEspecie criarResponse(Especie e, String msg) {
        ApiResponseEspecie r = new ApiResponseEspecie();
        r.setSucesso(true);
        r.setMensagem(msg);
        r.setDados(converterParaResponse(e));
        return r;
    }

    // DTO -> Entidade
    private Especie converterParaEntidade(EspecieRequest r) {
        Especie e = new Especie();
        e.setNomePopular(r.getNomePopular());
        e.setNomeCientifico(r.getNomeCientifico());
        e.setFamiliaTaxonomica(r.getFamiliaTaxonomica());
        e.setGrupo(r.getGrupo());

        e.setResumoClinico(r.getResumoClinico());
        e.setParametrosFisiologicos(r.getParametrosFisiologicos());
        e.setExpectativaVida(r.getExpectativaVida());
        e.setPesoAdulto(r.getPesoAdulto());

        e.setTipoDieta(r.getTipoDieta());
        e.setManejoInfos(r.getManejoInfos());
        e.setReceitaManejoPadrao(r.getReceitaManejoPadrao());
        e.setAlertasClinicos(r.getAlertasClinicos());
        e.setPontosExameFisico(r.getPontosExameFisico());

        e.setHabitat(r.getHabitat());
        e.setDistribuicaoGeografica(r.getDistribuicaoGeografica());
        e.setStatusConservacao(r.getStatusConservacao());
        e.setBibliografiaBase(r.getBibliografiaBase());

        return e;
    }

    // Entidade -> DTO
    private EspecieResponse converterParaResponse(Especie e) {
        EspecieResponse dto = new EspecieResponse();
        dto.setId(e.getId());

        dto.setNomePopular(e.getNomePopular());
        dto.setNomeCientifico(e.getNomeCientifico());
        dto.setFamiliaTaxonomica(e.getFamiliaTaxonomica());
        dto.setGrupo(e.getGrupo());

        dto.setResumoClinico(e.getResumoClinico());
        dto.setParametrosFisiologicos(e.getParametrosFisiologicos());
        dto.setExpectativaVida(e.getExpectativaVida());
        dto.setPesoAdulto(e.getPesoAdulto());

        dto.setTipoDieta(e.getTipoDieta());
        dto.setManejoInfos(e.getManejoInfos());
        dto.setReceitaManejoPadrao(e.getReceitaManejoPadrao());
        dto.setAlertasClinicos(e.getAlertasClinicos());
        dto.setPontosExameFisico(e.getPontosExameFisico());

        dto.setHabitat(e.getHabitat());
        dto.setDistribuicaoGeografica(e.getDistribuicaoGeografica());
        dto.setStatusConservacao(e.getStatusConservacao());
        dto.setBibliografiaBase(e.getBibliografiaBase());

        if (e.getCriadoEm() != null) {
            dto.setCriadoEm(e.getCriadoEm().atOffset(ZoneOffset.UTC));
        }

        return dto;
    }
}

```

### ApiDelegateModeloExame.java

```java
// src\main\java\br\com\vestris\species\interfaces\delegate\ApiDelegateModeloExame.java
package br.com.vestris.species.interfaces.delegate;

import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.species.application.ServiceModeloExame;
import br.com.vestris.species.domain.ModeloExameFisico;
import br.com.vestris.species.interfaces.api.ExamesFisicosApiDelegate;
import br.com.vestris.species.interfaces.dto.ApiResponseListaModeloExame;
import br.com.vestris.species.interfaces.dto.ApiResponseModeloExame;
import br.com.vestris.species.interfaces.dto.ModeloExameRequest;
import br.com.vestris.species.interfaces.dto.ModeloExameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateModeloExame implements ExamesFisicosApiDelegate {
    private final ServiceModeloExame servico;

    // --- GET (Buscar) ---
    @Override
    public ResponseEntity<ApiResponseModeloExame> obterModeloExame(UUID especieId) {
        try {
            ModeloExameFisico modelo = servico.buscarPorEspecie(especieId);
            return ResponseEntity.ok(criarResponse(modelo, "Modelo encontrado com sucesso."));
        } catch (ExceptionRecursoNaoEncontrado e) {
            // Se não encontrar o modelo, retornamos um 200 com dados nulos ou 404.
            // Para facilitar o front (evitar erro vermelho no console), vamos retornar 200 com dados null
            // e o front decide mostrar o "template genérico".
            ApiResponseModeloExame response = new ApiResponseModeloExame();
            response.setSucesso(false);
            response.setMensagem("Nenhum modelo específico encontrado. Use o padrão.");
            return ResponseEntity.ok(response);
        }
    }

    // --- POST (Criar) ---
    @Override
    public ResponseEntity<ApiResponseModeloExame> criarModeloExame(UUID especieId, ModeloExameRequest request) {
        ModeloExameFisico salvo = servico.criar(especieId, request.getTextoBase());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(criarResponse(salvo, "Modelo de exame físico criado com sucesso."));
    }

    // --- PUT (Atualizar) ---
    @Override
    public ResponseEntity<ApiResponseModeloExame> atualizarModeloExame(UUID especieId, ModeloExameRequest request) {
        ModeloExameFisico atualizado = servico.atualizar(especieId, request.getTextoBase());
        return ResponseEntity.ok(criarResponse(atualizado, "Modelo atualizado com sucesso."));
    }

    // --- DELETE (Remover) ---
    @Override
    public ResponseEntity<Void> deletarModeloExame(UUID especieId) {
        servico.deletar(especieId);
        return ResponseEntity.noContent().build();
    }

    // --- CONVERSOR AUXILIAR ---
    private ApiResponseModeloExame criarResponse(ModeloExameFisico entidade, String mensagem) {
        ModeloExameResponse dto = new ModeloExameResponse();
        dto.setId(entidade.getId());
        dto.setEspecieId(entidade.getEspecieId());
        dto.setTextoBase(entidade.getTextoBase());

        ApiResponseModeloExame wrapper = new ApiResponseModeloExame();
        wrapper.setSucesso(true);
        wrapper.setMensagem(mensagem);
        wrapper.setDados(dto);

        return wrapper;
    }

    @Override
    public ResponseEntity<ApiResponseListaModeloExame> listarTodosModelosExame() {
        List<ModeloExameResponse> listaDto = servico.listarTodos().stream()
                .map(entidade -> {
                    ModeloExameResponse dto = new ModeloExameResponse();
                    dto.setId(entidade.getId());
                    dto.setEspecieId(entidade.getEspecieId());
                    dto.setTextoBase(entidade.getTextoBase());
                    return dto;
                })
                .collect(Collectors.toList());

        ApiResponseListaModeloExame response = new ApiResponseListaModeloExame();
        response.setSucesso(true);
        response.setDados(listaDto);

        return ResponseEntity.ok(response);
    }
}

```

