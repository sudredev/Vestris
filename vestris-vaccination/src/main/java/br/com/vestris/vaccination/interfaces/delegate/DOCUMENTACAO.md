## src\main\java\br\com\vestris\vaccination\interfaces\delegate

### ApiDelegateAplicacaoVacina.java

```java
// src\main\java\br\com\vestris\vaccination\interfaces\delegate\ApiDelegateAplicacaoVacina.java
package br.com.vestris.vaccination.interfaces.delegate;

import br.com.vestris.user.application.ServiceUsuario;
import br.com.vestris.vaccination.application.ServiceAplicacaoVacina;
import br.com.vestris.vaccination.application.ServiceVacinacao;
import br.com.vestris.vaccination.domain.model.AplicacaoVacina;
import br.com.vestris.vaccination.interfaces.api.VacinacaoPacienteApiDelegate;
import br.com.vestris.vaccination.interfaces.dto.ApiResponseAplicacaoVacina;
import br.com.vestris.vaccination.interfaces.dto.ApiResponseListaAplicacaoVacina;
import br.com.vestris.vaccination.interfaces.dto.AplicacaoVacinaRequest;
import br.com.vestris.vaccination.interfaces.dto.AplicacaoVacinaResponse;
import lombok.RequiredArgsConstructor;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateAplicacaoVacina implements VacinacaoPacienteApiDelegate {
    private final ServiceAplicacaoVacina servico;
    private final ServiceVacinacao serviceVacinacao;
    private final ServiceUsuario serviceUsuario;

    @Override
    public ResponseEntity<ApiResponseAplicacaoVacina> registrarVacinaPaciente(UUID pacienteId, UUID veterinarioId, AplicacaoVacinaRequest request) {
        AplicacaoVacina app = new AplicacaoVacina();
        app.setVacinaId(request.getVacinaId());

        // Data de Aplicação é obrigatória, então vem direto
        app.setDataAplicacao(request.getDataAplicacao());

        // --- CORREÇÃO: Usando unwrap para Data Próxima Dose (que é opcional/nullable) ---
        app.setDataProximaDose(unwrap(request.getDataProximaDose()));
        // -------------------------------------------------------------------------------

        app.setLote(request.getLote());
        app.setObservacoes(request.getObservacoes());
        app.setVeterinarioId(veterinarioId);

        AplicacaoVacina salva = servico.registrar(app, pacienteId);

        return ResponseEntity.status(201).body(criarResponse(salva));
    }

    @Override
    public ResponseEntity<ApiResponseListaAplicacaoVacina> listarVacinasDoPaciente(UUID pacienteId) {
        List<AplicacaoVacinaResponse> lista = servico.listarPorPaciente(pacienteId).stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaAplicacaoVacina response = new ApiResponseListaAplicacaoVacina();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deletarVacinaAplicada(UUID id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // --- HELPER PARA DESEMBRULHAR JSONNULLABLE ---
    private <T> T unwrap(JsonNullable<T> nullable) {
        if (nullable == null || !nullable.isPresent()) {
            return null;
        }
        return nullable.get();
    }

    // --- CONVERSORES ---

    private ApiResponseAplicacaoVacina criarResponse(AplicacaoVacina app) {
        ApiResponseAplicacaoVacina r = new ApiResponseAplicacaoVacina();
        r.setSucesso(true);
        r.setDados(converter(app));
        return r;
    }

    private AplicacaoVacinaResponse converter(AplicacaoVacina app) {
        AplicacaoVacinaResponse dto = new AplicacaoVacinaResponse();
        dto.setId(app.getId());
        dto.setPacienteId(app.getPaciente().getId());
        dto.setVacinaId(app.getVacinaId());
        dto.setDataAplicacao(app.getDataAplicacao());
        dto.setDataProximaDose(app.getDataProximaDose());
        dto.setLote(app.getLote());
        dto.setObservacoes(app.getObservacoes());

        // Enriquecimento com nome da vacina
        try {
            dto.setVacinaNome(serviceVacinacao.buscarNomePorId(app.getVacinaId()));
        } catch (Exception e) {
            dto.setVacinaNome("Vacina não identificada");
        }

        // Enriquecimento com nome do vet
        try {
            var vet = serviceUsuario.buscarPorId(app.getVeterinarioId());
            dto.setVeterinarioNome(vet.getNome());
        } catch (Exception e) {
            dto.setVeterinarioNome("Veterinário");
        }

        return dto;
    }

}

```

### ApiDelegateProtocoloVacinal.java

