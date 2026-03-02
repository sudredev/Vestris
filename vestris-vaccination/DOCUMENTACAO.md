# Documentação do projeto vestris-vaccination

## Índice de Diretórios e Arquivos

- [.](#)
  - [pom.xml](#pomxml)
- [src\main\java\br\com\vestris\vaccination\application](#srcmainjavabrcomvestrisvaccinationapplication)
  - [ServiceAplicacaoVacina.java](#srcmainjavabrcomvestrisvaccinationapplicationserviceaplicacaovacinajava)
  - [ServiceProtocoloVacinacao.java](#srcmainjavabrcomvestrisvaccinationapplicationserviceprotocolovacinacaojava)
  - [ServiceVacinacao.java](#srcmainjavabrcomvestrisvaccinationapplicationservicevacinacaojava)
- [src\main\java\br\com\vestris\vaccination\domain\model](#srcmainjavabrcomvestrisvaccinationdomainmodel)
  - [AplicacaoVacina.java](#srcmainjavabrcomvestrisvaccinationdomainmodelaplicacaovacinajava)
  - [ProtocoloVacinacao.java](#srcmainjavabrcomvestrisvaccinationdomainmodelprotocolovacinacaojava)
  - [Vacina.java](#srcmainjavabrcomvestrisvaccinationdomainmodelvacinajava)
- [src\main\java\br\com\vestris\vaccination\domain\repository](#srcmainjavabrcomvestrisvaccinationdomainrepository)
  - [RepositorioAplicacaoVacina.java](#srcmainjavabrcomvestrisvaccinationdomainrepositoryrepositorioaplicacaovacinajava)
  - [RepositorioProtocoloVacinacao.java](#srcmainjavabrcomvestrisvaccinationdomainrepositoryrepositorioprotocolovacinacaojava)
  - [RepositorioVacina.java](#srcmainjavabrcomvestrisvaccinationdomainrepositoryrepositoriovacinajava)
- [src\main\java\br\com\vestris\vaccination\interfaces\delegate](#srcmainjavabrcomvestrisvaccinationinterfacesdelegate)
  - [ApiDelegateAplicacaoVacina.java](#srcmainjavabrcomvestrisvaccinationinterfacesdelegateapidelegateaplicacaovacinajava)
  - [ApiDelegateProtocoloVacinal.java](#srcmainjavabrcomvestrisvaccinationinterfacesdelegateapidelegateprotocolovacinaljava)
  - [ApiDelegateVacinas.java](#srcmainjavabrcomvestrisvaccinationinterfacesdelegateapidelegatevacinasjava)
- [src\main\resources\swagger](#srcmainresourcesswagger)
  - [openapi.yml](#srcmainresourcesswaggeropenapiyml)
- [src\main\resources\swagger\components](#srcmainresourcesswaggercomponents)
  - [schemas.yml](#srcmainresourcesswaggercomponentsschemasyml)
- [src\main\resources\swagger\paths](#srcmainresourcesswaggerpaths)
  - [protocolos-vacinais.yml](#srcmainresourcesswaggerpathsprotocolos-vacinaisyml)
  - [vacinacao-paciente.yml](#srcmainresourcesswaggerpathsvacinacao-pacienteyml)
  - [vacinas.yml](#srcmainresourcesswaggerpathsvacinasyml)

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

    <artifactId>vestris-vaccination</artifactId>

    <dependencies>
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-shared</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <!-- Para validar a Espécie -->
        <dependency>
            <groupId>br.com.vestris</groupId> <!-- ou br.com.vestris -->
            <artifactId>vestris-species</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <!-- Para validar a Fonte/Referência (CRÍTICO) -->
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-reference</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-medical-record</artifactId>
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
                            <apiPackage>br.com.vestris.vaccination.interfaces.api</apiPackage>
                            <modelPackage>br.com.vestris.vaccination.interfaces.dto</modelPackage>

                            <!-- Configurações para evitar erros de teste -->
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

## src\main\java\br\com\vestris\vaccination\application

### ServiceAplicacaoVacina.java

```java
// src\main\java\br\com\vestris\vaccination\application\ServiceAplicacaoVacina.java
package br.com.vestris.vaccination.application;

import br.com.vestris.medicalrecord.domain.model.Paciente;
import br.com.vestris.medicalrecord.domain.repository.RepositorioPaciente;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.vaccination.domain.model.AplicacaoVacina;
import br.com.vestris.vaccination.domain.repository.RepositorioAplicacaoVacina;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ServiceAplicacaoVacina {
    private final RepositorioAplicacaoVacina repositorio;
    private final RepositorioPaciente repoPaciente;
    private final ServiceVacinacao serviceVacinacao; // Valida se vacina existe

    public AplicacaoVacina registrar(AplicacaoVacina app, UUID pacienteId) {
        Paciente paciente = repoPaciente.findById(pacienteId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Paciente", pacienteId.toString()));

        // Valida Vacina no outro módulo
        if (!serviceVacinacao.existePorId(app.getVacinaId())) { // Precisa criar este método no ServiceVacinacao se não tiver
            throw new ExcecaoRegraNegocio("Vacina informada não existe no catálogo.");
        }

        app.setPaciente(paciente);
        return repositorio.save(app);
    }

    public List<AplicacaoVacina> listarPorPaciente(UUID pacienteId) {
        return repositorio.findByPacienteIdOrderByDataAplicacaoDesc(pacienteId);
    }

    public void deletar(UUID id) {
        repositorio.deleteById(id);
    }
}

```

### ServiceProtocoloVacinacao.java

```java
// src\main\java\br\com\vestris\vaccination\application\ServiceProtocoloVacinacao.java
package br.com.vestris.vaccination.application;

import br.com.vestris.reference.application.ServiceReferencia;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.species.application.ServiceEspecie;
import br.com.vestris.vaccination.domain.model.ProtocoloVacinacao;
import br.com.vestris.vaccination.domain.model.Vacina;
import br.com.vestris.vaccination.domain.repository.RepositorioProtocoloVacinacao;
import br.com.vestris.vaccination.domain.repository.RepositorioVacina;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ServiceProtocoloVacinacao {
    private final RepositorioProtocoloVacinacao repoProtocolo;
    private final RepositorioVacina repoVacina;

    // Serviços Externos
    private final ServiceEspecie serviceEspecie;
    private final ServiceReferencia serviceReferencia;

    public ProtocoloVacinacao criar(ProtocoloVacinacao novo, UUID vacinaId) {
        // 1. Validar Vacina (Interno)
        Vacina vacina = repoVacina.findById(vacinaId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Vacina", vacinaId.toString()));

        // 2. Validar Espécie (Externo)
        if (!serviceEspecie.existePorId(novo.getEspecieId())) {
            throw new ExcecaoRegraNegocio("Espécie não encontrada.");
        }

        // 3. Validar Referência (Externo - OBRIGATÓRIO)
        if (!serviceReferencia.existePorId(novo.getReferenciaId())) {
            throw new ExcecaoRegraNegocio("Referência Bibliográfica/Legal é obrigatória e não foi encontrada. O sistema exige respaldo científico para vacinação.");
        }

        // 4. Validar Duplicidade
        if (repoProtocolo.existsByEspecieIdAndVacinaId(novo.getEspecieId(), vacinaId)) {
            throw new ExcecaoRegraNegocio("Esta vacina já consta no protocolo desta espécie.");
        }

        novo.setVacina(vacina);
        return repoProtocolo.save(novo);
    }

    public List<ProtocoloVacinacao> listarPorEspecie(UUID especieId) {
        // 1. Validar se a espécie existe antes de buscar
        if (!serviceEspecie.existePorId(especieId)) {
            throw new ExceptionRecursoNaoEncontrado("Espécie", especieId.toString());
        }

        List<ProtocoloVacinacao> protocolos = repoProtocolo.findByEspecieId(especieId);

        // 2. REGRA DE NEGÓCIO: Mensagem clara se não houver vacinas
        if (protocolos.isEmpty()) {
            throw new ExcecaoRegraNegocio(
                    "Não constam vacinas obrigatórias ou recomendadas para esta espécie na base de dados atual. " +
                            "Verifique a legislação local e sanitária."
            );
        }

        return protocolos;
    }

    public ProtocoloVacinacao buscarPorId(UUID id) {
        return repoProtocolo.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Protocolo Vacinal", id.toString()));
    }

    public ProtocoloVacinacao atualizar(UUID id, UUID especieId, UUID vacinaId, UUID refId,
                                        Integer idade, Integer reforco, boolean obrig, String obs) {

        ProtocoloVacinacao existente = buscarPorId(id);

        // Se mudar Espécie, valida
        if (!existente.getEspecieId().equals(especieId)) {
            if (!serviceEspecie.existePorId(especieId)) {
                throw new ExcecaoRegraNegocio("Nova espécie não encontrada.");
            }
            existente.setEspecieId(especieId);
        }

        // Se mudar Vacina, valida e checa duplicidade
        if (!existente.getVacina().getId().equals(vacinaId)) {
            // Valida Vacina
            Vacina novaVacina = repoVacina.findById(vacinaId)
                    .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Vacina", vacinaId.toString()));

            // Checa se já não existe essa vacina para essa espécie (evitar duplicidade)
            if (repoProtocolo.existsByEspecieIdAndVacinaId(especieId, vacinaId)) {
                throw new ExcecaoRegraNegocio("Esta vacina já existe no protocolo desta espécie.");
            }
            existente.setVacina(novaVacina);
        }

        // Se mudar Referência, valida
        if (!existente.getReferenciaId().equals(refId)) {
            if (!serviceReferencia.existePorId(refId)) {
                throw new ExcecaoRegraNegocio("Referência bibliográfica inválida.");
            }
            existente.setReferenciaId(refId);
        }

        existente.setIdadeMinimaDias(idade);
        existente.setDiasParaReforco(reforco);
        existente.setObrigatoria(obrig);
        existente.setObservacoes(obs);

        return repoProtocolo.save(existente);
    }

    public void deletar(UUID id) {
        if (!repoProtocolo.existsById(id)) {
            throw new ExceptionRecursoNaoEncontrado("Protocolo Vacinal", id.toString());
        }
        repoProtocolo.deleteById(id);
    }
}

```

### ServiceVacinacao.java

```java
// src\main\java\br\com\vestris\vaccination\application\ServiceVacinacao.java
package br.com.vestris.vaccination.application;

import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.vaccination.domain.model.Vacina;
import br.com.vestris.vaccination.domain.repository.RepositorioVacina;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceVacinacao {
    private final RepositorioVacina repositorio;

    public Vacina criar(Vacina novaVacina) {
        if (repositorio.existsByNome(novaVacina.getNome())) {
            throw new ExcecaoRegraNegocio("Já existe uma vacina cadastrada com este nome: " + novaVacina.getNome());
        }
        return repositorio.save(novaVacina);
    }

    public List<Vacina> listarTodas() {
        return repositorio.findAll();
    }

    public Vacina buscarPorId(UUID id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Vacina", id.toString()));
    }

    public Vacina atualizar(UUID id, Vacina dados) {
        Vacina existente = buscarPorId(id);

        // Verifica duplicidade de nome apenas se o nome mudou
        if (!existente.getNome().equalsIgnoreCase(dados.getNome()) && repositorio.existsByNome(dados.getNome())) {
            throw new ExcecaoRegraNegocio("Já existe outra vacina com este nome.");
        }

        existente.setNome(dados.getNome());
        existente.setFabricante(dados.getFabricante());
        existente.setTipoVacina(dados.getTipoVacina());
        existente.setDescricao(dados.getDescricao());
        existente.setDoencaAlvo(dados.getDoencaAlvo());

        return repositorio.save(existente);
    }

    public boolean existePorId(UUID id) {
        return repositorio.existsById(id);
    }

    // Adicione também para buscar o nome (para o DTO)
    public String buscarNomePorId(UUID id) {
        return repositorio.findById(id).map(v -> v.getNome()).orElse("Vacina Desconhecida");
    }

    public void deletar(UUID id) {
        if (!repositorio.existsById(id)) {
            throw new ExceptionRecursoNaoEncontrado("Vacina", id.toString());
        }
        try {
            repositorio.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ExcecaoRegraNegocio("Não é possível remover esta vacina pois ela está sendo usada em protocolos vacinais.");
        }
    }
}

```

---

## src\main\java\br\com\vestris\vaccination\domain\model

### AplicacaoVacina.java

```java
// src\main\java\br\com\vestris\vaccination\domain\model\AplicacaoVacina.java
package br.com.vestris.vaccination.domain.model;

import br.com.vestris.medicalrecord.domain.model.Paciente;
import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "aplicacoes_vacinas", schema = "medical_record_schema")
public class AplicacaoVacina extends EntidadeBase {
    @ManyToOne(optional = false)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Column(nullable = false)
    private UUID vacinaId; // Referência ao módulo vaccination

    @Column(nullable = false)
    private LocalDate dataAplicacao;

    private LocalDate dataProximaDose;

    private String lote;

    private UUID veterinarioId; // Quem aplicou

    @Column(columnDefinition = "TEXT")
    private String observacoes;

}

```

### ProtocoloVacinacao.java

```java
// src\main\java\br\com\vestris\vaccination\domain\model\ProtocoloVacinacao.java
package br.com.vestris.vaccination.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "protocolos_vacinacao", schema = "vaccination_schema")
public class ProtocoloVacinacao extends EntidadeBase {
    @Column(nullable = false)
    private UUID especieId; // FK Lógica

    @ManyToOne(optional = false)
    @JoinColumn(name = "vacina_id", nullable = false)
    private Vacina vacina; // FK Real (Mesmo módulo)

    @Column(nullable = false)
    private UUID referenciaId; // FK Lógica (CRUCIAL para respaldo legal)

    private Integer idadeMinimaDias;

    private Integer diasParaReforco;

    @Column(nullable = false)
    private boolean obrigatoria;

    @Column(columnDefinition = "TEXT")
    private String observacoes;
}

```

### Vacina.java

```java
// src\main\java\br\com\vestris\vaccination\domain\model\Vacina.java
package br.com.vestris.vaccination.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "vacinas", schema = "vaccination_schema")
public class Vacina extends EntidadeBase {
    @Column(nullable = false, length = 150)
    private String nome;

    @Column(length = 100)
    private String fabricante;

    @Column(length = 100)
    private String tipoVacina; // Ex: "Vírus Vivo", "Inativada"

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(length = 150)
    private String doencaAlvo; // Ex: "Doença de Newcastle"

}

```

---

## src\main\java\br\com\vestris\vaccination\domain\repository

### RepositorioAplicacaoVacina.java

```java
// src\main\java\br\com\vestris\vaccination\domain\repository\RepositorioAplicacaoVacina.java
package br.com.vestris.vaccination.domain.repository;

import br.com.vestris.vaccination.domain.model.AplicacaoVacina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioAplicacaoVacina extends JpaRepository<AplicacaoVacina, UUID> {
    List<AplicacaoVacina> findByPacienteIdOrderByDataAplicacaoDesc(UUID pacienteId);
}

```

### RepositorioProtocoloVacinacao.java

```java
// src\main\java\br\com\vestris\vaccination\domain\repository\RepositorioProtocoloVacinacao.java
package br.com.vestris.vaccination.domain.repository;

import br.com.vestris.vaccination.domain.model.ProtocoloVacinacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RepositorioProtocoloVacinacao extends JpaRepository<ProtocoloVacinacao, UUID> {
    List<ProtocoloVacinacao> findByEspecieId(UUID especieId);
    // Evitar cadastrar a mesma vacina duas vezes para a mesma espécie
    boolean existsByEspecieIdAndVacinaId(UUID especieId, UUID vacinaId);
}

```

### RepositorioVacina.java

```java
// src\main\java\br\com\vestris\vaccination\domain\repository\RepositorioVacina.java
package br.com.vestris.vaccination.domain.repository;

import br.com.vestris.vaccination.domain.model.Vacina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositorioVacina extends JpaRepository<Vacina, UUID> {
    boolean existsByNome(String nome);
}

```

---

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

---

## src\main\resources\swagger

### openapi.yml

```yaml
# src\main\resources\swagger\openapi.yml
openapi: 3.0.3
info:
  title: Vestris - Módulo Vacinação
  description: Gestão de Imunobiológicos
  version: 1.0.0
servers:
  - url: http://localhost:8080
    description: Servidor Local

paths:
  # --- VACINAS ---
  /api/v1/vacinas:
    $ref: './paths/vacinas.yml#/vacinas_colecao'

  /api/v1/vacinas/{id}:
    $ref: './paths/vacinas.yml#/vacinas_item'

  # --- PROTOCOLOS VACINAIS ---
  /api/v1/protocolos-vacinais:
    $ref: './paths/protocolos-vacinais.yml#/protocolos_colecao'

  /api/v1/protocolos-vacinais/{id}:
    $ref: './paths/protocolos-vacinais.yml#/protocolos_item'

  /api/v1/especies/{especieId}/protocolos-vacinais:
    $ref: './paths/protocolos-vacinais.yml#/protocolos_por_especie'

  /api/v1/pacientes/{pacienteId}/vacinas:
    $ref: './paths/vacinacao-paciente.yml#/vacinacao_paciente'
  /api/v1/vacinas-aplicadas/{id}:
    $ref: './paths/vacinacao-paciente.yml#/vacinacao_item'

components:
  schemas:
    VacinaRequest:
      $ref: "./components/schemas.yml#/VacinaRequest"
    VacinaResponse:
      $ref: "./components/schemas.yml#/VacinaResponse"
    ApiResponseVacina:
      $ref: "./components/schemas.yml#/ApiResponseVacina"
    ApiResponseListaVacina:
      $ref: "./components/schemas.yml#/ApiResponseListaVacina"
    ProtocoloVacinalRequest:
      $ref: "./components/schemas.yml#/ProtocoloVacinalRequest"
    ProtocoloVacinalResponse:
      $ref: "./components/schemas.yml#/ProtocoloVacinalResponse"
    ApiResponseProtocoloVacinal:
      $ref: "./components/schemas.yml#/ApiResponseVacina"
    ApiResponseListaProtocoloVacinal:
      $ref: "./components/schemas.yml#/ApiResponseListaVacina"

```

---

## src\main\resources\swagger\components

### schemas.yml

```yaml
# src\main\resources\swagger\components\schemas.yml
VacinaRequest:
  type: object
  required: [nome]
  properties:
    nome:
      type: string
    fabricante:
      type: string
    tipoVacina:
      type: string
    descricao:
      type: string
    doencaAlvo:
      type: string

VacinaResponse:
  type: object
  properties:
    id:
      type: string
      format: uuid
    nome:
      type: string
    fabricante:
      type: string
    tipoVacina:
      type: string
    descricao:
      type: string
    doencaAlvo:
      type: string
    criadoEm:
      type: string
      format: date-time

# --- VACINAÇÃO DO PACIENTE ---
AplicacaoVacinaRequest:
  type: object
  required: [ vacinaId, dataAplicacao ]
  properties:
    vacinaId:
      type: string
      format: uuid
    dataAplicacao:
      type: string
      format: date
    dataProximaDose:
      type: string
      format: date
      nullable: true
    lote:
      type: string
    observacoes:
      type: string

AplicacaoVacinaResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    pacienteId: { type: string, format: uuid }
    vacinaId: { type: string, format: uuid }
    vacinaNome: { type: string } # Nome enriquecido
    dataAplicacao: { type: string, format: date }
    dataProximaDose: { type: string, format: date }
    lote: { type: string }
    observacoes: { type: string }
    veterinarioNome: { type: string } # Quem aplicou

ApiResponseAplicacaoVacina:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { $ref: '#/AplicacaoVacinaResponse' }

ApiResponseListaAplicacaoVacina:
  type: object
  properties:
    sucesso: { type: boolean }
    dados:
      type: array
      items: { $ref: '#/AplicacaoVacinaResponse' }

# --- PROTOCOLO VACINAL (NOVO) ---
ProtocoloVacinalRequest:
  type: object
  required: [especieId, vacinaId, referenciaId, idadeMinimaDias]
  properties:
    especieId:
      type: string
      format: uuid
    vacinaId:
      type: string
      format: uuid
    referenciaId:
      type: string
      format: uuid
      description: "Fonte científica ou legal que exige/recomenda esta vacina"
    idadeMinimaDias:
      type: integer
      example: 45
    diasParaReforco:
      type: integer
      description: "Se nulo ou zero, é dose única"
      example: 21
    obrigatoria:
      type: boolean
      description: "Se é exigida por lei (IBAMA/MAPA)"
    observacoes:
      type: string
      example: "Apenas para animais que terão contato com aves de produção."

ProtocoloVacinalResponse:
  type: object
  properties:
    id:
      type: string
      format: uuid
    especieId:
      type: string
      format: uuid
    vacinaId:
      type: string
      format: uuid
    referenciaId:
      type: string
      format: uuid
    nomeVacina:
      type: string
    idadeMinimaDias:
      type: integer
    diasParaReforco:
      type: integer
    obrigatoria:
      type: boolean
    observacoes:
      type: string

# Wrappers
ApiResponseVacina:
  type: object
  properties:
    sucesso: { type: boolean }
    mensagem:
      type: string
    dados: { $ref: '#/VacinaResponse' }
ApiResponseListaVacina:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { type: array, items: { $ref: '#/VacinaResponse' } }

ApiResponseProtocoloVacinal:
  type: object
  properties:
    sucesso: { type: boolean }
    mensagem:
      type: string
    dados: { $ref: '#/ProtocoloVacinalResponse' }
ApiResponseListaProtocoloVacinal:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { type: array, items: { $ref: '#/ProtocoloVacinalResponse' } }
```

---

## src\main\resources\swagger\paths

### protocolos-vacinais.yml

```yaml
# src\main\resources\swagger\paths\protocolos-vacinais.yml
# Rota: /api/v1/protocolos-vacinais
protocolos_colecao:
  post:
    tags:
      - ProtocolosVacinais
    summary: Criar protocolo
    operationId: criarProtocoloVacinal
    requestBody:
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/ProtocoloVacinalRequest'
    responses:
      '200':
        description: Sucesso
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseProtocoloVacinal'

# Rota: /api/v1/especies/{especieId}/protocolos-vacinais
protocolos_por_especie:
  get:
    tags:
      - ProtocolosVacinais
    summary: Listar por espécie
    operationId: listarProtocolosPorEspecie
    parameters:
      - name: especieId
        in: path
        required: true
        schema:
          type: string
          format: uuid
    responses:
      '200':
        description: Lista
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseListaProtocoloVacinal'

# Rota: /api/v1/protocolos-vacinais/{id}
protocolos_item:
  parameters:
    - name: id
      in: path
      required: true
      schema:
        type: string
        format: uuid
  get:
    tags:
      - ProtocolosVacinais
    summary: Buscar protocolo por ID
    operationId: buscarProtocoloVacinalPorId
    responses:
      '200':
        description: Encontrado
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseProtocoloVacinal'
  put:
    tags:
      - ProtocolosVacinais
    summary: Atualizar protocolo
    operationId: atualizarProtocoloVacinal
    requestBody:
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/ProtocoloVacinalRequest'
    responses:
      '200':
        description: Atualizado
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseProtocoloVacinal'
  delete:
    tags:
      - ProtocolosVacinais
    summary: Remover protocolo
    operationId: deletarProtocoloVacinal
    responses:
      '204':
        description: Removido
```

### vacinacao-paciente.yml

```yaml
# src\main\resources\swagger\paths\vacinacao-paciente.yml
# ROTA: /api/v1/pacientes/{pacienteId}/vacinas
vacinacao_paciente:
  get:
    tags: [VacinacaoPaciente]
    summary: Listar vacinas aplicadas no paciente
    operationId: listarVacinasDoPaciente
    parameters:
      - name: pacienteId
        in: path
        required: true
        schema: { type: string, format: uuid }
    responses:
      '200':
        description: Histórico vacinal
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseListaAplicacaoVacina' }

  post:
    tags: [VacinacaoPaciente]
    summary: Registrar aplicação de vacina
    operationId: registrarVacinaPaciente
    parameters:
      - name: pacienteId
        in: path
        required: true
        schema: { type: string, format: uuid }
      - name: veterinarioId
        in: query
        required: true
        schema: { type: string, format: uuid }
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/AplicacaoVacinaRequest' }
    responses:
      '201':
        description: Registrado com sucesso
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseAplicacaoVacina' }

# ROTA: /api/v1/vacinas-aplicadas/{id}
vacinacao_item:
  delete:
    tags: [VacinacaoPaciente]
    summary: Remover registro de vacina (se erro)
    operationId: deletarVacinaAplicada
    parameters:
      - name: id
        in: path
        required: true
        schema: { type: string, format: uuid }
    responses:
      '204': { description: Removido }
```

### vacinas.yml

```yaml
# src\main\resources\swagger\paths\vacinas.yml
# Rota: /api/v1/vacinas
vacinas_colecao:
  post:
    tags:
      - Vacinas
    summary: Cadastrar vacina
    operationId: criarVacina
    requestBody:
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/VacinaRequest'
    responses:
      '200':
        description: Sucesso
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseVacina'
  get:
    tags:
      - Vacinas
    summary: Listar todas
    operationId: listarVacinas
    responses:
      '200':
        description: Lista
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseListaVacina'

# Rota: /api/v1/vacinas/{id}
vacinas_item:
  parameters:
    - name: id
      in: path
      required: true
      schema:
        type: string
        format: uuid
  get:
    tags:
      - Vacinas
    summary: Buscar por ID
    operationId: buscarVacinaPorId
    responses:
      '200':
        description: Encontrado
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseVacina'
  put:
    tags:
      - Vacinas
    summary: Atualizar vacina
    operationId: atualizarVacina
    requestBody:
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/VacinaRequest'
    responses:
      '200':
        description: Atualizado
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseVacina'
  delete:
    tags:
      - Vacinas
    summary: Remover vacina
    description: "Não permite remover se estiver em uso num protocolo"
    operationId: deletarVacina
    responses:
      '204':
        description: Removido
```

