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

    // Segurança Clínica (NOVO)
    ALERTA_DOSE("Alerta de Dose Disparado"), // <--- ADICIONADO

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

    private static final Map<String, AcaoAuditoria> LEGACY_MAP = new HashMap<>();

    static {
        LEGACY_MAP.put("CRIOU", RECURSO_CRIADO);
        LEGACY_MAP.put("FINALIZOU", ATENDIMENTO_FINALIZADO);
        LEGACY_MAP.put("STATUS_UPDATE", ATENDIMENTO_EDITADO);
        LEGACY_MAP.put("FINALIZADO", ATENDIMENTO_FINALIZADO);
        LEGACY_MAP.put("CRIADO", RECURSO_CRIADO);
        LEGACY_MAP.put("EDITOU", RECURSO_EDITADO);
        LEGACY_MAP.put("REMOVIDO", ANEXO_REMOVIDO);
        LEGACY_MAP.put("DELETADO", RECURSO_DELETADO);
        LEGACY_MAP.put("ATUALIZOU", RECURSO_EDITADO);
    }

    public static AcaoAuditoria fromString(String s) {
        if (s == null) return null;
        String trimmed = s.trim();
        try {
            return AcaoAuditoria.valueOf(trimmed);
        } catch (IllegalArgumentException ignored) {
        }

        for (AcaoAuditoria a : values()) {
            if (a.getDescricao().equalsIgnoreCase(trimmed)) return a;
        }

        AcaoAuditoria fromLegacy = LEGACY_MAP.get(trimmed.toUpperCase());
        if (fromLegacy != null) return fromLegacy;

        String normalized = trimmed.toUpperCase().replaceAll("[^A-Z0-9]", "_");
        for (AcaoAuditoria a : values()) {
            if (a.name().equalsIgnoreCase(normalized)) return a;
        }

        return null;
    }
}
