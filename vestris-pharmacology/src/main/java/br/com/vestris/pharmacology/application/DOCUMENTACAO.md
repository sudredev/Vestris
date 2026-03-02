## src\main\java\br\com\vestris\pharmacology\application

### ServiceContraindicacao.java

```java
// src\main\java\br\com\vestris\pharmacology\application\ServiceContraindicacao.java
package br.com.vestris.pharmacology.application;

import br.com.vestris.pharmacology.domain.model.Contraindicacao;
import br.com.vestris.pharmacology.domain.model.Medicamento;
import br.com.vestris.pharmacology.domain.model.PrincipioAtivo;
import br.com.vestris.pharmacology.domain.repository.RepositorioContraindicacao;
import br.com.vestris.pharmacology.domain.repository.RepositorioMedicamento;
import br.com.vestris.pharmacology.domain.repository.RepositorioPrincipioAtivo;
import br.com.vestris.reference.application.ServiceReferencia;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.species.application.ServiceEspecie;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceContraindicacao {
    private final RepositorioContraindicacao repoContraindicacao;
    private final RepositorioMedicamento repoMedicamento;
    private final RepositorioPrincipioAtivo repoPrincipio;
    private final ServiceEspecie serviceEspecie;

    @Transactional
    public Contraindicacao criar(UUID medicamentoId, UUID principioAtivoId, UUID especieId, String referenciaTexto,
                                 Contraindicacao.Gravidade gravidade, String descricao) {

        PrincipioAtivo principio = null;

        // 1. Tenta pelo Princípio Ativo (Prioridade para o Admin)
        if (principioAtivoId != null) {
            principio = repoPrincipio.findById(principioAtivoId)
                    .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Princípio Ativo", principioAtivoId.toString()));
        }
        // 2. Fallback: Tenta pelo Medicamento (Legado)
        else if (medicamentoId != null) {
            Medicamento med = repoMedicamento.findById(medicamentoId)
                    .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Medicamento", medicamentoId.toString()));
            principio = med.getPrincipioAtivo();
        } else {
            throw new ExcecaoRegraNegocio("Informe o Medicamento ou o Princípio Ativo.");
        }

        // 2. Validar Espécie
        if (!serviceEspecie.existePorId(especieId)) {
            throw new ExcecaoRegraNegocio("Espécie não encontrada.");
        }

        // 3. Validar Duplicidade
        if (repoContraindicacao.existsByPrincipioAtivoIdAndEspecieId(principio.getId(), especieId)) {
            throw new ExcecaoRegraNegocio("Já existe uma contraindicação deste princípio ativo para esta espécie.");
        }

        Contraindicacao nova = new Contraindicacao();
        nova.setPrincipioAtivo(principio);
        nova.setEspecieId(especieId);
        nova.setReferenciaTexto(referenciaTexto);
        nova.setGravidade(gravidade);
        nova.setDescricao(descricao);

        return repoContraindicacao.save(nova);
    }

    public List<Contraindicacao> listarPorMedicamento(UUID medicamentoId) {
        Medicamento med = repoMedicamento.findById(medicamentoId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Medicamento", medicamentoId.toString()));

        // Busca contraindicações ligadas ao princípio ativo deste medicamento
        return repoContraindicacao.findByPrincipioAtivoId(med.getPrincipioAtivo().getId());
    }

    public Contraindicacao buscarPorId(UUID id) {
        return repoContraindicacao.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Contraindicação", id.toString()));
    }

    @Transactional
    public Contraindicacao atualizar(UUID id, UUID novoEspecieId, String novaReferencia,
                                     Contraindicacao.Gravidade novaGravidade, String novaDescricao) {

        Contraindicacao existente = buscarPorId(id);

        // 1. Se mudou a Espécie
        if (!existente.getEspecieId().equals(novoEspecieId)) {
            if (!serviceEspecie.existePorId(novoEspecieId)) {
                throw new ExcecaoRegraNegocio("A nova Espécie informada não existe.");
            }
            if (repoContraindicacao.existsByPrincipioAtivoIdAndEspecieId(existente.getPrincipioAtivo().getId(), novoEspecieId)) {
                throw new ExcecaoRegraNegocio("Já existe uma contraindicação para a nova espécie selecionada.");
            }
            existente.setEspecieId(novoEspecieId);
        }

        // 2. Atualiza dados simples
        existente.setReferenciaTexto(novaReferencia);
        existente.setGravidade(novaGravidade);
        existente.setDescricao(novaDescricao);

        return repoContraindicacao.save(existente);
    }

    public void deletar(UUID id) {
        if (!repoContraindicacao.existsById(id)) {
            throw new ExceptionRecursoNaoEncontrado("Contraindicação", id.toString());
        }
        repoContraindicacao.deleteById(id);
    }
}

```

