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
