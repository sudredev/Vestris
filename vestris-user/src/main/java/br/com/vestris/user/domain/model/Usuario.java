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
