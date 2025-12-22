package br.com.silvet.pharmacology.domain.model;

import br.com.silvet.shared.domain.model.EntidadeBase;
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
