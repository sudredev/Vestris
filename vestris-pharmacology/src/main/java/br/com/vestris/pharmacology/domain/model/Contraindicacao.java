package br.com.vestris.pharmacology.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "contraindicacoes", schema = "pharmacology_schema")
public class Contraindicacao extends EntidadeBase {
    @ManyToOne(optional = false)
    @JoinColumn(name = "medicamento_id", nullable = false)
    private Medicamento medicamento;

    @Column(nullable = false)
    private UUID especieId; // FK Lógica para Species

    @Column(nullable = false)
    private UUID referenciaId; // FK Lógica para Reference

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gravidade gravidade;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descricao;

    public enum Gravidade {
        LEVE, MODERADA, GRAVE, FATAL
    }
}
