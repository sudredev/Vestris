# Documentação do projeto vestris-pharmacology

## Índice de Diretórios e Arquivos

- [.](#)
  - [pom.xml](#pomxml)
  - [vestris-pharmacology.iml](#vestris-pharmacologyiml)
- [src\main\java\br\com\vestris\pharmacology\application](#srcmainjavabrcomvestrispharmacologyapplication)
  - [ServiceContraindicacao.java](#srcmainjavabrcomvestrispharmacologyapplicationservicecontraindicacaojava)
  - [ServiceDoseReferencia.java](#srcmainjavabrcomvestrispharmacologyapplicationservicedosereferenciajava)
  - [ServiceFarmacologia.java](#srcmainjavabrcomvestrispharmacologyapplicationservicefarmacologiajava)
  - [ServiceSegurancaFarmacologica.java](#srcmainjavabrcomvestrispharmacologyapplicationservicesegurancafarmacologicajava)
- [src\main\java\br\com\vestris\pharmacology\domain\model](#srcmainjavabrcomvestrispharmacologydomainmodel)
  - [Contraindicacao.java](#srcmainjavabrcomvestrispharmacologydomainmodelcontraindicacaojava)
  - [DoseReferencia.java](#srcmainjavabrcomvestrispharmacologydomainmodeldosereferenciajava)
  - [Medicamento.java](#srcmainjavabrcomvestrispharmacologydomainmodelmedicamentojava)
  - [PrincipioAtivo.java](#srcmainjavabrcomvestrispharmacologydomainmodelprincipioativojava)
- [src\main\java\br\com\vestris\pharmacology\domain\model\enums](#srcmainjavabrcomvestrispharmacologydomainmodelenums)
  - [StatusSegurancaDose.java](#srcmainjavabrcomvestrispharmacologydomainmodelenumsstatussegurancadosejava)
  - [UnidadeDose.java](#srcmainjavabrcomvestrispharmacologydomainmodelenumsunidadedosejava)
  - [ViaAdministracao.java](#srcmainjavabrcomvestrispharmacologydomainmodelenumsviaadministracaojava)
- [src\main\java\br\com\vestris\pharmacology\domain\repository](#srcmainjavabrcomvestrispharmacologydomainrepository)
  - [RepositorioContraindicacao.java](#srcmainjavabrcomvestrispharmacologydomainrepositoryrepositoriocontraindicacaojava)
  - [RepositorioDoseReferencia.java](#srcmainjavabrcomvestrispharmacologydomainrepositoryrepositoriodosereferenciajava)
  - [RepositorioMedicamento.java](#srcmainjavabrcomvestrispharmacologydomainrepositoryrepositoriomedicamentojava)
  - [RepositorioPrincipioAtivo.java](#srcmainjavabrcomvestrispharmacologydomainrepositoryrepositorioprincipioativojava)
- [src\main\java\br\com\vestris\pharmacology\interfaces\delegate](#srcmainjavabrcomvestrispharmacologyinterfacesdelegate)
  - [ApiDelegateContraindicacoes.java](#srcmainjavabrcomvestrispharmacologyinterfacesdelegateapidelegatecontraindicacoesjava)
  - [ApiDelegateMedicamentos.java](#srcmainjavabrcomvestrispharmacologyinterfacesdelegateapidelegatemedicamentosjava)
  - [ApiDelegatePrincipioAtivo.java](#srcmainjavabrcomvestrispharmacologyinterfacesdelegateapidelegateprincipioativojava)
  - [ApiDelegateSegurancaClinica.java](#srcmainjavabrcomvestrispharmacologyinterfacesdelegateapidelegatesegurancaclinicajava)
- [src\main\resources\swagger](#srcmainresourcesswagger)
  - [openapi.yml](#srcmainresourcesswaggeropenapiyml)
- [src\main\resources\swagger\components](#srcmainresourcesswaggercomponents)
  - [schemas.yml](#srcmainresourcesswaggercomponentsschemasyml)
- [src\main\resources\swagger\paths](#srcmainresourcesswaggerpaths)
  - [contraindicacoes.yml](#srcmainresourcesswaggerpathscontraindicacoesyml)
  - [medicamentos.yml](#srcmainresourcesswaggerpathsmedicamentosyml)
  - [principios-ativos.yml](#srcmainresourcesswaggerpathsprincipios-ativosyml)

## .

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

    <artifactId>vestris-pharmacology</artifactId>

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

        <!-- Para validar se a Referência existe -->
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-reference</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <!-- Dependências do Swagger/JPA iguais aos outros módulos -->
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
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
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
                            <inputSpec>${project.basedir}/src/main/resources/swagger/openapi.yml</inputSpec>
                            <generatorName>spring</generatorName>
                            <output>${project.build.directory}/generated-sources/openapi</output>
                            <apiPackage>br.com.vestris.pharmacology.interfaces.api</apiPackage>
                            <modelPackage>br.com.vestris.pharmacology.interfaces.dto</modelPackage>

                            <generateApiTests>false</generateApiTests>
                            <generateModelTests>false</generateModelTests>
                            <generateSupportingFiles>true</generateSupportingFiles>
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

### vestris-pharmacology.iml

```iml
# vestris-pharmacology.iml
<?xml version="1.0" encoding="UTF-8"?>
<module version="4">
  <component name="AdditionalModuleElements">
    <content url="file://$MODULE_DIR$/../vestris-pharmacology" dumb="true">
      <sourceFolder url="file://$MODULE_DIR$/../vestris-pharmacology/target/generated-sources/openapi/src/main/java" isTestSource="false" generated="true" />
    </content>
  </component>
</module>
```

---

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

---

## src\main\java\br\com\vestris\pharmacology\domain\model

### Contraindicacao.java

```java
// src\main\java\br\com\vestris\pharmacology\domain\model\Contraindicacao.java
package br.com.vestris.pharmacology.domain.model;

import br.com.vestris.pharmacology.interfaces.dto.MedicamentoRequest;
import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "contraindicacoes", schema = "pharmacology_schema")
public class Contraindicacao extends EntidadeBase {
    @ManyToOne(optional = false)
    @JoinColumn(name = "principio_ativo_id", nullable = false)
    private PrincipioAtivo principioAtivo;

    @Column(name = "especie_id", nullable = false)
    private UUID especieId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gravidade gravidade;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descricao;

    private String referenciaTexto;

    public enum Gravidade {
        LEVE, MODERADA, GRAVE, FATAL
    }
}

```

### DoseReferencia.java

```java
// src\main\java\br\com\vestris\pharmacology\domain\model\DoseReferencia.java
package br.com.vestris.pharmacology.domain.model;

import br.com.vestris.pharmacology.domain.model.enums.UnidadeDose;
import br.com.vestris.pharmacology.domain.model.enums.ViaAdministracao;
import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "doses_referencia", schema = "pharmacology_schema")
public class DoseReferencia extends EntidadeBase {

    @Column(nullable = false)
    private UUID medicamentoId;
    private UUID especieId;
    private UUID doencaId;
    private UUID clinicaId;

    @Enumerated(EnumType.STRING)
    private ViaAdministracao via;

    @Enumerated(EnumType.STRING)
    private UnidadeDose unidade;

    // NUMERIC no banco -> BigDecimal no Java
    private BigDecimal doseMin;
    private BigDecimal doseMax;

    private String origem; // OFICIAL ou INSTITUCIONAL (String pra simplificar persistencia com Check)
    private String nivelConfianca;
    private String fonteBibliografica;
    private String observacoes;

}

```

### Medicamento.java

```java
// src\main\java\br\com\vestris\pharmacology\domain\model\Medicamento.java
package br.com.vestris.pharmacology.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "medicamentos", schema = "pharmacology_schema")
public class Medicamento extends EntidadeBase {
    @Column(nullable = false, length = 150)
    private String nome;

    @Column(length = 50)
    private String concentracao; // Ex: 10mg/ml

    private String fabricante;

    private String formaFarmaceutica; // Ex: Comprimido, Injetável

    // Relacionamento forte: Medicamento PRECISA de um Princípio Ativo
    @ManyToOne(optional = false)
    @JoinColumn(name = "principio_ativo_id", nullable = false)
    private PrincipioAtivo principioAtivo;

}

```

### PrincipioAtivo.java

```java
// src\main\java\br\com\vestris\pharmacology\domain\model\PrincipioAtivo.java
package br.com.vestris.pharmacology.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "principios_ativos", schema = "pharmacology_schema")
public class PrincipioAtivo extends EntidadeBase {

    @Column(nullable = false, unique = true, length = 150)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(length = 100)
    private String grupoFarmacologico;

}

```

---

## src\main\java\br\com\vestris\pharmacology\domain\model\enums

### StatusSegurancaDose.java

```java
// src\main\java\br\com\vestris\pharmacology\domain\model\enums\StatusSegurancaDose.java
package br.com.vestris.pharmacology.domain.model.enums;

public enum StatusSegurancaDose {
    SEGURO,
    SUBDOSE,
    SUPERDOSE,
    SEM_REFERENCIA,
    NAO_VALIDADO // Para unidades não suportadas ainda (ex: UI/kg)
}

```

### UnidadeDose.java

```java
// src\main\java\br\com\vestris\pharmacology\domain\model\enums\UnidadeDose.java
package br.com.vestris.pharmacology.domain.model.enums;

public enum UnidadeDose {
    MG_KG, MCG_KG, UI_KG, MG_ANIMAL, ML_KG
}

```

### ViaAdministracao.java

```java
// src\main\java\br\com\vestris\pharmacology\domain\model\enums\ViaAdministracao.java
package br.com.vestris.pharmacology.domain.model.enums;

public enum ViaAdministracao {
    ORAL, SC, IM, IV, IO, INALATORIA, TOPICA, OUTRA
}

```

---

## src\main\java\br\com\vestris\pharmacology\domain\repository

### RepositorioContraindicacao.java

```java
// src\main\java\br\com\vestris\pharmacology\domain\repository\RepositorioContraindicacao.java
package br.com.vestris.pharmacology.domain.repository;

import br.com.vestris.pharmacology.domain.model.Contraindicacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioContraindicacao extends JpaRepository<Contraindicacao, UUID> {
    // CORREÇÃO: Busca por Princípio Ativo
    List<Contraindicacao> findByPrincipioAtivoId(UUID principioAtivoId);

    boolean existsByPrincipioAtivoIdAndEspecieId(UUID principioAtivoId, UUID especieId);

    // Para a validação de segurança
    @Query("SELECT c FROM Contraindicacao c WHERE c.principioAtivo.id = :principioId AND c.especieId = :especieId")
    List<Contraindicacao> encontrarRiscos(UUID principioId, UUID especieId);
}

```

### RepositorioDoseReferencia.java

```java
// src\main\java\br\com\vestris\pharmacology\domain\repository\RepositorioDoseReferencia.java
package br.com.vestris.pharmacology.domain.repository;

import br.com.vestris.pharmacology.domain.model.DoseReferencia;
import br.com.vestris.pharmacology.domain.model.enums.ViaAdministracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioDoseReferencia extends JpaRepository<DoseReferencia, UUID> {
    List<DoseReferencia> findByMedicamentoId(UUID medicamentoId);
}

```

### RepositorioMedicamento.java

```java
// src\main\java\br\com\vestris\pharmacology\domain\repository\RepositorioMedicamento.java
package br.com.vestris.pharmacology.domain.repository;

import br.com.vestris.pharmacology.domain.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositorioMedicamento extends JpaRepository<Medicamento, UUID> {

}

```

### RepositorioPrincipioAtivo.java

```java
// src\main\java\br\com\vestris\pharmacology\domain\repository\RepositorioPrincipioAtivo.java
package br.com.vestris.pharmacology.domain.repository;

import br.com.vestris.pharmacology.domain.model.PrincipioAtivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositorioPrincipioAtivo extends JpaRepository<PrincipioAtivo, UUID> {
    boolean existsByNome(String nome);
}


```

---

## src\main\java\br\com\vestris\pharmacology\interfaces\delegate

### ApiDelegateContraindicacoes.java

```java
// src\main\java\br\com\vestris\pharmacology\interfaces\delegate\ApiDelegateContraindicacoes.java
package br.com.vestris.pharmacology.interfaces.delegate;

import br.com.vestris.pharmacology.application.ServiceContraindicacao;
import br.com.vestris.pharmacology.domain.model.Contraindicacao;
import br.com.vestris.pharmacology.interfaces.api.ContraindicacoesApiDelegate;
import br.com.vestris.pharmacology.interfaces.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateContraindicacoes implements ContraindicacoesApiDelegate {

    private final ServiceContraindicacao servico;

    @Override
    public ResponseEntity<ApiResponseContraindicacao> criarContraindicacao(ContraindicacaoRequest request) {
        Contraindicacao.Gravidade gravidade = Contraindicacao.Gravidade.valueOf(request.getGravidade().name());

        // Captura a referência ou define um padrão caso venha nulo
        String referencia = request.getReferenciaTexto() != null ? request.getReferenciaTexto() : "Referência interna";

        // Chama o serviço passando tanto Medicamento quanto Princípio.
        // O Serviço deve ter a assinatura: criar(UUID medId, UUID principioId, UUID espId, String ref, Gravidade g, String desc)
        // Nota: request.getPrincipioAtivoId() só funcionará se você adicionou este campo no YAML do Swagger.
        // Se ainda não adicionou, o Java não encontrará o método getter.

        UUID principioId = null;
        // Tenta obter o ID do princípio se o DTO gerado tiver o método (depende da sua atualização no YAML)
        try {
            // principioId = request.getPrincipioAtivoId(); // Descomente se o método existir
        } catch (Exception e) {
            // Ignora se não existir no DTO ainda
        }

        Contraindicacao salvo = servico.criar(
                request.getMedicamentoId().get(), // Pode ser null se vier pelo admin de principios
                principioId,                // Novo campo (prioritário)
                request.getEspecieId(),
                referencia,
                gravidade,
                request.getDescricao()
        );

        ApiResponseContraindicacao response = new ApiResponseContraindicacao();
        response.setSucesso(true);
        response.setDados(converter(salvo));

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseListaContraindicacao> listarContraindicacoesPorMedicamento(UUID medicamentoId) {
        List<ContraindicacaoResponse> lista = servico.listarPorMedicamento(medicamentoId).stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaContraindicacao response = new ApiResponseListaContraindicacao();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseContraindicacao> buscarContraindicacaoPorId(UUID id) {
        Contraindicacao encontrada = servico.buscarPorId(id);

        ApiResponseContraindicacao response = new ApiResponseContraindicacao();
        response.setSucesso(true);
        response.setDados(converter(encontrada));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseContraindicacao> atualizarContraindicacao(UUID id, ContraindicacaoRequest request) {
        Contraindicacao.Gravidade gravidadeDominio = Contraindicacao.Gravidade.valueOf(request.getGravidade().name());

        String referencia = request.getReferenciaTexto() != null ? request.getReferenciaTexto() : "Referência não informada";

        // O método atualizar no serviço precisa aceitar a referência String agora
        Contraindicacao atualizada = servico.atualizar(
                id,
                request.getEspecieId(),
                referencia,
                gravidadeDominio,
                request.getDescricao()
        );

        ApiResponseContraindicacao response = new ApiResponseContraindicacao();
        response.setSucesso(true);
        response.setMensagem("Contraindicação atualizada.");
        response.setDados(converter(atualizada));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deletarContraindicacao(UUID id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // --- CONVERSOR ---
    private ContraindicacaoResponse converter(Contraindicacao c) {
        ContraindicacaoResponse dto = new ContraindicacaoResponse();
        dto.setId(c.getId());

        // IMPORTANTE: O Frontend espera um ID no campo "medicamentoId" para links.
        // Como a contraindicação agora é baseada em princípio ativo, retornamos o ID do princípio
        // neste campo para manter a compatibilidade visual, ou null se preferir.
        if (c.getPrincipioAtivo() != null) {
            dto.setMedicamentoId(c.getPrincipioAtivo().getId());
        }

        dto.setEspecieId(c.getEspecieId());

        // Se você atualizou o response DTO para ter referenciaTexto, use:
        // dto.setReferenciaTexto(c.getReferenciaTexto());
        // Caso contrário, mantemos referenciaId como null pois mudamos para texto
        dto.setReferenciaId(null);

        dto.setDescricao(c.getDescricao());
        dto.setGravidade(GravidadeEnum.valueOf(c.getGravidade().name()));
        return dto;
    }
}

```

### ApiDelegateMedicamentos.java

```java
// src\main\java\br\com\vestris\pharmacology\interfaces\delegate\ApiDelegateMedicamentos.java
package br.com.vestris.pharmacology.interfaces.delegate;

import br.com.vestris.pharmacology.application.ServiceFarmacologia;
import br.com.vestris.pharmacology.domain.model.Medicamento;
import br.com.vestris.pharmacology.interfaces.api.MedicamentosApiDelegate;
import br.com.vestris.pharmacology.interfaces.dto.ApiResponseListaMedicamento;
import br.com.vestris.pharmacology.interfaces.dto.ApiResponseMedicamento;
import br.com.vestris.pharmacology.interfaces.dto.MedicamentoRequest;
import br.com.vestris.pharmacology.interfaces.dto.MedicamentoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateMedicamentos implements MedicamentosApiDelegate {
    private final ServiceFarmacologia servico;

    @Override
    public ResponseEntity<ApiResponseMedicamento> criarMedicamento(MedicamentoRequest request) {
        Medicamento entidade = new Medicamento();
        entidade.setNome(request.getNome());
        entidade.setConcentracao(request.getConcentracao());
        entidade.setFabricante(request.getFabricante());
        entidade.setFormaFarmaceutica(request.getFormaFarmaceutica());

        Medicamento salvo = servico.criarMedicamento(entidade, request.getPrincipioAtivoId());

        ApiResponseMedicamento response = new ApiResponseMedicamento();
        response.setSucesso(true);
        response.setDados(converter(salvo));

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseListaMedicamento> listarMedicamentos() {
        List<MedicamentoResponse> lista = servico.listarMedicamentos().stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaMedicamento response = new ApiResponseListaMedicamento();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseMedicamento> buscarMedicamentoPorId(UUID id) {
        Medicamento med = servico.buscarMedicamentoPorId(id);

        ApiResponseMedicamento response = new ApiResponseMedicamento();
        response.setSucesso(true);
        response.setDados(converter(med));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseMedicamento> atualizarMedicamento(UUID id, MedicamentoRequest request) {
        Medicamento dados = new Medicamento();
        dados.setNome(request.getNome());
        dados.setConcentracao(request.getConcentracao());
        dados.setFabricante(request.getFabricante());
        dados.setFormaFarmaceutica(request.getFormaFarmaceutica());

        Medicamento atualizado = servico.atualizarMedicamento(id, dados, request.getPrincipioAtivoId());

        ApiResponseMedicamento response = new ApiResponseMedicamento();
        response.setSucesso(true);
        response.setMensagem("Medicamento atualizado.");
        response.setDados(converter(atualizado));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deletarMedicamento(UUID id) {
        servico.deletarMedicamento(id);
        return ResponseEntity.noContent().build();
    }

    // --- CONVERSOR ATUALIZADO ---
    private MedicamentoResponse converter(Medicamento med) {
        MedicamentoResponse dto = new MedicamentoResponse();
        dto.setId(med.getId());
        dto.setNome(med.getNome());
        dto.setConcentracao(med.getConcentracao());
        dto.setFabricante(med.getFabricante());
        dto.setFormaFarmaceutica(med.getFormaFarmaceutica());

        // Mapeamento do Princípio Ativo
        if (med.getPrincipioAtivo() != null) {
            dto.setPrincipioAtivoId(med.getPrincipioAtivo().getId());
            // CORREÇÃO: Preenchendo o nome que faltava
            dto.setPrincipioAtivoNome(med.getPrincipioAtivo().getNome());
        }

        return dto;
    }
}

```

### ApiDelegatePrincipioAtivo.java

```java
// src\main\java\br\com\vestris\pharmacology\interfaces\delegate\ApiDelegatePrincipioAtivo.java
package br.com.vestris.pharmacology.interfaces.delegate;

import br.com.vestris.pharmacology.application.ServiceFarmacologia;
import br.com.vestris.pharmacology.domain.model.PrincipioAtivo;
import br.com.vestris.pharmacology.interfaces.api.PrincipiosAtivosApiDelegate;
import br.com.vestris.pharmacology.interfaces.dto.ApiResponseListaPrincipioAtivo;
import br.com.vestris.pharmacology.interfaces.dto.ApiResponsePrincipioAtivo;
import br.com.vestris.pharmacology.interfaces.dto.PrincipioAtivoRequest;
import br.com.vestris.pharmacology.interfaces.dto.PrincipioAtivoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegatePrincipioAtivo implements PrincipiosAtivosApiDelegate {
    private final ServiceFarmacologia servico;

    @Override
    public ResponseEntity<ApiResponsePrincipioAtivo> criarPrincipioAtivo(PrincipioAtivoRequest request) {
        PrincipioAtivo entidade = new PrincipioAtivo();
        entidade.setNome(request.getNome());
        entidade.setDescricao(request.getDescricao());
        entidade.setGrupoFarmacologico(request.getGrupoFarmacologico());

        PrincipioAtivo salvo = servico.criarPrincipio(entidade);

        ApiResponsePrincipioAtivo response = new ApiResponsePrincipioAtivo();
        response.setSucesso(true);
        response.setDados(converter(salvo));

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseListaPrincipioAtivo> listarPrincipiosAtivos() {
        List<PrincipioAtivoResponse> lista = servico.listarPrincipios().stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaPrincipioAtivo response = new ApiResponseListaPrincipioAtivo();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponsePrincipioAtivo> buscarPrincipioAtivoPorId(UUID id) {
        PrincipioAtivo pa = servico.buscarPrincipioPorId(id);

        ApiResponsePrincipioAtivo response = new ApiResponsePrincipioAtivo();
        response.setSucesso(true);
        response.setDados(converter(pa));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponsePrincipioAtivo> atualizarPrincipioAtivo(UUID id, PrincipioAtivoRequest request) {
        PrincipioAtivo dados = new PrincipioAtivo();
        dados.setNome(request.getNome());
        dados.setDescricao(request.getDescricao());
        dados.setGrupoFarmacologico(request.getGrupoFarmacologico());

        PrincipioAtivo atualizado = servico.atualizarPrincipio(id, dados);

        ApiResponsePrincipioAtivo response = new ApiResponsePrincipioAtivo();
        response.setSucesso(true);
        response.setMensagem("Atualizado com sucesso.");
        response.setDados(converter(atualizado));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deletarPrincipioAtivo(UUID id) {
        servico.deletarPrincipio(id);
        return ResponseEntity.noContent().build();
    }

    // ... converter ...
    private PrincipioAtivoResponse converter(PrincipioAtivo pa) {
        PrincipioAtivoResponse dto = new PrincipioAtivoResponse();
        dto.setId(pa.getId());
        dto.setNome(pa.getNome());
        dto.setDescricao(pa.getDescricao());
        dto.setGrupoFarmacologico(pa.getGrupoFarmacologico());
        return dto;
    }


}

```

### ApiDelegateSegurancaClinica.java

```java
// src\main\java\br\com\vestris\pharmacology\interfaces\delegate\ApiDelegateSegurancaClinica.java
package br.com.vestris.pharmacology.interfaces.delegate;

import br.com.vestris.pharmacology.application.ServiceSegurancaFarmacologica;
import br.com.vestris.pharmacology.domain.model.Contraindicacao;
import br.com.vestris.pharmacology.interfaces.api.SegurancaClinicaApiDelegate;
import br.com.vestris.pharmacology.interfaces.dto.AlertaSeguranca;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateSegurancaClinica implements SegurancaClinicaApiDelegate {

    private final ServiceSegurancaFarmacologica servico;

    @Override
    public ResponseEntity<List<AlertaSeguranca>> validarSeguranca(UUID medicamentoId, UUID especieId) {

        List<Contraindicacao> riscos = servico.validarMedicamentoParaEspecie(medicamentoId, especieId);

        List<AlertaSeguranca> alertas = riscos.stream().map(c -> {
            AlertaSeguranca dto = new AlertaSeguranca();

            // Convertendo Enum do Domínio para String do DTO
            dto.setNivel(AlertaSeguranca.NivelEnum.fromValue(c.getGravidade().name()));
            dto.setDescricao(c.getDescricao());

            // CORREÇÃO: Como não temos o medicamento específico na entidade Contraindicacao,
            // informamos que o risco é global para aquele princípio.
            dto.setMedicamento("Todos contendo " + c.getPrincipioAtivo().getNome());

            dto.setPrincipioAtivo(c.getPrincipioAtivo().getNome());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(alertas);
    }
}

```

---

## src\main\resources\swagger

### openapi.yml

```yaml
# src\main\resources\swagger\openapi.yml
openapi: 3.0.3
info:
  title: Vestris - Módulo Farmacologia
  description: Gestão de Medicamentos e Princípios Ativos
  version: 1.0.0
servers:
  - url: http://localhost:8080
    description: Servidor Local

paths:
  # --- PRINCÍPIOS ATIVOS ---
  /api/v1/principios-ativos:
    $ref: './paths/principios-ativos.yml#/principios_colecao'

  /api/v1/principios-ativos/{id}:
    $ref: './paths/principios-ativos.yml#/principios_item'

  # --- MEDICAMENTOS ---
  /api/v1/medicamentos:
    $ref: './paths/medicamentos.yml#/medicamentos_colecao'

  /api/v1/medicamentos/{id}:
    $ref: './paths/medicamentos.yml#/medicamentos_item'

  # --- CONTRAINDICAÇÕES ---
  /api/v1/contraindicacoes:
    $ref: './paths/contraindicacoes.yml#/contraindicacoes_colecao'

  /api/v1/medicamentos/{medicamentoId}/contraindicacoes:
    $ref: './paths/contraindicacoes.yml#/contraindicacoes_por_medicamento'

  /api/v1/contraindicacoes/{id}:
    $ref: './paths/contraindicacoes.yml#/contraindicacoes_por_id'

  /api/v1/seguranca/validar:
    $ref: './paths/contraindicacoes.yml#/validar_seguranca'

# Components (apenas referenciando o arquivo de schemas que você já tem)
components:
  schemas:
    PrincipioAtivoRequest:
      $ref: "./components/schemas.yml#/PrincipioAtivoRequest"
    # ... O Swagger vai resolver os outros automaticamente através das refs nos paths
    # Mas se quiser garantir que apareçam na home do Swagger UI, liste todos aqui:
    PrincipioAtivoResponse:
      $ref: "./components/schemas.yml#/PrincipioAtivoResponse"
    MedicamentoRequest:
      $ref: "./components/schemas.yml#/MedicamentoRequest"
    MedicamentoResponse:
      $ref: "./components/schemas.yml#/MedicamentoResponse"
    ContraindicacaoRequest:
      $ref: "./components/schemas.yml#/ContraindicacaoRequest"
    ContraindicacaoResponse:
      $ref: "./components/schemas.yml#/ContraindicacaoResponse"
    AlertaSeguranca:
      $ref: "./components/schemas.yml#/AlertaSeguranca"
    ApiResponsePrincipioAtivo:
      $ref: "./components/schemas.yml#/ApiResponsePrincipioAtivo"
    ApiResponseListaPrincipioAtivo:
      $ref: "./components/schemas.yml#/ApiResponseListaPrincipioAtivo"
    ApiResponseMedicamento:
      $ref: "./components/schemas.yml#/ApiResponseMedicamento"
    ApiResponseListaMedicamento:
      $ref: "./components/schemas.yml#/ApiResponseListaMedicamento"
    ApiResponseContraindicacao:
      $ref: "./components/schemas.yml#/ApiResponseContraindicacao"
    ApiResponseListaContraindicacao:
      $ref: "./components/schemas.yml#/ApiResponseListaContraindicacao"
```

---

## src\main\resources\swagger\components

### schemas.yml

```yaml
# src\main\resources\swagger\components\schemas.yml
PrincipioAtivoRequest:
  type: object
  required: [nome]
  properties:
    nome:
      type: string
      example: "Enrofloxacina"
    descricao:
      type: string
    grupoFarmacologico:
      type: string
      example: "Antibiótico / Fluoroquinolona"

PrincipioAtivoResponse:
  type: object
  properties:
    id:
      type: string
      format: uuid
    nome:
      type: string
    descricao:
      type: string
    grupoFarmacologico:
      type: string

# --- DTOs Medicamento ---
MedicamentoRequest:
  type: object
  required: [nome, principioAtivoId]
  properties:
    nome:
      type: string
      example: "Baytril 10%"
    principioAtivoId:
      type: string
      format: uuid
    principioAtivoNome:
      type: string
    concentracao:
      type: string
      example: "100 mg/ml"
    fabricante:
      type: string
    formaFarmaceutica:
      type: string
      example: "Injetável / Oral"

MedicamentoResponse:
  type: object
  properties:
    id:
      type: string
      format: uuid
    nome:
      type: string
    principioAtivoId:
      type: string
      format: uuid
    principioAtivoNome:
      type: string
    concentracao:
      type: string
    fabricante:
      type: string
    formaFarmaceutica:
      type: string
GravidadeEnum:
  type: string
  enum: [LEVE, MODERADA, GRAVE, FATAL]

ContraindicacaoRequest:
  type: object
  required: [ especieId, gravidade, descricao ] # MedicamentoId não é mais obrigatório se tiver Principio
  properties:
    medicamentoId:
      type: string
      format: uuid
      nullable: true
    principioAtivoId:   # <--- CAMPO NOVO
      type: string
      format: uuid
      nullable: true
    especieId:
      type: string
      format: uuid
    referenciaId:       # <--- PODE REMOVER OU DEIXAR COMO LEGADO
      type: string
      format: uuid
      nullable: true
    referenciaTexto:    # <--- CAMPO NOVO PREFERENCIAL
      type: string
    gravidade:
      $ref: '#/GravidadeEnum'
    descricao:
      type: string
      example: "Causa toxicidade neurológica severa."

ContraindicacaoResponse:
  type: object
  properties:
    id:
      type: string
      format: uuid
    medicamentoId:
      type: string
      format: uuid
    especieId:
      type: string
      format: uuid
    referenciaId:
      type: string
      format: uuid
    gravidade:
      $ref: '#/GravidadeEnum'
    descricao:
      type: string

AlertaSeguranca:
  type: object
  properties:
    nivel: { type: string, enum: [LEVE, MODERADA, GRAVE, FATAL] }
    descricao: { type: string }
    medicamento: { type: string }
    principioAtivo: { type: string }

  # Wrappers
ApiResponseContraindicacao:
  type: object
  properties:
    sucesso: { type: boolean }
    mensagem:
      type: string
    dados: { $ref: '#/ContraindicacaoResponse' }

ApiResponseListaContraindicacao:
  type: object
  properties:
    sucesso: { type: boolean }
    dados:
      type: array
      items: { $ref: '#/ContraindicacaoResponse' }

ApiResponsePrincipioAtivo:
  type: object
  properties:
    sucesso: { type: boolean }
    mensagem:
      type: string
    dados: { $ref: '#/PrincipioAtivoResponse' }

ApiResponseListaPrincipioAtivo:
  type: object
  properties:
    sucesso: { type: boolean }
    dados:
      type: array
      items: { $ref: '#/PrincipioAtivoResponse' }

ApiResponseMedicamento:
  type: object
  properties:
    sucesso: { type: boolean }
    mensagem:
      type:  string
    dados: { $ref: '#/MedicamentoResponse' }

ApiResponseListaMedicamento:
  type: object
  properties:
    sucesso: { type: boolean }
    dados:
      type: array
      items: { $ref: '#/MedicamentoResponse' }
```

---

## src\main\resources\swagger\paths

### contraindicacoes.yml

```yaml
# src\main\resources\swagger\paths\contraindicacoes.yml
# Rota: /api/v1/contraindicacoes
contraindicacoes_colecao:
  post:
    tags:
      - Contraindicacoes
    summary: Cadastrar contraindicação
    operationId: criarContraindicacao
    requestBody:
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/ContraindicacaoRequest'
    responses:
      '200':
        description: Sucesso
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseContraindicacao'

# Rota: /api/v1/medicamentos/{medicamentoId}/contraindicacoes
contraindicacoes_por_medicamento:
  get:
    tags:
      - Contraindicacoes
    summary: Listar contraindicações de um medicamento
    operationId: listarContraindicacoesPorMedicamento
    parameters:
      - name: medicamentoId
        in: path
        required: true
        schema:
          type: string
          format: uuid
    responses:
      '200':
        description: Lista
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseListaContraindicacao'

# Rota: /api/v1/contraindicacoes/{id}
contraindicacoes_por_id:
  parameters:
    - name: id
      in: path
      required: true
      schema:
        type: string
        format: uuid
  get:
    tags: [ Contraindicacoes ]
    summary: Buscar por ID
    operationId: buscarContraindicacaoPorId
    responses:
      '200':
        description: Encontrado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseContraindicacao' }
  put:
    tags: [ Contraindicacoes ]
    summary: Atualizar
    operationId: atualizarContraindicacao
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/ContraindicacaoRequest' }
    responses:
      '200':
        description: Atualizado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseContraindicacao' }
  delete:
    tags: [ Contraindicacoes ]
    summary: Remover
    operationId: deletarContraindicacao
    responses:
      '204': { description: Removido }

# ROTA: /api/v1/seguranca/validar
validar_seguranca:
  get:
    tags: [SegurancaClinica]
    summary: Validar medicamento para espécie
    operationId: validarSeguranca
    parameters:
      - name: medicamentoId
        in: query
        required: true
        schema: { type: string, format: uuid }
      - name: especieId
        in: query
        required: true
        schema: { type: string, format: uuid }
    responses:
      '200':
        description: Lista de alertas (vazio se seguro)
        content:
          application/json:
            schema:
              type: array
              items: { $ref: '../components/schemas.yml#/AlertaSeguranca' }



```

### medicamentos.yml

```yaml
# src\main\resources\swagger\paths\medicamentos.yml
# Rota: /api/v1/medicamentos
medicamentos_colecao:
  post:
    tags:
      - Medicamentos
    summary: Cadastrar medicamento
    operationId: criarMedicamento
    requestBody:
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/MedicamentoRequest'
    responses:
      '200':
        description: Sucesso
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseMedicamento'
  get:
    tags:
      - Medicamentos
    summary: Listar medicamentos
    operationId: listarMedicamentos
    responses:
      '200':
        description: Lista
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseListaMedicamento'

# Rota: /api/v1/medicamentos/{id}
medicamentos_item:
  parameters:
    - name: id
      in: path
      required: true
      schema:
        type: string
        format: uuid
  get:
    tags:
      - Medicamentos
    summary: Buscar por ID
    operationId: buscarMedicamentoPorId
    responses:
      '200':
        description: Encontrado
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseMedicamento'
  put:
    tags:
      - Medicamentos
    summary: Atualizar
    operationId: atualizarMedicamento
    requestBody:
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/MedicamentoRequest'
    responses:
      '200':
        description: Atualizado
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseMedicamento'
  delete:
    tags:
      - Medicamentos
    summary: Remover
    operationId: deletarMedicamento
    responses:
      '204':
        description: Removido
```

### principios-ativos.yml

```yaml
# src\main\resources\swagger\paths\principios-ativos.yml
# Rota: /api/v1/principios-ativos
principios_colecao:
  post:
    tags:
      - PrincipiosAtivos
    summary: Cadastrar princípio ativo
    operationId: criarPrincipioAtivo
    requestBody:
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/PrincipioAtivoRequest'
    responses:
      '200':
        description: Sucesso
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponsePrincipioAtivo'

  get:
    tags:
      - PrincipiosAtivos
    summary: Listar todos
    operationId: listarPrincipiosAtivos
    responses:
      '200':
        description: Lista
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseListaPrincipioAtivo'

# Rota: /api/v1/principios-ativos/{id}
principios_item:
  parameters:
    - name: id
      in: path
      required: true
      schema:
        type: string
        format: uuid
  get:
    tags:
      - PrincipiosAtivos
    summary: Buscar por ID
    operationId: buscarPrincipioAtivoPorId
    responses:
      '200':
        description: Encontrado
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponsePrincipioAtivo'
  put:
    tags:
      - PrincipiosAtivos
    summary: Atualizar
    operationId: atualizarPrincipioAtivo
    requestBody:
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/PrincipioAtivoRequest'
    responses:
      '200':
        description: Atualizado
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponsePrincipioAtivo'
  delete:
    tags:
      - PrincipiosAtivos
    summary: Remover
    description: "Não permite se houver medicamentos vinculados"
    operationId: deletarPrincipioAtivo
    responses:
      '204':
        description: Removido
```

