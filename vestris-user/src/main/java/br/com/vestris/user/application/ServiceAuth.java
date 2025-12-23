package br.com.vestris.user.application;

import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.user.domain.model.Perfil;
import br.com.vestris.user.domain.model.Usuario;
import br.com.vestris.user.domain.repository.RepositorioUsuario;
import br.com.vestris.user.infrastructure.security.ServicoToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceAuth {
    private final RepositorioUsuario repositorio;
    private final PasswordEncoder passwordEncoder; // Criptografia
    private final ServicoToken servicoToken;       // JWT

    public Usuario registrar(String nome, String email, String senhaAberta, String crmv) {
        // 1. Validar duplicidade
        if (repositorio.existsByEmail(email)) {
            throw new ExcecaoRegraNegocio("E-mail já cadastrado.");
        }

        // 2. Criar usuário
        Usuario novo = new Usuario();
        novo.setNome(nome);
        novo.setEmail(email);
        novo.setCrmv(crmv);
        // 3. Definir perfil (Regra simples: Tem CRMV? É Veterinário. Senão, Estudante)
        novo.setPerfil(crmv != null && !crmv.isBlank() ? Perfil.VETERINARIO : Perfil.ESTUDANTE);

        // 4. CRIPTOGRAFAR A SENHA ANTES DE SALVAR
        novo.setSenha(passwordEncoder.encode(senhaAberta));

        return repositorio.save(novo);
    }

    public String login(String email, String senha) {
        // 1. Buscar usuário
        Usuario usuario = repositorio.findByEmail(email)
                .orElseThrow(() -> new ExcecaoRegraNegocio("Usuário ou senha inválidos."));

        // 2. Verificar senha (Compara a senha aberta com o Hash do banco)
        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new ExcecaoRegraNegocio("Usuário ou senha inválidos.");
        }

        // 3. Gerar e retornar Token
        return servicoToken.gerarToken(usuario);
    }
}
