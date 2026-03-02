package br.com.vestris.feedbacks.domain.repository;

import br.com.vestris.feedbacks.domain.Sugestao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioSugestao extends JpaRepository<Sugestao, UUID> {
    List<Sugestao> findByTipo(Sugestao.TipoSugestao tipo);
    List<Sugestao> findByStatus(Sugestao.StatusSugestao status);
    List<Sugestao> findByTipoAndStatus(Sugestao.TipoSugestao tipo, Sugestao.StatusSugestao status);
}
