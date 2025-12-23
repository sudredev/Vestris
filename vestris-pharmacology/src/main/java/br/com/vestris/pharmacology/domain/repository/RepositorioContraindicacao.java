package br.com.vestris.pharmacology.domain.repository;

import br.com.vestris.pharmacology.domain.model.Contraindicacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioContraindicacao extends JpaRepository<Contraindicacao, UUID> {
    List<Contraindicacao> findByMedicamentoId(UUID medicamentoId);

    // Para verificar se já existe (evitar duplicatas para a mesma especie/medicamento)
    boolean existsByMedicamentoIdAndEspecieId(UUID medicamentoId, UUID especieId);
}
