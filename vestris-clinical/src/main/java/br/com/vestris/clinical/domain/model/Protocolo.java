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

    // --- DOENÇA (HÍBRIDO) ---
    @ManyToOne(optional = true)
    @JoinColumn(name = "doenca_id", nullable = true)
    private Doenca doenca;

    @Column(name = "doenca_texto_livre")
    private String doencaTextoLivre;

    // --- REFERÊNCIA (HÍBRIDO) ---
    @Column(name = "referencia_id")
    private UUID referenciaId;

    @Column(name = "referencia_texto")
    private String referenciaTexto;

    // --- ORIGEM ---
    @Enumerated(EnumType.STRING)
    private OrigemProtocolo origem; // OFICIAL, PROPRIO

    private UUID autorId;

    @Column(name = "clinica_id")
    private UUID clinicaId; // Novo campo

    public enum OrigemProtocolo {
        OFICIAL,
        PROPRIO,
        INSTITUCIONAL // Novo Enum
    }

    @OneToMany(mappedBy = "protocolo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dosagem> dosagens = new ArrayList<>();

    public void adicionarDosagem(Dosagem dosagem) {
        dosagens.add(dosagem);
        dosagem.setProtocolo(this);
    }
}
