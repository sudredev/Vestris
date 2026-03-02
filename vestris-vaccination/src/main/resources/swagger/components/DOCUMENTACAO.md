## src\main\resources\swagger\components

### schemas.yml

```yaml
# src\main\resources\swagger\components\schemas.yml
VacinaRequest:
  type: object
  required: [nome]
  properties:
    nome:
      type: string
    fabricante:
      type: string
    tipoVacina:
      type: string
    descricao:
      type: string
    doencaAlvo:
      type: string

VacinaResponse:
  type: object
  properties:
    id:
      type: string
      format: uuid
    nome:
      type: string
    fabricante:
      type: string
    tipoVacina:
      type: string
    descricao:
      type: string
    doencaAlvo:
      type: string
    criadoEm:
      type: string
      format: date-time

# --- VACINAÇÃO DO PACIENTE ---
AplicacaoVacinaRequest:
  type: object
  required: [ vacinaId, dataAplicacao ]
  properties:
    vacinaId:
      type: string
      format: uuid
    dataAplicacao:
      type: string
      format: date
    dataProximaDose:
      type: string
      format: date
      nullable: true
    lote:
      type: string
    observacoes:
      type: string

AplicacaoVacinaResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    pacienteId: { type: string, format: uuid }
    vacinaId: { type: string, format: uuid }
    vacinaNome: { type: string } # Nome enriquecido
    dataAplicacao: { type: string, format: date }
    dataProximaDose: { type: string, format: date }
    lote: { type: string }
    observacoes: { type: string }
    veterinarioNome: { type: string } # Quem aplicou

ApiResponseAplicacaoVacina:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { $ref: '#/AplicacaoVacinaResponse' }

ApiResponseListaAplicacaoVacina:
  type: object
  properties:
    sucesso: { type: boolean }
    dados:
      type: array
      items: { $ref: '#/AplicacaoVacinaResponse' }

# --- PROTOCOLO VACINAL (NOVO) ---
ProtocoloVacinalRequest:
  type: object
  required: [especieId, vacinaId, referenciaId, idadeMinimaDias]
  properties:
    especieId:
      type: string
      format: uuid
    vacinaId:
      type: string
      format: uuid
    referenciaId:
      type: string
      format: uuid
      description: "Fonte científica ou legal que exige/recomenda esta vacina"
    idadeMinimaDias:
      type: integer
      example: 45
    diasParaReforco:
      type: integer
      description: "Se nulo ou zero, é dose única"
      example: 21
    obrigatoria:
      type: boolean
      description: "Se é exigida por lei (IBAMA/MAPA)"
    observacoes:
      type: string
      example: "Apenas para animais que terão contato com aves de produção."

ProtocoloVacinalResponse:
  type: object
  properties:
    id:
      type: string
      format: uuid
    especieId:
      type: string
      format: uuid
    vacinaId:
      type: string
      format: uuid
    referenciaId:
      type: string
      format: uuid
    nomeVacina:
      type: string
    idadeMinimaDias:
      type: integer
    diasParaReforco:
      type: integer
    obrigatoria:
      type: boolean
    observacoes:
      type: string

# Wrappers
ApiResponseVacina:
  type: object
  properties:
    sucesso: { type: boolean }
    mensagem:
      type: string
    dados: { $ref: '#/VacinaResponse' }
ApiResponseListaVacina:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { type: array, items: { $ref: '#/VacinaResponse' } }

ApiResponseProtocoloVacinal:
  type: object
  properties:
    sucesso: { type: boolean }
    mensagem:
      type: string
    dados: { $ref: '#/ProtocoloVacinalResponse' }
ApiResponseListaProtocoloVacinal:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { type: array, items: { $ref: '#/ProtocoloVacinalResponse' } }
```

