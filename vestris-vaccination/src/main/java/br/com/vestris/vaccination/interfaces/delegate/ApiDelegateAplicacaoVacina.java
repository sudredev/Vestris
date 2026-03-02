package br.com.vestris.vaccination.interfaces.delegate;

import br.com.vestris.user.application.ServiceUsuario;
import br.com.vestris.vaccination.application.ServiceAplicacaoVacina;
import br.com.vestris.vaccination.application.ServiceVacinacao;
import br.com.vestris.vaccination.domain.model.AplicacaoVacina;
import br.com.vestris.vaccination.interfaces.api.VacinacaoPacienteApiDelegate;
import br.com.vestris.vaccination.interfaces.dto.ApiResponseAplicacaoVacina;
import br.com.vestris.vaccination.interfaces.dto.ApiResponseListaAplicacaoVacina;
import br.com.vestris.vaccination.interfaces.dto.AplicacaoVacinaRequest;
import br.com.vestris.vaccination.interfaces.dto.AplicacaoVacinaResponse;
import lombok.RequiredArgsConstructor;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateAplicacaoVacina implements VacinacaoPacienteApiDelegate {
    private final ServiceAplicacaoVacina servico;
    private final ServiceVacinacao serviceVacinacao;
    private final ServiceUsuario serviceUsuario;

    @Override
    public ResponseEntity<ApiResponseAplicacaoVacina> registrarVacinaPaciente(UUID pacienteId, UUID veterinarioId, AplicacaoVacinaRequest request) {
        AplicacaoVacina app = new AplicacaoVacina();
        app.setVacinaId(request.getVacinaId());

        // Data de Aplicação é obrigatória, então vem direto
        app.setDataAplicacao(request.getDataAplicacao());

        // --- CORREÇÃO: Usando unwrap para Data Próxima Dose (que é opcional/nullable) ---
        app.setDataProximaDose(unwrap(request.getDataProximaDose()));
        // -------------------------------------------------------------------------------

        app.setLote(request.getLote());
        app.setObservacoes(request.getObservacoes());
        app.setVeterinarioId(veterinarioId);

        AplicacaoVacina salva = servico.registrar(app, pacienteId);

        return ResponseEntity.status(201).body(criarResponse(salva));
    }

    @Override
    public ResponseEntity<ApiResponseListaAplicacaoVacina> listarVacinasDoPaciente(UUID pacienteId) {
        List<AplicacaoVacinaResponse> lista = servico.listarPorPaciente(pacienteId).stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaAplicacaoVacina response = new ApiResponseListaAplicacaoVacina();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deletarVacinaAplicada(UUID id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // --- HELPER PARA DESEMBRULHAR JSONNULLABLE ---
    private <T> T unwrap(JsonNullable<T> nullable) {
        if (nullable == null || !nullable.isPresent()) {
            return null;
        }
        return nullable.get();
    }

    // --- CONVERSORES ---

    private ApiResponseAplicacaoVacina criarResponse(AplicacaoVacina app) {
        ApiResponseAplicacaoVacina r = new ApiResponseAplicacaoVacina();
        r.setSucesso(true);
        r.setDados(converter(app));
        return r;
    }

    private AplicacaoVacinaResponse converter(AplicacaoVacina app) {
        AplicacaoVacinaResponse dto = new AplicacaoVacinaResponse();
        dto.setId(app.getId());
        dto.setPacienteId(app.getPaciente().getId());
        dto.setVacinaId(app.getVacinaId());
        dto.setDataAplicacao(app.getDataAplicacao());
        dto.setDataProximaDose(app.getDataProximaDose());
        dto.setLote(app.getLote());
        dto.setObservacoes(app.getObservacoes());

        // Enriquecimento com nome da vacina
        try {
            dto.setVacinaNome(serviceVacinacao.buscarNomePorId(app.getVacinaId()));
        } catch (Exception e) {
            dto.setVacinaNome("Vacina não identificada");
        }

        // Enriquecimento com nome do vet
        try {
            var vet = serviceUsuario.buscarPorId(app.getVeterinarioId());
            dto.setVeterinarioNome(vet.getNome());
        } catch (Exception e) {
            dto.setVeterinarioNome("Veterinário");
        }

        return dto;
    }

}
