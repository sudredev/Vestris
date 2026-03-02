package br.com.vestris.saas.interfaces.api;

import br.com.vestris.saas.application.ServiceAssinatura;
import br.com.vestris.saas.interfaces.dto.WebhookMP;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public/webhooks")
@RequiredArgsConstructor
public class WebhookController {
    private final ServiceAssinatura serviceAssinatura;

    @PostMapping("/mercadopago")
    public ResponseEntity<Void> receberNotificacao(@RequestBody WebhookMP payload) {
        System.out.println("🔔 Webhook Recebido: " + payload.getAction() + " | ID: " + payload.getData().getId());

        // Lógica simplificada: Se for 'payment.created' ou 'subscription_preapproval', processamos
        if ("payment.created".equals(payload.getAction()) || "created".equals(payload.getAction())) {
            // Aqui você chamaria a API do Mercado Pago usando o ID recebido para saber quem pagou (email)
            // E atualizaria a assinatura no banco.
            // serviceAssinatura.processarPagamento(payload.getData().getId());
        }

        return ResponseEntity.ok().build(); // Sempre retornar 200 pro MP não ficar tentando de novo
    }
}
