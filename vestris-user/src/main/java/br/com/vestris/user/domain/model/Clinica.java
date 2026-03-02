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
