package br.com.vestris.medicalrecord.application;

import br.com.vestris.medicalrecord.domain.model.Atendimento;
import br.com.vestris.medicalrecord.domain.model.Paciente;
import br.com.vestris.medicalrecord.domain.repository.RepositorioAtendimento;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.user.application.ServiceUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceAtendimento {
    private final RepositorioAtendimento repositorio;
    private final ServicePaciente servicoPaciente;
    private final ServiceUsuario servicoUsuario;

    public Atendimento criar(Atendimento novo, UUID pacienteId) {
        Paciente paciente = servicoPaciente.buscarPorId(pacienteId);

        if (!servicoUsuario.existePorId(novo.getVeterinarioId())) {
            throw new ExcecaoRegraNegocio("Veterinário responsável não encontrado.");
        }

        novo.setPaciente(paciente);
        return repositorio.save(novo);
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

    // --- AQUI ESTAVA O PROVÁVEL ERRO ---
    public Atendimento atualizar(UUID id, Atendimento dados) {
        Atendimento existente = buscarPorId(id);

        // Atualiza TODOS os campos textuais
        existente.setQueixaPrincipal(dados.getQueixaPrincipal());

        // CONFIRA SE ESTAS LINHAS ESTÃO LÁ:
        existente.setHistoricoClinico(dados.getHistoricoClinico());
        existente.setExameFisico(dados.getExameFisico());
        existente.setDiagnostico(dados.getDiagnostico());
        existente.setCondutaClinica(dados.getCondutaClinica());
        existente.setObservacoes(dados.getObservacoes());

        // Atualiza Protocolo (se mudou)
        existente.setProtocoloId(dados.getProtocoloId());

        return repositorio.save(existente);
    }
}
