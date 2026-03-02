# 🔧 SETUP & VALIDAÇÃO - AUDITORIA VESTRIS

## 1. VALIDAR COMPILAÇÃO

### Maven Clean + Build
```bash
cd C:\Users\erick\vestris

# Limpar builds anteriores
mvn clean

# Compilar todos os módulos
mvn compile

# Se houver erros de imports, adicione estas dependências ao pom.xml
```

### Verificar Erros de Compilação
```bash
# Compilar apenas os módulos modificados
mvn -pl vestris-user compile
mvn -pl vestris-medical-record compile
mvn -pl vestris-clinical compile
mvn -pl vestris-shared compile
```

---

## 2. DEPENDÊNCIAS VERIFICADAS

### Jackson (ObjectMapper)
```xml
<!-- Já incluso em spring-boot-starter-web -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <!-- Versão gerenciada pelo Spring Boot -->
</dependency>
```

### Lombok (já em uso)
```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```

### JPA (já em uso)
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

---

## 3. TESTAR COMPILAÇÃO POR MÓDULO

### Vestris User (Auditoria)
```bash
mvn -pl vestris-user clean compile

# Validar enums
javap -classpath target/classes br.com.vestris.user.domain.model.AcaoAuditoria
javap -classpath target/classes br.com.vestris.user.domain.model.EntidadeAuditoria
```

### Vestris Shared (Helper)
```bash
mvn -pl vestris-shared clean compile

# Validar helper
javap -classpath target/classes br.com.vestris.shared.infrastructure.helper.HelperAuditoria
```

### Vestris Medical Record (Services)
```bash
mvn -pl vestris-medical-record clean compile
```

### Vestris Clinical (Protocolo)
```bash
mvn -pl vestris-clinical clean compile
```

---

## 4. EXECUTAR TESTES (SE EXISTIREM)

```bash
# Todos os testes
mvn test

# Apenas um módulo
mvn -pl vestris-user test

# Apenas uma classe de teste
mvn -pl vestris-user test -Dtest=AuditoriaTest
```

---

## 5. BUILD FINAL

### Compilar todos os JAR's
```bash
mvn clean package -DskipTests

# Resultado esperado:
# vestris-user/target/vestris-user-0.0.1-SNAPSHOT.jar
# vestris-medical-record/target/vestris-medical-record-0.0.1-SNAPSHOT.jar
# vestris-clinical/target/vestris-clinical-0.0.1-SNAPSHOT.jar
# vestris-shared/target/vestris-shared-0.0.1-SNAPSHOT.jar
```

### Build da Aplicação Portal (agregador)
```bash
mvn -pl vestris-portal clean package -DskipTests
```

---

## 6. EXECUTAR APLICAÇÃO

### Via Maven
```bash
# Iniciar todos os serviços
mvn spring-boot:run

# Ou iniciar módulo específico
mvn -pl vestris-portal spring-boot:run
```

### Via Docker Compose
```bash
# Construir imagens
docker-compose build

# Subir containers
docker-compose up -d

# Verificar logs
docker-compose logs -f

# Parar
docker-compose down
```

### Via JAR Compilado
```bash
java -jar vestris-portal/target/vestris-portal-0.0.1-SNAPSHOT.jar
```

---

## 7. TESTAR AUDITORIA MANUALMENTE

### 1. Criar Paciente
```bash
curl -X POST http://localhost:8080/api/pacientes \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer {TOKEN}" \
  -d '{
    "nome": "Felix Teste",
    "veterinarioId": "550e8400-e29b-41d4-a716-446655440000",
    "especieId": "123e4567-e89b-12d3-a456-426614174001",
    "dadosTutor": "João Silva",
    "sexo": "Macho",
    "pesoAtualGramas": 5000
  }'
```

**Resultado esperado:**
```json
{
  "id": "6ba7b810-9dad-11d1-80b4-00c04fd430c8",
  "nome": "Felix Teste",
  "veterinarioId": "550e8400..."
}
```

### 2. Verificar Log Criado
```bash
curl -X GET "http://localhost:8080/api/auditoria/logs?clinicaId=550e8400-e29b-41d4-a716-446655440000" \
  -H "Authorization: Bearer {TOKEN}"
```

