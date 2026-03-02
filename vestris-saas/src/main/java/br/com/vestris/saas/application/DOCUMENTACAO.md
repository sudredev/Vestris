## src\main\java\br\com\vestris\saas\application

### ServiceAssinatura.java

```java
// src\main\java\br\com\vestris\saas\application\ServiceAssinatura.java
package br.com.vestris.saas.application;

import br.com.vestris.saas.domain.model.Assinatura;
import br.com.vestris.saas.domain.model.Plano;
import br.com.vestris.saas.domain.repository.RepositorioAssinatura;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.user.application.ValidadorPlanoService;
import br.com.vestris.user.domain.model.Clinica;
import br.com.vestris.user.domain.repository.RepositorioClinica;
import br.com.vestris.user.domain.repository.RepositorioUsuario;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceAssinatura implements ValidadorPlanoService {
    private final RepositorioAssinatura repoAssinatura;
    private final RepositorioClinica repoClinica;
    private final RepositorioUsuario repoUsuario;
    private final ServicePlano servicePlano;

    public Assinatura buscarPorClinica(UUID clinicaId) {
        return repoAssinatura.findByClinicaId(clinicaId).orElse(null);
    }

    @Transactional
    public Assinatura assinarPlano(UUID clinicaId, UUID planoId, String ciclo) {
        // ... (código igual ao anterior) ...
        Clinica clinica = repoClinica.findById(clinicaId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Clínica", clinicaId.toString()));
        Plano novoPlano = servicePlano.buscarPorId(planoId);
        Assinatura assinatura = repoAssinatura.findByClinicaId(clinicaId).orElse(new Assinatura());
        assinatura.setClinica(clinica);
        assinatura.setPlano(novoPlano);
        assinatura.setStatus(Assinatura.StatusAssinatura.ATIVO);
        assinatura.setTipoFaturamento(novoPlano.isCustom() ? Assinatura.TipoFaturamento.MANUAL : Assinatura.TipoFaturamento.AUTO);
        assinatura.setDataInicio(LocalDateTime.now());
        if ("ANUAL".equalsIgnoreCase(ciclo)) {
            assinatura.setDataFim(LocalDateTime.now().plusYears(1));
        } else {
            assinatura.setDataFim(LocalDateTime.now().plusMonths(1));
        }
        return repoAssinatura.save(assinatura);
    }

    // --- IMPLEMENTAÇÃO DO CONTRATO ---
    @Override
    public void validarLimiteVeterinarios(UUID clinicaId) {
        // 1. Busca Assinatura
        Assinatura assinatura = buscarPorClinica(clinicaId);

        // 2. Valida Status (Ativo ou Trial)
        if (assinatura == null ||
                (assinatura.getStatus() != Assinatura.StatusAssinatura.ATIVO &&
                        assinatura.getStatus() != Assinatura.StatusAssinatura.TRIAL)) {

            throw new ExcecaoRegraNegocio("Sua clínica não possui uma assinatura ativa. Regularize o plano para gerenciar a equipe.");
        }

        // 3. Verifica Limites
        int limiteMaximo = assinatura.getPlano().getMaxVeterinarios();
        long totalAtual = repoUsuario.countByClinicaId(clinicaId);

        if (totalAtual >= limiteMaximo) {
            throw new ExcecaoRegraNegocio(
                    String.format("Limite do plano atingido (%d/%d). Faça upgrade para adicionar mais veterinários.",
                            totalAtual, limiteMaximo)
            );
        }
    }
}

```

### ServiceCadastroSaas.java

```java
// src\main\java\br\com\vestris\saas\application\ServiceCadastroSaas.java
package br.com.vestris.saas.application;

import br.com.vestris.saas.domain.model.Assinatura;
import br.com.vestris.saas.domain.repository.RepositorioAssinatura;
import br.com.vestris.saas.domain.repository.RepositorioPlano;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.user.domain.model.Clinica;
import br.com.vestris.user.domain.model.Perfil;
import br.com.vestris.user.domain.model.Usuario;
import br.com.vestris.user.domain.repository.RepositorioClinica;
import br.com.vestris.user.domain.repository.RepositorioUsuario;
import br.com.vestris.user.infrastructure.security.ServicoToken;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceCadastroSaas {
    private final RepositorioUsuario repoUsuario;
    private final RepositorioClinica repoClinica;
    private final RepositorioAssinatura repoAssinatura;
    private final RepositorioPlano repoPlano;
    private final PasswordEncoder passwordEncoder;
    private final ServicoToken servicoToken;

    @Transactional
    public String registrarCliente(String nome, String email, String senha, String crmv, String nomeClinica, UUID planoId) {
        // 1. Valida E-mail
        if (repoUsuario.existsByEmail(email)) {
            throw new ExcecaoRegraNegocio("Este e-mail já está em uso.");
        }

        // 2. Cria Clínica
        Clinica clinica = new Clinica();
        clinica.setNomeFantasia(nomeClinica);
        clinica = repoClinica.save(clinica);

        // 3. Cria Usuário Admin
        Usuario admin = new Usuario();
        admin.setNome(nome);
        admin.setEmail(email);
        admin.setSenha(passwordEncoder.encode(senha));
        admin.setCrmv(crmv);
        admin.setPerfil(Perfil.ADMIN_GESTOR); // Dono da clínica
        admin.setScope(Usuario.UserScope.TENANT);
        admin.setClinica(clinica);
        admin = repoUsuario.save(admin);

        // 4. Cria Assinatura (JÁ ATIVA PARA TESTE, depois mudamos para TRIAL ou PENDENTE)
        var plano = repoPlano.findById(planoId)
                .orElseThrow(() -> new ExcecaoRegraNegocio("Plano inválido"));

        Assinatura assinatura = new Assinatura();
        assinatura.setClinica(clinica);
        assinatura.setPlano(plano);
        assinatura.setStatus(Assinatura.StatusAssinatura.ATIVO); // <--- ATIVO DIRETO
        assinatura.setTipoFaturamento(Assinatura.TipoFaturamento.AUTO);
        assinatura.setDataInicio(LocalDateTime.now());
        assinatura.setDataFim(LocalDateTime.now().plusMonths(1)); // 1 mês grátis/teste

        repoAssinatura.save(assinatura);

        // 5. Retorna Token de Login Imediato
        return servicoToken.gerarToken(admin);
    }
}

```

### ServicePlano.java

```java
// src\main\java\br\com\vestris\saas\application\ServicePlano.java
package br.com.vestris.saas.application;

import br.com.vestris.saas.domain.model.Plano;
import br.com.vestris.saas.domain.repository.RepositorioPlano;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ServicePlano {
    private final RepositorioPlano repositorio;

    public List<Plano> listarTodos() {
        return repositorio.findAll();
    }

    public Plano buscarPorId(UUID id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Plano", id.toString()));
    }

    // Método para Admin criar planos (futuro)
    public Plano criar(Plano plano) {
        return repositorio.save(plano);
    }
}

```

