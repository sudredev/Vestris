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
