package br.com.vestris.vaccination.application;

import br.com.vestris.reference.application.ServiceReferencia;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.species.application.ServiceEspecie;
import br.com.vestris.vaccination.domain.model.ProtocoloVacinacao;
import br.com.vestris.vaccination.domain.model.Vacina;
import br.com.vestris.vaccination.domain.repository.RepositorioProtocoloVacinacao;
import br.com.vestris.vaccination.domain.repository.RepositorioVacina;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ServiceProtocoloVacinacao {
    private final RepositorioProtocoloVacinacao repoProtocolo;
    private final RepositorioVacina repoVacina;

    // Serviços Externos
    private final ServiceEspecie serviceEspecie;
    private final ServiceReferencia serviceReferencia;

    public ProtocoloVacinacao criar(ProtocoloVacinacao novo, UUID vacinaId) {
        // 1. Validar Vacina (Interno)
        Vacina vacina = repoVacina.findById(vacinaId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Vacina", vacinaId.toString()));

        // 2. Validar Espécie (Externo)
        if (!serviceEspecie.existePorId(novo.getEspecieId())) {
            throw new ExcecaoRegraNegocio("Espécie não encontrada.");
        }

        // 3. Validar Referência (Externo - OBRIGATÓRIO)
        if (!serviceReferencia.existePorId(novo.getReferenciaId())) {
            throw new ExcecaoRegraNegocio("Referência Bibliográfica/Legal é obrigatória e não foi encontrada. O sistema exige respaldo científico para vacinação.");
        }

        // 4. Validar Duplicidade
        if (repoProtocolo.existsByEspecieIdAndVacinaId(novo.getEspecieId(), vacinaId)) {
            throw new ExcecaoRegraNegocio("Esta vacina já consta no protocolo desta espécie.");
        }

        novo.setVacina(vacina);
        return repoProtocolo.save(novo);
    }

    public List<ProtocoloVacinacao> listarPorEspecie(UUID especieId) {
        // 1. Validar se a espécie existe antes de buscar
        if (!serviceEspecie.existePorId(especieId)) {
            throw new ExceptionRecursoNaoEncontrado("Espécie", especieId.toString());
        }

        List<ProtocoloVacinacao> protocolos = repoProtocolo.findByEspecieId(especieId);

        // 2. REGRA DE NEGÓCIO: Mensagem clara se não houver vacinas
        if (protocolos.isEmpty()) {
            throw new ExcecaoRegraNegocio(
                    "Não constam vacinas obrigatórias ou recomendadas para esta espécie na base de dados atual. " +
                            "Verifique a legislação local e sanitária."
            );
        }

        return protocolos;
    }

    public ProtocoloVacinacao buscarPorId(UUID id) {
        return repoProtocolo.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Protocolo Vacinal", id.toString()));
    }

    public ProtocoloVacinacao atualizar(UUID id, UUID especieId, UUID vacinaId, UUID refId,
                                        Integer idade, Integer reforco, boolean obrig, String obs) {

        ProtocoloVacinacao existente = buscarPorId(id);

        // Se mudar Espécie, valida
        if (!existente.getEspecieId().equals(especieId)) {
            if (!serviceEspecie.existePorId(especieId)) {
                throw new ExcecaoRegraNegocio("Nova espécie não encontrada.");
            }
            existente.setEspecieId(especieId);
        }

        // Se mudar Vacina, valida e checa duplicidade
        if (!existente.getVacina().getId().equals(vacinaId)) {
            // Valida Vacina
            Vacina novaVacina = repoVacina.findById(vacinaId)
                    .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Vacina", vacinaId.toString()));

            // Checa se já não existe essa vacina para essa espécie (evitar duplicidade)
            if (repoProtocolo.existsByEspecieIdAndVacinaId(especieId, vacinaId)) {
                throw new ExcecaoRegraNegocio("Esta vacina já existe no protocolo desta espécie.");
            }
            existente.setVacina(novaVacina);
        }

        // Se mudar Referência, valida
        if (!existente.getReferenciaId().equals(refId)) {
            if (!serviceReferencia.existePorId(refId)) {
                throw new ExcecaoRegraNegocio("Referência bibliográfica inválida.");
            }
            existente.setReferenciaId(refId);
        }

        existente.setIdadeMinimaDias(idade);
        existente.setDiasParaReforco(reforco);
        existente.setObrigatoria(obrig);
        existente.setObservacoes(obs);

        return repoProtocolo.save(existente);
    }

    public void deletar(UUID id) {
        if (!repoProtocolo.existsById(id)) {
            throw new ExceptionRecursoNaoEncontrado("Protocolo Vacinal", id.toString());
        }
        repoProtocolo.deleteById(id);
    }
}
