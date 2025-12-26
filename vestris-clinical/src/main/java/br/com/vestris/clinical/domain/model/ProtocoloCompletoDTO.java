package br.com.vestris.clinical.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProtocoloCompletoDTO {
    private Doenca doenca;
    private List<Protocolo> protocolos;
}
