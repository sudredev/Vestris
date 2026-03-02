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
