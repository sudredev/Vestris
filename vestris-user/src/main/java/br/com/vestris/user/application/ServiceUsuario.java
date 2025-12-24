package br.com.vestris.user.application;

import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.user.domain.model.Perfil;
import br.com.vestris.user.domain.model.Usuario;
import br.com.vestris.user.domain.repository.RepositorioUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceUsuario {

    private final RepositorioUsuario repositorio;

    public List<Usuario> listar(String perfilStr, Boolean apenasComCrmv) {
        // 1. Filtro por CRMV
        if (Boolean.TRUE.equals(apenasComCrmv)) {
            return repositorio.findAllComCrmv();
        }

        // 2. Filtro por Perfil
        if (perfilStr != null) {
            try {
                Perfil perfil = Perfil.valueOf(perfilStr.toUpperCase());
                return repositorio.findByPerfil(perfil);
            } catch (IllegalArgumentException e) {
                // Se mandar perfil errado, retorna vazio ou erro (decisão sua)
                return List.of();
            }
        }

        // 3. Sem filtros: retorna tudo
        return repositorio.findAll();
    }

    public boolean existePorId(UUID id) {
        return repositorio.existsById(id);
    }

    public Usuario buscarPorId(UUID id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Usuário", id.toString()));
    }

    public Usuario atualizar(UUID id, String novoNome, String novoCrmv) {
        Usuario usuario = buscarPorId(id);

        if (novoNome != null) usuario.setNome(novoNome);
        if (novoCrmv != null) usuario.setCrmv(novoCrmv);

        // Regra de negócio: Se ganhou CRMV, vira Veterinário automaticamente?
        if (novoCrmv != null && !novoCrmv.isBlank()) {
            usuario.setPerfil(Perfil.VETERINARIO);
        }

        return repositorio.save(usuario);
    }

    public void deletar(UUID id) {
        if (!repositorio.existsById(id)) {
            throw new ExceptionRecursoNaoEncontrado("Usuário", id.toString());
        }
        repositorio.deleteById(id);
    }
    
}
