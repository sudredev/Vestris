## src\main\java\br\com\vestris\user\domain\repository

### RepositorioAuditoria.java

```java
// src\main\java\br\com\vestris\user\domain\repository\RepositorioAuditoria.java
package br.com.vestris.user.domain.repository;

import br.com.vestris.user.domain.model.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioAuditoria extends JpaRepository<Auditoria, UUID> {
    // Método Mágico do Spring Data JPA (Funciona perfeitamente com Between)
    // O Delegate se encarregará de garantir que 'inicio' e 'fim' nunca sejam nulos
    List<Auditoria> findByClinicaIdAndDataHoraBetweenOrderByDataHoraDesc(
            UUID clinicaId,
            LocalDateTime inicio,
            LocalDateTime fim
    );

    // Método para quando não houver filtro de data (busca tudo da clínica)
    List<Auditoria> findByClinicaIdOrderByDataHoraDesc(UUID clinicaId);
}

```

### RepositorioClinica.java

```java
// src\main\java\br\com\vestris\user\domain\repository\RepositorioClinica.java
package br.com.vestris.user.domain.repository;

import br.com.vestris.user.domain.model.Clinica;
import br.com.vestris.user.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioClinica extends JpaRepository<Clinica, UUID> {
}

```

### RepositorioUsuario.java

```java
// src\main\java\br\com\vestris\user\domain\repository\RepositorioUsuario.java
package br.com.vestris.user.domain.repository;

import br.com.vestris.user.domain.model.Perfil;
import br.com.vestris.user.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Usuario> findByPerfil(Perfil perfil);

    // Busca quem tem CRMV preenchido
    @Query("SELECT u FROM Usuario u WHERE u.crmv IS NOT NULL AND u.crmv <> ''")
    List<Usuario> findAllComCrmv();

    // Busca por Clínica (para listar equipe)
    List<Usuario> findByClinicaId(UUID clinicaId);

    // --- NOVO: CONTAGEM PARA VALIDAÇÃO DE PLANO ---
    long countByClinicaId(UUID clinicaId);;

}

```

