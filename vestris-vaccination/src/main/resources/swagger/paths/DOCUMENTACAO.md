## src\main\resources\swagger\paths

### protocolos-vacinais.yml

```yaml
# src\main\resources\swagger\paths\protocolos-vacinais.yml
# Rota: /api/v1/protocolos-vacinais
protocolos_colecao:
  post:
    tags:
      - ProtocolosVacinais
    summary: Criar protocolo
    operationId: criarProtocoloVacinal
    requestBody:
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/ProtocoloVacinalRequest'
    responses:
      '200':
        description: Sucesso
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseProtocoloVacinal'

# Rota: /api/v1/especies/{especieId}/protocolos-vacinais
protocolos_por_especie:
  get:
    tags:
      - ProtocolosVacinais
    summary: Listar por espécie
    operationId: listarProtocolosPorEspecie
    parameters:
      - name: especieId
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
              $ref: '../components/schemas.yml#/ApiResponseListaProtocoloVacinal'

# Rota: /api/v1/protocolos-vacinais/{id}
protocolos_item:
  parameters:
    - name: id
      in: path
      required: true
      schema:
        type: string
        format: uuid
  get:
    tags:
      - ProtocolosVacinais
    summary: Buscar protocolo por ID
    operationId: buscarProtocoloVacinalPorId
    responses:
      '200':
        description: Encontrado
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseProtocoloVacinal'
  put:
    tags:
      - ProtocolosVacinais
    summary: Atualizar protocolo
    operationId: atualizarProtocoloVacinal
    requestBody:
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/ProtocoloVacinalRequest'
    responses:
      '200':
        description: Atualizado
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseProtocoloVacinal'
  delete:
    tags:
      - ProtocolosVacinais
    summary: Remover protocolo
    operationId: deletarProtocoloVacinal
    responses:
      '204':
        description: Removido
```

### vacinacao-paciente.yml

```yaml
# src\main\resources\swagger\paths\vacinacao-paciente.yml
# ROTA: /api/v1/pacientes/{pacienteId}/vacinas
vacinacao_paciente:
  get:
    tags: [VacinacaoPaciente]
    summary: Listar vacinas aplicadas no paciente
    operationId: listarVacinasDoPaciente
    parameters:
      - name: pacienteId
        in: path
        required: true
        schema: { type: string, format: uuid }
    responses:
      '200':
        description: Histórico vacinal
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseListaAplicacaoVacina' }

  post:
    tags: [VacinacaoPaciente]
    summary: Registrar aplicação de vacina
    operationId: registrarVacinaPaciente
    parameters:
      - name: pacienteId
        in: path
        required: true
        schema: { type: string, format: uuid }
      - name: veterinarioId
        in: query
        required: true
        schema: { type: string, format: uuid }
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/AplicacaoVacinaRequest' }
    responses:
      '201':
        description: Registrado com sucesso
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseAplicacaoVacina' }

# ROTA: /api/v1/vacinas-aplicadas/{id}
vacinacao_item:
  delete:
    tags: [VacinacaoPaciente]
    summary: Remover registro de vacina (se erro)
    operationId: deletarVacinaAplicada
    parameters:
      - name: id
        in: path
        required: true
        schema: { type: string, format: uuid }
    responses:
      '204': { description: Removido }
```

### vacinas.yml

```yaml
# src\main\resources\swagger\paths\vacinas.yml
# Rota: /api/v1/vacinas
vacinas_colecao:
  post:
    tags:
      - Vacinas
    summary: Cadastrar vacina
    operationId: criarVacina
    requestBody:
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/VacinaRequest'
    responses:
      '200':
        description: Sucesso
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseVacina'
  get:
    tags:
      - Vacinas
    summary: Listar todas
    operationId: listarVacinas
    responses:
      '200':
        description: Lista
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseListaVacina'

# Rota: /api/v1/vacinas/{id}
vacinas_item:
  parameters:
    - name: id
      in: path
      required: true
      schema:
        type: string
        format: uuid
  get:
    tags:
      - Vacinas
    summary: Buscar por ID
    operationId: buscarVacinaPorId
    responses:
      '200':
        description: Encontrado
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseVacina'
  put:
    tags:
      - Vacinas
    summary: Atualizar vacina
    operationId: atualizarVacina
    requestBody:
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/VacinaRequest'
    responses:
      '200':
        description: Atualizado
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseVacina'
  delete:
    tags:
      - Vacinas
    summary: Remover vacina
    description: "Não permite remover se estiver em uso num protocolo"
    operationId: deletarVacina
    responses:
      '204':
        description: Removido
```

