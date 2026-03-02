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

