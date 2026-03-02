## src\main\java\br\com\vestris\clinical\application

### ServiceCalculadora.java

```java
// src\main\java\br\com\vestris\clinical\application\ServiceCalculadora.java
package br.com.vestris.clinical.application;

import br.com.vestris.clinical.domain.model.CalculoResultadoDTO;
import br.com.vestris.clinical.domain.model.Dosagem;
import br.com.vestris.clinical.domain.model.Protocolo;
import br.com.vestris.pharmacology.application.ServiceDoseReferencia;
import br.com.vestris.pharmacology.application.ServiceFarmacologia;
import br.com.vestris.pharmacology.domain.model.DoseReferencia;
import br.com.vestris.pharmacology.domain.model.Medicamento;
import br.com.vestris.reference.application.ServiceReferencia;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.user.application.ServiceAuditoria;
import br.com.vestris.user.domain.model.AcaoAuditoria;
import br.com.vestris.user.domain.model.EntidadeAuditoria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ServiceCalculadora {
    private final ServiceProtocolo servicoProtocolo;
    private final ServiceFarmacologia servicoFarmacologia;
    private final ServiceReferencia servicoReferencia;
    private final ServiceDoseReferencia serviceDoseReferencia;
    private final ServiceAuditoria serviceAuditoria;

    // --- CÁLCULOS MATEMÁTICOS SIMPLES ---
    public CalculoResultadoDTO calcularMatematico(
            String nomeMedicamento, Double concentracao, Double pesoKg,
            Double doseInformada, String via, String frequencia, String duracao
    ) {
        CalculoResultadoDTO resultado = new CalculoResultadoDTO();
        resultado.setMedicamentoNome(nomeMedicamento != null ? nomeMedicamento : "Medicamento Manual");
        resultado.setPesoKg(pesoKg);
        resultado.setConcentracao(concentracao != null ? concentracao + " mg/ml" : "N/A");
        resultado.setVia(via);
        resultado.setFrequencia(frequencia);
        resultado.setDuracao(duracao);

        // Cálculo da Dose Total em MG
        Double doseTotalMg = (doseInformada != null && pesoKg != null) ? doseInformada * pesoKg : null;

        // Cálculo do Volume em ML
        Double volumeMl = null;
        if (doseTotalMg != null && concentracao != null && concentracao > 0) {
            volumeMl = doseTotalMg / concentracao;
        }

        // Popula DTO
        resultado.setDoseMinMg(arredondar(doseTotalMg)); // Nesse caso min=max
        resultado.setDoseMaxMg(arredondar(doseTotalMg));
        resultado.setVolMinMl(arredondar(volumeMl));
        resultado.setVolMaxMl(arredondar(volumeMl));

        resultado.setDoseCalculadaMg(arredondar(doseTotalMg));
        resultado.setVolumeCalculadoMl(arredondar(volumeMl));

        // Metadados
        resultado.setStatusSeguranca("NAO_VALIDADO");
        resultado.setMensagemSeguranca("Cálculo livre: responsabilidade do veterinário.");

        return resultado;
    }

    /**
     * Valida e calcula uma dose (Modo Híbrido: Catálogo ou Manual)
     */
    public CalculoResultadoDTO validarDose(
            UUID medicamentoId, UUID especieId, UUID doencaId, UUID clinicaId, UUID usuarioId,
            Double pesoKg, Double doseInformadaMgKg, String unidade, String via,
            Double concentracaoManual // <--- NOVO: Suporte a modo livre
    ) {
        CalculoResultadoDTO resultado = new CalculoResultadoDTO();
        resultado.setPesoKg(pesoKg);

        Double concentracaoValor = null;
        String nomeMedicamento = "Medicamento Manual";
        String textoConcentracao = null;

        // --- 1. RESOLVER MEDICAMENTO E CONCENTRAÇÃO ---

        // CENÁRIO A: MODO CATÁLOGO (Tem ID do medicamento)
        if (medicamentoId != null) {
            Medicamento med = servicoFarmacologia.buscarEntidadePorId(medicamentoId);
            nomeMedicamento = med.getNome();
            textoConcentracao = med.getConcentracao();
            concentracaoValor = extrairValorConcentracao(med.getConcentracao());
        }
        // CENÁRIO B: MODO MANUAL (Sem ID, usa o valor digitado)
        else if (concentracaoManual != null && concentracaoManual > 0) {
            concentracaoValor = concentracaoManual;
            textoConcentracao = concentracaoManual + " mg/ml";
        }

        resultado.setMedicamentoNome(nomeMedicamento);
        resultado.setConcentracao(textoConcentracao);

        // --- 2. CÁLCULO MATEMÁTICO (VOLUME) ---
        Double doseTotalMg = doseInformadaMgKg * pesoKg;
        Double volMl = null;

        if (concentracaoValor != null && concentracaoValor > 0) {
            volMl = doseTotalMg / concentracaoValor;
        }

        // Popula resultado matemático
        resultado.setDoseMinMg(arredondar(doseTotalMg));
        resultado.setDoseMaxMg(arredondar(doseTotalMg)); // É pontual
        resultado.setVolMinMl(arredondar(volMl));
        resultado.setVolMaxMl(arredondar(volMl));

        // --- 3. VALIDAÇÃO DE SEGURANÇA (APENAS MODO CATÁLOGO) ---

        // Se não tiver ID de medicamento e espécie, ou a unidade for estranha, não validamos segurança
        if (medicamentoId == null || especieId == null || !"MG_KG".equalsIgnoreCase(unidade)) {
            resultado.setStatusSeguranca(medicamentoId == null ? "SEM_REFERENCIA" : "NAO_VALIDADO");
            resultado.setMensagemSeguranca(medicamentoId == null
                    ? "Cálculo manual (sem validação de segurança)."
                    : "Validação indisponível para esta unidade/configuração.");
            return resultado;
        }

        // Busca Referência Científica no Banco
        DoseReferencia ref = serviceDoseReferencia.buscarMelhorReferencia(medicamentoId, especieId, doencaId, via, clinicaId);

        if (ref == null) {
            resultado.setStatusSeguranca("SEM_REFERENCIA");
            resultado.setMensagemSeguranca("Nenhuma referência encontrada para esta combinação.");
            return resultado;
        }

        // Comparação (Usando BigDecimal para precisão)
        BigDecimal doseUser = BigDecimal.valueOf(doseInformadaMgKg);
        String status = "SEGURO";
        String msg = "Dose dentro da faixa de referência.";

        if (ref.getDoseMin() != null && doseUser.compareTo(ref.getDoseMin()) < 0) {
            status = "SUBDOSE";
            msg = "A dose informada (" + doseInformadaMgKg + ") está ABAIXO do mínimo recomendado (" + ref.getDoseMin() + " mg/kg).";
        } else if (ref.getDoseMax() != null && doseUser.compareTo(ref.getDoseMax()) > 0) {
            status = "SUPERDOSE";
            msg = "A dose informada (" + doseInformadaMgKg + ") está ACIMA do máximo recomendado (" + ref.getDoseMax() + " mg/kg).";
        }

        // Preenche DTO de Retorno com dados de segurança
        resultado.setStatusSeguranca(status);
        resultado.setMensagemSeguranca(msg);
        resultado.setRefMin(ref.getDoseMin() != null ? ref.getDoseMin().doubleValue() : null);
        resultado.setRefMax(ref.getDoseMax() != null ? ref.getDoseMax().doubleValue() : null);
        resultado.setRefFonte(ref.getFonteBibliografica() + " (" + ref.getOrigem() + ")");

        // --- 4. AUDITORIA (Se houver risco e usuário identificado) ---
        if (usuarioId != null && ("SUBDOSE".equals(status) || "SUPERDOSE".equals(status))) {
            try {
                // Tenta gravar log de alerta
                // Nota: Usando 'RECURSO_CRIADO' ou similar caso o enum ALERTA_DOSE não tenha sido criado ainda.
                // Idealmente crie AcaoAuditoria.ALERTA_DOSE
                serviceAuditoria.registrar(
                        clinicaId, usuarioId,
                        AcaoAuditoria.RECURSO_CRIADO, // Ajuste para o Enum correto se tiver criado
                        EntidadeAuditoria.RECEITA, // Entidade contexto
                        medicamentoId,
                        "Alerta de " + status + " disparado: " + doseInformadaMgKg + " mg/kg"
                );
            } catch (Exception e) {
                // Log falhou, não trava o cálculo
            }
        }

        return resultado;
    }

    /**
     * Cálculo baseado em Protocolo (Legado/Padrão para a tela de Protocolos)
     */
    public CalculoResultadoDTO calcular(UUID protocoloId, UUID medicamentoId, Double pesoInput, String unidadePeso) {
        java.util.List<String> alertas = new java.util.ArrayList<>();
        Protocolo protocolo = servicoProtocolo.buscarPorId(protocoloId);

        Dosagem regra = protocolo.getDosagens().stream()
                .filter(d -> d.getMedicamentoId().equals(medicamentoId))
                .findFirst()
                .orElseThrow(() -> new ExcecaoRegraNegocio("Este medicamento não pertence ao protocolo selecionado."));

        Medicamento medicamento = servicoFarmacologia.buscarEntidadePorId(medicamentoId);

        String citacaoReferencia;
        if (protocolo.getReferenciaId() != null) {
            citacaoReferencia = servicoReferencia.buscarCitacaoPorId(protocolo.getReferenciaId());
        } else {
            citacaoReferencia = protocolo.getReferenciaTexto() != null ? protocolo.getReferenciaTexto() : "Referência não informada";
        }

        Double pesoKg = "G".equalsIgnoreCase(unidadePeso) ? pesoInput / 1000.0 : pesoInput;

        Double doseMinMg = regra.getDoseMinima() * pesoKg;
        Double doseMaxMg = (regra.getDoseMaxima() != null) ? regra.getDoseMaxima() * pesoKg : doseMinMg;

        Double volMinMl = null;
        Double volMaxMl = null;
        Double concentracaoValor = extrairValorConcentracao(medicamento.getConcentracao());

        if (concentracaoValor != null && concentracaoValor > 0) {
            volMinMl = doseMinMg / concentracaoValor;
            volMaxMl = doseMaxMg / concentracaoValor;
        }

        return CalculoResultadoDTO.builder()
                .protocoloTitulo(protocolo.getTitulo())
                .medicamentoNome(medicamento.getNome())
                .referencia(citacaoReferencia)
                .pesoKg(pesoKg)
                .doseMinMg(arredondar(doseMinMg))
                .doseMaxMg(arredondar(doseMaxMg))
                .volMinMl(arredondar(volMinMl))
                .volMaxMl(arredondar(volMaxMl))
                .concentracao(medicamento.getConcentracao())
                .frequencia(regra.getFrequencia())
                .via(regra.getVia())
                .duracao(regra.getDuracao())
                .alertas(alertas)
                .statusSeguranca("SEGURO") // Default p/ protocolo fixo
                .mensagemSeguranca("Conforme protocolo.")
                .build();
    }

    private Double arredondar(Double valor) {
        if (valor == null) return null;
        BigDecimal bd = BigDecimal.valueOf(valor);
        return bd.setScale(3, RoundingMode.HALF_UP).doubleValue();
    }

    private Double extrairValorConcentracao(String textoConcentracao) {
        if (textoConcentracao == null || textoConcentracao.isBlank()) return null;
        try {
            Matcher matcher = Pattern.compile("([0-9]+([.,][0-9]+)?)").matcher(textoConcentracao);
            if (matcher.find()) {
                String numeroStr = matcher.group(1).replace(",", ".");
                return Double.parseDouble(numeroStr);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}

```

