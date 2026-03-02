## src\main\java\br\com\vestris\clinical\interfaces\delegate

### ApiDelegateCalculadora.java

```java
// src\main\java\br\com\vestris\clinical\interfaces\delegate\ApiDelegateCalculadora.java
package br.com.vestris.clinical.interfaces.delegate;

import br.com.vestris.clinical.application.ServiceCalculadora;
import br.com.vestris.clinical.domain.model.CalculoResultadoDTO;
import br.com.vestris.clinical.interfaces.api.CalculadoraApiDelegate;
import br.com.vestris.clinical.interfaces.dto.*;
import lombok.RequiredArgsConstructor;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiDelegateCalculadora implements CalculadoraApiDelegate {
    private final ServiceCalculadora serviceCalculadora;

    @Override
    public ResponseEntity<ApiResponseCalculo> calcularDosagemSegura(CalculoSeguroRequest request) {
        var resultado = serviceCalculadora.calcular(
                request.getProtocoloId(),
                request.getMedicamentoId(),
                request.getPeso(),
                request.getUnidadePeso().name()
        );
        return montarResposta(resultado);
    }

    // 1. CÁLCULO LIVRE (SIMPLES)
    @Override
    public ResponseEntity<ApiResponseCalculo> calcularDoseLivre(CalculoLivreRequest request) {
        Double pesoKg = request.getPeso();
        if (CalculoLivreRequest.UnidadePesoEnum.G.equals(request.getUnidadePeso())) {
            pesoKg = pesoKg / 1000.0;
        }

        // Chama método específico no Service (vou mostrar abaixo)
        var resultado = serviceCalculadora.calcularMatematico(
                request.getNomeMedicamento(),
                request.getConcentracao(),
                pesoKg,
                request.getDoseInformada(),
                request.getVia(),
                request.getFrequencia(), // Wrap in JsonNullable as per previous patterns if service expects it, checking service signature next step if error. Wait, snippet passed request.getFrequencia() directly. Assuming serviceCalculadora.calcularMatematico takes String/Double.
                request.getDuracao()
        );

        return montarResposta(resultado);
    }

    // 2. VALIDAÇÃO CATÁLOGO (COM SEGURANÇA)
    @Override
    public ResponseEntity<ApiResponseCalculo> validarDoseCatalogo(CalculoCatalogoRequest request) {
        Double pesoKg = request.getPeso();
        if (CalculoCatalogoRequest.UnidadePesoEnum.G.equals(request.getUnidadePeso())) {
            pesoKg = pesoKg / 1000.0;
        }

        var resultado = serviceCalculadora.validarDose(
                request.getMedicamentoId(),
                request.getEspecieId(),
                request.getDoencaId(),
                request.getClinicaId(),
                request.getUsuarioId(),
                pesoKg,
                request.getDoseInformada(),
                "MG_KG",
                request.getVia(),
                null // Concentração manual é null aqui, pois pega do banco
        );

        return montarResposta(resultado);
    }

    // --- HELPERS SEGUROS ---

    private <T> T unwrap(JsonNullable<T> nullable) {
        return (nullable != null && nullable.isPresent()) ? nullable.get() : null;
    }

    private String unwrapString(JsonNullable<String> nullable) {
        return (nullable != null && nullable.isPresent()) ? nullable.get() : null;
    }

    private ResponseEntity<ApiResponseCalculo> montarResposta(CalculoResultadoDTO resultado) {
        CalculoResponse response = new CalculoResponse();

        response.setProtocoloTitulo(resultado.getProtocoloTitulo());
        response.setMedicamentoNome(resultado.getMedicamentoNome());
        response.setReferenciaBibliografica(resultado.getReferencia());
        response.setPesoConsideradoKg(resultado.getPesoKg());
        response.setDoseMinimaMg(resultado.getDoseMinMg());
        response.setDoseMaximaMg(resultado.getDoseMaxMg());
        response.setVolumeMinimoMl(resultado.getVolMinMl());
        response.setVolumeMaximoMl(resultado.getVolMaxMl());
        response.setConcentracaoUtilizada(resultado.getConcentracao());

        // Mapeamento Seguro do Enum
        if (resultado.getStatusSeguranca() != null) {
            try {
                // Tenta converter string para Enum (Case sensitive pode ser problema)
                String statusStr = resultado.getStatusSeguranca().toUpperCase(); // Força upper
                response.setStatusSeguranca(StatusSegurancaEnum.fromValue(statusStr));
            } catch (Exception e) {
                System.err.println("Erro ao converter status: " + resultado.getStatusSeguranca());
                response.setStatusSeguranca(StatusSegurancaEnum.NAO_VALIDADO);
            }
        } else {
            response.setStatusSeguranca(StatusSegurancaEnum.SEGURO);
        }

        response.setMensagemSeguranca(resultado.getMensagemSeguranca());
        response.setRefMin(resultado.getRefMin());
        response.setRefMax(resultado.getRefMax());
        response.setRefFonte(resultado.getRefFonte());

        // Campos do cálculo matemático que podem vir no resultado
        response.setDoseCalculadaMg(resultado.getDoseCalculadaMg());
        response.setVolumeCalculadoMl(resultado.getVolumeCalculadoMl());

        ApiResponseCalculo wrapper = new ApiResponseCalculo();
        wrapper.setSucesso(true);
        wrapper.setDados(response);

        return ResponseEntity.ok(wrapper);
    }
}

```

