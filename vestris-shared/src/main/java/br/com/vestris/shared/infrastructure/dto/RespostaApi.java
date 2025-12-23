package br.com.vestris.shared.infrastructure.dto;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RespostaApi<T>{
    private boolean sucesso;
    private String mensagem;
    private T dados; // O objeto que você quer retornar (ex: Especie)
    private LocalDateTime dataHora;

    // Método estático para facilitar o sucesso
    public static <T> RespostaApi<T> sucesso(T dados) {
        return RespostaApi.<T>builder()
                .sucesso(true)
                .dados(dados)
                .dataHora(LocalDateTime.now())
                .build();
    }

    // Método estático para facilitar o erro
    public static <T> RespostaApi<T> erro(String mensagem) {
        return RespostaApi.<T>builder()
                .sucesso(false)
                .mensagem(mensagem)
                .dataHora(LocalDateTime.now())
                .build();
    }
}
