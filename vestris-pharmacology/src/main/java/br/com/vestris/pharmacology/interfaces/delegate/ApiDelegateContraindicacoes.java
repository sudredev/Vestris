package br.com.vestris.pharmacology.interfaces.delegate;

import br.com.vestris.pharmacology.application.ServiceContraindicacao;
import br.com.vestris.pharmacology.domain.model.Contraindicacao;
import br.com.vestris.pharmacology.interfaces.api.ContraindicacoesApiDelegate;
import br.com.vestris.pharmacology.interfaces.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateContraindicacoes implements ContraindicacoesApiDelegate {

    private final ServiceContraindicacao servico;

    @Override
    public ResponseEntity<ApiResponseContraindicacao> criarContraindicacao(ContraindicacaoRequest request) {
        Contraindicacao.Gravidade gravidade = Contraindicacao.Gravidade.valueOf(request.getGravidade().name());

        Contraindicacao salvo = servico.criar(
                request.getMedicamentoId(),
                request.getEspecieId(),
                request.getReferenciaId(),
                gravidade,
                request.getDescricao()
        );

        ApiResponseContraindicacao response = new ApiResponseContraindicacao();
        response.setSucesso(true);
        response.setDados(converter(salvo));

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseListaContraindicacao> listarContraindicacoesPorMedicamento(UUID medicamentoId) {
        List<ContraindicacaoResponse> lista = servico.listarPorMedicamento(medicamentoId).stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaContraindicacao response = new ApiResponseListaContraindicacao();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    private ContraindicacaoResponse converter(Contraindicacao c) {
        ContraindicacaoResponse dto = new ContraindicacaoResponse();
        dto.setId(c.getId());
        dto.setMedicamentoId(c.getMedicamento().getId());
        dto.setEspecieId(c.getEspecieId());
        dto.setReferenciaId(c.getReferenciaId());
        dto.setDescricao(c.getDescricao());
        dto.setGravidade(GravidadeEnum.valueOf(c.getGravidade().name()));
        return dto;
    }

    @Override
    public ResponseEntity<ApiResponseContraindicacao> buscarContraindicacaoPorId(UUID id) {
        Contraindicacao encontrada = servico.buscarPorId(id);

        ApiResponseContraindicacao response = new ApiResponseContraindicacao();
        response.setSucesso(true);
        response.setDados(converter(encontrada));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseContraindicacao> atualizarContraindicacao(UUID id, ContraindicacaoRequest request) {
        // Converte o Enum do DTO para o Enum do Domínio
        Contraindicacao.Gravidade gravidadeDominio = Contraindicacao.Gravidade.valueOf(request.getGravidade().name());

        // Chama o serviço (Note que mantemos o Medicamento original, geralmente não se muda o medicamento da contraindicação, se cria outra)
        // Mas se quiser permitir mudar medicamento, precisaria de mais validação no serviço.
        // Aqui assumo que atualizamos Especie, Referencia, Gravidade e Descrição.

        Contraindicacao atualizada = servico.atualizar(
                id,
                request.getEspecieId(),
                request.getReferenciaId(),
                gravidadeDominio,
                request.getDescricao()
        );

        ApiResponseContraindicacao response = new ApiResponseContraindicacao();
        response.setSucesso(true);
        response.setMensagem("Contraindicação atualizada.");
        response.setDados(converter(atualizada));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deletarContraindicacao(UUID id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