**Resultado esperado:**
```json
{
  "sucesso": true,
  "dados": [
    {
      "id": "550e8400...",
      "clinicaId": "550e8400...",
      "usuarioId": "550e8400...",
      "acao": "Paciente criado",
      "entidade": "Paciente",
      "idAlvo": "6ba7b810...",
      "detalhes": "Novo paciente criado: Felix Teste",
      "criadoEm": "2026-02-17T10:15:30Z"
    }
  ]
}
```

### 3. Agendar Atendimento
```bash
curl -X POST http://localhost:8080/api/atendimentos \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer {TOKEN}" \
  -d '{
    "pacienteId": "6ba7b810-9dad-11d1-80b4-00c04fd430c8",
    "veterinarioId": "550e8400-e29b-41d4-a716-446655440000",
    "titulo": "Consulta Inicial",
    "dataHora": "2026-02-20T14:30:00"
  }'
```

### 4. Verificar Novo Log
```bash
curl -X GET "http://localhost:8080/api/auditoria/logs?clinicaId=..." \
  -H "Authorization: Bearer {TOKEN}"
```

---

## 8. VALIDAR NO BANCO DE DADOS

### Conectar ao PostgreSQL
```bash
# Via psql
psql -h localhost -U vestris_user -d vestris_db

# Via DBeaver
# Host: localhost
# Port: 5432
# Database: vestris_db
# User: vestris_user
# Password: {password}
```

### Consultas de Validação
```sql
-- Verificar tabela existe
\dt users_schema.auditoria;

-- Ver todos os logs
SELECT * FROM users_schema.auditoria ORDER BY data_hora DESC;

-- Ver logs por ação
SELECT acao, COUNT(*) as total FROM users_schema.auditoria GROUP BY acao;

-- Ver logs por clínica
SELECT clinica_id, COUNT(*) as total FROM users_schema.auditoria 
GROUP BY clinica_id ORDER BY total DESC;

-- Ver metadados (JSON)
SELECT acao, metadados FROM users_schema.auditoria LIMIT 5;

-- Verificar que campos estão como enum
SELECT data_type FROM information_schema.columns 
WHERE table_schema='users_schema' AND table_name='auditoria';
```

---

## 9. TROUBLESHOOTING

### Erro: "ObjectMapper não encontrado"
```bash
# Solução: Adicionar dependência
# Mas já vem com Spring Boot, então verificar imports

# Validar import correto:
import com.fasterxml.jackson.databind.ObjectMapper;
```

### Erro: "AcaoAuditoria não compilado"
```bash
# Solução: Limpar cache Maven
mvn clean compile

# Verificar arquivo foi criado:
ls -la vestris-user/src/main/java/br/com/vestris/user/domain/model/AcaoAuditoria.java
```

### Log não aparece no banco
```bash
# Verificar:
1. usuario.getClinica() não é null
2. Método registrar() foi chamado
3. Try-catch não capturou silenciosamente
4. Ver System.err nos logs da aplicação

# Debug:
mvn clean compile
mvn spring-boot:run 2>&1 | grep -i auditoria
```

### Metadados não salvo como JSON
```sql
-- Verificar tipo de coluna
SELECT column_name, data_type FROM information_schema.columns 
WHERE table_schema='users_schema' AND table_name='auditoria' 
AND column_name='metadados';

-- Deve retornar: jsonb ou json
```

---

## 10. HEALTH CHECK

### Verificar se Auditoria está Funcionando

```bash
#!/bin/bash

echo "=== Health Check Auditoria ==="

# 1. Verificar se aplicação está rodando
echo "1. Verificando aplicação..."
curl -s http://localhost:8080/actuator/health | jq .

# 2. Verificar banco
echo "2. Verificando banco..."
psql -h localhost -U vestris_user -d vestris_db -c "SELECT COUNT(*) FROM users_schema.auditoria;"

# 3. Contar logs por ação
echo "3. Contando logs por ação..."
psql -h localhost -U vestris_user -d vestris_db -c \
  "SELECT acao, COUNT(*) as total FROM users_schema.auditoria GROUP BY acao;"

# 4. Verificar último log
echo "4. Último log criado..."
psql -h localhost -U vestris_user -d vestris_db -c \
  "SELECT id, acao, descricao_curta, data_hora FROM users_schema.auditoria ORDER BY data_hora DESC LIMIT 1;"

echo "=== Health Check Completo ==="
```