### ServiceDoenca.java

```java
// src\main\java\br\com\vestris\clinical\application\ServiceDoenca.java
package br.com.vestris.clinical.application;

import br.com.vestris.clinical.domain.model.Doenca;
import br.com.vestris.clinical.domain.repository.RepositorioDoenca;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.species.application.ServiceEspecie;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceDoenca {

    private final RepositorioDoenca repositorio;
    private final ServiceEspecie serviceEspecie; // <--- INJEÇÃO DO MÓDULO VIZINHO

    public Doenca criar(Doenca novaDoenca) {
        // 1. VALIDAÇÃO DE INTEGRIDADE: A Espécie existe?
        if (!serviceEspecie.existePorId(novaDoenca.getEspecieId())) {
            throw new ExcecaoRegraNegocio("Não foi possível cadastrar a doença. A espécie informada não existe.");
        }

        // 2. Validação de Duplicidade
        if (repositorio.existsByNomeAndEspecieId(novaDoenca.getNome(), novaDoenca.getEspecieId())) {
            throw new ExcecaoRegraNegocio("Esta doença já está cadastrada para esta espécie.");
        }

        return repositorio.save(novaDoenca);
    }

    public List<Doenca> listarTodas() {
        return repositorio.findAll();
    }

    public List<Doenca> listarPorEspecie(UUID especieId) {
        if (!serviceEspecie.existePorId(especieId)) {
            throw new ExceptionRecursoNaoEncontrado("Espécie", especieId.toString());
        }
        // 2. Se existe, busca as doenças
        return repositorio.findAllByEspecieId(especieId);
    }

    public Doenca buscarPorId(UUID id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Doença", id.toString()));
    }

    public Doenca atualizar(UUID id, Doenca dados) {
        Doenca existente = buscarPorId(id);

        // Regra: Se mudar o nome, verificar duplicidade de novo
        if (!existente.getNome().equalsIgnoreCase(dados.getNome()) &&
                repositorio.existsByNomeAndEspecieId(dados.getNome(), existente.getEspecieId())) {
            throw new ExcecaoRegraNegocio("Já existe outra doença com este nome para esta espécie.");
        }

        existente.setNome(dados.getNome());
        existente.setNomeCientifico(dados.getNomeCientifico());
        existente.setSintomas(dados.getSintomas());
        // Nota: Geralmente não deixamos mudar a Espécie (especieId) de uma doença existente,
        // pois quebraria os protocolos. Se quiser permitir, tem que validar se a nova espécie existe.

        return repositorio.save(existente);
    }

    public void deletar(UUID id) {
        if (!repositorio.existsById(id)) {
            throw new ExceptionRecursoNaoEncontrado("Doença", id.toString());
        }
        try {
            repositorio.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ExcecaoRegraNegocio("Não é possível remover esta doença pois existem protocolos vinculados a ela.");
        }
    }
}

```

