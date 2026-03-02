## src\main\java\br\com\vestris\pharmacology\interfaces\delegate

### ApiDelegateContraindicacoes.java

```java
// src\main\java\br\com\vestris\pharmacology\interfaces\delegate\ApiDelegateContraindicacoes.java
package br.com.vestris.pharmacology.interfaces.delegate;

import br.com.vestris.pharmacology.application.ServiceContraindicacao;
import br.com.vestris.pharmacology.domain.model.Contraindicacao;
import br.com.vestris.pharmacology.interfaces.api.ContraindicacoesApiDelegate;
import br.com.vestris.pharmacology.interfaces.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateContraindicacoes implements ContraindicacoesApiDelegate {

    private final ServiceContraindicacao servico;

    @Override
    public ResponseEntity<ApiResponseContraindicacao> criarContraindicacao(ContraindicacaoRequest request) {
        Contraindicacao.Gravidade gravidade = Contraindicacao.Gravidade.valueOf(request.getGravidade().name());

        // Captura a referência ou define um padrão caso venha nulo
        String referencia = request.getReferenciaTexto() != null ? request.getReferenciaTexto() : "Referência interna";

        // Chama o serviço passando tanto Medicamento quanto Princípio.
        // O Serviço deve ter a assinatura: criar(UUID medId, UUID principioId, UUID espId, String ref, Gravidade g, String desc)
        // Nota: request.getPrincipioAtivoId() só funcionará se você adicionou este campo no YAML do Swagger.
        // Se ainda não adicionou, o Java não encontrará o método getter.

        UUID principioId = null;
        // Tenta obter o ID do princípio se o DTO gerado tiver o método (depende da sua atualização no YAML)
        try {
            // principioId = request.getPrincipioAtivoId(); // Descomente se o método existir
        } catch (Exception e) {
            // Ignora se não existir no DTO ainda
        }

        Contraindicacao salvo = servico.criar(
                request.getMedicamentoId().get(), // Pode ser null se vier pelo admin de principios
                principioId,                // Novo campo (prioritário)
                request.getEspecieId(),
                referencia,
                gravidade,
                request.getDescricao()
        );

        ApiResponseContraindicacao response = new ApiResponseContraindicacao();
        response.setSucesso(true);
        response.setDados(converter(salvo));

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseListaContraindicacao> listarContraindicacoesPorMedicamento(UUID medicamentoId) {
        List<ContraindicacaoResponse> lista = servico.listarPorMedicamento(medicamentoId).stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaContraindicacao response = new ApiResponseListaContraindicacao();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseContraindicacao> buscarContraindicacaoPorId(UUID id) {
        Contraindicacao encontrada = servico.buscarPorId(id);

        ApiResponseContraindicacao response = new ApiResponseContraindicacao();
        response.setSucesso(true);
        response.setDados(converter(encontrada));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseContraindicacao> atualizarContraindicacao(UUID id, ContraindicacaoRequest request) {
        Contraindicacao.Gravidade gravidadeDominio = Contraindicacao.Gravidade.valueOf(request.getGravidade().name());

        String referencia = request.getReferenciaTexto() != null ? request.getReferenciaTexto() : "Referência não informada";

        // O método atualizar no serviço precisa aceitar a referência String agora
        Contraindicacao atualizada = servico.atualizar(
                id,
                request.getEspecieId(),
                referencia,
                gravidadeDominio,
                request.getDescricao()
        );

        ApiResponseContraindicacao response = new ApiResponseContraindicacao();
        response.setSucesso(true);
        response.setMensagem("Contraindicação atualizada.");
        response.setDados(converter(atualizada));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deletarContraindicacao(UUID id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // --- CONVERSOR ---
    private ContraindicacaoResponse converter(Contraindicacao c) {
        ContraindicacaoResponse dto = new ContraindicacaoResponse();
        dto.setId(c.getId());

        // IMPORTANTE: O Frontend espera um ID no campo "medicamentoId" para links.
        // Como a contraindicação agora é baseada em princípio ativo, retornamos o ID do princípio
        // neste campo para manter a compatibilidade visual, ou null se preferir.
        if (c.getPrincipioAtivo() != null) {
            dto.setMedicamentoId(c.getPrincipioAtivo().getId());
        }

        dto.setEspecieId(c.getEspecieId());

        // Se você atualizou o response DTO para ter referenciaTexto, use:
        // dto.setReferenciaTexto(c.getReferenciaTexto());
        // Caso contrário, mantemos referenciaId como null pois mudamos para texto
        dto.setReferenciaId(null);

        dto.setDescricao(c.getDescricao());
        dto.setGravidade(GravidadeEnum.valueOf(c.getGravidade().name()));
        return dto;
    }
}

```

