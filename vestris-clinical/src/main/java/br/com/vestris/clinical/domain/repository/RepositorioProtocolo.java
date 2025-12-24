package br.com.vestris.clinical.domain.repository;

import br.com.vestris.clinical.domain.model.Protocolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioProtocolo extends JpaRepository<Protocolo, UUID> {
    List<Protocolo> findByDoencaId(UUID doencaId);
}
