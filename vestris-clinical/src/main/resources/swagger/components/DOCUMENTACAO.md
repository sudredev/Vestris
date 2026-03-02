## src\main\resources\swagger\components

### schemas.yml

```yaml
# src\main\resources\swagger\components\schemas.yml
# src/main/resources/swagger/components/schemas.yml

# --- ENUMS ---
OrigemProtocoloEnum:
  type: string
  enum: [OFICIAL, PROPRIO, INSTITUCIONAL]

StatusSegurancaEnum:
  type: string
  enum: [SEGURO, SUBDOSE, SUPERDOSE, SEM_REFERENCIA, NAO_VALIDADO]

# --- DOENÇAS ---
DoencaRequest:
  type: object
  required: [nome, especieId]
  properties:
    nome: { type: string }
    nomeCientifico: { type: string }
    sintomas: { type: string }
    especieId: { type: string, format: uuid }

DoencaResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    nome: { type: string }
    nomeCientifico: { type: string }
    sintomas: { type: string }
    especieId: { type: string, format: uuid }
    criadoEm: { type: string, format: date-time }

# --- PROTOCOLOS ---
DosagemItemRequest:
  type: object
  properties:
    medicamentoId: { type: string, format: uuid, nullable: true }
    medicamentoTexto: { type: string }
    doseMinima: { type: number, format: double, nullable: true }
    doseMaxima: { type: number, format: double, nullable: true }
    unidade: { type: string }
    frequencia: { type: string }
    duracao: { type: string }
    via: { type: string }

ProtocoloRequest:
  type: object
  required: [ titulo, dosagens ]
  properties:
    titulo: { type: string }
    doencaId: { type: string, format: uuid, nullable: true }
    doencaTexto: { type: string }
    referenciaId: { type: string, format: uuid, nullable: true }
    referenciaTexto: { type: string }
    observacoes: { type: string }
    origem: { $ref: '#/OrigemProtocoloEnum' }
    autorId: { type: string, format: uuid }
    clinicaId: { type: string, format: uuid, nullable: true }
    dosagens:
      type: array
      items: { $ref: '#/DosagemItemRequest' }

DosagemResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    medicamentoId: { type: string, format: uuid }
    medicamentoTexto: { type: string }
    nomeMedicamento: { type: string }
    dose: { type: string }
    detalhes: { type: string }

ProtocoloResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    titulo: { type: string }
    doencaId: { type: string, format: uuid }
    doencaTexto: { type: string }
    referenciaId: { type: string, format: uuid }
    referenciaTexto: { type: string }
    observacoes: { type: string }
    origem: { $ref: '#/OrigemProtocoloEnum' }
    autorId: { type: string, format: uuid }
    clinicaId: { type: string, format: uuid }
    dosagens:
      type: array
      items: { $ref: '#/DosagemResponse' }

ProtocoloDetalhadoResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    titulo: { type: string }
    referenciaTexto: { type: string }
    referenciaId: { type: string, format: uuid }
    observacoes: { type: string }
    origem: { $ref: '#/OrigemProtocoloEnum' }
    autorId: { type: string, format: uuid }
    dosagens:
      type: array
      items: { $ref: '#/DosagemResponse' }
    alertasGerais:
      type: array
      items: { type: string }

ProtocoloCompletoResponse:
  type: object
  properties:
    doenca: { $ref: '#/DoencaResponse' }
    protocolos:
      type: array
      items: { $ref: '#/ProtocoloDetalhadoResponse' }

# --- CALCULADORA ---

CalculoLivreRequest:
  type: object
  required: [peso, doseInformada, concentracao, unidadePeso]
  properties:
    peso: { type: number, format: double }
    doseInformada: { type: number, format: double }
    concentracao: { type: number, format: double }
    unidadePeso: { type: string, enum: [KG, G] }
    nomeMedicamento: { type: string } # Opcional, só para devolver no DTO
    via: { type: string }
    frequencia: { type: string }
    duracao: { type: string }

# REQUEST PARA VALIDAÇÃO (CATÁLOGO)
CalculoCatalogoRequest:
  type: object
  required: [medicamentoId, especieId, peso, doseInformada, unidadePeso]
  properties:
    medicamentoId: { type: string, format: uuid }
    especieId: { type: string, format: uuid }
    doencaId: { type: string, format: uuid } # Mantém nullable aqui pois é ID
    clinicaId: { type: string, format: uuid }
    usuarioId: { type: string, format: uuid}
    peso: { type: number, format: double }
    doseInformada: { type: number, format: double }
    unidadePeso: { type: string, enum: [KG, G] }
    via: { type: string }

CalculoSeguroRequest:
  type: object
  required: [protocoloId, medicamentoId, peso, unidadePeso]
  properties:
    protocoloId: { type: string, format: uuid }
    medicamentoId: { type: string, format: uuid }
    peso: { type: number, format: double }
    unidadePeso: { type: string, enum: [KG, G] }

CalculoResponse:
  type: object
  properties:
    protocoloTitulo: { type: string }
    medicamentoNome: { type: string }
    referenciaBibliografica: { type: string }
    pesoConsideradoKg: { type: number, format: double }
    doseMinimaMg: { type: number, format: double }
    doseMaximaMg: { type: number, format: double }
    volumeMinimoMl: { type: number, format: double }
    volumeMaximoMl: { type: number, format: double }
    concentracaoUtilizada: { type: string }
    frequencia: { type: string }
    doseCalculadaMg: { type: number, format: double }
    volumeCalculadoMl: { type: number, format: double }
    via: { type: string }
    duracao: { type: string }
    alertas:
      type: array
      items: { type: string }
    # NOVOS CAMPOS DE SEGURANÇA
    statusSeguranca: { $ref: '#/StatusSegurancaEnum' }
    mensagemSeguranca: { type: string }
    refMin: { type: number, format: double }
    refMax: { type: number, format: double }
    refFonte: { type: string }

CalculoValidacaoRequest:
  type: object
  required: [ peso, doseInformada, unidadePeso]
  properties:
    medicamentoId: { type: string, format: uuid, nullable: true }
    especieId: { type: string, format: uuid, nullable: true }
    peso: { type: number, format: double }
    doseInformada: { type: number, format: double, description: "A dose que o vet quer usar (mg/kg)" }
    unidadePeso: { type: string, enum: [KG, G] }
    concentracaoInformada: { type: number, format: double, nullable: true, description: "mg/ml (usado se não houver ID)" }
    # Opcionais para refinar a busca
    doencaId: { type: string, format: uuid, nullable: true }
    via: { type: string, nullable: true }
    clinicaId: { type: string, format: uuid, nullable: true }
    usuarioId: { type: string, format: uuid, nullable: true }

# --- WRAPPERS ---
ApiResponseProtocolo:
  type: object
  properties:
    sucesso: { type: boolean }
    mensagem: { type: string }
    dados: { $ref: '#/ProtocoloResponse' }

ApiResponseListaProtocolo:
  type: object
  properties:
    sucesso: { type: boolean }
    dados:
      type: array
      items: { $ref: '#/ProtocoloResponse' }

ApiResponseProtocoloCompleto:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { $ref: '#/ProtocoloCompletoResponse' }

ApiResponseDoenca:
  type: object
  properties:
    sucesso: { type: boolean }
    mensagem: { type: string }
    dados: { $ref: '#/DoencaResponse' }

ApiResponseListaDoenca:
  type: object
  properties:
    sucesso: { type: boolean }
    dados:
      type: array
      items: { $ref: '#/DoencaResponse' }

ApiResponseCalculo:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { $ref: '#/CalculoResponse' }
```

