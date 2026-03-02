## src\main\resources\swagger

### openapi.yml

```yaml
# src\main\resources\swagger\openapi.yml
openapi: 3.0.3
info:
  title: Vestris - Módulo Feedback
  description: Gestão de Sugestões e Melhorias
  version: 1.0.0
servers:
  - url: http://localhost:8080

paths:
  /api/v1/sugestoes/especies:
    $ref: './paths/sugestoes.yml#/sugestoes_especies'
  /api/v1/sugestoes/doencas:
    $ref: './paths/sugestoes.yml#/sugestoes_doencas'
  /api/v1/sugestoes/protocolos:
    $ref: './paths/sugestoes.yml#/sugestoes_protocolos'
  /api/v1/sugestoes/calculos:
    $ref: './paths/sugestoes.yml#/sugestoes_calculos'
  /api/v1/sugestoes:
    $ref: './paths/sugestoes.yml#/sugestoes_global'
  /api/v1/sugestoes/{id}/status:
    $ref: './paths/sugestoes.yml#/sugestoes_atualizar_status'

components:
  schemas:
    TipoSugestaoEnum:
      $ref: "./components/schemas.yml#/TipoSugestaoEnum"
    SugestaoRequest:
      $ref: "./components/schemas.yml#/SugestaoRequest"
    ApiResponseSugestao:
      $ref: "./components/schemas.yml#/ApiResponseSugestao"
    StatusSugestaoEnum:
      $ref: "./components/schemas.yml#/StatusSugestaoEnum"
    SugestaoResponse:
      $ref: "./components/schemas.yml#/SugestaoResponse"
    AtualizarStatusRequest:
      $ref: "./components/schemas.yml#/AtualizarStatusRequest"
    ApiResponseListaSugestao:
      $ref: "./components/schemas.yml#/ApiResponseListaSugestao"





```

