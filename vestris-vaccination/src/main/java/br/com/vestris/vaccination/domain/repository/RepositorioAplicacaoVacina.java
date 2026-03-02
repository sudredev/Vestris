package br.com.vestris.vaccination.domain.repository;

import br.com.vestris.vaccination.domain.model.AplicacaoVacina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioAplicacaoVacina extends JpaRepository<AplicacaoVacina, UUID> {
    List<AplicacaoVacina> findByPacienteIdOrderByDataAplicacaoDesc(UUID pacienteId);

    // --- QUERIES DO HUB (ADMINISTRATIVO) ---

    // 1. Vacinas Atrasadas (Global ou por Vet)
    // Busca onde a data da próxima dose já passou e ainda não foi dada baixa (logica simplificada por data)
    @Query("SELECT a FROM AplicacaoVacina a WHERE a.dataProximaDose < :hoje ORDER BY a.dataProximaDose ASC")
    List<AplicacaoVacina> listarAtrasadas(@Param("hoje") LocalDate hoje);

    // 2. Vacinas Previstas (Próximos dias)
    @Query("SELECT a FROM AplicacaoVacina a WHERE a.dataProximaDose BETWEEN :inicio AND :fim ORDER BY a.dataProximaDose ASC")
    List<AplicacaoVacina> listarPrevistas(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);

    // 3. Histórico Geral (Filtros dinâmicos seriam ideais com Specification, mas aqui vai o básico)
    @Query("SELECT a FROM AplicacaoVacina a WHERE a.dataAplicacao BETWEEN :inicio AND :fim ORDER BY a.dataAplicacao DESC")
    List<AplicacaoVacina> listarHistoricoGeral(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);
}
