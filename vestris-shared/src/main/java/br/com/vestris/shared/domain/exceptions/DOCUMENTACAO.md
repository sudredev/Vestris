## src\main\java\br\com\vestris\shared\domain\exceptions

### ExcecaoRegraNegocio.java

```java
// src\main\java\br\com\vestris\shared\domain\exceptions\ExcecaoRegraNegocio.java
package br.com.vestris.shared.domain.exceptions;

public class ExcecaoRegraNegocio extends RuntimeException {
    public ExcecaoRegraNegocio(String message) {
        super(message);
    }
}

```

### ExceptionRecursoNaoEncontrado.java

```java
// src\main\java\br\com\vestris\shared\domain\exceptions\ExceptionRecursoNaoEncontrado.java
package br.com.vestris.shared.domain.exceptions;

public class ExceptionRecursoNaoEncontrado extends RuntimeException {
    public ExceptionRecursoNaoEncontrado(String nomeDoRecurso, String id) {
        super(String.format("%s não encontrado com id: %s", nomeDoRecurso, id));
    }
}

```

