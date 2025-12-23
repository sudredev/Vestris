package br.com.vestris.clinical.domain.repository;

import br.com.vestris.clinical.domain.model.Doenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioDoenca extends JpaRepository<Doenca, UUID> {
    // Busca todas as doenças de uma espécie específica
    List<Doenca> findAllByEspecieId(UUID especieId);

    // Evita cadastrar a mesma doença para a mesma espécie duas vezes
    boolean existsByNomeAndEspecieId(String nome, UUID especieId);
}
