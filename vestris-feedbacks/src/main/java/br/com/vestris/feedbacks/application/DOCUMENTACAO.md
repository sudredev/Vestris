## src\main\java\br\com\vestris\feedbacks\application

### ServiceSugestao.java

```java
// src\main\java\br\com\vestris\feedbacks\application\ServiceSugestao.java
package br.com.vestris.feedbacks.application;

import br.com.vestris.feedbacks.domain.Sugestao;
import br.com.vestris.feedbacks.domain.repository.RepositorioSugestao;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.user.application.ServiceUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceSugestao {
    private final RepositorioSugestao repositorio;
    private final ServiceUsuario servicoUsuario;

    public void registrar(UUID usuarioId, Sugestao.TipoSugestao tipo, String conteudo) {
        // Valida se quem sugeriu existe
        if (!servicoUsuario.existePorId(usuarioId)) {
            throw new ExcecaoRegraNegocio("Usuário não encontrado.");
        }

        Sugestao s = new Sugestao();
        s.setUsuarioId(usuarioId);
        s.setTipo(tipo);
        s.setConteudo(conteudo);
        s.setStatus(Sugestao.StatusSugestao.PENDENTE); // Sempre nasce pendente

        repositorio.save(s);
    }

    public List<Sugestao> listar(Sugestao.TipoSugestao tipo, Sugestao.StatusSugestao status) {
        if (tipo != null && status != null) {
            return repositorio.findByTipoAndStatus(tipo, status);
        } else if (tipo != null) {
            return repositorio.findByTipo(tipo);
        } else if (status != null) {
            return repositorio.findByStatus(status);
        } else {
            return repositorio.findAll();
        }
    }

    public void atualizarStatus(UUID id, Sugestao.StatusSugestao novoStatus) {
        Sugestao sugestao = repositorio.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Sugestão", id.toString()));

        sugestao.setStatus(novoStatus);
        repositorio.save(sugestao);
    }
}

```

