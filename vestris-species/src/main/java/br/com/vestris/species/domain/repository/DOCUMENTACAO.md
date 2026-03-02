## src\main\java\br\com\vestris\species\domain\repository

### RepositorioEspecie.java

```java
// src\main\java\br\com\vestris\species\domain\repository\RepositorioEspecie.java
package br.com.vestris.species.domain.repository;

import br.com.vestris.species.domain.Especie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositorioEspecie extends JpaRepository<Especie, UUID> {
    // Verifica se já existe pelo nome científico
    boolean existsByNomeCientifico(String nomeCientifico);
}

```

### RepositorioModeloExame.java

```java
// src\main\java\br\com\vestris\species\domain\repository\RepositorioModeloExame.java
package br.com.vestris.species.domain.repository;

import br.com.vestris.species.domain.ModeloExameFisico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RepositorioModeloExame extends JpaRepository<ModeloExameFisico, UUID> {
    Optional<ModeloExameFisico> findByEspecieId(UUID especieId);
}

```

