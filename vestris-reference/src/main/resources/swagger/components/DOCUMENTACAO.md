## src\main\resources\swagger\components

### schemas.yml

```yaml
# src\main\resources\swagger\components\schemas.yml
ReferenciaRequest:
  type: object
  required: [titulo, autores, ano]
  properties:
    titulo:
      type: string
      example: "Exotic Animal Formulary"
    autores:
      type: string
      example: "James W. Carpenter"
    ano:
      type: integer
      example: 2018
    fonte:
      type: string
      description: "Nome do Jornal, Editora ou Revista"
      example: "Elsevier"
    url:
      type: string
      description: "Link para o DOI ou PDF (opcional)"

ReferenciaResponse:
  type: object
  properties:
    id:
      type: string
      format: uuid
    titulo:
      type: string
    autores:
      type: string
    ano:
      type: integer
    fonte:
      type: string
    url:
      type: string
    criadoEm:
      type: string
      format: date-time

# Wrappers
ApiResponseReferencia:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { $ref: '#/ReferenciaResponse' }

ApiResponseListaReferencia:
  type: object
  properties:
    sucesso: { type: boolean }
    dados:
      type: array
      items: { $ref: '#/ReferenciaResponse' }
```

