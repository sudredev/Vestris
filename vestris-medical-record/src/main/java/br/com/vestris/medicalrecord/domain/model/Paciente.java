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
