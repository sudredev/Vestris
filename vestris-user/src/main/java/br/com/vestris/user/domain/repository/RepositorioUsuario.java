package br.com.vestris.user.domain.repository;

import br.com.vestris.user.domain.model.Perfil;
import br.com.vestris.user.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Usuario> findByPerfil(Perfil perfil);

    // Busca quem tem CRMV preenchido
    @Query("SELECT u FROM Usuario u WHERE u.crmv IS NOT NULL AND u.crmv <> ''")
    List<Usuario> findAllComCrmv();

    // Busca por Clínica (para listar equipe)
    List<Usuario> findByClinicaId(UUID clinicaId);

    // --- NOVO: CONTAGEM PARA VALIDAÇÃO DE PLANO ---
    long countByClinicaId(UUID clinicaId);;

}
