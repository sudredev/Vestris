## src\main\java\br\com\vestris\user\domain\model

### AcaoAuditoria.java

```java
// src\main\java\br\com\vestris\user\domain\model\AcaoAuditoria.java
package br.com.vestris.user.domain.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum para padronizar as ações de auditoria no sistema.
 * Garante type-safety e facilita buscas/filtros por ação específica.
 */
public enum AcaoAuditoria {
    // Paciente
    PACIENTE_CRIADO("Paciente criado"),
    PACIENTE_EDITADO("Paciente editado"),
    PACIENTE_CANCELADO("Paciente cancelado"),

    // Atendimento
    ATENDIMENTO_AGENDADO("Atendimento agendado"),
    ATENDIMENTO_INICIADO("Atendimento iniciado"),
    ATENDIMENTO_FINALIZADO("Atendimento finalizado"),
    ATENDIMENTO_CANCELADO("Atendimento cancelado"),
    ATENDIMENTO_EDITADO("Atendimento editado"),

    // Prontuário
    PRONTUARIO_EDITADO("Prontuário editado"),
    PRONTUARIO_SALVO("Prontuário salvo"),

    // Anexos e Exames
    ANEXO_ADICIONADO("Anexo/Exame adicionado"),
    ANEXO_REMOVIDO("Anexo/Exame removido"),

    // PDFs
    PDF_RECEITA_GERADO("PDF de receita médica gerado"),
    PDF_MANEJO_GERADO("PDF de manejo gerado"),
    PDF_PRONTUARIO_GERADO("PDF de prontuário gerado"),

    // Protocolos
    PROTOCOLO_CRIADO("Protocolo criado"),
    PROTOCOLO_EDITADO("Protocolo editado"),
    PROTOCOLO_REMOVIDO("Protocolo removido"),
    PROTOCOLO_CANCELADO("Protocolo cancelado"),

    // Genéricos
    RECURSO_CRIADO("Recurso criado"),
    RECURSO_EDITADO("Recurso editado"),
    RECURSO_DELETADO("Recurso deletado");

    private final String descricao;

    AcaoAuditoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    // Map of legacy names to enums for tolerant conversion from DB
    private static final Map<String, AcaoAuditoria> LEGACY_MAP = new HashMap<>();

    static {
        // Common legacy tokens that appeared in the project history
        LEGACY_MAP.put("CRIOU", RECURSO_CRIADO);
        LEGACY_MAP.put("FINALIZOU", ATENDIMENTO_FINALIZADO);
        LEGACY_MAP.put("STATUS_UPDATE", ATENDIMENTO_EDITADO);
        LEGACY_MAP.put("FINALIZADO", ATENDIMENTO_FINALIZADO);
        LEGACY_MAP.put("CRIADO", RECURSO_CRIADO);
        LEGACY_MAP.put("EDITOU", RECURSO_EDITADO);
        LEGACY_MAP.put("REMOVIDO", ANEXO_REMOVIDO);
        LEGACY_MAP.put("DELETADO", RECURSO_DELETADO);
        LEGACY_MAP.put("ATUALIZOU", RECURSO_EDITADO);
        // Add any other observed legacy tokens here
    }

    /**
     * Tolerant mapping from a database string to the enum.
     * Attempts in order: exact name match, description match, legacy aliases (case-insensitive).
     */
    public static AcaoAuditoria fromString(String s) {
        if (s == null) return null;
        String trimmed = s.trim();
        // 1) Try exact enum name
        try {
            return AcaoAuditoria.valueOf(trimmed);
        } catch (IllegalArgumentException ignored) {
        }

        // 2) Try matching by description
        for (AcaoAuditoria a : values()) {
            if (a.getDescricao().equalsIgnoreCase(trimmed)) return a;
        }

        // 3) Try legacy map (case-insensitive)
        AcaoAuditoria fromLegacy = LEGACY_MAP.get(trimmed.toUpperCase());
        if (fromLegacy != null) return fromLegacy;

        // 4) Fallback: try to match by transform (replace spaces/accents/underscores)
        String normalized = trimmed.toUpperCase().replaceAll("[^A-Z0-9]", "_");
        for (AcaoAuditoria a : values()) {
            if (a.name().equalsIgnoreCase(normalized)) return a;
        }

        // 5) Last resort: return null so caller can handle it
        return null;
    }
}

```

