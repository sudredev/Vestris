package br.com.silvet.clinical.domain.model;


import br.com.silvet.shared.domain.model.EntidadeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "doencas", schema = "clinical_schema")
public class Doenca extends EntidadeBase {

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(length = 150)
    private String nomeCientifico;

    @Column(columnDefinition = "TEXT")
    private String sintomas;

    // Foreign Key Lógica (Aponta para o ID da Espécie no outro módulo)
    @Column(nullable = false)
    private UUID especieId;
}
