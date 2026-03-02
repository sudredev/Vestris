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
