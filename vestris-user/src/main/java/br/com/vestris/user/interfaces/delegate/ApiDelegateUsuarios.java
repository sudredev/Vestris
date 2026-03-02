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
