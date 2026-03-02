## src\main\resources\swagger\paths

### especies.yml

```yaml
# src\main\resources\swagger\paths\especies.yml
paths:
  # ---------------------------------------------------------
  # ROTA 1: Coleção (Listar / Criar)
  # ---------------------------------------------------------
  /api/v1/especies:
    get:
      tags:
        - Especies
      summary: Listar todas as espécies
      operationId: listarEspecies
      responses:
        '200':
          description: Lista recuperada
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseListaEspecie'
    post:
      tags:
        - Especies
      summary: Cadastrar nova espécie
      operationId: criarEspecie
      requestBody:
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/EspecieRequest'
      responses:
        '200':
          description: Espécie criada com sucesso
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseEspecie'

  # ---------------------------------------------------------
  # ROTA 2: Item Específico (Buscar / Editar / Deletar)
  # ---------------------------------------------------------
  /api/v1/especies/{id}:
    parameters:
      - name: id
        in: path
        required: true
        schema:
          type: string
          format: uuid

    get:
      tags:
        - Especies
      summary: Buscar espécie por ID
      operationId: buscarEspeciePorId
      responses:
        '200':
          description: Encontrado
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseEspecie'

    put:
      tags:
        - Especies
      summary: Atualizar dados da espécie
      operationId: atualizarEspecie
      requestBody:
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/EspecieRequest'
      responses:
        '200':
          description: Atualizado com sucesso
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseEspecie'

    delete:
      tags:
        - Especies
      summary: Remover espécie
      operationId: deletarEspecie
      responses:
        '204':
          description: Removido com sucesso

```

### exames-fisicos.yml

```yaml
# src\main\resources\swagger\paths\exames-fisicos.yml
# ROTA: /api/v1/especies/{especieId}/exame-fisico
exame_fisico_por_especie:

  # 1. GET - Buscar o modelo (Já existia, agora isolado)
  get:
    tags: [ExamesFisicos]
    summary: Obter modelo de exame físico da espécie
    operationId: obterModeloExame
    parameters:
      - name: especieId
        in: path
        required: true
        schema: { type: string, format: uuid }
    responses:
      '200':
        description: Modelo encontrado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseModeloExame' }
      '404':
        description: Modelo não definido para esta espécie

  # 2. POST - Criar um modelo novo para a espécie
  post:
    tags: [ExamesFisicos]
    summary: Criar modelo de exame físico
    description: "Define o template padrão para uma espécie específica."
    operationId: criarModeloExame
    parameters:
      - name: especieId
        in: path
        required: true
        schema: { type: string, format: uuid }
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/ModeloExameRequest' }
    responses:
      '201':
        description: Criado com sucesso
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseModeloExame' }

  # 3. PUT - Atualizar o texto do modelo existente
  put:
    tags: [ExamesFisicos]
    summary: Atualizar modelo de exame físico
    operationId: atualizarModeloExame
    parameters:
      - name: especieId
        in: path
        required: true
        schema: { type: string, format: uuid }
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/ModeloExameRequest' }
    responses:
      '200':
        description: Atualizado com sucesso
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseModeloExame' }

  # 4. DELETE - Remover o modelo (voltar ao padrão genérico)
  delete:
    tags: [ExamesFisicos]
    summary: Remover modelo de exame físico
    operationId: deletarModeloExame
    parameters:
      - name: especieId
        in: path
        required: true
        schema: { type: string, format: uuid }
    responses:
      '204':
        description: Removido com sucesso

# ROTA: /api/v1/exames-fisicos (Lista Geral)
exames_fisicos_colecao:
  get:
    tags: [ExamesFisicos]
    summary: Listar todos os modelos cadastrados
    description: "Retorna todos os templates de exame físico de todas as espécies."
    operationId: listarTodosModelosExame
    responses:
      '200':
        description: Lista recuperada com sucesso
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseListaModeloExame' }

```

