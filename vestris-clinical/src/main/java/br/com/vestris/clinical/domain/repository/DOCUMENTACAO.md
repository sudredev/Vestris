## src\main\java\br\com\vestris\clinical\domain\repository

### RepositorioDoenca.java

```java
// src\main\java\br\com\vestris\clinical\domain\repository\RepositorioDoenca.java
package br.com.vestris.clinical.domain.repository;

import br.com.vestris.clinical.domain.model.Doenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioDoenca extends JpaRepository<Doenca, UUID> {
    // Busca todas as doenças de uma espécie específica
    List<Doenca> findAllByEspecieId(UUID especieId);

    // Evita cadastrar a mesma doença para a mesma espécie duas vezes
    boolean existsByNomeAndEspecieId(String nome, UUID especieId);
}

```

### RepositorioProtocolo.java

```java
// src\main\java\br\com\vestris\clinical\domain\repository\RepositorioProtocolo.java
package br.com.vestris.clinical.domain.repository;

import br.com.vestris.clinical.domain.model.Protocolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioProtocolo extends JpaRepository<Protocolo, UUID> {
    List<Protocolo> findByDoencaId(UUID doencaId);

    // --- NOVO MÉTODO DE GOVERNANÇA ---
    @Query("SELECT p FROM Protocolo p WHERE p.doenca.id = :doencaId AND (" +
            "p.origem = 'OFICIAL' OR " +
            "(p.origem = 'INSTITUCIONAL' AND p.clinicaId = :clinicaId) OR " +
            "(p.origem = 'PROPRIO' AND p.autorId = :usuarioId)" +
            ")")
    List<Protocolo> listarAcessiveis(@Param("doencaId") UUID doencaId,
                                     @Param("clinicaId") UUID clinicaId,
                                     @Param("usuarioId") UUID usuarioId);
}

```

