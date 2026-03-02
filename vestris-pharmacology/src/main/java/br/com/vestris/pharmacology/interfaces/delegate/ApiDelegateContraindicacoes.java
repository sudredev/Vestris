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

        // Captura a referência ou define um padrão caso venha nulo
        String referencia = request.getReferenciaTexto() != null ? request.getReferenciaTexto() : "Referência interna";

        // Chama o serviço passando tanto Medicamento quanto Princípio.
        // O Serviço deve ter a assinatura: criar(UUID medId, UUID principioId, UUID espId, String ref, Gravidade g, String desc)
        // Nota: request.getPrincipioAtivoId() só funcionará se você adicionou este campo no YAML do Swagger.
        // Se ainda não adicionou, o Java não encontrará o método getter.

        UUID principioId = null;
        // Tenta obter o ID do princípio se o DTO gerado tiver o método (depende da sua atualização no YAML)
        try {
            // principioId = request.getPrincipioAtivoId(); // Descomente se o método existir
        } catch (Exception e) {
            // Ignora se não existir no DTO ainda
        }

        Contraindicacao salvo = servico.criar(
                request.getMedicamentoId().get(), // Pode ser null se vier pelo admin de principios
                principioId,                // Novo campo (prioritário)
                request.getEspecieId(),
                referencia,
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
        Contraindicacao.Gravidade gravidadeDominio = Contraindicacao.Gravidade.valueOf(request.getGravidade().name());

        String referencia = request.getReferenciaTexto() != null ? request.getReferenciaTexto() : "Referência não informada";

        // O método atualizar no serviço precisa aceitar a referência String agora
        Contraindicacao atualizada = servico.atualizar(
                id,
                request.getEspecieId(),
                referencia,
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

    // --- CONVERSOR ---
    private ContraindicacaoResponse converter(Contraindicacao c) {
        ContraindicacaoResponse dto = new ContraindicacaoResponse();
        dto.setId(c.getId());

        // IMPORTANTE: O Frontend espera um ID no campo "medicamentoId" para links.
        // Como a contraindicação agora é baseada em princípio ativo, retornamos o ID do princípio
        // neste campo para manter a compatibilidade visual, ou null se preferir.
        if (c.getPrincipioAtivo() != null) {
            dto.setMedicamentoId(c.getPrincipioAtivo().getId());
        }

        dto.setEspecieId(c.getEspecieId());

        // Se você atualizou o response DTO para ter referenciaTexto, use:
        // dto.setReferenciaTexto(c.getReferenciaTexto());
        // Caso contrário, mantemos referenciaId como null pois mudamos para texto
        dto.setReferenciaId(null);

        dto.setDescricao(c.getDescricao());
        dto.setGravidade(GravidadeEnum.valueOf(c.getGravidade().name()));
        return dto;
    }
}
