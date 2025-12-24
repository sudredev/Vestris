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
import java.util.UUID;
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

    @Override
    public ResponseEntity<ApiResponsePrincipioAtivo> buscarPrincipioAtivoPorId(UUID id) {
        PrincipioAtivo pa = servico.buscarPrincipioPorId(id);

        ApiResponsePrincipioAtivo response = new ApiResponsePrincipioAtivo();
        response.setSucesso(true);
        response.setDados(converter(pa));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponsePrincipioAtivo> atualizarPrincipioAtivo(UUID id, PrincipioAtivoRequest request) {
        PrincipioAtivo dados = new PrincipioAtivo();
        dados.setNome(request.getNome());
        dados.setDescricao(request.getDescricao());
        dados.setGrupoFarmacologico(request.getGrupoFarmacologico());

        PrincipioAtivo atualizado = servico.atualizarPrincipio(id, dados);

        ApiResponsePrincipioAtivo response = new ApiResponsePrincipioAtivo();
        response.setSucesso(true);
        response.setMensagem("Atualizado com sucesso.");
        response.setDados(converter(atualizado));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deletarPrincipioAtivo(UUID id) {
        servico.deletarPrincipio(id);
        return ResponseEntity.noContent().build();
    }

    // ... converter ...
    private PrincipioAtivoResponse converter(PrincipioAtivo pa) {
        PrincipioAtivoResponse dto = new PrincipioAtivoResponse();
        dto.setId(pa.getId());
        dto.setNome(pa.getNome());
        dto.setDescricao(pa.getDescricao());
        dto.setGrupoFarmacologico(pa.getGrupoFarmacologico());
        return dto;
    }


}
