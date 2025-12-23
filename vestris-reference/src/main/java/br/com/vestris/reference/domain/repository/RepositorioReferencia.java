package br.com.vestris.reference.domain.repository;

import br.com.vestris.reference.domain.model.ReferenciaBibliografica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositorioReferencia extends JpaRepository<ReferenciaBibliografica, UUID> {
    boolean existsByTituloAndAutores(String titulo, String autores);
}
