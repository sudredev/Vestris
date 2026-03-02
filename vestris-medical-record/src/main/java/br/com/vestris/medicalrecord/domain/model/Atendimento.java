package br.com.vestris.medicalrecord.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
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
    private UUID veterinarioId;

    private UUID protocoloId;

    // --- NOVOS CAMPOS ---
    @Column(nullable = false)
    private String titulo; // Ex: "Consulta Inicial", "Retorno", "Vacinação"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAtendimento tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAtendimento status; // AGENDADO, REALIZADO, CANCELADO
    // --------------------

    // --- CORREÇÃO: ADICIONE ESTE CAMPO ---
    @Column(nullable = false)
    private LocalDateTime dataHora; // Data do agendamento ou da consulta
    // -------------------------------------

    @Column(columnDefinition = "TEXT") private String queixaPrincipal;
    @Column(columnDefinition = "TEXT") private String historicoClinico;
    @Column(columnDefinition = "TEXT") private String exameFisico;
    @Column(columnDefinition = "TEXT") private String diagnostico;
    @Column(columnDefinition = "TEXT") private String condutaClinica;
    @Column(columnDefinition = "TEXT") private String orientacoesManejo;
    @Column(columnDefinition = "TEXT") private String observacoes;

    // ADICIONE ESTE ENUM:
    public enum StatusAtendimento {
        AGENDADO,
        EM_ANDAMENTO,
        REALIZADO,
        CANCELADO
    }

    public enum TipoAtendimento {
        CONSULTA_CLINICA,
        VACINACAO,
        RETORNO,
        PROCEDIMENTO,
        CIRURGIA,
        EXAME
    }



}
