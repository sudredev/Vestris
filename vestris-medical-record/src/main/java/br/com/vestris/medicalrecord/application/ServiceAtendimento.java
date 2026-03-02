package br.com.vestris.medicalrecord.application;

import br.com.vestris.medicalrecord.domain.model.Atendimento;
import br.com.vestris.medicalrecord.domain.model.Paciente;
import br.com.vestris.medicalrecord.domain.repository.RepositorioAtendimento;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.shared.infrastructure.helper.HelperAuditoria;
import br.com.vestris.user.application.ServiceAuditoria;
import br.com.vestris.user.application.ServiceUsuario;
import br.com.vestris.user.domain.model.AcaoAuditoria;
import br.com.vestris.user.domain.model.EntidadeAuditoria;
import br.com.vestris.user.domain.model.Usuario;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceAtendimento {
    private final RepositorioAtendimento repositorio;
    private final ServicePaciente servicoPaciente;
    private final ServiceUsuario servicoUsuario;
    private final ServiceAuditoria servicoAuditoria;
    private final HelperAuditoria helperAuditoria;

    // --- LISTAGEM COM FILTROS DINÂMICOS E COMPARTILHAMENTO ---
    public List<Atendimento> listar(UUID usuarioLogadoId, Atendimento.StatusAtendimento status, UUID pacienteId, LocalDate dataInicio, LocalDate dataFim) {
        Usuario usuario = servicoUsuario.buscarPorId(usuarioLogadoId);
        List<UUID> idsPermitidos = new ArrayList<>();

        if (usuario.getClinica() != null) {
            List<Usuario> equipe = servicoUsuario.listarPorClinica(usuario.getClinica().getId());
            idsPermitidos = equipe.stream().map(Usuario::getId).collect(Collectors.toList());
        } else {
            idsPermitidos.add(usuarioLogadoId);
        }

        List<UUID> finalIdsPermitidos = idsPermitidos;
        Specification<Atendimento> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(root.get("veterinarioId").in(finalIdsPermitidos));

            if (status != null) predicates.add(cb.equal(root.get("status"), status));
            if (pacienteId != null) predicates.add(cb.equal(root.get("paciente").get("id"), pacienteId));
            if (dataInicio != null) predicates.add(cb.greaterThanOrEqualTo(root.get("dataHora"), dataInicio.atStartOfDay()));
            if (dataFim != null) predicates.add(cb.lessThanOrEqualTo(root.get("dataHora"), dataFim.atTime(LocalTime.MAX)));

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return repositorio.findAll(spec, Sort.by(Sort.Direction.ASC, "dataHora"));
    }

    public Atendimento criar(Atendimento novo, UUID pacienteId) {
        Paciente paciente = servicoPaciente.buscarPorId(pacienteId);

        if (!servicoUsuario.existePorId(novo.getVeterinarioId())) {
            throw new ExcecaoRegraNegocio("Veterinário responsável não encontrado.");
        }

        novo.setPaciente(paciente);
        if (novo.getStatus() == null) novo.setStatus(Atendimento.StatusAtendimento.AGENDADO);
        if (novo.getTitulo() == null) novo.setTitulo("Atendimento Clínico");
        if (novo.getDataHora() == null) novo.setDataHora(LocalDateTime.now());

        Atendimento salvo = repositorio.save(novo);

        try {
            Usuario vet = servicoUsuario.buscarPorId(novo.getVeterinarioId());
            if (vet.getClinica() != null) {
                servicoAuditoria.registrar(
                        vet.getClinica().getId(),
                        vet.getId(),
                        AcaoAuditoria.ATENDIMENTO_AGENDADO,
                        EntidadeAuditoria.ATENDIMENTO,
                        salvo.getId(),
                        "Agendamento criado: " + novo.getTitulo(),
                        helperAuditoria.montarMetadadosAtendimento(paciente.getNome(), "AGENDADO", vet.getId())
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return salvo;
    }

    public List<Atendimento> listarPorPaciente(UUID pacienteId) {
        if (!servicoPaciente.existePorId(pacienteId)) {
            throw new ExceptionRecursoNaoEncontrado("Paciente", pacienteId.toString());
        }
        return repositorio.findByPacienteIdOrderByCriadoEmDesc(pacienteId);
    }

    public Atendimento buscarPorId(UUID id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Atendimento", id.toString()));
    }

    public Atendimento atualizar(UUID id, Atendimento dados) {
        Atendimento existente = buscarPorId(id);
        existente.setTitulo(dados.getTitulo());
        existente.setQueixaPrincipal(dados.getQueixaPrincipal());
        existente.setHistoricoClinico(dados.getHistoricoClinico());
        existente.setExameFisico(dados.getExameFisico());
        existente.setDiagnostico(dados.getDiagnostico());
        existente.setCondutaClinica(dados.getCondutaClinica());
        existente.setObservacoes(dados.getObservacoes());
        existente.setProtocoloId(dados.getProtocoloId());
        existente.setOrientacoesManejo(dados.getOrientacoesManejo());

        if (dados.getStatus() != null) {
            existente.setStatus(dados.getStatus());
        }
        Atendimento salvo = repositorio.save(existente);

        try {
            Usuario vet = servicoUsuario.buscarPorId(existente.getVeterinarioId());
            if (vet != null && vet.getClinica() != null) {
                servicoAuditoria.registrar(
                        vet.getClinica().getId(),
                        vet.getId(),
                        AcaoAuditoria.PRONTUARIO_EDITADO,
                        EntidadeAuditoria.ATENDIMENTO,
                        salvo.getId(),
                        "Prontuário editado",
                        helperAuditoria.montarMetadados("campos", "dados_clinicos")
                );
            }
        } catch (Exception e) {}

        return salvo;
    }

    public Atendimento atualizarStatus(UUID id, Atendimento.StatusAtendimento novoStatus) {
        Atendimento a = buscarPorId(id);
        a.setStatus(novoStatus);
        Atendimento salvo = repositorio.save(a);

        try {
            Usuario vet = servicoUsuario.buscarPorId(salvo.getVeterinarioId());
            if (vet != null && vet.getClinica() != null) {
                AcaoAuditoria acao = novoStatus == Atendimento.StatusAtendimento.CANCELADO ?
                        AcaoAuditoria.ATENDIMENTO_CANCELADO : AcaoAuditoria.ATENDIMENTO_EDITADO;

                servicoAuditoria.registrar(
                        vet.getClinica().getId(),
                        vet.getId(),
                        acao,
                        EntidadeAuditoria.ATENDIMENTO,
                        salvo.getId(),
                        "Mudança de status para " + novoStatus,
                        null
                );
            }
        } catch (Exception e) {}

        return salvo;
    }

    public Atendimento finalizar(UUID id, Atendimento dadosClinicos) {
        Atendimento existente = buscarPorId(id);
        existente.setTitulo(dadosClinicos.getTitulo() != null ? dadosClinicos.getTitulo() : existente.getTitulo());
        existente.setQueixaPrincipal(dadosClinicos.getQueixaPrincipal());
        existente.setHistoricoClinico(dadosClinicos.getHistoricoClinico());
        existente.setExameFisico(dadosClinicos.getExameFisico());
        existente.setDiagnostico(dadosClinicos.getDiagnostico());
        existente.setCondutaClinica(dadosClinicos.getCondutaClinica());
        existente.setObservacoes(dadosClinicos.getObservacoes());
        existente.setProtocoloId(dadosClinicos.getProtocoloId());
        existente.setOrientacoesManejo(dadosClinicos.getOrientacoesManejo());

        existente.setStatus(Atendimento.StatusAtendimento.REALIZADO);
        Atendimento salvo = repositorio.save(existente);

        try {
            Usuario vet = servicoUsuario.buscarPorId(salvo.getVeterinarioId());
            if (vet != null && vet.getClinica() != null) {
                servicoAuditoria.registrar(
                        vet.getClinica().getId(),
                        vet.getId(),
                        AcaoAuditoria.ATENDIMENTO_FINALIZADO,
                        EntidadeAuditoria.ATENDIMENTO,
                        salvo.getId(),
                        "Atendimento finalizado",
                        null
                );
            }
        } catch (Exception e) {}

        return salvo;
    }
}
