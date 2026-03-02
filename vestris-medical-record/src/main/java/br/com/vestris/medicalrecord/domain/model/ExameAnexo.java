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
