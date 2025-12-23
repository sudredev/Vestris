package br.com.vestris.reference.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "referencias", schema = "reference_schema")

public class ReferenciaBibliografica extends EntidadeBase {
    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String autores;

    private Integer ano;

    private String fonte; // Editora ou Revista

    private String url;

}
