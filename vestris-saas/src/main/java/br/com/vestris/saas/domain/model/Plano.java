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
