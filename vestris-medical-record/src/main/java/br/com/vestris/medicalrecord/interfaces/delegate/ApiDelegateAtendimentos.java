package br.com.vestris.medicalrecord.interfaces.delegate;

import br.com.vestris.medicalrecord.application.ServiceAtendimento;
import br.com.vestris.medicalrecord.domain.model.Atendimento;
import br.com.vestris.medicalrecord.interfaces.api.AtendimentosApiDelegate;
import br.com.vestris.medicalrecord.interfaces.dto.ApiResponseAtendimento;
import br.com.vestris.medicalrecord.interfaces.dto.ApiResponseListaAtendimento;
import br.com.vestris.medicalrecord.interfaces.dto.AtendimentoRequest;
import br.com.vestris.medicalrecord.interfaces.dto.AtendimentoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateAtendimentos implements AtendimentosApiDelegate {

    private final ServiceAtendimento servico;

    @Override
    public ResponseEntity<ApiResponseAtendimento> criarAtendimento(AtendimentoRequest request) {
        Atendimento a = new Atendimento();
        a.setVeterinarioId(request.getVeterinarioId());
        a.setProtocoloId(request.getProtocoloId());
        a.setQueixaPrincipal(request.getQueixaPrincipal());
        a.setHistoricoClinico(request.getHistoricoClinico());
        a.setExameFisico(request.getExameFisico());
        a.setDiagnostico(request.getDiagnostico());
        a.setCondutaClinica(request.getCondutaClinica());
        a.setObservacoes(request.getObservacoes());

        Atendimento salvo = servico.criar(a, request.getPacienteId());

        return ResponseEntity.ok(criarResponse(salvo));
    }

    @Override
    public ResponseEntity<ApiResponseListaAtendimento> listarAtendimentosPorPaciente(UUID pacienteId) {
        List<AtendimentoResponse> lista = servico.listarPorPaciente(pacienteId).stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaAtendimento r = new ApiResponseListaAtendimento();
        r.setSucesso(true);
        r.setDados(lista);
        return ResponseEntity.ok(r);
    }

    @Override
    public ResponseEntity<ApiResponseAtendimento> buscarAtendimentoPorId(UUID id) {
        return ResponseEntity.ok(criarResponse(servico.buscarPorId(id)));
    }

    @Override
    public ResponseEntity<ApiResponseAtendimento> atualizarAtendimento(UUID id, AtendimentoRequest request) {
        Atendimento a = new Atendimento();
        a.setQueixaPrincipal(request.getQueixaPrincipal());
        a.setHistoricoClinico(request.getHistoricoClinico());
        a.setExameFisico(request.getExameFisico());
        a.setDiagnostico(request.getDiagnostico());
        a.setCondutaClinica(request.getCondutaClinica());
        a.setObservacoes(request.getObservacoes());
        a.setProtocoloId(request.getProtocoloId());

        return ResponseEntity.ok(criarResponse(servico.atualizar(id, a)));
    }

    private ApiResponseAtendimento criarResponse(Atendimento a) {
        ApiResponseAtendimento r = new ApiResponseAtendimento();
        r.setSucesso(true);
        r.setDados(converter(a));
        return r;
    }

    private AtendimentoResponse converter(Atendimento a) {
        AtendimentoResponse dto = new AtendimentoResponse();
        dto.setId(a.getId());
        if(a.getCriadoEm() != null) dto.setDataHora(a.getCriadoEm().atOffset(ZoneOffset.UTC));
        dto.setQueixaPrincipal(a.getQueixaPrincipal());
        dto.setHistoricoClinico(a.getHistoricoClinico());
        dto.setExameFisico(a.getExameFisico());
        dto.setDiagnostico(a.getDiagnostico());
        dto.setCondutaClinica(a.getCondutaClinica());
        dto.setProtocoloId(a.getProtocoloId());
        // Futuramente, podemos buscar o nome do vet no módulo User para preencher veterinarioNome
        return dto;
    }
}
