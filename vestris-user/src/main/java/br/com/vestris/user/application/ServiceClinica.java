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
