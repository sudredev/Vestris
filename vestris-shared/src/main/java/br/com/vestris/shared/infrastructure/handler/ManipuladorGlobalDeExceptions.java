package br.com.vestris.shared.infrastructure.handler;

import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.shared.infrastructure.dto.RespostaApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ManipuladorGlobalDeExceptions {

    // Trata erros de Regra de Negócio (ex: Espécie não existe, Duplicidade)
    // Retorna 400 Bad Request
    @ExceptionHandler(ExcecaoRegraNegocio.class)
    public ResponseEntity<RespostaApi<Void>> tratarRegraNegocio(ExcecaoRegraNegocio ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(RespostaApi.erro(ex.getMessage()));
    }

    // Trata erros de Recurso Não Encontrado (ex: ID inexistente no banco)
    // Retorna 404 Not Found
    @ExceptionHandler(ExceptionRecursoNaoEncontrado.class)
    public ResponseEntity<RespostaApi<Void>> tratarRecursoNaoEncontrado(ExceptionRecursoNaoEncontrado ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(RespostaApi.erro(ex.getMessage()));
    }

    // Trata qualquer outro erro não previsto (NullPointer, Banco fora do ar, etc)
    // Retorna 500 Internal Server Error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespostaApi<Void>> tratarErroGenerico(Exception ex) {
        ex.printStackTrace(); // Imprime no terminal para o dev ver
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(RespostaApi.erro("Ocorreu um erro interno no servidor. Contate o suporte."));
    }

}
