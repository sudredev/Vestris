## src\main\java\br\com\vestris\saas\interfaces\delegate

### ApiDelegateAssinaturas.java

```java
// src\main\java\br\com\vestris\saas\interfaces\delegate\ApiDelegateAssinaturas.java
package br.com.vestris.saas.interfaces.delegate;

import br.com.vestris.saas.application.ServiceAssinatura;
import br.com.vestris.saas.domain.model.Assinatura;
import br.com.vestris.saas.interfaces.api.AssinaturasApiDelegate;
import br.com.vestris.saas.interfaces.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ApiDelegateAssinaturas implements AssinaturasApiDelegate {

    private final ServiceAssinatura servico;

    @Override
    public ResponseEntity<ApiResponseAssinatura> obterMinhaAssinatura(UUID clinicaId) {
        Assinatura assinatura = servico.buscarPorClinica(clinicaId);

        ApiResponseAssinatura response = new ApiResponseAssinatura();

        if (assinatura == null) {
            // Retorna sucesso mas sem dados (Clínica sem plano ainda)
            response.setSucesso(true);
            response.setDados(null);
        } else {
            response.setSucesso(true);
            response.setDados(converter(assinatura));
        }

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseAssinatura> assinarPlano(UUID clinicaId, AssinarPlanoRequest request) {
        Assinatura nova = servico.assinarPlano(clinicaId, request.getPlanoId(), request.getCiclo().name());

        ApiResponseAssinatura response = new ApiResponseAssinatura();
        response.setSucesso(true);
        response.setDados(converter(nova));

        return ResponseEntity.ok(response);
    }

    private AssinaturaResponse converter(Assinatura a) {
        AssinaturaResponse dto = new AssinaturaResponse();
        dto.setId(a.getId());
        dto.setClinicaId(a.getClinica().getId());

        // Mapeia o Plano simplificado dentro da resposta
        PlanoResponse planoDto = new PlanoResponse();
        planoDto.setId(a.getPlano().getId());
        planoDto.setNome(a.getPlano().getNome());
        planoDto.setMaxVeterinarios(a.getPlano().getMaxVeterinarios());
        dto.setPlano(planoDto);

        dto.setStatus(StatusAssinaturaEnum.valueOf(a.getStatus().name()));
        dto.setTipoFaturamento(TipoFaturamentoEnum.valueOf(a.getTipoFaturamento().name()));

        if(a.getDataInicio() != null) dto.setDataInicio(a.getDataInicio().atOffset(ZoneOffset.UTC));
        if(a.getDataFim() != null) dto.setDataFim(a.getDataFim().atOffset(ZoneOffset.UTC));
        if(a.getDataTrialFim() != null) dto.setDataTrialFim(a.getDataTrialFim().atOffset(ZoneOffset.UTC));

        return dto;
    }
}

```

### ApiDelegateCadastroSaas.java

```java
// src\main\java\br\com\vestris\saas\interfaces\delegate\ApiDelegateCadastroSaas.java
package br.com.vestris.saas.interfaces.delegate;

import br.com.vestris.saas.application.ServiceCadastroSaas;
import br.com.vestris.saas.interfaces.api.PublicoApiDelegate;
import br.com.vestris.saas.interfaces.dto.ApiResponseToken;
import br.com.vestris.saas.interfaces.dto.CadastroSaasRequest;
import br.com.vestris.saas.interfaces.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiDelegateCadastroSaas implements PublicoApiDelegate {

    private final ServiceCadastroSaas serviceCadastro;

    @Override
    public ResponseEntity<ApiResponseToken> cadastrarClienteSaas(CadastroSaasRequest request) {

        // 1. Chama o serviço "Combo" que cria tudo e retorna o JWT
        String tokenJwt = serviceCadastro.registrarCliente(
                request.getNomeUsuario(),
                request.getEmail(),
                request.getSenha(),
                request.getCrmv(),
                request.getNomeClinica(),
                request.getPlanoId()
        );

        // 2. Monta o DTO de Token (específico do módulo SaaS)
        TokenResponse tokenDto = new TokenResponse();
        tokenDto.setToken(tokenJwt);
        tokenDto.setTipo("Bearer");
        tokenDto.setExpiraEm("24h"); // Ou pegar da config se preferir

        // 3. Monta o Wrapper de Resposta
        ApiResponseToken response = new ApiResponseToken();
        response.setSucesso(true);
        response.setMensagem("Conta criada e assinatura ativada com sucesso.");
        response.setDados(tokenDto);

        // 4. Retorna 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

```

### ApiDelegatePlanos.java

```java
// src\main\java\br\com\vestris\saas\interfaces\delegate\ApiDelegatePlanos.java
package br.com.vestris.saas.interfaces.delegate;

import br.com.vestris.saas.application.ServicePlano;
import br.com.vestris.saas.domain.model.Plano;
import br.com.vestris.saas.interfaces.api.PlanosApiDelegate;
import br.com.vestris.saas.interfaces.dto.ApiResponseListaPlano;
import br.com.vestris.saas.interfaces.dto.ApiResponsePlano;
import br.com.vestris.saas.interfaces.dto.PlanoRequest;
import br.com.vestris.saas.interfaces.dto.PlanoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegatePlanos implements PlanosApiDelegate {

    private final ServicePlano servico;

    @Override
    public ResponseEntity<ApiResponseListaPlano> listarPlanos() {
        List<PlanoResponse> lista = servico.listarTodos().stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaPlano response = new ApiResponseListaPlano();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponsePlano> buscarPlanoPorId(UUID id) {
        Plano plano = servico.buscarPorId(id);

        ApiResponsePlano response = new ApiResponsePlano();
        response.setSucesso(true);
        response.setDados(converter(plano));

        return ResponseEntity.ok(response);
    }

    // Método POST (Admin) - Se você definiu no YAML
    @Override
    public ResponseEntity<ApiResponsePlano> criarPlano(PlanoRequest request) {
        Plano p = new Plano();
        p.setNome(request.getNome());
        p.setDescricao(request.getDescricao());
        p.setPrecoMensal(BigDecimal.valueOf(request.getPrecoMensal()));
        p.setPrecoAnual(BigDecimal.valueOf(request.getPrecoAnual()));
        p.setMaxVeterinarios(request.getMaxVeterinarios());
        p.setCustom(request.getIsCustom());
        p.setFeaturesJson(request.getFeaturesJson());

        Plano salvo = servico.criar(p);

        ApiResponsePlano response = new ApiResponsePlano();
        response.setSucesso(true);
        response.setDados(converter(salvo));
        return ResponseEntity.ok(response);
    }

    private PlanoResponse converter(Plano p) {
        PlanoResponse dto = new PlanoResponse();
        dto.setId(p.getId());
        dto.setNome(p.getNome());
        dto.setDescricao(p.getDescricao());
        if(p.getPrecoMensal() != null) dto.setPrecoMensal(p.getPrecoMensal().doubleValue());
        if(p.getPrecoAnual() != null) dto.setPrecoAnual(p.getPrecoAnual().doubleValue());
        dto.setMaxVeterinarios(p.getMaxVeterinarios());
        dto.setIsCustom(p.isCustom());
        dto.setFeaturesJson(p.getFeaturesJson());
        return dto;
    }
}

```

