package br.com.vestris.pharmacology.domain.model;

import br.com.vestris.pharmacology.interfaces.dto.MedicamentoRequest;
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
    @JoinColumn(name = "principio_ativo_id", nullable = false)
    private PrincipioAtivo principioAtivo;

    @Column(name = "especie_id", nullable = false)
    private UUID especieId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gravidade gravidade;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descricao;

    private String referenciaTexto;

    public enum Gravidade {
        LEVE, MODERADA, GRAVE, FATAL
    }
}
