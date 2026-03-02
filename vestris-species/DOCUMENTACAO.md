# Documentação do projeto vestris-species

## Índice de Diretórios e Arquivos

- [.](#)
  - [.openapi-generator-ignore](#openapi-generator-ignore)
  - [pom.xml](#pomxml)
- [src\main\java\br\com\vestris\species\application](#srcmainjavabrcomvestrisspeciesapplication)
  - [ServiceEspecie.java](#srcmainjavabrcomvestrisspeciesapplicationserviceespeciejava)
  - [ServiceModeloExame.java](#srcmainjavabrcomvestrisspeciesapplicationservicemodeloexamejava)
- [src\main\java\br\com\vestris\species\domain](#srcmainjavabrcomvestrisspeciesdomain)
  - [Especie.java](#srcmainjavabrcomvestrisspeciesdomainespeciejava)
  - [ModeloExameFisico.java](#srcmainjavabrcomvestrisspeciesdomainmodeloexamefisicojava)
- [src\main\java\br\com\vestris\species\domain\repository](#srcmainjavabrcomvestrisspeciesdomainrepository)
  - [RepositorioEspecie.java](#srcmainjavabrcomvestrisspeciesdomainrepositoryrepositorioespeciejava)
  - [RepositorioModeloExame.java](#srcmainjavabrcomvestrisspeciesdomainrepositoryrepositoriomodeloexamejava)
- [src\main\java\br\com\vestris\species\interfaces\delegate](#srcmainjavabrcomvestrisspeciesinterfacesdelegate)
  - [ApiDelegateEspecies.java](#srcmainjavabrcomvestrisspeciesinterfacesdelegateapidelegateespeciesjava)
  - [ApiDelegateModeloExame.java](#srcmainjavabrcomvestrisspeciesinterfacesdelegateapidelegatemodeloexamejava)
- [src\main\resources\swagger](#srcmainresourcesswagger)
  - [openapi.yml](#srcmainresourcesswaggeropenapiyml)
- [src\main\resources\swagger\components](#srcmainresourcesswaggercomponents)
  - [schemas.yml](#srcmainresourcesswaggercomponentsschemasyml)
- [src\main\resources\swagger\paths](#srcmainresourcesswaggerpaths)
  - [especies.yml](#srcmainresourcesswaggerpathsespeciesyml)
  - [exames-fisicos.yml](#srcmainresourcesswaggerpathsexames-fisicosyml)

## .

### .openapi-generator-ignore

```text
# .openapi-generator-ignore
# Ignora todos os testes gerados automaticamente
src/test/**

# Ignora arquivos de configuração inúteis
pom.xml
README.md
.gitignore
```

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

    <artifactId>vestris-species</artifactId>

    <dependencies>
        <!-- Módulo Compartilhado (EntidadeBase, RespostaApi) -->
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-shared</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <!-- Dependências OBRIGATÓRIAS para o código gerado pelo OpenAPI -->
        <dependency>
            <groupId>io.swagger.core.v3</groupId>
            <artifactId>swagger-annotations</artifactId>
            <version>2.2.19</version>
        </dependency>

        <!-- SWAGGER: Models (ESSA ERA A QUE FALTAVA! Para classe OpenAPI) -->
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

        <!-- LOMBOK: Para getters/setters automáticos -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <!-- JPA: Para usar @Entity, @Id, @MappedSuperclass -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <!-- Web Starter (Necessário para as anotações Spring Web geradas) -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <!-- Plugin do OpenAPI Generator -->
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
                            <!-- Seus caminhos (mantenha como estão) -->
                            <inputSpec>${project.basedir}/src/main/resources/swagger/openapi.yml</inputSpec>
                            <ignoreFileOverride>${project.basedir}/.openapi-generator-ignore</ignoreFileOverride>

                            <generatorName>spring</generatorName>
                            <output>${project.build.directory}/generated-sources/openapi</output>
                            <apiPackage>br.com.vestris.species.interfaces.api</apiPackage> <!-- (Mude para 'clinical' no outro pom) -->
                            <modelPackage>br.com.vestris.species.interfaces.dto</modelPackage> <!-- (Mude para 'clinical' no outro pom) -->

                            <!-- Desliga testes de API e Modelos -->
                            <generateApiTests>false</generateApiTests>
                            <generateModelTests>false</generateModelTests>

                            <!-- O SEGREDO ESTÁ AQUI: -->
                            <!-- Dizemos explicitamente: "Gere APENAS o ApiUtil.java. Não gere Application.java nem Tests.java" -->
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

## src\main\java\br\com\vestris\species\application

### ServiceEspecie.java

```java
// src\main\java\br\com\vestris\species\application\ServiceEspecie.java
package br.com.vestris.species.application;

import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.species.domain.Especie;
import br.com.vestris.species.domain.repository.RepositorioEspecie;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceEspecie {

    private final RepositorioEspecie repositorio;

    public Especie criar(Especie novaEspecie) {
        // Regra: Nome científico deve ser único
        if (repositorio.existsByNomeCientifico(novaEspecie.getNomeCientifico())) {
            throw new ExcecaoRegraNegocio("Já existe uma espécie cadastrada com o nome científico: " + novaEspecie.getNomeCientifico());
        }
        return repositorio.save(novaEspecie);
    }

    public List<Especie> listarTodas(){
        return repositorio.findAll();
    }

    public Especie buscarPorId(UUID id){
        return repositorio.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Especie", id.toString()));
    }

    public boolean existePorId(UUID id) {
        return repositorio.existsById(id);
    }

    // ATUALIZAR (Mapeamento Completo)
    public Especie atualizar(UUID id, Especie dados) {
        Especie existente = buscarPorId(id);

        existente.setNomePopular(dados.getNomePopular());
        existente.setNomeCientifico(dados.getNomeCientifico());
        existente.setFamiliaTaxonomica(dados.getFamiliaTaxonomica());
        existente.setGrupo(dados.getGrupo());

        existente.setResumoClinico(dados.getResumoClinico());
        existente.setParametrosFisiologicos(dados.getParametrosFisiologicos());
        existente.setExpectativaVida(dados.getExpectativaVida());
        existente.setPesoAdulto(dados.getPesoAdulto());

        existente.setTipoDieta(dados.getTipoDieta());
        existente.setManejoInfos(dados.getManejoInfos());

        existente.setAlertasClinicos(dados.getAlertasClinicos());
        existente.setPontosExameFisico(dados.getPontosExameFisico());

        existente.setHabitat(dados.getHabitat());
        existente.setDistribuicaoGeografica(dados.getDistribuicaoGeografica());
        existente.setStatusConservacao(dados.getStatusConservacao());

        existente.setBibliografiaBase(dados.getBibliografiaBase());

        return repositorio.save(existente);
    }

    // DELETAR
    public void deletar(UUID id) {
        if (!repositorio.existsById(id)) {
            throw new ExceptionRecursoNaoEncontrado("Espécie", id.toString());
        }
        try {
            repositorio.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ExcecaoRegraNegocio("Não é possível remover esta espécie pois ela possui registros vinculados.");
        }
    }
}

```

### ServiceModeloExame.java

```java
// src\main\java\br\com\vestris\species\application\ServiceModeloExame.java
package br.com.vestris.species.application;

import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.species.domain.ModeloExameFisico;
import br.com.vestris.species.domain.repository.RepositorioModeloExame;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceModeloExame {
    private final RepositorioModeloExame repositorio;
    private final ServiceEspecie serviceEspecie; // Para validar se a espécie existe

    // --- BUSCAR ---
    public ModeloExameFisico buscarPorEspecie(UUID especieId) {
        // Valida se a espécie existe antes de buscar o modelo
        if (!serviceEspecie.existePorId(especieId)) {
            throw new ExceptionRecursoNaoEncontrado("Espécie", especieId.toString());
        }

        return repositorio.findByEspecieId(especieId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Modelo de Exame Físico para a espécie", especieId.toString()));
    }

    // --- CRIAR ---
    @Transactional
    public ModeloExameFisico criar(UUID especieId, String textoBase) {
        if (!serviceEspecie.existePorId(especieId)) {
            throw new ExceptionRecursoNaoEncontrado("Espécie", especieId.toString());
        }

        // Regra: Uma espécie só pode ter UM modelo ativo
        if (repositorio.findByEspecieId(especieId).isPresent()) {
            throw new ExcecaoRegraNegocio("Já existe um modelo de exame físico cadastrado para esta espécie. Use a atualização (PUT).");
        }

        ModeloExameFisico novo = new ModeloExameFisico();
        novo.setEspecieId(especieId);
        novo.setTextoBase(textoBase);

        return repositorio.save(novo);
    }

    // --- ATUALIZAR ---
    @Transactional
    public ModeloExameFisico atualizar(UUID especieId, String novoTextoBase) {
        ModeloExameFisico existente = buscarPorEspecie(especieId); // Já valida existência

        existente.setTextoBase(novoTextoBase);

        return repositorio.save(existente);
    }

    // --- DELETAR ---
    @Transactional
    public void deletar(UUID especieId) {
        ModeloExameFisico existente = buscarPorEspecie(especieId);
        repositorio.delete(existente);
    }

    // --- LISTAR TODOS ---
    public List<ModeloExameFisico> listarTodos() {
        return repositorio.findAll();
    }
}

```

---

## src\main\java\br\com\vestris\species\domain

### Especie.java

```java
// src\main\java\br\com\vestris\species\domain\Especie.java
package br.com.vestris.species.domain;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "especies", schema = "species_schema")
public class Especie extends EntidadeBase {
    // 1. Identificação
    @Column(nullable = false, length = 150)
    private String nomePopular;

    @Column(nullable = false, unique = true, length = 150)
    private String nomeCientifico;

    @Column(length = 100)
    private String familiaTaxonomica;

    @Column(length = 50)
    private String grupo; // Ave, Réptil, etc.

    // 2. Visão Geral
    @Column(columnDefinition = "TEXT")
    private String resumoClinico;

    // 3. Fisiologia
    @Column(columnDefinition = "TEXT")
    private String parametrosFisiologicos;

    private String expectativaVida;
    private String pesoAdulto;

    // 4. Manejo
    private String tipoDieta;
    @Column(columnDefinition = "TEXT")
    private String manejoInfos;

    // 5. Alertas
    @Column(columnDefinition = "TEXT")
    private String alertasClinicos;

    // 6. Exame Físico
    @Column(columnDefinition = "TEXT")
    private String pontosExameFisico;
    @Column(columnDefinition = "TEXT")
    private String receitaManejoPadrao;

    // 8. Biologia
    @Column(columnDefinition = "TEXT")
    private String habitat;
    @Column(columnDefinition = "TEXT")
    private String distribuicaoGeografica;
    private String statusConservacao;

    // 9. Referência
    private String bibliografiaBase;
}

```

### ModeloExameFisico.java

```java
// src\main\java\br\com\vestris\species\domain\ModeloExameFisico.java
package br.com.vestris.species.domain;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "modelos_exame_fisico", schema = "species_schema")
public class ModeloExameFisico extends EntidadeBase {
    @Column(nullable = false)
    private UUID especieId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String textoBase; // Guardará o JSON stringificado
}

```

---

## src\main\java\br\com\vestris\species\domain\repository

### RepositorioEspecie.java

```java
// src\main\java\br\com\vestris\species\domain\repository\RepositorioEspecie.java
package br.com.vestris.species.domain.repository;

import br.com.vestris.species.domain.Especie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositorioEspecie extends JpaRepository<Especie, UUID> {
    // Verifica se já existe pelo nome científico
    boolean existsByNomeCientifico(String nomeCientifico);
}

```

### RepositorioModeloExame.java

```java
// src\main\java\br\com\vestris\species\domain\repository\RepositorioModeloExame.java
package br.com.vestris.species.domain.repository;

import br.com.vestris.species.domain.ModeloExameFisico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RepositorioModeloExame extends JpaRepository<ModeloExameFisico, UUID> {
    Optional<ModeloExameFisico> findByEspecieId(UUID especieId);
}

```

---

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

---

## src\main\resources\swagger

### openapi.yml

```yaml
# src\main\resources\swagger\openapi.yml
openapi: 3.0.3
info:
  title: Vestris - Módulo Espécies
  description: API de Taxonomia do Silvet
  version: 1.0.0
servers:
  - url: http://localhost:8080
    description: Servidor Local

# Importando os caminhos
paths:
  /api/v1/especies:
    $ref: './paths/especies.yml#/paths/~1api~1v1~1especies'

  /api/v1/especies/{id}:
    $ref: './paths/especies.yml#/paths/~1api~1v1~1especies~1{id}'

  # ADICIONE ESTA LINHA:
  /api/v1/especies/{especieId}/exame-fisico:
    $ref: './paths/exames-fisicos.yml#/exame_fisico_por_especie'

  /api/v1/exames-fisicos:
    $ref: './paths/exames-fisicos.yml#/exames_fisicos_colecao'

components:
  schemas:
    EspecieRequest:
      $ref: "./components/schemas.yml#/EspecieRequest"
    EspecieResponse:
      $ref: "./components/schemas.yml#/EspecieResponse"
    ModeloExameRequest:
      $ref: "./components/schemas.yml#/ModeloExameRequest"
    ModeloExameResponse:
      $ref: "./components/schemas.yml#/ModeloExameResponse"
    ApiResponseModeloExame:
      $ref: "./components/schemas.yml#/ApiResponseModeloExame"
    ApiResponseEspecie:
      $ref: "./components/schemas.yml#/ApiResponseEspecie"
    ApiResponseListaEspecie:
      $ref: "./components/schemas.yml#/ApiResponseListaEspecie"
```

---

## src\main\resources\swagger\components

### schemas.yml

```yaml
# src\main\resources\swagger\components\schemas.yml
EspecieRequest:
  type: object
  required:
    - nomePopular
    - nomeCientifico
  properties:
    nomePopular:
      type: string
      example: "Calopsita"
    nomeCientifico:
      type: string
      example: "Nymphicus hollandicus"
    familiaTaxonomica:
      type: string
      example: "Psittacidae"
    grupo:
      type: string
      example: "Ave"
    resumoClinico:
      type: string
      description: "Visão geral rápida para o clínico."
    parametrosFisiologicos:
      type: string
      description: "FC, FR, Temp (Markdown)"
    expectativaVida:
      type: string
    pesoAdulto:
      type: string
    tipoDieta:
      type: string
    manejoInfos:
      type: string
      description: "Erros comuns de manejo"
    receitaManejoPadrao:
      type: string
      description: "8 pilares de manejo pré-preenchidos"
    alertasClinicos:
      type: string
      description: "Sensibilidades a medicamentos"
    pontosExameFisico:
      type: string
      description: "O que não esquecer no exame"
    habitat: { type: string }
    distribuicaoGeografica: { type: string }
    statusConservacao: { type: string }
    bibliografiaBase: { type: string }


EspecieResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    nomePopular: { type: string }
    nomeCientifico: { type: string }
    familiaTaxonomica: { type: string }
    grupo: { type: string }
    resumoClinico: { type: string }
    parametrosFisiologicos: { type: string }
    expectativaVida: { type: string }
    pesoAdulto: { type: string }
    tipoDieta: { type: string }
    manejoInfos: { type: string }
    receitaManejoPadrao: { type: string }
    alertasClinicos: { type: string }
    pontosExameFisico: { type: string }
    habitat: { type: string }
    distribuicaoGeografica: { type: string }
    statusConservacao: { type: string }
    bibliografiaBase: { type: string }
    criadoEm: { type: string, format: date-time }

# --- MODELOS DE EXAME FÍSICO ---

# Request (O que o Front envia para criar/editar)
ModeloExameRequest:
  type: object
  required: [ textoBase ]
  properties:
    textoBase:
      type: string
      description: "JSON Stringificado contendo as seções do exame. Ex: [{'titulo': 'Geral', 'conteudo': '...'}]"
      example: '[{"titulo": "Avaliação Geral", "conteudo": "Postura, alerta..."}]'

# Response (O que o Back devolve)
ModeloExameResponse:
  type: object
  properties:
    id:
      type: string
      format: uuid
    especieId:
      type: string
      format: uuid
    textoBase:
      type: string
      description: "JSON Stringificado com as seções"

# Wrapper da API
ApiResponseModeloExame:
  type: object
  properties:
    sucesso: { type: boolean }
    mensagem: { type: string }
    dados: { $ref: '#/ModeloExameResponse' }

ApiResponseEspecie:
  type: object
  properties:
    sucesso: { type: boolean }
    mensagem: { type: string }
    dados: { $ref: '#/EspecieResponse' }
    dataHora: { type: string, format: date-time }

ApiResponseListaEspecie:
  type: object
  properties:
    sucesso: { type: boolean }
    dados:
      type: array
      items: { $ref: '#/EspecieResponse' }

# Wrapper de Lista para Exames
ApiResponseListaModeloExame:
  type: object
  properties:
    sucesso: { type: boolean }
    dados:
      type: array
      items: { $ref: '#/ModeloExameResponse' }
```

---

## src\main\resources\swagger\paths

### especies.yml

```yaml
# src\main\resources\swagger\paths\especies.yml
paths:
  # ---------------------------------------------------------
  # ROTA 1: Coleção (Listar / Criar)
  # ---------------------------------------------------------
  /api/v1/especies:
    get:
      tags:
        - Especies
      summary: Listar todas as espécies
      operationId: listarEspecies
      responses:
        '200':
          description: Lista recuperada
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseListaEspecie'
    post:
      tags:
        - Especies
      summary: Cadastrar nova espécie
      operationId: criarEspecie
      requestBody:
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/EspecieRequest'
      responses:
        '200':
          description: Espécie criada com sucesso
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseEspecie'

  # ---------------------------------------------------------
  # ROTA 2: Item Específico (Buscar / Editar / Deletar)
  # ---------------------------------------------------------
  /api/v1/especies/{id}:
    parameters:
      - name: id
        in: path
        required: true
        schema:
          type: string
          format: uuid

    get:
      tags:
        - Especies
      summary: Buscar espécie por ID
      operationId: buscarEspeciePorId
      responses:
        '200':
          description: Encontrado
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseEspecie'

    put:
      tags:
        - Especies
      summary: Atualizar dados da espécie
      operationId: atualizarEspecie
      requestBody:
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/EspecieRequest'
      responses:
        '200':
          description: Atualizado com sucesso
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseEspecie'

    delete:
      tags:
        - Especies
      summary: Remover espécie
      operationId: deletarEspecie
      responses:
        '204':
          description: Removido com sucesso

```

### exames-fisicos.yml

```yaml
# src\main\resources\swagger\paths\exames-fisicos.yml
# ROTA: /api/v1/especies/{especieId}/exame-fisico
exame_fisico_por_especie:

  # 1. GET - Buscar o modelo (Já existia, agora isolado)
  get:
    tags: [ExamesFisicos]
    summary: Obter modelo de exame físico da espécie
    operationId: obterModeloExame
    parameters:
      - name: especieId
        in: path
        required: true
        schema: { type: string, format: uuid }
    responses:
      '200':
        description: Modelo encontrado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseModeloExame' }
      '404':
        description: Modelo não definido para esta espécie

  # 2. POST - Criar um modelo novo para a espécie
  post:
    tags: [ExamesFisicos]
    summary: Criar modelo de exame físico
    description: "Define o template padrão para uma espécie específica."
    operationId: criarModeloExame
    parameters:
      - name: especieId
        in: path
        required: true
        schema: { type: string, format: uuid }
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/ModeloExameRequest' }
    responses:
      '201':
        description: Criado com sucesso
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseModeloExame' }

  # 3. PUT - Atualizar o texto do modelo existente
  put:
    tags: [ExamesFisicos]
    summary: Atualizar modelo de exame físico
    operationId: atualizarModeloExame
    parameters:
      - name: especieId
        in: path
        required: true
        schema: { type: string, format: uuid }
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/ModeloExameRequest' }
    responses:
      '200':
        description: Atualizado com sucesso
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseModeloExame' }

  # 4. DELETE - Remover o modelo (voltar ao padrão genérico)
  delete:
    tags: [ExamesFisicos]
    summary: Remover modelo de exame físico
    operationId: deletarModeloExame
    parameters:
      - name: especieId
        in: path
        required: true
        schema: { type: string, format: uuid }
    responses:
      '204':
        description: Removido com sucesso

# ROTA: /api/v1/exames-fisicos (Lista Geral)
exames_fisicos_colecao:
  get:
    tags: [ExamesFisicos]
    summary: Listar todos os modelos cadastrados
    description: "Retorna todos os templates de exame físico de todas as espécies."
    operationId: listarTodosModelosExame
    responses:
      '200':
        description: Lista recuperada com sucesso
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseListaModeloExame' }

```

