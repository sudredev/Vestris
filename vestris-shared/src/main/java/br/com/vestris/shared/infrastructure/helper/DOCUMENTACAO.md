## src\main\java\br\com\vestris\shared\infrastructure\helper

### HelperAuditoria.java

```java
// src\main\java\br\com\vestris\shared\infrastructure\helper\HelperAuditoria.java
package br.com.vestris.shared.infrastructure.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Helper centralizado para operações de auditoria.
 * Responsável por:
 * - Extrair clinicaId de diferentes contextos
 * - Montar metadados estruturados
 * - Serializar dados para auditoria
 */
@Component
@RequiredArgsConstructor
public class HelperAuditoria {
    public Map<String, Object> montarMetadados(Object... args) {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            if (i + 1 < args.length) {
                map.put(String.valueOf(args[i]), args[i + 1]);
            }
        }
        return map;
    }

    public Map<String, Object> montarMetadadosPaciente(String nome, String especie, String tutor) {
        return montarMetadados(
                "nomePaciente", nome,
                "especie", especie,
                "tutor", tutor
        );
    }

    public Map<String, Object> montarMetadadosAtendimento(String pacienteNome, String status, UUID vetId, Object... extras) {
        Map<String, Object> map = montarMetadados(
                "paciente", pacienteNome,
                "status", status,
                "veterinarioId", vetId
        );
        // Adiciona extras
        if (extras != null) {
            map.putAll(montarMetadados(extras));
        }
        return map;
    }

    public Map<String, Object> montarMetadadosProtocolo(String titulo, String origem, UUID autorId, Object... extras) {
        Map<String, Object> map = montarMetadados(
                "titulo", titulo,
                "origem", origem,
                "autorId", autorId
        );
        if (extras != null) map.putAll(montarMetadados(extras));
        return map;
    }
}


```

