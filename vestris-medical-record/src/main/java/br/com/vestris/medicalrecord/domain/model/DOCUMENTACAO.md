## src\main\java\br\com\vestris\medicalrecord\domain\model

### Atendimento.java

```java
// src\main\java\br\com\vestris\medicalrecord\domain\model\Atendimento.java
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

}

```

### ExameAnexo.java

```java
// src\main\java\br\com\vestris\medicalrecord\domain\model\ExameAnexo.java
package br.com.vestris.medicalrecord.domain.model;

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
@Table(name = "exames_anexos", schema = "medical_record_schema")
public class ExameAnexo extends EntidadeBase {
    @Column(nullable = false)
    private UUID atendimentoId; // Vínculo com o atendimento

    @Column(nullable = false)
    private String nomeArquivo;

    private String tipoArquivo; // pdf, jpg, png

    @Column(columnDefinition = "TEXT")
    private String urlArquivo; // URL S3 ou Path Local

    @Column(columnDefinition = "TEXT")
    private String observacoes;
}

```

### Paciente.java

```java
// src\main\java\br\com\vestris\medicalrecord\domain\model\Paciente.java
package br.com.vestris.medicalrecord.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "pacientes", schema = "medical_record_schema")
public class Paciente extends EntidadeBase {

    @Column(nullable = false)
    private UUID veterinarioId;

    @Column(nullable = false)
    private UUID especieId;

    @Column(nullable = false)
    private String nome;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String dadosTutor;

    private String identificacaoAnimal; // Ex: Anilha, Marcação

    private String microchip;

    @Column(length = 50)
    private String pelagemCor;

    private String sexo;

    private LocalDate dataNascimento;

    private Integer pesoAtualGramas;

}

```

