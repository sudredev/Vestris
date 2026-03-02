## src\main\java\br\com\vestris\saas\domain\repository

### RepositorioAssinatura.java

```java
// src\main\java\br\com\vestris\saas\domain\repository\RepositorioAssinatura.java
package br.com.vestris.saas.domain.repository;

import br.com.vestris.saas.domain.model.Assinatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RepositorioAssinatura extends JpaRepository<Assinatura, UUID> {
    // Busca a assinatura ativa da clínica
    Optional<Assinatura> findByClinicaId(UUID clinicaId);
}

```

### RepositorioPlano.java

```java
// src\main\java\br\com\vestris\saas\domain\repository\RepositorioPlano.java
package br.com.vestris.saas.domain.repository;

import br.com.vestris.saas.domain.model.Plano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface RepositorioPlano extends JpaRepository<Plano, UUID> {
}

```

