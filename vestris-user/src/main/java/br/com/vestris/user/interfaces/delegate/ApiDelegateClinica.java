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
