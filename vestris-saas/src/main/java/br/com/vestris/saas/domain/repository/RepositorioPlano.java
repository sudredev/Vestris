package br.com.vestris.saas.domain.repository;

import br.com.vestris.saas.domain.model.Plano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface RepositorioPlano extends JpaRepository<Plano, UUID> {
}
