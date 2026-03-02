## src\main\resources\swagger

### openapi.yml

```yaml
# src\main\resources\swagger\openapi.yml
openapi: 3.0.3
info:
  title: Vestris - Módulo Usuários
  description: Autenticação e Gestão de Contas
  version: 1.0.0
servers:
  - url: http://localhost:8080
    description: Servidor Local

paths:
  /api/v1/auth/registro:
    $ref: "./paths/registro.yml"
  /api/v1/auth/login:
    $ref: "./paths/login.yml"
  /api/v1/usuarios:
    $ref: './paths/usuarios.yml#/usuarios_colecao'
  /api/v1/usuarios/{id}:
    $ref: './paths/usuarios.yml#/usuarios_item'
  /api/v1/minha-clinica:
    $ref: './paths/clinica.yml#/clinica_geral'
  /api/v1/minha-clinica/equipe:
    $ref: './paths/clinica.yml#/clinica_equipe'
  /api/v1/admin/impersonate:
    $ref: './paths/admin.yml#/impersonate'

  # NOVO
  /api/v1/auditoria:
    $ref: './paths/auditoria.yml#/auditoria_colecao'
  /api/v1/auditoria/evento:
    $ref: './paths/auditoria.yml#/auditoria_evento'

components:
  schemas:
    RegistroRequest:
      $ref: "./components/schemas.yml#/RegistroRequest"
    LoginRequest:
      $ref: "./components/schemas.yml#/LoginRequest"
    UsuarioResponse:
      $ref: "./components/schemas.yml#/UsuarioResponse"
    TokenResponse:
      $ref: "./components/schemas.yml#/TokenResponse"
    ApiResponseUsuario:
      $ref: "./components/schemas.yml#/ApiResponseUsuario"
    ApiResponseToken:
      $ref: "./components/schemas.yml#/ApiResponseToken"

    # NOVOS E ATUALIZADOS
    EventoAuditoriaRequest:
      $ref: "./components/schemas.yml#/EventoAuditoriaRequest"
    AuditoriaResponse:
      $ref: "./components/schemas.yml#/AuditoriaResponse"
    ApiResponseListaAuditoria:
      $ref: "./components/schemas.yml#/ApiResponseListaAuditoria"
    UsuarioResponsePerfilEnum:
      $ref: "./components/schemas.yml#/UsuarioResponsePerfilEnum"

```

