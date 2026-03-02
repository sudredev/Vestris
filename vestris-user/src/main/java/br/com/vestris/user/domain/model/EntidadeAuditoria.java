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

