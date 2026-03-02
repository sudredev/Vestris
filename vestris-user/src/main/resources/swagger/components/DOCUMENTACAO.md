## src\main\resources\swagger\components

### schemas.yml

```yaml
# src\main\resources\swagger\components\schemas.yml
# vestris-user/src/main/resources/swagger/components/schemas.yml

# --- ENUMS ---
UsuarioResponsePerfilEnum:
  type: string
  enum: [ADMIN_GLOBAL, ADMIN_GESTOR, ADMIN_CLINICO, VETERINARIO, ESTUDANTE]

UsuarioResponseScopeEnum:
  type: string
  enum: [ GLOBAL, TENANT ]

# --- AUTH & USER ---
RegistroRequest:
  type: object
  required: [nome, email, senha]
  properties:
    nome: { type: string }
    email: { type: string, format: email }
    senha: { type: string, format: password }
    crmv: { type: string }

LoginRequest:
  type: object
  required: [email, senha]
  properties:
    email: { type: string }
    senha: { type: string }

UsuarioResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    nome: { type: string }
    email: { type: string }
    perfil: { $ref: '#/UsuarioResponsePerfilEnum' }
    crmv: { type: string }
    scope: { $ref: '#/UsuarioResponseScopeEnum' }

TokenResponse:
  type: object
  properties:
    token: { type: string }
    tipo: { type: string, example: "Bearer" }
    expiraEm: { type: string }

AtualizacaoUsuarioRequest:
  type: object
  properties:
    nome: { type: string }
    crmv: { type: string }

# --- CLÍNICA ---
ClinicaRequest:
  type: object
  required: [ nomeFantasia ]
  properties:
    nomeFantasia: { type: string }
    razaoSocial: { type: string }
    cnpj: { type: string }
    endereco: { type: string }
    telefone: { type: string }
    emailContato: { type: string }
    logoBase64: { type: string }

ClinicaResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    nomeFantasia: { type: string }
    razaoSocial: { type: string }
    cnpj: { type: string }
    endereco: { type: string }
    telefone: { type: string }
    emailContato: { type: string }
    logoBase64: { type: string }

NovoMembroRequest:
  type: object
  required: [ nome, email, senha, crmv ]
  properties:
    nome: { type: string }
    email: { type: string, format: email }
    senha: { type: string }
    crmv: { type: string }

# Enums para o Frontend saber o que mandar
AcaoAuditoriaEnum:
  type: string
  enum:
    - PDF_RECEITA_GERADO
    - PDF_MANEJO_GERADO
    - PDF_PRONTUARIO_GERADO
    - VISUALIZOU_PRONTUARIO

EventoAuditoriaRequest:
  type: object
  required: [ acao, entidade, idAlvo, descricao ]
  properties:
    acao: { $ref: '#/AcaoAuditoriaEnum' }
    entidade: { type: string, example: "PDF" }
    idAlvo: { type: string, format: uuid }
    descricao: { type: string }
    metadados: { type: string, description: "JSON stringificado opcional" }

AuditoriaResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    clinicaId: { type: string, format: uuid }
    usuarioId: { type: string, format: uuid }
    acao: { type: string }
    entidade: { type: string }
    idAlvo: { type: string, format: uuid }
    detalhes: { type: string }
    metadados: { type: string } # Adicionado
    criadoEm: { type: string, format: date-time }

# --- WRAPPERS ---
ApiResponseUsuario:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { $ref: '#/UsuarioResponse' }

ApiResponseToken:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { $ref: '#/TokenResponse' }

ApiResponseListaUsuario:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { type: array, items: { $ref: '#/UsuarioResponse' } }

ApiResponseClinica:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { $ref: '#/ClinicaResponse' }

ApiResponseListaAuditoria: # NOVO
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { type: array, items: { $ref: '#/AuditoriaResponse' } }

ApiResponseSucesso:
  type: object
  properties:
    sucesso: { type: boolean }
    mensagem: { type: string }
```

