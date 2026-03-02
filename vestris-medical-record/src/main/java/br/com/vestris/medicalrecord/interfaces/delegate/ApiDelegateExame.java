package br.com.vestris.medicalrecord.interfaces.delegate;

import br.com.vestris.medicalrecord.application.ServiceExames;
import br.com.vestris.medicalrecord.domain.model.ExameAnexo;
import br.com.vestris.medicalrecord.interfaces.api.ExamesApiDelegate;
import br.com.vestris.medicalrecord.interfaces.dto.ApiResponseExameAnexo;
import br.com.vestris.medicalrecord.interfaces.dto.ApiResponseListaExameAnexo;
import br.com.vestris.medicalrecord.interfaces.dto.ExameAnexoResponse;
import br.com.vestris.user.application.ServiceAuditoria;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateExame implements ExamesApiDelegate {

    private final ServiceExames servico;
    private final ServiceAuditoria serviceAuditoria;

    @Override
    public ResponseEntity<ApiResponseExameAnexo> uploadExame(UUID atendimentoId, MultipartFile arquivo, String observacoes) {
        // Validação básica de arquivo
        if (arquivo == null || arquivo.isEmpty()) {
            return ResponseEntity.badRequest().body(null); // Ou lance uma ExcecaoRegraNegocio
        }

        // Lógica de "Upload" (Passamos a responsabilidade pro Service lidar com bytes/S3)
        // Aqui simulamos que o Service vai salvar e devolver a entidade com a URL gerada

        String nomeOriginal = arquivo.getOriginalFilename();
        String tipoConteudo = arquivo.getContentType();

        // Dica: Em um cenário real, você passaria 'arquivo.getInputStream()' para o service.
        // Como combinamos que o Service receberia Strings no passo anterior,
        // vamos simular a URL aqui ou ajustar o Service para aceitar MultipartFile (recomendado).

        // Assumindo que o ServiceExames foi ajustado para receber os metadados e gerar a URL:
        // urlSimulada = "https://s3.amazon..." ou "/uploads/..."
        String urlSimulada = "https://storage.vestris.com/" + UUID.randomUUID() + "_" + nomeOriginal;

        ExameAnexo salvo = servico.anexar(
                atendimentoId,
                nomeOriginal,
                tipoConteudo,
                urlSimulada,
                observacoes
        );

        ApiResponseExameAnexo response = new ApiResponseExameAnexo();
        response.setSucesso(true);
        response.setMensagem("Arquivo anexado com sucesso.");
        response.setDados(converter(salvo));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<ApiResponseListaExameAnexo> listarExamesPorAtendimento(UUID atendimentoId) {
        List<ExameAnexoResponse> lista = servico.listarPorAtendimento(atendimentoId).stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaExameAnexo response = new ApiResponseListaExameAnexo();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deletarExame(UUID id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // --- CONVERSOR ---
    private ExameAnexoResponse converter(ExameAnexo entidade) {
        ExameAnexoResponse dto = new ExameAnexoResponse();
        dto.setId(entidade.getId());
        dto.setAtendimentoId(entidade.getAtendimentoId());
        dto.setNomeArquivo(entidade.getNomeArquivo());
        dto.setTipoArquivo(entidade.getTipoArquivo());
        dto.setUrlArquivo(entidade.getUrlArquivo());
        dto.setObservacoes(entidade.getObservacoes());

        if (entidade.getCriadoEm() != null) {
            dto.setCriadoEm(entidade.getCriadoEm().atOffset(ZoneOffset.UTC));
        }
        return dto;
    }
}
