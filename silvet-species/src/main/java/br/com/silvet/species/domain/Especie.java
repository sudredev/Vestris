package br.com.silvet.species.domain;

import br.com.silvet.shared.domain.model.EntidadeBase;
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
    @Column(nullable = false, length = 100)
    private String nomePopular;

    @Column(nullable = false, unique = true, length = 100)
    private String nomeCientifico;

    @Column(length = 50)
    private String familiaTaxonomica;

    @Column(columnDefinition = "TEXT")
    private String descricao;
}
