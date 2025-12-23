package br.com.vestris.user.interfaces.delegate;

import br.com.vestris.user.application.ServiceAuth;
import br.com.vestris.user.domain.model.Usuario;
import br.com.vestris.user.interfaces.api.AutenticacaoApiDelegate;
import br.com.vestris.user.interfaces.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiDelegateAuth implements AutenticacaoApiDelegate {
    private final ServiceAuth servicoAuth;

    @Override
    public ResponseEntity<ApiResponseUsuario> registrarUsuario(RegistroRequest request) {
        Usuario salvo = servicoAuth.registrar(
                request.getNome(),
                request.getEmail(),
                request.getSenha(),
                request.getCrmv()
        );

        // Converter para Response
        UsuarioResponse dto = new UsuarioResponse();
        dto.setId(salvo.getId());
        dto.setNome(salvo.getNome());
        dto.setEmail(salvo.getEmail());
        dto.setPerfil(UsuarioResponse.PerfilEnum.valueOf(salvo.getPerfil().name()));

        ApiResponseUsuario response = new ApiResponseUsuario();
        response.setSucesso(true);
        response.setDados(dto);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseToken> login(LoginRequest request) {
        String tokenJwt = servicoAuth.login(request.getEmail(), request.getSenha());

        TokenResponse dto = new TokenResponse();
        dto.setToken(tokenJwt);
        dto.setTipo("Bearer");
        dto.setExpiraEm("24h");

        ApiResponseToken response = new ApiResponseToken();
        response.setSucesso(true);
        response.setDados(dto);

        return ResponseEntity.ok(response);
    }
}
