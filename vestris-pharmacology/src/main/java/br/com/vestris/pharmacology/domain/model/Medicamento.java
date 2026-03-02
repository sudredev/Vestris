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
