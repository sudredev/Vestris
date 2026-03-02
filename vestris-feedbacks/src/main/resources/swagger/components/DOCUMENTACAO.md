## src\main\resources\swagger\components

### schemas.yml

```yaml
# src\main\resources\swagger\components\schemas.yml
TipoSugestaoEnum:
  type: string
  enum: [ESPECIE, DOENCA, PROTOCOLO, CALCULO, OUTRO]

# Request Genérico
SugestaoRequest:
  type: object
  required: [usuarioId, conteudo]
  properties:
    usuarioId:
      type: string
      format: uuid
    tipo:
      $ref: '#/TipoSugestaoEnum'
    conteudo:
      type: string
      description: "JSON stringify ou Texto descrevendo a sugestão (Nome, obs, contexto)"
      example: "{ 'nome': 'Papagaio-verdadeiro', 'obs': 'Comum em resgates' }"

StatusSugestaoEnum:
  type: string
  enum: [PENDENTE, EM_ANALISE, APROVADA, REJEITADA]

SugestaoResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    usuarioId: { type: string, format: uuid }
    tipo: { $ref: '#/TipoSugestaoEnum' }
    status: { $ref: '#/StatusSugestaoEnum' }
    conteudo: { type: string }
    criadoEm: { type: string, format: date-time }

# Wrappers
ApiResponseSugestao:
  type: object
  properties:
    sucesso: { type: boolean }
    mensagem: { type: string }

ApiResponseListaSugestao:
  type: object
  properties:
    sucesso: { type: boolean }
    dados:
      type: array
      items: { $ref: '#/SugestaoResponse' }

AtualizarStatusRequest:
  type: object
  required: [status]
  properties:
    status:
      $ref: '#/StatusSugestaoEnum'
```