### ServiceProtocolo.java

```java
// src\main\java\br\com\vestris\clinical\application\ServiceProtocolo.java
package br.com.vestris.clinical.application;

import br.com.vestris.clinical.domain.model.Doenca;
import br.com.vestris.clinical.domain.model.Dosagem;
import br.com.vestris.clinical.domain.model.Protocolo;
import br.com.vestris.clinical.domain.model.ProtocoloCompletoDTO;
import br.com.vestris.clinical.domain.repository.RepositorioDoenca;
import br.com.vestris.clinical.domain.repository.RepositorioProtocolo;

import br.com.vestris.pharmacology.application.ServiceFarmacologia;
import br.com.vestris.reference.application.ServiceReferencia;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.shared.infrastructure.helper.HelperAuditoria;
import br.com.vestris.user.application.ServiceAuditoria;
import br.com.vestris.user.application.ServiceUsuario;
import br.com.vestris.user.domain.model.AcaoAuditoria;
import br.com.vestris.user.domain.model.EntidadeAuditoria;
import br.com.vestris.user.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceProtocolo {
    private final RepositorioProtocolo repoProtocolo;
    private final RepositorioDoenca repoDoenca;
    private final ServiceFarmacologia serviceFarmacologia;
    private final ServiceReferencia serviceReferencia;
    private final ServiceAuditoria servicoAuditoria;
    private final ServiceUsuario servicoUsuario;
    private final HelperAuditoria helperAuditoria;

    @Transactional
    public Protocolo criar(Protocolo protocolo, UUID doencaIdInput, List<Dosagem> itens) {

        // 1. Lógica Híbrida para Doença
        if (doencaIdInput != null) {
            Doenca doenca = repoDoenca.findById(doencaIdInput)
                    .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Doença", doencaIdInput.toString()));
            protocolo.setDoenca(doenca);
        } else if (protocolo.getDoencaTextoLivre() == null || protocolo.getDoencaTextoLivre().isBlank()) {
            throw new ExcecaoRegraNegocio("É necessário informar uma doença (selecione da lista ou digite o nome).");
        }

        // 2. Validação de Origem e Referência
        if (protocolo.getOrigem() == Protocolo.OrigemProtocolo.OFICIAL) {
            if (protocolo.getReferenciaId() == null) {
                throw new ExcecaoRegraNegocio("Protocolos oficiais exigem ID de referência.");
            }
            if (!serviceReferencia.existePorId(protocolo.getReferenciaId())) {
                throw new ExcecaoRegraNegocio("Referência ID inválida.");
            }
        } else {
            // Se for próprio, exige autor
            if (protocolo.getAutorId() == null) {
                throw new ExcecaoRegraNegocio("Protocolos próprios exigem um autor vinculado.");
            }
            // Se não tem nem ID nem Texto de referência
            if (protocolo.getReferenciaId() == null && (protocolo.getReferenciaTexto() == null || protocolo.getReferenciaTexto().isBlank())) {
                protocolo.setReferenciaTexto("Autoria Própria / Experiência Clínica"); // Fallback
            }
        }

        // 3. Processar Dosagens Híbridas
        if (itens != null && !itens.isEmpty()) {
            for (Dosagem item : itens) {
                if (item.getMedicamentoId() != null) {
                    // Se mandou ID, valida
                    if (!serviceFarmacologia.existeMedicamentoPorId(item.getMedicamentoId())) {
                        throw new ExcecaoRegraNegocio("Medicamento ID inválido: " + item.getMedicamentoId());
                    }
                } else if (item.getMedicamentoTextoLivre() == null || item.getMedicamentoTextoLivre().isBlank()) {
                    throw new ExcecaoRegraNegocio("Todo item da prescrição precisa de um medicamento (ID ou Nome).");
                }
                protocolo.adicionarDosagem(item);
            }
        } else {
            throw new ExcecaoRegraNegocio("Adicione pelo menos um item ao protocolo.");
        }

        Protocolo salvo = repoProtocolo.save(protocolo);

        // --- LOG DE AUDITORIA ---
        try {
            UUID clinicaId = null;
            if (protocolo.getOrigem() == Protocolo.OrigemProtocolo.PROPRIO ||
                protocolo.getOrigem() == Protocolo.OrigemProtocolo.INSTITUCIONAL) {
                Usuario autor = servicoUsuario.buscarPorId(protocolo.getAutorId());
                if (autor.getClinica() != null) {
                    clinicaId = autor.getClinica().getId();
                }
            }

            if (clinicaId != null) {
                var metadados = helperAuditoria.montarMetadadosProtocolo(
                    salvo.getTitulo(),
                    protocolo.getOrigem().name(),
                    protocolo.getAutorId(),
                    "doenca", (protocolo.getDoenca() != null ? protocolo.getDoenca().getNome() : protocolo.getDoencaTextoLivre()),
                    "totalDosagens", String.valueOf(salvo.getDosagens().size())
                );
                servicoAuditoria.registrar(
                    clinicaId,
                    protocolo.getAutorId(),
                    AcaoAuditoria.PROTOCOLO_CRIADO,
                    EntidadeAuditoria.PROTOCOLO,
                    salvo.getId(),
                    "Protocolo criado: " + salvo.getTitulo(),
                    metadados
                );
            }
        } catch (Exception e) {
            System.err.println("Erro ao auditar criação de protocolo: " + e.getMessage());
        }
        // -------------------------

        return salvo;
    }

    public List<Protocolo> listarPorDoenca(UUID doencaId) {
        // Se vier ID, busca exato.
        // TODO: Futuramente implementar busca por texto livre da doença também
        if (!repoDoenca.existsById(doencaId)) {
            throw new ExceptionRecursoNaoEncontrado("Doença", doencaId.toString());
        }
        return repoProtocolo.findByDoencaId(doencaId);
    }

    // Lista TODOS (para o filtro "TODAS" funcionar no front, se desejar)
    public List<Protocolo> listarTodos() {
        return repoProtocolo.findAll();
    }

    public Protocolo buscarPorId(UUID id) {
        return repoProtocolo.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Protocolo", id.toString()));
    }

    public ProtocoloCompletoDTO montarProtocoloCompleto(UUID especieId, UUID doencaId) {
        Doenca doenca = repoDoenca.findById(doencaId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Doença", doencaId.toString()));

        if (!doenca.getEspecieId().equals(especieId)) {
            throw new ExcecaoRegraNegocio("Doença não pertence à espécie.");
        }

        List<Protocolo> protocolos = repoProtocolo.findByDoencaId(doencaId);

        return ProtocoloCompletoDTO.builder()
                .doenca(doenca)
                .protocolos(protocolos)
                .build();
    }

    public List<Protocolo> listarAcessiveis(UUID doencaId, UUID clinicaId, UUID usuarioId) {
        if (!repoDoenca.existsById(doencaId)) {
            throw new ExceptionRecursoNaoEncontrado("Doença", doencaId.toString());
        }

        // Se não vier clinicaId ou usuarioId (ex: acesso público ou erro),
        // a query JPQL lida com null, mas idealmente deveríamos garantir que não venham nulos do controller.
        // No caso de null, a comparação (p.clinicaId = null) retornaria falso no SQL padrão para valores preenchidos.

        return repoProtocolo.listarAcessiveis(doencaId, clinicaId, usuarioId);
    }

    @Transactional
    public Protocolo atualizar(UUID id, Protocolo dados, List<Dosagem> novasDosagens) {
        Protocolo existente = buscarPorId(id);

        existente.setTitulo(dados.getTitulo());
        existente.setObservacoes(dados.getObservacoes());
        existente.setReferenciaTexto(dados.getReferenciaTexto());
        existente.setReferenciaId(dados.getReferenciaId());

        // Atualiza campos de doença se necessário (geralmente não muda, mas ok)
        if(dados.getDoenca() != null) existente.setDoenca(dados.getDoenca());
        if(dados.getDoencaTextoLivre() != null) existente.setDoencaTextoLivre(dados.getDoencaTextoLivre());

        existente.getDosagens().clear();
        if (novasDosagens != null) {
            for (Dosagem d : novasDosagens) {
                // Mesma validação híbrida
                if (d.getMedicamentoId() != null) {
                    if (!serviceFarmacologia.existeMedicamentoPorId(d.getMedicamentoId())) {
                        throw new ExcecaoRegraNegocio("Medicamento inválido na edição.");
                    }
                }
                existente.adicionarDosagem(d);
            }
        }
        Protocolo salvo = repoProtocolo.save(existente);

        // --- LOG DE AUDITORIA ---
        try {
            UUID clinicaId = null;
            if (existente.getOrigem() == Protocolo.OrigemProtocolo.PROPRIO ||
                existente.getOrigem() == Protocolo.OrigemProtocolo.INSTITUCIONAL) {
                Usuario autor = servicoUsuario.buscarPorId(existente.getAutorId());
                if (autor.getClinica() != null) {
                    clinicaId = autor.getClinica().getId();
                }
            }

            if (clinicaId != null) {
                var metadados = helperAuditoria.montarMetadadosProtocolo(
                    salvo.getTitulo(),
                    existente.getOrigem().name(),
                    existente.getAutorId(),
                    "totalDosagens", String.valueOf(salvo.getDosagens().size())
                );
                servicoAuditoria.registrar(
                    clinicaId,
                    existente.getAutorId(),
                    AcaoAuditoria.PROTOCOLO_EDITADO,
                    EntidadeAuditoria.PROTOCOLO,
                    salvo.getId(),
                    "Protocolo atualizado: " + salvo.getTitulo(),
                    metadados
                );
            }
        } catch (Exception e) {
            System.err.println("Erro ao auditar edição de protocolo: " + e.getMessage());
        }
        // -------------------------

        return salvo;
    }

    public void deletar(UUID id) {
        if (!repoProtocolo.existsById(id)) {
            throw new ExceptionRecursoNaoEncontrado("Protocolo", id.toString());
        }

        Protocolo protocolo = buscarPorId(id);

        try {
            repoProtocolo.deleteById(id);

            // --- LOG DE AUDITORIA ---
            try {
                UUID clinicaId = null;
                if (protocolo.getOrigem() == Protocolo.OrigemProtocolo.PROPRIO ||
                    protocolo.getOrigem() == Protocolo.OrigemProtocolo.INSTITUCIONAL) {
                    Usuario autor = servicoUsuario.buscarPorId(protocolo.getAutorId());
                    if (autor.getClinica() != null) {
                        clinicaId = autor.getClinica().getId();
                    }
                }

                if (clinicaId != null) {
                    var metadados = helperAuditoria.montarMetadadosProtocolo(
                        protocolo.getTitulo(),
                        protocolo.getOrigem().name(),
                        protocolo.getAutorId()
                    );
                    servicoAuditoria.registrar(
                        clinicaId,
                        protocolo.getAutorId(),
                        AcaoAuditoria.PROTOCOLO_REMOVIDO,
                        EntidadeAuditoria.PROTOCOLO,
                        id,
                        "Protocolo deletado: " + protocolo.getTitulo(),
                        metadados
                    );
                }
            } catch (Exception e) {
                System.err.println("Erro ao auditar deleção de protocolo: " + e.getMessage());
            }
            // -------------------------
        } catch (DataIntegrityViolationException e) {
            throw new ExcecaoRegraNegocio("Protocolo em uso.");
        }
    }
}

```

