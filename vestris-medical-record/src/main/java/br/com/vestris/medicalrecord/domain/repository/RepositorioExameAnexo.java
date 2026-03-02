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
