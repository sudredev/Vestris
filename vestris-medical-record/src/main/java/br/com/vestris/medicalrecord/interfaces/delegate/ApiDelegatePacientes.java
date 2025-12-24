package br.com.vestris.medicalrecord.interfaces.delegate;

import br.com.vestris.medicalrecord.application.ServicePaciente;
import br.com.vestris.medicalrecord.domain.model.Paciente;
import br.com.vestris.medicalrecord.interfaces.api.PacientesApiDelegate;
import br.com.vestris.medicalrecord.interfaces.dto.ApiResponseListaPaciente;
import br.com.vestris.medicalrecord.interfaces.dto.ApiResponsePaciente;
import br.com.vestris.medicalrecord.interfaces.dto.PacienteRequest;
import br.com.vestris.medicalrecord.interfaces.dto.PacienteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegatePacientes implements PacientesApiDelegate {

    private final ServicePaciente servico;

    @Override
    public ResponseEntity<ApiResponsePaciente> criarPaciente(PacienteRequest request) {
        Paciente p = new Paciente();
        p.setNome(request.getNome());
        p.setEspecieId(request.getEspecieId());
        p.setVeterinarioId(request.getVeterinarioId());
        p.setDadosTutor(request.getDadosTutor());
        p.setIdentificacaoAnimal(request.getIdentificacaoAnimal());
        p.setDataNascimento(request.getDataNascimento());
        p.setPesoAtualGramas(request.getPesoAtualGramas());

        // Novos campos
        p.setMicrochip(request.getMicrochip());
        p.setPelagemCor(request.getPelagemCor());

        // Enum (Agora converte bonito)
        if(request.getSexo() != null) {
            p.setSexo(request.getSexo().name());
        }

        Paciente salvo = servico.criar(p);

        ApiResponsePaciente response = new ApiResponsePaciente();
        response.setSucesso(true);
        response.setDados(converter(salvo));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseListaPaciente> listarPacientes(UUID veterinarioId) {
        List<PacienteResponse> lista = servico.listarPorVeterinario(veterinarioId).stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaPaciente response = new ApiResponseListaPaciente();
        response.setSucesso(true);
        response.setDados(lista);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponsePaciente> buscarPacientePorId(UUID id) {
        return ResponseEntity.ok(criarResponse(servico.buscarPorId(id)));
    }

    @Override
    public ResponseEntity<ApiResponsePaciente> atualizarPaciente(UUID id, PacienteRequest request) {
        Paciente p = new Paciente();
        p.setNome(request.getNome());
        p.setDadosTutor(request.getDadosTutor());
        p.setIdentificacaoAnimal(request.getIdentificacaoAnimal());
        p.setDataNascimento(request.getDataNascimento());
        p.setPesoAtualGramas(request.getPesoAtualGramas());
        p.setMicrochip(request.getMicrochip());
        p.setPelagemCor(request.getPelagemCor());
        if(request.getSexo() != null) p.setSexo(request.getSexo().name());

        return ResponseEntity.ok(criarResponse(servico.atualizar(id, p)));
    }

    @Override
    public ResponseEntity<Void> deletarPaciente(UUID id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // --- HELPERS ---
    private ApiResponsePaciente criarResponse(Paciente p) {
        ApiResponsePaciente r = new ApiResponsePaciente();
        r.setSucesso(true);
        r.setDados(converter(p));
        return r;
    }

    private PacienteResponse converter(Paciente p) {
        PacienteResponse dto = new PacienteResponse();
        dto.setId(p.getId());
        dto.setNome(p.getNome());
        dto.setEspecieId(p.getEspecieId());
        dto.setVeterinarioId(p.getVeterinarioId()); // Agora existe no DTO
        dto.setDadosTutor(p.getDadosTutor());
        dto.setIdentificacaoAnimal(p.getIdentificacaoAnimal());

        // Novos campos no DTO
        dto.setDataNascimento(p.getDataNascimento());
        dto.setMicrochip(p.getMicrochip());
        dto.setPelagemCor(p.getPelagemCor());
        dto.setPesoAtualGramas(p.getPesoAtualGramas());

        if (p.getCriadoEm() != null) dto.setCriadoEm(p.getCriadoEm().atOffset(ZoneOffset.UTC));

        // Conversão do Enum (Agora o DTO espera SexoEnum)
        try {
            if(p.getSexo() != null) {
                // Importe o SexoEnum do pacote interfaces.dto
                dto.setSexo(br.com.vestris.medicalrecord.interfaces.dto.SexoEnum.valueOf(p.getSexo()));
            }
        } catch (Exception e) {
            // Se o valor no banco for inválido, deixa nulo ou trata
        }

        return dto;
    }
}
