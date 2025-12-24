package br.com.vestris.clinical.interfaces.delegate;

import br.com.vestris.clinical.application.ServiceDoenca;
import br.com.vestris.clinical.domain.model.Doenca;
import br.com.vestris.clinical.interfaces.api.DoencasApiDelegate;
import br.com.vestris.clinical.interfaces.dto.ApiResponseDoenca;
import br.com.vestris.clinical.interfaces.dto.ApiResponseListaDoenca;
import br.com.vestris.clinical.interfaces.dto.DoencaRequest;
import br.com.vestris.clinical.interfaces.dto.DoencaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateDoencas implements DoencasApiDelegate {
    private final ServiceDoenca servico;

    // --- CRIAÇÃO ---
    @Override
    public ResponseEntity<ApiResponseDoenca> criarDoenca(DoencaRequest request) {
        Doenca entidade = new Doenca();
        entidade.setNome(request.getNome());
        entidade.setNomeCientifico(request.getNomeCientifico());
        entidade.setSintomas(request.getSintomas());
        entidade.setEspecieId(request.getEspecieId());

        Doenca salva = servico.criar(entidade);

        ApiResponseDoenca response = new ApiResponseDoenca();
        response.setSucesso(true);
        response.setMensagem("Doença cadastrada com sucesso.");
        response.setDados(converterParaDTO(salva));

        return ResponseEntity.ok(response);
    }

    // --- LISTAGEM ---
    @Override
    public ResponseEntity<ApiResponseListaDoenca> listarDoencas() {
        List<DoencaResponse> lista = servico.listarTodas().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());

        ApiResponseListaDoenca response = new ApiResponseListaDoenca();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseListaDoenca> listarDoencasPorEspecie(UUID especieId) {
        List<DoencaResponse> lista = servico.listarPorEspecie(especieId).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());

        ApiResponseListaDoenca response = new ApiResponseListaDoenca();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    // --- NOVOS MÉTODOS (ID, PUT, DELETE) ---

    @Override
    public ResponseEntity<ApiResponseDoenca> buscarDoencaPorId(UUID id) {
        Doenca encontrada = servico.buscarPorId(id);

        ApiResponseDoenca response = new ApiResponseDoenca();
        response.setSucesso(true);
        response.setDados(converterParaDTO(encontrada));

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseDoenca> atualizarDoenca(UUID id, DoencaRequest request) {
        Doenca dadosParaAtualizar = new Doenca();
        dadosParaAtualizar.setNome(request.getNome());
        dadosParaAtualizar.setNomeCientifico(request.getNomeCientifico());
        dadosParaAtualizar.setSintomas(request.getSintomas());
        // Nota: Geralmente não atualizamos o especieId no PUT para não quebrar integridade,
        // mas se o serviço permitir, adicione: dadosParaAtualizar.setEspecieId(request.getEspecieId());

        Doenca atualizada = servico.atualizar(id, dadosParaAtualizar);

        ApiResponseDoenca response = new ApiResponseDoenca();
        response.setSucesso(true);
        response.setMensagem("Doença atualizada com sucesso.");
        response.setDados(converterParaDTO(atualizada));

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deletarDoenca(UUID id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build(); // Retorna 204
    }

    // --- CONVERSOR ---
    private DoencaResponse converterParaDTO(Doenca entidade) {
        DoencaResponse dto = new DoencaResponse();
        dto.setId(entidade.getId());
        dto.setNome(entidade.getNome());
        dto.setNomeCientifico(entidade.getNomeCientifico());
        dto.setSintomas(entidade.getSintomas());
        dto.setEspecieId(entidade.getEspecieId());

        if (entidade.getCriadoEm() != null) {
            dto.setCriadoEm(entidade.getCriadoEm().atOffset(ZoneOffset.UTC));
        }
        return dto;
    }
}
