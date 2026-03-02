package br.com.vestris.vaccination.interfaces.delegate;

import br.com.vestris.species.application.ServiceEspecie;
import br.com.vestris.user.application.ServiceUsuario;
import br.com.vestris.vaccination.application.ServiceAplicacaoVacina;
import br.com.vestris.vaccination.application.ServiceVacinacao;
import br.com.vestris.vaccination.domain.model.AplicacaoVacina;
import br.com.vestris.vaccination.interfaces.api.HubVacinacaoApiDelegate;
import br.com.vestris.vaccination.interfaces.dto.ApiResponseListaAplicacaoVacina;
import br.com.vestris.vaccination.interfaces.dto.AplicacaoVacinaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ApiDelegateHubVacinacao implements HubVacinacaoApiDelegate {
    private final ServiceAplicacaoVacina serviceApp;
    private final ServiceVacinacao serviceVacina;
    private final ServiceUsuario serviceUser;
    private final ServiceEspecie serviceEspecie;

    @Override
    public ResponseEntity<ApiResponseListaAplicacaoVacina> listarVacinasAtrasadas() {
        var lista = serviceApp.listarAtrasadas().stream()
                .map(this::converterCompleto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponseListaAplicacaoVacina().sucesso(true).dados(lista));
    }

    @Override
    public ResponseEntity<ApiResponseListaAplicacaoVacina> listarVacinasPrevistas() {
        var lista = serviceApp.listarPrevistas().stream()
                .map(this::converterCompleto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponseListaAplicacaoVacina().sucesso(true).dados(lista));
    }

    private AplicacaoVacinaResponse converterCompleto(AplicacaoVacina app) {
        AplicacaoVacinaResponse dto = new AplicacaoVacinaResponse();
        dto.setId(app.getId());

        if (app.getPaciente() != null) {
            dto.setPacienteId(app.getPaciente().getId());
            dto.setPacienteNome(app.getPaciente().getNome());

            // --- CORREÇÃO AQUI: BUSCAR O NOME DA ESPÉCIE ---
            try {
                // Busca a espécie pelo ID que está no paciente
                var especie = serviceEspecie.buscarPorId(app.getPaciente().getEspecieId());
                dto.setPacienteEspecie(especie.getNomePopular()); // Ex: "Calopsita"
            } catch (Exception e) {
                dto.setPacienteEspecie("Espécie Desconhecida");
            }
            // -----------------------------------------------

            if (app.getPaciente().getDadosTutor() != null) {
                dto.setTutorNome(app.getPaciente().getDadosTutor());
            } else {
                dto.setTutorNome("Tutor não informado");
            }
        } else {
            dto.setPacienteId(null);
            dto.setPacienteNome("Paciente Inválido");
            dto.setPacienteEspecie("-");
            dto.setTutorNome("-");
        }

        dto.setDataAplicacao(app.getDataAplicacao());
        dto.setDataProximaDose(app.getDataProximaDose());

        String statusStr = serviceApp.calcularStatus(app);
        if (statusStr != null) {
            try {
                dto.setStatus(AplicacaoVacinaResponse.StatusEnum.fromValue(statusStr));
            } catch (Exception e) {}
        }

        try {
            dto.setVacinaNome(serviceVacina.buscarNomePorId(app.getVacinaId()));
        } catch (Exception e) { dto.setVacinaNome("?"); }

        return dto;
    }
}
