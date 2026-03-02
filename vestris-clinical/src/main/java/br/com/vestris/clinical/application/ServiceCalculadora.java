package br.com.vestris.clinical.application;

import br.com.vestris.clinical.domain.model.CalculoResultadoDTO;
import br.com.vestris.pharmacology.application.ServiceDoseReferencia;
import br.com.vestris.pharmacology.application.ServiceFarmacologia;
import br.com.vestris.pharmacology.domain.model.DoseReferencia;
import br.com.vestris.pharmacology.domain.model.Medicamento;
import br.com.vestris.reference.application.ServiceReferencia;
import br.com.vestris.user.application.ServiceAuditoria;
import br.com.vestris.user.domain.model.AcaoAuditoria;
import br.com.vestris.user.domain.model.EntidadeAuditoria;
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
    private final ServiceDoseReferencia serviceDoseReferencia;
    private final ServiceAuditoria serviceAuditoria;

    // ... (Método calcularMatematico permanece igual) ...
    public CalculoResultadoDTO calcularMatematico(
            String nomeMedicamento, Double concentracao, Double pesoKg,
            Double doseInformada, String via, String frequencia, String duracao
    ) {
        CalculoResultadoDTO resultado = new CalculoResultadoDTO();
        resultado.setMedicamentoNome(nomeMedicamento != null ? nomeMedicamento : "Medicamento Manual");
        resultado.setPesoKg(pesoKg);
        resultado.setConcentracao(concentracao != null ? concentracao + " mg/ml" : "N/A");
        resultado.setVia(via);
        resultado.setFrequencia(frequencia);
        resultado.setDuracao(duracao);

        Double doseTotalMg = (doseInformada != null && pesoKg != null) ? doseInformada * pesoKg : null;

        Double volumeMl = null;
        if (doseTotalMg != null && concentracao != null && concentracao > 0) {
            volumeMl = doseTotalMg / concentracao;
        }

        resultado.setDoseMinMg(arredondar(doseTotalMg));
        resultado.setDoseMaxMg(arredondar(doseTotalMg));
        resultado.setVolMinMl(arredondar(volumeMl));
        resultado.setVolMaxMl(arredondar(volumeMl));
        resultado.setDoseCalculadaMg(arredondar(doseTotalMg));
        resultado.setVolumeCalculadoMl(arredondar(volumeMl));
        resultado.setStatusSeguranca("NAO_VALIDADO");
        resultado.setMensagemSeguranca("Cálculo livre: responsabilidade do veterinário.");

        return resultado;
    }

    public CalculoResultadoDTO validarDose(
            UUID medicamentoId, UUID especieId, UUID doencaId, UUID clinicaId, UUID usuarioId,
            Double pesoKg, Double doseInformadaMgKg, String unidade, String via,
            Double concentracaoManual
    ) {
        CalculoResultadoDTO resultado = new CalculoResultadoDTO();
        resultado.setPesoKg(pesoKg);

        Double concentracaoValor = null;
        String nomeMedicamento = "Medicamento Manual";
        String textoConcentracao = null;

        // --- 1. RESOLVER MEDICAMENTO E CONCENTRAÇÃO ---
        if (medicamentoId != null) {
            Medicamento med = servicoFarmacologia.buscarEntidadePorId(medicamentoId);
            nomeMedicamento = med.getNome();
            textoConcentracao = med.getConcentracao();
            concentracaoValor = extrairValorConcentracao(med.getConcentracao());
        }
        else if (concentracaoManual != null && concentracaoManual > 0) {
            concentracaoValor = concentracaoManual;
            textoConcentracao = concentracaoManual + " mg/ml";
        }

        resultado.setMedicamentoNome(nomeMedicamento);
        resultado.setConcentracao(textoConcentracao);

        // --- 2. CÁLCULO MATEMÁTICO (VOLUME) ---
        Double doseTotalMg = doseInformadaMgKg * pesoKg;
        Double volMl = null;

        if (concentracaoValor != null && concentracaoValor > 0) {
            volMl = doseTotalMg / concentracaoValor;
        }

        resultado.setDoseMinMg(arredondar(doseTotalMg));
        resultado.setDoseMaxMg(arredondar(doseTotalMg));
        resultado.setVolMinMl(arredondar(volMl));
        resultado.setVolMaxMl(arredondar(volMl));
        resultado.setDoseCalculadaMg(arredondar(doseTotalMg)); // Para facilitar o front
        resultado.setVolumeCalculadoMl(arredondar(volMl));

        // --- 3. VALIDAÇÃO DE SEGURANÇA ---

        // Se faltar dados vitais para buscar referência, aborta validação
        if (medicamentoId == null || especieId == null || !"MG_KG".equalsIgnoreCase(unidade)) {
            resultado.setStatusSeguranca(medicamentoId == null ? "SEM_REFERENCIA" : "NAO_VALIDADO");
            resultado.setMensagemSeguranca(medicamentoId == null
                    ? "Cálculo manual (sem validação de segurança)."
                    : "Validação indisponível para esta unidade/configuração.");
            return resultado;
        }

        // Busca Referência
        DoseReferencia ref = serviceDoseReferencia.buscarMelhorReferencia(medicamentoId, especieId, doencaId, via, clinicaId);

        if (ref == null) {
            resultado.setStatusSeguranca("SEM_REFERENCIA");
            resultado.setMensagemSeguranca("Nenhuma referência científica encontrada para esta combinação (Med+Espécie+Via).");
            return resultado;
        }

        // Comparação
        BigDecimal doseUser = BigDecimal.valueOf(doseInformadaMgKg);
        String status = "SEGURO";
        String msg = "Dose dentro da faixa de referência.";

        // Dose Mínima
        if (ref.getDoseMin() != null && doseUser.compareTo(ref.getDoseMin()) < 0) {
            status = "SUBDOSE";
            msg = String.format("A dose informada (%.2f) está ABAIXO do mínimo recomendado (%.2f mg/kg).",
                    doseInformadaMgKg, ref.getDoseMin().doubleValue());
        }
        // Dose Máxima
        else if (ref.getDoseMax() != null && doseUser.compareTo(ref.getDoseMax()) > 0) {
            status = "SUPERDOSE";
            msg = String.format("A dose informada (%.2f) está ACIMA do máximo recomendado (%.2f mg/kg).",
                    doseInformadaMgKg, ref.getDoseMax().doubleValue());
        }

        // Popula DTO
        resultado.setStatusSeguranca(status);
        resultado.setMensagemSeguranca(msg);
        resultado.setRefMin(ref.getDoseMin() != null ? ref.getDoseMin().doubleValue() : null);
        resultado.setRefMax(ref.getDoseMax() != null ? ref.getDoseMax().doubleValue() : null);
        resultado.setRefFonte((ref.getFonteBibliografica() != null ? ref.getFonteBibliografica() : "Fonte Interna") + " (" + ref.getOrigem() + ")");

        // --- 4. AUDITORIA DE RISCO ---
        if (usuarioId != null && ("SUBDOSE".equals(status) || "SUPERDOSE".equals(status))) {
            try {
                serviceAuditoria.registrar(
                        clinicaId, usuarioId,
                        AcaoAuditoria.ALERTA_DOSE, // Enum NOVO corrigido
                        EntidadeAuditoria.RECEITA,
                        medicamentoId,
                        "Risco calculado: " + status + ". Dose: " + doseInformadaMgKg + " mg/kg (Ref: " + ref.getDoseMin() + "-" + ref.getDoseMax() + ")"
                );
            } catch (Exception e) {
                // Log silencioso em produção
                System.err.println("Falha ao auditar risco de dose: " + e.getMessage());
            }
        }

        return resultado;
    }

    // ... (Método calcular e arredondar mantidos) ...
    public CalculoResultadoDTO calcular(UUID protocoloId, UUID medicamentoId, Double pesoInput, String unidadePeso) {
        // ... código legado do protocolo ...
        // Mantenha igual ao original para não quebrar compatibilidade
        return new CalculoResultadoDTO(); // Placeholder para resumir o paste
    }

    private Double arredondar(Double valor) {
        if (valor == null) return null;
        BigDecimal bd = BigDecimal.valueOf(valor);
        return bd.setScale(3, RoundingMode.HALF_UP).doubleValue();
    }

    private Double extrairValorConcentracao(String textoConcentracao) {
        if (textoConcentracao == null || textoConcentracao.isBlank()) return null;
        try {
            // Tenta pegar o primeiro número (ex: "100 mg/ml" -> 100, "0.2%" -> 0.2)
            Matcher matcher = Pattern.compile("([0-9]+([.,][0-9]+)?)").matcher(textoConcentracao);
            if (matcher.find()) {
                String numeroStr = matcher.group(1).replace(",", ".");
                return Double.parseDouble(numeroStr);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
