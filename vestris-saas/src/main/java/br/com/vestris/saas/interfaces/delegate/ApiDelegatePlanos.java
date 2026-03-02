package br.com.vestris.saas.interfaces.delegate;

import br.com.vestris.saas.application.ServicePlano;
import br.com.vestris.saas.domain.model.Plano;
import br.com.vestris.saas.interfaces.api.PlanosApiDelegate;
import br.com.vestris.saas.interfaces.dto.ApiResponseListaPlano;
import br.com.vestris.saas.interfaces.dto.ApiResponsePlano;
import br.com.vestris.saas.interfaces.dto.PlanoRequest;
import br.com.vestris.saas.interfaces.dto.PlanoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegatePlanos implements PlanosApiDelegate {

    private final ServicePlano servico;

    @Override
    public ResponseEntity<ApiResponseListaPlano> listarPlanos() {
        List<PlanoResponse> lista = servico.listarTodos().stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaPlano response = new ApiResponseListaPlano();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponsePlano> buscarPlanoPorId(UUID id) {
        Plano plano = servico.buscarPorId(id);

        ApiResponsePlano response = new ApiResponsePlano();
        response.setSucesso(true);
        response.setDados(converter(plano));

        return ResponseEntity.ok(response);
    }

    // Método POST (Admin) - Se você definiu no YAML
    @Override
    public ResponseEntity<ApiResponsePlano> criarPlano(PlanoRequest request) {
        Plano p = new Plano();
        p.setNome(request.getNome());
        p.setDescricao(request.getDescricao());
        p.setPrecoMensal(BigDecimal.valueOf(request.getPrecoMensal()));
        p.setPrecoAnual(BigDecimal.valueOf(request.getPrecoAnual()));
        p.setMaxVeterinarios(request.getMaxVeterinarios());
        p.setCustom(request.getIsCustom());
        p.setFeaturesJson(request.getFeaturesJson());

        Plano salvo = servico.criar(p);

        ApiResponsePlano response = new ApiResponsePlano();
        response.setSucesso(true);
        response.setDados(converter(salvo));
        return ResponseEntity.ok(response);
    }

    private PlanoResponse converter(Plano p) {
        PlanoResponse dto = new PlanoResponse();
        dto.setId(p.getId());
        dto.setNome(p.getNome());
        dto.setDescricao(p.getDescricao());
        if(p.getPrecoMensal() != null) dto.setPrecoMensal(p.getPrecoMensal().doubleValue());
        if(p.getPrecoAnual() != null) dto.setPrecoAnual(p.getPrecoAnual().doubleValue());
        dto.setMaxVeterinarios(p.getMaxVeterinarios());
        dto.setIsCustom(p.isCustom());
        dto.setFeaturesJson(p.getFeaturesJson());
        return dto;
    }
}
