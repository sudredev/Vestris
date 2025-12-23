package br.com.vestris.species.interfaces.delegate;

import br.com.vestris.species.application.ServiceEspecie;
import br.com.vestris.species.domain.Especie;
import br.com.vestris.species.interfaces.api.EspeciesApiDelegate;
import br.com.vestris.species.interfaces.dto.ApiResponseEspecie;
import br.com.vestris.species.interfaces.dto.EspecieRequest;
import br.com.vestris.species.interfaces.dto.EspecieResponse;
import br.com.vestris.species.interfaces.dto.ApiResponseListaEspecie;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;


@Component // O Delegate deve ser um Componente Spring
@RequiredArgsConstructor
public class ApiDelegateEspecies implements EspeciesApiDelegate {
    private final ServiceEspecie servico;

    @Override
    public ResponseEntity<ApiResponseEspecie> criarEspecie(EspecieRequest request) {
        // 1. Converter DTO de Request (Gerado) -> Entidade (Domínio)
        Especie novaEspecie = new Especie();
        novaEspecie.setNomePopular(request.getNomePopular());
        novaEspecie.setNomeCientifico(request.getNomeCientifico());
        novaEspecie.setFamiliaTaxonomica(request.getFamiliaTaxonomica());
        novaEspecie.setDescricao(request.getDescricao());

        // 2. Chamar a Regra de Negócio
        Especie salva = servico.criar(novaEspecie);

        // 3. Converter Entidade -> DTO de Resposta
        EspecieResponse responseDto = converterParaResponse(salva);

        // 4. Embrulhar no Wrapper de Sucesso (ApiResponseEspecie)
        ApiResponseEspecie wrapper = new ApiResponseEspecie();
        wrapper.setSucesso(true);
        wrapper.setMensagem("Espécie criada com sucesso.");
        wrapper.setDados(responseDto);
        // wrapper.setDataHora(OffsetDateTime.now()); // Se tiver esse campo no YAML

        return ResponseEntity.ok(wrapper);
    }

    @Override
    public ResponseEntity<ApiResponseListaEspecie> listarEspecies(){
        List<Especie> listaEntidades = servico.listarTodas();

        // 2. Converter
        List<EspecieResponse> listaDto = listaEntidades.stream()
                .map(this::converterParaResponse)
                .collect(Collectors.toList());

        // 3. Embrulhar
        ApiResponseListaEspecie wrapper = new ApiResponseListaEspecie();
        wrapper.setSucesso(true);
        wrapper.setDados(listaDto);

        // Retorna o objeto wrapper (singular)
        return ResponseEntity.ok(wrapper);
    }

    // Método auxiliar para conversão (poderia ser o MapStruct no futuro)
    private EspecieResponse converterParaResponse(Especie entidade) {
        EspecieResponse dto = new EspecieResponse();
        // O Swagger gera UUID como UUID mesmo (dependendo da config) ou String.
        // Se der erro aqui, adicione .toString()
        dto.setId(entidade.getId());

        dto.setNomePopular(entidade.getNomePopular());
        dto.setNomeCientifico(entidade.getNomeCientifico());
        dto.setFamiliaTaxonomica(entidade.getFamiliaTaxonomica());
        dto.setDescricao(entidade.getDescricao());

        // Conversão de Data: LocalDateTime (Banco) -> OffsetDateTime (JSON Standard)
        if (entidade.getCriadoEm() != null) {
            dto.setCriadoEm(entidade.getCriadoEm().atOffset(ZoneOffset.UTC));
        }

        return dto;
    }
}