---

## 11. PERFORMANCE

### Verificar Índices
```sql
-- Criar índices para melhor performance
CREATE INDEX IF NOT EXISTS idx_auditoria_clinica_data 
  ON users_schema.auditoria(clinica_id, data_hora DESC);

CREATE INDEX IF NOT EXISTS idx_auditoria_usuario 
  ON users_schema.auditoria(usuario_id);

CREATE INDEX IF NOT EXISTS idx_auditoria_acao 
  ON users_schema.auditoria(acao);

-- Verificar índices criados
\d users_schema.auditoria;
```

### Query Performance
```sql
-- Explicar query lenta
EXPLAIN ANALYZE 
SELECT * FROM users_schema.auditoria 
WHERE clinica_id = 'uuid' 
AND data_hora >= NOW() - INTERVAL '30 days';
```

---

## 12. BACKUP

### Backup da Tabela de Auditoria
```bash
# Via SQL
pg_dump -h localhost -U vestris_user -d vestris_db -t users_schema.auditoria > auditoria_backup.sql

# Via JSON
psql -h localhost -U vestris_user -d vestris_db -c \
  "COPY (SELECT row_to_json(t) FROM users_schema.auditoria t) TO STDOUT;" > auditoria_backup.jsonl
```

### Restaurar
```bash
# Via SQL
psql -h localhost -U vestris_user -d vestris_db < auditoria_backup.sql

# Via JSON
psql -h localhost -U vestris_user -d vestris_db -c \
  "COPY users_schema.auditoria FROM STDIN WITH (FORMAT json);" < auditoria_backup.jsonl
```

---

## 13. LIMPEZA (SE NECESSÁRIO)

⚠️ **CUIDADO: Estas operações são irreversíveis!**

```bash
# Deletar todos os logs (não recomendado)
psql -h localhost -U vestris_user -d vestris_db -c \
  "TRUNCATE TABLE users_schema.auditoria;"

# Deletar logs antigos (ex: > 30 dias)
psql -h localhost -U vestris_user -d vestris_db -c \
  "DELETE FROM users_schema.auditoria WHERE data_hora < NOW() - INTERVAL '30 days';"
```

---

## 14. CHECKLIST DE VALIDAÇÃO

- [ ] Maven compile sem erros
- [ ] Enums criados e compilados
- [ ] HelperAuditoria compilado
- [ ] Services injetados com sucesso
- [ ] Aplicação inicia sem erros
- [ ] Paciente criado com sucesso
- [ ] Log aparece no banco
- [ ] Metadados está como JSON válido
- [ ] Query /api/auditoria/logs retorna logs
- [ ] Isolamento por clínica funciona
- [ ] Índices criados no banco

---

## 15. PRÓXIMOS PASSOS PÓS-VALIDAÇÃO

1. **Implementar PDFs** (3 eventos)
2. **Implementar Vacinação** (2 eventos)
3. **Criar testes** (unitários + integração)
4. **Gerar documentação Swagger**
5. **Criar dashboard** (frontend)
6. **Deploy em staging**
7. **Testes de carga**
8. **Deploy em produção**

---

## RESUMO DE COMANDOS

```bash
# Build completo
mvn clean package -DskipTests

# Testar compilação
mvn compile

# Rodar aplicação
mvn spring-boot:run

# Testar paciente
curl -X POST http://localhost:8080/api/pacientes ...

# Ver logs
curl -X GET http://localhost:8080/api/auditoria/logs?clinicaId=...

# Verificar banco
psql -h localhost -U vestris_user -d vestris_db -c "SELECT COUNT(*) FROM users_schema.auditoria;"
```

---

**Status:** ✅ Pronto para validação
**Data:** 17 de fevereiro de 2026

