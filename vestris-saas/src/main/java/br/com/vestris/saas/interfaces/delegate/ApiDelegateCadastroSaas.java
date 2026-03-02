package br.com.vestris.saas.interfaces.delegate;

import br.com.vestris.saas.application.ServiceCadastroSaas;
import br.com.vestris.saas.interfaces.api.PublicoApiDelegate;
import br.com.vestris.saas.interfaces.dto.ApiResponseToken;
import br.com.vestris.saas.interfaces.dto.CadastroSaasRequest;
import br.com.vestris.saas.interfaces.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiDelegateCadastroSaas implements PublicoApiDelegate {

    private final ServiceCadastroSaas serviceCadastro;

    @Override
    public ResponseEntity<ApiResponseToken> cadastrarClienteSaas(CadastroSaasRequest request) {

        // 1. Chama o serviço "Combo" que cria tudo e retorna o JWT
        String tokenJwt = serviceCadastro.registrarCliente(
                request.getNomeUsuario(),
                request.getEmail(),
                request.getSenha(),
                request.getCrmv(),
                request.getNomeClinica(),
                request.getPlanoId()
        );

        // 2. Monta o DTO de Token (específico do módulo SaaS)
        TokenResponse tokenDto = new TokenResponse();
        tokenDto.setToken(tokenJwt);
        tokenDto.setTipo("Bearer");
        tokenDto.setExpiraEm("24h"); // Ou pegar da config se preferir

        // 3. Monta o Wrapper de Resposta
        ApiResponseToken response = new ApiResponseToken();
        response.setSucesso(true);
        response.setMensagem("Conta criada e assinatura ativada com sucesso.");
        response.setDados(tokenDto);

        // 4. Retorna 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
