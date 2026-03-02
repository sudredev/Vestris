package br.com.vestris.pharmacology.interfaces.delegate;

import br.com.vestris.pharmacology.application.ServiceDoseReferencia;
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
    private final ServiceDoseReferencia serviceDose;

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

    @Override
    public ResponseEntity<List<String>> listarViasDoMedicamento(UUID id, UUID especieId) {
        List<String> vias = serviceDose.listarViasPorMedicamentoEEspecie(id, especieId);
        return ResponseEntity.ok(vias);
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
