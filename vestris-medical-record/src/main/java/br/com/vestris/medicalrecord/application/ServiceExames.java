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
