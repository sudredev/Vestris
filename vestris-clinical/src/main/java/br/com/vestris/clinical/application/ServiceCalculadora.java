package br.com.vestris.clinical.application;

import br.com.vestris.clinical.domain.model.CalculoResultadoDTO;
import br.com.vestris.clinical.domain.model.Dosagem;
import br.com.vestris.clinical.domain.model.Protocolo;
import br.com.vestris.pharmacology.application.ServiceFarmacologia;
import br.com.vestris.pharmacology.domain.model.Medicamento;
import br.com.vestris.reference.application.ServiceReferencia;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ServiceCalculadora {
    private final ServiceProtocolo servicoProtocolo;
    private final ServiceFarmacologia servicoFarmacologia;
    private final ServiceReferencia servicoReferencia;

    public CalculoResultadoDTO calcular(UUID protocoloId, UUID medicamentoId, Double pesoInput, String unidadePeso) {
        // 1. Busca o Protocolo (Garante que existe e tem referência)
        java.util.List<String> alertas = new java.util.ArrayList<>();
        Protocolo protocolo = servicoProtocolo.buscarPorId(protocoloId);

        // 2. Busca a Regra de Dosagem ESPECÍFICA para aquele medicamento dentro do protocolo
        Dosagem regra = protocolo.getDosagens().stream()
                .filter(d -> d.getMedicamentoId().equals(medicamentoId))
                .findFirst()
                .orElseThrow(() -> new ExcecaoRegraNegocio("Este medicamento não pertence ao protocolo selecionado. O cálculo deve seguir um protocolo validado."));

        // 3. Busca dados externos (Medicamento e Referência)
        Medicamento medicamento = servicoFarmacologia.buscarEntidadePorId(medicamentoId);
        String citacaoReferencia = servicoReferencia.buscarCitacaoPorId(protocolo.getReferenciaId());

        // 4. Normaliza Peso para KG
        Double pesoKg = "G".equalsIgnoreCase(unidadePeso) ? pesoInput / 1000.0 : pesoInput;

        // 5. Cálculo da Dose (Massa em mg)
        // Fórmula: Dose = Peso * Faixa
        Double doseMinMg = regra.getDoseMinima() * pesoKg;
        Double doseMaxMg = (regra.getDoseMaxima() != null) ? regra.getDoseMaxima() * pesoKg : doseMinMg;

        // 6. Cálculo do Volume (Líquido em ml)
        Double volMinMl = null;
        Double volMaxMl = null;
        Double concentracaoValor = extrairValorConcentracao(medicamento.getConcentracao()); // Ex: extrai "50" de "50mg/ml"

        if (concentracaoValor != null && concentracaoValor > 0) {
            volMinMl = doseMinMg / concentracaoValor;
            volMaxMl = doseMaxMg / concentracaoValor;
        }

        // 7. Arredondamento seguro (2 casas decimais)
        doseMinMg = arredondar(doseMinMg);
        doseMaxMg = arredondar(doseMaxMg);
        if(volMinMl != null) volMinMl = arredondar(volMinMl);
        if(volMaxMl != null) volMaxMl = arredondar(volMaxMl);

        // 8. Montar DTO de Retorno (Classe interna ou use o Response do Swagger direto no Delegate)
        // Aqui retornarei um objeto simples para o Delegate converter
        return CalculoResultadoDTO.builder()
                .protocoloTitulo(protocolo.getTitulo())
                .medicamentoNome(medicamento.getNome())
                .referencia(citacaoReferencia)
                .pesoKg(pesoKg)
                .doseMinMg(doseMinMg)
                .doseMaxMg(doseMaxMg)
                .volMinMl(volMinMl)
                .volMaxMl(volMaxMl)
                .concentracao(medicamento.getConcentracao())
                .frequencia(regra.getFrequencia())
                .via(regra.getVia())
                .duracao(regra.getDuracao())
                .alertas(alertas) // Passando a lista de alertas
                .build();
    }

    // --- Helpers Matemáticos ---

    private Double arredondar(Double valor) {
        if (valor == null) return null;
        BigDecimal bd = BigDecimal.valueOf(valor);
        return bd.setScale(3, RoundingMode.HALF_UP).doubleValue(); // 3 casas para animais muito pequenos
    }

    // Tenta extrair o número de uma string como "50 mg/ml" ou "10%"
    private Double extrairValorConcentracao(String textoConcentracao) {
        if (textoConcentracao == null || textoConcentracao.isBlank()) return null;
        try {
            // Regex simples para pegar o primeiro número (ex: "50" de "50 mg")
            Matcher matcher = Pattern.compile("([0-9]+([.,][0-9]+)?)").matcher(textoConcentracao);
            if (matcher.find()) {
                String numeroStr = matcher.group(1).replace(",", ".");
                return Double.parseDouble(numeroStr);
            }
        } catch (Exception e) {
            return null; // Falhou ao converter, retorna nulo e o front mostra só mg
        }
        return null;
    }
}
