package br.com.vestris.shared.domain.exceptions;

public class ExceptionRecursoNaoEncontrado extends RuntimeException {
    public ExceptionRecursoNaoEncontrado(String nomeDoRecurso, String id) {
        super(String.format("%s não encontrado com id: %s", nomeDoRecurso, id));
    }
}
