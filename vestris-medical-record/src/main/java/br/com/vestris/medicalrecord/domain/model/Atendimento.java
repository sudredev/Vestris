package br.com.vestris.medicalrecord.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "atendimentos", schema = "medical_record_schema")
public class Atendimento extends EntidadeBase {
    @ManyToOne(optional = false)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Column(nullable = false)
    private UUID veterinarioId; // Quem atendeu

    private UUID protocoloId; // Opcional (Link com módulo clínico)

    // Dados Clínicos (Texto Livre)
    @Column(columnDefinition = "TEXT") private String queixaPrincipal;
    @Column(columnDefinition = "TEXT") private String historicoClinico;
    @Column(columnDefinition = "TEXT") private String exameFisico;
    @Column(columnDefinition = "TEXT") private String diagnostico;
    @Column(columnDefinition = "TEXT") private String condutaClinica;
    @Column(columnDefinition = "TEXT") private String observacoes;
}
