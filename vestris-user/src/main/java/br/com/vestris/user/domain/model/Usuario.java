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
    private String senha; // Será salvo criptografado (Hash)

    private String crmv; // Opcional

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Perfil perfil;

}
