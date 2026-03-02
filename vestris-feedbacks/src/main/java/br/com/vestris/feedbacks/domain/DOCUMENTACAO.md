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

