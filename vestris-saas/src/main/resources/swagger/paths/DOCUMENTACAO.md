## src\main\resources\swagger\paths

### assinaturas.yml

```yaml
# src\main\resources\swagger\paths\assinaturas.yml
# ROTA: /api/v1/saas/minha-assinatura
assinatura_atual:
  get:
    tags: [Assinaturas]
    summary: Obter status da assinatura da clínica logada
    operationId: obterMinhaAssinatura
    parameters:
      - name: clinicaId
        in: query
        required: true
        schema: { type: string, format: uuid }
    responses:
      '200':
        description: Detalhes da assinatura vigente
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseAssinatura' }

# ROTA: /api/v1/saas/assinar
assinar_plano:
  post:
    tags: [Assinaturas]
    summary: Contratar ou mudar de plano
    description: "Inicia o fluxo de checkout ou altera o plano imediatamente se for upgrade gratuito/trial"
    operationId: assinarPlano
    parameters:
      - name: clinicaId
        in: query
        required: true
        schema: { type: string, format: uuid }
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/AssinarPlanoRequest' }
    responses:
      '200':
        description: Sucesso (ou Link de Pagamento no futuro)
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseAssinatura' }
```

### auth-public.yml

```yaml
# src\main\resources\swagger\paths\auth-public.yml
# ROTA: /api/v1/public/cadastro-saas
cadastro_saas:
  post:
    tags: [Publico]
    summary: Criar conta com plano e clínica
    operationId: cadastrarClienteSaas
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/CadastroSaasRequest' }
    responses:
      '201':
        description: Conta criada e logada
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseToken' }
```

### planos.yml

```yaml
# src\main\resources\swagger\paths\planos.yml
# ROTA: /api/v1/saas/planos
planos_colecao:
  get:
    tags: [Planos]
    summary: Listar planos disponíveis
    operationId: listarPlanos
    responses:
      '200':
        description: Lista de planos
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseListaPlano' }

  post:
    tags: [Planos]
    summary: Criar novo plano (Super Admin)
    operationId: criarPlano
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/PlanoRequest' }
    responses:
      '201':
        description: Plano criado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponsePlano' }

# ROTA: /api/v1/saas/planos/{id}
planos_item:
  get:
    tags: [Planos]
    summary: Buscar plano por ID
    operationId: buscarPlanoPorId
    parameters:
      - name: id
        in: path
        required: true
        schema: { type: string, format: uuid }
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponsePlano' }
```

