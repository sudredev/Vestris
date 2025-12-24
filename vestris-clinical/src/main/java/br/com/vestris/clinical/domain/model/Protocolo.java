package br.com.vestris.clinical.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "protocolos", schema = "clinical_schema")
public class Protocolo extends EntidadeBase {

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @ManyToOne(optional = false)
    @JoinColumn(name = "doenca_id", nullable = false)
    private Doenca doenca;

    @Column(nullable = false)
    private UUID referenciaId; // FK Lógica para Reference

    // Um Protocolo tem várias Dosagens
    @OneToMany(mappedBy = "protocolo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dosagem> dosagens = new ArrayList<>();

    // Método helper para adicionar dosagem
    public void adicionarDosagem(Dosagem dosagem) {
        dosagens.add(dosagem);
        dosagem.setProtocolo(this);
    }
}
