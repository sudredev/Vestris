# AUDITORIA EM TEMPO REAL - PLANO COMPLETO DE IMPLEMENTAÇÃO

## RESUMO EXECUTIVO

Sistema Vestris agora possui auditoria **real em tempo real** para todas as ações críticas. 
Os logs são registrados no momento exato em que ocorrem, com:
- **Enums type-safe** para ações e entidades
- **Metadados estruturados** em JSON
- **Rastreabilidade completa** por clínica
- **Isolamento de dados** respeitando segurança

---

## 1. NOVAS CLASSES CRIADAS

### ✅ `AcaoAuditoria.java` (enum)
**Localização:** `vestris-user/src/main/java/br/com/vestris/user/domain/model/`

Padroniza todas as ações possíveis no sistema:
```
PACIENTE_CRIADO, PACIENTE_EDITADO, PACIENTE_CANCELADO
ATENDIMENTO_AGENDADO, ATENDIMENTO_INICIADO, ATENDIMENTO_FINALIZADO, ATENDIMENTO_CANCELADO
PRONTUARIO_EDITADO, PRONTUARIO_SALVO
ANEXO_ADICIONADO, ANEXO_REMOVIDO
PDF_RECEITA_GERADO, PDF_MANEJO_GERADO, PDF_PRONTUARIO_GERADO
PROTOCOLO_CRIADO, PROTOCOLO_EDITADO, PROTOCOLO_REMOVIDO
```

### ✅ `EntidadeAuditoria.java` (enum)
**Localização:** `vestris-user/src/main/java/br/com/vestris/user/domain/model/`

Categoriza os tipos de entidades auditadas:
```
PACIENTE, ATENDIMENTO, PRONTUARIO, ANEXO, EXAME, PDF, RECEITA, MANEJO, PROTOCOLO, USUARIO, CLINICA, etc.
```

### ✅ `HelperAuditoria.java` (utilitário)
**Localização:** `vestris-shared/src/main/java/br/com/vestris/shared/infrastructure/helper/`

Centraliza lógica para:
- Montar metadados estruturados
- Métodos específicos: `montarMetadadosPaciente()`, `montarMetadadosPDF()`, `montarMetadadosAtendimento()`, etc.
- Serialização JSON automática

---

## 2. CLASSES MODIFICADAS

### ✅ `Auditoria.java` (modelo)
**Mudanças:**
- `String acao` → `@Enumerated(EnumType.STRING) AcaoAuditoria acao`
- `String entidade` → `@Enumerated(EnumType.STRING) EntidadeAuditoria entidade`
- `String detalhes` → `String descricaoCurta` + `String metadados` (JSONB)
- `LocalDateTime criadoEm` → `LocalDateTime dataHora`

### ✅ `ServiceAuditoria.java`
**Mudanças:**
- 3 métodos sobrecarregados agora disponíveis:
  1. `registrar(UUID, UUID, AcaoAuditoria, EntidadeAuditoria, UUID, String, Map<String, Object>)` - **NOVO**
  2. `registrar(UUID, UUID, AcaoAuditoria, EntidadeAuditoria, UUID, String)` - **NOVO**
  3. `registrar(UUID, UUID, String, String, UUID, String)` - **@Deprecated** (compatibilidade)
- Implementação robusta com ObjectMapper para serializar metadados
- Try-catch seguro para não quebrar fluxo principal

### ✅ `ApiDelegateAuditoria.java`
**Mudanças:**
- Atualizado método `converter()` para mapear:
  - `log.getAcao().getDescricao()` (enum → descrição)
  - `log.getEntidade().getDescricao()` (enum → descrição)
  - `log.getDescricaoCurta()` (novo nome do campo)

### ✅ `ServicePaciente.java`
**Mudanças:**
- ✅ Injeção: `ServiceAuditoria` + `HelperAuditoria`
- ✅ `criar()` - log PACIENTE_CRIADO
- ✅ `atualizar()` - log PACIENTE_EDITADO
- ✅ `deletar()` - log PACIENTE_CANCELADO

**Exemplo de log gerado:**
```
Ação: PACIENTE_CRIADO
Entidade: PACIENTE
Descrição: "Novo paciente criado: Felix"
Metadados: {
  "nomePaciente": "Felix",
  "especie": "Espécie ID: ...",
  "tutor": "João Silva"
}
```

### ✅ `ServiceAtendimento.java`
**Mudanças:**
- ✅ Injeção: `ServiceAuditoria` + `HelperAuditoria`
- ✅ `criar()` - log ATENDIMENTO_AGENDADO
- ✅ `atualizar()` - log PRONTUARIO_EDITADO
- ✅ `atualizarStatus()` - log dinâmico baseado no status (INICIADO, FINALIZADO, CANCELADO)
- ✅ `finalizar()` - log ATENDIMENTO_FINALIZADO

