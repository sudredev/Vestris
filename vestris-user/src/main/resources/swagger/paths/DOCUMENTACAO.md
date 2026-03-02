## src\main\resources\swagger\paths

### admin.yml

```yaml
# src\main\resources\swagger\paths\admin.yml
# /api/v1/admin/impersonate
impersonate:
  post:
    tags: [AdminGlobal]
    summary: Gerar token de acesso para qualquer usuário
    operationId: impersonateUser
    parameters:
      - name: adminId
        in: query
        required: true
        schema: { type: string, format: uuid }
      - name: targetUserId
        in: query
        required: true
        schema: { type: string, format: uuid }
    responses:
      '200':
        description: Token gerado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseToken' }
```

### auditoria.yml

```yaml
# src\main\resources\swagger\paths\auditoria.yml
# ROTA: /api/v1/auditoria (Listagem)
auditoria_colecao:
  get:
    tags: [Auditoria]
    summary: Listar logs de auditoria da clínica
    operationId: listarLogsAuditoria
    parameters:
      - name: clinicaId
        in: query
        required: true
        schema: { type: string, format: uuid }
      - name: dataInicio
        in: query
        schema: { type: string, format: date }
      - name: dataFim
        in: query
        schema: { type: string, format: date }
    responses:
      '200':
        description: Lista de eventos
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseListaAuditoria' }

# NOVA ROTA: /api/v1/auditoria/evento (Registro manual do Front)
auditoria_evento:
  post:
    tags: [Auditoria]
    summary: Registrar evento de frontend
    operationId: registrarEventoAuditoria
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/EventoAuditoriaRequest' }
    responses:
      '200':
        description: Evento registrado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseSucesso' }
```

### clinica.yml

```yaml
# src\main\resources\swagger\paths\clinica.yml
clinica_geral:
  get:
    tags: [Clinica]
    summary: Obter dados da minha clínica
    operationId: obterMinhaClinica
    parameters:
      - name: usuarioId
        in: query
        required: true
        schema: { type: string, format: uuid }
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseClinica' }

  post:
    tags: [Clinica]
    summary: Criar ou Atualizar minha clínica
    operationId: salvarMinhaClinica
    parameters:
      - name: usuarioId
        in: query
        required: true
        schema: { type: string, format: uuid }
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/ClinicaRequest' }
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseClinica' }

clinica_equipe:
  get:
    tags: [ Clinica ]
    summary: Listar veterinários da minha clínica
    operationId: listarEquipe
    parameters:
      - name: usuarioId
        in: query
        required: true
        schema: { type: string, format: uuid }
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseListaUsuario' }

  post:
    tags: [ Clinica ]
    summary: Adicionar veterinário à equipe
    operationId: adicionarMembroEquipe
    parameters:
      - name: usuarioId
        in: query
        required: true
        schema: { type: string, format: uuid }
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/NovoMembroRequest' }
    responses:
      '201':
        description: Membro criado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseUsuario' }
  delete:
    tags: [ Clinica ]
    summary: Remover veterinário da equipe
    description: "Desvincula o veterinário da clínica. Ele perde acesso aos dados, mas o histórico é mantido."
    operationId: removerMembroEquipe
    parameters:
      - name: usuarioId
        in: query
        description: "ID do Admin que está removendo"
        required: true
        schema: { type: string, format: uuid }
      - name: membroId
        in: query
        description: "ID do Veterinário a ser removido"
        required: true
        schema: { type: string, format: uuid }
    responses:
      '204':
        description: Removido com sucesso

```

### login.yml

```yaml
# src\main\resources\swagger\paths\login.yml
post:
  tags:
    - Autenticacao
  summary: Fazer login e obter Token
  operationId: login
  requestBody:
    content:
      application/json:
        schema:
          $ref: '../components/schemas.yml#/LoginRequest'
  responses:
    '200':
      description: Login com sucesso
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/ApiResponseToken'
```

### registro.yml

```yaml
# src\main\resources\swagger\paths\registro.yml
post:
  tags:
    - Autenticacao
  summary: Criar nova conta
  operationId: registrarUsuario
  requestBody:
    content:
      application/json:
        schema:
          $ref: '../components/schemas.yml#/RegistroRequest'
  responses:
    '200':
      description: Conta criada
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/ApiResponseUsuario'
```

### usuarios.yml

```yaml
# src\main\resources\swagger\paths\usuarios.yml
# vestris-user/src/main/resources/swagger/paths/usuarios.yml
# Rota: /api/v1/usuarios
usuarios_colecao:
  get:
    tags: [GestaoUsuarios]
    summary: Listar usuários com filtros
    operationId: listarUsuarios
    parameters:
      - name: perfil
        in: query
        description: "Filtrar por perfil"
        required: false
        schema:
          type: string
          enum: [ADMIN_GLOBAL, ADMIN_GESTOR, ADMIN_CLINICO, VETERINARIO, ESTUDANTE]
      - name: apenasComCrmv
        in: query
        description: "Se true, traz apenas quem tem CRMV preenchido"
        required: false
        schema:
          type: boolean
    responses:
      '200':
        description: Lista recuperada
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseListaUsuario'

# Rota: /api/v1/usuarios/{id}
usuarios_item:
  parameters:
    - name: id
      in: path
      required: true
      schema: { type: string, format: uuid }
  get:
    tags: [GestaoUsuarios]
    summary: Buscar usuário por ID
    operationId: buscarUsuarioPorId
    responses:
      '200':
        description: Encontrado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseUsuario' }
  put:
    tags: [GestaoUsuarios]
    summary: Atualizar dados cadastrais
    description: "Atualiza nome e CRMV. Não atualiza senha/email por aqui."
    operationId: atualizarUsuario
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/AtualizacaoUsuarioRequest' }
    responses:
      '200':
        description: Atualizado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseUsuario' }
  delete:
    tags: [GestaoUsuarios]
    summary: Remover usuário
    operationId: deletarUsuario
    responses:
      '204': { description: Removido }
```

