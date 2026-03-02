package br.com.vestris.user.domain.model;

public enum Perfil {
    ADMIN_GLOBAL,   // Dono do SaaS
    ADMIN_GESTOR,   // Gestor da Clínica (Não vê dados clínicos)
    ADMIN_CLINICO,  // Dono Técnico (Vê tudo + alterna modo)
    VETERINARIO,    // Funcionário (Vê pacientes da clínica + seus protocolos)
    ESTUDANTE
}
