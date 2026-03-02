## src\main\java\br\com\vestris\reference\interfaces\delegate

### ApiDelegateReferencias.java

```java
// src\main\java\br\com\vestris\reference\interfaces\delegate\ApiDelegateReferencias.java
package br.com.vestris.reference.interfaces.delegate;

import br.com.vestris.reference.application.ServiceReferencia;
import br.com.vestris.reference.domain.model.ReferenciaBibliografica;
import br.com.vestris.reference.interfaces.api.ReferenciasApiDelegate;
import br.com.vestris.reference.interfaces.dto.ApiResponseListaReferencia;
import br.com.vestris.reference.interfaces.dto.ApiResponseReferencia;
import br.com.vestris.reference.interfaces.dto.ReferenciaRequest;
import br.com.vestris.reference.interfaces.dto.ReferenciaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateReferencias implements ReferenciasApiDelegate {

    private final ServiceReferencia servico;

    @Override
    public ResponseEntity<ApiResponseReferencia> criarReferencia(ReferenciaRequest request) {
        ReferenciaBibliografica entidade = new ReferenciaBibliografica();
        entidade.setTitulo(request.getTitulo());
        entidade.setAutores(request.getAutores());
        entidade.setAno(request.getAno());
        entidade.setFonte(request.getFonte());
        entidade.setUrl(request.getUrl());

        ReferenciaBibliografica salva = servico.criar(entidade);

        ApiResponseReferencia response = new ApiResponseReferencia();
        response.setSucesso(true);
        response.setDados(converter(salva));

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseListaReferencia> listarReferencias() {
        List<ReferenciaResponse> lista = servico.listarTodas().stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaReferencia response = new ApiResponseListaReferencia();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    private ReferenciaResponse converter(ReferenciaBibliografica ref) {
        ReferenciaResponse dto = new ReferenciaResponse();
        dto.setId(ref.getId());
        dto.setTitulo(ref.getTitulo());
        dto.setAutores(ref.getAutores());
        dto.setAno(ref.getAno());
        dto.setFonte(ref.getFonte());
        dto.setUrl(ref.getUrl());

        if (ref.getCriadoEm() != null) {
            dto.setCriadoEm(ref.getCriadoEm().atOffset(ZoneOffset.UTC));
        }
        return dto;
    }

    @Override
    public ResponseEntity<ApiResponseReferencia> buscarReferenciaPorId(UUID id) {
        // 1. Busca a entidade
        var entidade = servico.buscarPorId(id);

        // 2. Instancia a resposta (Sem Builder)
        ApiResponseReferencia response = new ApiResponseReferencia();
        response.setSucesso(true);
        response.setDados(converter(entidade));

        // 3. Retorna
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseReferencia> atualizarReferencia(UUID id, ReferenciaRequest request) {
        ReferenciaBibliografica dados = new ReferenciaBibliografica();
        dados.setTitulo(request.getTitulo());
        dados.setAutores(request.getAutores());
        dados.setAno(request.getAno());
        dados.setFonte(request.getFonte());
        dados.setUrl(request.getUrl());

        ReferenciaBibliografica atualizada = servico.atualizar(id, dados);

        ApiResponseReferencia response = new ApiResponseReferencia();
        response.setSucesso(true);
        response.setDados(converter(atualizada));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deletarReferencia(UUID id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

```

