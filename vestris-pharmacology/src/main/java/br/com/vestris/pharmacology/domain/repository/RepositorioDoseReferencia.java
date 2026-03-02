package br.com.vestris.pharmacology.domain.repository;

import br.com.vestris.pharmacology.domain.model.DoseReferencia;
import br.com.vestris.pharmacology.domain.model.enums.ViaAdministracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioDoseReferencia extends JpaRepository<DoseReferencia, UUID> {
    List<DoseReferencia> findByMedicamentoId(UUID medicamentoId);

    // --- NOVO MÉTODO ---
    // Busca vias cadastradas para o medicamento, filtrando pela espécie ou referências genéricas (null)
    @Query("SELECT DISTINCT d.via FROM DoseReferencia d WHERE d.medicamentoId = :medId AND (d.especieId = :espId OR d.especieId IS NULL)")
    List<ViaAdministracao> listarViasDisponiveis(@Param("medId") UUID medId, @Param("espId") UUID espId);
}