### AcaoAuditoriaConverter.java

```java
// src\main\java\br\com\vestris\user\domain\model\AcaoAuditoriaConverter.java
package br.com.vestris.user.domain.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class AcaoAuditoriaConverter implements AttributeConverter<AcaoAuditoria, String> {

    @Override
    public String convertToDatabaseColumn(AcaoAuditoria attribute) {
        return attribute == null ? null : attribute.name();
    }

    @Override
    public AcaoAuditoria convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        AcaoAuditoria v = AcaoAuditoria.fromString(dbData);
        if (v != null) return v;
        // If mapping failed, try direct enum name (case-insensitive)
        try {
            return AcaoAuditoria.valueOf(dbData);
        } catch (IllegalArgumentException e) {
            // fallback to null
            return null;
        }
    }
}


```

### Auditoria.java

```java
// src\main\java\br\com\vestris\user\domain\model\Auditoria.java
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

```

### Clinica.java

```java
// src\main\java\br\com\vestris\user\domain\model\Clinica.java
package br.com.vestris.user.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "clinicas", schema = "users_schema")
public class Clinica extends EntidadeBase {

    @Column(nullable = false)
    private String nomeFantasia;

    private String razaoSocial;
    private String cnpj;

    @Column(columnDefinition = "TEXT")
    private String endereco;

    private String telefone;
    private String emailContato;

    @Column(columnDefinition = "TEXT")
    private String logoBase64;
}

```

### EntidadeAuditoria.java

```java
// src\main\java\br\com\vestris\user\domain\model\EntidadeAuditoria.java
package br.com.vestris.user.domain.model;

/**
 * Enum para padronizar as entidades que podem ser auditadas no sistema.
 * Facilita categorização e filtros de logs por tipo de entidade.
 */
public enum EntidadeAuditoria {
    PACIENTE("Paciente"),
    ATENDIMENTO("Atendimento"),
    PRONTUARIO("Prontuário"),
    ANEXO("Anexo/Exame"),
    EXAME("Exame"),
    PDF("PDF/Documento"),
    RECEITA("Receita Médica"),
    MANEJO("Manejo/Orientação"),
    PROTOCOLO("Protocolo"),
    PROTOCOLO_INSTITUCIONAL("Protocolo Institucional"),
    PROTOCOLO_PROPRIO("Protocolo Próprio"),
    USUARIO("Usuário"),
    CLINICA("Clínica"),
    VACINACAO("Vacinação"),
    APLICACAO_VACINA("Aplicação de Vacina");

    private final String descricao;

    EntidadeAuditoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}


```

### Perfil.java

```java
// src\main\java\br\com\vestris\user\domain\model\Perfil.java
package br.com.vestris.user.domain.model;

public enum Perfil {
    ADMIN_GLOBAL,   // Dono do SaaS
    ADMIN_GESTOR,   // Gestor da Clínica (Não vê dados clínicos)
    ADMIN_CLINICO,  // Dono Técnico (Vê tudo + alterna modo)
    VETERINARIO,    // Funcionário (Vê pacientes da clínica + seus protocolos)
    ESTUDANTE
}

```

### Usuario.java

```java
// src\main\java\br\com\vestris\user\domain\model\Usuario.java
package br.com.vestris.user.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "usuarios", schema = "users_schema")
public class Usuario extends EntidadeBase {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    private String crmv;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Perfil perfil;

    // --- NOVO VÍNCULO ---
    @ManyToOne
    @JoinColumn(name = "clinica_id")
    private Clinica clinica;

    @Enumerated(EnumType.STRING)
    private UserScope scope; // GLOBAL ou TENANT

    public enum UserScope {
        GLOBAL, // Você (Vestris)
        TENANT  // Clientes
    }

}

```

