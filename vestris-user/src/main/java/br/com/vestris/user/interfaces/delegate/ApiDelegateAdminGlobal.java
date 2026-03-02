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
