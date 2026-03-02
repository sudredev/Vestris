## src\main\java\br\com\vestris\pharmacology\domain\repository

### RepositorioContraindicacao.java

```java
// src\main\java\br\com\vestris\pharmacology\domain\repository\RepositorioContraindicacao.java
package br.com.vestris.pharmacology.domain.repository;

import br.com.vestris.pharmacology.domain.model.Contraindicacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioContraindicacao extends JpaRepository<Contraindicacao, UUID> {
    // CORREÇÃO: Busca por Princípio Ativo
    List<Contraindicacao> findByPrincipioAtivoId(UUID principioAtivoId);

    boolean existsByPrincipioAtivoIdAndEspecieId(UUID principioAtivoId, UUID especieId);

    // Para a validação de segurança
    @Query("SELECT c FROM Contraindicacao c WHERE c.principioAtivo.id = :principioId AND c.especieId = :especieId")
    List<Contraindicacao> encontrarRiscos(UUID principioId, UUID especieId);
}

```

### RepositorioDoseReferencia.java

```java
// src\main\java\br\com\vestris\pharmacology\domain\repository\RepositorioDoseReferencia.java
package br.com.vestris.pharmacology.domain.repository;

import br.com.vestris.pharmacology.domain.model.DoseReferencia;
import br.com.vestris.pharmacology.domain.model.enums.ViaAdministracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioDoseReferencia extends JpaRepository<DoseReferencia, UUID> {
    List<DoseReferencia> findByMedicamentoId(UUID medicamentoId);
}

```

### RepositorioMedicamento.java

```java
// src\main\java\br\com\vestris\pharmacology\domain\repository\RepositorioMedicamento.java
package br.com.vestris.pharmacology.domain.repository;

import br.com.vestris.pharmacology.domain.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositorioMedicamento extends JpaRepository<Medicamento, UUID> {

}

```

### RepositorioPrincipioAtivo.java

```java
// src\main\java\br\com\vestris\pharmacology\domain\repository\RepositorioPrincipioAtivo.java
package br.com.vestris.pharmacology.domain.repository;

import br.com.vestris.pharmacology.domain.model.PrincipioAtivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositorioPrincipioAtivo extends JpaRepository<PrincipioAtivo, UUID> {
    boolean existsByNome(String nome);
}


```

