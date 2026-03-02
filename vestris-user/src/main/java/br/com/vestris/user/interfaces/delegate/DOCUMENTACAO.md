## src\main\java\br\com\vestris\user\interfaces\delegate

### ApiDelegateAdminGlobal.java

```java
// src\main\java\br\com\vestris\user\interfaces\delegate\ApiDelegateAdminGlobal.java
package br.com.vestris.user.interfaces.delegate;

import br.com.vestris.user.application.ServiceAuth;
import br.com.vestris.user.interfaces.api.AdminGlobalApiDelegate;
import br.com.vestris.user.interfaces.dto.ApiResponseToken;
import br.com.vestris.user.interfaces.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ApiDelegateAdminGlobal implements AdminGlobalApiDelegate {

    private final ServiceAuth servicoAuth;

    @Override
    public ResponseEntity<ApiResponseToken> impersonateUser(UUID adminId, UUID targetUserId) {
        System.out.println(">>> DELEGATE ADMIN ALCANÇADO! <<<"); // Log de prova de vida

        String token = servicoAuth.impersonate(adminId, targetUserId);

        TokenResponse dto = new TokenResponse();
        dto.setToken(token);
        dto.setTipo("Bearer");
        dto.setExpiraEm("Impersonated Session");

        ApiResponseToken response = new ApiResponseToken();
        response.setSucesso(true);
        response.setDados(dto);

        return ResponseEntity.ok(response);
    }
}

```

### ApiDelegateAuditoria.java

