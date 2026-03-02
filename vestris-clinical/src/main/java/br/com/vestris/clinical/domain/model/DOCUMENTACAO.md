## src\main\java\br\com\vestris\clinical\domain\model

### CalculoResultadoDTO.java

```java
// src\main\java\br\com\vestris\clinical\domain\model\CalculoResultadoDTO.java
package br.com.vestris.clinical.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data // Gera Getters, Setters, toString, etc.
@Builder // Permite criar o objeto de forma fluida (.builder()...build())
@NoArgsConstructor // Construtor vazio
@AllArgsConstructor // Construtor com todos os argumentos
public class CalculoResultadoDTO {
    // Contexto
    private String protocoloTitulo;
    private String medicamentoNome;
    private String referencia;

    // Dados de Entrada Normalizados
    private Double pesoKg;

    // Resultado Massa (Dose)
    private Double doseMinMg;
    private Double doseMaxMg;

    // Resultado Volume (Líquido)
    private Double volMinMl;
    private Double volMaxMl;

    // Metadados do Medicamento/Protocolo
    private String concentracao;
    private String frequencia;
    private String via;
    private String duracao;

    // Segurança
    private List<String> alertas;

    // --- NOVOS CAMPOS DE SEGURANÇA (VALIDAÇÃO) ---
    private String statusSeguranca; // SEGURO, SUBDOSE, etc.
    private String mensagemSeguranca;
    private Double refMin; // Dose de referência (mg/kg) mínima usada para validar
    private Double refMax; // Dose de referência (mg/kg) máxima usada para validar
    private String refFonte; // Fonte da referência (Ex: Carpenter)

    // --- CÁLCULO LIVRE ---
    private Double doseCalculadaMg;
    private Double volumeCalculadoMl;
}

```

### Doenca.java

```java
// src\main\java\br\com\vestris\clinical\domain\model\Doenca.java
package br.com.vestris.clinical.domain.model;


import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "doencas", schema = "clinical_schema")
public class Doenca extends EntidadeBase {

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(length = 150)
    private String nomeCientifico;

    @Column(columnDefinition = "TEXT")
    private String sintomas;

    // Foreign Key Lógica (Aponta para o ID da Espécie no outro módulo)
    @Column(nullable = false)
    private UUID especieId;
}

```

### Dosagem.java

```java
// src\main\java\br\com\vestris\clinical\domain\model\Dosagem.java
package br.com.vestris.clinical.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "protocolo") // Evita loop infinito no log
@Entity
@Table(name = "dosagens", schema = "clinical_schema")
public class Dosagem extends EntidadeBase {
    @ManyToOne(optional = false)
    @JoinColumn(name = "protocolo_id", nullable = false)
    private Protocolo protocolo;

    // --- MEDICAMENTO (HÍBRIDO) ---
    @Column(name = "medicamento_id", nullable = true)
    private UUID medicamentoId;

    @Column(name = "medicamento_texto_livre")
    private String medicamentoTextoLivre;

    private Double doseMinima;
    private Double doseMaxima;
    private String unidade;
    private String frequencia;
    private String duracao;
    private String via;
}

```

### Protocolo.java

```java
// src\main\java\br\com\vestris\clinical\domain\model\Protocolo.java
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

```

### ProtocoloCompletoDTO.java

```java
// src\main\java\br\com\vestris\clinical\domain\model\ProtocoloCompletoDTO.java
package br.com.vestris.clinical.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProtocoloCompletoDTO {
    private Doenca doenca;
    private List<Protocolo> protocolos;
}

```

