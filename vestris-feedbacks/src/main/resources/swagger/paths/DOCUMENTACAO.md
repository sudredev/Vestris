## src\main\resources\swagger\paths

### sugestoes.yml

```yaml
# src\main\resources\swagger\paths\sugestoes.yml
# ROTA: /api/v1/sugestoes/especies
sugestoes_especies:
  post:
    tags: [Sugestoes]
    summary: Sugerir nova espécie
    operationId: sugerirEspecie
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/SugestaoRequest' }
    responses:
      '200':
        description: Recebido
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseSugestao' }

# ROTA: /api/v1/sugestoes/doencas
sugestoes_doencas:
  post:
    tags: [Sugestoes]
    summary: Sugerir doença
    operationId: sugerirDoenca
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/SugestaoRequest' }
    responses:
      '200':
        description: Recebido
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseSugestao' }

# ROTA: /api/v1/sugestoes/protocolos
sugestoes_protocolos:  
  post:
    tags: [Sugestoes]
    summary: Sugerir melhoria em protocolo
    operationId: sugerirProtocolo
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/SugestaoRequest' }
    responses:
      '200':
        description: Recebido
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseSugestao' }

#ROTA: /api/v1/sugestoes/calculos
sugestoes_calculos:
  post:
    tags: [Sugestoes]
    summary: Sugerir novo tipo de cálculo
    operationId: sugerirCalculo
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/SugestaoRequest' }
    responses:
      '200':
        description: Recebido
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseSugestao' }

#ROTA: /api/v1/sugestoes
sugestoes_global:
  get:
    tags: [Sugestoes]
    summary: Listar sugestões recebidas
    description: "Permite filtrar por tipo (ESPECIE, DOENCA, etc) e status"
    operationId: listarSugestoes
    parameters:
      - name: tipo
        in: query
        required: false
        schema:
          $ref: '../components/schemas.yml#/TipoSugestaoEnum'
      - name: status
        in: query
        required: false
        schema:
          $ref: '../components/schemas.yml#/StatusSugestaoEnum'
    responses:
      '200':
        description: Lista recuperada
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseListaSugestao'

# ROTA: /api/v1/sugestoes/{id}/status
sugestoes_atualizar_status:
  patch:
    tags: [Sugestoes]
    summary: Alterar status da sugestão (Admin)
    operationId: atualizarStatusSugestao
    parameters:
      - name: id
        in: path
        required: true
        schema:
          type: string
          format: uuid
    requestBody:
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/AtualizarStatusRequest'
    responses:
      '200':
        description: Status atualizado
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseSugestao'

```

