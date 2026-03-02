package br.com.vestris.medicalrecord.interfaces.delegate;

import br.com.vestris.medicalrecord.application.ServiceAtendimento;
import br.com.vestris.medicalrecord.domain.model.Atendimento;
import br.com.vestris.medicalrecord.interfaces.api.AtendimentosApiDelegate;
import br.com.vestris.medicalrecord.interfaces.dto.*;
import br.com.vestris.species.application.ServiceEspecie;
import br.com.vestris.user.application.ServiceUsuario;
import br.com.vestris.user.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateAtendimentos implements AtendimentosApiDelegate {

    private final ServiceAtendimento servico;
    private final ServiceUsuario servicoUsuario;
    private final ServiceEspecie servicoEspecie;

    // --- AGENDAR (NOVO FLUXO COM TIPO) ---
    @Override
    public ResponseEntity<ApiResponseAtendimento> agendarAtendimento(AgendamentoRequest request) {
        Atendimento a = new Atendimento();
        a.setVeterinarioId(request.getVeterinarioId());
        a.setTitulo(request.getTitulo());
        a.setProtocoloId(request.getProtocoloId());

        // Mapeamento do Tipo de Atendimento
        if (request.getTipo() != null) {
            try {
                a.setTipo(Atendimento.TipoAtendimento.valueOf(request.getTipo().name()));
            } catch (Exception e) {
                // Fallback seguro
                a.setTipo(Atendimento.TipoAtendimento.CONSULTA_CLINICA);
            }
        } else {
            a.setTipo(Atendimento.TipoAtendimento.CONSULTA_CLINICA);
        }

        if (request.getDataHora() != null) {
            a.setDataHora(request.getDataHora().toLocalDateTime());
        } else {
            a.setDataHora(LocalDateTime.now());
        }

        a.setStatus(Atendimento.StatusAtendimento.AGENDADO);

        Atendimento salvo = servico.criar(a, request.getPacienteId());
        return ResponseEntity.ok(criarResponse(salvo));
    }

    // --- CRIAR (LEGADO/COMPLETO) ---
    @Override
    public ResponseEntity<ApiResponseAtendimento> criarAtendimento(AtendimentoRequest request) {
        Atendimento a = new Atendimento();
        a.setVeterinarioId(request.getVeterinarioId());
        a.setProtocoloId(request.getProtocoloId());
        a.setTitulo(request.getTitulo());

        // Mapeamento de Status
        if (request.getStatus() != null) {
            try {
                a.setStatus(Atendimento.StatusAtendimento.valueOf(request.getStatus().name()));
            } catch (Exception e) { }
        }

        // Mapeamento de Tipo (Se enviado no modo completo)
        if (request.getTipo() != null) {
            try {
                a.setTipo(Atendimento.TipoAtendimento.valueOf(request.getTipo().name()));
            } catch (Exception e) {
                a.setTipo(Atendimento.TipoAtendimento.CONSULTA_CLINICA);
            }
        } else {
            a.setTipo(Atendimento.TipoAtendimento.CONSULTA_CLINICA);
        }

        a.setQueixaPrincipal(request.getQueixaPrincipal());
        a.setHistoricoClinico(request.getHistoricoClinico());
        a.setExameFisico(request.getExameFisico());
        a.setDiagnostico(request.getDiagnostico());
        a.setCondutaClinica(request.getCondutaClinica());
        a.setObservacoes(request.getObservacoes());

        if (request.getDataHora() != null) {
            a.setDataHora(request.getDataHora().toLocalDateTime());
        }

        Atendimento salvo = servico.criar(a, request.getPacienteId());
        return ResponseEntity.ok(criarResponse(salvo));
    }

    // --- LISTAR ---
    @Override
    public ResponseEntity<ApiResponseListaAtendimento> listarMeusAtendimentos(
            UUID veterinarioId,
            StatusAtendimentoEnum status,
            UUID pacienteId,
            java.time.LocalDate dataInicio,
            java.time.LocalDate dataFim
    ) {
        Atendimento.StatusAtendimento statusDomain = status != null
                ? Atendimento.StatusAtendimento.valueOf(status.name())
                : null;

        List<AtendimentoResponse> lista = servico.listar(veterinarioId, statusDomain, pacienteId, dataInicio, dataFim)
                .stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaAtendimento r = new ApiResponseListaAtendimento();
        r.setSucesso(true);
        r.setDados(lista);
        return ResponseEntity.ok(r);
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

    // --- ATUALIZAR (RASCUNHO/PUT) ---
    @Override
    public ResponseEntity<ApiResponseAtendimento> atualizarAtendimento(UUID id, AtendimentoRequest request) {
        Atendimento a = new Atendimento();
        a.setTitulo(request.getTitulo());

        if (request.getStatus() != null) {
            try {
                a.setStatus(Atendimento.StatusAtendimento.valueOf(request.getStatus().name()));
            } catch (Exception e) {}
        }

        a.setQueixaPrincipal(request.getQueixaPrincipal());
        a.setHistoricoClinico(request.getHistoricoClinico());
        a.setExameFisico(request.getExameFisico());
        a.setDiagnostico(request.getDiagnostico());
        a.setCondutaClinica(request.getCondutaClinica());
        a.setObservacoes(request.getObservacoes());
        a.setOrientacoesManejo(request.getOrientacoesManejo());
        a.setProtocoloId(request.getProtocoloId());

        Atendimento atualizado = servico.atualizar(id, a);
        return ResponseEntity.ok(criarResponse(atualizado));
    }

    // --- FINALIZAR ---
    @Override
    public ResponseEntity<ApiResponseAtendimento> finalizarAtendimento(UUID id, FinalizacaoAtendimentoRequest request) {
        Atendimento dadosClinicos = new Atendimento();
        dadosClinicos.setTitulo(request.getTitulo());
        dadosClinicos.setQueixaPrincipal(request.getQueixaPrincipal());
        dadosClinicos.setHistoricoClinico(request.getHistoricoClinico());
        dadosClinicos.setExameFisico(request.getExameFisico());
        dadosClinicos.setDiagnostico(request.getDiagnostico());
        dadosClinicos.setCondutaClinica(request.getCondutaClinica());
        dadosClinicos.setObservacoes(request.getObservacoes());
        dadosClinicos.setProtocoloId(request.getProtocoloId());

        Atendimento finalizado = servico.finalizar(id, dadosClinicos);
        return ResponseEntity.ok(criarResponse(finalizado));
    }

    @Override
    public ResponseEntity<ApiResponseAtendimento> atualizarStatusAtendimento(UUID id, AtualizarStatusAtendimentoRequest request) {
        Atendimento.StatusAtendimento novoStatus = Atendimento.StatusAtendimento.valueOf(request.getStatus().name());
        Atendimento atualizado = servico.atualizarStatus(id, novoStatus);
        return ResponseEntity.ok(criarResponse(atualizado));
    }

    // --- CONVERSORES ---
    private ApiResponseAtendimento criarResponse(Atendimento a) {
        ApiResponseAtendimento r = new ApiResponseAtendimento();
        r.setSucesso(true);
        r.setDados(converter(a));
        return r;
    }

    private AtendimentoResponse converter(Atendimento a) {
        AtendimentoResponse dto = new AtendimentoResponse();
        dto.setId(a.getId());

        // Datas
        if(a.getCriadoEm() != null) dto.setDataHora(a.getCriadoEm().atOffset(ZoneOffset.UTC));
        if(a.getDataHora() != null) dto.setDataHora(a.getDataHora().atOffset(ZoneOffset.UTC));

        dto.setTitulo(a.getTitulo());

        // Conversão de Status
        if (a.getStatus() != null) {
            dto.setStatus(StatusAtendimentoEnum.valueOf(a.getStatus().name()));
        }

        // Conversão de Tipo (NOVO)
        if (a.getTipo() != null) {
            try {
                dto.setTipo(TipoAtendimentoEnum.valueOf(a.getTipo().name()));
            } catch (Exception e) {
                // Se o enum do banco for novo e o DTO antigo não conhecer, fallback ou null
                dto.setTipo(TipoAtendimentoEnum.CONSULTA_CLINICA);
            }
        } else {
            dto.setTipo(TipoAtendimentoEnum.CONSULTA_CLINICA);
        }

        // Dados Clínicos
        dto.setQueixaPrincipal(a.getQueixaPrincipal());
        dto.setHistoricoClinico(a.getHistoricoClinico());
        dto.setExameFisico(a.getExameFisico());
        dto.setDiagnostico(a.getDiagnostico());
        dto.setCondutaClinica(a.getCondutaClinica());
        dto.setOrientacoesManejo(a.getOrientacoesManejo());
        dto.setObservacoes(a.getObservacoes());

        dto.setProtocoloId(a.getProtocoloId());
        dto.setVeterinarioId(a.getVeterinarioId());

        // Dados Relacionados (Enriquecimento)
        try {
            Usuario veterinario = servicoUsuario.buscarPorId(a.getVeterinarioId());
            dto.setVeterinarioNome(veterinario.getNome());
            dto.setVeterinarioCrmv(veterinario.getCrmv());
        } catch (Exception e) {
            dto.setVeterinarioNome("Veterinário não encontrado");
        }

        if (a.getPaciente() != null) {
            dto.setPacienteId(a.getPaciente().getId());
            dto.setPacienteNome(a.getPaciente().getNome());
            try {
                var especie = servicoEspecie.buscarPorId(a.getPaciente().getEspecieId());
                dto.setPacienteEspecie(especie.getNomePopular());
            } catch (Exception e) {
                dto.setPacienteEspecie("Espécie não identificada");
            }
        }
        return dto;
    }
}