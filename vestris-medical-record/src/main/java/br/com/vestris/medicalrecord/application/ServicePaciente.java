package br.com.vestris.medicalrecord.application;

import br.com.vestris.medicalrecord.domain.model.Paciente;
import br.com.vestris.medicalrecord.domain.repository.RepositorioPaciente;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.shared.infrastructure.helper.HelperAuditoria;
import br.com.vestris.species.application.ServiceEspecie;
import br.com.vestris.user.application.ServiceAuditoria;
import br.com.vestris.user.application.ServiceUsuario;
import br.com.vestris.user.domain.model.AcaoAuditoria;
import br.com.vestris.user.domain.model.EntidadeAuditoria;
import br.com.vestris.user.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServicePaciente {
    private final RepositorioPaciente repositorio;
    private final ServiceEspecie serviceEspecie;
    private final ServiceUsuario servicoUsuario;
    private final ServiceAuditoria servicoAuditoria;
    private final HelperAuditoria helperAuditoria;

    public Paciente criar(Paciente novo) {
        if (!serviceEspecie.existePorId(novo.getEspecieId())) {
            throw new ExcecaoRegraNegocio("Espécie informada não existe.");
        }
        if (!servicoUsuario.existePorId(novo.getVeterinarioId())) {
            throw new ExcecaoRegraNegocio("Veterinário informado não encontrado.");
        }

        Paciente salvo = repositorio.save(novo);

        // --- LOG DE AUDITORIA ---
        try {
            Usuario vet = servicoUsuario.buscarPorId(novo.getVeterinarioId());
            if (vet != null && vet.getClinica() != null) {
                var metadados = helperAuditoria.montarMetadadosPaciente(
                        salvo.getNome(),
                        "Espécie ID: " + salvo.getEspecieId(),
                        salvo.getDadosTutor()
                );
                servicoAuditoria.registrar(
                        vet.getClinica().getId(),
                        vet.getId(),
                        AcaoAuditoria.PACIENTE_CRIADO,
                        EntidadeAuditoria.PACIENTE,
                        salvo.getId(),
                        "Novo paciente criado: " + salvo.getNome(),
                        metadados
                );
            }
        } catch (Exception e) {
            System.err.println("Erro ao auditar criação de paciente: " + e.getMessage());
        }

        return salvo;
    }

    public List<Paciente> listarPorVeterinario(UUID usuarioLogadoId) {
        Usuario usuario = servicoUsuario.buscarPorId(usuarioLogadoId);
        if (usuario.getClinica() != null) {
            List<Usuario> equipe = servicoUsuario.listarPorClinica(usuario.getClinica().getId());
            List<UUID> idsEquipe = equipe.stream().map(Usuario::getId).collect(Collectors.toList());
            return repositorio.findByVeterinarioIdIn(idsEquipe);
        }
        return repositorio.findByVeterinarioId(usuarioLogadoId);
    }

    public Paciente buscarPorId(UUID id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Paciente", id.toString()));
    }

    public Paciente atualizar(UUID id, Paciente dados) {
        Paciente existente = buscarPorId(id);
        existente.setNome(dados.getNome());
        existente.setDadosTutor(dados.getDadosTutor());
        existente.setIdentificacaoAnimal(dados.getIdentificacaoAnimal());
        existente.setSexo(dados.getSexo());
        existente.setDataNascimento(dados.getDataNascimento());
        existente.setPesoAtualGramas(dados.getPesoAtualGramas());
        existente.setPelagemCor(dados.getPelagemCor());
        existente.setMicrochip(dados.getMicrochip());

        Paciente salvo = repositorio.save(existente);

        try {
            Usuario vet = servicoUsuario.buscarPorId(existente.getVeterinarioId());
            if (vet != null && vet.getClinica() != null) {
                var metadados = helperAuditoria.montarMetadadosPaciente(
                        salvo.getNome(),
                        "Espécie ID: " + salvo.getEspecieId(),
                        salvo.getDadosTutor()
                );
                servicoAuditoria.registrar(
                        vet.getClinica().getId(),
                        vet.getId(),
                        AcaoAuditoria.PACIENTE_EDITADO,
                        EntidadeAuditoria.PACIENTE,
                        salvo.getId(),
                        "Dados do paciente atualizados",
                        metadados
                );
            }
        } catch (Exception e) {}

        return salvo;
    }

    public void deletar(UUID id) {
        if (!repositorio.existsById(id)) {
            throw new ExceptionRecursoNaoEncontrado("Paciente", id.toString());
        }

        Paciente paciente = buscarPorId(id);

        try {
            repositorio.deleteById(id);

            try {
                Usuario vet = servicoUsuario.buscarPorId(paciente.getVeterinarioId());
                if (vet != null && vet.getClinica() != null) {
                    var metadados = helperAuditoria.montarMetadadosPaciente(
                            paciente.getNome(),
                            "Espécie ID: " + paciente.getEspecieId(),
                            paciente.getDadosTutor()
                    );
                    servicoAuditoria.registrar(
                            vet.getClinica().getId(),
                            vet.getId(),
                            AcaoAuditoria.PACIENTE_CANCELADO,
                            EntidadeAuditoria.PACIENTE,
                            id,
                            "Paciente deletado: " + paciente.getNome(),
                            metadados
                    );
                }
            } catch (Exception e) {}

        } catch (DataIntegrityViolationException e) {
            throw new ExcecaoRegraNegocio("Não é possível remover este paciente pois ele possui atendimentos registrados.");
        }
    }

    public boolean existePorId(UUID id) {
        return repositorio.existsById(id);
    }
}
