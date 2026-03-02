## src\main\java\br\com\vestris\pharmacology\domain\model

### Contraindicacao.java

```java
// src\main\java\br\com\vestris\pharmacology\domain\model\Contraindicacao.java
package br.com.vestris.pharmacology.domain.model;

import br.com.vestris.pharmacology.interfaces.dto.MedicamentoRequest;
import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "contraindicacoes", schema = "pharmacology_schema")
public class Contraindicacao extends EntidadeBase {
    @ManyToOne(optional = false)
    @JoinColumn(name = "principio_ativo_id", nullable = false)
    private PrincipioAtivo principioAtivo;

    @Column(name = "especie_id", nullable = false)
    private UUID especieId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gravidade gravidade;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descricao;

    private String referenciaTexto;

    public enum Gravidade {
        LEVE, MODERADA, GRAVE, FATAL
    }
}

```

### DoseReferencia.java

```java
// src\main\java\br\com\vestris\pharmacology\domain\model\DoseReferencia.java
package br.com.vestris.pharmacology.domain.model;

import br.com.vestris.pharmacology.domain.model.enums.UnidadeDose;
import br.com.vestris.pharmacology.domain.model.enums.ViaAdministracao;
import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "doses_referencia", schema = "pharmacology_schema")
public class DoseReferencia extends EntidadeBase {

    @Column(nullable = false)
    private UUID medicamentoId;
    private UUID especieId;
    private UUID doencaId;
    private UUID clinicaId;

    @Enumerated(EnumType.STRING)
    private ViaAdministracao via;

    @Enumerated(EnumType.STRING)
    private UnidadeDose unidade;

    // NUMERIC no banco -> BigDecimal no Java
    private BigDecimal doseMin;
    private BigDecimal doseMax;

    private String origem; // OFICIAL ou INSTITUCIONAL (String pra simplificar persistencia com Check)
    private String nivelConfianca;
    private String fonteBibliografica;
    private String observacoes;

}

```

### Medicamento.java

```java
// src\main\java\br\com\vestris\pharmacology\domain\model\Medicamento.java
package br.com.vestris.pharmacology.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "medicamentos", schema = "pharmacology_schema")
public class Medicamento extends EntidadeBase {
    @Column(nullable = false, length = 150)
    private String nome;

    @Column(length = 50)
    private String concentracao; // Ex: 10mg/ml

    private String fabricante;

    private String formaFarmaceutica; // Ex: Comprimido, Injetável

    // Relacionamento forte: Medicamento PRECISA de um Princípio Ativo
    @ManyToOne(optional = false)
    @JoinColumn(name = "principio_ativo_id", nullable = false)
    private PrincipioAtivo principioAtivo;

}

```

### PrincipioAtivo.java

```java
// src\main\java\br\com\vestris\pharmacology\domain\model\PrincipioAtivo.java
package br.com.vestris.pharmacology.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "principios_ativos", schema = "pharmacology_schema")
public class PrincipioAtivo extends EntidadeBase {

    @Column(nullable = false, unique = true, length = 150)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(length = 100)
    private String grupoFarmacologico;

}

```

