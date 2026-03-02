package br.com.vestris.user.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "auditoria", schema = "users_schema")
public class Auditoria {

    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID clinicaId;

    @Column(nullable = false)
    private UUID usuarioId;

    @Convert(converter = AcaoAuditoriaConverter.class)
    @Column(nullable = false)
    private AcaoAuditoria acao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EntidadeAuditoria entidade;

    @Column(nullable = false)
    private UUID idAlvo;

    @Column(columnDefinition = "TEXT")
    private String descricaoCurta;

    // --- CORREÇÃO AQUI ---
    // Usamos TEXT simples para evitar conflito de tipo com o driver do Postgres
    @Column(columnDefinition = "TEXT", name = "metadados")
    private String metadados;

    @CreationTimestamp
    private LocalDateTime dataHora;
}
