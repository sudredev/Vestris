package br.com.vestris.vaccination.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "vacinas", schema = "vaccination_schema")
public class Vacina extends EntidadeBase {
    @Column(nullable = false, length = 150)
    private String nome;

    @Column(length = 100)
    private String fabricante;

    @Column(length = 100)
    private String tipoVacina; // Ex: "Vírus Vivo", "Inativada"

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(length = 150)
    private String doencaAlvo; // Ex: "Doença de Newcastle"

}
