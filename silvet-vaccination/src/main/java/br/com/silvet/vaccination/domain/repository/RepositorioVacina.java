package br.com.silvet.vaccination.domain.repository;

import br.com.silvet.vaccination.domain.model.Vacina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositorioVacina extends JpaRepository<Vacina, UUID> {
    boolean existsByNome(String nome);
}
