package br.com.vestris.medicalrecord.application;

import br.com.vestris.medicalrecord.domain.model.Paciente;
import br.com.vestris.medicalrecord.domain.repository.RepositorioPaciente;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.species.application.ServiceEspecie;
import br.com.vestris.user.application.ServiceUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServicePaciente {
    private final RepositorioPaciente repositorio;
    private final ServiceEspecie serviceEspecie;
    private final ServiceUsuario servicoUsuario;

    public Paciente criar(Paciente novo) {
        // 1. Validar Espécie
        if (!serviceEspecie.existePorId(novo.getEspecieId())) {
            throw new ExcecaoRegraNegocio("Espécie informada não existe.");
        }

        // 2. Validar Veterinário (Dono do registro)
        if (!servicoUsuario.existePorId(novo.getVeterinarioId())) {
            throw new ExcecaoRegraNegocio("Veterinário informado não encontrado.");
        }

        return repositorio.save(novo);
    }

    public List<Paciente> listarPorVeterinario(UUID veterinarioId) {
        // 1. Validação: O Veterinário existe?
        if (!servicoUsuario.existePorId(veterinarioId)) {
            // Isso vai gerar um 404 (Not Found) tratado, e não um 500
            throw new ExceptionRecursoNaoEncontrado("Veterinário", veterinarioId.toString());
        }

        // 2. Busca (O JPA retorna lista vazia [] automaticamente se não achar nada, não null)
        return repositorio.findByVeterinarioId(veterinarioId);
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

        return repositorio.save(existente);
    }

    public void deletar(UUID id) {
        if (!repositorio.existsById(id)) {
            throw new ExceptionRecursoNaoEncontrado("Paciente", id.toString());
        }
        try {
            repositorio.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ExcecaoRegraNegocio("Não é possível remover este paciente pois ele possui atendimentos registrados.");
        }
    }

    public boolean existePorId(UUID id) {
        return repositorio.existsById(id);
    }
}
