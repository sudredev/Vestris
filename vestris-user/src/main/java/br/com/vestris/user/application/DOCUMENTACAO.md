## src\main\java\br\com\vestris\user\application

### ServiceAuditoria.java

```java
// src\main\java\br\com\vestris\user\application\ServiceAuditoria.java
package br.com.vestris.user.application;

import br.com.vestris.user.domain.model.Auditoria;
import br.com.vestris.user.domain.model.AcaoAuditoria;
import br.com.vestris.user.domain.model.EntidadeAuditoria;
import br.com.vestris.user.domain.model.Usuario;
import br.com.vestris.user.domain.repository.RepositorioAuditoria;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class ServiceAuditoria {
    private final RepositorioAuditoria repositorio;
    private final ObjectMapper objectMapper; // Para converter metadados em JSON
    private final ServiceUsuario serviceUsuario;

    /**
     * Registra um evento de auditoria.
     * Usa transação REQUIRES_NEW para garantir que o log seja salvo mesmo se a operação principal falhar (opcional, mas bom para logs de erro),
     * ou MANDATORY se quiser que faça parte da mesma transação. Vamos usar o padrão (REQUIRED) para simplicidade.
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void registrar(
            UUID clinicaId,
            UUID usuarioId,
            AcaoAuditoria acao,
            EntidadeAuditoria entidade,
            UUID idAlvo,
            String descricaoCurta,
            Map<String, Object> metadados
    )
    {
        if (usuarioId == null) {
            System.err.println("[AUDIT ERROR] Tentativa de log sem usuário.");
            return;
        }

        // Tenta recuperar ClinicaID se vier nulo (fallback)
        if (clinicaId == null) {
            try {
                Usuario u = serviceUsuario.buscarPorId(usuarioId);
                if (u.getClinica() != null) {
                    clinicaId = u.getClinica().getId();
                }
            } catch (Exception e) {
                // Ignora, vai salvar sem clinicaId (pode quebrar se for NOT NULL no banco, então cuidado)
            }
        }

        // Se ainda for nulo e o banco exige, aborta ou usa um ID de sistema
        if (clinicaId == null) {
            System.err.println("[AUDIT ERROR] Auditoria ignorada: Usuário " + usuarioId + " não tem clínica vinculada.");
            return;
        }

        try {
            Auditoria log = new Auditoria();
            log.setId(UUID.randomUUID());
            log.setClinicaId(clinicaId);
            log.setUsuarioId(usuarioId);
            log.setAcao(acao);
            log.setEntidade(entidade);
            log.setIdAlvo(idAlvo);
            log.setDescricaoCurta(descricaoCurta);
            log.setDataHora(LocalDateTime.now());

            if (metadados != null && !metadados.isEmpty()) {
                log.setMetadados(objectMapper.writeValueAsString(metadados));
            }

            repositorio.saveAndFlush(log);

        } catch (Exception e) {
            // Auditoria não deve quebrar o fluxo de negócio, apenas logar o erro no console
            e.printStackTrace();
        }
    }

    // Sobrecarga simples
    public void registrar(UUID clinicaId, UUID usuarioId, AcaoAuditoria acao, EntidadeAuditoria entidade, UUID idAlvo, String descricao) {
        registrar(clinicaId, usuarioId, acao, entidade, idAlvo, descricao, null);
    }
}

```

### ServiceAuth.java

```java
// src\main\java\br\com\vestris\user\application\ServiceAuth.java
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

```

### ServiceClinica.java

