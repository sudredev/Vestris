# Documentação do projeto vestris-medical-record

## Índice de Diretórios e Arquivos

- [.](#)
  - [pom.xml](#pomxml)
- [src\main\java\br\com\vestris\medicalrecord\application](#srcmainjavabrcomvestrismedicalrecordapplication)
  - [ServiceAtendimento.java](#srcmainjavabrcomvestrismedicalrecordapplicationserviceatendimentojava)
  - [ServiceExames.java](#srcmainjavabrcomvestrismedicalrecordapplicationserviceexamesjava)
  - [ServicePaciente.java](#srcmainjavabrcomvestrismedicalrecordapplicationservicepacientejava)
- [src\main\java\br\com\vestris\medicalrecord\domain\model](#srcmainjavabrcomvestrismedicalrecorddomainmodel)
  - [Atendimento.java](#srcmainjavabrcomvestrismedicalrecorddomainmodelatendimentojava)
  - [ExameAnexo.java](#srcmainjavabrcomvestrismedicalrecorddomainmodelexameanexojava)
  - [Paciente.java](#srcmainjavabrcomvestrismedicalrecorddomainmodelpacientejava)
- [src\main\java\br\com\vestris\medicalrecord\domain\repository](#srcmainjavabrcomvestrismedicalrecorddomainrepository)
  - [RepositorioAtendimento.java](#srcmainjavabrcomvestrismedicalrecorddomainrepositoryrepositorioatendimentojava)
  - [RepositorioExameAnexo.java](#srcmainjavabrcomvestrismedicalrecorddomainrepositoryrepositorioexameanexojava)
  - [RepositorioPaciente.java](#srcmainjavabrcomvestrismedicalrecorddomainrepositoryrepositoriopacientejava)
- [src\main\java\br\com\vestris\medicalrecord\interfaces\delegate](#srcmainjavabrcomvestrismedicalrecordinterfacesdelegate)
  - [ApiDelegateAtendimentos.java](#srcmainjavabrcomvestrismedicalrecordinterfacesdelegateapidelegateatendimentosjava)
  - [ApiDelegateExame.java](#srcmainjavabrcomvestrismedicalrecordinterfacesdelegateapidelegateexamejava)
  - [ApiDelegatePacientes.java](#srcmainjavabrcomvestrismedicalrecordinterfacesdelegateapidelegatepacientesjava)
- [src\main\resources\swagger](#srcmainresourcesswagger)
  - [openapi.yml](#srcmainresourcesswaggeropenapiyml)
- [src\main\resources\swagger\components](#srcmainresourcesswaggercomponents)
  - [schemas.yml](#srcmainresourcesswaggercomponentsschemasyml)
- [src\main\resources\swagger\paths](#srcmainresourcesswaggerpaths)
  - [atendimentos.yml](#srcmainresourcesswaggerpathsatendimentosyml)
  - [exames-anexo.yml](#srcmainresourcesswaggerpathsexames-anexoyml)
  - [pacientes.yml](#srcmainresourcesswaggerpathspacientesyml)

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

    <artifactId>vestris-medical-record</artifactId>

    <dependencies>
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-shared</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <!-- 1. Para vincular ao Veterinário -->
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-user</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <!-- 2. Para vincular a Espécie -->
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-species</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <!-- 3. Para vincular Protocolos (Opcional) -->
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-clinical</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <!-- Dependências Padrão -->
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
                            <apiPackage>br.com.vestris.medicalrecord.interfaces.api</apiPackage>
                            <modelPackage>br.com.vestris.medicalrecord.interfaces.dto</modelPackage>

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

---

## src\main\java\br\com\vestris\medicalrecord\application

### ServiceAtendimento.java

```java
// src\main\java\br\com\vestris\medicalrecord\application\ServiceAtendimento.java
package br.com.vestris.medicalrecord.application;

import br.com.vestris.medicalrecord.domain.model.Atendimento;
import br.com.vestris.medicalrecord.domain.model.Paciente;
import br.com.vestris.medicalrecord.domain.repository.RepositorioAtendimento;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.shared.infrastructure.helper.HelperAuditoria;
import br.com.vestris.user.application.ServiceAuditoria;
import br.com.vestris.user.application.ServiceUsuario;
import br.com.vestris.user.domain.model.AcaoAuditoria;
import br.com.vestris.user.domain.model.EntidadeAuditoria;
import br.com.vestris.user.domain.model.Usuario;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceAtendimento {
    private final RepositorioAtendimento repositorio;
    private final ServicePaciente servicoPaciente;
    private final ServiceUsuario servicoUsuario;
    private final ServiceAuditoria servicoAuditoria;
    private final HelperAuditoria helperAuditoria;

    // --- LISTAGEM COM FILTROS DINÂMICOS E COMPARTILHAMENTO ---
    public List<Atendimento> listar(UUID usuarioLogadoId, Atendimento.StatusAtendimento status, UUID pacienteId, LocalDate dataInicio, LocalDate dataFim) {
        Usuario usuario = servicoUsuario.buscarPorId(usuarioLogadoId);
        List<UUID> idsPermitidos = new ArrayList<>();

        if (usuario.getClinica() != null) {
            List<Usuario> equipe = servicoUsuario.listarPorClinica(usuario.getClinica().getId());
            idsPermitidos = equipe.stream().map(Usuario::getId).collect(Collectors.toList());
        } else {
            idsPermitidos.add(usuarioLogadoId);
        }

        List<UUID> finalIdsPermitidos = idsPermitidos;
        Specification<Atendimento> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(root.get("veterinarioId").in(finalIdsPermitidos));

            if (status != null) predicates.add(cb.equal(root.get("status"), status));
            if (pacienteId != null) predicates.add(cb.equal(root.get("paciente").get("id"), pacienteId));
            if (dataInicio != null) predicates.add(cb.greaterThanOrEqualTo(root.get("dataHora"), dataInicio.atStartOfDay()));
            if (dataFim != null) predicates.add(cb.lessThanOrEqualTo(root.get("dataHora"), dataFim.atTime(LocalTime.MAX)));

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return repositorio.findAll(spec, Sort.by(Sort.Direction.ASC, "dataHora"));
    }

    public Atendimento criar(Atendimento novo, UUID pacienteId) {
        Paciente paciente = servicoPaciente.buscarPorId(pacienteId);

        if (!servicoUsuario.existePorId(novo.getVeterinarioId())) {
            throw new ExcecaoRegraNegocio("Veterinário responsável não encontrado.");
        }

        novo.setPaciente(paciente);
        if (novo.getStatus() == null) novo.setStatus(Atendimento.StatusAtendimento.AGENDADO);
        if (novo.getTitulo() == null) novo.setTitulo("Atendimento Clínico");
        if (novo.getDataHora() == null) novo.setDataHora(LocalDateTime.now());

        Atendimento salvo = repositorio.save(novo);

        try {
            Usuario vet = servicoUsuario.buscarPorId(novo.getVeterinarioId());
            if (vet.getClinica() != null) {
                servicoAuditoria.registrar(
                        vet.getClinica().getId(),
                        vet.getId(),
                        AcaoAuditoria.ATENDIMENTO_AGENDADO,
                        EntidadeAuditoria.ATENDIMENTO,
                        salvo.getId(),
                        "Agendamento criado: " + novo.getTitulo(),
                        helperAuditoria.montarMetadadosAtendimento(paciente.getNome(), "AGENDADO", vet.getId())
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return salvo;
    }

    public List<Atendimento> listarPorPaciente(UUID pacienteId) {
        if (!servicoPaciente.existePorId(pacienteId)) {
            throw new ExceptionRecursoNaoEncontrado("Paciente", pacienteId.toString());
        }
        return repositorio.findByPacienteIdOrderByCriadoEmDesc(pacienteId);
    }

    public Atendimento buscarPorId(UUID id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Atendimento", id.toString()));
    }

    public Atendimento atualizar(UUID id, Atendimento dados) {
        Atendimento existente = buscarPorId(id);
        existente.setTitulo(dados.getTitulo());
        existente.setQueixaPrincipal(dados.getQueixaPrincipal());
        existente.setHistoricoClinico(dados.getHistoricoClinico());
        existente.setExameFisico(dados.getExameFisico());
        existente.setDiagnostico(dados.getDiagnostico());
        existente.setCondutaClinica(dados.getCondutaClinica());
        existente.setObservacoes(dados.getObservacoes());
        existente.setProtocoloId(dados.getProtocoloId());
        existente.setOrientacoesManejo(dados.getOrientacoesManejo());

        if (dados.getStatus() != null) {
            existente.setStatus(dados.getStatus());
        }
        Atendimento salvo = repositorio.save(existente);

        try {
            Usuario vet = servicoUsuario.buscarPorId(existente.getVeterinarioId());
            if (vet != null && vet.getClinica() != null) {
                servicoAuditoria.registrar(
                        vet.getClinica().getId(),
                        vet.getId(),
                        AcaoAuditoria.PRONTUARIO_EDITADO,
                        EntidadeAuditoria.ATENDIMENTO,
                        salvo.getId(),
                        "Prontuário editado",
                        helperAuditoria.montarMetadados("campos", "dados_clinicos")
                );
            }
        } catch (Exception e) {}

        return salvo;
    }

    public Atendimento atualizarStatus(UUID id, Atendimento.StatusAtendimento novoStatus) {
        Atendimento a = buscarPorId(id);
        a.setStatus(novoStatus);
        Atendimento salvo = repositorio.save(a);

        try {
            Usuario vet = servicoUsuario.buscarPorId(salvo.getVeterinarioId());
            if (vet != null && vet.getClinica() != null) {
                AcaoAuditoria acao = novoStatus == Atendimento.StatusAtendimento.CANCELADO ?
                        AcaoAuditoria.ATENDIMENTO_CANCELADO : AcaoAuditoria.ATENDIMENTO_EDITADO;

                servicoAuditoria.registrar(
                        vet.getClinica().getId(),
                        vet.getId(),
                        acao,
                        EntidadeAuditoria.ATENDIMENTO,
                        salvo.getId(),
                        "Mudança de status para " + novoStatus,
                        null
                );
            }
        } catch (Exception e) {}

        return salvo;
    }

    public Atendimento finalizar(UUID id, Atendimento dadosClinicos) {
        Atendimento existente = buscarPorId(id);
        existente.setTitulo(dadosClinicos.getTitulo() != null ? dadosClinicos.getTitulo() : existente.getTitulo());
        existente.setQueixaPrincipal(dadosClinicos.getQueixaPrincipal());
        existente.setHistoricoClinico(dadosClinicos.getHistoricoClinico());
        existente.setExameFisico(dadosClinicos.getExameFisico());
        existente.setDiagnostico(dadosClinicos.getDiagnostico());
        existente.setCondutaClinica(dadosClinicos.getCondutaClinica());
        existente.setObservacoes(dadosClinicos.getObservacoes());
        existente.setProtocoloId(dadosClinicos.getProtocoloId());
        existente.setOrientacoesManejo(dadosClinicos.getOrientacoesManejo());

        existente.setStatus(Atendimento.StatusAtendimento.REALIZADO);
        Atendimento salvo = repositorio.save(existente);

        try {
            Usuario vet = servicoUsuario.buscarPorId(salvo.getVeterinarioId());
            if (vet != null && vet.getClinica() != null) {
                servicoAuditoria.registrar(
                        vet.getClinica().getId(),
                        vet.getId(),
                        AcaoAuditoria.ATENDIMENTO_FINALIZADO,
                        EntidadeAuditoria.ATENDIMENTO,
                        salvo.getId(),
                        "Atendimento finalizado",
                        null
                );
            }
        } catch (Exception e) {}

        return salvo;
    }
}

```

### ServiceExames.java

```java
// src\main\java\br\com\vestris\medicalrecord\application\ServiceExames.java
package br.com.vestris.medicalrecord.application;

import br.com.vestris.medicalrecord.domain.model.ExameAnexo;
import br.com.vestris.medicalrecord.domain.repository.RepositorioExameAnexo;
import br.com.vestris.medicalrecord.domain.repository.RepositorioAtendimento;
import br.com.vestris.shared.infrastructure.helper.HelperAuditoria;
import br.com.vestris.user.application.ServiceAuditoria;
import br.com.vestris.user.application.ServiceUsuario;
import br.com.vestris.user.domain.model.AcaoAuditoria;
import br.com.vestris.user.domain.model.EntidadeAuditoria;
import br.com.vestris.user.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceExames {
    private final RepositorioExameAnexo repositorio;
    private final RepositorioAtendimento repositorioAtendimento;
    private final ServiceAuditoria servicoAuditoria;
    private final ServiceUsuario servicoUsuario;
    private final HelperAuditoria helperAuditoria;

    public ExameAnexo anexar(UUID atendimentoId, String nome, String tipo, String url, String obs) {
        ExameAnexo e = new ExameAnexo();
        e.setAtendimentoId(atendimentoId);
        e.setNomeArquivo(nome);
        e.setTipoArquivo(tipo);
        e.setUrlArquivo(url);
        e.setObservacoes(obs);

        ExameAnexo salvo = repositorio.save(e);

        try {
            var atendimento = repositorioAtendimento.findById(atendimentoId).orElse(null);
            if (atendimento != null) {
                Usuario vet = servicoUsuario.buscarPorId(atendimento.getVeterinarioId());
                if (vet != null && vet.getClinica() != null) {
                    var metadados = helperAuditoria.montarMetadados(
                            "nomeArquivo", nome,
                            "tipoArquivo", tipo,
                            "paciente", atendimento.getPaciente().getNome(),
                            "observacoes", obs
                    );
                    servicoAuditoria.registrar(
                            vet.getClinica().getId(),
                            vet.getId(),
                            AcaoAuditoria.ANEXO_ADICIONADO,
                            EntidadeAuditoria.ANEXO,
                            salvo.getId(),
                            "Anexo/Exame adicionado: " + nome,
                            metadados
                    );
                }
            }
        } catch (Exception ex) {
            System.err.println("Erro ao auditar adição de anexo: " + ex.getMessage());
        }

        return salvo;
    }

    public List<ExameAnexo> listarPorAtendimento(UUID atendimentoId) {
        return repositorio.findByAtendimentoId(atendimentoId);
    }

    public void deletar(UUID id) {
        var anexo = repositorio.findById(id).orElse(null);

        if (anexo != null) {
            repositorio.deleteById(id);

            try {
                var atendimento = repositorioAtendimento.findById(anexo.getAtendimentoId()).orElse(null);
                if (atendimento != null) {
                    Usuario vet = servicoUsuario.buscarPorId(atendimento.getVeterinarioId());
                    if (vet != null && vet.getClinica() != null) {
                        var metadados = helperAuditoria.montarMetadados(
                                "nomeArquivo", anexo.getNomeArquivo(),
                                "tipoArquivo", anexo.getTipoArquivo(),
                                "paciente", atendimento.getPaciente().getNome()
                        );
                        servicoAuditoria.registrar(
                                vet.getClinica().getId(),
                                vet.getId(),
                                AcaoAuditoria.ANEXO_REMOVIDO,
                                EntidadeAuditoria.ANEXO,
                                id,
                                "Anexo/Exame removido: " + anexo.getNomeArquivo(),
                                metadados
                        );
                    }
                }
            } catch (Exception ex) {
                System.err.println("Erro ao auditar remoção de anexo: " + ex.getMessage());
            }
        }
    }
}

```

### ServicePaciente.java

```java
// src\main\java\br\com\vestris\medicalrecord\application\ServicePaciente.java
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

```

---

## src\main\java\br\com\vestris\medicalrecord\domain\model

### Atendimento.java

```java
// src\main\java\br\com\vestris\medicalrecord\domain\model\Atendimento.java
package br.com.vestris.medicalrecord.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "atendimentos", schema = "medical_record_schema")
public class Atendimento extends EntidadeBase {
    @ManyToOne(optional = false)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Column(nullable = false)
    private UUID veterinarioId;

    private UUID protocoloId;

    // --- NOVOS CAMPOS ---
    @Column(nullable = false)
    private String titulo; // Ex: "Consulta Inicial", "Retorno", "Vacinação"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAtendimento status; // AGENDADO, REALIZADO, CANCELADO
    // --------------------

    // --- CORREÇÃO: ADICIONE ESTE CAMPO ---
    @Column(nullable = false)
    private LocalDateTime dataHora; // Data do agendamento ou da consulta
    // -------------------------------------

    @Column(columnDefinition = "TEXT") private String queixaPrincipal;
    @Column(columnDefinition = "TEXT") private String historicoClinico;
    @Column(columnDefinition = "TEXT") private String exameFisico;
    @Column(columnDefinition = "TEXT") private String diagnostico;
    @Column(columnDefinition = "TEXT") private String condutaClinica;
    @Column(columnDefinition = "TEXT") private String orientacoesManejo;
    @Column(columnDefinition = "TEXT") private String observacoes;

    // ADICIONE ESTE ENUM:
    public enum StatusAtendimento {
        AGENDADO,
        EM_ANDAMENTO,
        REALIZADO,
        CANCELADO
    }

}

```

### ExameAnexo.java

```java
// src\main\java\br\com\vestris\medicalrecord\domain\model\ExameAnexo.java
package br.com.vestris.medicalrecord.domain.model;

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
@Table(name = "exames_anexos", schema = "medical_record_schema")
public class ExameAnexo extends EntidadeBase {
    @Column(nullable = false)
    private UUID atendimentoId; // Vínculo com o atendimento

    @Column(nullable = false)
    private String nomeArquivo;

    private String tipoArquivo; // pdf, jpg, png

    @Column(columnDefinition = "TEXT")
    private String urlArquivo; // URL S3 ou Path Local

    @Column(columnDefinition = "TEXT")
    private String observacoes;
}

```

### Paciente.java

```java
// src\main\java\br\com\vestris\medicalrecord\domain\model\Paciente.java
package br.com.vestris.medicalrecord.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "pacientes", schema = "medical_record_schema")
public class Paciente extends EntidadeBase {

    @Column(nullable = false)
    private UUID veterinarioId;

    @Column(nullable = false)
    private UUID especieId;

    @Column(nullable = false)
    private String nome;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String dadosTutor;

    private String identificacaoAnimal; // Ex: Anilha, Marcação

    private String microchip;

    @Column(length = 50)
    private String pelagemCor;

    private String sexo;

    private LocalDate dataNascimento;

    private Integer pesoAtualGramas;

}

```

---

## src\main\java\br\com\vestris\medicalrecord\domain\repository

### RepositorioAtendimento.java

```java
// src\main\java\br\com\vestris\medicalrecord\domain\repository\RepositorioAtendimento.java
package br.com.vestris.medicalrecord.domain.repository;

import br.com.vestris.medicalrecord.domain.model.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioAtendimento extends JpaRepository<Atendimento, UUID>, JpaSpecificationExecutor<Atendimento> {
    List<Atendimento> findByPacienteIdOrderByCriadoEmDesc(UUID pacienteId);
}

```

### RepositorioExameAnexo.java

```java
// src\main\java\br\com\vestris\medicalrecord\domain\repository\RepositorioExameAnexo.java
package br.com.vestris.medicalrecord.domain.repository;

import br.com.vestris.medicalrecord.domain.model.ExameAnexo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioExameAnexo extends JpaRepository<ExameAnexo, UUID> {
    List<ExameAnexo> findByAtendimentoId(UUID atendimentoId);
}

```

### RepositorioPaciente.java

```java
// src\main\java\br\com\vestris\medicalrecord\domain\repository\RepositorioPaciente.java
package br.com.vestris.medicalrecord.domain.repository;

import br.com.vestris.medicalrecord.domain.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioPaciente extends JpaRepository<Paciente, UUID> {
    // Busca os pacientes de um determinado veterinário
    List<Paciente> findByVeterinarioId(UUID veterinarioId);

    // --- NOVO: BUSCA INSTITUCIONAL ---
    // Traz pacientes de qualquer veterinário que esteja na lista (Equipe)
    List<Paciente> findByVeterinarioIdIn(List<UUID> veterinarioIds);
}

```

---

## src\main\java\br\com\vestris\medicalrecord\interfaces\delegate

### ApiDelegateAtendimentos.java

```java
// src\main\java\br\com\vestris\medicalrecord\interfaces\delegate\ApiDelegateAtendimentos.java
package br.com.vestris.medicalrecord.interfaces.delegate;

import br.com.vestris.medicalrecord.application.ServiceAtendimento;
import br.com.vestris.medicalrecord.domain.model.Atendimento;
import br.com.vestris.medicalrecord.interfaces.api.AtendimentosApiDelegate;
import br.com.vestris.medicalrecord.interfaces.dto.*;
import br.com.vestris.species.application.ServiceEspecie;
import br.com.vestris.user.application.ServiceUsuario;
import br.com.vestris.user.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateAtendimentos implements AtendimentosApiDelegate {

    private final ServiceAtendimento servico;
    private final ServiceUsuario servicoUsuario;
    private final ServiceEspecie servicoEspecie;

    // --- AGENDAR ---
    @Override
    public ResponseEntity<ApiResponseAtendimento> agendarAtendimento(AgendamentoRequest request) {
        Atendimento a = new Atendimento();
        a.setVeterinarioId(request.getVeterinarioId());
        a.setTitulo(request.getTitulo());
        a.setProtocoloId(request.getProtocoloId());

        if (request.getDataHora() != null) {
            a.setDataHora(request.getDataHora().toLocalDateTime());
        } else {
            a.setDataHora(LocalDateTime.now());
        }

        a.setStatus(Atendimento.StatusAtendimento.AGENDADO);

        Atendimento salvo = servico.criar(a, request.getPacienteId());
        return ResponseEntity.ok(criarResponse(salvo));
    }

    // --- CRIAR (LEGADO/COMPLETO) ---
    @Override
    public ResponseEntity<ApiResponseAtendimento> criarAtendimento(AtendimentoRequest request) {
        Atendimento a = new Atendimento();
        a.setVeterinarioId(request.getVeterinarioId());
        a.setProtocoloId(request.getProtocoloId());
        a.setTitulo(request.getTitulo());

        if (request.getStatus() != null) {
            try {
                a.setStatus(Atendimento.StatusAtendimento.valueOf(request.getStatus().name()));
            } catch (Exception e) { }
        }

        a.setQueixaPrincipal(request.getQueixaPrincipal());
        a.setHistoricoClinico(request.getHistoricoClinico());
        a.setExameFisico(request.getExameFisico());
        a.setDiagnostico(request.getDiagnostico());
        a.setCondutaClinica(request.getCondutaClinica());
        a.setObservacoes(request.getObservacoes());

        if (request.getDataHora() != null) {
            a.setDataHora(request.getDataHora().toLocalDateTime());
        }

        Atendimento salvo = servico.criar(a, request.getPacienteId());
        return ResponseEntity.ok(criarResponse(salvo));
    }

    // --- LISTAR ---
    @Override
    public ResponseEntity<ApiResponseListaAtendimento> listarMeusAtendimentos(
            UUID veterinarioId,
            StatusAtendimentoEnum status,
            UUID pacienteId,
            java.time.LocalDate dataInicio,
            java.time.LocalDate dataFim
    ) {
        Atendimento.StatusAtendimento statusDomain = status != null
                ? Atendimento.StatusAtendimento.valueOf(status.name())
                : null;

        List<AtendimentoResponse> lista = servico.listar(veterinarioId, statusDomain, pacienteId, dataInicio, dataFim)
                .stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaAtendimento r = new ApiResponseListaAtendimento();
        r.setSucesso(true);
        r.setDados(lista);
        return ResponseEntity.ok(r);
    }

    @Override
    public ResponseEntity<ApiResponseListaAtendimento> listarAtendimentosPorPaciente(UUID pacienteId) {
        List<AtendimentoResponse> lista = servico.listarPorPaciente(pacienteId).stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaAtendimento r = new ApiResponseListaAtendimento();
        r.setSucesso(true);
        r.setDados(lista);
        return ResponseEntity.ok(r);
    }

    @Override
    public ResponseEntity<ApiResponseAtendimento> buscarAtendimentoPorId(UUID id) {
        return ResponseEntity.ok(criarResponse(servico.buscarPorId(id)));
    }

    // --- ATUALIZAR (RASCUNHO/PUT) ---
    @Override
    public ResponseEntity<ApiResponseAtendimento> atualizarAtendimento(UUID id, AtendimentoRequest request) {
        Atendimento a = new Atendimento();
        a.setTitulo(request.getTitulo());

        if (request.getStatus() != null) {
            try {
                a.setStatus(Atendimento.StatusAtendimento.valueOf(request.getStatus().name()));
            } catch (Exception e) {}
        }

        a.setQueixaPrincipal(request.getQueixaPrincipal());
        a.setHistoricoClinico(request.getHistoricoClinico());
        a.setExameFisico(request.getExameFisico());
        a.setDiagnostico(request.getDiagnostico());
        a.setCondutaClinica(request.getCondutaClinica());

        // CORREÇÃO: Garantindo que Observações sejam passadas
        a.setObservacoes(request.getObservacoes());

        a.setOrientacoesManejo(request.getOrientacoesManejo());

        a.setProtocoloId(request.getProtocoloId());

        Atendimento atualizado = servico.atualizar(id, a);
        return ResponseEntity.ok(criarResponse(atualizado));
    }

    // --- FINALIZAR ---
    @Override
    public ResponseEntity<ApiResponseAtendimento> finalizarAtendimento(UUID id, FinalizacaoAtendimentoRequest request) {
        Atendimento dadosClinicos = new Atendimento();
        dadosClinicos.setTitulo(request.getTitulo());
        dadosClinicos.setQueixaPrincipal(request.getQueixaPrincipal());
        dadosClinicos.setHistoricoClinico(request.getHistoricoClinico());
        dadosClinicos.setExameFisico(request.getExameFisico());
        dadosClinicos.setDiagnostico(request.getDiagnostico());
        dadosClinicos.setCondutaClinica(request.getCondutaClinica());

        // CORREÇÃO: Garantindo observações na finalização
        dadosClinicos.setObservacoes(request.getObservacoes());

        dadosClinicos.setProtocoloId(request.getProtocoloId());

        Atendimento finalizado = servico.finalizar(id, dadosClinicos);
        return ResponseEntity.ok(criarResponse(finalizado));
    }

    @Override
    public ResponseEntity<ApiResponseAtendimento> atualizarStatusAtendimento(UUID id, AtualizarStatusAtendimentoRequest request) {
        Atendimento.StatusAtendimento novoStatus = Atendimento.StatusAtendimento.valueOf(request.getStatus().name());
        Atendimento atualizado = servico.atualizarStatus(id, novoStatus);
        return ResponseEntity.ok(criarResponse(atualizado));
    }

    // --- CONVERSORES ---
    private ApiResponseAtendimento criarResponse(Atendimento a) {
        ApiResponseAtendimento r = new ApiResponseAtendimento();
        r.setSucesso(true);
        r.setDados(converter(a));
        return r;
    }

    private AtendimentoResponse converter(Atendimento a) {
        AtendimentoResponse dto = new AtendimentoResponse();
        dto.setId(a.getId());

        // Datas
        if(a.getCriadoEm() != null) dto.setDataHora(a.getCriadoEm().atOffset(ZoneOffset.UTC));
        if(a.getDataHora() != null) dto.setDataHora(a.getDataHora().atOffset(ZoneOffset.UTC));

        dto.setTitulo(a.getTitulo());

        if (a.getStatus() != null) {
            dto.setStatus(StatusAtendimentoEnum.valueOf(a.getStatus().name()));
        }

        // --- LÓGICA DE GOVERNANÇA E PROTEÇÃO (NOVO) ---
        // Na prática, teríamos o contexto de segurança aqui.
        // Como o SecurityContext ainda não foi totalmente integrado neste método (Drop 1),
        // preparamos a estrutura.
        // O FrontEnd já faz o bloqueio visual para 'ADMIN_GESTOR'.
        // No futuro (Drop 2), adicionaremos:
        // if (SecurityUtils.temPerfil("ADMIN_GESTOR")) { censurar() }

        // Por enquanto, enviamos os dados, pois o front do Admin Gestor não deve chamar esta rota de detalhe,
        // ou se chamar, o front esconde. Mas para segurança em profundidade, deixamos o hook pronto:

        boolean censurarDadosClinicos = false; // Mudar para lógica real futuramente

        if (censurarDadosClinicos) {
            dto.setQueixaPrincipal("[DADO PROTEGIDO]");
            dto.setHistoricoClinico("[DADO PROTEGIDO]");
            dto.setExameFisico("[DADO PROTEGIDO]");
            dto.setDiagnostico("[DADO PROTEGIDO]");
            dto.setCondutaClinica("[DADO PROTEGIDO]");
            dto.setObservacoes("[DADO PROTEGIDO]");
            dto.setOrientacoesManejo("[DADO PROTEGIDO]");
        } else {
            dto.setQueixaPrincipal(a.getQueixaPrincipal());
            dto.setHistoricoClinico(a.getHistoricoClinico());
            dto.setExameFisico(a.getExameFisico());
            dto.setDiagnostico(a.getDiagnostico());
            dto.setCondutaClinica(a.getCondutaClinica());
            dto.setOrientacoesManejo(a.getOrientacoesManejo());
            dto.setObservacoes(a.getObservacoes());
        }

        dto.setProtocoloId(a.getProtocoloId());
        dto.setVeterinarioId(a.getVeterinarioId());

        // Dados Relacionados (Enriquecimento)
        try {
            Usuario veterinario = servicoUsuario.buscarPorId(a.getVeterinarioId());
            dto.setVeterinarioNome(veterinario.getNome());
            dto.setVeterinarioCrmv(veterinario.getCrmv());
        } catch (Exception e) {
            dto.setVeterinarioNome("Veterinário não encontrado");
        }

        if (a.getPaciente() != null) {
            dto.setPacienteId(a.getPaciente().getId());
            dto.setPacienteNome(a.getPaciente().getNome());
            try {
                var especie = servicoEspecie.buscarPorId(a.getPaciente().getEspecieId());
                dto.setPacienteEspecie(especie.getNomePopular());
            } catch (Exception e) {
                dto.setPacienteEspecie("Espécie não identificada");
            }
        }
        return dto;
    }
}
```

### ApiDelegateExame.java

```java
// src\main\java\br\com\vestris\medicalrecord\interfaces\delegate\ApiDelegateExame.java
package br.com.vestris.medicalrecord.interfaces.delegate;

import br.com.vestris.medicalrecord.application.ServiceExames;
import br.com.vestris.medicalrecord.domain.model.ExameAnexo;
import br.com.vestris.medicalrecord.interfaces.api.ExamesApiDelegate;
import br.com.vestris.medicalrecord.interfaces.dto.ApiResponseExameAnexo;
import br.com.vestris.medicalrecord.interfaces.dto.ApiResponseListaExameAnexo;
import br.com.vestris.medicalrecord.interfaces.dto.ExameAnexoResponse;
import br.com.vestris.user.application.ServiceAuditoria;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateExame implements ExamesApiDelegate {

    private final ServiceExames servico;
    private final ServiceAuditoria serviceAuditoria;

    @Override
    public ResponseEntity<ApiResponseExameAnexo> uploadExame(UUID atendimentoId, MultipartFile arquivo, String observacoes) {
        // Validação básica de arquivo
        if (arquivo == null || arquivo.isEmpty()) {
            return ResponseEntity.badRequest().body(null); // Ou lance uma ExcecaoRegraNegocio
        }

        // Lógica de "Upload" (Passamos a responsabilidade pro Service lidar com bytes/S3)
        // Aqui simulamos que o Service vai salvar e devolver a entidade com a URL gerada

        String nomeOriginal = arquivo.getOriginalFilename();
        String tipoConteudo = arquivo.getContentType();

        // Dica: Em um cenário real, você passaria 'arquivo.getInputStream()' para o service.
        // Como combinamos que o Service receberia Strings no passo anterior,
        // vamos simular a URL aqui ou ajustar o Service para aceitar MultipartFile (recomendado).

        // Assumindo que o ServiceExames foi ajustado para receber os metadados e gerar a URL:
        // urlSimulada = "https://s3.amazon..." ou "/uploads/..."
        String urlSimulada = "https://storage.vestris.com/" + UUID.randomUUID() + "_" + nomeOriginal;

        ExameAnexo salvo = servico.anexar(
                atendimentoId,
                nomeOriginal,
                tipoConteudo,
                urlSimulada,
                observacoes
        );

        ApiResponseExameAnexo response = new ApiResponseExameAnexo();
        response.setSucesso(true);
        response.setMensagem("Arquivo anexado com sucesso.");
        response.setDados(converter(salvo));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<ApiResponseListaExameAnexo> listarExamesPorAtendimento(UUID atendimentoId) {
        List<ExameAnexoResponse> lista = servico.listarPorAtendimento(atendimentoId).stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaExameAnexo response = new ApiResponseListaExameAnexo();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deletarExame(UUID id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // --- CONVERSOR ---
    private ExameAnexoResponse converter(ExameAnexo entidade) {
        ExameAnexoResponse dto = new ExameAnexoResponse();
        dto.setId(entidade.getId());
        dto.setAtendimentoId(entidade.getAtendimentoId());
        dto.setNomeArquivo(entidade.getNomeArquivo());
        dto.setTipoArquivo(entidade.getTipoArquivo());
        dto.setUrlArquivo(entidade.getUrlArquivo());
        dto.setObservacoes(entidade.getObservacoes());

        if (entidade.getCriadoEm() != null) {
            dto.setCriadoEm(entidade.getCriadoEm().atOffset(ZoneOffset.UTC));
        }
        return dto;
    }
}

```

### ApiDelegatePacientes.java

```java
// src\main\java\br\com\vestris\medicalrecord\interfaces\delegate\ApiDelegatePacientes.java
package br.com.vestris.medicalrecord.interfaces.delegate;

import br.com.vestris.medicalrecord.application.ServicePaciente;
import br.com.vestris.medicalrecord.domain.model.Paciente;
import br.com.vestris.medicalrecord.interfaces.api.PacientesApiDelegate;
import br.com.vestris.medicalrecord.interfaces.dto.ApiResponseListaPaciente;
import br.com.vestris.medicalrecord.interfaces.dto.ApiResponsePaciente;
import br.com.vestris.medicalrecord.interfaces.dto.PacienteRequest;
import br.com.vestris.medicalrecord.interfaces.dto.PacienteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegatePacientes implements PacientesApiDelegate {

    private final ServicePaciente servico;

    @Override
    public ResponseEntity<ApiResponsePaciente> criarPaciente(PacienteRequest request) {
        Paciente p = new Paciente();
        p.setNome(request.getNome());
        p.setEspecieId(request.getEspecieId());
        p.setVeterinarioId(request.getVeterinarioId());
        p.setDadosTutor(request.getDadosTutor());
        p.setIdentificacaoAnimal(request.getIdentificacaoAnimal());
        p.setDataNascimento(request.getDataNascimento());
        p.setPesoAtualGramas(request.getPesoAtualGramas());

        // Novos campos
        p.setMicrochip(request.getMicrochip());
        p.setPelagemCor(request.getPelagemCor());

        // Enum (Agora converte bonito)
        if(request.getSexo() != null) {
            p.setSexo(request.getSexo().name());
        }

        Paciente salvo = servico.criar(p);

        ApiResponsePaciente response = new ApiResponsePaciente();
        response.setSucesso(true);
        response.setDados(converter(salvo));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseListaPaciente> listarPacientes(UUID veterinarioId) {
        // O serviço já valida se o vet existe. Se não existir, lança exceção tratada.
        List<PacienteResponse> lista = servico.listarPorVeterinario(veterinarioId).stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaPaciente response = new ApiResponseListaPaciente();
        response.setSucesso(true);
        // Se a lista estiver vazia, o JSON será { "sucesso": true, "dados": [] } -> PERFEITO
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponsePaciente> buscarPacientePorId(UUID id) {
        return ResponseEntity.ok(criarResponse(servico.buscarPorId(id)));
    }

    @Override
    public ResponseEntity<ApiResponsePaciente> atualizarPaciente(UUID id, PacienteRequest request) {
        Paciente p = new Paciente();
        p.setNome(request.getNome());
        p.setDadosTutor(request.getDadosTutor());
        p.setIdentificacaoAnimal(request.getIdentificacaoAnimal());
        p.setDataNascimento(request.getDataNascimento());
        p.setPesoAtualGramas(request.getPesoAtualGramas());
        p.setMicrochip(request.getMicrochip());
        p.setPelagemCor(request.getPelagemCor());
        if(request.getSexo() != null) p.setSexo(request.getSexo().name());

        return ResponseEntity.ok(criarResponse(servico.atualizar(id, p)));
    }

    @Override
    public ResponseEntity<Void> deletarPaciente(UUID id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // --- HELPERS ---
    private ApiResponsePaciente criarResponse(Paciente p) {
        ApiResponsePaciente r = new ApiResponsePaciente();
        r.setSucesso(true);
        r.setDados(converter(p));
        return r;
    }

    private PacienteResponse converter(Paciente p) {
        PacienteResponse dto = new PacienteResponse();
        dto.setId(p.getId());
        dto.setNome(p.getNome());
        dto.setEspecieId(p.getEspecieId());
        dto.setVeterinarioId(p.getVeterinarioId()); // Agora existe no DTO
        dto.setDadosTutor(p.getDadosTutor());
        dto.setIdentificacaoAnimal(p.getIdentificacaoAnimal());

        // Novos campos no DTO
        dto.setDataNascimento(p.getDataNascimento());
        dto.setMicrochip(p.getMicrochip());
        dto.setPelagemCor(p.getPelagemCor());
        dto.setPesoAtualGramas(p.getPesoAtualGramas());

        if (p.getCriadoEm() != null) dto.setCriadoEm(p.getCriadoEm().atOffset(ZoneOffset.UTC));

        // Conversão do Enum (Agora o DTO espera SexoEnum)
        try {
            if(p.getSexo() != null) {
                // Importe o SexoEnum do pacote interfaces.dto
                dto.setSexo(br.com.vestris.medicalrecord.interfaces.dto.SexoEnum.valueOf(p.getSexo()));
            }
        } catch (Exception e) {
            // Se o valor no banco for inválido, deixa nulo ou trata
        }

        return dto;
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
  title: Vestris - Prontuário Eletrônico
  version: 1.0.0
servers:
  - url: http://localhost:8080

paths:
  # --- PACIENTES ---
  /api/v1/pacientes:
    $ref: './paths/pacientes.yml#/pacientes_item'
  /api/v1/pacientes/{id}:
    $ref: './paths/pacientes.yml#/paciente_por_id'

  # --- ATENDIMENTOS ---

  # CORREÇÃO: Adicionada rota de Agendamento
  /api/v1/atendimentos/agendar:
    $ref: './paths/atendimentos.yml#/agendamento_item'

  /api/v1/atendimentos:
    $ref: './paths/atendimentos.yml#/atendimentos_item'

  /api/v1/atendimentos/{id}:
    $ref: './paths/atendimentos.yml#/atendimentos_por_id'

  /api/v1/atendimentos/{id}/status:
    $ref: './paths/atendimentos.yml#/atendimentos_status'

  /api/v1/pacientes/{pacienteId}/atendimentos:
    $ref: './paths/atendimentos.yml#/atendimentos_por_paciente'

  /api/v1/atendimentos/{id}/finalizar:
    $ref: './paths/atendimentos.yml#/atendimentos_finalizados'

    # --- NOVAS ROTAS DE EXAMES ---
  /api/v1/atendimentos/{atendimentoId}/exames:
    $ref: './paths/exames-anexo.yml#/exames_por_atendimento'

  /api/v1/exames/{id}:
    $ref: './paths/exames-anexo.yml#/exame_item'

components:
  schemas:
    PacienteRequest:
      $ref: "./components/schemas.yml#/PacienteRequest"
    PacienteResponse:
      $ref: "./components/schemas.yml#/PacienteResponse"

    # CORREÇÃO: Adicionados os DTOs novos para o gerador criar o Java
    AgendamentoRequest:
      $ref: "./components/schemas.yml#/AgendamentoRequest"
    FinalizacaoAtendimentoRequest:
      $ref: "./components/schemas.yml#/FinalizacaoAtendimentoRequest"

    AtendimentoRequest:
      $ref: "./components/schemas.yml#/AtendimentoRequest"
    AtendimentoResponse:
      $ref: "./components/schemas.yml#/AtendimentoResponse"
    ApiResponsePaciente:
      $ref: "./components/schemas.yml#/ApiResponsePaciente"
    ApiResponseListaPaciente:
      $ref: "./components/schemas.yml#/ApiResponseListaPaciente"
    ApiResponseAtendimento:
      $ref: "./components/schemas.yml#/ApiResponseAtendimento"
    ApiResponseListaAtendimento:
      $ref: "./components/schemas.yml#/ApiResponseListaAtendimento"
    StatusAtendimentoEnum:
      $ref: "./components/schemas.yml#/StatusAtendimentoEnum"
    SexoEnum:
      $ref: "./components/schemas.yml#/SexoEnum"
    ExameAnexoResponse:
      $ref: "./components/schemas.yml#/ExameAnexoResponse"
    ApiResponseExameAnexo:
      $ref: "./components/schemas.yml#/ApiResponseExameAnexo"
    ApiResponseListaExameAnexo:
      $ref: "./components/schemas.yml#/ApiResponseListaExameAnexo"
```

---

## src\main\resources\swagger\components

### schemas.yml

```yaml
# src\main\resources\swagger\components\schemas.yml
# --- ENUM COMPARTILHADO ---
SexoEnum:
  type: string
  enum: [MACHO, FEMEA, INDEFINIDO]

StatusAtendimentoEnum:
  type: string
  enum: [AGENDADO, EM_ANDAMENTO, REALIZADO, CANCELADO]

# --- PACIENTE ---
PacienteRequest:
  type: object
  required: [veterinarioId, especieId, nome, dadosTutor]
  properties:
    veterinarioId: { type: string, format: uuid }
    especieId: { type: string, format: uuid }
    nome: { type: string }
    dadosTutor: { type: string }
    identificacaoAnimal: { type: string }
    microchip: { type: string }
    pelagemCor: { type: string }
    sexo: { $ref: '#/SexoEnum' }
    dataNascimento: { type: string, format: date }
    pesoAtualGramas: { type: integer }

PacienteResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    nome: { type: string }
    especieId: { type: string, format: uuid }
    dadosTutor: { type: string }
    identificacaoAnimal: { type: string }
    pesoAtualGramas: { type: integer }
    criadoEm: { type: string, format: date-time }
    veterinarioId: { type: string, format: uuid }
    dataNascimento: { type: string, format: date }
    microchip: { type: string }
    pelagemCor: { type: string }
    sexo: { $ref: '#/SexoEnum' }

# --- DTO DE AGENDAMENTO (Criação Leve) ---
AgendamentoRequest:
  type: object
  required: [pacienteId, veterinarioId, titulo, dataHora]
  properties:
    pacienteId: { type: string, format: uuid }
    veterinarioId: { type: string, format: uuid }
    titulo: { type: string, example: "Consulta de Rotina" }
    dataHora: { type: string, format: date-time }
    protocoloId: { type: string, format: uuid }

# --- DTO DE PRONTUÁRIO (Finalização Rigorosa) ---
FinalizacaoAtendimentoRequest:
  type: object
  required: [queixaPrincipal, condutaClinica, diagnostico]
  properties:
    titulo: { type: string }
    protocoloId: { type: string, format: uuid }
    queixaPrincipal: { type: string }
    historicoClinico: { type: string }
    exameFisico: { type: string }
    diagnostico: { type: string }
    condutaClinica: { type: string }
    observacoes: { type: string }

# --- DTO GENÉRICO (Atualização/Legado) ---
AtendimentoRequest:
  type: object
  required: [pacienteId, veterinarioId, titulo]
  properties:
    pacienteId: { type: string, format: uuid }
    veterinarioId: { type: string, format: uuid }
    protocoloId: { type: string, format: uuid }
    dataHora: { type: string, format: date-time }
    titulo: { type: string }
    status: { $ref: '#/StatusAtendimentoEnum' }
    queixaPrincipal: { type: string }
    historicoClinico: { type: string }
    exameFisico: { type: string }
    diagnostico: { type: string }
    condutaClinica: { type: string }
    observacoes: { type: string }
    orientacoesManejo:
      type: string
      description: "JSON stringificado contendo os 8 pilares de manejo"

AtendimentoResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    pacienteId: { type: string, format: uuid }
    dataHora: { type: string, format: date-time }
    titulo: { type: string }
    status: { $ref: '#/StatusAtendimentoEnum' }
    queixaPrincipal: { type: string }
    historicoClinico: { type: string }
    exameFisico: { type: string }
    diagnostico: { type: string }
    condutaClinica: { type: string }
    observacoes: { type: string }
    protocoloId: { type: string, format: uuid }
    veterinarioId: { type: string, format: uuid }
    veterinarioNome: { type: string }
    veterinarioCrmv: { type: string }
    pacienteNome: { type: string }
    pacienteEspecie: { type: string }
    orientacoesManejo: { type: string }

ExameAnexoResponse:
  type: object
  properties:
    id:
      type: string
      format: uuid
    atendimentoId:
      type: string
      format: uuid
    nomeArquivo:
      type: string
      example: "hemograma_thor.pdf"
    tipoArquivo:
      type: string
      example: "application/pdf"
    urlArquivo:
      type: string
      description: "URL assinada ou caminho público do arquivo"
    observacoes:
      type: string
    criadoEm:
      type: string
      format: date-time

# Wrappers
ApiResponseExameAnexo:
  type: object
  properties:
    sucesso: { type: boolean }
    mensagem: { type: string }
    dados: { $ref: '#/ExameAnexoResponse' }

# --- WRAPPERS ---

ApiResponseListaExameAnexo:
  type: object
  properties:
    sucesso: { type: boolean }
    dados:
      type: array
      items: { $ref: '#/ExameAnexoResponse' }

ApiResponsePaciente:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { $ref: '#/PacienteResponse' }
ApiResponseListaPaciente:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { type: array, items: { $ref: '#/PacienteResponse' } }
ApiResponseAtendimento:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { $ref: '#/AtendimentoResponse' }
ApiResponseListaAtendimento:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { type: array, items: { $ref: '#/AtendimentoResponse' } }
```

---

## src\main\resources\swagger\paths

### atendimentos.yml

```yaml
# src\main\resources\swagger\paths\atendimentos.yml
# Rota: /api/v1/atendimentos/agendar (NOVA ROTA DE AGENDAMENTO)
agendamento_item:
  post:
    tags: [Atendimentos]
    summary: Agendar novo atendimento
    description: "Cria um registro com status AGENDADO. Dados clínicos não são permitidos aqui."
    operationId: agendarAtendimento
    requestBody:
      content:
        application/json:
          # CORREÇÃO: Aponta para AgendamentoRequest
          schema: { $ref: '../components/schemas.yml#/AgendamentoRequest' }
    responses:
      '200':
        description: Agendado com sucesso
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseAtendimento' }

# Rota: /api/v1/atendimentos (Rota Legada/Genérica - Mantida por compatibilidade ou criação direta completa)
atendimentos_item:
  post:
    tags: [Atendimentos]
    summary: Registrar atendimento completo (Legado)
    operationId: criarAtendimento
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/AtendimentoRequest' }
    responses:
      '200':
        description: Sucesso
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseAtendimento' }
  get:
    tags: [Atendimentos]
    summary: Listar meus atendimentos (Agenda)
    operationId: listarMeusAtendimentos
    parameters:
      - name: veterinarioId
        in: query
        required: true
        schema: { type: string, format: uuid }
      - name: status
        in: query
        required: false
        schema: { $ref: '../components/schemas.yml#/StatusAtendimentoEnum' }
      - name: pacienteId
        in: query
        required: false
        schema: { type: string, format: uuid }
      - name: dataInicio
        in: query
        required: false
        schema: { type: string, format: date }
      - name: dataFim
        in: query
        required: false
        schema: { type: string, format: date }
    responses:
      '200':
        description: Lista
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseListaAtendimento' }

# Rota: /api/v1/atendimentos/{id}/finalizar (FINALIZAÇÃO)
atendimentos_finalizados:
  put:
    tags: [Atendimentos]
    summary: Finalizar atendimento (Preencher Prontuário)
    description: "Recebe os dados clínicos e muda status para REALIZADO"
    operationId: finalizarAtendimento
    parameters:
      - name: id
        in: path
        required: true
        schema: { type: string, format: uuid }
    requestBody:
      content:
        application/json:
          # CORREÇÃO: Aponta para FinalizacaoAtendimentoRequest (Valida campos obrigatórios)
          schema: { $ref: '../components/schemas.yml#/FinalizacaoAtendimentoRequest' }
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseAtendimento' }

# Rota: /api/v1/pacientes/{pacienteId}/atendimentos
atendimentos_por_paciente:
  get:
    tags: [Atendimentos]
    summary: Histórico clínico do paciente
    operationId: listarAtendimentosPorPaciente
    parameters:
      - name: pacienteId
        in: path
        required: true
        schema: { type: string, format: uuid }
    responses:
      '200':
        description: Histórico
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseListaAtendimento' }

# ROTA: /api/v1/atendimentos/{id}
atendimentos_por_id:
  parameters:
    - name: id
      in: path
      required: true
      schema: { type: string, format: uuid }
  get:
    tags: [Atendimentos]
    summary: Ver detalhes
    operationId: buscarAtendimentoPorId
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseAtendimento' }
  put:
    tags: [Atendimentos]
    summary: Atualizar (Evoluir status, adicionar obs)
    operationId: atualizarAtendimento
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/AtendimentoRequest' }
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseAtendimento' }

# ROTA: /api/v1/atendimentos/{id}/status:
atendimentos_status:
  patch:
    tags: [Atendimentos]
    summary: Alterar status do atendimento
    operationId: atualizarStatusAtendimento
    parameters:
      - name: id
        in: path
        required: true
        schema: { type: string, format: uuid }
    requestBody:
      content:
        application/json:
          schema:
            type: object
            properties:
              status: { $ref: '../components/schemas.yml#/StatusAtendimentoEnum' }
    responses:
      '200':
        description: Status atualizado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseAtendimento' }
```

### exames-anexo.yml

```yaml
# src\main\resources\swagger\paths\exames-anexo.yml
# vestris-medical-record/src/main/resources/swagger/paths/exames-anexos.yml

# ROTA: /api/v1/atendimentos/{atendimentoId}/exames
exames_por_atendimento:
  get:
    tags: [Exames]
    summary: Listar exames de um atendimento
    operationId: listarExamesPorAtendimento
    parameters:
      - name: atendimentoId
        in: path
        required: true
        schema: { type: string, format: uuid }
    responses:
      '200':
        description: Lista recuperada
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseListaExameAnexo' }

  post:
    tags: [Exames]
    summary: Anexar novo exame/arquivo
    operationId: uploadExame
    parameters:
      - name: atendimentoId
        in: path
        required: true
        schema: { type: string, format: uuid }
    requestBody:
      content:
        multipart/form-data:
          schema:
            type: object
            required: [ arquivo ]
            properties:
              arquivo:
                type: string
                format: binary
                description: "Arquivo PDF, JPG ou PNG"
              observacoes:
                type: string
                description: "Notas sobre o exame (opcional)"
    responses:
      '201':
        description: Upload realizado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseExameAnexo' }

# ROTA: /api/v1/exames/{id}
exame_item:
  delete:
    tags: [Exames]
    summary: Remover anexo
    operationId: deletarExame
    parameters:
      - name: id
        in: path
        required: true
        schema: { type: string, format: uuid }
    responses:
      '204':
        description: Removido com sucesso
```

### pacientes.yml

```yaml
# src\main\resources\swagger\paths\pacientes.yml
# Rota: /api/v1/pacientes
pacientes_item:
  post:
    tags: [Pacientes]
    summary: Cadastrar novo paciente
    operationId: criarPaciente
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/PacienteRequest' }
    responses:
      '200':
        description: Sucesso
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponsePaciente' }
  get:
    tags: [Pacientes]
    summary: Listar meus pacientes
    description: "Filtra pelo veterinário logado (ou ID passado)"
    operationId: listarPacientes
    parameters:
      - name: veterinarioId
        in: query
        required: true
        schema: { type: string, format: uuid }
    responses:
      '200':
        description: Lista
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseListaPaciente' }

# Rota: /api/v1/pacientes/{id}
paciente_por_id:
  parameters:
    - name: id
      in: path
      required: true
      schema: { type: string, format: uuid }
  get:
    tags: [Pacientes]
    summary: Buscar paciente por ID
    operationId: buscarPacientePorId
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponsePaciente' }
  put:
    tags: [Pacientes]
    summary: Atualizar dados do paciente
    operationId: atualizarPaciente
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/PacienteRequest' }
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponsePaciente' }

  # --- BLOCO ADICIONADO ---
  delete:
    tags: [Pacientes]
    summary: Remover paciente
    description: "Remove o paciente apenas se não houver atendimentos vinculados"
    operationId: deletarPaciente
    responses:
      '204':
        description: Removido com sucesso (No Content)
```

