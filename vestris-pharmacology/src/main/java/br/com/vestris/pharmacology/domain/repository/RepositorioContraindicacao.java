package br.com.vestris.pharmacology.domain.repository;

import br.com.vestris.pharmacology.domain.model.Contraindicacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioContraindicacao extends JpaRepository<Contraindicacao, UUID> {
    // CORREÇÃO: Busca por Princípio Ativo
    List<Contraindicacao> findByPrincipioAtivoId(UUID principioAtivoId);

    boolean existsByPrincipioAtivoIdAndEspecieId(UUID principioAtivoId, UUID especieId);

    // Para a validação de segurança
    @Query("SELECT c FROM Contraindicacao c WHERE c.principioAtivo.id = :principioId AND c.especieId = :especieId")
    List<Contraindicacao> encontrarRiscos(UUID principioId, UUID especieId);
}
