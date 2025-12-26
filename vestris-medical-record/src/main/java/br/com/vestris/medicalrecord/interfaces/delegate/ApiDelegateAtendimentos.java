package br.com.vestris.medicalrecord.interfaces.delegate;

import br.com.vestris.medicalrecord.application.ServiceAtendimento;
import br.com.vestris.medicalrecord.domain.model.Atendimento;
import br.com.vestris.medicalrecord.interfaces.api.AtendimentosApiDelegate;
import br.com.vestris.medicalrecord.interfaces.dto.ApiResponseAtendimento;
import br.com.vestris.medicalrecord.interfaces.dto.ApiResponseListaAtendimento;
import br.com.vestris.medicalrecord.interfaces.dto.AtendimentoRequest;
import br.com.vestris.medicalrecord.interfaces.dto.AtendimentoResponse;
import br.com.vestris.user.application.ServiceUsuario;
import br.com.vestris.user.domain.model.Usuario;
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
    private final ServiceUsuario servicoUsuario;

    @Override
    public ResponseEntity<ApiResponseAtendimento> criarAtendimento(AtendimentoRequest request) {
        Atendimento a = new Atendimento();
        a.setVeterinarioId(request.getVeterinarioId());
        a.setProtocoloId(request.getProtocoloId());

        // Mapeamento COMPLETO
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
        // Criamos um objeto temporário com os dados novos que vieram do JSON
        Atendimento a = new Atendimento();

        // --- AQUI É O PONTO CRÍTICO DO PUT ---
        // Certifique-se que todos os sets estão aqui
        a.setQueixaPrincipal(request.getQueixaPrincipal());
        a.setHistoricoClinico(request.getHistoricoClinico());
        a.setExameFisico(request.getExameFisico());
        a.setDiagnostico(request.getDiagnostico());
        a.setCondutaClinica(request.getCondutaClinica());
        a.setObservacoes(request.getObservacoes());
        a.setProtocoloId(request.getProtocoloId());
        // -------------------------------------

        // Passamos para o serviço atualizar o registro do banco
        Atendimento atualizado = servico.atualizar(id, a);

        return ResponseEntity.ok(criarResponse(atualizado));
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

        // --- MAPEAMENTO DE VOLTA (ENTITY -> DTO) ---
        dto.setQueixaPrincipal(a.getQueixaPrincipal());
        dto.setHistoricoClinico(a.getHistoricoClinico());
        dto.setExameFisico(a.getExameFisico());
        dto.setDiagnostico(a.getDiagnostico());
        dto.setCondutaClinica(a.getCondutaClinica());
        dto.setProtocoloId(a.getProtocoloId());

        // Busca dados do veterinário
        try {
            Usuario veterinario = servicoUsuario.buscarPorId(a.getVeterinarioId());
            dto.setVeterinarioNome(veterinario.getNome());
            dto.setVeterinarioCrmv(veterinario.getCrmv());
        } catch (Exception e) {
            dto.setVeterinarioNome("Veterinário não encontrado");
        }

        return dto;
    }
}
