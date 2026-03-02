package br.com.vestris.saas.domain.repository;

import br.com.vestris.saas.domain.model.Assinatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RepositorioAssinatura extends JpaRepository<Assinatura, UUID> {
    // Busca a assinatura ativa da clínica
    Optional<Assinatura> findByClinicaId(UUID clinicaId);
}
