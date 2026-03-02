package br.com.vestris.pharmacology.domain.model.enums;

public enum StatusSegurancaDose {
    SEGURO,
    SUBDOSE,
    SUPERDOSE,
    SEM_REFERENCIA,
    NAO_VALIDADO // Para unidades não suportadas ainda (ex: UI/kg)
}
