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
