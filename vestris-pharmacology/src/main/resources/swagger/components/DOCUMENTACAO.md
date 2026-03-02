## src\main\resources\swagger\components

### schemas.yml

```yaml
# src\main\resources\swagger\components\schemas.yml
PrincipioAtivoRequest:
  type: object
  required: [nome]
  properties:
    nome:
      type: string
      example: "Enrofloxacina"
    descricao:
      type: string
    grupoFarmacologico:
      type: string
      example: "Antibiótico / Fluoroquinolona"

PrincipioAtivoResponse:
  type: object
  properties:
    id:
      type: string
      format: uuid
    nome:
      type: string
    descricao:
      type: string
    grupoFarmacologico:
      type: string

# --- DTOs Medicamento ---
MedicamentoRequest:
  type: object
  required: [nome, principioAtivoId]
  properties:
    nome:
      type: string
      example: "Baytril 10%"
    principioAtivoId:
      type: string
      format: uuid
    principioAtivoNome:
      type: string
    concentracao:
      type: string
      example: "100 mg/ml"
    fabricante:
      type: string
    formaFarmaceutica:
      type: string
      example: "Injetável / Oral"

MedicamentoResponse:
  type: object
  properties:
    id:
      type: string
      format: uuid
    nome:
      type: string
    principioAtivoId:
      type: string
      format: uuid
    principioAtivoNome:
      type: string
    concentracao:
      type: string
    fabricante:
      type: string
    formaFarmaceutica:
      type: string
GravidadeEnum:
  type: string
  enum: [LEVE, MODERADA, GRAVE, FATAL]

ContraindicacaoRequest:
  type: object
  required: [ especieId, gravidade, descricao ] # MedicamentoId não é mais obrigatório se tiver Principio
  properties:
    medicamentoId:
      type: string
      format: uuid
      nullable: true
    principioAtivoId:   # <--- CAMPO NOVO
      type: string
      format: uuid
      nullable: true
    especieId:
      type: string
      format: uuid
    referenciaId:       # <--- PODE REMOVER OU DEIXAR COMO LEGADO
      type: string
      format: uuid
      nullable: true
    referenciaTexto:    # <--- CAMPO NOVO PREFERENCIAL
      type: string
    gravidade:
      $ref: '#/GravidadeEnum'
    descricao:
      type: string
      example: "Causa toxicidade neurológica severa."

ContraindicacaoResponse:
  type: object
  properties:
    id:
      type: string
      format: uuid
    medicamentoId:
      type: string
      format: uuid
    especieId:
      type: string
      format: uuid
    referenciaId:
      type: string
      format: uuid
    gravidade:
      $ref: '#/GravidadeEnum'
    descricao:
      type: string

AlertaSeguranca:
  type: object
  properties:
    nivel: { type: string, enum: [LEVE, MODERADA, GRAVE, FATAL] }
    descricao: { type: string }
    medicamento: { type: string }
    principioAtivo: { type: string }

  # Wrappers
ApiResponseContraindicacao:
  type: object
  properties:
    sucesso: { type: boolean }
    mensagem:
      type: string
    dados: { $ref: '#/ContraindicacaoResponse' }

ApiResponseListaContraindicacao:
  type: object
  properties:
    sucesso: { type: boolean }
    dados:
      type: array
      items: { $ref: '#/ContraindicacaoResponse' }

ApiResponsePrincipioAtivo:
  type: object
  properties:
    sucesso: { type: boolean }
    mensagem:
      type: string
    dados: { $ref: '#/PrincipioAtivoResponse' }

ApiResponseListaPrincipioAtivo:
  type: object
  properties:
    sucesso: { type: boolean }
    dados:
      type: array
      items: { $ref: '#/PrincipioAtivoResponse' }

ApiResponseMedicamento:
  type: object
  properties:
    sucesso: { type: boolean }
    mensagem:
      type:  string
    dados: { $ref: '#/MedicamentoResponse' }

ApiResponseListaMedicamento:
  type: object
  properties:
    sucesso: { type: boolean }
    dados:
      type: array
      items: { $ref: '#/MedicamentoResponse' }
```

