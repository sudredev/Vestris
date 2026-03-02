package br.com.vestris.vaccination.application;

import br.com.vestris.medicalrecord.domain.model.Paciente;
import br.com.vestris.medicalrecord.domain.repository.RepositorioPaciente;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.vaccination.domain.model.AplicacaoVacina;
import br.com.vestris.vaccination.domain.repository.RepositorioAplicacaoVacina;
import br.com.vestris.vaccination.interfaces.dto.AplicacaoVacinaResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ServiceAplicacaoVacina {
    private final RepositorioAplicacaoVacina repositorio;
    private final RepositorioPaciente repoPaciente;
    private final ServiceVacinacao serviceVacinacao; // Valida se vacina existe

    public AplicacaoVacina registrar(AplicacaoVacina app, UUID pacienteId) {
        Paciente paciente = repoPaciente.findById(pacienteId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Paciente", pacienteId.toString()));

        // Valida Vacina no outro módulo
        if (!serviceVacinacao.existePorId(app.getVacinaId())) { // Precisa criar este método no ServiceVacinacao se não tiver
            throw new ExcecaoRegraNegocio("Vacina informada não existe no catálogo.");
        }

        app.setPaciente(paciente);
        return repositorio.save(app);
    }

    public List<AplicacaoVacina> listarPorPaciente(UUID pacienteId) {
        return repositorio.findByPacienteIdOrderByDataAplicacaoDesc(pacienteId);
    }

    public void deletar(UUID id) {
        repositorio.deleteById(id);
    }

    // --- MÉTODOS DO HUB ---

    public List<AplicacaoVacina> listarAtrasadas() {
        return repositorio.listarAtrasadas(LocalDate.now());
    }

    public List<AplicacaoVacina> listarPrevistas() {
        LocalDate hoje = LocalDate.now();
        LocalDate futuro = hoje.plusDays(30); // Próximos 30 dias
        return repositorio.listarPrevistas(hoje, futuro);
    }

    // Método auxiliar para calcular status (usado no Delegate)
    public String calcularStatus(AplicacaoVacina app) {
        if (app.getDataProximaDose() == null) return "CONCLUIDA"; // Dose única ou final
        if (app.getDataProximaDose().isBefore(LocalDate.now())) return "ATRASADA";
        return "EM_DIA";
    }
}
