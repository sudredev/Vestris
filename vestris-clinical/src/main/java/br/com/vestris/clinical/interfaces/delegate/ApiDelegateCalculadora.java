package br.com.vestris.clinical.interfaces.delegate;

import br.com.vestris.clinical.application.ServiceCalculadora;
import br.com.vestris.clinical.domain.model.CalculoResultadoDTO;
import br.com.vestris.clinical.interfaces.api.CalculadoraApiDelegate;
import br.com.vestris.clinical.interfaces.dto.*;
import lombok.RequiredArgsConstructor;
import org.openapitools.jackson.nullable.JsonNullable;
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
                request.getUnidadePeso().name()
        );
        return montarResposta(resultado);
    }

    // 1. CÁLCULO LIVRE (SIMPLES)
    @Override
    public ResponseEntity<ApiResponseCalculo> calcularDoseLivre(CalculoLivreRequest request) {
        Double pesoKg = request.getPeso();
        if (CalculoLivreRequest.UnidadePesoEnum.G.equals(request.getUnidadePeso())) {
            pesoKg = pesoKg / 1000.0;
        }

        // Chama método específico no Service (vou mostrar abaixo)
        var resultado = serviceCalculadora.calcularMatematico(
                request.getNomeMedicamento(),
                request.getConcentracao(),
                pesoKg,
                request.getDoseInformada(),
                request.getVia(),
                request.getFrequencia(), // Wrap in JsonNullable as per previous patterns if service expects it, checking service signature next step if error. Wait, snippet passed request.getFrequencia() directly. Assuming serviceCalculadora.calcularMatematico takes String/Double.
                request.getDuracao()
        );

        return montarResposta(resultado);
    }

    // 2. VALIDAÇÃO CATÁLOGO (COM SEGURANÇA)
    @Override
    public ResponseEntity<ApiResponseCalculo> validarDoseCatalogo(CalculoCatalogoRequest request) {
        Double pesoKg = request.getPeso();
        if (CalculoCatalogoRequest.UnidadePesoEnum.G.equals(request.getUnidadePeso())) {
            pesoKg = pesoKg / 1000.0;
        }

        var resultado = serviceCalculadora.validarDose(
                request.getMedicamentoId(),
                request.getEspecieId(),
                request.getDoencaId(),
                request.getClinicaId(),
                request.getUsuarioId(),
                pesoKg,
                request.getDoseInformada(),
                "MG_KG",
                request.getVia(),
                null // Concentração manual é null aqui, pois pega do banco
        );

        return montarResposta(resultado);
    }

    // --- HELPERS SEGUROS ---

    private <T> T unwrap(JsonNullable<T> nullable) {
        return (nullable != null && nullable.isPresent()) ? nullable.get() : null;
    }

    private String unwrapString(JsonNullable<String> nullable) {
        return (nullable != null && nullable.isPresent()) ? nullable.get() : null;
    }

    private ResponseEntity<ApiResponseCalculo> montarResposta(CalculoResultadoDTO resultado) {
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

        // Mapeamento Seguro do Enum
        if (resultado.getStatusSeguranca() != null) {
            try {
                // Tenta converter string para Enum (Case sensitive pode ser problema)
                String statusStr = resultado.getStatusSeguranca().toUpperCase(); // Força upper
                response.setStatusSeguranca(StatusSegurancaEnum.fromValue(statusStr));
            } catch (Exception e) {
                System.err.println("Erro ao converter status: " + resultado.getStatusSeguranca());
                response.setStatusSeguranca(StatusSegurancaEnum.NAO_VALIDADO);
            }
        } else {
            response.setStatusSeguranca(StatusSegurancaEnum.SEGURO);
        }

        response.setMensagemSeguranca(resultado.getMensagemSeguranca());
        response.setRefMin(resultado.getRefMin());
        response.setRefMax(resultado.getRefMax());
        response.setRefFonte(resultado.getRefFonte());

        // Campos do cálculo matemático que podem vir no resultado
        response.setDoseCalculadaMg(resultado.getDoseCalculadaMg());
        response.setVolumeCalculadoMl(resultado.getVolumeCalculadoMl());

        ApiResponseCalculo wrapper = new ApiResponseCalculo();
        wrapper.setSucesso(true);
        wrapper.setDados(response);

        return ResponseEntity.ok(wrapper);
    }
}
