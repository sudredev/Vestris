package br.com.vestris.species.application;

import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.species.domain.ModeloExameFisico;
import br.com.vestris.species.domain.repository.RepositorioModeloExame;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceModeloExame {
    private final RepositorioModeloExame repositorio;
    private final ServiceEspecie serviceEspecie; // Para validar se a espécie existe

    // --- BUSCAR ---
    public ModeloExameFisico buscarPorEspecie(UUID especieId) {
        // Valida se a espécie existe antes de buscar o modelo
        if (!serviceEspecie.existePorId(especieId)) {
            throw new ExceptionRecursoNaoEncontrado("Espécie", especieId.toString());
        }

        return repositorio.findByEspecieId(especieId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Modelo de Exame Físico para a espécie", especieId.toString()));
    }

    // --- CRIAR ---
    @Transactional
    public ModeloExameFisico criar(UUID especieId, String textoBase) {
        if (!serviceEspecie.existePorId(especieId)) {
            throw new ExceptionRecursoNaoEncontrado("Espécie", especieId.toString());
        }

        // Regra: Uma espécie só pode ter UM modelo ativo
        if (repositorio.findByEspecieId(especieId).isPresent()) {
            throw new ExcecaoRegraNegocio("Já existe um modelo de exame físico cadastrado para esta espécie. Use a atualização (PUT).");
        }

        ModeloExameFisico novo = new ModeloExameFisico();
        novo.setEspecieId(especieId);
        novo.setTextoBase(textoBase);

        return repositorio.save(novo);
    }

    // --- ATUALIZAR ---
    @Transactional
    public ModeloExameFisico atualizar(UUID especieId, String novoTextoBase) {
        ModeloExameFisico existente = buscarPorEspecie(especieId); // Já valida existência

        existente.setTextoBase(novoTextoBase);

        return repositorio.save(existente);
    }

    // --- DELETAR ---
    @Transactional
    public void deletar(UUID especieId) {
        ModeloExameFisico existente = buscarPorEspecie(especieId);
        repositorio.delete(existente);
    }

    // --- LISTAR TODOS ---
    public List<ModeloExameFisico> listarTodos() {
        return repositorio.findAll();
    }
}
