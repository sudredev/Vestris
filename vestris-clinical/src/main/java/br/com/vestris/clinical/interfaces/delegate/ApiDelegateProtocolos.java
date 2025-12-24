package br.com.vestris.clinical.interfaces.delegate;

import br.com.vestris.clinical.application.ServiceProtocolo;
import br.com.vestris.clinical.domain.model.Dosagem;
import br.com.vestris.clinical.domain.model.Protocolo;
import br.com.vestris.clinical.interfaces.api.ProtocolosApiDelegate;
import br.com.vestris.clinical.interfaces.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

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

    // --- NOVOS MÉTODOS (ID, PUT, DELETE) ---

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
        // 1. Dados básicos
        Protocolo dadosProtocolo = new Protocolo();
        dadosProtocolo.setTitulo(request.getTitulo());
        dadosProtocolo.setObservacoes(request.getObservacoes());
        dadosProtocolo.setReferenciaId(request.getReferenciaId());

        // 2. Nova lista de dosagens
        List<Dosagem> novasDosagens = converterDosagensRequest(request.getDosagens());

        // 3. Chama serviço de atualização
        Protocolo atualizado = servico.atualizar(id, dadosProtocolo, novasDosagens);

        ApiResponseProtocolo response = new ApiResponseProtocolo();
        response.setSucesso(true);
        response.setMensagem("Protocolo atualizado com sucesso.");
        response.setDados(converterParaDto(atualizado));

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deletarProtocolo(UUID id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // --- CONVERSORES AUXILIARES ---

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

    private ProtocoloResponse converterParaDto(Protocolo entidade) {
        ProtocoloResponse dto = new ProtocoloResponse();
        dto.setId(entidade.getId());
        dto.setTitulo(entidade.getTitulo());
        dto.setReferenciaId(entidade.getReferenciaId());
        dto.setObservacoes(entidade.getObservacoes());

        if (entidade.getDosagens() != null) {
            List<DosagemResponse> dosagensDto = entidade.getDosagens().stream()
                    .map(d -> {
                        DosagemResponse dDto = new DosagemResponse();
                        dDto.setId(d.getId());
                        dDto.setMedicamentoId(d.getMedicamentoId());

                        // Formatação amigável da dose
                        String doseTexto = (d.getDoseMaxima() != null && d.getDoseMaxima() > d.getDoseMinima())
                                ? d.getDoseMinima() + " a " + d.getDoseMaxima() + " " + d.getUnidade()
                                : d.getDoseMinima() + " " + d.getUnidade();

                        dDto.setDose(doseTexto);
                        dDto.setDetalhes((d.getVia() != null ? d.getVia() : "") +
                                (d.getFrequencia() != null ? ", " + d.getFrequencia() : "") +
                                (d.getDuracao() != null ? " por " + d.getDuracao() : ""));
                        return dDto;
                    })
                    .collect(Collectors.toList());

            dto.setDosagens(dosagensDto);
        }
        return dto;
    }
}
