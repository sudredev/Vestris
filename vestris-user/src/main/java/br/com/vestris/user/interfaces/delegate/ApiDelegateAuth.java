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
