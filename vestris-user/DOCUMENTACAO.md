# Documentação do projeto vestris-user

## Índice de Diretórios e Arquivos

- [.](#)
  - [pom.xml](#pomxml)
- [src\main\java\br\com\vestris\user\application](#srcmainjavabrcomvestrisuserapplication)
  - [ServiceAuditoria.java](#srcmainjavabrcomvestrisuserapplicationserviceauditoriajava)
  - [ServiceAuth.java](#srcmainjavabrcomvestrisuserapplicationserviceauthjava)
  - [ServiceClinica.java](#srcmainjavabrcomvestrisuserapplicationserviceclinicajava)
  - [ServiceUsuario.java](#srcmainjavabrcomvestrisuserapplicationserviceusuariojava)
  - [ValidadorPlanoService.java](#srcmainjavabrcomvestrisuserapplicationvalidadorplanoservicejava)
- [src\main\java\br\com\vestris\user\domain\model](#srcmainjavabrcomvestrisuserdomainmodel)
  - [AcaoAuditoria.java](#srcmainjavabrcomvestrisuserdomainmodelacaoauditoriajava)
  - [AcaoAuditoriaConverter.java](#srcmainjavabrcomvestrisuserdomainmodelacaoauditoriaconverterjava)
  - [Auditoria.java](#srcmainjavabrcomvestrisuserdomainmodelauditoriajava)
  - [Clinica.java](#srcmainjavabrcomvestrisuserdomainmodelclinicajava)
  - [EntidadeAuditoria.java](#srcmainjavabrcomvestrisuserdomainmodelentidadeauditoriajava)
  - [Perfil.java](#srcmainjavabrcomvestrisuserdomainmodelperfiljava)
  - [Usuario.java](#srcmainjavabrcomvestrisuserdomainmodelusuariojava)
- [src\main\java\br\com\vestris\user\domain\repository](#srcmainjavabrcomvestrisuserdomainrepository)
  - [RepositorioAuditoria.java](#srcmainjavabrcomvestrisuserdomainrepositoryrepositorioauditoriajava)
  - [RepositorioClinica.java](#srcmainjavabrcomvestrisuserdomainrepositoryrepositorioclinicajava)
  - [RepositorioUsuario.java](#srcmainjavabrcomvestrisuserdomainrepositoryrepositoriousuariojava)
- [src\main\java\br\com\vestris\user\infrastructure\config](#srcmainjavabrcomvestrisuserinfrastructureconfig)
  - [ConfiguracaoSeguranca.java](#srcmainjavabrcomvestrisuserinfrastructureconfigconfiguracaosegurancajava)
- [src\main\java\br\com\vestris\user\infrastructure\security](#srcmainjavabrcomvestrisuserinfrastructuresecurity)
  - [ServicoToken.java](#srcmainjavabrcomvestrisuserinfrastructuresecurityservicotokenjava)
- [src\main\java\br\com\vestris\user\interfaces\delegate](#srcmainjavabrcomvestrisuserinterfacesdelegate)
  - [ApiDelegateAdminGlobal.java](#srcmainjavabrcomvestrisuserinterfacesdelegateapidelegateadminglobaljava)
  - [ApiDelegateAuditoria.java](#srcmainjavabrcomvestrisuserinterfacesdelegateapidelegateauditoriajava)
  - [ApiDelegateAuth.java](#srcmainjavabrcomvestrisuserinterfacesdelegateapidelegateauthjava)
  - [ApiDelegateClinica.java](#srcmainjavabrcomvestrisuserinterfacesdelegateapidelegateclinicajava)
  - [ApiDelegateUsuarios.java](#srcmainjavabrcomvestrisuserinterfacesdelegateapidelegateusuariosjava)
- [src\main\resources\swagger](#srcmainresourcesswagger)
  - [openapi.yml](#srcmainresourcesswaggeropenapiyml)
- [src\main\resources\swagger\components](#srcmainresourcesswaggercomponents)
  - [schemas.yml](#srcmainresourcesswaggercomponentsschemasyml)
- [src\main\resources\swagger\paths](#srcmainresourcesswaggerpaths)
  - [admin.yml](#srcmainresourcesswaggerpathsadminyml)
  - [auditoria.yml](#srcmainresourcesswaggerpathsauditoriayml)
  - [clinica.yml](#srcmainresourcesswaggerpathsclinicayml)
  - [login.yml](#srcmainresourcesswaggerpathsloginyml)
  - [registro.yml](#srcmainresourcesswaggerpathsregistroyml)
  - [usuarios.yml](#srcmainresourcesswaggerpathsusuariosyml)

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

    <artifactId>vestris-user</artifactId>

    <dependencies>
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-shared</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <!-- SECURITY -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>

        <!-- JWT (JSON Web Token) -->
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>0.11.5</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>0.11.5</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <version>0.11.5</version>
            <scope>runtime</scope>
        </dependency>

        <!-- Swagger & Web & JPA (Padrão) -->
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
    </dependencies>

    <build>
        <plugins>
            <!-- Plugin OpenAPI (Mesma config de sempre) -->
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
                            <apiPackage>br.com.vestris.user.interfaces.api</apiPackage>
                            <modelPackage>br.com.vestris.user.interfaces.dto</modelPackage>

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

## src\main\java\br\com\vestris\user\application

### ServiceAuditoria.java

```java
// src\main\java\br\com\vestris\user\application\ServiceAuditoria.java
package br.com.vestris.user.application;

import br.com.vestris.user.domain.model.Auditoria;
import br.com.vestris.user.domain.model.AcaoAuditoria;
import br.com.vestris.user.domain.model.EntidadeAuditoria;
import br.com.vestris.user.domain.model.Usuario;
import br.com.vestris.user.domain.repository.RepositorioAuditoria;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class ServiceAuditoria {
    private final RepositorioAuditoria repositorio;
    private final ObjectMapper objectMapper; // Para converter metadados em JSON
    private final ServiceUsuario serviceUsuario;

    /**
     * Registra um evento de auditoria.
     * Usa transação REQUIRES_NEW para garantir que o log seja salvo mesmo se a operação principal falhar (opcional, mas bom para logs de erro),
     * ou MANDATORY se quiser que faça parte da mesma transação. Vamos usar o padrão (REQUIRED) para simplicidade.
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void registrar(
            UUID clinicaId,
            UUID usuarioId,
            AcaoAuditoria acao,
            EntidadeAuditoria entidade,
            UUID idAlvo,
            String descricaoCurta,
            Map<String, Object> metadados
    )
    {
        if (usuarioId == null) {
            System.err.println("[AUDIT ERROR] Tentativa de log sem usuário.");
            return;
        }

        // Tenta recuperar ClinicaID se vier nulo (fallback)
        if (clinicaId == null) {
            try {
                Usuario u = serviceUsuario.buscarPorId(usuarioId);
                if (u.getClinica() != null) {
                    clinicaId = u.getClinica().getId();
                }
            } catch (Exception e) {
                // Ignora, vai salvar sem clinicaId (pode quebrar se for NOT NULL no banco, então cuidado)
            }
        }

        // Se ainda for nulo e o banco exige, aborta ou usa um ID de sistema
        if (clinicaId == null) {
            System.err.println("[AUDIT ERROR] Auditoria ignorada: Usuário " + usuarioId + " não tem clínica vinculada.");
            return;
        }

        try {
            Auditoria log = new Auditoria();
            log.setId(UUID.randomUUID());
            log.setClinicaId(clinicaId);
            log.setUsuarioId(usuarioId);
            log.setAcao(acao);
            log.setEntidade(entidade);
            log.setIdAlvo(idAlvo);
            log.setDescricaoCurta(descricaoCurta);
            log.setDataHora(LocalDateTime.now());

            if (metadados != null && !metadados.isEmpty()) {
                log.setMetadados(objectMapper.writeValueAsString(metadados));
            }

            repositorio.saveAndFlush(log);

        } catch (Exception e) {
            // Auditoria não deve quebrar o fluxo de negócio, apenas logar o erro no console
            e.printStackTrace();
        }
    }

    // Sobrecarga simples
    public void registrar(UUID clinicaId, UUID usuarioId, AcaoAuditoria acao, EntidadeAuditoria entidade, UUID idAlvo, String descricao) {
        registrar(clinicaId, usuarioId, acao, entidade, idAlvo, descricao, null);
    }
}

```

### ServiceAuth.java

```java
// src\main\java\br\com\vestris\user\application\ServiceAuth.java
package br.com.vestris.user.application;

import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.user.domain.model.Perfil;
import br.com.vestris.user.domain.model.Usuario;
import br.com.vestris.user.domain.repository.RepositorioUsuario;
import br.com.vestris.user.infrastructure.security.ServicoToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceAuth {
    private final RepositorioUsuario repositorio;
    private final PasswordEncoder passwordEncoder;
    private final ServicoToken servicoToken;

    public Usuario registrar(String nome, String email, String senhaAberta, String crmv) {
        if (repositorio.existsByEmail(email)) {
            throw new ExcecaoRegraNegocio("E-mail já cadastrado.");
        }
        Usuario novo = new Usuario();
        novo.setNome(nome);
        novo.setEmail(email);
        novo.setCrmv(crmv);
        // Default para cadastro comum
        novo.setPerfil(crmv != null && !crmv.isBlank() ? Perfil.VETERINARIO : Perfil.ESTUDANTE);
        novo.setScope(Usuario.UserScope.TENANT); // Padrão é Tenant
        novo.setSenha(passwordEncoder.encode(senhaAberta));

        return repositorio.save(novo);
    }

    public String login(String email, String senha) {
        Usuario usuario = repositorio.findByEmail(email)
                .orElseThrow(() -> new ExcecaoRegraNegocio("Usuário ou senha inválidos."));

        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new ExcecaoRegraNegocio("Usuário ou senha inválidos.");
        }
        return servicoToken.gerarToken(usuario);
    }

    // --- GOD MODE (IMPERSONATE) ---
    public String impersonate(UUID adminId, UUID targetUserId) {
        System.out.println("--- INICIANDO IMPERSONATE ---");
        System.out.println("Admin ID recebido: " + adminId);

        Usuario admin = repositorio.findById(adminId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Admin", adminId.toString()));

        System.out.println("Admin encontrado: " + admin.getEmail());
        System.out.println("Scope do Admin no Objeto: " + admin.getScope());

        // VALIDAÇÃO COM LOG
        if (admin.getScope() == null) {
            System.out.println("ERRO: Scope é NULL");
            throw new ExcecaoRegraNegocio("Seu usuário não possui escopo definido.");
        }

        if (admin.getScope() != Usuario.UserScope.GLOBAL) {
            System.out.println("ERRO: Scope não é GLOBAL. É: " + admin.getScope());
            throw new ExcecaoRegraNegocio("Acesso negado: Requer privilégio GLOBAL.");
        }

        Usuario alvo = repositorio.findById(targetUserId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Usuário alvo", targetUserId.toString()));

        System.out.println("Alvo encontrado: " + alvo.getEmail());

        return servicoToken.gerarToken(alvo);
    }
}

```

### ServiceClinica.java

```java
// src\main\java\br\com\vestris\user\application\ServiceClinica.java
package br.com.vestris.user.application;

import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.user.domain.model.Clinica;
import br.com.vestris.user.domain.model.Perfil;
import br.com.vestris.user.domain.model.Usuario;
import br.com.vestris.user.domain.repository.RepositorioClinica;
import br.com.vestris.user.domain.repository.RepositorioUsuario;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceClinica {
    private final RepositorioClinica repoClinica;
    private final RepositorioUsuario repoUsuario;
    private final PasswordEncoder passwordEncoder;

    // Injeta a INTERFACE, não a classe concreta do outro módulo
    private final ValidadorPlanoService validadorPlano;

    public Clinica buscarPorUsuario(UUID usuarioId) {
        Usuario user = repoUsuario.findById(usuarioId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Usuário", usuarioId.toString()));

        return user.getClinica();
    }

    @Transactional
    public Clinica salvar(UUID usuarioId, Clinica dados) {
        Usuario user = repoUsuario.findById(usuarioId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Usuário", usuarioId.toString()));

        Clinica clinica = user.getClinica();

        if (clinica == null) {
            clinica = new Clinica();
        }

        clinica.setNomeFantasia(dados.getNomeFantasia());
        clinica.setRazaoSocial(dados.getRazaoSocial());
        clinica.setCnpj(dados.getCnpj());
        clinica.setEndereco(dados.getEndereco());
        clinica.setTelefone(dados.getTelefone());
        clinica.setEmailContato(dados.getEmailContato());
        clinica.setLogoBase64(dados.getLogoBase64());

        clinica = repoClinica.save(clinica);

        if (user.getClinica() == null) {
            user.setClinica(clinica);
            repoUsuario.save(user);
        }

        return clinica;
    }

    // --- GESTÃO DE EQUIPE ---

    public List<Usuario> listarMembros(UUID adminId) {
        Usuario admin = repoUsuario.findById(adminId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Admin", adminId.toString()));

        if (admin.getClinica() == null) return List.of();

        return repoUsuario.findByClinicaId(admin.getClinica().getId());
    }

    @Transactional
    public Usuario adicionarMembro(UUID adminId, String nome, String email, String senha, String crmv) {
        Usuario admin = repoUsuario.findById(adminId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Admin", adminId.toString()));

        if (admin.getClinica() == null) {
            throw new ExcecaoRegraNegocio("Você precisa salvar os dados da clínica antes de adicionar equipe.");
        }

        // --- GUARDIÃO VIA INTERFACE ---
        // O Spring vai procurar quem implementa essa interface (no caso, o módulo SaaS)
        validadorPlano.validarLimiteVeterinarios(admin.getClinica().getId());
        // ------------------------------

        if (repoUsuario.existsByEmail(email)) {
            throw new ExcecaoRegraNegocio("E-mail já cadastrado no sistema.");
        }

        Usuario novo = new Usuario();
        novo.setNome(nome);
        novo.setEmail(email);
        novo.setSenha(passwordEncoder.encode(senha));
        novo.setCrmv(crmv);
        novo.setPerfil(Perfil.VETERINARIO);
        novo.setScope(Usuario.UserScope.TENANT);
        novo.setClinica(admin.getClinica());

        return repoUsuario.save(novo);
    }

    // ... (método removerMembro mantido igual) ...
    @Transactional
    public void removerMembro(UUID adminId, UUID membroId) {
        if (adminId.equals(membroId)) {
            throw new ExcecaoRegraNegocio("Você não pode remover a si mesmo da equipe.");
        }
        Usuario admin = repoUsuario.findById(adminId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Admin", adminId.toString()));
        Usuario membro = repoUsuario.findById(membroId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Veterinário", membroId.toString()));

        if (admin.getClinica() == null) {
            throw new ExcecaoRegraNegocio("Admin não possui clínica vinculada.");
        }

        if (membro.getClinica() == null || !membro.getClinica().getId().equals(admin.getClinica().getId())) {
            throw new ExcecaoRegraNegocio("Este veterinário não pertence à sua equipe.");
        }

        membro.setClinica(null);
        repoUsuario.save(membro);
    }
}

```

### ServiceUsuario.java

```java
// src\main\java\br\com\vestris\user\application\ServiceUsuario.java
package br.com.vestris.user.application;

import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.user.domain.model.Perfil;
import br.com.vestris.user.domain.model.Usuario;
import br.com.vestris.user.domain.repository.RepositorioUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceUsuario {

    private final RepositorioUsuario repositorio;

    public List<Usuario> listar(String perfilStr, Boolean apenasComCrmv) {
        // 1. Filtro por CRMV
        if (Boolean.TRUE.equals(apenasComCrmv)) {
            return repositorio.findAllComCrmv();
        }

        // 2. Filtro por Perfil
        if (perfilStr != null) {
            try {
                Perfil perfil = Perfil.valueOf(perfilStr.toUpperCase());
                return repositorio.findByPerfil(perfil);
            } catch (IllegalArgumentException e) {
                // Se mandar perfil errado, retorna vazio ou erro (decisão sua)
                return List.of();
            }
        }

        // 3. Sem filtros: retorna tudo
        return repositorio.findAll();
    }

    public boolean existePorId(UUID id) {
        return repositorio.existsById(id);
    }

    public Usuario buscarPorId(UUID id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Usuário", id.toString()));
    }

    public Usuario atualizar(UUID id, String novoNome, String novoCrmv) {
        Usuario usuario = buscarPorId(id);

        if (novoNome != null) usuario.setNome(novoNome);
        if (novoCrmv != null) usuario.setCrmv(novoCrmv);

        // Regra de negócio: Se ganhou CRMV, vira Veterinário automaticamente?
        if (novoCrmv != null && !novoCrmv.isBlank()) {
            usuario.setPerfil(Perfil.VETERINARIO);
        }

        return repositorio.save(usuario);
    }

    public void deletar(UUID id) {
        if (!repositorio.existsById(id)) {
            throw new ExceptionRecursoNaoEncontrado("Usuário", id.toString());
        }
        repositorio.deleteById(id);
    }

    public List<Usuario> listarPorClinica(UUID clinicaId) {
        return repositorio.findByClinicaId(clinicaId);
    }
}

```

### ValidadorPlanoService.java

```java
// src\main\java\br\com\vestris\user\application\ValidadorPlanoService.java
package br.com.vestris.user.application;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface ValidadorPlanoService {
    void validarLimiteVeterinarios(UUID clinicaId);
}

```

---

## src\main\java\br\com\vestris\user\domain\model

### AcaoAuditoria.java

```java
// src\main\java\br\com\vestris\user\domain\model\AcaoAuditoria.java
package br.com.vestris.user.domain.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum para padronizar as ações de auditoria no sistema.
 * Garante type-safety e facilita buscas/filtros por ação específica.
 */
public enum AcaoAuditoria {
    // Paciente
    PACIENTE_CRIADO("Paciente criado"),
    PACIENTE_EDITADO("Paciente editado"),
    PACIENTE_CANCELADO("Paciente cancelado"),

    // Atendimento
    ATENDIMENTO_AGENDADO("Atendimento agendado"),
    ATENDIMENTO_INICIADO("Atendimento iniciado"),
    ATENDIMENTO_FINALIZADO("Atendimento finalizado"),
    ATENDIMENTO_CANCELADO("Atendimento cancelado"),
    ATENDIMENTO_EDITADO("Atendimento editado"),

    // Prontuário
    PRONTUARIO_EDITADO("Prontuário editado"),
    PRONTUARIO_SALVO("Prontuário salvo"),

    // Anexos e Exames
    ANEXO_ADICIONADO("Anexo/Exame adicionado"),
    ANEXO_REMOVIDO("Anexo/Exame removido"),

    // PDFs
    PDF_RECEITA_GERADO("PDF de receita médica gerado"),
    PDF_MANEJO_GERADO("PDF de manejo gerado"),
    PDF_PRONTUARIO_GERADO("PDF de prontuário gerado"),

    // Protocolos
    PROTOCOLO_CRIADO("Protocolo criado"),
    PROTOCOLO_EDITADO("Protocolo editado"),
    PROTOCOLO_REMOVIDO("Protocolo removido"),
    PROTOCOLO_CANCELADO("Protocolo cancelado"),

    // Genéricos
    RECURSO_CRIADO("Recurso criado"),
    RECURSO_EDITADO("Recurso editado"),
    RECURSO_DELETADO("Recurso deletado");

    private final String descricao;

    AcaoAuditoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    // Map of legacy names to enums for tolerant conversion from DB
    private static final Map<String, AcaoAuditoria> LEGACY_MAP = new HashMap<>();

    static {
        // Common legacy tokens that appeared in the project history
        LEGACY_MAP.put("CRIOU", RECURSO_CRIADO);
        LEGACY_MAP.put("FINALIZOU", ATENDIMENTO_FINALIZADO);
        LEGACY_MAP.put("STATUS_UPDATE", ATENDIMENTO_EDITADO);
        LEGACY_MAP.put("FINALIZADO", ATENDIMENTO_FINALIZADO);
        LEGACY_MAP.put("CRIADO", RECURSO_CRIADO);
        LEGACY_MAP.put("EDITOU", RECURSO_EDITADO);
        LEGACY_MAP.put("REMOVIDO", ANEXO_REMOVIDO);
        LEGACY_MAP.put("DELETADO", RECURSO_DELETADO);
        LEGACY_MAP.put("ATUALIZOU", RECURSO_EDITADO);
        // Add any other observed legacy tokens here
    }

    /**
     * Tolerant mapping from a database string to the enum.
     * Attempts in order: exact name match, description match, legacy aliases (case-insensitive).
     */
    public static AcaoAuditoria fromString(String s) {
        if (s == null) return null;
        String trimmed = s.trim();
        // 1) Try exact enum name
        try {
            return AcaoAuditoria.valueOf(trimmed);
        } catch (IllegalArgumentException ignored) {
        }

        // 2) Try matching by description
        for (AcaoAuditoria a : values()) {
            if (a.getDescricao().equalsIgnoreCase(trimmed)) return a;
        }

        // 3) Try legacy map (case-insensitive)
        AcaoAuditoria fromLegacy = LEGACY_MAP.get(trimmed.toUpperCase());
        if (fromLegacy != null) return fromLegacy;

        // 4) Fallback: try to match by transform (replace spaces/accents/underscores)
        String normalized = trimmed.toUpperCase().replaceAll("[^A-Z0-9]", "_");
        for (AcaoAuditoria a : values()) {
            if (a.name().equalsIgnoreCase(normalized)) return a;
        }

        // 5) Last resort: return null so caller can handle it
        return null;
    }
}

```

### AcaoAuditoriaConverter.java

```java
// src\main\java\br\com\vestris\user\domain\model\AcaoAuditoriaConverter.java
package br.com.vestris.user.domain.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class AcaoAuditoriaConverter implements AttributeConverter<AcaoAuditoria, String> {

    @Override
    public String convertToDatabaseColumn(AcaoAuditoria attribute) {
        return attribute == null ? null : attribute.name();
    }

    @Override
    public AcaoAuditoria convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        AcaoAuditoria v = AcaoAuditoria.fromString(dbData);
        if (v != null) return v;
        // If mapping failed, try direct enum name (case-insensitive)
        try {
            return AcaoAuditoria.valueOf(dbData);
        } catch (IllegalArgumentException e) {
            // fallback to null
            return null;
        }
    }
}


```

### Auditoria.java

```java
// src\main\java\br\com\vestris\user\domain\model\Auditoria.java
package br.com.vestris.user.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "auditoria", schema = "users_schema")
public class Auditoria {

    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID clinicaId;

    @Column(nullable = false)
    private UUID usuarioId;

    @Convert(converter = AcaoAuditoriaConverter.class)
    @Column(nullable = false)
    private AcaoAuditoria acao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EntidadeAuditoria entidade;

    @Column(nullable = false)
    private UUID idAlvo;

    @Column(columnDefinition = "TEXT")
    private String descricaoCurta;

    // --- CORREÇÃO AQUI ---
    // Usamos TEXT simples para evitar conflito de tipo com o driver do Postgres
    @Column(columnDefinition = "TEXT", name = "metadados")
    private String metadados;

    @CreationTimestamp
    private LocalDateTime dataHora;
}

```

### Clinica.java

```java
// src\main\java\br\com\vestris\user\domain\model\Clinica.java
package br.com.vestris.user.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "clinicas", schema = "users_schema")
public class Clinica extends EntidadeBase {

    @Column(nullable = false)
    private String nomeFantasia;

    private String razaoSocial;
    private String cnpj;

    @Column(columnDefinition = "TEXT")
    private String endereco;

    private String telefone;
    private String emailContato;

    @Column(columnDefinition = "TEXT")
    private String logoBase64;
}

```

### EntidadeAuditoria.java

```java
// src\main\java\br\com\vestris\user\domain\model\EntidadeAuditoria.java
package br.com.vestris.user.domain.model;

/**
 * Enum para padronizar as entidades que podem ser auditadas no sistema.
 * Facilita categorização e filtros de logs por tipo de entidade.
 */
public enum EntidadeAuditoria {
    PACIENTE("Paciente"),
    ATENDIMENTO("Atendimento"),
    PRONTUARIO("Prontuário"),
    ANEXO("Anexo/Exame"),
    EXAME("Exame"),
    PDF("PDF/Documento"),
    RECEITA("Receita Médica"),
    MANEJO("Manejo/Orientação"),
    PROTOCOLO("Protocolo"),
    PROTOCOLO_INSTITUCIONAL("Protocolo Institucional"),
    PROTOCOLO_PROPRIO("Protocolo Próprio"),
    USUARIO("Usuário"),
    CLINICA("Clínica"),
    VACINACAO("Vacinação"),
    APLICACAO_VACINA("Aplicação de Vacina");

    private final String descricao;

    EntidadeAuditoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}


```

### Perfil.java

```java
// src\main\java\br\com\vestris\user\domain\model\Perfil.java
package br.com.vestris.user.domain.model;

public enum Perfil {
    ADMIN_GLOBAL,   // Dono do SaaS
    ADMIN_GESTOR,   // Gestor da Clínica (Não vê dados clínicos)
    ADMIN_CLINICO,  // Dono Técnico (Vê tudo + alterna modo)
    VETERINARIO,    // Funcionário (Vê pacientes da clínica + seus protocolos)
    ESTUDANTE
}

```

### Usuario.java

```java
// src\main\java\br\com\vestris\user\domain\model\Usuario.java
package br.com.vestris.user.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "usuarios", schema = "users_schema")
public class Usuario extends EntidadeBase {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    private String crmv;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Perfil perfil;

    // --- NOVO VÍNCULO ---
    @ManyToOne
    @JoinColumn(name = "clinica_id")
    private Clinica clinica;

    @Enumerated(EnumType.STRING)
    private UserScope scope; // GLOBAL ou TENANT

    public enum UserScope {
        GLOBAL, // Você (Vestris)
        TENANT  // Clientes
    }

}

```

---

## src\main\java\br\com\vestris\user\domain\repository

### RepositorioAuditoria.java

```java
// src\main\java\br\com\vestris\user\domain\repository\RepositorioAuditoria.java
package br.com.vestris.user.domain.repository;

import br.com.vestris.user.domain.model.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioAuditoria extends JpaRepository<Auditoria, UUID> {
    // Método Mágico do Spring Data JPA (Funciona perfeitamente com Between)
    // O Delegate se encarregará de garantir que 'inicio' e 'fim' nunca sejam nulos
    List<Auditoria> findByClinicaIdAndDataHoraBetweenOrderByDataHoraDesc(
            UUID clinicaId,
            LocalDateTime inicio,
            LocalDateTime fim
    );

    // Método para quando não houver filtro de data (busca tudo da clínica)
    List<Auditoria> findByClinicaIdOrderByDataHoraDesc(UUID clinicaId);
}

```

### RepositorioClinica.java

```java
// src\main\java\br\com\vestris\user\domain\repository\RepositorioClinica.java
package br.com.vestris.user.domain.repository;

import br.com.vestris.user.domain.model.Clinica;
import br.com.vestris.user.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioClinica extends JpaRepository<Clinica, UUID> {
}

```

### RepositorioUsuario.java

```java
// src\main\java\br\com\vestris\user\domain\repository\RepositorioUsuario.java
package br.com.vestris.user.domain.repository;

import br.com.vestris.user.domain.model.Perfil;
import br.com.vestris.user.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Usuario> findByPerfil(Perfil perfil);

    // Busca quem tem CRMV preenchido
    @Query("SELECT u FROM Usuario u WHERE u.crmv IS NOT NULL AND u.crmv <> ''")
    List<Usuario> findAllComCrmv();

    // Busca por Clínica (para listar equipe)
    List<Usuario> findByClinicaId(UUID clinicaId);

    // --- NOVO: CONTAGEM PARA VALIDAÇÃO DE PLANO ---
    long countByClinicaId(UUID clinicaId);;

}

```

---

## src\main\java\br\com\vestris\user\infrastructure\config

### ConfiguracaoSeguranca.java

```java
// src\main\java\br\com\vestris\user\infrastructure\config\ConfiguracaoSeguranca.java
package br.com.vestris.user.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ConfiguracaoSeguranca {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

```

---

## src\main\java\br\com\vestris\user\infrastructure\security

### ServicoToken.java

```java
// src\main\java\br\com\vestris\user\infrastructure\security\ServicoToken.java
package br.com.vestris.user.infrastructure.security;

import br.com.vestris.user.domain.model.Usuario;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class ServicoToken {
    // Em produção, use application.properties. Para MVP, chave fixa.
    private static final Key CHAVE_SECRETA = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRACAO_MS = 86400000; // 24h

    public String gerarToken(Usuario usuario) {
        Date agora = new Date();
        Date dataExpiracao = new Date(agora.getTime() + EXPIRACAO_MS);

        JwtBuilder builder = Jwts.builder()
                .setSubject(usuario.getId().toString())
                .claim("email", usuario.getEmail())
                .claim("perfil", usuario.getPerfil().name())
                .claim("scope", usuario.getScope() != null ? usuario.getScope().name() : "TENANT");

        // --- CORREÇÃO: EMBUTIR A CLÍNICA NO TOKEN PARA O FRONTEND VER ---
        if (usuario.getClinica() != null) {
            builder.claim("clinicaId", usuario.getClinica().getId().toString());
        }

        return builder
                .setIssuedAt(agora)
                .setExpiration(dataExpiracao)
                .signWith(CHAVE_SECRETA)
                .compact();
    }
}

```

---

## src\main\java\br\com\vestris\user\interfaces\delegate

### ApiDelegateAdminGlobal.java

```java
// src\main\java\br\com\vestris\user\interfaces\delegate\ApiDelegateAdminGlobal.java
package br.com.vestris.user.interfaces.delegate;

import br.com.vestris.user.application.ServiceAuth;
import br.com.vestris.user.interfaces.api.AdminGlobalApiDelegate;
import br.com.vestris.user.interfaces.dto.ApiResponseToken;
import br.com.vestris.user.interfaces.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ApiDelegateAdminGlobal implements AdminGlobalApiDelegate {

    private final ServiceAuth servicoAuth;

    @Override
    public ResponseEntity<ApiResponseToken> impersonateUser(UUID adminId, UUID targetUserId) {
        System.out.println(">>> DELEGATE ADMIN ALCANÇADO! <<<"); // Log de prova de vida

        String token = servicoAuth.impersonate(adminId, targetUserId);

        TokenResponse dto = new TokenResponse();
        dto.setToken(token);
        dto.setTipo("Bearer");
        dto.setExpiraEm("Impersonated Session");

        ApiResponseToken response = new ApiResponseToken();
        response.setSucesso(true);
        response.setDados(dto);

        return ResponseEntity.ok(response);
    }
}

```

### ApiDelegateAuditoria.java

```java
// src\main\java\br\com\vestris\user\interfaces\delegate\ApiDelegateAuditoria.java
package br.com.vestris.user.interfaces.delegate;

import br.com.vestris.user.application.ServiceAuditoria;
import br.com.vestris.user.application.ServiceUsuario;
import br.com.vestris.user.domain.model.AcaoAuditoria;
import br.com.vestris.user.domain.model.Auditoria;
import br.com.vestris.user.domain.model.EntidadeAuditoria;
import br.com.vestris.user.domain.model.Usuario;
import br.com.vestris.user.domain.repository.RepositorioAuditoria;
import br.com.vestris.user.interfaces.api.AuditoriaApiDelegate;
import br.com.vestris.user.interfaces.dto.ApiResponseListaAuditoria;
import br.com.vestris.user.interfaces.dto.ApiResponseSucesso;
import br.com.vestris.user.interfaces.dto.AuditoriaResponse;
import br.com.vestris.user.interfaces.dto.EventoAuditoriaRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateAuditoria implements AuditoriaApiDelegate {
    private final RepositorioAuditoria repositorio;
    private final ServiceAuditoria serviceAuditoria;
    private final ServiceUsuario serviceUsuario;
    private final HttpServletRequest httpRequest;
    private final ObjectMapper objectMapper;

    @Override
    public ResponseEntity<ApiResponseListaAuditoria> listarLogsAuditoria(UUID clinicaId, LocalDate dataInicio, LocalDate dataFim) {

        List<Auditoria> logs;

        // ESTRATÉGIA: Se o usuário filtrar data, usamos o BETWEEN.
        // Se não filtrar, usamos o método simples.

        if (dataInicio != null || dataFim != null) {
            // Se início for nulo, pega desde o começo dos tempos
            LocalDateTime inicio = (dataInicio != null) ? dataInicio.atStartOfDay() : LocalDateTime.of(1970, 1, 1, 0, 0);

            // Se fim for nulo, pega até o final dos tempos (hoje + folga)
            LocalDateTime fim = (dataFim != null) ? dataFim.atTime(LocalTime.MAX) : LocalDateTime.now().plusDays(1);

            logs = repositorio.findByClinicaIdAndDataHoraBetweenOrderByDataHoraDesc(clinicaId, inicio, fim);
        } else {
            // Sem filtros: traz tudo
            logs = repositorio.findByClinicaIdOrderByDataHoraDesc(clinicaId);
        }

        List<AuditoriaResponse> listaDTO = logs.stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaAuditoria response = new ApiResponseListaAuditoria();
        response.setSucesso(true);
        response.setDados(listaDTO);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseSucesso> registrarEventoAuditoria(EventoAuditoriaRequest request) {
        try {
            UUID usuarioId = extrairUsuarioIdDoToken();

            if (usuarioId == null) {
                ApiResponseSucesso erro = new ApiResponseSucesso();
                erro.setSucesso(false);
                erro.setMensagem("Token inválido ou expirado.");
                return ResponseEntity.badRequest().body(erro);
            }

            Usuario usuario = serviceUsuario.buscarPorId(usuarioId);
            if (usuario.getClinica() == null) {
                ApiResponseSucesso r = new ApiResponseSucesso();
                r.setSucesso(true);
                r.setMensagem("Usuário sem clínica.");
                return ResponseEntity.ok(r);
            }

            AcaoAuditoria acao = AcaoAuditoria.fromString(request.getAcao().toString());
            EntidadeAuditoria entidade = EntidadeAuditoria.PDF;
            try {
                entidade = EntidadeAuditoria.valueOf(request.getEntidade());
            } catch (Exception e) {}

            serviceAuditoria.registrar(
                    usuario.getClinica().getId(),
                    usuario.getId(),
                    acao,
                    entidade,
                    request.getIdAlvo(),
                    request.getDescricao()
            );

            ApiResponseSucesso response = new ApiResponseSucesso();
            response.setSucesso(true);
            response.setMensagem("Evento registrado.");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            ApiResponseSucesso erro = new ApiResponseSucesso();
            erro.setSucesso(false);
            erro.setMensagem("Erro interno: " + e.getMessage());
            return ResponseEntity.internalServerError().body(erro);
        }
    }

    private UUID extrairUsuarioIdDoToken() {
        try {
            String header = httpRequest.getHeader("Authorization");
            if (header == null || !header.startsWith("Bearer ")) return null;
            String token = header.substring(7);
            String[] parts = token.split("\\.");
            if (parts.length < 2) return null;
            String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]));
            JsonNode node = objectMapper.readTree(payloadJson);
            if (node.has("sub")) return UUID.fromString(node.get("sub").asText());
        } catch (Exception e) {}
        return null;
    }

    private AuditoriaResponse converter(Auditoria log) {
        AuditoriaResponse dto = new AuditoriaResponse();
        dto.setId(log.getId());
        dto.setClinicaId(log.getClinicaId());
        dto.setUsuarioId(log.getUsuarioId());
        dto.setAcao(log.getAcao() != null ? log.getAcao().name() : "DESCONHECIDO");
        dto.setEntidade(log.getEntidade() != null ? log.getEntidade().name() : "DESCONHECIDO");
        dto.setIdAlvo(log.getIdAlvo());
        dto.setDetalhes(log.getDescricaoCurta());
        dto.setMetadados(log.getMetadados());
        if (log.getDataHora() != null) {
            // Enviamos o LocalDateTime com Offset UTC para o front ajustar
            dto.setCriadoEm(log.getDataHora().atOffset(ZoneOffset.UTC));
        }
        return dto;
    }
}

```

### ApiDelegateAuth.java

```java
// src\main\java\br\com\vestris\user\interfaces\delegate\ApiDelegateAuth.java
package br.com.vestris.user.interfaces.delegate;

import br.com.vestris.user.application.ServiceAuth;
import br.com.vestris.user.domain.model.Usuario;
import br.com.vestris.user.interfaces.api.AdminGlobalApiDelegate;
import br.com.vestris.user.interfaces.api.AutenticacaoApiDelegate;
import br.com.vestris.user.interfaces.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ApiDelegateAuth implements AutenticacaoApiDelegate{

    private final ServiceAuth servicoAuth;

    @Override
    public ResponseEntity<ApiResponseUsuario> registrarUsuario(RegistroRequest request) {
        Usuario salvo = servicoAuth.registrar(
                request.getNome(),
                request.getEmail(),
                request.getSenha(),
                request.getCrmv()
        );
        return ResponseEntity.ok(createResponse(salvo));
    }

    @Override
    public ResponseEntity<ApiResponseToken> login(LoginRequest request) {
        String tokenJwt = servicoAuth.login(request.getEmail(), request.getSenha());
        return ResponseEntity.ok(createTokenResponse(tokenJwt));
    }

    // Helpers
    private ApiResponseUsuario createResponse(Usuario u) {
        UsuarioResponse dto = new UsuarioResponse();
        dto.setId(u.getId());
        dto.setNome(u.getNome());
        dto.setEmail(u.getEmail());

        // CORREÇÃO: Usando o Enum externo gerado
        if (u.getPerfil() != null) {
            dto.setPerfil(UsuarioResponsePerfilEnum.valueOf(u.getPerfil().name()));
        }

        if (u.getScope() != null) {
            dto.setScope(UsuarioResponseScopeEnum.valueOf(u.getScope().name()));
        }

        if (u.getCrmv() != null) {
            dto.setCrmv(u.getCrmv());
        }

        ApiResponseUsuario response = new ApiResponseUsuario();
        response.setSucesso(true);
        response.setDados(dto);
        return response;
    }

    private ApiResponseToken createTokenResponse(String token) {
        TokenResponse dto = new TokenResponse();
        dto.setToken(token);
        dto.setTipo("Bearer");
        dto.setExpiraEm("24h");

        ApiResponseToken response = new ApiResponseToken();
        response.setSucesso(true);
        response.setDados(dto);
        return response;
    }
}

```

### ApiDelegateClinica.java

```java
// src\main\java\br\com\vestris\user\interfaces\delegate\ApiDelegateClinica.java
package br.com.vestris.user.interfaces.delegate;

import br.com.vestris.user.application.ServiceClinica;
import br.com.vestris.user.domain.model.Clinica;
import br.com.vestris.user.domain.model.Usuario;
import br.com.vestris.user.interfaces.api.ClinicaApiDelegate;
import br.com.vestris.user.interfaces.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateClinica implements ClinicaApiDelegate {
    private final ServiceClinica servico;

    @Override
    public ResponseEntity<ApiResponseClinica> obterMinhaClinica(UUID usuarioId) {
        Clinica c = servico.buscarPorUsuario(usuarioId);
        if (c == null) {
            ApiResponseClinica r = new ApiResponseClinica();
            r.setSucesso(true);
            return ResponseEntity.ok(r);
        }
        return ResponseEntity.ok(criarResponse(c));
    }

    @Override
    public ResponseEntity<ApiResponseClinica> salvarMinhaClinica(UUID usuarioId, ClinicaRequest request) {
        Clinica entidade = new Clinica();
        entidade.setNomeFantasia(request.getNomeFantasia());
        entidade.setRazaoSocial(request.getRazaoSocial());
        entidade.setCnpj(request.getCnpj());
        entidade.setEndereco(request.getEndereco());
        entidade.setTelefone(request.getTelefone());
        entidade.setEmailContato(request.getEmailContato());
        entidade.setLogoBase64(request.getLogoBase64());

        Clinica salva = servico.salvar(usuarioId, entidade);
        return ResponseEntity.ok(criarResponse(salva));
    }

    private ApiResponseClinica criarResponse(Clinica c) {
        ClinicaResponse dto = new ClinicaResponse();
        dto.setId(c.getId());
        dto.setNomeFantasia(c.getNomeFantasia());
        dto.setRazaoSocial(c.getRazaoSocial());
        dto.setCnpj(c.getCnpj());
        dto.setEndereco(c.getEndereco());
        dto.setTelefone(c.getTelefone());
        dto.setEmailContato(c.getEmailContato());
        dto.setLogoBase64(c.getLogoBase64());

        ApiResponseClinica r = new ApiResponseClinica();
        r.setSucesso(true);
        r.setDados(dto);
        return r;
    }

    @Override
    public ResponseEntity<ApiResponseListaUsuario> listarEquipe(UUID usuarioId) {
        List<UsuarioResponse> lista = servico.listarMembros(usuarioId).stream()
                .map(this::converterUsuario)
                .collect(Collectors.toList());

        ApiResponseListaUsuario r = new ApiResponseListaUsuario();
        r.setSucesso(true);
        r.setDados(lista);
        return ResponseEntity.ok(r);
    }

    @Override
    public ResponseEntity<ApiResponseUsuario> adicionarMembroEquipe(UUID usuarioId, NovoMembroRequest request) {
        Usuario novo = servico.adicionarMembro(
                usuarioId,
                request.getNome(),
                request.getEmail(),
                request.getSenha(),
                request.getCrmv()
        );

        ApiResponseUsuario r = new ApiResponseUsuario();
        r.setSucesso(true);
        r.setDados(converterUsuario(novo));
        return ResponseEntity.status(201).body(r);
    }

    // Helper
    private UsuarioResponse converterUsuario(Usuario u) {
        UsuarioResponse dto = new UsuarioResponse();
        dto.setId(u.getId());
        dto.setNome(u.getNome());
        dto.setEmail(u.getEmail());
        dto.setCrmv(u.getCrmv());

        // CORREÇÃO: Usando o Enum externo gerado
        if (u.getPerfil() != null) {
            dto.setPerfil(UsuarioResponsePerfilEnum.valueOf(u.getPerfil().name()));
        }
        // Scope não é obrigatório mostrar na listagem de equipe, mas se quiser:
        if (u.getScope() != null) {
            dto.setScope(UsuarioResponseScopeEnum.valueOf(u.getScope().name()));
        }

        return dto;
    }

    @Override
    public ResponseEntity<Void> removerMembroEquipe(UUID usuarioId, UUID membroId) {
        servico.removerMembro(usuarioId, membroId);
        return ResponseEntity.noContent().build();
    }
}

```

### ApiDelegateUsuarios.java

```java
// src\main\java\br\com\vestris\user\interfaces\delegate\ApiDelegateUsuarios.java
package br.com.vestris.user.interfaces.delegate;

import br.com.vestris.user.application.ServiceUsuario;
import br.com.vestris.user.domain.model.Usuario;
import br.com.vestris.user.interfaces.api.GestaoUsuariosApiDelegate;
import br.com.vestris.user.interfaces.dto.*;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateUsuarios implements GestaoUsuariosApiDelegate {
    private final ServiceUsuario servico;

    @Override
    public ResponseEntity<ApiResponseListaUsuario> listarUsuarios(String perfil, Boolean apenasComCrmv) {
        // O ServiceUsuario.listar espera uma String ou um Enum interno do domínio.
        // Vamos passar as strings e deixar o service resolver ou converter aqui se necessário.

        List<UsuarioResponse> lista = servico.listar(perfil, apenasComCrmv).stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaUsuario response = new ApiResponseListaUsuario();
        response.setSucesso(true);
        response.setDados(lista);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseUsuario> buscarUsuarioPorId(UUID id) {
        return ResponseEntity.ok(criarResponse(servico.buscarPorId(id)));
    }

    @Override
    public ResponseEntity<ApiResponseUsuario> atualizarUsuario(UUID id, AtualizacaoUsuarioRequest request) {
        Usuario atualizado = servico.atualizar(id, request.getNome(), request.getCrmv());
        return ResponseEntity.ok(criarResponse(atualizado));
    }

    @Override
    public ResponseEntity<Void> deletarUsuario(UUID id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // --- Helpers ---
    private ApiResponseUsuario criarResponse(Usuario u) {
        ApiResponseUsuario r = new ApiResponseUsuario();
        r.setSucesso(true);
        r.setDados(converter(u));
        return r;
    }

    private UsuarioResponse converter(Usuario u) {
        UsuarioResponse dto = new UsuarioResponse();
        dto.setId(u.getId());
        dto.setNome(u.getNome());
        dto.setEmail(u.getEmail());
        dto.setCrmv(u.getCrmv());

        // Conversão segura dos Enums do Domínio para os Enums do DTO
        if (u.getPerfil() != null) {
            try {
                // Tenta converter o nome do enum do domínio (ex: ADMIN_GLOBAL) para o DTO
                dto.setPerfil(UsuarioResponsePerfilEnum.valueOf(u.getPerfil().name()));
            } catch (Exception e) {
                // Fallback seguro caso o banco tenha algo estranho
                dto.setPerfil(UsuarioResponsePerfilEnum.VETERINARIO);
            }
        }

        if (u.getScope() != null) {
            try {
                dto.setScope(UsuarioResponseScopeEnum.valueOf(u.getScope().name()));
            } catch (Exception e) {
                dto.setScope(UsuarioResponseScopeEnum.TENANT);
            }
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
  title: Vestris - Módulo Usuários
  description: Autenticação e Gestão de Contas
  version: 1.0.0
servers:
  - url: http://localhost:8080
    description: Servidor Local

paths:
  /api/v1/auth/registro:
    $ref: "./paths/registro.yml"
  /api/v1/auth/login:
    $ref: "./paths/login.yml"
  /api/v1/usuarios:
    $ref: './paths/usuarios.yml#/usuarios_colecao'
  /api/v1/usuarios/{id}:
    $ref: './paths/usuarios.yml#/usuarios_item'
  /api/v1/minha-clinica:
    $ref: './paths/clinica.yml#/clinica_geral'
  /api/v1/minha-clinica/equipe:
    $ref: './paths/clinica.yml#/clinica_equipe'
  /api/v1/admin/impersonate:
    $ref: './paths/admin.yml#/impersonate'

  # NOVO
  /api/v1/auditoria:
    $ref: './paths/auditoria.yml#/auditoria_colecao'
  /api/v1/auditoria/evento:
    $ref: './paths/auditoria.yml#/auditoria_evento'

components:
  schemas:
    RegistroRequest:
      $ref: "./components/schemas.yml#/RegistroRequest"
    LoginRequest:
      $ref: "./components/schemas.yml#/LoginRequest"
    UsuarioResponse:
      $ref: "./components/schemas.yml#/UsuarioResponse"
    TokenResponse:
      $ref: "./components/schemas.yml#/TokenResponse"
    ApiResponseUsuario:
      $ref: "./components/schemas.yml#/ApiResponseUsuario"
    ApiResponseToken:
      $ref: "./components/schemas.yml#/ApiResponseToken"

    # NOVOS E ATUALIZADOS
    EventoAuditoriaRequest:
      $ref: "./components/schemas.yml#/EventoAuditoriaRequest"
    AuditoriaResponse:
      $ref: "./components/schemas.yml#/AuditoriaResponse"
    ApiResponseListaAuditoria:
      $ref: "./components/schemas.yml#/ApiResponseListaAuditoria"
    UsuarioResponsePerfilEnum:
      $ref: "./components/schemas.yml#/UsuarioResponsePerfilEnum"

```

---

## src\main\resources\swagger\components

### schemas.yml

```yaml
# src\main\resources\swagger\components\schemas.yml
# vestris-user/src/main/resources/swagger/components/schemas.yml

# --- ENUMS ---
UsuarioResponsePerfilEnum:
  type: string
  enum: [ADMIN_GLOBAL, ADMIN_GESTOR, ADMIN_CLINICO, VETERINARIO, ESTUDANTE]

UsuarioResponseScopeEnum:
  type: string
  enum: [ GLOBAL, TENANT ]

# --- AUTH & USER ---
RegistroRequest:
  type: object
  required: [nome, email, senha]
  properties:
    nome: { type: string }
    email: { type: string, format: email }
    senha: { type: string, format: password }
    crmv: { type: string }

LoginRequest:
  type: object
  required: [email, senha]
  properties:
    email: { type: string }
    senha: { type: string }

UsuarioResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    nome: { type: string }
    email: { type: string }
    perfil: { $ref: '#/UsuarioResponsePerfilEnum' }
    crmv: { type: string }
    scope: { $ref: '#/UsuarioResponseScopeEnum' }

TokenResponse:
  type: object
  properties:
    token: { type: string }
    tipo: { type: string, example: "Bearer" }
    expiraEm: { type: string }

AtualizacaoUsuarioRequest:
  type: object
  properties:
    nome: { type: string }
    crmv: { type: string }

# --- CLÍNICA ---
ClinicaRequest:
  type: object
  required: [ nomeFantasia ]
  properties:
    nomeFantasia: { type: string }
    razaoSocial: { type: string }
    cnpj: { type: string }
    endereco: { type: string }
    telefone: { type: string }
    emailContato: { type: string }
    logoBase64: { type: string }

ClinicaResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    nomeFantasia: { type: string }
    razaoSocial: { type: string }
    cnpj: { type: string }
    endereco: { type: string }
    telefone: { type: string }
    emailContato: { type: string }
    logoBase64: { type: string }

NovoMembroRequest:
  type: object
  required: [ nome, email, senha, crmv ]
  properties:
    nome: { type: string }
    email: { type: string, format: email }
    senha: { type: string }
    crmv: { type: string }

# Enums para o Frontend saber o que mandar
AcaoAuditoriaEnum:
  type: string
  enum:
    - PDF_RECEITA_GERADO
    - PDF_MANEJO_GERADO
    - PDF_PRONTUARIO_GERADO
    - VISUALIZOU_PRONTUARIO

EventoAuditoriaRequest:
  type: object
  required: [ acao, entidade, idAlvo, descricao ]
  properties:
    acao: { $ref: '#/AcaoAuditoriaEnum' }
    entidade: { type: string, example: "PDF" }
    idAlvo: { type: string, format: uuid }
    descricao: { type: string }
    metadados: { type: string, description: "JSON stringificado opcional" }

AuditoriaResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    clinicaId: { type: string, format: uuid }
    usuarioId: { type: string, format: uuid }
    acao: { type: string }
    entidade: { type: string }
    idAlvo: { type: string, format: uuid }
    detalhes: { type: string }
    metadados: { type: string } # Adicionado
    criadoEm: { type: string, format: date-time }

# --- WRAPPERS ---
ApiResponseUsuario:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { $ref: '#/UsuarioResponse' }

ApiResponseToken:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { $ref: '#/TokenResponse' }

ApiResponseListaUsuario:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { type: array, items: { $ref: '#/UsuarioResponse' } }

ApiResponseClinica:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { $ref: '#/ClinicaResponse' }

ApiResponseListaAuditoria: # NOVO
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { type: array, items: { $ref: '#/AuditoriaResponse' } }

ApiResponseSucesso:
  type: object
  properties:
    sucesso: { type: boolean }
    mensagem: { type: string }
```

---

## src\main\resources\swagger\paths

### admin.yml

```yaml
# src\main\resources\swagger\paths\admin.yml
# /api/v1/admin/impersonate
impersonate:
  post:
    tags: [AdminGlobal]
    summary: Gerar token de acesso para qualquer usuário
    operationId: impersonateUser
    parameters:
      - name: adminId
        in: query
        required: true
        schema: { type: string, format: uuid }
      - name: targetUserId
        in: query
        required: true
        schema: { type: string, format: uuid }
    responses:
      '200':
        description: Token gerado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseToken' }
```

### auditoria.yml

```yaml
# src\main\resources\swagger\paths\auditoria.yml
# ROTA: /api/v1/auditoria (Listagem)
auditoria_colecao:
  get:
    tags: [Auditoria]
    summary: Listar logs de auditoria da clínica
    operationId: listarLogsAuditoria
    parameters:
      - name: clinicaId
        in: query
        required: true
        schema: { type: string, format: uuid }
      - name: dataInicio
        in: query
        schema: { type: string, format: date }
      - name: dataFim
        in: query
        schema: { type: string, format: date }
    responses:
      '200':
        description: Lista de eventos
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseListaAuditoria' }

# NOVA ROTA: /api/v1/auditoria/evento (Registro manual do Front)
auditoria_evento:
  post:
    tags: [Auditoria]
    summary: Registrar evento de frontend
    operationId: registrarEventoAuditoria
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/EventoAuditoriaRequest' }
    responses:
      '200':
        description: Evento registrado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseSucesso' }
```

### clinica.yml

```yaml
# src\main\resources\swagger\paths\clinica.yml
clinica_geral:
  get:
    tags: [Clinica]
    summary: Obter dados da minha clínica
    operationId: obterMinhaClinica
    parameters:
      - name: usuarioId
        in: query
        required: true
        schema: { type: string, format: uuid }
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseClinica' }

  post:
    tags: [Clinica]
    summary: Criar ou Atualizar minha clínica
    operationId: salvarMinhaClinica
    parameters:
      - name: usuarioId
        in: query
        required: true
        schema: { type: string, format: uuid }
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/ClinicaRequest' }
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseClinica' }

clinica_equipe:
  get:
    tags: [ Clinica ]
    summary: Listar veterinários da minha clínica
    operationId: listarEquipe
    parameters:
      - name: usuarioId
        in: query
        required: true
        schema: { type: string, format: uuid }
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseListaUsuario' }

  post:
    tags: [ Clinica ]
    summary: Adicionar veterinário à equipe
    operationId: adicionarMembroEquipe
    parameters:
      - name: usuarioId
        in: query
        required: true
        schema: { type: string, format: uuid }
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/NovoMembroRequest' }
    responses:
      '201':
        description: Membro criado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseUsuario' }
  delete:
    tags: [ Clinica ]
    summary: Remover veterinário da equipe
    description: "Desvincula o veterinário da clínica. Ele perde acesso aos dados, mas o histórico é mantido."
    operationId: removerMembroEquipe
    parameters:
      - name: usuarioId
        in: query
        description: "ID do Admin que está removendo"
        required: true
        schema: { type: string, format: uuid }
      - name: membroId
        in: query
        description: "ID do Veterinário a ser removido"
        required: true
        schema: { type: string, format: uuid }
    responses:
      '204':
        description: Removido com sucesso

```

### login.yml

```yaml
# src\main\resources\swagger\paths\login.yml
post:
  tags:
    - Autenticacao
  summary: Fazer login e obter Token
  operationId: login
  requestBody:
    content:
      application/json:
        schema:
          $ref: '../components/schemas.yml#/LoginRequest'
  responses:
    '200':
      description: Login com sucesso
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/ApiResponseToken'
```

### registro.yml

```yaml
# src\main\resources\swagger\paths\registro.yml
post:
  tags:
    - Autenticacao
  summary: Criar nova conta
  operationId: registrarUsuario
  requestBody:
    content:
      application/json:
        schema:
          $ref: '../components/schemas.yml#/RegistroRequest'
  responses:
    '200':
      description: Conta criada
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/ApiResponseUsuario'
```

### usuarios.yml

```yaml
# src\main\resources\swagger\paths\usuarios.yml
# vestris-user/src/main/resources/swagger/paths/usuarios.yml
# Rota: /api/v1/usuarios
usuarios_colecao:
  get:
    tags: [GestaoUsuarios]
    summary: Listar usuários com filtros
    operationId: listarUsuarios
    parameters:
      - name: perfil
        in: query
        description: "Filtrar por perfil"
        required: false
        schema:
          type: string
          enum: [ADMIN_GLOBAL, ADMIN_GESTOR, ADMIN_CLINICO, VETERINARIO, ESTUDANTE]
      - name: apenasComCrmv
        in: query
        description: "Se true, traz apenas quem tem CRMV preenchido"
        required: false
        schema:
          type: boolean
    responses:
      '200':
        description: Lista recuperada
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseListaUsuario'

# Rota: /api/v1/usuarios/{id}
usuarios_item:
  parameters:
    - name: id
      in: path
      required: true
      schema: { type: string, format: uuid }
  get:
    tags: [GestaoUsuarios]
    summary: Buscar usuário por ID
    operationId: buscarUsuarioPorId
    responses:
      '200':
        description: Encontrado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseUsuario' }
  put:
    tags: [GestaoUsuarios]
    summary: Atualizar dados cadastrais
    description: "Atualiza nome e CRMV. Não atualiza senha/email por aqui."
    operationId: atualizarUsuario
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/AtualizacaoUsuarioRequest' }
    responses:
      '200':
        description: Atualizado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseUsuario' }
  delete:
    tags: [GestaoUsuarios]
    summary: Remover usuário
    operationId: deletarUsuario
    responses:
      '204': { description: Removido }
```