### ServiceDoseReferencia.java

```java
// src\main\java\br\com\vestris\pharmacology\application\ServiceDoseReferencia.java
package br.com.vestris.pharmacology.application;

import br.com.vestris.pharmacology.domain.model.DoseReferencia;
import br.com.vestris.pharmacology.domain.model.enums.ViaAdministracao;
import br.com.vestris.pharmacology.domain.repository.RepositorioDoseReferencia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ServiceDoseReferencia {
    private final RepositorioDoseReferencia repositorio;

    public DoseReferencia buscarMelhorReferencia(UUID medicamentoId, UUID especieId, UUID doencaId, String viaString, UUID clinicaId) {

        // 1. Resolve o Enum da Via de forma segura
        ViaAdministracao viaAlvo = null;
        if (viaString != null && !viaString.isBlank()) {
            try {
                // Remove espaços e converte para maiúsculo (ex: "Oral " -> "ORAL")
                viaAlvo = ViaAdministracao.valueOf(viaString.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                // Se o usuário digitou algo não mapeado, ignoramos a via na busca específica
                System.out.println("Via ignorada na validação (não mapeada): " + viaString);
            }
        }

        // 2. Busca TUDO do banco para este remédio (Query simples, sem erro de SQL)
        List<DoseReferencia> referencias = repositorio.findByMedicamentoId(medicamentoId);

        if (referencias.isEmpty()) return null;

        // 3. Filtra e Pontua em Memória (Java)
        ViaAdministracao finalViaAlvo = viaAlvo;

        return referencias.stream()
                // --- FILTROS DE ELEGIBILIDADE (Match ou Genérico) ---
                .filter(ref -> {
                    // Clínica: Ou é oficial (null) ou é da minha clínica
                    boolean clinicaMatch = ref.getClinicaId() == null || (clinicaId != null && ref.getClinicaId().equals(clinicaId));

                    // Espécie: Ou é genérica (null) ou é a minha espécie exata
                    boolean especieMatch = ref.getEspecieId() == null || (especieId != null && ref.getEspecieId().equals(especieId));

                    // Doença: Ou é genérica (null) ou é a minha doença exata
                    boolean doencaMatch = ref.getDoencaId() == null || (doencaId != null && ref.getDoencaId().equals(doencaId));

                    // Via: Ou é genérica (null) ou é a via que eu pedi
                    boolean viaMatch = ref.getVia() == null || (finalViaAlvo != null && ref.getVia() == finalViaAlvo);

                    return clinicaMatch && especieMatch && doencaMatch && viaMatch;
                })
                // --- ORDENAÇÃO POR SCORE (O Melhor Match vence) ---
                .max(Comparator.comparingInt(ref -> calcularScore(ref, especieId, doencaId, finalViaAlvo, clinicaId)))
                .orElse(null);
    }

    private int calcularScore(DoseReferencia ref, UUID especieId, UUID doencaId, ViaAdministracao viaAlvo, UUID clinicaId) {
        int score = 0;

        // Doença bateu exato? (Peso Máximo)
        if (ref.getDoencaId() != null && ref.getDoencaId().equals(doencaId)) score += 1000;

        // Espécie bateu exato? (Peso Alto)
        if (ref.getEspecieId() != null && ref.getEspecieId().equals(especieId)) score += 100;

        // Via bateu exata? (Peso Médio)
        if (ref.getVia() != null && ref.getVia() == viaAlvo) score += 10;

        // É Institucional? (Desempate - Prefere regra da casa sobre oficial se for específica igual)
        if (ref.getClinicaId() != null && ref.getClinicaId().equals(clinicaId)) score += 1;

        return score;
    }
}

```

### ServiceFarmacologia.java

