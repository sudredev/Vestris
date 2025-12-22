package br.com.silvet.vaccination.interfaces.delegate;

import br.com.silvet.vaccination.application.ServiceVacinacao;
import br.com.silvet.vaccination.domain.model.Vacina;
import br.com.silvet.vaccination.interfaces.api.VacinasApiDelegate;
import br.com.silvet.vaccination.interfaces.dto.ApiResponseListaVacina;
import br.com.silvet.vaccination.interfaces.dto.ApiResponseVacina;
import br.com.silvet.vaccination.interfaces.dto.VacinaRequest;
import br.com.silvet.vaccination.interfaces.dto.VacinaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateVacinas implements VacinasApiDelegate {

    private final ServiceVacinacao servico;

    @Override
    public ResponseEntity<ApiResponseVacina> criarVacina(VacinaRequest request) {
        Vacina entidade = new Vacina();
        entidade.setNome(request.getNome());
        entidade.setFabricante(request.getFabricante());
        entidade.setTipoVacina(request.getTipoVacina());
        entidade.setDescricao(request.getDescricao());
        entidade.setDoencaAlvo(request.getDoencaAlvo());

        Vacina salva = servico.criar(entidade);

        ApiResponseVacina response = new ApiResponseVacina();
        response.setSucesso(true);
        response.setDados(converter(salva));

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseListaVacina> listarVacinas() {
        List<VacinaResponse> lista = servico.listarTodas().stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaVacina response = new ApiResponseListaVacina();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    private VacinaResponse converter(Vacina v) {
        VacinaResponse dto = new VacinaResponse();
        dto.setId(v.getId());
        dto.setNome(v.getNome());
        dto.setFabricante(v.getFabricante());
        dto.setTipoVacina(v.getTipoVacina());
        dto.setDescricao(v.getDescricao());
        dto.setDoencaAlvo(v.getDoencaAlvo());

        if (v.getCriadoEm() != null) {
            dto.setCriadoEm(v.getCriadoEm().atOffset(ZoneOffset.UTC));
        }
        return dto;
    }
}
