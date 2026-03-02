package br.com.vestris.user.domain.repository;

import br.com.vestris.user.domain.model.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioAuditoria extends JpaRepository<Auditoria, UUID> {
    // Método Mágico do Spring Data JPA (Funciona perfeitamente com Between)
    // O Delegate se encarregará de garantir que 'inicio' e 'fim' nunca sejam nulos
    List<Auditoria> findByClinicaIdAndDataHoraBetweenOrderByDataHoraDesc(
            UUID clinicaId,
            LocalDateTime inicio,
            LocalDateTime fim
    );

    // Método para quando não houver filtro de data (busca tudo da clínica)
    List<Auditoria> findByClinicaIdOrderByDataHoraDesc(UUID clinicaId);
}
