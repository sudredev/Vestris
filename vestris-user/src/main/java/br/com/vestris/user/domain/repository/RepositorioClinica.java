package br.com.vestris.user.domain.repository;

import br.com.vestris.user.domain.model.Clinica;
import br.com.vestris.user.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioClinica extends JpaRepository<Clinica, UUID> {
}
