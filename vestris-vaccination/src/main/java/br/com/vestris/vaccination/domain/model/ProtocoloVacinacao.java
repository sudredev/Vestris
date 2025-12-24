package br.com.vestris.vaccination.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "protocolos_vacinacao", schema = "vaccination_schema")
public class ProtocoloVacinacao extends EntidadeBase {
    @Column(nullable = false)
    private UUID especieId; // FK Lógica

    @ManyToOne(optional = false)
    @JoinColumn(name = "vacina_id", nullable = false)
    private Vacina vacina; // FK Real (Mesmo módulo)

    @Column(nullable = false)
    private UUID referenciaId; // FK Lógica (CRUCIAL para respaldo legal)

    private Integer idadeMinimaDias;

    private Integer diasParaReforco;

    @Column(nullable = false)
    private boolean obrigatoria;

    @Column(columnDefinition = "TEXT")
    private String observacoes;
}
