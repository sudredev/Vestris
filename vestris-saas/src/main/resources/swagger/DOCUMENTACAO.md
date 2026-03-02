## src\main\resources\swagger

### openapi.yml

```yaml
# src\main\resources\swagger\openapi.yml
openapi: 3.0.3
info:
  title: Vestris - Módulo SaaS
  description: Gestão de Planos, Assinaturas e Limites
  version: 1.0.0
servers:
  - url: http://localhost:8080

paths:
  # --- PLANOS ---
  /api/v1/saas/planos:
    $ref: './paths/planos.yml#/planos_colecao'

  /api/v1/saas/planos/{id}:
    $ref: './paths/planos.yml#/planos_item'

  # --- ASSINATURAS ---
  /api/v1/saas/minha-assinatura:
    $ref: './paths/assinaturas.yml#/assinatura_atual'

  /api/v1/saas/assinar:
    $ref: './paths/assinaturas.yml#/assinar_plano'

  /api/v1/public/cadastro-saas:
    $ref: './paths/auth-public.yml#/cadastro_saas'


components:
  schemas:
    # Requests
    PlanoRequest:
      $ref: "./components/schemas.yml#/PlanoRequest"
    AssinarPlanoRequest:
      $ref: "./components/schemas.yml#/AssinarPlanoRequest"

    # Responses
    PlanoResponse:
      $ref: "./components/schemas.yml#/PlanoResponse"
    AssinaturaResponse:
      $ref: "./components/schemas.yml#/AssinaturaResponse"

    # Enums
    StatusAssinaturaEnum:
      $ref: "./components/schemas.yml#/StatusAssinaturaEnum"
    TipoFaturamentoEnum:
      $ref: "./components/schemas.yml#/TipoFaturamentoEnum"

    CadastroSaasRequest:
      $ref: "./components//schemas.yml#/CadastroSaasRequest"
    ApiResponseToken:
      $ref: "./components/schemas.yml#/ApiResponseToken"
    TokenResponse:
      $ref: "./components/schemas.yml#/TokenResponse"
    # Wrappers
    ApiResponsePlano:
      $ref: "./components/schemas.yml#/ApiResponsePlano"
    ApiResponseListaPlano:
      $ref: "./components/schemas.yml#/ApiResponseListaPlano"
    ApiResponseAssinatura:
      $ref: "./components/schemas.yml#/ApiResponseAssinatura"
```