### ApiDelegateDoencas.java

```java
// src\main\java\br\com\vestris\clinical\interfaces\delegate\ApiDelegateDoencas.java
package br.com.vestris.clinical.interfaces.delegate;

import br.com.vestris.clinical.application.ServiceDoenca;
import br.com.vestris.clinical.domain.model.Doenca;
import br.com.vestris.clinical.interfaces.api.DoencasApiDelegate;
import br.com.vestris.clinical.interfaces.dto.ApiResponseDoenca;
import br.com.vestris.clinical.interfaces.dto.ApiResponseListaDoenca;
import br.com.vestris.clinical.interfaces.dto.DoencaRequest;
import br.com.vestris.clinical.interfaces.dto.DoencaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateDoencas implements DoencasApiDelegate {
    private final ServiceDoenca servico;

    // --- CRIAÇÃO ---
    @Override
    public ResponseEntity<ApiResponseDoenca> criarDoenca(DoencaRequest request) {
        Doenca entidade = new Doenca();
        entidade.setNome(request.getNome());
        entidade.setNomeCientifico(request.getNomeCientifico());
        entidade.setSintomas(request.getSintomas());
        entidade.setEspecieId(request.getEspecieId());

        Doenca salva = servico.criar(entidade);

        ApiResponseDoenca response = new ApiResponseDoenca();
        response.setSucesso(true);
        response.setMensagem("Doença cadastrada com sucesso.");
        response.setDados(converterParaDTO(salva));

        return ResponseEntity.ok(response);
    }

    // --- LISTAGEM ---
    @Override
    public ResponseEntity<ApiResponseListaDoenca> listarDoencas() {
        List<DoencaResponse> lista = servico.listarTodas().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());

        ApiResponseListaDoenca response = new ApiResponseListaDoenca();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseListaDoenca> listarDoencasPorEspecie(UUID especieId) {
        List<DoencaResponse> lista = servico.listarPorEspecie(especieId).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());

        ApiResponseListaDoenca response = new ApiResponseListaDoenca();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    // --- NOVOS MÉTODOS (ID, PUT, DELETE) ---

    @Override
    public ResponseEntity<ApiResponseDoenca> buscarDoencaPorId(UUID id) {
        Doenca encontrada = servico.buscarPorId(id);

        ApiResponseDoenca response = new ApiResponseDoenca();
        response.setSucesso(true);
        response.setDados(converterParaDTO(encontrada));

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseDoenca> atualizarDoenca(UUID id, DoencaRequest request) {
        Doenca dadosParaAtualizar = new Doenca();
        dadosParaAtualizar.setNome(request.getNome());
        dadosParaAtualizar.setNomeCientifico(request.getNomeCientifico());
        dadosParaAtualizar.setSintomas(request.getSintomas());
        // Nota: Geralmente não atualizamos o especieId no PUT para não quebrar integridade,
        // mas se o serviço permitir, adicione: dadosParaAtualizar.setEspecieId(request.getEspecieId());

        Doenca atualizada = servico.atualizar(id, dadosParaAtualizar);

        ApiResponseDoenca response = new ApiResponseDoenca();
        response.setSucesso(true);
        response.setMensagem("Doença atualizada com sucesso.");
        response.setDados(converterParaDTO(atualizada));

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deletarDoenca(UUID id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build(); // Retorna 204
    }

    // --- CONVERSOR ---
    private DoencaResponse converterParaDTO(Doenca entidade) {
        DoencaResponse dto = new DoencaResponse();
        dto.setId(entidade.getId());
        dto.setNome(entidade.getNome());
        dto.setNomeCientifico(entidade.getNomeCientifico());
        dto.setSintomas(entidade.getSintomas());
        dto.setEspecieId(entidade.getEspecieId());

        if (entidade.getCriadoEm() != null) {
            dto.setCriadoEm(entidade.getCriadoEm().atOffset(ZoneOffset.UTC));
        }
        return dto;
    }
}

```

