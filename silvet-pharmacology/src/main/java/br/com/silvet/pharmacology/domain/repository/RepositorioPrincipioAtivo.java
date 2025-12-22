package br.com.silvet.pharmacology.domain.repository;

import br.com.silvet.pharmacology.domain.model.PrincipioAtivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositorioPrincipioAtivo extends JpaRepository<PrincipioAtivo, UUID> {
    boolean existsByNome(String nome);
}

