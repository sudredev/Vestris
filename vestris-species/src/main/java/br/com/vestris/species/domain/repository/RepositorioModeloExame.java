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
