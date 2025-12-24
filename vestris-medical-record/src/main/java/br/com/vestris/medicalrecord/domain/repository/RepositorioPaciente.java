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
}
