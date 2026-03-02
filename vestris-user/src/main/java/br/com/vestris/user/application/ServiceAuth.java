package br.com.vestris.user.application;

import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.user.domain.model.Perfil;
import br.com.vestris.user.domain.model.Usuario;
import br.com.vestris.user.domain.repository.RepositorioUsuario;
import br.com.vestris.user.infrastructure.security.ServicoToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceAuth {
    private final RepositorioUsuario repositorio;
    private final PasswordEncoder passwordEncoder;
    private final ServicoToken servicoToken;

    public Usuario registrar(String nome, String email, String senhaAberta, String crmv) {
        if (repositorio.existsByEmail(email)) {
            throw new ExcecaoRegraNegocio("E-mail já cadastrado.");
        }
        Usuario novo = new Usuario();
        novo.setNome(nome);
        novo.setEmail(email);
        novo.setCrmv(crmv);
        // Default para cadastro comum
        novo.setPerfil(crmv != null && !crmv.isBlank() ? Perfil.VETERINARIO : Perfil.ESTUDANTE);
        novo.setScope(Usuario.UserScope.TENANT); // Padrão é Tenant
        novo.setSenha(passwordEncoder.encode(senhaAberta));

        return repositorio.save(novo);
    }

    public String login(String email, String senha) {
        Usuario usuario = repositorio.findByEmail(email)
                .orElseThrow(() -> new ExcecaoRegraNegocio("Usuário ou senha inválidos."));

        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new ExcecaoRegraNegocio("Usuário ou senha inválidos.");
        }
        return servicoToken.gerarToken(usuario);
    }

    // --- GOD MODE (IMPERSONATE) ---
    public String impersonate(UUID adminId, UUID targetUserId) {
        System.out.println("--- INICIANDO IMPERSONATE ---");
        System.out.println("Admin ID recebido: " + adminId);

        Usuario admin = repositorio.findById(adminId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Admin", adminId.toString()));

        System.out.println("Admin encontrado: " + admin.getEmail());
        System.out.println("Scope do Admin no Objeto: " + admin.getScope());

        // VALIDAÇÃO COM LOG
        if (admin.getScope() == null) {
            System.out.println("ERRO: Scope é NULL");
            throw new ExcecaoRegraNegocio("Seu usuário não possui escopo definido.");
        }

        if (admin.getScope() != Usuario.UserScope.GLOBAL) {
            System.out.println("ERRO: Scope não é GLOBAL. É: " + admin.getScope());
            throw new ExcecaoRegraNegocio("Acesso negado: Requer privilégio GLOBAL.");
        }

        Usuario alvo = repositorio.findById(targetUserId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Usuário alvo", targetUserId.toString()));

        System.out.println("Alvo encontrado: " + alvo.getEmail());

        return servicoToken.gerarToken(alvo);
    }
}
