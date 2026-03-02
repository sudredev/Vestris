## src\main\resources\swagger\paths

### contraindicacoes.yml

```yaml
# src\main\resources\swagger\paths\contraindicacoes.yml
# Rota: /api/v1/contraindicacoes
contraindicacoes_colecao:
  post:
    tags:
      - Contraindicacoes
    summary: Cadastrar contraindicação
    operationId: criarContraindicacao
    requestBody:
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/ContraindicacaoRequest'
    responses:
      '200':
        description: Sucesso
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseContraindicacao'

# Rota: /api/v1/medicamentos/{medicamentoId}/contraindicacoes
contraindicacoes_por_medicamento:
  get:
    tags:
      - Contraindicacoes
    summary: Listar contraindicações de um medicamento
    operationId: listarContraindicacoesPorMedicamento
    parameters:
      - name: medicamentoId
        in: path
        required: true
        schema:
          type: string
          format: uuid
    responses:
      '200':
        description: Lista
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseListaContraindicacao'

# Rota: /api/v1/contraindicacoes/{id}
contraindicacoes_por_id:
  parameters:
    - name: id
      in: path
      required: true
      schema:
        type: string
        format: uuid
  get:
    tags: [ Contraindicacoes ]
    summary: Buscar por ID
    operationId: buscarContraindicacaoPorId
    responses:
      '200':
        description: Encontrado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseContraindicacao' }
  put:
    tags: [ Contraindicacoes ]
    summary: Atualizar
    operationId: atualizarContraindicacao
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/ContraindicacaoRequest' }
    responses:
      '200':
        description: Atualizado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseContraindicacao' }
  delete:
    tags: [ Contraindicacoes ]
    summary: Remover
    operationId: deletarContraindicacao
    responses:
      '204': { description: Removido }

# ROTA: /api/v1/seguranca/validar
validar_seguranca:
  get:
    tags: [SegurancaClinica]
    summary: Validar medicamento para espécie
    operationId: validarSeguranca
    parameters:
      - name: medicamentoId
        in: query
        required: true
        schema: { type: string, format: uuid }
      - name: especieId
        in: query
        required: true
        schema: { type: string, format: uuid }
    responses:
      '200':
        description: Lista de alertas (vazio se seguro)
        content:
          application/json:
            schema:
              type: array
              items: { $ref: '../components/schemas.yml#/AlertaSeguranca' }



```

### medicamentos.yml

```yaml
# src\main\resources\swagger\paths\medicamentos.yml
# Rota: /api/v1/medicamentos
medicamentos_colecao:
  post:
    tags:
      - Medicamentos
    summary: Cadastrar medicamento
    operationId: criarMedicamento
    requestBody:
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/MedicamentoRequest'
    responses:
      '200':
        description: Sucesso
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseMedicamento'
  get:
    tags:
      - Medicamentos
    summary: Listar medicamentos
    operationId: listarMedicamentos
    responses:
      '200':
        description: Lista
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseListaMedicamento'

# Rota: /api/v1/medicamentos/{id}
medicamentos_item:
  parameters:
    - name: id
      in: path
      required: true
      schema:
        type: string
        format: uuid
  get:
    tags:
      - Medicamentos
    summary: Buscar por ID
    operationId: buscarMedicamentoPorId
    responses:
      '200':
        description: Encontrado
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseMedicamento'
  put:
    tags:
      - Medicamentos
    summary: Atualizar
    operationId: atualizarMedicamento
    requestBody:
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/MedicamentoRequest'
    responses:
      '200':
        description: Atualizado
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseMedicamento'
  delete:
    tags:
      - Medicamentos
    summary: Remover
    operationId: deletarMedicamento
    responses:
      '204':
        description: Removido
```

### principios-ativos.yml

```yaml
# src\main\resources\swagger\paths\principios-ativos.yml
# Rota: /api/v1/principios-ativos
principios_colecao:
  post:
    tags:
      - PrincipiosAtivos
    summary: Cadastrar princípio ativo
    operationId: criarPrincipioAtivo
    requestBody:
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/PrincipioAtivoRequest'
    responses:
      '200':
        description: Sucesso
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponsePrincipioAtivo'

  get:
    tags:
      - PrincipiosAtivos
    summary: Listar todos
    operationId: listarPrincipiosAtivos
    responses:
      '200':
        description: Lista
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseListaPrincipioAtivo'

# Rota: /api/v1/principios-ativos/{id}
principios_item:
  parameters:
    - name: id
      in: path
      required: true
      schema:
        type: string
        format: uuid
  get:
    tags:
      - PrincipiosAtivos
    summary: Buscar por ID
    operationId: buscarPrincipioAtivoPorId
    responses:
      '200':
        description: Encontrado
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponsePrincipioAtivo'
  put:
    tags:
      - PrincipiosAtivos
    summary: Atualizar
    operationId: atualizarPrincipioAtivo
    requestBody:
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/PrincipioAtivoRequest'
    responses:
      '200':
        description: Atualizado
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponsePrincipioAtivo'
  delete:
    tags:
      - PrincipiosAtivos
    summary: Remover
    description: "Não permite se houver medicamentos vinculados"
    operationId: deletarPrincipioAtivo
    responses:
      '204':
        description: Removido
```

