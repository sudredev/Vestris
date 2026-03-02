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