**Novo método helper:**
```java
private AcaoAuditoria mapearAcaoPorStatus(Atendimento.StatusAtendimento status)
```

**Exemplo de log:**
```
Ação: ATENDIMENTO_AGENDADO
Entidade: ATENDIMENTO
Descrição: "Atendimento agendado para Felix"
Metadados: {
  "nomePaciente": "Felix",
  "statusAtual": "AGENDADO",
  "veterinarioId": "...",
  "titulo": "Consulta Inicial",
  "dataHora": "2026-02-17T14:30:00"
}
```

### ✅ `ServiceExames.java`
**Mudanças:**
- ✅ Injeção: `ServiceAuditoria`, `ServiceUsuario`, `HelperAuditoria`
- ✅ `anexar()` - log ANEXO_ADICIONADO
- ✅ `deletar()` - log ANEXO_REMOVIDO

**Exemplo:**
```
Ação: ANEXO_ADICIONADO
Entidade: ANEXO
Descrição: "Anexo/Exame adicionado: RX_Torax.pdf"
Metadados: {
  "nomeArquivo": "RX_Torax.pdf",
  "tipoArquivo": "application/pdf",
  "paciente": "Felix",
  "observacoes": "Radiografia de tórax normal"
}
```

### ✅ `ServiceProtocolo.java`
**Mudanças:**
- ✅ Injeção: `ServiceAuditoria`, `ServiceUsuario`, `HelperAuditoria`
- ✅ `criar()` - log PROTOCOLO_CRIADO
- ✅ `atualizar()` - log PROTOCOLO_EDITADO
- ✅ `deletar()` - log PROTOCOLO_REMOVIDO

**Exemplo:**
```
Ação: PROTOCOLO_CRIADO
Entidade: PROTOCOLO
Descrição: "Protocolo criado: Tratamento de Dermatite"
Metadados: {
  "nomeProtocolo": "Tratamento de Dermatite",
  "origem": "INSTITUCIONAL",
  "autorId": "...",
  "doenca": "Dermatite Atópica",
  "totalDosagens": "3"
}
```

---

## 3. EVENTOS JÁ IMPLEMENTADOS

✅ **Paciente:**
- Criação → PACIENTE_CRIADO
- Edição → PACIENTE_EDITADO
- Deleção → PACIENTE_CANCELADO

✅ **Atendimento:**
- Agendamento → ATENDIMENTO_AGENDADO
- Edição de prontuário → PRONTUARIO_EDITADO
- Mudança de status → ATENDIMENTO_INICIADO / FINALIZADO / CANCELADO (dinâmico)
- Finalização → ATENDIMENTO_FINALIZADO

✅ **Exames/Anexos:**
- Adição → ANEXO_ADICIONADO
- Remoção → ANEXO_REMOVIDO

✅ **Protocolos:**
- Criação → PROTOCOLO_CRIADO
- Edição → PROTOCOLO_EDITADO
- Deleção → PROTOCOLO_REMOVIDO

❌ **Pendentes (fáceis de implementar):**
- PDF_RECEITA_GERADO - controller de geração de PDF
- PDF_MANEJO_GERADO - controller de geração de PDF
- PDF_PRONTUARIO_GERADO - controller de geração de PDF

---

## 4. ESTRUTURA DE DADOS NO BANCO

```sql
CREATE TABLE auditoria (
  id UUID PRIMARY KEY,
  clinica_id UUID NOT NULL,
  usuario_id UUID NOT NULL,
  acao VARCHAR(50) NOT NULL,        -- ENUM em STRING
  entidade VARCHAR(50) NOT NULL,    -- ENUM em STRING
  id_alvo UUID NOT NULL,
  descricao_curta TEXT,
  metadados JSONB,                  -- Novo: JSON com contexto
  data_hora TIMESTAMP NOT NULL
);

CREATE INDEX idx_auditoria_clinica_data 
  ON auditoria(clinica_id, data_hora DESC);
```

---

## 5. EXEMPLO DE LOG COMPLETO (JSON)

```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "clinicaId": "123e4567-e89b-12d3-a456-426614174000",
  "usuarioId": "987fcdeb-51a8-12d3-a456-426614174000",
  "acao": "ATENDIMENTO_AGENDADO",
  "entidade": "ATENDIMENTO",
  "idAlvo": "6ba7b810-9dad-11d1-80b4-00c04fd430c8",
  "descricaoCurta": "Atendimento agendado para Felix",
  "metadados": {
    "nomePaciente": "Felix",
    "statusAtual": "AGENDADO",
    "veterinarioId": "987fcdeb-51a8-12d3-a456-426614174000",
    "titulo": "Consulta Inicial",
    "dataHora": "2026-02-17T14:30:00"
  },
  "dataHora": "2026-02-17T10:15:30Z"
}
```