```java
// src\main\java\br\com\vestris\user\interfaces\delegate\ApiDelegateAuditoria.java
package br.com.vestris.user.interfaces.delegate;

import br.com.vestris.user.application.ServiceAuditoria;
import br.com.vestris.user.application.ServiceUsuario;
import br.com.vestris.user.domain.model.AcaoAuditoria;
import br.com.vestris.user.domain.model.Auditoria;
import br.com.vestris.user.domain.model.EntidadeAuditoria;
import br.com.vestris.user.domain.model.Usuario;
import br.com.vestris.user.domain.repository.RepositorioAuditoria;
import br.com.vestris.user.interfaces.api.AuditoriaApiDelegate;
import br.com.vestris.user.interfaces.dto.ApiResponseListaAuditoria;
import br.com.vestris.user.interfaces.dto.ApiResponseSucesso;
import br.com.vestris.user.interfaces.dto.AuditoriaResponse;
import br.com.vestris.user.interfaces.dto.EventoAuditoriaRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateAuditoria implements AuditoriaApiDelegate {
    private final RepositorioAuditoria repositorio;
    private final ServiceAuditoria serviceAuditoria;
    private final ServiceUsuario serviceUsuario;
    private final HttpServletRequest httpRequest;
    private final ObjectMapper objectMapper;

    @Override
    public ResponseEntity<ApiResponseListaAuditoria> listarLogsAuditoria(UUID clinicaId, LocalDate dataInicio, LocalDate dataFim) {

        List<Auditoria> logs;

        // ESTRATÉGIA: Se o usuário filtrar data, usamos o BETWEEN.
        // Se não filtrar, usamos o método simples.

        if (dataInicio != null || dataFim != null) {
            // Se início for nulo, pega desde o começo dos tempos
            LocalDateTime inicio = (dataInicio != null) ? dataInicio.atStartOfDay() : LocalDateTime.of(1970, 1, 1, 0, 0);

            // Se fim for nulo, pega até o final dos tempos (hoje + folga)
            LocalDateTime fim = (dataFim != null) ? dataFim.atTime(LocalTime.MAX) : LocalDateTime.now().plusDays(1);

            logs = repositorio.findByClinicaIdAndDataHoraBetweenOrderByDataHoraDesc(clinicaId, inicio, fim);
        } else {
            // Sem filtros: traz tudo
            logs = repositorio.findByClinicaIdOrderByDataHoraDesc(clinicaId);
        }

        List<AuditoriaResponse> listaDTO = logs.stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaAuditoria response = new ApiResponseListaAuditoria();
        response.setSucesso(true);
        response.setDados(listaDTO);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseSucesso> registrarEventoAuditoria(EventoAuditoriaRequest request) {
        try {
            UUID usuarioId = extrairUsuarioIdDoToken();

            if (usuarioId == null) {
                ApiResponseSucesso erro = new ApiResponseSucesso();
                erro.setSucesso(false);
                erro.setMensagem("Token inválido ou expirado.");
                return ResponseEntity.badRequest().body(erro);
            }

            Usuario usuario = serviceUsuario.buscarPorId(usuarioId);
            if (usuario.getClinica() == null) {
                ApiResponseSucesso r = new ApiResponseSucesso();
                r.setSucesso(true);
                r.setMensagem("Usuário sem clínica.");
                return ResponseEntity.ok(r);
            }

            AcaoAuditoria acao = AcaoAuditoria.fromString(request.getAcao().toString());
            EntidadeAuditoria entidade = EntidadeAuditoria.PDF;
            try {
                entidade = EntidadeAuditoria.valueOf(request.getEntidade());
            } catch (Exception e) {}

            serviceAuditoria.registrar(
                    usuario.getClinica().getId(),
                    usuario.getId(),
                    acao,
                    entidade,
                    request.getIdAlvo(),
                    request.getDescricao()
            );

            ApiResponseSucesso response = new ApiResponseSucesso();
            response.setSucesso(true);
            response.setMensagem("Evento registrado.");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            ApiResponseSucesso erro = new ApiResponseSucesso();
            erro.setSucesso(false);
            erro.setMensagem("Erro interno: " + e.getMessage());
            return ResponseEntity.internalServerError().body(erro);
        }
    }

    private UUID extrairUsuarioIdDoToken() {
        try {
            String header = httpRequest.getHeader("Authorization");
            if (header == null || !header.startsWith("Bearer ")) return null;
            String token = header.substring(7);
            String[] parts = token.split("\\.");
            if (parts.length < 2) return null;
            String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]));
            JsonNode node = objectMapper.readTree(payloadJson);
            if (node.has("sub")) return UUID.fromString(node.get("sub").asText());
        } catch (Exception e) {}
        return null;
    }

    private AuditoriaResponse converter(Auditoria log) {
        AuditoriaResponse dto = new AuditoriaResponse();
        dto.setId(log.getId());
        dto.setClinicaId(log.getClinicaId());
        dto.setUsuarioId(log.getUsuarioId());
        dto.setAcao(log.getAcao() != null ? log.getAcao().name() : "DESCONHECIDO");
        dto.setEntidade(log.getEntidade() != null ? log.getEntidade().name() : "DESCONHECIDO");
        dto.setIdAlvo(log.getIdAlvo());
        dto.setDetalhes(log.getDescricaoCurta());
        dto.setMetadados(log.getMetadados());
        if (log.getDataHora() != null) {
            // Enviamos o LocalDateTime com Offset UTC para o front ajustar
            dto.setCriadoEm(log.getDataHora().atOffset(ZoneOffset.UTC));
        }
        return dto;
    }
}

```

### ApiDelegateAuth.java

```java
// src\main\java\br\com\vestris\user\interfaces\delegate\ApiDelegateAuth.java
package br.com.vestris.user.interfaces.delegate;

import br.com.vestris.user.application.ServiceAuth;
import br.com.vestris.user.domain.model.Usuario;
import br.com.vestris.user.interfaces.api.AdminGlobalApiDelegate;
import br.com.vestris.user.interfaces.api.AutenticacaoApiDelegate;
import br.com.vestris.user.interfaces.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ApiDelegateAuth implements AutenticacaoApiDelegate{

    private final ServiceAuth servicoAuth;

    @Override
    public ResponseEntity<ApiResponseUsuario> registrarUsuario(RegistroRequest request) {
        Usuario salvo = servicoAuth.registrar(
                request.getNome(),
                request.getEmail(),
                request.getSenha(),
                request.getCrmv()
        );
        return ResponseEntity.ok(createResponse(salvo));
    }

    @Override
    public ResponseEntity<ApiResponseToken> login(LoginRequest request) {
        String tokenJwt = servicoAuth.login(request.getEmail(), request.getSenha());
        return ResponseEntity.ok(createTokenResponse(tokenJwt));
    }

    // Helpers
    private ApiResponseUsuario createResponse(Usuario u) {
        UsuarioResponse dto = new UsuarioResponse();
        dto.setId(u.getId());
        dto.setNome(u.getNome());
        dto.setEmail(u.getEmail());

        // CORREÇÃO: Usando o Enum externo gerado
        if (u.getPerfil() != null) {
            dto.setPerfil(UsuarioResponsePerfilEnum.valueOf(u.getPerfil().name()));
        }

        if (u.getScope() != null) {
            dto.setScope(UsuarioResponseScopeEnum.valueOf(u.getScope().name()));
        }

        if (u.getCrmv() != null) {
            dto.setCrmv(u.getCrmv());
        }

        ApiResponseUsuario response = new ApiResponseUsuario();
        response.setSucesso(true);
        response.setDados(dto);
        return response;
    }

    private ApiResponseToken createTokenResponse(String token) {
        TokenResponse dto = new TokenResponse();
        dto.setToken(token);
        dto.setTipo("Bearer");
        dto.setExpiraEm("24h");

        ApiResponseToken response = new ApiResponseToken();
        response.setSucesso(true);
        response.setDados(dto);
        return response;
    }
}

```

