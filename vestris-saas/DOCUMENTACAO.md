# Documentação do projeto vestris-saas

## Índice de Diretórios e Arquivos

- [.](#)
  - [pom.xml](#pomxml)
- [src\main\java\br\com\vestris\saas\application](#srcmainjavabrcomvestrissaasapplication)
  - [ServiceAssinatura.java](#srcmainjavabrcomvestrissaasapplicationserviceassinaturajava)
  - [ServiceCadastroSaas.java](#srcmainjavabrcomvestrissaasapplicationservicecadastrosaasjava)
  - [ServicePlano.java](#srcmainjavabrcomvestrissaasapplicationserviceplanojava)
- [src\main\java\br\com\vestris\saas\domain\model](#srcmainjavabrcomvestrissaasdomainmodel)
  - [Assinatura.java](#srcmainjavabrcomvestrissaasdomainmodelassinaturajava)
  - [Plano.java](#srcmainjavabrcomvestrissaasdomainmodelplanojava)
- [src\main\java\br\com\vestris\saas\domain\repository](#srcmainjavabrcomvestrissaasdomainrepository)
  - [RepositorioAssinatura.java](#srcmainjavabrcomvestrissaasdomainrepositoryrepositorioassinaturajava)
  - [RepositorioPlano.java](#srcmainjavabrcomvestrissaasdomainrepositoryrepositorioplanojava)
- [src\main\java\br\com\vestris\saas\interfaces\api](#srcmainjavabrcomvestrissaasinterfacesapi)
  - [WebhookController.java](#srcmainjavabrcomvestrissaasinterfacesapiwebhookcontrollerjava)
- [src\main\java\br\com\vestris\saas\interfaces\delegate](#srcmainjavabrcomvestrissaasinterfacesdelegate)
  - [ApiDelegateAssinaturas.java](#srcmainjavabrcomvestrissaasinterfacesdelegateapidelegateassinaturasjava)
  - [ApiDelegateCadastroSaas.java](#srcmainjavabrcomvestrissaasinterfacesdelegateapidelegatecadastrosaasjava)
  - [ApiDelegatePlanos.java](#srcmainjavabrcomvestrissaasinterfacesdelegateapidelegateplanosjava)
- [src\main\java\br\com\vestris\saas\interfaces\dto](#srcmainjavabrcomvestrissaasinterfacesdto)
  - [WebhookMP.java](#srcmainjavabrcomvestrissaasinterfacesdtowebhookmpjava)
- [src\main\resources\swagger](#srcmainresourcesswagger)
  - [openapi.yml](#srcmainresourcesswaggeropenapiyml)
- [src\main\resources\swagger\components](#srcmainresourcesswaggercomponents)
  - [schemas.yml](#srcmainresourcesswaggercomponentsschemasyml)
- [src\main\resources\swagger\paths](#srcmainresourcesswaggerpaths)
  - [assinaturas.yml](#srcmainresourcesswaggerpathsassinaturasyml)
  - [auth-public.yml](#srcmainresourcesswaggerpathsauth-publicyml)
  - [planos.yml](#srcmainresourcesswaggerpathsplanosyml)

## .

### pom.xml

```xml
<!-- pom.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>br.com.vestris</groupId>
        <artifactId>vestris-backend</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>

    <artifactId>vestris-saas</artifactId>

    <dependencies>
        <!-- 1. Shared (Padrão) -->
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-shared</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <!-- 2. IMPORTANTE: Dependência de Usuário (Para vincular Assinatura -> Clínica) -->
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-user</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <!-- Swagger e Web -->
        <dependency>
            <groupId>io.swagger.core.v3</groupId>
            <artifactId>swagger-annotations</artifactId>
            <version>2.2.19</version>
        </dependency>
        <dependency>
            <groupId>io.swagger.core.v3</groupId>
            <artifactId>swagger-models</artifactId>
            <version>2.2.19</version>

        </dependency>
        <dependency>
            <groupId>jakarta.validation</groupId>
            <artifactId>jakarta.validation-api</artifactId>
        </dependency>
        <dependency>
            <groupId>org.openapitools</groupId>
            <artifactId>jackson-databind-nullable</artifactId>
            <version>0.2.6</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.openapitools</groupId>
                <artifactId>openapi-generator-maven-plugin</artifactId>
                <version>7.1.0</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>generate</goal>
                        </goals>
                        <configuration>
                            <inputSpec>${project.basedir}/src/main/resources/swagger/openapi.yml</inputSpec>
                            <generatorName>spring</generatorName>
                            <output>${project.build.directory}/generated-sources/openapi</output>

                            <!-- CORREÇÃO AQUI: Mudado de 'reference' para 'saas' -->
                            <apiPackage>br.com.vestris.saas.interfaces.api</apiPackage>
                            <modelPackage>br.com.vestris.saas.interfaces.dto</modelPackage>

                            <generateApiTests>false</generateApiTests>
                            <generateModelTests>false</generateModelTests>
                            <generateSupportingFiles>true</generateSupportingFiles>
                            <supportingFilesToGenerate>ApiUtil.java</supportingFilesToGenerate>

                            <configOptions>
                                <delegatePattern>true</delegatePattern>
                                <interfaceOnly>false</interfaceOnly>
                                <useSpringBoot3>true</useSpringBoot3>
                                <useTags>true</useTags>
                            </configOptions>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

</project>
```

---

## src\main\java\br\com\vestris\saas\application

### ServiceAssinatura.java

```java
// src\main\java\br\com\vestris\saas\application\ServiceAssinatura.java
package br.com.vestris.saas.application;

import br.com.vestris.saas.domain.model.Assinatura;
import br.com.vestris.saas.domain.model.Plano;
import br.com.vestris.saas.domain.repository.RepositorioAssinatura;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.user.application.ValidadorPlanoService;
import br.com.vestris.user.domain.model.Clinica;
import br.com.vestris.user.domain.repository.RepositorioClinica;
import br.com.vestris.user.domain.repository.RepositorioUsuario;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceAssinatura implements ValidadorPlanoService {
    private final RepositorioAssinatura repoAssinatura;
    private final RepositorioClinica repoClinica;
    private final RepositorioUsuario repoUsuario;
    private final ServicePlano servicePlano;

    public Assinatura buscarPorClinica(UUID clinicaId) {
        return repoAssinatura.findByClinicaId(clinicaId).orElse(null);
    }

    @Transactional
    public Assinatura assinarPlano(UUID clinicaId, UUID planoId, String ciclo) {
        // ... (código igual ao anterior) ...
        Clinica clinica = repoClinica.findById(clinicaId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Clínica", clinicaId.toString()));
        Plano novoPlano = servicePlano.buscarPorId(planoId);
        Assinatura assinatura = repoAssinatura.findByClinicaId(clinicaId).orElse(new Assinatura());
        assinatura.setClinica(clinica);
        assinatura.setPlano(novoPlano);
        assinatura.setStatus(Assinatura.StatusAssinatura.ATIVO);
        assinatura.setTipoFaturamento(novoPlano.isCustom() ? Assinatura.TipoFaturamento.MANUAL : Assinatura.TipoFaturamento.AUTO);
        assinatura.setDataInicio(LocalDateTime.now());
        if ("ANUAL".equalsIgnoreCase(ciclo)) {
            assinatura.setDataFim(LocalDateTime.now().plusYears(1));
        } else {
            assinatura.setDataFim(LocalDateTime.now().plusMonths(1));
        }
        return repoAssinatura.save(assinatura);
    }

    // --- IMPLEMENTAÇÃO DO CONTRATO ---
    @Override
    public void validarLimiteVeterinarios(UUID clinicaId) {
        // 1. Busca Assinatura
        Assinatura assinatura = buscarPorClinica(clinicaId);

        // 2. Valida Status (Ativo ou Trial)
        if (assinatura == null ||
                (assinatura.getStatus() != Assinatura.StatusAssinatura.ATIVO &&
                        assinatura.getStatus() != Assinatura.StatusAssinatura.TRIAL)) {

            throw new ExcecaoRegraNegocio("Sua clínica não possui uma assinatura ativa. Regularize o plano para gerenciar a equipe.");
        }

        // 3. Verifica Limites
        int limiteMaximo = assinatura.getPlano().getMaxVeterinarios();
        long totalAtual = repoUsuario.countByClinicaId(clinicaId);

        if (totalAtual >= limiteMaximo) {
            throw new ExcecaoRegraNegocio(
                    String.format("Limite do plano atingido (%d/%d). Faça upgrade para adicionar mais veterinários.",
                            totalAtual, limiteMaximo)
            );
        }
    }
}

```

### ServiceCadastroSaas.java

```java
// src\main\java\br\com\vestris\saas\application\ServiceCadastroSaas.java
package br.com.vestris.saas.application;

import br.com.vestris.saas.domain.model.Assinatura;
import br.com.vestris.saas.domain.repository.RepositorioAssinatura;
import br.com.vestris.saas.domain.repository.RepositorioPlano;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.user.domain.model.Clinica;
import br.com.vestris.user.domain.model.Perfil;
import br.com.vestris.user.domain.model.Usuario;
import br.com.vestris.user.domain.repository.RepositorioClinica;
import br.com.vestris.user.domain.repository.RepositorioUsuario;
import br.com.vestris.user.infrastructure.security.ServicoToken;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceCadastroSaas {
    private final RepositorioUsuario repoUsuario;
    private final RepositorioClinica repoClinica;
    private final RepositorioAssinatura repoAssinatura;
    private final RepositorioPlano repoPlano;
    private final PasswordEncoder passwordEncoder;
    private final ServicoToken servicoToken;

    @Transactional
    public String registrarCliente(String nome, String email, String senha, String crmv, String nomeClinica, UUID planoId) {
        // 1. Valida E-mail
        if (repoUsuario.existsByEmail(email)) {
            throw new ExcecaoRegraNegocio("Este e-mail já está em uso.");
        }

        // 2. Cria Clínica
        Clinica clinica = new Clinica();
        clinica.setNomeFantasia(nomeClinica);
        clinica = repoClinica.save(clinica);

        // 3. Cria Usuário Admin
        Usuario admin = new Usuario();
        admin.setNome(nome);
        admin.setEmail(email);
        admin.setSenha(passwordEncoder.encode(senha));
        admin.setCrmv(crmv);
        admin.setPerfil(Perfil.ADMIN_GESTOR); // Dono da clínica
        admin.setScope(Usuario.UserScope.TENANT);
        admin.setClinica(clinica);
        admin = repoUsuario.save(admin);

        // 4. Cria Assinatura (JÁ ATIVA PARA TESTE, depois mudamos para TRIAL ou PENDENTE)
        var plano = repoPlano.findById(planoId)
                .orElseThrow(() -> new ExcecaoRegraNegocio("Plano inválido"));

        Assinatura assinatura = new Assinatura();
        assinatura.setClinica(clinica);
        assinatura.setPlano(plano);
        assinatura.setStatus(Assinatura.StatusAssinatura.ATIVO); // <--- ATIVO DIRETO
        assinatura.setTipoFaturamento(Assinatura.TipoFaturamento.AUTO);
        assinatura.setDataInicio(LocalDateTime.now());
        assinatura.setDataFim(LocalDateTime.now().plusMonths(1)); // 1 mês grátis/teste

        repoAssinatura.save(assinatura);

        // 5. Retorna Token de Login Imediato
        return servicoToken.gerarToken(admin);
    }
}

```

### ServicePlano.java

```java
// src\main\java\br\com\vestris\saas\application\ServicePlano.java
package br.com.vestris.saas.application;

import br.com.vestris.saas.domain.model.Plano;
import br.com.vestris.saas.domain.repository.RepositorioPlano;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ServicePlano {
    private final RepositorioPlano repositorio;

    public List<Plano> listarTodos() {
        return repositorio.findAll();
    }

    public Plano buscarPorId(UUID id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Plano", id.toString()));
    }

    // Método para Admin criar planos (futuro)
    public Plano criar(Plano plano) {
        return repositorio.save(plano);
    }
}

```

---

## src\main\java\br\com\vestris\saas\domain\model

### Assinatura.java

```java
// src\main\java\br\com\vestris\saas\domain\model\Assinatura.java
package br.com.vestris.saas.domain.model;

import br.com.vestris.user.domain.model.Clinica;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import br.com.vestris.shared.domain.model.EntidadeBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "assinaturas", schema = "saas_schema")
public class Assinatura extends EntidadeBase {
    @OneToOne(optional = false)
    @JoinColumn(name = "clinica_id", unique = true) // 1 Assinatura ativa por clínica
    private Clinica clinica;

    @ManyToOne(optional = false)
    @JoinColumn(name = "plano_id")
    private Plano plano;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAssinatura status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoFaturamento tipoFaturamento;

    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;      // Renovação
    private LocalDateTime dataTrialFim; // Expiração do Trial

    @Column(columnDefinition = "TEXT")
    private String overrideLimitsJson;

    public enum StatusAssinatura {
        TRIAL, ATIVO, BLOQUEADO, INADIMPLENTE, CANCELADO
    }

    public enum TipoFaturamento {
        AUTO, MANUAL
    }
}

```

### Plano.java

```java
// src\main\java\br\com\vestris\saas\domain\model\Plano.java
package br.com.vestris.saas.domain.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import jakarta.persistence.*;
import br.com.vestris.shared.domain.model.EntidadeBase;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "planos", schema = "saas_schema")
public class Plano extends EntidadeBase{
    @Column(nullable = false)
    private String nome;

    private String descricao;

    private BigDecimal precoMensal;
    private BigDecimal precoAnual;

    @Column(nullable = false)
    private Integer maxVeterinarios;

    @Column(nullable = false)
    private boolean isCustom; // True = Enterprise

    @Column(columnDefinition = "TEXT")
    private String featuresJson;
}

```

---

## src\main\java\br\com\vestris\saas\domain\repository

### RepositorioAssinatura.java

```java
// src\main\java\br\com\vestris\saas\domain\repository\RepositorioAssinatura.java
package br.com.vestris.saas.domain.repository;

import br.com.vestris.saas.domain.model.Assinatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RepositorioAssinatura extends JpaRepository<Assinatura, UUID> {
    // Busca a assinatura ativa da clínica
    Optional<Assinatura> findByClinicaId(UUID clinicaId);
}

```

### RepositorioPlano.java

```java
// src\main\java\br\com\vestris\saas\domain\repository\RepositorioPlano.java
package br.com.vestris.saas.domain.repository;

import br.com.vestris.saas.domain.model.Plano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface RepositorioPlano extends JpaRepository<Plano, UUID> {
}

```

---

## src\main\java\br\com\vestris\saas\interfaces\api

### WebhookController.java

```java
// src\main\java\br\com\vestris\saas\interfaces\api\WebhookController.java
package br.com.vestris.saas.interfaces.api;

import br.com.vestris.saas.application.ServiceAssinatura;
import br.com.vestris.saas.interfaces.dto.WebhookMP;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public/webhooks")
@RequiredArgsConstructor
public class WebhookController {
    private final ServiceAssinatura serviceAssinatura;

    @PostMapping("/mercadopago")
    public ResponseEntity<Void> receberNotificacao(@RequestBody WebhookMP payload) {
        System.out.println("🔔 Webhook Recebido: " + payload.getAction() + " | ID: " + payload.getData().getId());

        // Lógica simplificada: Se for 'payment.created' ou 'subscription_preapproval', processamos
        if ("payment.created".equals(payload.getAction()) || "created".equals(payload.getAction())) {
            // Aqui você chamaria a API do Mercado Pago usando o ID recebido para saber quem pagou (email)
            // E atualizaria a assinatura no banco.
            // serviceAssinatura.processarPagamento(payload.getData().getId());
        }

        return ResponseEntity.ok().build(); // Sempre retornar 200 pro MP não ficar tentando de novo
    }
}

```

---

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

---

## src\main\java\br\com\vestris\saas\interfaces\dto

### WebhookMP.java

```java
// src\main\java\br\com\vestris\saas\interfaces\dto\WebhookMP.java
package br.com.vestris.saas.interfaces.dto;

import lombok.Data;

@Data
public class WebhookMP {
    private String action; // payment.created, etc
    private String type;
    private DataObj data;

    @Data
    public static class DataObj {
        private String id;
    }
}

```

---

## src\main\resources\swagger

### openapi.yml

```yaml
# src\main\resources\swagger\openapi.yml
openapi: 3.0.3
info:
  title: Vestris - Módulo SaaS
  description: Gestão de Planos, Assinaturas e Limites
  version: 1.0.0
servers:
  - url: http://localhost:8080

paths:
  # --- PLANOS ---
  /api/v1/saas/planos:
    $ref: './paths/planos.yml#/planos_colecao'

  /api/v1/saas/planos/{id}:
    $ref: './paths/planos.yml#/planos_item'

  # --- ASSINATURAS ---
  /api/v1/saas/minha-assinatura:
    $ref: './paths/assinaturas.yml#/assinatura_atual'

  /api/v1/saas/assinar:
    $ref: './paths/assinaturas.yml#/assinar_plano'

  /api/v1/public/cadastro-saas:
    $ref: './paths/auth-public.yml#/cadastro_saas'


components:
  schemas:
    # Requests
    PlanoRequest:
      $ref: "./components/schemas.yml#/PlanoRequest"
    AssinarPlanoRequest:
      $ref: "./components/schemas.yml#/AssinarPlanoRequest"

    # Responses
    PlanoResponse:
      $ref: "./components/schemas.yml#/PlanoResponse"
    AssinaturaResponse:
      $ref: "./components/schemas.yml#/AssinaturaResponse"

    # Enums
    StatusAssinaturaEnum:
      $ref: "./components/schemas.yml#/StatusAssinaturaEnum"
    TipoFaturamentoEnum:
      $ref: "./components/schemas.yml#/TipoFaturamentoEnum"

    CadastroSaasRequest:
      $ref: "./components//schemas.yml#/CadastroSaasRequest"
    ApiResponseToken:
      $ref: "./components/schemas.yml#/ApiResponseToken"
    TokenResponse:
      $ref: "./components/schemas.yml#/TokenResponse"
    # Wrappers
    ApiResponsePlano:
      $ref: "./components/schemas.yml#/ApiResponsePlano"
    ApiResponseListaPlano:
      $ref: "./components/schemas.yml#/ApiResponseListaPlano"
    ApiResponseAssinatura:
      $ref: "./components/schemas.yml#/ApiResponseAssinatura"
```

---

## src\main\resources\swagger\components

### schemas.yml

```yaml
# src\main\resources\swagger\components\schemas.yml
# --- ENUMS ---
StatusAssinaturaEnum:
  type: string
  enum: [TRIAL, ATIVO, BLOQUEADO, INADIMPLENTE, CANCELADO]

TipoFaturamentoEnum:
  type: string
  enum: [AUTO, MANUAL]

# --- PLANO (CATÁLOGO) ---
PlanoRequest:
  type: object
  required: [ nome, maxVeterinarios ]
  properties:
    nome:
      type: string
      example: "Clínica Pequena"
    descricao:
      type: string
    precoMensal:
      type: number
      format: double
      example: 249.00
    precoAnual:
      type: number
      format: double
      example: 2490.00
    maxVeterinarios:
      type: integer
      example: 3
    isCustom:
      type: boolean
      default: false
    featuresJson:
      type: string
      description: "JSON contendo flags (ex: branding, api, export)"

PlanoResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    nome: { type: string }
    descricao: { type: string }
    precoMensal: { type: number, format: double }
    precoAnual: { type: number, format: double }
    maxVeterinarios: { type: integer }
    isCustom: { type: boolean }
    featuresJson: { type: string }

# --- ASSINATURA (CONTRATO) ---
AssinaturaResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    clinicaId: { type: string, format: uuid }
    plano: { $ref: '#/PlanoResponse' }
    status: { $ref: '#/StatusAssinaturaEnum' }
    tipoFaturamento: { $ref: '#/TipoFaturamentoEnum' }
    dataInicio: { type: string, format: date-time }
    dataFim: { type: string, format: date-time }
    dataTrialFim: { type: string, format: date-time }

# Request para iniciar/trocar plano
AssinarPlanoRequest:
  type: object
  required: [ planoId, ciclo ]
  properties:
    planoId:
      type: string
      format: uuid
    ciclo:
      type: string
      enum: [MENSAL, ANUAL]

CadastroSaasRequest:
  type: object
  required: [ nomeUsuario, email, senha, crmv, nomeClinica, planoId ]
  properties:
    nomeUsuario:
      type: string
    email:
      type: string
      format: email
    senha:
      type: string
    crmv:
      type: string
    nomeClinica:
      type: string
    planoId:
      type: string
      format: uuid

TokenResponse:
  type: object
  properties:
    token:
      type: string
    tipo:
      type: string
      example: "Bearer"
    expiraEm:
      type: string


ApiResponseToken:
  type: object
  properties:
    sucesso: { type: boolean }
    mensagem: { type: string }
    dados: { $ref: '#/TokenResponse' }

# --- WRAPPERS ---
ApiResponsePlano:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { $ref: '#/PlanoResponse' }

ApiResponseListaPlano:
  type: object
  properties:
    sucesso: { type: boolean }
    dados:
      type: array
      items: { $ref: '#/PlanoResponse' }

ApiResponseAssinatura:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { $ref: '#/AssinaturaResponse' }
```

---

## src\main\resources\swagger\paths

### assinaturas.yml

```yaml
# src\main\resources\swagger\paths\assinaturas.yml
# ROTA: /api/v1/saas/minha-assinatura
assinatura_atual:
  get:
    tags: [Assinaturas]
    summary: Obter status da assinatura da clínica logada
    operationId: obterMinhaAssinatura
    parameters:
      - name: clinicaId
        in: query
        required: true
        schema: { type: string, format: uuid }
    responses:
      '200':
        description: Detalhes da assinatura vigente
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseAssinatura' }

# ROTA: /api/v1/saas/assinar
assinar_plano:
  post:
    tags: [Assinaturas]
    summary: Contratar ou mudar de plano
    description: "Inicia o fluxo de checkout ou altera o plano imediatamente se for upgrade gratuito/trial"
    operationId: assinarPlano
    parameters:
      - name: clinicaId
        in: query
        required: true
        schema: { type: string, format: uuid }
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/AssinarPlanoRequest' }
    responses:
      '200':
        description: Sucesso (ou Link de Pagamento no futuro)
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseAssinatura' }
```

### auth-public.yml

```yaml
# src\main\resources\swagger\paths\auth-public.yml
# ROTA: /api/v1/public/cadastro-saas
cadastro_saas:
  post:
    tags: [Publico]
    summary: Criar conta com plano e clínica
    operationId: cadastrarClienteSaas
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/CadastroSaasRequest' }
    responses:
      '201':
        description: Conta criada e logada
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseToken' }
```

### planos.yml

```yaml
# src\main\resources\swagger\paths\planos.yml
# ROTA: /api/v1/saas/planos
planos_colecao:
  get:
    tags: [Planos]
    summary: Listar planos disponíveis
    operationId: listarPlanos
    responses:
      '200':
        description: Lista de planos
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseListaPlano' }

  post:
    tags: [Planos]
    summary: Criar novo plano (Super Admin)
    operationId: criarPlano
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/PlanoRequest' }
    responses:
      '201':
        description: Plano criado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponsePlano' }

# ROTA: /api/v1/saas/planos/{id}
planos_item:
  get:
    tags: [Planos]
    summary: Buscar plano por ID
    operationId: buscarPlanoPorId
    parameters:
      - name: id
        in: path
        required: true
        schema: { type: string, format: uuid }
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponsePlano' }
```

