package br.com.vestris.saas.interfaces.delegate;

import br.com.vestris.saas.application.ServiceAssinatura;
import br.com.vestris.saas.domain.model.Assinatura;
import br.com.vestris.saas.interfaces.api.AssinaturasApiDelegate;
import br.com.vestris.saas.interfaces.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ApiDelegateAssinaturas implements AssinaturasApiDelegate {

    private final ServiceAssinatura servico;

    @Override
    public ResponseEntity<ApiResponseAssinatura> obterMinhaAssinatura(UUID clinicaId) {
        Assinatura assinatura = servico.buscarPorClinica(clinicaId);

        ApiResponseAssinatura response = new ApiResponseAssinatura();

        if (assinatura == null) {
            // Retorna sucesso mas sem dados (Clínica sem plano ainda)
            response.setSucesso(true);
            response.setDados(null);
        } else {
            response.setSucesso(true);
            response.setDados(converter(assinatura));
        }

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseAssinatura> assinarPlano(UUID clinicaId, AssinarPlanoRequest request) {
        Assinatura nova = servico.assinarPlano(clinicaId, request.getPlanoId(), request.getCiclo().name());

        ApiResponseAssinatura response = new ApiResponseAssinatura();
        response.setSucesso(true);
        response.setDados(converter(nova));

        return ResponseEntity.ok(response);
    }

    private AssinaturaResponse converter(Assinatura a) {
        AssinaturaResponse dto = new AssinaturaResponse();
        dto.setId(a.getId());
        dto.setClinicaId(a.getClinica().getId());

        // Mapeia o Plano simplificado dentro da resposta
        PlanoResponse planoDto = new PlanoResponse();
        planoDto.setId(a.getPlano().getId());
        planoDto.setNome(a.getPlano().getNome());
        planoDto.setMaxVeterinarios(a.getPlano().getMaxVeterinarios());
        dto.setPlano(planoDto);

        dto.setStatus(StatusAssinaturaEnum.valueOf(a.getStatus().name()));
        dto.setTipoFaturamento(TipoFaturamentoEnum.valueOf(a.getTipoFaturamento().name()));

        if(a.getDataInicio() != null) dto.setDataInicio(a.getDataInicio().atOffset(ZoneOffset.UTC));
        if(a.getDataFim() != null) dto.setDataFim(a.getDataFim().atOffset(ZoneOffset.UTC));
        if(a.getDataTrialFim() != null) dto.setDataTrialFim(a.getDataTrialFim().atOffset(ZoneOffset.UTC));

        return dto;
    }
}