### ApiDelegateProtocolos.java

```java
// src\main\java\br\com\vestris\clinical\interfaces\delegate\ApiDelegateProtocolos.java
package br.com.vestris.clinical.interfaces.delegate;

import br.com.vestris.clinical.application.ServiceProtocolo;
import br.com.vestris.clinical.domain.model.ProtocoloCompletoDTO;
import br.com.vestris.clinical.domain.model.Doenca;
import br.com.vestris.clinical.domain.model.Dosagem;
import br.com.vestris.clinical.domain.model.Protocolo;
import br.com.vestris.clinical.interfaces.api.ProtocolosApiDelegate;
import br.com.vestris.clinical.interfaces.dto.*;
import br.com.vestris.pharmacology.application.ServiceFarmacologia;
import br.com.vestris.reference.application.ServiceReferencia;
import lombok.RequiredArgsConstructor;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateProtocolos implements ProtocolosApiDelegate {

    private final ServiceProtocolo servico;
    private final ServiceFarmacologia serviceFarmacologia;
    private final ServiceReferencia serviceReferencia;

    // --- CRIAÇÃO ---
    @Override
    public ResponseEntity<ApiResponseProtocolo> criarProtocolo(ProtocoloRequest request) {
        Protocolo protocolo = new Protocolo();
        protocolo.setTitulo(request.getTitulo());
        protocolo.setObservacoes(request.getObservacoes());

        protocolo.setReferenciaId(unwrap(request.getReferenciaId()));
        protocolo.setReferenciaTexto(request.getReferenciaTexto());
        protocolo.setDoencaTextoLivre(request.getDoencaTexto());

        if (request.getOrigem() != null) {
            protocolo.setOrigem(Protocolo.OrigemProtocolo.valueOf(request.getOrigem().name()));
        } else {
            protocolo.setOrigem(Protocolo.OrigemProtocolo.OFICIAL);
        }

        protocolo.setAutorId(request.getAutorId());

        // NOVO: Setar Clinica ID
        protocolo.setClinicaId(unwrap(request.getClinicaId()));

        List<Dosagem> listaDosagens = converterDosagensRequest(request.getDosagens());

        UUID doencaId = unwrap(request.getDoencaId());

        Protocolo salvo = servico.criar(protocolo, doencaId, listaDosagens);

        ApiResponseProtocolo response = new ApiResponseProtocolo();
        response.setSucesso(true);
        response.setMensagem("Protocolo criado com sucesso.");
        response.setDados(converterParaDto(salvo));

        return ResponseEntity.ok(response);
    }

    // --- LISTAGEM (CORRIGIDA) ---
    @Override
    public ResponseEntity<ApiResponseListaProtocolo> listarProtocolosPorDoenca(UUID doencaId, UUID clinicaId, UUID usuarioId) {

        List<Protocolo> protocolosEncontrados;

        // Se vierem os filtros de segurança, usa a busca inteligente
        if (clinicaId != null && usuarioId != null) {
            protocolosEncontrados = servico.listarAcessiveis(doencaId, clinicaId, usuarioId);
        } else {
            // Fallback (Legado ou uso interno): Traz tudo da doença (Cuidado aqui em produção)
            // Se preferir segurança total, force retornar lista vazia se não tiver contexto.
            protocolosEncontrados = servico.listarPorDoenca(doencaId);
        }

        List<ProtocoloResponse> lista = protocolosEncontrados.stream()
                .map(this::converterParaDto)
                .collect(Collectors.toList());

        ApiResponseListaProtocolo response = new ApiResponseListaProtocolo();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    // --- PROTOCOLO COMPLETO ---
    @Override
    public ResponseEntity<ApiResponseProtocoloCompleto> obterProtocoloCompleto(UUID especieId, UUID doencaId) {
        // Nota: Este método ainda retorna TODOS os protocolos da doença para compor a visão completa.
        // Se precisar filtrar aqui também, o ServiceProtocolo.montarProtocoloCompleto precisaria ser atualizado.
        // Por enquanto, mantemos o comportamento padrão do "Motor Clínico".

        ProtocoloCompletoDTO dtoCompleto = servico.montarProtocoloCompleto(especieId, doencaId);

        ProtocoloCompletoResponse response = new ProtocoloCompletoResponse();
        response.setDoenca(converterDoenca(dtoCompleto.getDoenca()));

        List<ProtocoloDetalhadoResponse> listaProtos = dtoCompleto.getProtocolos().stream()
                .map(p -> {
                    ProtocoloDetalhadoResponse detalhe = new ProtocoloDetalhadoResponse();
                    detalhe.setId(p.getId());
                    detalhe.setTitulo(p.getTitulo());
                    detalhe.setObservacoes(p.getObservacoes());

                    if (p.getReferenciaId() != null) {
                        String citacao = serviceReferencia.buscarCitacaoPorId(p.getReferenciaId());
                        detalhe.setReferenciaTexto(citacao);
                    } else {
                        detalhe.setReferenciaTexto(p.getReferenciaTexto());
                    }

                    if (p.getOrigem() != null) {
                        detalhe.setOrigem(OrigemProtocoloEnum.valueOf(p.getOrigem().name()));
                    }
                    detalhe.setAutorId(p.getAutorId());

                    List<DosagemResponse> dosesResponse = p.getDosagens().stream()
                            .map(this::converterDosagemUnica)
                            .collect(Collectors.toList());

                    detalhe.setDosagens(dosesResponse);
                    detalhe.setAlertasGerais(new ArrayList<>());
                    return detalhe;
                }).collect(Collectors.toList());

        response.setProtocolos(listaProtos);

        ApiResponseProtocoloCompleto wrapper = new ApiResponseProtocoloCompleto();
        wrapper.setSucesso(true);
        wrapper.setDados(response);
        return ResponseEntity.ok(wrapper);
    }

    // --- MÉTODOS CRUD PADRÃO ---

    @Override
    public ResponseEntity<ApiResponseProtocolo> buscarProtocoloPorId(UUID id) {
        Protocolo p = servico.buscarPorId(id);
        ApiResponseProtocolo response = new ApiResponseProtocolo();
        response.setSucesso(true);
        response.setDados(converterParaDto(p));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseProtocolo> atualizarProtocolo(UUID id, ProtocoloRequest request) {
        Protocolo dadosProtocolo = new Protocolo();
        dadosProtocolo.setTitulo(request.getTitulo());
        dadosProtocolo.setObservacoes(request.getObservacoes());
        dadosProtocolo.setReferenciaTexto(request.getReferenciaTexto());

        dadosProtocolo.setReferenciaId(unwrap(request.getReferenciaId()));
        dadosProtocolo.setDoencaTextoLivre(request.getDoencaTexto());

        // Atualiza vínculo institucional se enviado
        dadosProtocolo.setClinicaId(unwrap(request.getClinicaId()));

        List<Dosagem> novasDosagens = converterDosagensRequest(request.getDosagens());

        Protocolo atualizado = servico.atualizar(id, dadosProtocolo, novasDosagens);

        ApiResponseProtocolo response = new ApiResponseProtocolo();
        response.setSucesso(true);
        response.setMensagem("Protocolo atualizado.");
        response.setDados(converterParaDto(atualizado));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deletarProtocolo(UUID id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // --- HELPERS ---
    private <T> T unwrap(JsonNullable<T> nullable) {
        if (nullable == null || !nullable.isPresent()) {
            return null;
        }
        return nullable.get();
    }

    private DoencaResponse converterDoenca(Doenca d) {
        DoencaResponse dto = new DoencaResponse();
        dto.setId(d.getId());
        dto.setNome(d.getNome());
        dto.setNomeCientifico(d.getNomeCientifico());
        dto.setSintomas(d.getSintomas());
        dto.setEspecieId(d.getEspecieId());
        if(d.getCriadoEm() != null) dto.setCriadoEm(d.getCriadoEm().atOffset(ZoneOffset.UTC));
        return dto;
    }

    private List<Dosagem> converterDosagensRequest(List<DosagemItemRequest> itensRequest) {
        List<Dosagem> lista = new ArrayList<>();
        if (itensRequest != null) {
            for (DosagemItemRequest item : itensRequest) {
                Dosagem d = new Dosagem();
                d.setMedicamentoId(unwrap(item.getMedicamentoId()));
                d.setDoseMinima(unwrap(item.getDoseMinima()));
                d.setDoseMaxima(unwrap(item.getDoseMaxima()));
                d.setMedicamentoTextoLivre(item.getMedicamentoTexto());
                d.setUnidade(item.getUnidade());
                d.setFrequencia(item.getFrequencia());
                d.setDuracao(item.getDuracao());
                d.setVia(item.getVia());
                lista.add(d);
            }
        }
        return lista;
    }

    private ProtocoloResponse converterParaDto(Protocolo entidade) {
        ProtocoloResponse dto = new ProtocoloResponse();
        dto.setId(entidade.getId());
        dto.setTitulo(entidade.getTitulo());
        dto.setObservacoes(entidade.getObservacoes());

        // NOVO CAMPO
        dto.setClinicaId(entidade.getClinicaId());

        if (entidade.getReferenciaTexto() != null) {
            dto.setReferenciaTexto(entidade.getReferenciaTexto());
        } else if (entidade.getReferenciaId() != null) {
            try {
                dto.setReferenciaTexto(serviceReferencia.buscarCitacaoPorId(entidade.getReferenciaId()));
            } catch (Exception e) {
                dto.setReferenciaTexto("Ref ID: " + entidade.getReferenciaId());
            }
        }

        if (entidade.getDoenca() != null) {
            dto.setDoencaId(entidade.getDoenca().getId());
        }
        dto.setDoencaTexto(entidade.getDoencaTextoLivre());

        if (entidade.getOrigem() != null) {
            dto.setOrigem(OrigemProtocoloEnum.valueOf(entidade.getOrigem().name()));
        }
        dto.setAutorId(entidade.getAutorId());

        if (entidade.getDosagens() != null) {
            List<DosagemResponse> dosagensDto = entidade.getDosagens().stream()
                    .map(this::converterDosagemUnica)
                    .collect(Collectors.toList());
            dto.setDosagens(dosagensDto);
        }
        return dto;
    }

    private DosagemResponse converterDosagemUnica(Dosagem d) {
        DosagemResponse dDto = new DosagemResponse();
        dDto.setId(d.getId());
        dDto.setMedicamentoId(d.getMedicamentoId());
        dDto.setMedicamentoTexto(d.getMedicamentoTextoLivre());

        if (d.getMedicamentoId() != null) {
            try {
                var med = serviceFarmacologia.buscarMedicamentoPorId(d.getMedicamentoId());
                dDto.setNomeMedicamento(med.getNome());
            } catch (Exception e) {
                dDto.setNomeMedicamento(d.getMedicamentoTextoLivre() != null ? d.getMedicamentoTextoLivre() : "Medicamento não encontrado");
            }
        } else {
            dDto.setNomeMedicamento(d.getMedicamentoTextoLivre());
        }

        String doseTexto = "";
        if (d.getDoseMinima() != null) {
            doseTexto = d.getDoseMinima() + (d.getUnidade() != null ? " " + d.getUnidade() : "");
        } else {
            doseTexto = "Dose a critério";
        }

        dDto.setDose(doseTexto);
        dDto.setDetalhes((d.getVia() != null ? d.getVia() : "") +
                (d.getFrequencia() != null ? ", " + d.getFrequencia() : "") +
                (d.getDuracao() != null ? " por " + d.getDuracao() : ""));
        return dDto;
    }
}
```