### ApiDelegateClinica.java

```java
// src\main\java\br\com\vestris\user\interfaces\delegate\ApiDelegateClinica.java
package br.com.vestris.user.interfaces.delegate;

import br.com.vestris.user.application.ServiceClinica;
import br.com.vestris.user.domain.model.Clinica;
import br.com.vestris.user.domain.model.Usuario;
import br.com.vestris.user.interfaces.api.ClinicaApiDelegate;
import br.com.vestris.user.interfaces.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateClinica implements ClinicaApiDelegate {
    private final ServiceClinica servico;

    @Override
    public ResponseEntity<ApiResponseClinica> obterMinhaClinica(UUID usuarioId) {
        Clinica c = servico.buscarPorUsuario(usuarioId);
        if (c == null) {
            ApiResponseClinica r = new ApiResponseClinica();
            r.setSucesso(true);
            return ResponseEntity.ok(r);
        }
        return ResponseEntity.ok(criarResponse(c));
    }

    @Override
    public ResponseEntity<ApiResponseClinica> salvarMinhaClinica(UUID usuarioId, ClinicaRequest request) {
        Clinica entidade = new Clinica();
        entidade.setNomeFantasia(request.getNomeFantasia());
        entidade.setRazaoSocial(request.getRazaoSocial());
        entidade.setCnpj(request.getCnpj());
        entidade.setEndereco(request.getEndereco());
        entidade.setTelefone(request.getTelefone());
        entidade.setEmailContato(request.getEmailContato());
        entidade.setLogoBase64(request.getLogoBase64());

        Clinica salva = servico.salvar(usuarioId, entidade);
        return ResponseEntity.ok(criarResponse(salva));
    }

    private ApiResponseClinica criarResponse(Clinica c) {
        ClinicaResponse dto = new ClinicaResponse();
        dto.setId(c.getId());
        dto.setNomeFantasia(c.getNomeFantasia());
        dto.setRazaoSocial(c.getRazaoSocial());
        dto.setCnpj(c.getCnpj());
        dto.setEndereco(c.getEndereco());
        dto.setTelefone(c.getTelefone());
        dto.setEmailContato(c.getEmailContato());
        dto.setLogoBase64(c.getLogoBase64());

        ApiResponseClinica r = new ApiResponseClinica();
        r.setSucesso(true);
        r.setDados(dto);
        return r;
    }

    @Override
    public ResponseEntity<ApiResponseListaUsuario> listarEquipe(UUID usuarioId) {
        List<UsuarioResponse> lista = servico.listarMembros(usuarioId).stream()
                .map(this::converterUsuario)
                .collect(Collectors.toList());

        ApiResponseListaUsuario r = new ApiResponseListaUsuario();
        r.setSucesso(true);
        r.setDados(lista);
        return ResponseEntity.ok(r);
    }

    @Override
    public ResponseEntity<ApiResponseUsuario> adicionarMembroEquipe(UUID usuarioId, NovoMembroRequest request) {
        Usuario novo = servico.adicionarMembro(
                usuarioId,
                request.getNome(),
                request.getEmail(),
                request.getSenha(),
                request.getCrmv()
        );

        ApiResponseUsuario r = new ApiResponseUsuario();
        r.setSucesso(true);
        r.setDados(converterUsuario(novo));
        return ResponseEntity.status(201).body(r);
    }

    // Helper
    private UsuarioResponse converterUsuario(Usuario u) {
        UsuarioResponse dto = new UsuarioResponse();
        dto.setId(u.getId());
        dto.setNome(u.getNome());
        dto.setEmail(u.getEmail());
        dto.setCrmv(u.getCrmv());

        // CORREÇÃO: Usando o Enum externo gerado
        if (u.getPerfil() != null) {
            dto.setPerfil(UsuarioResponsePerfilEnum.valueOf(u.getPerfil().name()));
        }
        // Scope não é obrigatório mostrar na listagem de equipe, mas se quiser:
        if (u.getScope() != null) {
            dto.setScope(UsuarioResponseScopeEnum.valueOf(u.getScope().name()));
        }

        return dto;
    }

    @Override
    public ResponseEntity<Void> removerMembroEquipe(UUID usuarioId, UUID membroId) {
        servico.removerMembro(usuarioId, membroId);
        return ResponseEntity.noContent().build();
    }
}

```

