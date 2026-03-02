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