package br.com.vestris.clinical.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data // Gera Getters, Setters, toString, etc.
@Builder // Permite criar o objeto de forma fluida (.builder()...build())
@NoArgsConstructor // Construtor vazio
@AllArgsConstructor // Construtor com todos os argumentos
public class CalculoResultadoDTO {
    // Contexto
    private String protocoloTitulo;
    private String medicamentoNome;
    private String referencia;

    // Dados de Entrada Normalizados
    private Double pesoKg;

    // Resultado Massa (Dose)
    private Double doseMinMg;
    private Double doseMaxMg;

    // Resultado Volume (Líquido)
    private Double volMinMl;
    private Double volMaxMl;

    // Metadados do Medicamento/Protocolo
    private String concentracao;
    private String frequencia;
    private String via;
    private String duracao;

    // Segurança
    private List<String> alertas;
}
