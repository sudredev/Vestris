# Documentação do projeto vestris-reference

## Índice de Diretórios e Arquivos

- [.](#)
  - [pom.xml](#pomxml)
- [src\main\java\br\com\vestris\reference\application](#srcmainjavabrcomvestrisreferenceapplication)
  - [ServiceReferencia.java](#srcmainjavabrcomvestrisreferenceapplicationservicereferenciajava)
- [src\main\java\br\com\vestris\reference\domain\model](#srcmainjavabrcomvestrisreferencedomainmodel)
  - [ReferenciaBibliografica.java](#srcmainjavabrcomvestrisreferencedomainmodelreferenciabibliograficajava)
- [src\main\java\br\com\vestris\reference\domain\repository](#srcmainjavabrcomvestrisreferencedomainrepository)
  - [RepositorioReferencia.java](#srcmainjavabrcomvestrisreferencedomainrepositoryrepositorioreferenciajava)
- [src\main\java\br\com\vestris\reference\interfaces\delegate](#srcmainjavabrcomvestrisreferenceinterfacesdelegate)
  - [ApiDelegateReferencias.java](#srcmainjavabrcomvestrisreferenceinterfacesdelegateapidelegatereferenciasjava)
- [src\main\resources\swagger](#srcmainresourcesswagger)
  - [openapi.yml](#srcmainresourcesswaggeropenapiyml)
- [src\main\resources\swagger\components](#srcmainresourcesswaggercomponents)
  - [schemas.yml](#srcmainresourcesswaggercomponentsschemasyml)
- [src\main\resources\swagger\paths](#srcmainresourcesswaggerpaths)
  - [referencias.yml](#srcmainresourcesswaggerpathsreferenciasyml)

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

    <artifactId>vestris-reference</artifactId>

    <dependencies>
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-shared</artifactId>
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
                            <apiPackage>br.com.vestris.reference.interfaces.api</apiPackage>
                            <modelPackage>br.com.vestris.reference.interfaces.dto</modelPackage>

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

## src\main\java\br\com\vestris\reference\application

### ServiceReferencia.java

```java
// src\main\java\br\com\vestris\reference\application\ServiceReferencia.java
package br.com.vestris.reference.application;

import br.com.vestris.reference.domain.model.ReferenciaBibliografica;
import br.com.vestris.reference.domain.repository.RepositorioReferencia;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceReferencia {

    private final RepositorioReferencia repositorio;

    public ReferenciaBibliografica criar(ReferenciaBibliografica nova) {
        if (repositorio.existsByTituloAndAutores(nova.getTitulo(), nova.getAutores())) {
            throw new ExcecaoRegraNegocio("Esta referência já está cadastrada.");
        }
        return repositorio.save(nova);
    }

    public List<ReferenciaBibliografica> listarTodas() {
        return repositorio.findAll();
    }

    public boolean existePorId(UUID id) {
        return repositorio.existsById(id);
    }

    public ReferenciaBibliografica buscarPorId(UUID id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Referência", id.toString()));
    }

    public ReferenciaBibliografica atualizar(UUID id, ReferenciaBibliografica dados) {
        ReferenciaBibliografica existente = buscarPorId(id);

        existente.setTitulo(dados.getTitulo());
        existente.setAutores(dados.getAutores());
        existente.setAno(dados.getAno());
        existente.setFonte(dados.getFonte());
        existente.setUrl(dados.getUrl());

        return repositorio.save(existente);
    }

    public void deletar(UUID id) {
        if (!repositorio.existsById(id)) {
            throw new ExceptionRecursoNaoEncontrado("Referência", id.toString());
        }
        try {
            repositorio.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ExcecaoRegraNegocio("Esta referência não pode ser removida pois embasa protocolos ou vacinas no sistema.");
        }
    }

    public String buscarCitacaoPorId(UUID id) {
        return repositorio.findById(id)
                .map(r -> r.getAutores() + " (" + r.getAno() + ") - " + r.getTitulo())
                .orElse("Referência não identificada");
    }
}

```

---

## src\main\java\br\com\vestris\reference\domain\model

### ReferenciaBibliografica.java

```java
// src\main\java\br\com\vestris\reference\domain\model\ReferenciaBibliografica.java
package br.com.vestris.reference.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "referencias", schema = "reference_schema")

public class ReferenciaBibliografica extends EntidadeBase {
    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String autores;

    private Integer ano;

    private String fonte; // Editora ou Revista

    private String url;

}

```

---

## src\main\java\br\com\vestris\reference\domain\repository

### RepositorioReferencia.java

```java
// src\main\java\br\com\vestris\reference\domain\repository\RepositorioReferencia.java
package br.com.vestris.reference.domain.repository;

import br.com.vestris.reference.domain.model.ReferenciaBibliografica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositorioReferencia extends JpaRepository<ReferenciaBibliografica, UUID> {
    boolean existsByTituloAndAutores(String titulo, String autores);
}

```

---

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

---

## src\main\resources\swagger

### openapi.yml

```yaml
# src\main\resources\swagger\openapi.yml
openapi: 3.0.3
info:
  title: Vestris - Módulo Referências
  description: Gestão de Bibliografia Científica
  version: 1.0.0
servers:
  - url: http://localhost:8080
    description: Servidor Local

paths:
  /api/v1/referencias:
    $ref: './paths/referencias.yml#/referencias_colecao'

  /api/v1/referencias/{id}:
    $ref: './paths/referencias.yml#/referencias_item'

components:
  schemas:
    ReferenciaRequest:
      $ref: "./components/schemas.yml#/ReferenciaRequest"
    ReferenciaResponse:
      $ref: "./components/schemas.yml#/ReferenciaResponse"
    ApiResponseReferencia:
      $ref: "./components/schemas.yml#/ApiResponseReferencia"
    ApiResponseListaReferencia:
      $ref: "./components/schemas.yml#/ApiResponseListaReferencia"
```

---

## src\main\resources\swagger\components

### schemas.yml

```yaml
# src\main\resources\swagger\components\schemas.yml
ReferenciaRequest:
  type: object
  required: [titulo, autores, ano]
  properties:
    titulo:
      type: string
      example: "Exotic Animal Formulary"
    autores:
      type: string
      example: "James W. Carpenter"
    ano:
      type: integer
      example: 2018
    fonte:
      type: string
      description: "Nome do Jornal, Editora ou Revista"
      example: "Elsevier"
    url:
      type: string
      description: "Link para o DOI ou PDF (opcional)"

ReferenciaResponse:
  type: object
  properties:
    id:
      type: string
      format: uuid
    titulo:
      type: string
    autores:
      type: string
    ano:
      type: integer
    fonte:
      type: string
    url:
      type: string
    criadoEm:
      type: string
      format: date-time

# Wrappers
ApiResponseReferencia:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { $ref: '#/ReferenciaResponse' }

ApiResponseListaReferencia:
  type: object
  properties:
    sucesso: { type: boolean }
    dados:
      type: array
      items: { $ref: '#/ReferenciaResponse' }
```

---

## src\main\resources\swagger\paths

### referencias.yml

```yaml
# src\main\resources\swagger\paths\referencias.yml
# Rota: /api/v1/referencias
referencias_colecao:
  post:
    tags: [Referencias]
    summary: Cadastrar referência
    operationId: criarReferencia
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/ReferenciaRequest' }
    responses:
      '200':
        description: Sucesso
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseReferencia' }
  get:
    tags: [Referencias]
    summary: Listar todas
    operationId: listarReferencias
    responses:
      '200':
        description: Lista
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseListaReferencia' }

# Rota: /api/v1/referencias/{id}
referencias_item:
  parameters:
    - name: id
      in: path
      required: true
      schema: { type: string, format: uuid }
  get:
    tags: [Referencias]
    summary: Buscar por ID
    operationId: buscarReferenciaPorId
    responses:
      '200':
        description: Encontrado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseReferencia' }
  put:
    tags: [Referencias]
    summary: Atualizar referência
    operationId: atualizarReferencia
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/ReferenciaRequest' }
    responses:
      '200':
        description: Atualizado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseReferencia' }
  delete:
    tags: [Referencias]
    summary: Remover referência
    description: "Bloqueia se estiver em uso (Protocolos, Vacinas, etc)"
    operationId: deletarReferencia
    responses:
      '204': { description: Removido }
```

