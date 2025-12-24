package br.com.vestris.clinical.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "protocolo") // Evita loop infinito no log
@Entity
@Table(name = "dosagens", schema = "clinical_schema")
public class Dosagem extends EntidadeBase {

    @ManyToOne(optional = false)
    @JoinColumn(name = "protocolo_id", nullable = false)
    private Protocolo protocolo;

    @Column(nullable = false)
    private UUID medicamentoId; // FK Lógica para Pharmacology

    private Double doseMinima;
    private Double doseMaxima;
    private String unidade;   // mg/kg
    private String frequencia; // BID
    private String duracao;    // 10 dias
    private String via;        // Oral
}