```java
// src\main\java\br\com\vestris\vaccination\interfaces\delegate\ApiDelegateProtocoloVacinal.java
package br.com.vestris.vaccination.interfaces.delegate;

import br.com.vestris.vaccination.application.ServiceProtocoloVacinacao;
import br.com.vestris.vaccination.domain.model.ProtocoloVacinacao;
import br.com.vestris.vaccination.interfaces.api.ProtocolosVacinaisApiDelegate;
import br.com.vestris.vaccination.interfaces.dto.ApiResponseListaProtocoloVacinal;
import br.com.vestris.vaccination.interfaces.dto.ApiResponseProtocoloVacinal;
import br.com.vestris.vaccination.interfaces.dto.ProtocoloVacinalRequest;
import br.com.vestris.vaccination.interfaces.dto.ProtocoloVacinalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateProtocoloVacinal implements ProtocolosVacinaisApiDelegate {
    private final ServiceProtocoloVacinacao servico;

    @Override
    public ResponseEntity<ApiResponseProtocoloVacinal> criarProtocoloVacinal(ProtocoloVacinalRequest request) {
        ProtocoloVacinacao entidade = new ProtocoloVacinacao();
        entidade.setEspecieId(request.getEspecieId());
        entidade.setReferenciaId(request.getReferenciaId());
        entidade.setIdadeMinimaDias(request.getIdadeMinimaDias());
        entidade.setDiasParaReforco(request.getDiasParaReforco());
        entidade.setObrigatoria(request.getObrigatoria());
        entidade.setObservacoes(request.getObservacoes());

        ProtocoloVacinacao salvo = servico.criar(entidade, request.getVacinaId());

        ApiResponseProtocoloVacinal response = new ApiResponseProtocoloVacinal();
        response.setSucesso(true);
        response.setDados(converter(salvo));

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseListaProtocoloVacinal> listarProtocolosPorEspecie(UUID especieId) {
        List<ProtocoloVacinalResponse> lista = servico.listarPorEspecie(especieId).stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaProtocoloVacinal response = new ApiResponseListaProtocoloVacinal();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    private ProtocoloVacinalResponse converter(ProtocoloVacinacao p) {
        ProtocoloVacinalResponse dto = new ProtocoloVacinalResponse();
        dto.setId(p.getId());
        dto.setEspecieId(p.getEspecieId());
        dto.setReferenciaId(p.getReferenciaId());
        dto.setVacinaId(p.getVacina().getId());
        dto.setNomeVacina(p.getVacina().getNome()); // Informação útil para o front
        dto.setIdadeMinimaDias(p.getIdadeMinimaDias());
        dto.setDiasParaReforco(p.getDiasParaReforco());
        dto.setObrigatoria(p.isObrigatoria());
        dto.setObservacoes(p.getObservacoes());
        return dto;
    }

    @Override
    public ResponseEntity<ApiResponseProtocoloVacinal> buscarProtocoloVacinalPorId(UUID id) {
        ProtocoloVacinacao p = servico.buscarPorId(id);
        ApiResponseProtocoloVacinal response = new ApiResponseProtocoloVacinal();
        response.setSucesso(true);
        response.setDados(converter(p));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseProtocoloVacinal> atualizarProtocoloVacinal(UUID id, ProtocoloVacinalRequest request) {
        ProtocoloVacinacao atualizado = servico.atualizar(
                id,
                request.getEspecieId(),
                request.getVacinaId(),
                request.getReferenciaId(),
                request.getIdadeMinimaDias(),
                request.getDiasParaReforco(),
                request.getObrigatoria(),
                request.getObservacoes()
        );

        ApiResponseProtocoloVacinal response = new ApiResponseProtocoloVacinal();
        response.setSucesso(true);
        response.setMensagem("Protocolo atualizado.");
        response.setDados(converter(atualizado));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deletarProtocoloVacinal(UUID id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

```

### ApiDelegateVacinas.java

```java
// src\main\java\br\com\vestris\vaccination\interfaces\delegate\ApiDelegateVacinas.java
package br.com.vestris.vaccination.interfaces.delegate;

import br.com.vestris.vaccination.application.ServiceVacinacao;
import br.com.vestris.vaccination.domain.model.Vacina;
import br.com.vestris.vaccination.interfaces.api.VacinasApiDelegate;
import br.com.vestris.vaccination.interfaces.dto.ApiResponseListaVacina;
import br.com.vestris.vaccination.interfaces.dto.ApiResponseVacina;
import br.com.vestris.vaccination.interfaces.dto.VacinaRequest;
import br.com.vestris.vaccination.interfaces.dto.VacinaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateVacinas implements VacinasApiDelegate {

    private final ServiceVacinacao servico;

    @Override
    public ResponseEntity<ApiResponseVacina> criarVacina(VacinaRequest request) {
        Vacina entidade = new Vacina();
        entidade.setNome(request.getNome());
        entidade.setFabricante(request.getFabricante());
        entidade.setTipoVacina(request.getTipoVacina());
        entidade.setDescricao(request.getDescricao());
        entidade.setDoencaAlvo(request.getDoencaAlvo());

        Vacina salva = servico.criar(entidade);

        ApiResponseVacina response = new ApiResponseVacina();
        response.setSucesso(true);
        response.setDados(converter(salva));

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseListaVacina> listarVacinas() {
        List<VacinaResponse> lista = servico.listarTodas().stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaVacina response = new ApiResponseListaVacina();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    private VacinaResponse converter(Vacina v) {
        VacinaResponse dto = new VacinaResponse();
        dto.setId(v.getId());
        dto.setNome(v.getNome());
        dto.setFabricante(v.getFabricante());
        dto.setTipoVacina(v.getTipoVacina());
        dto.setDescricao(v.getDescricao());
        dto.setDoencaAlvo(v.getDoencaAlvo());

        if (v.getCriadoEm() != null) {
            dto.setCriadoEm(v.getCriadoEm().atOffset(ZoneOffset.UTC));
        }
        return dto;
    }

    @Override
    public ResponseEntity<ApiResponseVacina> buscarVacinaPorId(UUID id) {
        Vacina v = servico.buscarPorId(id);
        ApiResponseVacina response = new ApiResponseVacina();
        response.setSucesso(true);
        response.setDados(converter(v));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseVacina> atualizarVacina(UUID id, VacinaRequest request) {
        Vacina dados = new Vacina();
        dados.setNome(request.getNome());
        dados.setFabricante(request.getFabricante());
        dados.setTipoVacina(request.getTipoVacina());
        dados.setDescricao(request.getDescricao());
        dados.setDoencaAlvo(request.getDoencaAlvo());

        Vacina atualizada = servico.atualizar(id, dados);

        ApiResponseVacina response = new ApiResponseVacina();
        response.setSucesso(true);
        response.setMensagem("Vacina atualizada.");
        response.setDados(converter(atualizada));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deletarVacina(UUID id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

```

