package br.com.vestris.clinical.interfaces.delegate;

import br.com.vestris.clinical.application.ServiceProtocolo;
import br.com.vestris.clinical.domain.model.Doenca;
import br.com.vestris.clinical.domain.model.Dosagem;
import br.com.vestris.clinical.domain.model.Protocolo;
import br.com.vestris.clinical.domain.model.ProtocoloCompletoDTO;
import br.com.vestris.clinical.interfaces.api.ProtocolosApiDelegate;
import br.com.vestris.clinical.interfaces.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ApiDelegateProtocolos implements ProtocolosApiDelegate {
    private final ServiceProtocolo servico;

    // --- CRIAÇÃO ---
    @Override
    public ResponseEntity<ApiResponseProtocolo> criarProtocolo(ProtocoloRequest request) {
        Protocolo protocolo = new Protocolo();
        protocolo.setTitulo(request.getTitulo());
        protocolo.setObservacoes(request.getObservacoes());
        protocolo.setReferenciaId(request.getReferenciaId());

        List<Dosagem> listaDosagens = converterDosagensRequest(request.getDosagens());

        Protocolo salvo = servico.criar(protocolo, request.getDoencaId(), listaDosagens);

        ApiResponseProtocolo response = new ApiResponseProtocolo();
        response.setSucesso(true);
        response.setMensagem("Protocolo criado com sucesso.");
        response.setDados(converterParaDto(salvo));

        return ResponseEntity.ok(response);
    }

    // --- LISTAGEM ---
    @Override
    public ResponseEntity<ApiResponseListaProtocolo> listarProtocolosPorDoenca(UUID doencaId) {
        List<ProtocoloResponse> lista = servico.listarPorDoenca(doencaId).stream()
                .map(this::converterParaDto)
                .collect(Collectors.toList());

        ApiResponseListaProtocolo response = new ApiResponseListaProtocolo();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    // --- PROTOCOLO COMPLETO (O Novo Método) ---
    @Override
    public ResponseEntity<ApiResponseProtocoloCompleto> obterProtocoloCompleto(UUID especieId, UUID doencaId) {
        // 1. Busca os dados agregados do serviço
        ProtocoloCompletoDTO dtoCompleto = servico.montarProtocoloCompleto(especieId, doencaId);

        // 2. Converte para o Response do Swagger
        ProtocoloCompletoResponse response = new ProtocoloCompletoResponse();

        // Aqui usamos o conversor de doença que faltava
        response.setDoenca(converterDoenca(dtoCompleto.getDoenca()));

        List<ProtocoloDetalhadoResponse> listaProtos = dtoCompleto.getProtocolos().stream()
                .map(p -> {
                    ProtocoloDetalhadoResponse detalhe = new ProtocoloDetalhadoResponse();
                    detalhe.setTitulo(p.getTitulo());
                    detalhe.setReferencia(p.getReferenciaId().toString()); // Pode buscar o nome da ref se quiser
                    detalhe.setObservacoes(p.getObservacoes());

                    // Reusa a lógica de converter dosagens que já temos no outro método
                    // Mas aqui precisamos adaptar para a lista interna do detalhe
                    List<DosagemResponse> dosesResponse = p.getDosagens().stream()
                            .map(this::converterDosagemUnica)
                            .collect(Collectors.toList());

                    detalhe.setDosagens(dosesResponse);

                    // Alertas Gerais (Placeholder - Futuramente buscar do modulo de Farmacologia)
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
        dadosProtocolo.setReferenciaId(request.getReferenciaId());

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

    // --- CONVERSORES AUXILIARES ---

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
                d.setMedicamentoId(item.getMedicamentoId());
                d.setDoseMinima(item.getDoseMinima());
                d.setDoseMaxima(item.getDoseMaxima());
                d.setUnidade(item.getUnidade());
                d.setFrequencia(item.getFrequencia());
                d.setDuracao(item.getDuracao());
                d.setVia(item.getVia());
                lista.add(d);
            }
        }
        return lista;
    }

    private DosagemResponse converterDosagemUnica(Dosagem d) {
        DosagemResponse dDto = new DosagemResponse();
        dDto.setId(d.getId());
        dDto.setMedicamentoId(d.getMedicamentoId());

        String doseTexto = (d.getDoseMaxima() != null && d.getDoseMaxima() > d.getDoseMinima())
                ? d.getDoseMinima() + " a " + d.getDoseMaxima() + " " + d.getUnidade()
                : d.getDoseMinima() + " " + d.getUnidade();

        dDto.setDose(doseTexto);
        dDto.setDetalhes((d.getVia() != null ? d.getVia() : "") +
                (d.getFrequencia() != null ? ", " + d.getFrequencia() : "") +
                (d.getDuracao() != null ? " por " + d.getDuracao() : ""));
        return dDto;
    }

    private ProtocoloResponse converterParaDto(Protocolo entidade) {
        ProtocoloResponse dto = new ProtocoloResponse();
        dto.setId(entidade.getId());
        dto.setTitulo(entidade.getTitulo());
        dto.setReferenciaId(entidade.getReferenciaId());
        dto.setObservacoes(entidade.getObservacoes());

        if (entidade.getDosagens() != null) {
            List<DosagemResponse> dosagensDto = entidade.getDosagens().stream()
                    .map(this::converterDosagemUnica)
                    .collect(Collectors.toList());
            dto.setDosagens(dosagensDto);
        }
        return dto;
    }
}
