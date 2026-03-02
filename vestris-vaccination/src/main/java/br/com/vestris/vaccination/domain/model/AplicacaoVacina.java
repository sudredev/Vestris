package br.com.vestris.vaccination.domain.model;

import br.com.vestris.medicalrecord.domain.model.Paciente;
import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "aplicacoes_vacinas", schema = "medical_record_schema")
public class AplicacaoVacina extends EntidadeBase {
    @ManyToOne(optional = false)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Column(nullable = false)
    private UUID vacinaId;

    // Campos existentes
    @Column(nullable = false)
    private LocalDate dataAplicacao;
    private LocalDate dataProximaDose;
    private String lote;
    private UUID veterinarioId;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    // --- NOVOS CAMPOS PARA O HUB ---
    private String fabricante; // Pode ser diferente do cadastro da vacina (lote específico)
    private String via;        // SC, IM, Oral...
    private String doseAplicada; // Ex: "1ml", "0.5ml"

}
