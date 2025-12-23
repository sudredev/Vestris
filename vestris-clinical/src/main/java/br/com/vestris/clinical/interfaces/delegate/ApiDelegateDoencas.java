package br.com.vestris.clinical.interfaces.delegate;

import br.com.vestris.clinical.application.ServiceDoenca;
import br.com.vestris.clinical.domain.model.Doenca;
import br.com.vestris.clinical.interfaces.api.DoencasApiDelegate;
import br.com.vestris.clinical.interfaces.dto.ApiResponseDoenca;
import br.com.vestris.clinical.interfaces.dto.ApiResponseListaDoenca;
import br.com.vestris.clinical.interfaces.dto.DoencaRequest;
import br.com.vestris.clinical.interfaces.dto.DoencaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateDoencas implements DoencasApiDelegate {
    private final ServiceDoenca servico;

    @Override
    public ResponseEntity<ApiResponseDoenca> criarDoenca(DoencaRequest request) {
        // 1. Converter DTO Entidade
        Doenca entidade = new Doenca();
        entidade.setNome(request.getNome());
        entidade.setNomeCientifico(request.getNomeCientifico());
        entidade.setSintomas(request.getSintomas());
        // O Swagger gera UUID como UUID mesmo ou String, confira se precisa de UUID.fromString()
        entidade.setEspecieId(request.getEspecieId());

        // 2. Serviço
        Doenca salva = servico.criar(entidade);

        // 3. Converter Entidade DTO Response
        DoencaResponse response = converterParaDTO(salva);

        // 4. Wrapper
        ApiResponseDoenca wrapper = new ApiResponseDoenca();
        wrapper.setSucesso(true);
        wrapper.setMensagem("Doença cadastrada com sucesso.");
        wrapper.setDados(response);

        return ResponseEntity.ok(wrapper);
    }

    @Override
    public ResponseEntity<ApiResponseListaDoenca> listarDoencas() {
        List<DoencaResponse> lista = servico.listarTodas().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());

        ApiResponseListaDoenca wrapper = new ApiResponseListaDoenca();
        wrapper.setSucesso(true);
        wrapper.setDados(lista);

        return ResponseEntity.ok(wrapper);
    }

    @Override
    public ResponseEntity<ApiResponseListaDoenca> listarDoencasPorEspecie(UUID especieId) {
        List<DoencaResponse> lista = servico.listarPorEspecie(especieId).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());

        ApiResponseListaDoenca wrapper = new ApiResponseListaDoenca();
        wrapper.setSucesso(true);
        wrapper.setDados(lista);

        return ResponseEntity.ok(wrapper);
    }

    // Conversor Auxiliar
    private DoencaResponse converterParaDTO(Doenca entidade) {
        DoencaResponse dto = new DoencaResponse();
        dto.setId(entidade.getId());
        dto.setNome(entidade.getNome());
        dto.setNomeCientifico(entidade.getNomeCientifico());
        dto.setSintomas(entidade.getSintomas());
        dto.setEspecieId(entidade.getEspecieId());

        if (entidade.getCriadoEm() != null) {
            dto.setCriadoEm(entidade.getCriadoEm().atOffset(ZoneOffset.UTC));
        }
        return dto;
    }
}