```java
// src\main\java\br\com\vestris\pharmacology\application\ServiceFarmacologia.java
package br.com.vestris.pharmacology.application;

import br.com.vestris.pharmacology.domain.model.Medicamento;
import br.com.vestris.pharmacology.domain.model.PrincipioAtivo;
import br.com.vestris.pharmacology.domain.repository.RepositorioMedicamento;
import br.com.vestris.pharmacology.domain.repository.RepositorioPrincipioAtivo;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceFarmacologia {
    private final RepositorioPrincipioAtivo repoPrincipio;
    private final RepositorioMedicamento repoMedicamento;


    // PRINCÍPIOS ATIVOS
    public PrincipioAtivo criarPrincipio(PrincipioAtivo novo) {
        if (repoPrincipio.existsByNome(novo.getNome())) {
            throw new ExcecaoRegraNegocio("Princípio ativo já cadastrado: " + novo.getNome());
        }
        return repoPrincipio.save(novo);
    }

    public List<PrincipioAtivo> listarPrincipios() {
        return repoPrincipio.findAll();
    }

    public PrincipioAtivo buscarPrincipioPorId(UUID id) {
        return repoPrincipio.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Princípio Ativo", id.toString()));
    }

    public PrincipioAtivo atualizarPrincipio(UUID id, PrincipioAtivo dados) {
        PrincipioAtivo existente = buscarPrincipioPorId(id);

        // Se mudar o nome, verifica duplicidade
        if (!existente.getNome().equalsIgnoreCase(dados.getNome()) && repoPrincipio.existsByNome(dados.getNome())) {
            throw new ExcecaoRegraNegocio("Já existe outro princípio ativo com este nome.");
        }

        existente.setNome(dados.getNome());
        existente.setDescricao(dados.getDescricao());
        existente.setGrupoFarmacologico(dados.getGrupoFarmacologico());

        return repoPrincipio.save(existente);
    }

    public void deletarPrincipio(UUID id) {
        if (!repoPrincipio.existsById(id)) {
            throw new ExceptionRecursoNaoEncontrado("Princípio Ativo", id.toString());
        }
        try {
            repoPrincipio.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            // O banco travou porque tem medicamento usando este princípio
            throw new ExcecaoRegraNegocio("Não é possível remover este princípio ativo pois existem medicamentos vinculados a ele.");
        }
    }


    // MEDICAMENTOS

    public Medicamento criarMedicamento(Medicamento novo, UUID principioAtivoId) {
        PrincipioAtivo pa = buscarPrincipioPorId(principioAtivoId);
        novo.setPrincipioAtivo(pa);
        return repoMedicamento.save(novo);
    }

    public List<Medicamento> listarMedicamentos() {
        return repoMedicamento.findAll();
    }

    public Medicamento buscarMedicamentoPorId(UUID id) {
        return repoMedicamento.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Medicamento", id.toString()));
    }

    public Medicamento atualizarMedicamento(UUID id, Medicamento dados, UUID novoPrincipioId) {
        Medicamento existente = buscarMedicamentoPorId(id);

        // Se o ID do princípio ativo mudou, buscamos o novo
        if (!existente.getPrincipioAtivo().getId().equals(novoPrincipioId)) {
            PrincipioAtivo novoPa = buscarPrincipioPorId(novoPrincipioId);
            existente.setPrincipioAtivo(novoPa);
        }

        existente.setNome(dados.getNome());
        existente.setConcentracao(dados.getConcentracao());
        existente.setFabricante(dados.getFabricante());
        existente.setFormaFarmaceutica(dados.getFormaFarmaceutica());

        return repoMedicamento.save(existente);
    }

    public void deletarMedicamento(UUID id) {
        if (!repoMedicamento.existsById(id)) {
            throw new ExceptionRecursoNaoEncontrado("Medicamento", id.toString());
        }
        try {
            repoMedicamento.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            // O banco travou porque tem Contraindicação ou Protocolo (em outro módulo se houver FK real, mas aqui é provavel Contraindicação)
            throw new ExcecaoRegraNegocio("Não é possível remover este medicamento pois ele está sendo usado em contraindicações ou protocolos.");
        }
    }

    // Método auxiliar para outros módulos
    public boolean existeMedicamentoPorId(UUID id) {
        return repoMedicamento.existsById(id);
    }

    public Medicamento buscarEntidadePorId(UUID id) {
        return buscarMedicamentoPorId(id); // Reusa o método que já lança exceção se não achar
    }
}

```

### ServiceSegurancaFarmacologica.java

```java
// src\main\java\br\com\vestris\pharmacology\application\ServiceSegurancaFarmacologica.java
package br.com.vestris.pharmacology.application;

import br.com.vestris.pharmacology.domain.model.Contraindicacao;
import br.com.vestris.pharmacology.domain.model.Medicamento;
import br.com.vestris.pharmacology.domain.repository.RepositorioContraindicacao;
import br.com.vestris.pharmacology.domain.repository.RepositorioMedicamento;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceSegurancaFarmacologica {
    private final RepositorioContraindicacao repoContra;
    private final RepositorioMedicamento repoMedicamento;

    public List<Contraindicacao> validarMedicamentoParaEspecie(UUID medicamentoId, UUID especieId) {
        // 1. Achar o medicamento
        Medicamento med = repoMedicamento.findById(medicamentoId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Medicamento", medicamentoId.toString()));

        // 2. Descobrir o Princípio Ativo (A regra é ligada à molécula)
        if (med.getPrincipioAtivo() == null) {
            return List.of(); // Sem princípio cadastrado, sem validação automática
        }

        UUID principioId = med.getPrincipioAtivo().getId();

        // 3. Buscar riscos no banco
        return repoContra.encontrarRiscos(principioId, especieId);
    }
}

```

