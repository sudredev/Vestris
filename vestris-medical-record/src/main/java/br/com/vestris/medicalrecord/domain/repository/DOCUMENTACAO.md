## src\main\java\br\com\vestris\medicalrecord\domain\repository

### RepositorioAtendimento.java

```java
// src\main\java\br\com\vestris\medicalrecord\domain\repository\RepositorioAtendimento.java
package br.com.vestris.medicalrecord.domain.repository;

import br.com.vestris.medicalrecord.domain.model.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioAtendimento extends JpaRepository<Atendimento, UUID>, JpaSpecificationExecutor<Atendimento> {
    List<Atendimento> findByPacienteIdOrderByCriadoEmDesc(UUID pacienteId);
}

```

### RepositorioExameAnexo.java

```java
// src\main\java\br\com\vestris\medicalrecord\domain\repository\RepositorioExameAnexo.java
package br.com.vestris.medicalrecord.domain.repository;

import br.com.vestris.medicalrecord.domain.model.ExameAnexo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioExameAnexo extends JpaRepository<ExameAnexo, UUID> {
    List<ExameAnexo> findByAtendimentoId(UUID atendimentoId);
}

```

### RepositorioPaciente.java

```java
// src\main\java\br\com\vestris\medicalrecord\domain\repository\RepositorioPaciente.java
package br.com.vestris.medicalrecord.domain.repository;

import br.com.vestris.medicalrecord.domain.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioPaciente extends JpaRepository<Paciente, UUID> {
    // Busca os pacientes de um determinado veterinário
    List<Paciente> findByVeterinarioId(UUID veterinarioId);

    // --- NOVO: BUSCA INSTITUCIONAL ---
    // Traz pacientes de qualquer veterinário que esteja na lista (Equipe)
    List<Paciente> findByVeterinarioIdIn(List<UUID> veterinarioIds);
}

```

