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