```java
// src\main\java\br\com\vestris\user\application\ServiceClinica.java
package br.com.vestris.user.application;

import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.user.domain.model.Clinica;
import br.com.vestris.user.domain.model.Perfil;
import br.com.vestris.user.domain.model.Usuario;
import br.com.vestris.user.domain.repository.RepositorioClinica;
import br.com.vestris.user.domain.repository.RepositorioUsuario;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceClinica {
    private final RepositorioClinica repoClinica;
    private final RepositorioUsuario repoUsuario;
    private final PasswordEncoder passwordEncoder;

    // Injeta a INTERFACE, não a classe concreta do outro módulo
    private final ValidadorPlanoService validadorPlano;

    public Clinica buscarPorUsuario(UUID usuarioId) {
        Usuario user = repoUsuario.findById(usuarioId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Usuário", usuarioId.toString()));

        return user.getClinica();
    }

    @Transactional
    public Clinica salvar(UUID usuarioId, Clinica dados) {
        Usuario user = repoUsuario.findById(usuarioId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Usuário", usuarioId.toString()));

        Clinica clinica = user.getClinica();

        if (clinica == null) {
            clinica = new Clinica();
        }

        clinica.setNomeFantasia(dados.getNomeFantasia());
        clinica.setRazaoSocial(dados.getRazaoSocial());
        clinica.setCnpj(dados.getCnpj());
        clinica.setEndereco(dados.getEndereco());
        clinica.setTelefone(dados.getTelefone());
        clinica.setEmailContato(dados.getEmailContato());
        clinica.setLogoBase64(dados.getLogoBase64());

        clinica = repoClinica.save(clinica);

        if (user.getClinica() == null) {
            user.setClinica(clinica);
            repoUsuario.save(user);
        }

        return clinica;
    }

    // --- GESTÃO DE EQUIPE ---

    public List<Usuario> listarMembros(UUID adminId) {
        Usuario admin = repoUsuario.findById(adminId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Admin", adminId.toString()));

        if (admin.getClinica() == null) return List.of();

        return repoUsuario.findByClinicaId(admin.getClinica().getId());
    }

    @Transactional
    public Usuario adicionarMembro(UUID adminId, String nome, String email, String senha, String crmv) {
        Usuario admin = repoUsuario.findById(adminId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Admin", adminId.toString()));

        if (admin.getClinica() == null) {
            throw new ExcecaoRegraNegocio("Você precisa salvar os dados da clínica antes de adicionar equipe.");
        }

        // --- GUARDIÃO VIA INTERFACE ---
        // O Spring vai procurar quem implementa essa interface (no caso, o módulo SaaS)
        validadorPlano.validarLimiteVeterinarios(admin.getClinica().getId());
        // ------------------------------

        if (repoUsuario.existsByEmail(email)) {
            throw new ExcecaoRegraNegocio("E-mail já cadastrado no sistema.");
        }

        Usuario novo = new Usuario();
        novo.setNome(nome);
        novo.setEmail(email);
        novo.setSenha(passwordEncoder.encode(senha));
        novo.setCrmv(crmv);
        novo.setPerfil(Perfil.VETERINARIO);
        novo.setScope(Usuario.UserScope.TENANT);
        novo.setClinica(admin.getClinica());

        return repoUsuario.save(novo);
    }

    // ... (método removerMembro mantido igual) ...
    @Transactional
    public void removerMembro(UUID adminId, UUID membroId) {
        if (adminId.equals(membroId)) {
            throw new ExcecaoRegraNegocio("Você não pode remover a si mesmo da equipe.");
        }
        Usuario admin = repoUsuario.findById(adminId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Admin", adminId.toString()));
        Usuario membro = repoUsuario.findById(membroId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Veterinário", membroId.toString()));

        if (admin.getClinica() == null) {
            throw new ExcecaoRegraNegocio("Admin não possui clínica vinculada.");
        }

        if (membro.getClinica() == null || !membro.getClinica().getId().equals(admin.getClinica().getId())) {
            throw new ExcecaoRegraNegocio("Este veterinário não pertence à sua equipe.");
        }

        membro.setClinica(null);
        repoUsuario.save(membro);
    }
}

```

### ServiceUsuario.java

```java
// src\main\java\br\com\vestris\user\application\ServiceUsuario.java
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

    public List<Usuario> listarPorClinica(UUID clinicaId) {
        return repositorio.findByClinicaId(clinicaId);
    }
}

```

### ValidadorPlanoService.java

```java
// src\main\java\br\com\vestris\user\application\ValidadorPlanoService.java
package br.com.vestris.user.application;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface ValidadorPlanoService {
    void validarLimiteVeterinarios(UUID clinicaId);
}

```

