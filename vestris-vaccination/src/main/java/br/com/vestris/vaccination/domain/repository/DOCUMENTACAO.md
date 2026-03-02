## src\main\java\br\com\vestris\vaccination\domain\repository

### RepositorioAplicacaoVacina.java

```java
// src\main\java\br\com\vestris\vaccination\domain\repository\RepositorioAplicacaoVacina.java
package br.com.vestris.vaccination.domain.repository;

import br.com.vestris.vaccination.domain.model.AplicacaoVacina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioAplicacaoVacina extends JpaRepository<AplicacaoVacina, UUID> {
    List<AplicacaoVacina> findByPacienteIdOrderByDataAplicacaoDesc(UUID pacienteId);
}

```

### RepositorioProtocoloVacinacao.java

```java
// src\main\java\br\com\vestris\vaccination\domain\repository\RepositorioProtocoloVacinacao.java
package br.com.vestris.vaccination.domain.repository;

import br.com.vestris.vaccination.domain.model.ProtocoloVacinacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RepositorioProtocoloVacinacao extends JpaRepository<ProtocoloVacinacao, UUID> {
    List<ProtocoloVacinacao> findByEspecieId(UUID especieId);
    // Evitar cadastrar a mesma vacina duas vezes para a mesma espécie
    boolean existsByEspecieIdAndVacinaId(UUID especieId, UUID vacinaId);
}

```

### RepositorioVacina.java

```java
// src\main\java\br\com\vestris\vaccination\domain\repository\RepositorioVacina.java
package br.com.vestris.vaccination.domain.repository;

import br.com.vestris.vaccination.domain.model.Vacina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositorioVacina extends JpaRepository<Vacina, UUID> {
    boolean existsByNome(String nome);
}

```

