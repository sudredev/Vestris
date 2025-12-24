package br.com.vestris.clinical.application;

import br.com.vestris.clinical.domain.model.Doenca;
import br.com.vestris.clinical.domain.model.Dosagem;
import br.com.vestris.clinical.domain.model.Protocolo;
import br.com.vestris.clinical.domain.repository.RepositorioDoenca;
import br.com.vestris.clinical.domain.repository.RepositorioProtocolo;

import br.com.vestris.pharmacology.application.ServiceFarmacologia;
import br.com.vestris.reference.application.ServiceReferencia;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceProtocolo {
    private final RepositorioProtocolo repoProtocolo;
    private final RepositorioDoenca repoDoenca;


    // Serviços Externos
    private final ServiceReferencia serviceReferencia;
    private final ServiceFarmacologia serviceFarmacologia; // Descomente quando adicionar a dependência

    @Transactional
    public Protocolo criar(Protocolo protocolo, UUID doencaId, List<Dosagem> itens) {

        // 1. VALIDAÇÃO DA DOENÇA (Local)
        // Se a doença não existe, para tudo.
        Doenca doenca = repoDoenca.findById(doencaId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Doença", doencaId.toString()));

        // 2. VALIDAÇÃO DA REFERÊNCIA (Externo - Reference)
        if (!serviceReferencia.existePorId(protocolo.getReferenciaId())) {
            throw new ExcecaoRegraNegocio("A Referência Bibliográfica informada não existe.");
        }

        // 3. VALIDAÇÃO DOS MEDICAMENTOS (Externo - Pharmacology)
        // Varre a lista de dosagens e verifica um por um
        if (itens != null && !itens.isEmpty()) {
            for (Dosagem item : itens) {
                if (!serviceFarmacologia.existeMedicamentoPorId(item.getMedicamentoId())) {
                    throw new ExcecaoRegraNegocio("O Medicamento informado (ID: " + item.getMedicamentoId() + ") não existe.");
                }

                // Vincula o item ao protocolo (para o Hibernate salvar a FK corretamente)
                protocolo.adicionarDosagem(item);
            }
        } else {
            throw new ExcecaoRegraNegocio("O protocolo deve conter pelo menos uma dosagem.");
        }

        // Se chegou aqui, todos os IDs existem!
        protocolo.setDoenca(doenca);

        return repoProtocolo.save(protocolo);
    }

    public List<Protocolo> listarPorDoenca(UUID doencaId) {
        // 1. Validação: A doença existe?
        if (!repoDoenca.existsById(doencaId)) {
            throw new ExceptionRecursoNaoEncontrado("Doença", doencaId.toString());
        }

        // 2. Busca os protocolos
        List<Protocolo> protocolos = repoProtocolo.findByDoencaId(doencaId);

        // 3. NOVA VALIDAÇÃO: A lista está vazia?
        if (protocolos.isEmpty()) {
            // Aqui usamos ExcecaoRegraNegocio para mandar uma mensagem personalizada
            // O Status será 400 Bad Request (pelo seu Manipulador)
            throw new ExcecaoRegraNegocio("Ainda não há protocolos definidos para esta doença.");

            // DICA: Se você fizer questão que o erro seja 404 (Not Found),
            // você teria que alterar sua ExcecaoRecursoNaoEncontrado para aceitar mensagem livre,
            // mas ExcecaoRegraNegocio funciona super bem aqui para avisar o usuário.
        }

        return protocolos;
    }

    public Protocolo buscarPorId(UUID id) {
        return repoProtocolo.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Protocolo", id.toString()));
    }

    @Transactional
    public Protocolo atualizar(UUID id, Protocolo dadosNovos, List<Dosagem> novasDosagens) {
        Protocolo existente = buscarPorId(id);

        // 1. Atualiza dados básicos
        existente.setTitulo(dadosNovos.getTitulo());
        existente.setObservacoes(dadosNovos.getObservacoes());

        // Se mudou a referência, valida se a nova existe
        if (!existente.getReferenciaId().equals(dadosNovos.getReferenciaId())) {
            if (!serviceReferencia.existePorId(dadosNovos.getReferenciaId())) {
                throw new ExcecaoRegraNegocio("Nova Referência Bibliográfica não encontrada.");
            }
            existente.setReferenciaId(dadosNovos.getReferenciaId());
        }

        // 2. Atualiza Dosagens (Estratégia: Limpar e Adicionar Novas)
        // Como a Entidade Protocolo tem cascade=ALL e orphanRemoval=true,
        // o Hibernate vai deletar do banco as que sumirem da lista.

        existente.getDosagens().clear(); // Remove as antigas

        if (novasDosagens != null) {
            for (Dosagem d : novasDosagens) {
                // Valida medicamento
                // if (!serviceFarmacologia.existe...(d.getMedicamentoId())) ...

                existente.adicionarDosagem(d); // Adiciona as novas
            }
        }

        return repoProtocolo.save(existente);
    }

    public void deletar(UUID id) {
        if (!repoProtocolo.existsById(id)) {
            throw new ExceptionRecursoNaoEncontrado("Protocolo", id.toString());
        }
        // Protocolo geralmente pode ser deletado sem travar nada (a não ser que já tenha sido usado em Prontuário no futuro)
        repoProtocolo.deleteById(id);
    }
}
