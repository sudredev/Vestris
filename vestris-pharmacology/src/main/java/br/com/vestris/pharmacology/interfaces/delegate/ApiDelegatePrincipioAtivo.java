package br.com.vestris.pharmacology.interfaces.delegate;

import br.com.vestris.pharmacology.application.ServiceFarmacologia;
import br.com.vestris.pharmacology.domain.model.PrincipioAtivo;
import br.com.vestris.pharmacology.interfaces.api.PrincipiosAtivosApiDelegate;
import br.com.vestris.pharmacology.interfaces.dto.ApiResponseListaPrincipioAtivo;
import br.com.vestris.pharmacology.interfaces.dto.ApiResponsePrincipioAtivo;
import br.com.vestris.pharmacology.interfaces.dto.PrincipioAtivoRequest;
import br.com.vestris.pharmacology.interfaces.dto.PrincipioAtivoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegatePrincipioAtivo implements PrincipiosAtivosApiDelegate {
    private final ServiceFarmacologia servico;

    @Override
    public ResponseEntity<ApiResponsePrincipioAtivo> criarPrincipioAtivo(PrincipioAtivoRequest request) {
        PrincipioAtivo entidade = new PrincipioAtivo();
        entidade.setNome(request.getNome());
        entidade.setDescricao(request.getDescricao());
        entidade.setGrupoFarmacologico(request.getGrupoFarmacologico());

        PrincipioAtivo salvo = servico.criarPrincipio(entidade);

        ApiResponsePrincipioAtivo response = new ApiResponsePrincipioAtivo();
        response.setSucesso(true);
        response.setDados(converter(salvo));

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseListaPrincipioAtivo> listarPrincipiosAtivos() {
        List<PrincipioAtivoResponse> lista = servico.listarPrincipios().stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaPrincipioAtivo response = new ApiResponseListaPrincipioAtivo();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    private PrincipioAtivoResponse converter(PrincipioAtivo pa) {
        PrincipioAtivoResponse dto = new PrincipioAtivoResponse();
        dto.setId(pa.getId());
        dto.setNome(pa.getNome());
        dto.setDescricao(pa.getDescricao());
        dto.setGrupoFarmacologico(pa.getGrupoFarmacologico());
        return dto;
    }
}
