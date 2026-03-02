package br.com.vestris.species.interfaces.delegate;

import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.species.application.ServiceModeloExame;
import br.com.vestris.species.domain.ModeloExameFisico;
import br.com.vestris.species.interfaces.api.ExamesFisicosApiDelegate;
import br.com.vestris.species.interfaces.dto.ApiResponseListaModeloExame;
import br.com.vestris.species.interfaces.dto.ApiResponseModeloExame;
import br.com.vestris.species.interfaces.dto.ModeloExameRequest;
import br.com.vestris.species.interfaces.dto.ModeloExameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateModeloExame implements ExamesFisicosApiDelegate {
    private final ServiceModeloExame servico;

    // --- GET (Buscar) ---
    @Override
    public ResponseEntity<ApiResponseModeloExame> obterModeloExame(UUID especieId) {
        try {
            ModeloExameFisico modelo = servico.buscarPorEspecie(especieId);
            return ResponseEntity.ok(criarResponse(modelo, "Modelo encontrado com sucesso."));
        } catch (ExceptionRecursoNaoEncontrado e) {
            // Se não encontrar o modelo, retornamos um 200 com dados nulos ou 404.
            // Para facilitar o front (evitar erro vermelho no console), vamos retornar 200 com dados null
            // e o front decide mostrar o "template genérico".
            ApiResponseModeloExame response = new ApiResponseModeloExame();
            response.setSucesso(false);
            response.setMensagem("Nenhum modelo específico encontrado. Use o padrão.");
            return ResponseEntity.ok(response);
        }
    }

    // --- POST (Criar) ---
    @Override
    public ResponseEntity<ApiResponseModeloExame> criarModeloExame(UUID especieId, ModeloExameRequest request) {
        ModeloExameFisico salvo = servico.criar(especieId, request.getTextoBase());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(criarResponse(salvo, "Modelo de exame físico criado com sucesso."));
    }

    // --- PUT (Atualizar) ---
    @Override
    public ResponseEntity<ApiResponseModeloExame> atualizarModeloExame(UUID especieId, ModeloExameRequest request) {
        ModeloExameFisico atualizado = servico.atualizar(especieId, request.getTextoBase());
        return ResponseEntity.ok(criarResponse(atualizado, "Modelo atualizado com sucesso."));
    }

    // --- DELETE (Remover) ---
    @Override
    public ResponseEntity<Void> deletarModeloExame(UUID especieId) {
        servico.deletar(especieId);
        return ResponseEntity.noContent().build();
    }

    // --- CONVERSOR AUXILIAR ---
    private ApiResponseModeloExame criarResponse(ModeloExameFisico entidade, String mensagem) {
        ModeloExameResponse dto = new ModeloExameResponse();
        dto.setId(entidade.getId());
        dto.setEspecieId(entidade.getEspecieId());
        dto.setTextoBase(entidade.getTextoBase());

        ApiResponseModeloExame wrapper = new ApiResponseModeloExame();
        wrapper.setSucesso(true);
        wrapper.setMensagem(mensagem);
        wrapper.setDados(dto);

        return wrapper;
    }

    @Override
    public ResponseEntity<ApiResponseListaModeloExame> listarTodosModelosExame() {
        List<ModeloExameResponse> listaDto = servico.listarTodos().stream()
                .map(entidade -> {
                    ModeloExameResponse dto = new ModeloExameResponse();
                    dto.setId(entidade.getId());
                    dto.setEspecieId(entidade.getEspecieId());
                    dto.setTextoBase(entidade.getTextoBase());
                    return dto;
                })
                .collect(Collectors.toList());

        ApiResponseListaModeloExame response = new ApiResponseListaModeloExame();
        response.setSucesso(true);
        response.setDados(listaDto);

        return ResponseEntity.ok(response);
    }
}