---

## 6. COMO USAR (EXEMPLOS DE CÓDIGO)

### Exemplo 1: Registrar ação simples
```java
servicoAuditoria.registrar(
    clinicaId,
    usuarioId,
    AcaoAuditoria.PACIENTE_CRIADO,
    EntidadeAuditoria.PACIENTE,
    pacienteId,
    "Paciente criado com sucesso"
);
```

### Exemplo 2: Com metadados estruturados
```java
var metadados = helperAuditoria.montarMetadadosPaciente(
    "Felix",
    "Gato Siamês",
    "João Silva"
);

servicoAuditoria.registrar(
    clinicaId,
    usuarioId,
    AcaoAuditoria.PACIENTE_CRIADO,
    EntidadeAuditoria.PACIENTE,
    pacienteId,
    "Novo paciente criado",
    metadados
);
```

### Exemplo 3: Metadados customizados
```java
var metadados = helperAuditoria.montarMetadados(
    "tipoPDF", "RECEITA",
    "nomePaciente", "Felix",
    "medicamentos", "3",
    "dataEmissao", LocalDate.now().toString()
);

servicoAuditoria.registrar(
    clinicaId,
    usuarioId,
    AcaoAuditoria.PDF_RECEITA_GERADO,
    EntidadeAuditoria.RECEITA,
    receituarioId,
    "PDF de receita gerado",
    metadados
);
```

---

## 7. SEGURANÇA & CONFORMIDADE

✅ **Isolamento de clínicas:**
- Cada log possui `clinicaId`
- Admin Gestor vê apenas logs de sua clínica
- Veterinários veem logs de seus pacientes

✅ **Rastreabilidade:**
- Todos os logs incluem `usuarioId` (quem fez)
- Timestamp automático (`dataHora`)
- Descrição textual + metadados

✅ **Soft delete:**
- Deletados registram como PACIENTE_CANCELADO, não deletam BD
- Protocolos em uso registram erro controlado

✅ **Error handling:**
- Try-catch em todos os registros
- Não interrompe fluxo principal se auditoria falhar
- Logs de erro no console (System.err)

---

## 8. PRÓXIMOS PASSOS

1. **PDFs:** Adicionar logs em controllers de geração:
   ```java
   @PostMapping("/receitas/{atendimentoId}/gerar-pdf")
   public ResponseEntity<...> gerarReceita(...) {
       // ... lógica de PDF ...
       servicoAuditoria.registrar(
           clinicaId, usuarioId,
           AcaoAuditoria.PDF_RECEITA_GERADO,
           EntidadeAuditoria.RECEITA,
           atendimentoId,
           "Receita gerada",
           metadadosComTipoPDF
       );
   }
   ```

2. **Vacinação:** Análogo a Atendimento
   ```java
   // ServiceVacinacao
   AcaoAuditoria.VACINACAO_APLICADA ou APLICACAO_VACINA_REGISTRADA
   ```

3. **Dashboard de Auditoria:**
   - Filtro por ação, entidade, data
   - Busca por ID do alvo
   - Export CSV/JSON

4. **Alertas:** Ações críticas (deleção, mudança de status)

---

## 9. TESTES SUGERIDOS

```java
@Test
void deveLancarAuditoriaAoCriarPaciente() {
    Paciente novo = new Paciente();
    novo.setNome("Felix");
    novo.setVeterinarioId(vetId);
    novo.setEspecieId(especieId);
    
    servicePaciente.criar(novo);
    
    List<Auditoria> logs = repositorioAuditoria.findAllByClinicaAndDateRange(
        clinicaId, LocalDateTime.now().minusMinutes(1), LocalDateTime.now()
    );
    
    assertThat(logs).isNotEmpty();
    assertThat(logs.get(0).getAcao()).isEqualTo(AcaoAuditoria.PACIENTE_CRIADO);
    assertThat(logs.get(0).getMetadados()).contains("Felix");
}
```

---

## 10. DEPENDÊNCIAS NECESSÁRIAS

Garantir que `pom.xml` possui:
```xml
<!-- Jackson para JSON -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
</dependency>

<!-- Lombok -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```

---

## CONCLUSÃO

✅ **Sistema de Auditoria completo, type-safe e robusto!**

- Enums garantem consistência
- Metadados em JSON permitem análises futuras
- HelperAuditoria reduz duplicação de código
- Isolamento por clínica respeita DROP 1
- Try-catch previne falhas em cascata
- Documentação interna completa

**Próximo:** Implementar PDFs e validar com testes de integração.

