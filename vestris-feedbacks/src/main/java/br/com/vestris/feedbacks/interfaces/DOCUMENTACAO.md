## src\main\java\br\com\vestris\feedbacks\interfaces

### ApiDelegateSugestao.java

```java
// src\main\java\br\com\vestris\feedbacks\interfaces\ApiDelegateSugestao.java
package br.com.vestris.feedbacks.interfaces;

import br.com.vestris.feedback.interfaces.api.SugestoesApiDelegate;
import br.com.vestris.feedback.interfaces.dto.*;
import br.com.vestris.feedbacks.application.ServiceSugestao;
import br.com.vestris.feedbacks.domain.Sugestao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateSugestao implements SugestoesApiDelegate {

    private final ServiceSugestao servico;

    @Override
    public ResponseEntity<ApiResponseSugestao> sugerirEspecie(SugestaoRequest request) {
        return processar(request, Sugestao.TipoSugestao.ESPECIE);
    }

    @Override
    public ResponseEntity<ApiResponseSugestao> sugerirDoenca(SugestaoRequest request) {
        return processar(request, Sugestao.TipoSugestao.DOENCA);
    }

    @Override
    public ResponseEntity<ApiResponseSugestao> sugerirProtocolo(SugestaoRequest request) {
        return processar(request, Sugestao.TipoSugestao.PROTOCOLO);
    }

    @Override
    public ResponseEntity<ApiResponseSugestao> sugerirCalculo(SugestaoRequest request) {
        return processar(request, Sugestao.TipoSugestao.CALCULO);
    }

    // Método auxiliar para evitar repetição
    private ResponseEntity<ApiResponseSugestao> processar(SugestaoRequest req, Sugestao.TipoSugestao tipoForcado) {
        servico.registrar(req.getUsuarioId(), tipoForcado, req.getConteudo());

        ApiResponseSugestao response = new ApiResponseSugestao();
        response.setSucesso(true);
        response.setMensagem("Sugestão recebida! Nossa equipe científica irá analisar.");

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseListaSugestao> listarSugestoes(TipoSugestaoEnum tipoDTO, StatusSugestaoEnum statusDTO) {
        // Converte Enums do DTO para Enums do Domínio (Java)
        Sugestao.TipoSugestao tipoDomain = tipoDTO != null ? Sugestao.TipoSugestao.valueOf(tipoDTO.name()) : null;
        Sugestao.StatusSugestao statusDomain = statusDTO != null ? Sugestao.StatusSugestao.valueOf(statusDTO.name()) : null;

        List<SugestaoResponse> lista = servico.listar(tipoDomain, statusDomain).stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaSugestao response = new ApiResponseListaSugestao();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    private SugestaoResponse converter(Sugestao s) {
        SugestaoResponse dto = new SugestaoResponse();
        dto.setId(s.getId());
        dto.setUsuarioId(s.getUsuarioId());
        dto.setConteudo(s.getConteudo());
        dto.setTipo(TipoSugestaoEnum.valueOf(s.getTipo().name()));
        dto.setStatus(StatusSugestaoEnum.valueOf(s.getStatus().name()));
        if (s.getCriadoEm() != null) {
            dto.setCriadoEm(s.getCriadoEm().atOffset(ZoneOffset.UTC));
        }
        return dto;
    }

    @Override
    public ResponseEntity<ApiResponseSugestao> atualizarStatusSugestao(UUID id, AtualizarStatusRequest request) {
        // Converter Enum DTO -> Domain
        Sugestao.StatusSugestao statusDomain = Sugestao.StatusSugestao.valueOf(request.getStatus().name());

        servico.atualizarStatus(id, statusDomain);

        ApiResponseSugestao response = new ApiResponseSugestao();
        response.setSucesso(true);
        response.setMensagem("Status atualizado para " + statusDomain.name());

        return ResponseEntity.ok(response);
    }
}

```

