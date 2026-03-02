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
