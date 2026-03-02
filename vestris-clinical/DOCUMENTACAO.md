# Documentação do projeto vestris-clinical

## Índice de Diretórios e Arquivos

- [.](#)
  - [.openapi-generator-ignore](#openapi-generator-ignore)
  - [pom.xml](#pomxml)
- [src\main\java\br\com\vestris\clinical\application](#srcmainjavabrcomvestrisclinicalapplication)
  - [ServiceCalculadora.java](#srcmainjavabrcomvestrisclinicalapplicationservicecalculadorajava)
  - [ServiceDoenca.java](#srcmainjavabrcomvestrisclinicalapplicationservicedoencajava)
  - [ServiceProtocolo.java](#srcmainjavabrcomvestrisclinicalapplicationserviceprotocolojava)
- [src\main\java\br\com\vestris\clinical\domain\model](#srcmainjavabrcomvestrisclinicaldomainmodel)
  - [CalculoResultadoDTO.java](#srcmainjavabrcomvestrisclinicaldomainmodelcalculoresultadodtojava)
  - [Doenca.java](#srcmainjavabrcomvestrisclinicaldomainmodeldoencajava)
  - [Dosagem.java](#srcmainjavabrcomvestrisclinicaldomainmodeldosagemjava)
  - [Protocolo.java](#srcmainjavabrcomvestrisclinicaldomainmodelprotocolojava)
  - [ProtocoloCompletoDTO.java](#srcmainjavabrcomvestrisclinicaldomainmodelprotocolocompletodtojava)
- [src\main\java\br\com\vestris\clinical\domain\repository](#srcmainjavabrcomvestrisclinicaldomainrepository)
  - [RepositorioDoenca.java](#srcmainjavabrcomvestrisclinicaldomainrepositoryrepositoriodoencajava)
  - [RepositorioProtocolo.java](#srcmainjavabrcomvestrisclinicaldomainrepositoryrepositorioprotocolojava)
- [src\main\java\br\com\vestris\clinical\interfaces\delegate](#srcmainjavabrcomvestrisclinicalinterfacesdelegate)
  - [ApiDelegateCalculadora.java](#srcmainjavabrcomvestrisclinicalinterfacesdelegateapidelegatecalculadorajava)
  - [ApiDelegateDoencas.java](#srcmainjavabrcomvestrisclinicalinterfacesdelegateapidelegatedoencasjava)
  - [ApiDelegateProtocolos.java](#srcmainjavabrcomvestrisclinicalinterfacesdelegateapidelegateprotocolosjava)
- [src\main\java\br\com\vestris\clinical\interfaces\dto](#srcmainjavabrcomvestrisclinicalinterfacesdto)
  - [CalculoCatalogoRequest.java](#srcmainjavabrcomvestrisclinicalinterfacesdtocalculocatalogorequestjava)
  - [CalculoLivreRequest.java](#srcmainjavabrcomvestrisclinicalinterfacesdtocalculolivrerequestjava)
- [src\main\resources\swagger](#srcmainresourcesswagger)
  - [openapi.yml](#srcmainresourcesswaggeropenapiyml)
- [src\main\resources\swagger\components](#srcmainresourcesswaggercomponents)
  - [schemas.yml](#srcmainresourcesswaggercomponentsschemasyml)
- [src\main\resources\swagger\paths](#srcmainresourcesswaggerpaths)
  - [calculadora.yml](#srcmainresourcesswaggerpathscalculadorayml)
  - [doencas.yml](#srcmainresourcesswaggerpathsdoencasyml)
  - [protocolos.yml](#srcmainresourcesswaggerpathsprotocolosyml)

## .

### .openapi-generator-ignore

```text
# .openapi-generator-ignore
# Ignora todos os testes gerados automaticamente
src/test/**

# Ignora arquivos de configuração inúteis
pom.xml
README.md
.gitignore
```

### pom.xml

```xml
<!-- pom.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>br.com.vestris</groupId>
        <artifactId>vestris-backend</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>

    <artifactId>vestris-clinical</artifactId>

    <dependencies>
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-shared</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-species</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-pharmacology</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <!-- Para validar Referências (se já não tiver) -->
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-reference</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <!-- Swagger Dependencies -->
        <dependency>
            <groupId>io.swagger.core.v3</groupId>
            <artifactId>swagger-annotations</artifactId>
            <version>2.2.19</version>
        </dependency>
        <dependency>
            <groupId>io.swagger.core.v3</groupId>
            <artifactId>swagger-models</artifactId>
            <version>2.2.19</version>
        </dependency>
        <dependency>
            <groupId>jakarta.validation</groupId>
            <artifactId>jakarta.validation-api</artifactId>
        </dependency>
        <dependency>
            <groupId>org.openapitools</groupId>
            <artifactId>jackson-databind-nullable</artifactId>
            <version>0.2.6</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!-- JPA -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <!-- Fix Test Generation Error -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-user</artifactId>
            <version>0.0.1-SNAPSHOT</version>
            <scope>compile</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <!-- Plugin do OpenAPI Generator -->
            <plugin>
                <groupId>org.openapitools</groupId>
                <artifactId>openapi-generator-maven-plugin</artifactId>
                <version>7.1.0</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>generate</goal>
                        </goals>
                        <configuration>
                            <!-- Seus caminhos (mantenha como estão) -->
                            <inputSpec>${project.basedir}/src/main/resources/swagger/openapi.yml</inputSpec>
                            <ignoreFileOverride>${project.basedir}/.openapi-generator-ignore</ignoreFileOverride>

                            <generatorName>spring</generatorName>
                            <output>${project.build.directory}/generated-sources/openapi</output>
                            <apiPackage>br.com.vestris.clinical.interfaces.api</apiPackage> <!-- (Mude para 'clinical' no outro pom) -->
                            <modelPackage>br.com.vestris.clinical.interfaces.dto</modelPackage> <!-- (Mude para 'clinical' no outro pom) -->

                            <!-- Desliga testes de API e Modelos -->
                            <generateApiTests>false</generateApiTests>
                            <generateModelTests>false</generateModelTests>

                            <!-- O SEGREDO ESTÁ AQUI: -->
                            <!-- Dizemos explicitamente: "Gere APENAS o ApiUtil.java. Não gere Application.java nem Tests.java" -->
                            <supportingFilesToGenerate>ApiUtil.java</supportingFilesToGenerate>

                            <configOptions>
                                <delegatePattern>true</delegatePattern>
                                <interfaceOnly>false</interfaceOnly>
                                <useSpringBoot3>true</useSpringBoot3>
                                <useTags>true</useTags>
                            </configOptions>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>
```

---

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

---

## src\main\java\br\com\vestris\clinical\domain\model

### CalculoResultadoDTO.java

```java
// src\main\java\br\com\vestris\clinical\domain\model\CalculoResultadoDTO.java
package br.com.vestris.clinical.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data // Gera Getters, Setters, toString, etc.
@Builder // Permite criar o objeto de forma fluida (.builder()...build())
@NoArgsConstructor // Construtor vazio
@AllArgsConstructor // Construtor com todos os argumentos
public class CalculoResultadoDTO {
    // Contexto
    private String protocoloTitulo;
    private String medicamentoNome;
    private String referencia;

    // Dados de Entrada Normalizados
    private Double pesoKg;

    // Resultado Massa (Dose)
    private Double doseMinMg;
    private Double doseMaxMg;

    // Resultado Volume (Líquido)
    private Double volMinMl;
    private Double volMaxMl;

    // Metadados do Medicamento/Protocolo
    private String concentracao;
    private String frequencia;
    private String via;
    private String duracao;

    // Segurança
    private List<String> alertas;

    // --- NOVOS CAMPOS DE SEGURANÇA (VALIDAÇÃO) ---
    private String statusSeguranca; // SEGURO, SUBDOSE, etc.
    private String mensagemSeguranca;
    private Double refMin; // Dose de referência (mg/kg) mínima usada para validar
    private Double refMax; // Dose de referência (mg/kg) máxima usada para validar
    private String refFonte; // Fonte da referência (Ex: Carpenter)

    // --- CÁLCULO LIVRE ---
    private Double doseCalculadaMg;
    private Double volumeCalculadoMl;
}

```

### Doenca.java

```java
// src\main\java\br\com\vestris\clinical\domain\model\Doenca.java
package br.com.vestris.clinical.domain.model;


import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "doencas", schema = "clinical_schema")
public class Doenca extends EntidadeBase {

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(length = 150)
    private String nomeCientifico;

    @Column(columnDefinition = "TEXT")
    private String sintomas;

    // Foreign Key Lógica (Aponta para o ID da Espécie no outro módulo)
    @Column(nullable = false)
    private UUID especieId;
}

```

### Dosagem.java

```java
// src\main\java\br\com\vestris\clinical\domain\model\Dosagem.java
package br.com.vestris.clinical.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "protocolo") // Evita loop infinito no log
@Entity
@Table(name = "dosagens", schema = "clinical_schema")
public class Dosagem extends EntidadeBase {
    @ManyToOne(optional = false)
    @JoinColumn(name = "protocolo_id", nullable = false)
    private Protocolo protocolo;

    // --- MEDICAMENTO (HÍBRIDO) ---
    @Column(name = "medicamento_id", nullable = true)
    private UUID medicamentoId;

    @Column(name = "medicamento_texto_livre")
    private String medicamentoTextoLivre;

    private Double doseMinima;
    private Double doseMaxima;
    private String unidade;
    private String frequencia;
    private String duracao;
    private String via;
}

```

### Protocolo.java

```java
// src\main\java\br\com\vestris\clinical\domain\model\Protocolo.java
package br.com.vestris.clinical.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "protocolos", schema = "clinical_schema")
public class Protocolo extends EntidadeBase {
    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    // --- DOENÇA (HÍBRIDO) ---
    @ManyToOne(optional = true)
    @JoinColumn(name = "doenca_id", nullable = true)
    private Doenca doenca;

    @Column(name = "doenca_texto_livre")
    private String doencaTextoLivre;

    // --- REFERÊNCIA (HÍBRIDO) ---
    @Column(name = "referencia_id")
    private UUID referenciaId;

    @Column(name = "referencia_texto")
    private String referenciaTexto;

    // --- ORIGEM ---
    @Enumerated(EnumType.STRING)
    private OrigemProtocolo origem; // OFICIAL, PROPRIO

    private UUID autorId;

    @Column(name = "clinica_id")
    private UUID clinicaId; // Novo campo

    public enum OrigemProtocolo {
        OFICIAL,
        PROPRIO,
        INSTITUCIONAL // Novo Enum
    }

    @OneToMany(mappedBy = "protocolo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dosagem> dosagens = new ArrayList<>();

    public void adicionarDosagem(Dosagem dosagem) {
        dosagens.add(dosagem);
        dosagem.setProtocolo(this);
    }
}

```

### ProtocoloCompletoDTO.java

```java
// src\main\java\br\com\vestris\clinical\domain\model\ProtocoloCompletoDTO.java
package br.com.vestris.clinical.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProtocoloCompletoDTO {
    private Doenca doenca;
    private List<Protocolo> protocolos;
}

```

---

## src\main\java\br\com\vestris\clinical\domain\repository

### RepositorioDoenca.java

```java
// src\main\java\br\com\vestris\clinical\domain\repository\RepositorioDoenca.java
package br.com.vestris.clinical.domain.repository;

import br.com.vestris.clinical.domain.model.Doenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioDoenca extends JpaRepository<Doenca, UUID> {
    // Busca todas as doenças de uma espécie específica
    List<Doenca> findAllByEspecieId(UUID especieId);

    // Evita cadastrar a mesma doença para a mesma espécie duas vezes
    boolean existsByNomeAndEspecieId(String nome, UUID especieId);
}

```

### RepositorioProtocolo.java

```java
// src\main\java\br\com\vestris\clinical\domain\repository\RepositorioProtocolo.java
package br.com.vestris.clinical.domain.repository;

import br.com.vestris.clinical.domain.model.Protocolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioProtocolo extends JpaRepository<Protocolo, UUID> {
    List<Protocolo> findByDoencaId(UUID doencaId);

    // --- NOVO MÉTODO DE GOVERNANÇA ---
    @Query("SELECT p FROM Protocolo p WHERE p.doenca.id = :doencaId AND (" +
            "p.origem = 'OFICIAL' OR " +
            "(p.origem = 'INSTITUCIONAL' AND p.clinicaId = :clinicaId) OR " +
            "(p.origem = 'PROPRIO' AND p.autorId = :usuarioId)" +
            ")")
    List<Protocolo> listarAcessiveis(@Param("doencaId") UUID doencaId,
                                     @Param("clinicaId") UUID clinicaId,
                                     @Param("usuarioId") UUID usuarioId);
}

```

---

## src\main\java\br\com\vestris\clinical\interfaces\delegate

### ApiDelegateCalculadora.java

```java
// src\main\java\br\com\vestris\clinical\interfaces\delegate\ApiDelegateCalculadora.java
package br.com.vestris.clinical.interfaces.delegate;

import br.com.vestris.clinical.application.ServiceCalculadora;
import br.com.vestris.clinical.domain.model.CalculoResultadoDTO;
import br.com.vestris.clinical.interfaces.api.CalculadoraApiDelegate;
import br.com.vestris.clinical.interfaces.dto.*;
import lombok.RequiredArgsConstructor;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiDelegateCalculadora implements CalculadoraApiDelegate {
    private final ServiceCalculadora serviceCalculadora;

    @Override
    public ResponseEntity<ApiResponseCalculo> calcularDosagemSegura(CalculoSeguroRequest request) {
        var resultado = serviceCalculadora.calcular(
                request.getProtocoloId(),
                request.getMedicamentoId(),
                request.getPeso(),
                request.getUnidadePeso().name()
        );
        return montarResposta(resultado);
    }

    // 1. CÁLCULO LIVRE (SIMPLES)
    @Override
    public ResponseEntity<ApiResponseCalculo> calcularDoseLivre(CalculoLivreRequest request) {
        Double pesoKg = request.getPeso();
        if (CalculoLivreRequest.UnidadePesoEnum.G.equals(request.getUnidadePeso())) {
            pesoKg = pesoKg / 1000.0;
        }

        // Chama método específico no Service (vou mostrar abaixo)
        var resultado = serviceCalculadora.calcularMatematico(
                request.getNomeMedicamento(),
                request.getConcentracao(),
                pesoKg,
                request.getDoseInformada(),
                request.getVia(),
                request.getFrequencia(), // Wrap in JsonNullable as per previous patterns if service expects it, checking service signature next step if error. Wait, snippet passed request.getFrequencia() directly. Assuming serviceCalculadora.calcularMatematico takes String/Double.
                request.getDuracao()
        );

        return montarResposta(resultado);
    }

    // 2. VALIDAÇÃO CATÁLOGO (COM SEGURANÇA)
    @Override
    public ResponseEntity<ApiResponseCalculo> validarDoseCatalogo(CalculoCatalogoRequest request) {
        Double pesoKg = request.getPeso();
        if (CalculoCatalogoRequest.UnidadePesoEnum.G.equals(request.getUnidadePeso())) {
            pesoKg = pesoKg / 1000.0;
        }

        var resultado = serviceCalculadora.validarDose(
                request.getMedicamentoId(),
                request.getEspecieId(),
                request.getDoencaId(),
                request.getClinicaId(),
                request.getUsuarioId(),
                pesoKg,
                request.getDoseInformada(),
                "MG_KG",
                request.getVia(),
                null // Concentração manual é null aqui, pois pega do banco
        );

        return montarResposta(resultado);
    }

    // --- HELPERS SEGUROS ---

    private <T> T unwrap(JsonNullable<T> nullable) {
        return (nullable != null && nullable.isPresent()) ? nullable.get() : null;
    }

    private String unwrapString(JsonNullable<String> nullable) {
        return (nullable != null && nullable.isPresent()) ? nullable.get() : null;
    }

    private ResponseEntity<ApiResponseCalculo> montarResposta(CalculoResultadoDTO resultado) {
        CalculoResponse response = new CalculoResponse();

        response.setProtocoloTitulo(resultado.getProtocoloTitulo());
        response.setMedicamentoNome(resultado.getMedicamentoNome());
        response.setReferenciaBibliografica(resultado.getReferencia());
        response.setPesoConsideradoKg(resultado.getPesoKg());
        response.setDoseMinimaMg(resultado.getDoseMinMg());
        response.setDoseMaximaMg(resultado.getDoseMaxMg());
        response.setVolumeMinimoMl(resultado.getVolMinMl());
        response.setVolumeMaximoMl(resultado.getVolMaxMl());
        response.setConcentracaoUtilizada(resultado.getConcentracao());

        // Mapeamento Seguro do Enum
        if (resultado.getStatusSeguranca() != null) {
            try {
                // Tenta converter string para Enum (Case sensitive pode ser problema)
                String statusStr = resultado.getStatusSeguranca().toUpperCase(); // Força upper
                response.setStatusSeguranca(StatusSegurancaEnum.fromValue(statusStr));
            } catch (Exception e) {
                System.err.println("Erro ao converter status: " + resultado.getStatusSeguranca());
                response.setStatusSeguranca(StatusSegurancaEnum.NAO_VALIDADO);
            }
        } else {
            response.setStatusSeguranca(StatusSegurancaEnum.SEGURO);
        }

        response.setMensagemSeguranca(resultado.getMensagemSeguranca());
        response.setRefMin(resultado.getRefMin());
        response.setRefMax(resultado.getRefMax());
        response.setRefFonte(resultado.getRefFonte());

        // Campos do cálculo matemático que podem vir no resultado
        response.setDoseCalculadaMg(resultado.getDoseCalculadaMg());
        response.setVolumeCalculadoMl(resultado.getVolumeCalculadoMl());

        ApiResponseCalculo wrapper = new ApiResponseCalculo();
        wrapper.setSucesso(true);
        wrapper.setDados(response);

        return ResponseEntity.ok(wrapper);
    }
}

```

### ApiDelegateDoencas.java

```java
// src\main\java\br\com\vestris\clinical\interfaces\delegate\ApiDelegateDoencas.java
package br.com.vestris.clinical.interfaces.delegate;

import br.com.vestris.clinical.application.ServiceDoenca;
import br.com.vestris.clinical.domain.model.Doenca;
import br.com.vestris.clinical.interfaces.api.DoencasApiDelegate;
import br.com.vestris.clinical.interfaces.dto.ApiResponseDoenca;
import br.com.vestris.clinical.interfaces.dto.ApiResponseListaDoenca;
import br.com.vestris.clinical.interfaces.dto.DoencaRequest;
import br.com.vestris.clinical.interfaces.dto.DoencaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateDoencas implements DoencasApiDelegate {
    private final ServiceDoenca servico;

    // --- CRIAÇÃO ---
    @Override
    public ResponseEntity<ApiResponseDoenca> criarDoenca(DoencaRequest request) {
        Doenca entidade = new Doenca();
        entidade.setNome(request.getNome());
        entidade.setNomeCientifico(request.getNomeCientifico());
        entidade.setSintomas(request.getSintomas());
        entidade.setEspecieId(request.getEspecieId());

        Doenca salva = servico.criar(entidade);

        ApiResponseDoenca response = new ApiResponseDoenca();
        response.setSucesso(true);
        response.setMensagem("Doença cadastrada com sucesso.");
        response.setDados(converterParaDTO(salva));

        return ResponseEntity.ok(response);
    }

    // --- LISTAGEM ---
    @Override
    public ResponseEntity<ApiResponseListaDoenca> listarDoencas() {
        List<DoencaResponse> lista = servico.listarTodas().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());

        ApiResponseListaDoenca response = new ApiResponseListaDoenca();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseListaDoenca> listarDoencasPorEspecie(UUID especieId) {
        List<DoencaResponse> lista = servico.listarPorEspecie(especieId).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());

        ApiResponseListaDoenca response = new ApiResponseListaDoenca();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    // --- NOVOS MÉTODOS (ID, PUT, DELETE) ---

    @Override
    public ResponseEntity<ApiResponseDoenca> buscarDoencaPorId(UUID id) {
        Doenca encontrada = servico.buscarPorId(id);

        ApiResponseDoenca response = new ApiResponseDoenca();
        response.setSucesso(true);
        response.setDados(converterParaDTO(encontrada));

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseDoenca> atualizarDoenca(UUID id, DoencaRequest request) {
        Doenca dadosParaAtualizar = new Doenca();
        dadosParaAtualizar.setNome(request.getNome());
        dadosParaAtualizar.setNomeCientifico(request.getNomeCientifico());
        dadosParaAtualizar.setSintomas(request.getSintomas());
        // Nota: Geralmente não atualizamos o especieId no PUT para não quebrar integridade,
        // mas se o serviço permitir, adicione: dadosParaAtualizar.setEspecieId(request.getEspecieId());

        Doenca atualizada = servico.atualizar(id, dadosParaAtualizar);

        ApiResponseDoenca response = new ApiResponseDoenca();
        response.setSucesso(true);
        response.setMensagem("Doença atualizada com sucesso.");
        response.setDados(converterParaDTO(atualizada));

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deletarDoenca(UUID id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build(); // Retorna 204
    }

    // --- CONVERSOR ---
    private DoencaResponse converterParaDTO(Doenca entidade) {
        DoencaResponse dto = new DoencaResponse();
        dto.setId(entidade.getId());
        dto.setNome(entidade.getNome());
        dto.setNomeCientifico(entidade.getNomeCientifico());
        dto.setSintomas(entidade.getSintomas());
        dto.setEspecieId(entidade.getEspecieId());

        if (entidade.getCriadoEm() != null) {
            dto.setCriadoEm(entidade.getCriadoEm().atOffset(ZoneOffset.UTC));
        }
        return dto;
    }
}

```

### ApiDelegateProtocolos.java

```java
// src\main\java\br\com\vestris\clinical\interfaces\delegate\ApiDelegateProtocolos.java
package br.com.vestris.clinical.interfaces.delegate;

import br.com.vestris.clinical.application.ServiceProtocolo;
import br.com.vestris.clinical.domain.model.ProtocoloCompletoDTO;
import br.com.vestris.clinical.domain.model.Doenca;
import br.com.vestris.clinical.domain.model.Dosagem;
import br.com.vestris.clinical.domain.model.Protocolo;
import br.com.vestris.clinical.interfaces.api.ProtocolosApiDelegate;
import br.com.vestris.clinical.interfaces.dto.*;
import br.com.vestris.pharmacology.application.ServiceFarmacologia;
import br.com.vestris.reference.application.ServiceReferencia;
import lombok.RequiredArgsConstructor;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateProtocolos implements ProtocolosApiDelegate {

    private final ServiceProtocolo servico;
    private final ServiceFarmacologia serviceFarmacologia;
    private final ServiceReferencia serviceReferencia;

    // --- CRIAÇÃO ---
    @Override
    public ResponseEntity<ApiResponseProtocolo> criarProtocolo(ProtocoloRequest request) {
        Protocolo protocolo = new Protocolo();
        protocolo.setTitulo(request.getTitulo());
        protocolo.setObservacoes(request.getObservacoes());

        protocolo.setReferenciaId(unwrap(request.getReferenciaId()));
        protocolo.setReferenciaTexto(request.getReferenciaTexto());
        protocolo.setDoencaTextoLivre(request.getDoencaTexto());

        if (request.getOrigem() != null) {
            protocolo.setOrigem(Protocolo.OrigemProtocolo.valueOf(request.getOrigem().name()));
        } else {
            protocolo.setOrigem(Protocolo.OrigemProtocolo.OFICIAL);
        }

        protocolo.setAutorId(request.getAutorId());

        // NOVO: Setar Clinica ID
        protocolo.setClinicaId(unwrap(request.getClinicaId()));

        List<Dosagem> listaDosagens = converterDosagensRequest(request.getDosagens());

        UUID doencaId = unwrap(request.getDoencaId());

        Protocolo salvo = servico.criar(protocolo, doencaId, listaDosagens);

        ApiResponseProtocolo response = new ApiResponseProtocolo();
        response.setSucesso(true);
        response.setMensagem("Protocolo criado com sucesso.");
        response.setDados(converterParaDto(salvo));

        return ResponseEntity.ok(response);
    }

    // --- LISTAGEM (CORRIGIDA) ---
    @Override
    public ResponseEntity<ApiResponseListaProtocolo> listarProtocolosPorDoenca(UUID doencaId, UUID clinicaId, UUID usuarioId) {

        List<Protocolo> protocolosEncontrados;

        // Se vierem os filtros de segurança, usa a busca inteligente
        if (clinicaId != null && usuarioId != null) {
            protocolosEncontrados = servico.listarAcessiveis(doencaId, clinicaId, usuarioId);
        } else {
            // Fallback (Legado ou uso interno): Traz tudo da doença (Cuidado aqui em produção)
            // Se preferir segurança total, force retornar lista vazia se não tiver contexto.
            protocolosEncontrados = servico.listarPorDoenca(doencaId);
        }

        List<ProtocoloResponse> lista = protocolosEncontrados.stream()
                .map(this::converterParaDto)
                .collect(Collectors.toList());

        ApiResponseListaProtocolo response = new ApiResponseListaProtocolo();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    // --- PROTOCOLO COMPLETO ---
    @Override
    public ResponseEntity<ApiResponseProtocoloCompleto> obterProtocoloCompleto(UUID especieId, UUID doencaId) {
        // Nota: Este método ainda retorna TODOS os protocolos da doença para compor a visão completa.
        // Se precisar filtrar aqui também, o ServiceProtocolo.montarProtocoloCompleto precisaria ser atualizado.
        // Por enquanto, mantemos o comportamento padrão do "Motor Clínico".

        ProtocoloCompletoDTO dtoCompleto = servico.montarProtocoloCompleto(especieId, doencaId);

        ProtocoloCompletoResponse response = new ProtocoloCompletoResponse();
        response.setDoenca(converterDoenca(dtoCompleto.getDoenca()));

        List<ProtocoloDetalhadoResponse> listaProtos = dtoCompleto.getProtocolos().stream()
                .map(p -> {
                    ProtocoloDetalhadoResponse detalhe = new ProtocoloDetalhadoResponse();
                    detalhe.setId(p.getId());
                    detalhe.setTitulo(p.getTitulo());
                    detalhe.setObservacoes(p.getObservacoes());

                    if (p.getReferenciaId() != null) {
                        String citacao = serviceReferencia.buscarCitacaoPorId(p.getReferenciaId());
                        detalhe.setReferenciaTexto(citacao);
                    } else {
                        detalhe.setReferenciaTexto(p.getReferenciaTexto());
                    }

                    if (p.getOrigem() != null) {
                        detalhe.setOrigem(OrigemProtocoloEnum.valueOf(p.getOrigem().name()));
                    }
                    detalhe.setAutorId(p.getAutorId());

                    List<DosagemResponse> dosesResponse = p.getDosagens().stream()
                            .map(this::converterDosagemUnica)
                            .collect(Collectors.toList());

                    detalhe.setDosagens(dosesResponse);
                    detalhe.setAlertasGerais(new ArrayList<>());
                    return detalhe;
                }).collect(Collectors.toList());

        response.setProtocolos(listaProtos);

        ApiResponseProtocoloCompleto wrapper = new ApiResponseProtocoloCompleto();
        wrapper.setSucesso(true);
        wrapper.setDados(response);
        return ResponseEntity.ok(wrapper);
    }

    // --- MÉTODOS CRUD PADRÃO ---

    @Override
    public ResponseEntity<ApiResponseProtocolo> buscarProtocoloPorId(UUID id) {
        Protocolo p = servico.buscarPorId(id);
        ApiResponseProtocolo response = new ApiResponseProtocolo();
        response.setSucesso(true);
        response.setDados(converterParaDto(p));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseProtocolo> atualizarProtocolo(UUID id, ProtocoloRequest request) {
        Protocolo dadosProtocolo = new Protocolo();
        dadosProtocolo.setTitulo(request.getTitulo());
        dadosProtocolo.setObservacoes(request.getObservacoes());
        dadosProtocolo.setReferenciaTexto(request.getReferenciaTexto());

        dadosProtocolo.setReferenciaId(unwrap(request.getReferenciaId()));
        dadosProtocolo.setDoencaTextoLivre(request.getDoencaTexto());

        // Atualiza vínculo institucional se enviado
        dadosProtocolo.setClinicaId(unwrap(request.getClinicaId()));

        List<Dosagem> novasDosagens = converterDosagensRequest(request.getDosagens());

        Protocolo atualizado = servico.atualizar(id, dadosProtocolo, novasDosagens);

        ApiResponseProtocolo response = new ApiResponseProtocolo();
        response.setSucesso(true);
        response.setMensagem("Protocolo atualizado.");
        response.setDados(converterParaDto(atualizado));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deletarProtocolo(UUID id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // --- HELPERS ---
    private <T> T unwrap(JsonNullable<T> nullable) {
        if (nullable == null || !nullable.isPresent()) {
            return null;
        }
        return nullable.get();
    }

    private DoencaResponse converterDoenca(Doenca d) {
        DoencaResponse dto = new DoencaResponse();
        dto.setId(d.getId());
        dto.setNome(d.getNome());
        dto.setNomeCientifico(d.getNomeCientifico());
        dto.setSintomas(d.getSintomas());
        dto.setEspecieId(d.getEspecieId());
        if(d.getCriadoEm() != null) dto.setCriadoEm(d.getCriadoEm().atOffset(ZoneOffset.UTC));
        return dto;
    }

    private List<Dosagem> converterDosagensRequest(List<DosagemItemRequest> itensRequest) {
        List<Dosagem> lista = new ArrayList<>();
        if (itensRequest != null) {
            for (DosagemItemRequest item : itensRequest) {
                Dosagem d = new Dosagem();
                d.setMedicamentoId(unwrap(item.getMedicamentoId()));
                d.setDoseMinima(unwrap(item.getDoseMinima()));
                d.setDoseMaxima(unwrap(item.getDoseMaxima()));
                d.setMedicamentoTextoLivre(item.getMedicamentoTexto());
                d.setUnidade(item.getUnidade());
                d.setFrequencia(item.getFrequencia());
                d.setDuracao(item.getDuracao());
                d.setVia(item.getVia());
                lista.add(d);
            }
        }
        return lista;
    }

    private ProtocoloResponse converterParaDto(Protocolo entidade) {
        ProtocoloResponse dto = new ProtocoloResponse();
        dto.setId(entidade.getId());
        dto.setTitulo(entidade.getTitulo());
        dto.setObservacoes(entidade.getObservacoes());

        // NOVO CAMPO
        dto.setClinicaId(entidade.getClinicaId());

        if (entidade.getReferenciaTexto() != null) {
            dto.setReferenciaTexto(entidade.getReferenciaTexto());
        } else if (entidade.getReferenciaId() != null) {
            try {
                dto.setReferenciaTexto(serviceReferencia.buscarCitacaoPorId(entidade.getReferenciaId()));
            } catch (Exception e) {
                dto.setReferenciaTexto("Ref ID: " + entidade.getReferenciaId());
            }
        }

        if (entidade.getDoenca() != null) {
            dto.setDoencaId(entidade.getDoenca().getId());
        }
        dto.setDoencaTexto(entidade.getDoencaTextoLivre());

        if (entidade.getOrigem() != null) {
            dto.setOrigem(OrigemProtocoloEnum.valueOf(entidade.getOrigem().name()));
        }
        dto.setAutorId(entidade.getAutorId());

        if (entidade.getDosagens() != null) {
            List<DosagemResponse> dosagensDto = entidade.getDosagens().stream()
                    .map(this::converterDosagemUnica)
                    .collect(Collectors.toList());
            dto.setDosagens(dosagensDto);
        }
        return dto;
    }

    private DosagemResponse converterDosagemUnica(Dosagem d) {
        DosagemResponse dDto = new DosagemResponse();
        dDto.setId(d.getId());
        dDto.setMedicamentoId(d.getMedicamentoId());
        dDto.setMedicamentoTexto(d.getMedicamentoTextoLivre());

        if (d.getMedicamentoId() != null) {
            try {
                var med = serviceFarmacologia.buscarMedicamentoPorId(d.getMedicamentoId());
                dDto.setNomeMedicamento(med.getNome());
            } catch (Exception e) {
                dDto.setNomeMedicamento(d.getMedicamentoTextoLivre() != null ? d.getMedicamentoTextoLivre() : "Medicamento não encontrado");
            }
        } else {
            dDto.setNomeMedicamento(d.getMedicamentoTextoLivre());
        }

        String doseTexto = "";
        if (d.getDoseMinima() != null) {
            doseTexto = d.getDoseMinima() + (d.getUnidade() != null ? " " + d.getUnidade() : "");
        } else {
            doseTexto = "Dose a critério";
        }

        dDto.setDose(doseTexto);
        dDto.setDetalhes((d.getVia() != null ? d.getVia() : "") +
                (d.getFrequencia() != null ? ", " + d.getFrequencia() : "") +
                (d.getDuracao() != null ? " por " + d.getDuracao() : ""));
        return dDto;
    }
}
```

---

## src\main\java\br\com\vestris\clinical\interfaces\dto

### CalculoCatalogoRequest.java

```java
// src\main\java\br\com\vestris\clinical\interfaces\dto\CalculoCatalogoRequest.java

```

### CalculoLivreRequest.java

```java
// src\main\java\br\com\vestris\clinical\interfaces\dto\CalculoLivreRequest.java

```

---

## src\main\resources\swagger

### openapi.yml

```yaml
# src\main\resources\swagger\openapi.yml
openapi: 3.0.3
info:
  title: Vestris - Módulo Clínico
  description: Gestão de Doenças e Protocolos
  version: 1.0.0
servers:
  - url: http://localhost:8080
    description: Servidor Local

paths:
  /api/v1/doencas:
    $ref: './paths/doencas.yml#/paths/~1api~1v1~1doencas'

  /api/v1/doencas/{id}:
    $ref: './paths/doencas.yml#/paths/~1api~1v1~1doencas~1{id}'

  /api/v1/doencas/por-especie/{especieId}:
    $ref: './paths/doencas.yml#/paths/~1api~1v1~1doencas~1por-especie~1{especieId}'

  /api/v1/protocolos:
    $ref: './paths/protocolos.yml#/paths/~1api~1v1~1protocolos'

  /api/v1/protocolos/{id}:
    $ref: './paths/protocolos.yml#/paths/~1api~1v1~1protocolos~1{id}'

  /api/v1/doencas/{doencaId}/protocolos:
    $ref: './paths/protocolos.yml#/paths/~1api~1v1~1doencas~1{doencaId}~1protocolos'

  /api/v1/especies/{especieId}/doencas/{doencaId}/protocolo-completo:
    $ref: './paths/protocolos.yml#/paths/~1api~1v1~1especies~1{especieId}~1doencas~1{doencaId}~1protocolo-completo'

  /api/v1/calculadora/dosagem:
    $ref: './paths/calculadora.yml#/calculadora_item'
    # ADICIONE ESTA LINHA:
  /api/v1/calculadora/validar:
    $ref: './paths/calculadora.yml#/calculadora_validar'

  /api/v1/calculadora/livre:
    $ref: './paths/calculadora.yml#/calculadora_livre'


# Importando os componentes (necessário declarar aqui também para o parser raiz entender)
components:
  schemas:
    DoencaRequest:
      $ref: "./components/schemas.yml#/DoencaRequest"
    DoencaResponse:
      $ref: "./components/schemas.yml#/DoencaResponse"
    ApiResponseDoenca:
      $ref: "./components/schemas.yml#/ApiResponseDoenca"
    ApiResponseListaDoenca:
      $ref: "./components/schemas.yml#/ApiResponseListaDoenca"
    DosagemItemRequest:
      $ref: "./components/schemas.yml#/DosagemItemRequest"
    DosagemResponse:
      $ref: "./components/schemas.yml#/DosagemResponse"
    ProtocoloRequest:
      $ref: "./components/schemas.yml#/ProtocoloRequest"
    ProtocoloResponse:
      $ref: "./components/schemas.yml#/ProtocoloResponse"
    CalculoSeguroRequest:
      $ref: "./components/schemas.yml#/CalculoSeguroRequest"
    CalculoResponse:
      $ref: "./components/schemas.yml#/CalculoResponse"
    ApiResponseProtocolo:
      $ref: "./components/schemas.yml#/ApiResponseProtocolo"
    ApiResponseListaProtocolo:
      $ref: "./components/schemas.yml#/ApiResponseListaProtocolo"
    ProtocoloDetalhadoResponse:
      $ref: "./components/schemas.yml#/ProtocoloDetalhadoResponse"
    ProtocoloCompletoResponse:
      $ref: "./components/schemas.yml#/ProtocoloCompletoResponse"
    ApiResponseProtocoloCompleto:
      $ref: "./components/schemas.yml#/ApiResponseProtocoloCompleto"
    ApiResponseCalculo:
      $ref: "./components/schemas.yml#/ApiResponseCalculo"
    # ENUMS ATUALIZADOS PARA O GERADOR ENCONTRAR
    OrigemProtocoloEnum:
      $ref: "./components/schemas.yml#/OrigemProtocoloEnum"
    CalculoValidacaoRequest:
      $ref: "./components/schemas.yml#/CalculoValidacaoRequest"





```

---

## src\main\resources\swagger\components

### schemas.yml

```yaml
# src\main\resources\swagger\components\schemas.yml
# src/main/resources/swagger/components/schemas.yml

# --- ENUMS ---
OrigemProtocoloEnum:
  type: string
  enum: [OFICIAL, PROPRIO, INSTITUCIONAL]

StatusSegurancaEnum:
  type: string
  enum: [SEGURO, SUBDOSE, SUPERDOSE, SEM_REFERENCIA, NAO_VALIDADO]

# --- DOENÇAS ---
DoencaRequest:
  type: object
  required: [nome, especieId]
  properties:
    nome: { type: string }
    nomeCientifico: { type: string }
    sintomas: { type: string }
    especieId: { type: string, format: uuid }

DoencaResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    nome: { type: string }
    nomeCientifico: { type: string }
    sintomas: { type: string }
    especieId: { type: string, format: uuid }
    criadoEm: { type: string, format: date-time }

# --- PROTOCOLOS ---
DosagemItemRequest:
  type: object
  properties:
    medicamentoId: { type: string, format: uuid, nullable: true }
    medicamentoTexto: { type: string }
    doseMinima: { type: number, format: double, nullable: true }
    doseMaxima: { type: number, format: double, nullable: true }
    unidade: { type: string }
    frequencia: { type: string }
    duracao: { type: string }
    via: { type: string }

ProtocoloRequest:
  type: object
  required: [ titulo, dosagens ]
  properties:
    titulo: { type: string }
    doencaId: { type: string, format: uuid, nullable: true }
    doencaTexto: { type: string }
    referenciaId: { type: string, format: uuid, nullable: true }
    referenciaTexto: { type: string }
    observacoes: { type: string }
    origem: { $ref: '#/OrigemProtocoloEnum' }
    autorId: { type: string, format: uuid }
    clinicaId: { type: string, format: uuid, nullable: true }
    dosagens:
      type: array
      items: { $ref: '#/DosagemItemRequest' }

DosagemResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    medicamentoId: { type: string, format: uuid }
    medicamentoTexto: { type: string }
    nomeMedicamento: { type: string }
    dose: { type: string }
    detalhes: { type: string }

ProtocoloResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    titulo: { type: string }
    doencaId: { type: string, format: uuid }
    doencaTexto: { type: string }
    referenciaId: { type: string, format: uuid }
    referenciaTexto: { type: string }
    observacoes: { type: string }
    origem: { $ref: '#/OrigemProtocoloEnum' }
    autorId: { type: string, format: uuid }
    clinicaId: { type: string, format: uuid }
    dosagens:
      type: array
      items: { $ref: '#/DosagemResponse' }

ProtocoloDetalhadoResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    titulo: { type: string }
    referenciaTexto: { type: string }
    referenciaId: { type: string, format: uuid }
    observacoes: { type: string }
    origem: { $ref: '#/OrigemProtocoloEnum' }
    autorId: { type: string, format: uuid }
    dosagens:
      type: array
      items: { $ref: '#/DosagemResponse' }
    alertasGerais:
      type: array
      items: { type: string }

ProtocoloCompletoResponse:
  type: object
  properties:
    doenca: { $ref: '#/DoencaResponse' }
    protocolos:
      type: array
      items: { $ref: '#/ProtocoloDetalhadoResponse' }

# --- CALCULADORA ---

CalculoLivreRequest:
  type: object
  required: [peso, doseInformada, concentracao, unidadePeso]
  properties:
    peso: { type: number, format: double }
    doseInformada: { type: number, format: double }
    concentracao: { type: number, format: double }
    unidadePeso: { type: string, enum: [KG, G] }
    nomeMedicamento: { type: string } # Opcional, só para devolver no DTO
    via: { type: string }
    frequencia: { type: string }
    duracao: { type: string }

# REQUEST PARA VALIDAÇÃO (CATÁLOGO)
CalculoCatalogoRequest:
  type: object
  required: [medicamentoId, especieId, peso, doseInformada, unidadePeso]
  properties:
    medicamentoId: { type: string, format: uuid }
    especieId: { type: string, format: uuid }
    doencaId: { type: string, format: uuid } # Mantém nullable aqui pois é ID
    clinicaId: { type: string, format: uuid }
    usuarioId: { type: string, format: uuid}
    peso: { type: number, format: double }
    doseInformada: { type: number, format: double }
    unidadePeso: { type: string, enum: [KG, G] }
    via: { type: string }

CalculoSeguroRequest:
  type: object
  required: [protocoloId, medicamentoId, peso, unidadePeso]
  properties:
    protocoloId: { type: string, format: uuid }
    medicamentoId: { type: string, format: uuid }
    peso: { type: number, format: double }
    unidadePeso: { type: string, enum: [KG, G] }

CalculoResponse:
  type: object
  properties:
    protocoloTitulo: { type: string }
    medicamentoNome: { type: string }
    referenciaBibliografica: { type: string }
    pesoConsideradoKg: { type: number, format: double }
    doseMinimaMg: { type: number, format: double }
    doseMaximaMg: { type: number, format: double }
    volumeMinimoMl: { type: number, format: double }
    volumeMaximoMl: { type: number, format: double }
    concentracaoUtilizada: { type: string }
    frequencia: { type: string }
    doseCalculadaMg: { type: number, format: double }
    volumeCalculadoMl: { type: number, format: double }
    via: { type: string }
    duracao: { type: string }
    alertas:
      type: array
      items: { type: string }
    # NOVOS CAMPOS DE SEGURANÇA
    statusSeguranca: { $ref: '#/StatusSegurancaEnum' }
    mensagemSeguranca: { type: string }
    refMin: { type: number, format: double }
    refMax: { type: number, format: double }
    refFonte: { type: string }

CalculoValidacaoRequest:
  type: object
  required: [ peso, doseInformada, unidadePeso]
  properties:
    medicamentoId: { type: string, format: uuid, nullable: true }
    especieId: { type: string, format: uuid, nullable: true }
    peso: { type: number, format: double }
    doseInformada: { type: number, format: double, description: "A dose que o vet quer usar (mg/kg)" }
    unidadePeso: { type: string, enum: [KG, G] }
    concentracaoInformada: { type: number, format: double, nullable: true, description: "mg/ml (usado se não houver ID)" }
    # Opcionais para refinar a busca
    doencaId: { type: string, format: uuid, nullable: true }
    via: { type: string, nullable: true }
    clinicaId: { type: string, format: uuid, nullable: true }
    usuarioId: { type: string, format: uuid, nullable: true }

# --- WRAPPERS ---
ApiResponseProtocolo:
  type: object
  properties:
    sucesso: { type: boolean }
    mensagem: { type: string }
    dados: { $ref: '#/ProtocoloResponse' }

ApiResponseListaProtocolo:
  type: object
  properties:
    sucesso: { type: boolean }
    dados:
      type: array
      items: { $ref: '#/ProtocoloResponse' }

ApiResponseProtocoloCompleto:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { $ref: '#/ProtocoloCompletoResponse' }

ApiResponseDoenca:
  type: object
  properties:
    sucesso: { type: boolean }
    mensagem: { type: string }
    dados: { $ref: '#/DoencaResponse' }

ApiResponseListaDoenca:
  type: object
  properties:
    sucesso: { type: boolean }
    dados:
      type: array
      items: { $ref: '#/DoencaResponse' }

ApiResponseCalculo:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { $ref: '#/CalculoResponse' }
```

---

## src\main\resources\swagger\paths

### calculadora.yml

```yaml
# src\main\resources\swagger\paths\calculadora.yml
# ROTA 1: CÁLCULO BASEADO EM PROTOCOLO (JÁ EXISTENTE, MANTÉM)
calculadora_item:
  post:
    tags: [Calculadora]
    summary: Calcular via Protocolo
    operationId: calcularDosagemSegura
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/CalculoSeguroRequest' }
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseCalculo' }

# ROTA 2: CÁLCULO LIVRE (MATEMÁTICA PURA) - NOVO
calculadora_livre:
  post:
    tags: [Calculadora]
    summary: Calculadora Livre (Sem Validação)
    description: "Faz apenas o cálculo matemático de volume com base na dose e concentração informadas."
    operationId: calcularDoseLivre
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/CalculoLivreRequest' }
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseCalculo' }

# ROTA 3: VALIDAÇÃO DE SEGURANÇA (CATÁLOGO) - NOVO
calculadora_validar:
  post:
    tags: [Calculadora]
    summary: Validar Dose (Catálogo)
    description: "Verifica se a dose está segura cruzando com a base científica."
    operationId: validarDoseCatalogo
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/CalculoCatalogoRequest' }
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseCalculo' }
```

### doencas.yml

```yaml
# src\main\resources\swagger\paths\doencas.yml
paths:
  /api/v1/doencas:
    post:
      tags:
        - Doencas
      summary: Cadastrar nova doença
      operationId: criarDoenca
      requestBody:
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/DoencaRequest'
      responses:
        '200':
          description: Sucesso
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseDoenca'

    get:
      tags:
        - Doencas
      summary: Listar todas as doenças
      operationId: listarDoencas
      responses:
        '200':
          description: Lista recuperada
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseListaDoenca'

  /api/v1/doencas/{id}:
    parameters:
      - name: id
        in: path
        required: true
        schema:
          type: string
          format: uuid
    get:
      tags:
        - Doencas
      summary: Buscar doença por ID
      operationId: buscarDoencaPorId
      responses:
        '200':
          description: Encontrado
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseDoenca'
    put:
      tags:
        - Doencas
      summary: Atualizar doença
      operationId: atualizarDoenca
      requestBody:
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/DoencaRequest'
      responses:
        '200':
          description: Atualizado
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseDoenca'
    delete:
      tags:
        - Doencas
      summary: Remover doença
      operationId: deletarDoenca
      responses:
        '204':
          description: Removido

  /api/v1/doencas/por-especie/{especieId}:
    get:
      tags:
        - Doencas
      summary: Listar doenças de uma espécie específica
      operationId: listarDoencasPorEspecie
      parameters:
        - name: especieId
          in: path
          required: true
          schema:
            type: string
            format: uuid
      responses:
        '200':
          description: Lista filtrada
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseListaDoenca'

```

### protocolos.yml

```yaml
# src\main\resources\swagger\paths\protocolos.yml
# vestris-clinical/src/main/resources/swagger/paths/protocolos.yml
paths:
  /api/v1/protocolos:
    post:
      tags:
        - Protocolos
      summary: Criar protocolo terapêutico
      operationId: criarProtocolo
      requestBody:
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ProtocoloRequest'
      responses:
        '200':
          description: Sucesso
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseProtocolo'

  /api/v1/protocolos/{id}:
    parameters:
      - name: id
        in: path
        required: true
        schema:
          type: string
          format: uuid
    get:
      tags:
        - Protocolos
      summary: Buscar protocolo por ID
      operationId: buscarProtocoloPorId
      responses:
        '200':
          description: Encontrado
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseProtocolo'
    put:
      tags:
        - Protocolos
      summary: Atualizar protocolo
      operationId: atualizarProtocolo
      requestBody:
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ProtocoloRequest'
      responses:
        '200':
          description: Atualizado
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseProtocolo'
    delete:
      tags:
        - Protocolos
      summary: Remover protocolo
      operationId: deletarProtocolo
      responses:
        '204':
          description: Removido

  /api/v1/doencas/{doencaId}/protocolos:
    get:
      tags:
        - Protocolos
      summary: Listar protocolos de uma doença
      description: "Retorna protocolos Oficiais, Institucionais (da clínica) e Próprios (do usuário)."
      operationId: listarProtocolosPorDoenca
      parameters:
        - name: doencaId
          in: path
          required: true
          schema:
            type: string
            format: uuid
        # NOVOS PARÂMETROS PARA FILTRAGEM INTELIGENTE
        - name: clinicaId
          in: query
          required: false
          schema: { type: string, format: uuid }
        - name: usuarioId
          in: query
          required: false
          schema: { type: string, format: uuid }
      responses:
        '200':
          description: Lista
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseListaProtocolo'

  /api/v1/especies/{especieId}/doencas/{doencaId}/protocolo-completo:
    get:
      tags: [ Protocolos ]
      summary: Obter visão completa do tratamento
      description: "Retorna doença, protocolo, dosagens e contraindicações combinadas"
      operationId: obterProtocoloCompleto
      parameters:
        - name: especieId
          in: path
          required: true
          schema: { type: string, format: uuid }
        - name: doencaId
          in: path
          required: true
          schema: { type: string, format: uuid }
      responses:
        '200':
          description: Sucesso
          content:
            application/json:
              schema: { $ref: '../components/schemas.yml#/ApiResponseProtocoloCompleto' }
```