### ApiDelegateUsuarios.java

```java
// src\main\java\br\com\vestris\user\interfaces\delegate\ApiDelegateUsuarios.java
package br.com.vestris.user.interfaces.delegate;

import br.com.vestris.user.application.ServiceUsuario;
import br.com.vestris.user.domain.model.Usuario;
import br.com.vestris.user.interfaces.api.GestaoUsuariosApiDelegate;
import br.com.vestris.user.interfaces.dto.*;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateUsuarios implements GestaoUsuariosApiDelegate {
    private final ServiceUsuario servico;

    @Override
    public ResponseEntity<ApiResponseListaUsuario> listarUsuarios(String perfil, Boolean apenasComCrmv) {
        // O ServiceUsuario.listar espera uma String ou um Enum interno do domínio.
        // Vamos passar as strings e deixar o service resolver ou converter aqui se necessário.

        List<UsuarioResponse> lista = servico.listar(perfil, apenasComCrmv).stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaUsuario response = new ApiResponseListaUsuario();
        response.setSucesso(true);
        response.setDados(lista);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseUsuario> buscarUsuarioPorId(UUID id) {
        return ResponseEntity.ok(criarResponse(servico.buscarPorId(id)));
    }

    @Override
    public ResponseEntity<ApiResponseUsuario> atualizarUsuario(UUID id, AtualizacaoUsuarioRequest request) {
        Usuario atualizado = servico.atualizar(id, request.getNome(), request.getCrmv());
        return ResponseEntity.ok(criarResponse(atualizado));
    }

    @Override
    public ResponseEntity<Void> deletarUsuario(UUID id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // --- Helpers ---
    private ApiResponseUsuario criarResponse(Usuario u) {
        ApiResponseUsuario r = new ApiResponseUsuario();
        r.setSucesso(true);
        r.setDados(converter(u));
        return r;
    }

    private UsuarioResponse converter(Usuario u) {
        UsuarioResponse dto = new UsuarioResponse();
        dto.setId(u.getId());
        dto.setNome(u.getNome());
        dto.setEmail(u.getEmail());
        dto.setCrmv(u.getCrmv());

        // Conversão segura dos Enums do Domínio para os Enums do DTO
        if (u.getPerfil() != null) {
            try {
                // Tenta converter o nome do enum do domínio (ex: ADMIN_GLOBAL) para o DTO
                dto.setPerfil(UsuarioResponsePerfilEnum.valueOf(u.getPerfil().name()));
            } catch (Exception e) {
                // Fallback seguro caso o banco tenha algo estranho
                dto.setPerfil(UsuarioResponsePerfilEnum.VETERINARIO);
            }
        }

        if (u.getScope() != null) {
            try {
                dto.setScope(UsuarioResponseScopeEnum.valueOf(u.getScope().name()));
            } catch (Exception e) {
                dto.setScope(UsuarioResponseScopeEnum.TENANT);
            }
        }

        return dto;
    }
}

```

