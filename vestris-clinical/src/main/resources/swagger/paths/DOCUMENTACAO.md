## src\main\resources\swagger\paths

### calculadora.yml

```yaml
# src\main\resources\swagger\paths\calculadora.yml
# ROTA 1: CÁLCULO BASEADO EM PROTOCOLO (JÁ EXISTENTE, MANTÉM)
calculadora_item:
  post:
    tags: [Calculadora]
    summary: Calcular via Protocolo
    operationId: calcularDosagemSegura
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/CalculoSeguroRequest' }
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseCalculo' }

# ROTA 2: CÁLCULO LIVRE (MATEMÁTICA PURA) - NOVO
calculadora_livre:
  post:
    tags: [Calculadora]
    summary: Calculadora Livre (Sem Validação)
    description: "Faz apenas o cálculo matemático de volume com base na dose e concentração informadas."
    operationId: calcularDoseLivre
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/CalculoLivreRequest' }
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseCalculo' }

# ROTA 3: VALIDAÇÃO DE SEGURANÇA (CATÁLOGO) - NOVO
calculadora_validar:
  post:
    tags: [Calculadora]
    summary: Validar Dose (Catálogo)
    description: "Verifica se a dose está segura cruzando com a base científica."
    operationId: validarDoseCatalogo
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/CalculoCatalogoRequest' }
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseCalculo' }
```

### doencas.yml

```yaml
# src\main\resources\swagger\paths\doencas.yml
paths:
  /api/v1/doencas:
    post:
      tags:
        - Doencas
      summary: Cadastrar nova doença
      operationId: criarDoenca
      requestBody:
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/DoencaRequest'
      responses:
        '200':
          description: Sucesso
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseDoenca'

    get:
      tags:
        - Doencas
      summary: Listar todas as doenças
      operationId: listarDoencas
      responses:
        '200':
          description: Lista recuperada
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseListaDoenca'

  /api/v1/doencas/{id}:
    parameters:
      - name: id
        in: path
        required: true
        schema:
          type: string
          format: uuid
    get:
      tags:
        - Doencas
      summary: Buscar doença por ID
      operationId: buscarDoencaPorId
      responses:
        '200':
          description: Encontrado
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseDoenca'
    put:
      tags:
        - Doencas
      summary: Atualizar doença
      operationId: atualizarDoenca
      requestBody:
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/DoencaRequest'
      responses:
        '200':
          description: Atualizado
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseDoenca'
    delete:
      tags:
        - Doencas
      summary: Remover doença
      operationId: deletarDoenca
      responses:
        '204':
          description: Removido

  /api/v1/doencas/por-especie/{especieId}:
    get:
      tags:
        - Doencas
      summary: Listar doenças de uma espécie específica
      operationId: listarDoencasPorEspecie
      parameters:
        - name: especieId
          in: path
          required: true
          schema:
            type: string
            format: uuid
      responses:
        '200':
          description: Lista filtrada
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseListaDoenca'

```

### protocolos.yml

```yaml
# src\main\resources\swagger\paths\protocolos.yml
# vestris-clinical/src/main/resources/swagger/paths/protocolos.yml
paths:
  /api/v1/protocolos:
    post:
      tags:
        - Protocolos
      summary: Criar protocolo terapêutico
      operationId: criarProtocolo
      requestBody:
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ProtocoloRequest'
      responses:
        '200':
          description: Sucesso
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseProtocolo'

  /api/v1/protocolos/{id}:
    parameters:
      - name: id
        in: path
        required: true
        schema:
          type: string
          format: uuid
    get:
      tags:
        - Protocolos
      summary: Buscar protocolo por ID
      operationId: buscarProtocoloPorId
      responses:
        '200':
          description: Encontrado
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseProtocolo'
    put:
      tags:
        - Protocolos
      summary: Atualizar protocolo
      operationId: atualizarProtocolo
      requestBody:
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ProtocoloRequest'
      responses:
        '200':
          description: Atualizado
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseProtocolo'
    delete:
      tags:
        - Protocolos
      summary: Remover protocolo
      operationId: deletarProtocolo
      responses:
        '204':
          description: Removido

  /api/v1/doencas/{doencaId}/protocolos:
    get:
      tags:
        - Protocolos
      summary: Listar protocolos de uma doença
      description: "Retorna protocolos Oficiais, Institucionais (da clínica) e Próprios (do usuário)."
      operationId: listarProtocolosPorDoenca
      parameters:
        - name: doencaId
          in: path
          required: true
          schema:
            type: string
            format: uuid
        # NOVOS PARÂMETROS PARA FILTRAGEM INTELIGENTE
        - name: clinicaId
          in: query
          required: false
          schema: { type: string, format: uuid }
        - name: usuarioId
          in: query
          required: false
          schema: { type: string, format: uuid }
      responses:
        '200':
          description: Lista
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseListaProtocolo'

  /api/v1/especies/{especieId}/doencas/{doencaId}/protocolo-completo:
    get:
      tags: [ Protocolos ]
      summary: Obter visão completa do tratamento
      description: "Retorna doença, protocolo, dosagens e contraindicações combinadas"
      operationId: obterProtocoloCompleto
      parameters:
        - name: especieId
          in: path
          required: true
          schema: { type: string, format: uuid }
        - name: doencaId
          in: path
          required: true
          schema: { type: string, format: uuid }
      responses:
        '200':
          description: Sucesso
          content:
            application/json:
              schema: { $ref: '../components/schemas.yml#/ApiResponseProtocoloCompleto' }
```

