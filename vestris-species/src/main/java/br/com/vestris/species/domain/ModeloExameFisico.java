package br.com.vestris.species.domain;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "modelos_exame_fisico", schema = "species_schema")
public class ModeloExameFisico extends EntidadeBase {
    @Column(nullable = false)
    private UUID especieId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String textoBase; // Guardará o JSON stringificado
}