### ApiDelegateMedicamentos.java

```java
// src\main\java\br\com\vestris\pharmacology\interfaces\delegate\ApiDelegateMedicamentos.java
package br.com.vestris.pharmacology.interfaces.delegate;

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

```

### ApiDelegatePrincipioAtivo.java

```java
// src\main\java\br\com\vestris\pharmacology\interfaces\delegate\ApiDelegatePrincipioAtivo.java
package br.com.vestris.pharmacology.interfaces.delegate;

import br.com.vestris.pharmacology.application.ServiceFarmacologia;
import br.com.vestris.pharmacology.domain.model.PrincipioAtivo;
import br.com.vestris.pharmacology.interfaces.api.PrincipiosAtivosApiDelegate;
import br.com.vestris.pharmacology.interfaces.dto.ApiResponseListaPrincipioAtivo;
import br.com.vestris.pharmacology.interfaces.dto.ApiResponsePrincipioAtivo;
import br.com.vestris.pharmacology.interfaces.dto.PrincipioAtivoRequest;
import br.com.vestris.pharmacology.interfaces.dto.PrincipioAtivoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegatePrincipioAtivo implements PrincipiosAtivosApiDelegate {
    private final ServiceFarmacologia servico;

    @Override
    public ResponseEntity<ApiResponsePrincipioAtivo> criarPrincipioAtivo(PrincipioAtivoRequest request) {
        PrincipioAtivo entidade = new PrincipioAtivo();
        entidade.setNome(request.getNome());
        entidade.setDescricao(request.getDescricao());
        entidade.setGrupoFarmacologico(request.getGrupoFarmacologico());

        PrincipioAtivo salvo = servico.criarPrincipio(entidade);

        ApiResponsePrincipioAtivo response = new ApiResponsePrincipioAtivo();
        response.setSucesso(true);
        response.setDados(converter(salvo));

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseListaPrincipioAtivo> listarPrincipiosAtivos() {
        List<PrincipioAtivoResponse> lista = servico.listarPrincipios().stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaPrincipioAtivo response = new ApiResponseListaPrincipioAtivo();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponsePrincipioAtivo> buscarPrincipioAtivoPorId(UUID id) {
        PrincipioAtivo pa = servico.buscarPrincipioPorId(id);

        ApiResponsePrincipioAtivo response = new ApiResponsePrincipioAtivo();
        response.setSucesso(true);
        response.setDados(converter(pa));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponsePrincipioAtivo> atualizarPrincipioAtivo(UUID id, PrincipioAtivoRequest request) {
        PrincipioAtivo dados = new PrincipioAtivo();
        dados.setNome(request.getNome());
        dados.setDescricao(request.getDescricao());
        dados.setGrupoFarmacologico(request.getGrupoFarmacologico());

        PrincipioAtivo atualizado = servico.atualizarPrincipio(id, dados);

        ApiResponsePrincipioAtivo response = new ApiResponsePrincipioAtivo();
        response.setSucesso(true);
        response.setMensagem("Atualizado com sucesso.");
        response.setDados(converter(atualizado));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deletarPrincipioAtivo(UUID id) {
        servico.deletarPrincipio(id);
        return ResponseEntity.noContent().build();
    }

    // ... converter ...
    private PrincipioAtivoResponse converter(PrincipioAtivo pa) {
        PrincipioAtivoResponse dto = new PrincipioAtivoResponse();
        dto.setId(pa.getId());
        dto.setNome(pa.getNome());
        dto.setDescricao(pa.getDescricao());
        dto.setGrupoFarmacologico(pa.getGrupoFarmacologico());
        return dto;
    }


}

```

### ApiDelegateSegurancaClinica.java

```java
// src\main\java\br\com\vestris\pharmacology\interfaces\delegate\ApiDelegateSegurancaClinica.java
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

```

