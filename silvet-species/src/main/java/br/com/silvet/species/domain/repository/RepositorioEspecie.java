package br.com.silvet.species.domain.repository;

import br.com.silvet.species.domain.Especie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositorioEspecie extends JpaRepository<Especie, UUID> {
    // Verifica se já existe pelo nome científico
    boolean existsByNomeCientifico(String nomeCientifico);
}
