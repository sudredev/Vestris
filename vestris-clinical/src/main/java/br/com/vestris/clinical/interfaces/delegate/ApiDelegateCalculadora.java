package br.com.vestris.clinical.interfaces.delegate;

import br.com.vestris.clinical.application.ServiceCalculadora;
import br.com.vestris.clinical.interfaces.api.CalculadoraApiDelegate;
import br.com.vestris.clinical.interfaces.dto.ApiResponseCalculo;
import br.com.vestris.clinical.interfaces.dto.CalculoResponse;
import br.com.vestris.clinical.interfaces.dto.CalculoSeguroRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiDelegateCalculadora implements CalculadoraApiDelegate {

    private final ServiceCalculadora serviceCalculadora;

    @Override
    public ResponseEntity<ApiResponseCalculo> calcularDosagemSegura(CalculoSeguroRequest request) {

        var resultado = serviceCalculadora.calcular(
                request.getProtocoloId(),
                request.getMedicamentoId(),
                request.getPeso(),
                request.getUnidadePeso().name() // Perfeito, converte o Enum para String
        );

        CalculoResponse response = new CalculoResponse();
        response.setProtocoloTitulo(resultado.getProtocoloTitulo());
        response.setMedicamentoNome(resultado.getMedicamentoNome());
        response.setReferenciaBibliografica(resultado.getReferencia());

        response.setPesoConsideradoKg(resultado.getPesoKg());
        response.setDoseMinimaMg(resultado.getDoseMinMg());
        response.setDoseMaximaMg(resultado.getDoseMaxMg());

        response.setVolumeMinimoMl(resultado.getVolMinMl());
        response.setVolumeMaximoMl(resultado.getVolMaxMl());

        response.setConcentracaoUtilizada(resultado.getConcentracao());
        response.setFrequencia(resultado.getFrequencia());
        response.setVia(resultado.getVia());
        response.setDuracao(resultado.getDuracao());

        // --- ADICIONE ESTA LINHA AQUI ---
        response.setAlertas(resultado.getAlertas());
        // --------------------------------

        ApiResponseCalculo wrapper = new ApiResponseCalculo();
        wrapper.setSucesso(true);
        wrapper.setDados(response);

        return ResponseEntity.ok(wrapper);
    }
}
