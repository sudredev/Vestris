# Documentação do projeto vestris-feedbacks

## Índice de Diretórios e Arquivos

- [.](#)
  - [pom.xml](#pomxml)
- [src\main\java\br\com\vestris\feedbacks\application](#srcmainjavabrcomvestrisfeedbacksapplication)
  - [ServiceSugestao.java](#srcmainjavabrcomvestrisfeedbacksapplicationservicesugestaojava)
- [src\main\java\br\com\vestris\feedbacks\domain](#srcmainjavabrcomvestrisfeedbacksdomain)
  - [Sugestao.java](#srcmainjavabrcomvestrisfeedbacksdomainsugestaojava)
- [src\main\java\br\com\vestris\feedbacks\domain\repository](#srcmainjavabrcomvestrisfeedbacksdomainrepository)
  - [RepositorioSugestao.java](#srcmainjavabrcomvestrisfeedbacksdomainrepositoryrepositoriosugestaojava)
- [src\main\java\br\com\vestris\feedbacks\interfaces](#srcmainjavabrcomvestrisfeedbacksinterfaces)
  - [ApiDelegateSugestao.java](#srcmainjavabrcomvestrisfeedbacksinterfacesapidelegatesugestaojava)
- [src\main\resources\swagger](#srcmainresourcesswagger)
  - [openapi.yml](#srcmainresourcesswaggeropenapiyml)
- [src\main\resources\swagger\components](#srcmainresourcesswaggercomponents)
  - [schemas.yml](#srcmainresourcesswaggercomponentsschemasyml)
- [src\main\resources\swagger\paths](#srcmainresourcesswaggerpaths)
  - [sugestoes.yml](#srcmainresourcesswaggerpathssugestoesyml)

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

    <artifactId>vestris-feedback</artifactId>

    <dependencies>
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-shared</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-user</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <!-- Dependências Padrão Swagger/JPA -->
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
                            <apiPackage>br.com.vestris.feedback.interfaces.api</apiPackage>
                            <modelPackage>br.com.vestris.feedback.interfaces.dto</modelPackage>

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

## src\main\java\br\com\vestris\feedbacks\application

### ServiceSugestao.java

```java
// src\main\java\br\com\vestris\feedbacks\application\ServiceSugestao.java
package br.com.vestris.feedbacks.application;

import br.com.vestris.feedbacks.domain.Sugestao;
import br.com.vestris.feedbacks.domain.repository.RepositorioSugestao;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.user.application.ServiceUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceSugestao {
    private final RepositorioSugestao repositorio;
    private final ServiceUsuario servicoUsuario;

    public void registrar(UUID usuarioId, Sugestao.TipoSugestao tipo, String conteudo) {
        // Valida se quem sugeriu existe
        if (!servicoUsuario.existePorId(usuarioId)) {
            throw new ExcecaoRegraNegocio("Usuário não encontrado.");
        }

        Sugestao s = new Sugestao();
        s.setUsuarioId(usuarioId);
        s.setTipo(tipo);
        s.setConteudo(conteudo);
        s.setStatus(Sugestao.StatusSugestao.PENDENTE); // Sempre nasce pendente

        repositorio.save(s);
    }

    public List<Sugestao> listar(Sugestao.TipoSugestao tipo, Sugestao.StatusSugestao status) {
        if (tipo != null && status != null) {
            return repositorio.findByTipoAndStatus(tipo, status);
        } else if (tipo != null) {
            return repositorio.findByTipo(tipo);
        } else if (status != null) {
            return repositorio.findByStatus(status);
        } else {
            return repositorio.findAll();
        }
    }

    public void atualizarStatus(UUID id, Sugestao.StatusSugestao novoStatus) {
        Sugestao sugestao = repositorio.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Sugestão", id.toString()));

        sugestao.setStatus(novoStatus);
        repositorio.save(sugestao);
    }
}

```

---

## src\main\java\br\com\vestris\feedbacks\domain

### Sugestao.java

```java
// src\main\java\br\com\vestris\feedbacks\domain\Sugestao.java
package br.com.vestris.feedbacks.domain;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sugestoes", schema = "feedback_schema")
public class Sugestao extends EntidadeBase {
    @Column(nullable = false)
    private UUID usuarioId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoSugestao tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusSugestao status;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String conteudo; // Aqui guardamos o JSON ou Texto do que foi sugerido

    public enum TipoSugestao {
        ESPECIE, DOENCA, PROTOCOLO, CALCULO, OUTRO
    }

    public enum StatusSugestao {
        PENDENTE, EM_ANALISE, APROVADA, REJEITADA
    }
}

```

---

## src\main\java\br\com\vestris\feedbacks\domain\repository

### RepositorioSugestao.java

```java
// src\main\java\br\com\vestris\feedbacks\domain\repository\RepositorioSugestao.java
package br.com.vestris.feedbacks.domain.repository;

import br.com.vestris.feedbacks.domain.Sugestao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioSugestao extends JpaRepository<Sugestao, UUID> {
    List<Sugestao> findByTipo(Sugestao.TipoSugestao tipo);
    List<Sugestao> findByStatus(Sugestao.StatusSugestao status);
    List<Sugestao> findByTipoAndStatus(Sugestao.TipoSugestao tipo, Sugestao.StatusSugestao status);
}

```

---

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

---

## src\main\resources\swagger

### openapi.yml

```yaml
# src\main\resources\swagger\openapi.yml
openapi: 3.0.3
info:
  title: Vestris - Módulo Feedback
  description: Gestão de Sugestões e Melhorias
  version: 1.0.0
servers:
  - url: http://localhost:8080

paths:
  /api/v1/sugestoes/especies:
    $ref: './paths/sugestoes.yml#/sugestoes_especies'
  /api/v1/sugestoes/doencas:
    $ref: './paths/sugestoes.yml#/sugestoes_doencas'
  /api/v1/sugestoes/protocolos:
    $ref: './paths/sugestoes.yml#/sugestoes_protocolos'
  /api/v1/sugestoes/calculos:
    $ref: './paths/sugestoes.yml#/sugestoes_calculos'
  /api/v1/sugestoes:
    $ref: './paths/sugestoes.yml#/sugestoes_global'
  /api/v1/sugestoes/{id}/status:
    $ref: './paths/sugestoes.yml#/sugestoes_atualizar_status'

components:
  schemas:
    TipoSugestaoEnum:
      $ref: "./components/schemas.yml#/TipoSugestaoEnum"
    SugestaoRequest:
      $ref: "./components/schemas.yml#/SugestaoRequest"
    ApiResponseSugestao:
      $ref: "./components/schemas.yml#/ApiResponseSugestao"
    StatusSugestaoEnum:
      $ref: "./components/schemas.yml#/StatusSugestaoEnum"
    SugestaoResponse:
      $ref: "./components/schemas.yml#/SugestaoResponse"
    AtualizarStatusRequest:
      $ref: "./components/schemas.yml#/AtualizarStatusRequest"
    ApiResponseListaSugestao:
      $ref: "./components/schemas.yml#/ApiResponseListaSugestao"





```

---

## src\main\resources\swagger\components

### schemas.yml

```yaml
# src\main\resources\swagger\components\schemas.yml
TipoSugestaoEnum:
  type: string
  enum: [ESPECIE, DOENCA, PROTOCOLO, CALCULO, OUTRO]

# Request Genérico
SugestaoRequest:
  type: object
  required: [usuarioId, conteudo]
  properties:
    usuarioId:
      type: string
      format: uuid
    tipo:
      $ref: '#/TipoSugestaoEnum'
    conteudo:
      type: string
      description: "JSON stringify ou Texto descrevendo a sugestão (Nome, obs, contexto)"
      example: "{ 'nome': 'Papagaio-verdadeiro', 'obs': 'Comum em resgates' }"

StatusSugestaoEnum:
  type: string
  enum: [PENDENTE, EM_ANALISE, APROVADA, REJEITADA]

SugestaoResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    usuarioId: { type: string, format: uuid }
    tipo: { $ref: '#/TipoSugestaoEnum' }
    status: { $ref: '#/StatusSugestaoEnum' }
    conteudo: { type: string }
    criadoEm: { type: string, format: date-time }

# Wrappers
ApiResponseSugestao:
  type: object
  properties:
    sucesso: { type: boolean }
    mensagem: { type: string }

ApiResponseListaSugestao:
  type: object
  properties:
    sucesso: { type: boolean }
    dados:
      type: array
      items: { $ref: '#/SugestaoResponse' }

AtualizarStatusRequest:
  type: object
  required: [status]
  properties:
    status:
      $ref: '#/StatusSugestaoEnum'
```

---

## src\main\resources\swagger\paths

### sugestoes.yml

```yaml
# src\main\resources\swagger\paths\sugestoes.yml
# ROTA: /api/v1/sugestoes/especies
sugestoes_especies:
  post:
    tags: [Sugestoes]
    summary: Sugerir nova espécie
    operationId: sugerirEspecie
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/SugestaoRequest' }
    responses:
      '200':
        description: Recebido
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseSugestao' }

# ROTA: /api/v1/sugestoes/doencas
sugestoes_doencas:
  post:
    tags: [Sugestoes]
    summary: Sugerir doença
    operationId: sugerirDoenca
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/SugestaoRequest' }
    responses:
      '200':
        description: Recebido
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseSugestao' }

# ROTA: /api/v1/sugestoes/protocolos
sugestoes_protocolos:  
  post:
    tags: [Sugestoes]
    summary: Sugerir melhoria em protocolo
    operationId: sugerirProtocolo
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/SugestaoRequest' }
    responses:
      '200':
        description: Recebido
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseSugestao' }

#ROTA: /api/v1/sugestoes/calculos
sugestoes_calculos:
  post:
    tags: [Sugestoes]
    summary: Sugerir novo tipo de cálculo
    operationId: sugerirCalculo
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/SugestaoRequest' }
    responses:
      '200':
        description: Recebido
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseSugestao' }

#ROTA: /api/v1/sugestoes
sugestoes_global:
  get:
    tags: [Sugestoes]
    summary: Listar sugestões recebidas
    description: "Permite filtrar por tipo (ESPECIE, DOENCA, etc) e status"
    operationId: listarSugestoes
    parameters:
      - name: tipo
        in: query
        required: false
        schema:
          $ref: '../components/schemas.yml#/TipoSugestaoEnum'
      - name: status
        in: query
        required: false
        schema:
          $ref: '../components/schemas.yml#/StatusSugestaoEnum'
    responses:
      '200':
        description: Lista recuperada
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseListaSugestao'

# ROTA: /api/v1/sugestoes/{id}/status
sugestoes_atualizar_status:
  patch:
    tags: [Sugestoes]
    summary: Alterar status da sugestão (Admin)
    operationId: atualizarStatusSugestao
    parameters:
      - name: id
        in: path
        required: true
        schema:
          type: string
          format: uuid
    requestBody:
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/AtualizarStatusRequest'
    responses:
      '200':
        description: Status atualizado
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseSugestao'

```

