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

