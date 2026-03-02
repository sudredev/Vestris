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
