package br.com.vestris.vaccination.domain.repository;

import br.com.vestris.vaccination.domain.model.Vacina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositorioVacina extends JpaRepository<Vacina, UUID> {
    boolean existsByNome(String nome);
}
