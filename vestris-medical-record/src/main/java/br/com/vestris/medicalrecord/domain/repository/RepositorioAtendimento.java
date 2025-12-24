package br.com.vestris.medicalrecord.domain.repository;

import br.com.vestris.medicalrecord.domain.model.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioAtendimento extends JpaRepository<Atendimento, UUID> {
    List<Atendimento> findByPacienteIdOrderByCriadoEmDesc(UUID pacienteId);
}
