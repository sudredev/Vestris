package br.com.vestris.vaccination.interfaces.delegate;

import br.com.vestris.vaccination.application.ServiceProtocoloVacinacao;
import br.com.vestris.vaccination.domain.model.ProtocoloVacinacao;
import br.com.vestris.vaccination.interfaces.api.ProtocolosVacinaisApiDelegate;
import br.com.vestris.vaccination.interfaces.dto.ApiResponseListaProtocoloVacinal;
import br.com.vestris.vaccination.interfaces.dto.ApiResponseProtocoloVacinal;
import br.com.vestris.vaccination.interfaces.dto.ProtocoloVacinalRequest;
import br.com.vestris.vaccination.interfaces.dto.ProtocoloVacinalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateProtocoloVacinal implements ProtocolosVacinaisApiDelegate {
    private final ServiceProtocoloVacinacao servico;

    @Override
    public ResponseEntity<ApiResponseProtocoloVacinal> criarProtocoloVacinal(ProtocoloVacinalRequest request) {
        ProtocoloVacinacao entidade = new ProtocoloVacinacao();
        entidade.setEspecieId(request.getEspecieId());
        entidade.setReferenciaId(request.getReferenciaId());
        entidade.setIdadeMinimaDias(request.getIdadeMinimaDias());
        entidade.setDiasParaReforco(request.getDiasParaReforco());
        entidade.setObrigatoria(request.getObrigatoria());
        entidade.setObservacoes(request.getObservacoes());

        ProtocoloVacinacao salvo = servico.criar(entidade, request.getVacinaId());

        ApiResponseProtocoloVacinal response = new ApiResponseProtocoloVacinal();
        response.setSucesso(true);
        response.setDados(converter(salvo));

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseListaProtocoloVacinal> listarProtocolosPorEspecie(UUID especieId) {
        List<ProtocoloVacinalResponse> lista = servico.listarPorEspecie(especieId).stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaProtocoloVacinal response = new ApiResponseListaProtocoloVacinal();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    private ProtocoloVacinalResponse converter(ProtocoloVacinacao p) {
        ProtocoloVacinalResponse dto = new ProtocoloVacinalResponse();
        dto.setId(p.getId());
        dto.setEspecieId(p.getEspecieId());
        dto.setReferenciaId(p.getReferenciaId());
        dto.setVacinaId(p.getVacina().getId());
        dto.setNomeVacina(p.getVacina().getNome()); // Informação útil para o front
        dto.setIdadeMinimaDias(p.getIdadeMinimaDias());
        dto.setDiasParaReforco(p.getDiasParaReforco());
        dto.setObrigatoria(p.isObrigatoria());
        dto.setObservacoes(p.getObservacoes());
        return dto;
    }

    @Override
    public ResponseEntity<ApiResponseProtocoloVacinal> buscarProtocoloVacinalPorId(UUID id) {
        ProtocoloVacinacao p = servico.buscarPorId(id);
        ApiResponseProtocoloVacinal response = new ApiResponseProtocoloVacinal();
        response.setSucesso(true);
        response.setDados(converter(p));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseProtocoloVacinal> atualizarProtocoloVacinal(UUID id, ProtocoloVacinalRequest request) {
        ProtocoloVacinacao atualizado = servico.atualizar(
                id,
                request.getEspecieId(),
                request.getVacinaId(),
                request.getReferenciaId(),
                request.getIdadeMinimaDias(),
                request.getDiasParaReforco(),
                request.getObrigatoria(),
                request.getObservacoes()
        );

        ApiResponseProtocoloVacinal response = new ApiResponseProtocoloVacinal();
        response.setSucesso(true);
        response.setMensagem("Protocolo atualizado.");
        response.setDados(converter(atualizado));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deletarProtocoloVacinal(UUID id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
