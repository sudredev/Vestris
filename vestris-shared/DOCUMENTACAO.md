# Documentação do projeto vestris-shared

## Índice de Diretórios e Arquivos

- [.](#)
  - [pom.xml](#pomxml)
- [src\main\java\br\com\vestris\shared\domain\exceptions](#srcmainjavabrcomvestrisshareddomainexceptions)
  - [ExcecaoRegraNegocio.java](#srcmainjavabrcomvestrisshareddomainexceptionsexcecaoregranegociojava)
  - [ExceptionRecursoNaoEncontrado.java](#srcmainjavabrcomvestrisshareddomainexceptionsexceptionrecursonaoencontradojava)
- [src\main\java\br\com\vestris\shared\domain\model](#srcmainjavabrcomvestrisshareddomainmodel)
  - [EntidadeBase.java](#srcmainjavabrcomvestrisshareddomainmodelentidadebasejava)
- [src\main\java\br\com\vestris\shared\infrastructure\dto](#srcmainjavabrcomvestrissharedinfrastructuredto)
  - [RespostaApi.java](#srcmainjavabrcomvestrissharedinfrastructuredtorespostaapijava)
- [src\main\java\br\com\vestris\shared\infrastructure\handler](#srcmainjavabrcomvestrissharedinfrastructurehandler)
  - [ManipuladorGlobalDeExceptions.java](#srcmainjavabrcomvestrissharedinfrastructurehandlermanipuladorglobaldeexceptionsjava)
- [src\main\java\br\com\vestris\shared\infrastructure\helper](#srcmainjavabrcomvestrissharedinfrastructurehelper)
  - [HelperAuditoria.java](#srcmainjavabrcomvestrissharedinfrastructurehelperhelperauditoriajava)

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

    <artifactId>vestris-shared</artifactId>

    <dependencies>
        <!-- JPA: Para usar @Entity, @Id, @MappedSuperclass -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <!-- WEB: Para usar anotações JSON e ResponseEntity (Corrigido de webmvc para web) -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- VALIDATION: Para usar @NotNull, @NotBlank nos DTOs -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>

        <!-- LOMBOK: Para getters/setters automáticos -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <!-- TEST: Dependência única para testes (substitui todas aquelas separadas) -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```

---

## src\main\java\br\com\vestris\shared\domain\exceptions

### ExcecaoRegraNegocio.java

```java
// src\main\java\br\com\vestris\shared\domain\exceptions\ExcecaoRegraNegocio.java
package br.com.vestris.shared.domain.exceptions;

public class ExcecaoRegraNegocio extends RuntimeException {
    public ExcecaoRegraNegocio(String message) {
        super(message);
    }
}

```

### ExceptionRecursoNaoEncontrado.java

```java
// src\main\java\br\com\vestris\shared\domain\exceptions\ExceptionRecursoNaoEncontrado.java
package br.com.vestris.shared.domain.exceptions;

public class ExceptionRecursoNaoEncontrado extends RuntimeException {
    public ExceptionRecursoNaoEncontrado(String nomeDoRecurso, String id) {
        super(String.format("%s não encontrado com id: %s", nomeDoRecurso, id));
    }
}

```

---

## src\main\java\br\com\vestris\shared\domain\model

### EntidadeBase.java

```java
// src\main\java\br\com\vestris\shared\domain\model\EntidadeBase.java
package br.com.vestris.shared.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public abstract class EntidadeBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @CreationTimestamp
    @Column(name = "criado_em", updatable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;

    // Equals e HashCode baseados apenas no ID (Boas práticas do Hibernate)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntidadeBase)) return false;
        EntidadeBase that = (EntidadeBase) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}

```

---

## src\main\java\br\com\vestris\shared\infrastructure\dto

### RespostaApi.java

```java
// src\main\java\br\com\vestris\shared\infrastructure\dto\RespostaApi.java
package br.com.vestris.shared.infrastructure.dto;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RespostaApi<T>{
    private boolean sucesso;
    private String mensagem;
    private T dados; // O objeto que você quer retornar (ex: Especie)
    private LocalDateTime dataHora;

    // Método estático para facilitar o sucesso
    public static <T> RespostaApi<T> sucesso(T dados) {
        return RespostaApi.<T>builder()
                .sucesso(true)
                .dados(dados)
                .dataHora(LocalDateTime.now())
                .build();
    }

    // Método estático para facilitar o erro
    public static <T> RespostaApi<T> erro(String mensagem) {
        return RespostaApi.<T>builder()
                .sucesso(false)
                .mensagem(mensagem)
                .dataHora(LocalDateTime.now())
                .build();
    }
}

```

---

## src\main\java\br\com\vestris\shared\infrastructure\handler

### ManipuladorGlobalDeExceptions.java

```java
// src\main\java\br\com\vestris\shared\infrastructure\handler\ManipuladorGlobalDeExceptions.java
package br.com.vestris.shared.infrastructure.handler;

import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.shared.infrastructure.dto.RespostaApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ManipuladorGlobalDeExceptions {

    // Trata erros de Regra de Negócio (ex: Espécie não existe, Duplicidade)
    // Retorna 400 Bad Request
    @ExceptionHandler(ExcecaoRegraNegocio.class)
    public ResponseEntity<RespostaApi<Void>> tratarRegraNegocio(ExcecaoRegraNegocio ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(RespostaApi.erro(ex.getMessage()));
    }

    // Trata erros de Recurso Não Encontrado (ex: ID inexistente no banco)
    // Retorna 404 Not Found
    @ExceptionHandler(ExceptionRecursoNaoEncontrado.class)
    public ResponseEntity<RespostaApi<Void>> tratarRecursoNaoEncontrado(ExceptionRecursoNaoEncontrado ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(RespostaApi.erro(ex.getMessage()));
    }

    // Trata qualquer outro erro não previsto (NullPointer, Banco fora do ar, etc)
    // Retorna 500 Internal Server Error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespostaApi<Void>> tratarErroGenerico(Exception ex) {
        ex.printStackTrace(); // Imprime no terminal para o dev ver
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(RespostaApi.erro("Ocorreu um erro interno no servidor. Contate o suporte."));
    }

}

```

---

## src\main\java\br\com\vestris\shared\infrastructure\helper

### HelperAuditoria.java

```java
// src\main\java\br\com\vestris\shared\infrastructure\helper\HelperAuditoria.java
package br.com.vestris.shared.infrastructure.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Helper centralizado para operações de auditoria.
 * Responsável por:
 * - Extrair clinicaId de diferentes contextos
 * - Montar metadados estruturados
 * - Serializar dados para auditoria
 */
@Component
@RequiredArgsConstructor
public class HelperAuditoria {
    public Map<String, Object> montarMetadados(Object... args) {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            if (i + 1 < args.length) {
                map.put(String.valueOf(args[i]), args[i + 1]);
            }
        }
        return map;
    }

    public Map<String, Object> montarMetadadosPaciente(String nome, String especie, String tutor) {
        return montarMetadados(
                "nomePaciente", nome,
                "especie", especie,
                "tutor", tutor
        );
    }

    public Map<String, Object> montarMetadadosAtendimento(String pacienteNome, String status, UUID vetId, Object... extras) {
        Map<String, Object> map = montarMetadados(
                "paciente", pacienteNome,
                "status", status,
                "veterinarioId", vetId
        );
        // Adiciona extras
        if (extras != null) {
            map.putAll(montarMetadados(extras));
        }
        return map;
    }

    public Map<String, Object> montarMetadadosProtocolo(String titulo, String origem, UUID autorId, Object... extras) {
        Map<String, Object> map = montarMetadados(
                "titulo", titulo,
                "origem", origem,
                "autorId", autorId
        );
        if (extras != null) map.putAll(montarMetadados(extras));
        return map;
    }
}


```

