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
