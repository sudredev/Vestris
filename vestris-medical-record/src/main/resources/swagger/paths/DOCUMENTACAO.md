## src\main\resources\swagger\paths

### atendimentos.yml

```yaml
# src\main\resources\swagger\paths\atendimentos.yml
# Rota: /api/v1/atendimentos/agendar (NOVA ROTA DE AGENDAMENTO)
agendamento_item:
  post:
    tags: [Atendimentos]
    summary: Agendar novo atendimento
    description: "Cria um registro com status AGENDADO. Dados clínicos não são permitidos aqui."
    operationId: agendarAtendimento
    requestBody:
      content:
        application/json:
          # CORREÇÃO: Aponta para AgendamentoRequest
          schema: { $ref: '../components/schemas.yml#/AgendamentoRequest' }
    responses:
      '200':
        description: Agendado com sucesso
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseAtendimento' }

# Rota: /api/v1/atendimentos (Rota Legada/Genérica - Mantida por compatibilidade ou criação direta completa)
atendimentos_item:
  post:
    tags: [Atendimentos]
    summary: Registrar atendimento completo (Legado)
    operationId: criarAtendimento
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/AtendimentoRequest' }
    responses:
      '200':
        description: Sucesso
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseAtendimento' }
  get:
    tags: [Atendimentos]
    summary: Listar meus atendimentos (Agenda)
    operationId: listarMeusAtendimentos
    parameters:
      - name: veterinarioId
        in: query
        required: true
        schema: { type: string, format: uuid }
      - name: status
        in: query
        required: false
        schema: { $ref: '../components/schemas.yml#/StatusAtendimentoEnum' }
      - name: pacienteId
        in: query
        required: false
        schema: { type: string, format: uuid }
      - name: dataInicio
        in: query
        required: false
        schema: { type: string, format: date }
      - name: dataFim
        in: query
        required: false
        schema: { type: string, format: date }
    responses:
      '200':
        description: Lista
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseListaAtendimento' }

# Rota: /api/v1/atendimentos/{id}/finalizar (FINALIZAÇÃO)
atendimentos_finalizados:
  put:
    tags: [Atendimentos]
    summary: Finalizar atendimento (Preencher Prontuário)
    description: "Recebe os dados clínicos e muda status para REALIZADO"
    operationId: finalizarAtendimento
    parameters:
      - name: id
        in: path
        required: true
        schema: { type: string, format: uuid }
    requestBody:
      content:
        application/json:
          # CORREÇÃO: Aponta para FinalizacaoAtendimentoRequest (Valida campos obrigatórios)
          schema: { $ref: '../components/schemas.yml#/FinalizacaoAtendimentoRequest' }
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseAtendimento' }

# Rota: /api/v1/pacientes/{pacienteId}/atendimentos
atendimentos_por_paciente:
  get:
    tags: [Atendimentos]
    summary: Histórico clínico do paciente
    operationId: listarAtendimentosPorPaciente
    parameters:
      - name: pacienteId
        in: path
        required: true
        schema: { type: string, format: uuid }
    responses:
      '200':
        description: Histórico
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseListaAtendimento' }

# ROTA: /api/v1/atendimentos/{id}
atendimentos_por_id:
  parameters:
    - name: id
      in: path
      required: true
      schema: { type: string, format: uuid }
  get:
    tags: [Atendimentos]
    summary: Ver detalhes
    operationId: buscarAtendimentoPorId
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseAtendimento' }
  put:
    tags: [Atendimentos]
    summary: Atualizar (Evoluir status, adicionar obs)
    operationId: atualizarAtendimento
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/AtendimentoRequest' }
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseAtendimento' }

# ROTA: /api/v1/atendimentos/{id}/status:
atendimentos_status:
  patch:
    tags: [Atendimentos]
    summary: Alterar status do atendimento
    operationId: atualizarStatusAtendimento
    parameters:
      - name: id
        in: path
        required: true
        schema: { type: string, format: uuid }
    requestBody:
      content:
        application/json:
          schema:
            type: object
            properties:
              status: { $ref: '../components/schemas.yml#/StatusAtendimentoEnum' }
    responses:
      '200':
        description: Status atualizado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseAtendimento' }
```

### exames-anexo.yml

```yaml
# src\main\resources\swagger\paths\exames-anexo.yml
# vestris-medical-record/src/main/resources/swagger/paths/exames-anexos.yml

# ROTA: /api/v1/atendimentos/{atendimentoId}/exames
exames_por_atendimento:
  get:
    tags: [Exames]
    summary: Listar exames de um atendimento
    operationId: listarExamesPorAtendimento
    parameters:
      - name: atendimentoId
        in: path
        required: true
        schema: { type: string, format: uuid }
    responses:
      '200':
        description: Lista recuperada
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseListaExameAnexo' }

  post:
    tags: [Exames]
    summary: Anexar novo exame/arquivo
    operationId: uploadExame
    parameters:
      - name: atendimentoId
        in: path
        required: true
        schema: { type: string, format: uuid }
    requestBody:
      content:
        multipart/form-data:
          schema:
            type: object
            required: [ arquivo ]
            properties:
              arquivo:
                type: string
                format: binary
                description: "Arquivo PDF, JPG ou PNG"
              observacoes:
                type: string
                description: "Notas sobre o exame (opcional)"
    responses:
      '201':
        description: Upload realizado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseExameAnexo' }

# ROTA: /api/v1/exames/{id}
exame_item:
  delete:
    tags: [Exames]
    summary: Remover anexo
    operationId: deletarExame
    parameters:
      - name: id
        in: path
        required: true
        schema: { type: string, format: uuid }
    responses:
      '204':
        description: Removido com sucesso
```

### pacientes.yml

```yaml
# src\main\resources\swagger\paths\pacientes.yml
# Rota: /api/v1/pacientes
pacientes_item:
  post:
    tags: [Pacientes]
    summary: Cadastrar novo paciente
    operationId: criarPaciente
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/PacienteRequest' }
    responses:
      '200':
        description: Sucesso
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponsePaciente' }
  get:
    tags: [Pacientes]
    summary: Listar meus pacientes
    description: "Filtra pelo veterinário logado (ou ID passado)"
    operationId: listarPacientes
    parameters:
      - name: veterinarioId
        in: query
        required: true
        schema: { type: string, format: uuid }
    responses:
      '200':
        description: Lista
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseListaPaciente' }

# Rota: /api/v1/pacientes/{id}
paciente_por_id:
  parameters:
    - name: id
      in: path
      required: true
      schema: { type: string, format: uuid }
  get:
    tags: [Pacientes]
    summary: Buscar paciente por ID
    operationId: buscarPacientePorId
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponsePaciente' }
  put:
    tags: [Pacientes]
    summary: Atualizar dados do paciente
    operationId: atualizarPaciente
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/PacienteRequest' }
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponsePaciente' }

  # --- BLOCO ADICIONADO ---
  delete:
    tags: [Pacientes]
    summary: Remover paciente
    description: "Remove o paciente apenas se não houver atendimentos vinculados"
    operationId: deletarPaciente
    responses:
      '204':
        description: Removido com sucesso (No Content)
```

