package br.com.vestris.vaccination.domain.repository;

import br.com.vestris.vaccination.domain.model.ProtocoloVacinacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RepositorioProtocoloVacinacao extends JpaRepository<ProtocoloVacinacao, UUID> {
    List<ProtocoloVacinacao> findByEspecieId(UUID especieId);
    // Evitar cadastrar a mesma vacina duas vezes para a mesma espécie
    boolean existsByEspecieIdAndVacinaId(UUID especieId, UUID vacinaId);
}
