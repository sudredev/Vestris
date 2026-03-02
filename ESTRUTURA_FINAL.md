# 📁 ESTRUTURA FINAL - ARQUIVOS DE AUDITORIA

## Localização dos Arquivos

```
vestris (root)
│
├── 📄 README_AUDITORIA.md ........................ Resumo executivo
├── 📄 IMPLEMENTACAO_AUDITORIA_COMPLETA.md ....... Documentação técnica
├── 📄 SUMARIO_TECNICO_AUDITORIA.md .............. Referência de arquivos
├── 📄 GUIA_PRATICO_AUDITORIA.md ................. Exemplos e testes
├── 📄 SETUP_E_VALIDACAO.md ...................... Build e validação
│
├── vestris-shared/
│   └── src/main/java/br/com/vestris/shared/
│       └── infrastructure/
│           └── helper/
│               └── ✨ HelperAuditoria.java [NOVO] ......... Utilitário de metadados
│
├── vestris-user/
│   └── src/main/java/br/com/vestris/user/
│       ├── domain/
│       │   └── model/
│       │       ├── ✨ AcaoAuditoria.java [NOVO] ......... Enum de ações
│       │       ├── ✨ EntidadeAuditoria.java [NOVO] ..... Enum de entidades
│       │       ├── ✏️ Auditoria.java [MODIFICADO] ....... Modelo JPA
│       │       ├── Perfil.java
│       │       ├── Usuario.java
│       │       └── Clinica.java
│       ├── application/
│       │   ├── ✏️ ServiceAuditoria.java [MODIFICADO] .... Service principal
│       │   ├── ServiceAuth.java
│       │   ├── ServiceUsuario.java
│       │   └── ServiceClinica.java
│       └── interfaces/
│           └── delegate/
│               ├── ✏️ ApiDelegateAuditoria.java [MODIFICADO]
│               ├── ApiDelegateAuth.java
│               └── ApiDelegateUsuarios.java
│
├── vestris-medical-record/
│   └── src/main/java/br/com/vestris/medicalrecord/
│       ├── application/
│       │   ├── ✏️ ServicePaciente.java [MODIFICADO] ...... Logs: CRIAR, EDITAR, DELETAR
│       │   ├── ✏️ ServiceAtendimento.java [MODIFICADO] .. Logs: AGENDAR, EDITAR, FINALIZAR, STATUS
│       │   ├── ✏️ ServiceExames.java [MODIFICADO] ....... Logs: ADICIONAR, REMOVER
│       │   └── ServiceVacinacao.java
│       └── domain/
│           └── model/
│               ├── Paciente.java
│               ├── Atendimento.java
│               └── ExameAnexo.java
│
└── vestris-clinical/
    └── src/main/java/br/com/vestris/clinical/
        ├── application/
        │   ├── ✏️ ServiceProtocolo.java [MODIFICADO] .... Logs: CRIAR, EDITAR, DELETAR
        │   ├── ServiceDoenca.java
        │   └── ServiceCalculadora.java
        └── domain/
            └── model/
                ├── Protocolo.java
                ├── Dosagem.java
                └── Doenca.java
```

---

## Resumo por Arquivo

### ✨ NOVOS (3)

```
1. HelperAuditoria.java
   └─ Caminho: vestris-shared/src/main/java/.../infrastructure/helper/
   └─ Tipo: @Component Spring
   └─ Métodos: 5 (montarMetadados, montarMetadadosPaciente, etc)
   └─ Linhas: ~120

2. AcaoAuditoria.java
   └─ Caminho: vestris-user/src/main/java/.../user/domain/model/
   └─ Tipo: Enum
   └─ Valores: 12 ações
   └─ Linhas: ~45

3. EntidadeAuditoria.java
   └─ Caminho: vestris-user/src/main/java/.../user/domain/model/
   └─ Tipo: Enum
   └─ Valores: 14 entidades
   └─ Linhas: ~30
```

### ✏️ MODIFICADOS (7)

```
1. Auditoria.java
   └─ Tipo: Entity JPA
   └─ Mudanças: Enums + JSONB para metadados

2. ServiceAuditoria.java
   └─ Tipo: Service Spring
   └─ Mudanças: 3 métodos sobrecarregados + ObjectMapper

3. ApiDelegateAuditoria.java
   └─ Tipo: REST Delegate
   └─ Mudanças: converter() para enums

4. ServicePaciente.java
   └─ Tipo: Service Spring
   └─ Mudanças: 2 injeções + 3 logs

5. ServiceAtendimento.java
   └─ Tipo: Service Spring
   └─ Mudanças: 1 injeção + 5 logs + 1 método helper

6. ServiceExames.java
   └─ Tipo: Service Spring
   └─ Mudanças: 3 injeções + 2 logs

7. ServiceProtocolo.java
   └─ Tipo: Service Spring
   └─ Mudanças: 3 injeções + 3 logs
```

---

## Dependências de Injeção

```
HelperAuditoria
├─ ObjectMapper (Jackson)

ServiceAuditoria
├─ RepositorioAuditoria
├─ ObjectMapper

ServicePaciente
├─ ServiceAuditoria [NOVO]
├─ HelperAuditoria [NOVO]
├─ RepositorioPaciente
├─ ServiceEspecie
└─ ServiceUsuario

ServiceAtendimento
├─ ServiceAuditoria
├─ HelperAuditoria [NOVO]
├─ RepositorioAtendimento
├─ ServicePaciente
└─ ServiceUsuario

ServiceExames
├─ ServiceAuditoria [NOVO]
├─ ServiceUsuario [NOVO]
├─ HelperAuditoria [NOVO]
├─ RepositorioExameAnexo
└─ RepositorioAtendimento [NOVO]

ServiceProtocolo
├─ ServiceAuditoria [NOVO]
├─ ServiceUsuario [NOVO]
├─ HelperAuditoria [NOVO]
├─ RepositorioProtocolo
├─ RepositorioDoenca
├─ ServiceFarmacologia
└─ ServiceReferencia
```

---

## Fluxo de Auditoria

```
┌─────────────────┐
│  REST Controller│
│   (POST/PATCH)  │
└────────┬────────┘
         │
         ▼
    ┌─────────────────────┐
    │  Service (Business) │
    │  - Criar            │
    │  - Editar           │
    │  - Deletar          │
    └────────┬────────────┘
             │
             ├─► Validações
             │
             ├─► Salvar no BD
             │
             ▼
    ┌─────────────────────┐
    │  ServiceAuditoria   │
    │  .registrar(...)    │
    └────────┬────────────┘
             │
             ├─► Validar dados (not null)
             │
             ├─► HelperAuditoria.montarMetadados()
             │
             ├─► ObjectMapper.writeValueAsString()
             │
             ▼
    ┌─────────────────────┐
    │  Auditoria Entity   │
    │  - id               │
    │  - clinicaId        │
    │  - usuarioId        │
    │  - acao (ENUM)      │
    │  - entidade (ENUM)  │
    │  - metadados (JSON) │
    │  - dataHora         │
    └────────┬────────────┘
             │
             ▼
    ┌─────────────────────┐
    │ Auditoria.save()    │
    │ (JPA)               │
    └────────┬────────────┘
             │
             ▼
    ┌─────────────────────┐
    │ PostgreSQL (JSONB)  │
    │ users_schema        │
    │ .auditoria          │
    └─────────────────────┘
```

---

## Mapeamento de Eventos

```
PACIENTE (ServicePaciente)
├─ criar()
│  └─► PACIENTE_CRIADO
│      └─ Entidade: PACIENTE
│      └─ Metadados: nomePaciente, especie, tutor
│
├─ atualizar()
│  └─► PACIENTE_EDITADO
│      └─ Entidade: PACIENTE
│      └─ Metadados: nomePaciente, especie, tutor
│
└─ deletar()
   └─► PACIENTE_CANCELADO
       └─ Entidade: PACIENTE
       └─ Metadados: nomePaciente, especie, tutor

ATENDIMENTO (ServiceAtendimento)
├─ criar()
│  └─► ATENDIMENTO_AGENDADO
│      └─ Entidade: ATENDIMENTO
│      └─ Metadados: nomePaciente, statusAtual, veterinarioId, titulo, dataHora
│
├─ atualizar()
│  └─► PRONTUARIO_EDITADO
│      └─ Entidade: PRONTUARIO
│      └─ Metadados: nomePaciente, statusAtual, veterinarioId
│
├─ atualizarStatus()
│  ├─► ATENDIMENTO_INICIADO (EM_ANDAMENTO)
│  ├─► ATENDIMENTO_FINALIZADO (REALIZADO)
│  ├─► ATENDIMENTO_CANCELADO (CANCELADO)
│  └─► ATENDIMENTO_AGENDADO (AGENDADO)
│      └─ Entidade: ATENDIMENTO
│      └─ Metadados: nomePaciente, statusAtual, statusAnterior
│
└─ finalizar()
   └─► ATENDIMENTO_FINALIZADO
       └─ Entidade: ATENDIMENTO
       └─ Metadados: nomePaciente, statusAtual, diagnostico, condutaClinica

EXAMES (ServiceExames)
├─ anexar()
│  └─► ANEXO_ADICIONADO
│      └─ Entidade: ANEXO
│      └─ Metadados: nomeArquivo, tipoArquivo, paciente, observacoes
│
└─ deletar()
   └─► ANEXO_REMOVIDO
       └─ Entidade: ANEXO
       └─ Metadados: nomeArquivo, tipoArquivo, paciente

PROTOCOLO (ServiceProtocolo)
├─ criar()
│  └─► PROTOCOLO_CRIADO
│      └─ Entidade: PROTOCOLO
│      └─ Metadados: nomeProtocolo, origem, autorId, doenca, totalDosagens
│
├─ atualizar()
│  └─► PROTOCOLO_EDITADO
│      └─ Entidade: PROTOCOLO
│      └─ Metadados: nomeProtocolo, origem, autorId, totalDosagens
│
└─ deletar()
   └─► PROTOCOLO_REMOVIDO
       └─ Entidade: PROTOCOLO
       └─ Metadados: nomeProtocolo, origem, autorId
```

---

## Estrutura no Banco de Dados

```
Schema: users_schema

Table: auditoria
├─ id (UUID) ........................ Primary Key
├─ clinica_id (UUID) ................ Foreign Key [NÃO NULL]
├─ usuario_id (UUID) ................ Foreign Key [NÃO NULL]
├─ acao (VARCHAR(50)) ............... ENUM em STRING [NÃO NULL]
│  ├─ PACIENTE_CRIADO
│  ├─ PACIENTE_EDITADO
│  ├─ PACIENTE_CANCELADO
│  ├─ ATENDIMENTO_AGENDADO
│  ├─ ATENDIMENTO_INICIADO
│  ├─ ATENDIMENTO_FINALIZADO
│  ├─ ATENDIMENTO_CANCELADO
│  ├─ PRONTUARIO_EDITADO
│  ├─ ANEXO_ADICIONADO
│  ├─ ANEXO_REMOVIDO
│  ├─ PROTOCOLO_CRIADO
│  ├─ PROTOCOLO_EDITADO
│  ├─ PROTOCOLO_REMOVIDO
│  └─ ... (outros)
├─ entidade (VARCHAR(50)) ........... ENUM em STRING [NÃO NULL]
│  ├─ PACIENTE
│  ├─ ATENDIMENTO
│  ├─ PRONTUARIO
│  ├─ ANEXO
│  ├─ PROTOCOLO
│  └─ ... (outros)
├─ id_alvo (UUID) ................... Foreign Key [NÃO NULL]
├─ descricao_curta (TEXT) ........... Descrição da ação [NULLABLE]
├─ metadados (JSONB) ................ Dados estruturados [NULLABLE]
│  └─ Exemplo: {"nomePaciente":"Felix","especie":"Gato"}
└─ data_hora (TIMESTAMP) ............ Timestamp automático [NÃO NULL]

Índices:
├─ PK: auditoria_pkey (id)
├─ idx_auditoria_clinica_data (clinica_id, data_hora DESC)
├─ idx_auditoria_usuario (usuario_id)
└─ idx_auditoria_acao (acao)
```

---

## Imports Adicionados

### Em ServicePaciente
```java
import br.com.vestris.user.application.ServiceAuditoria;
import br.com.vestris.shared.infrastructure.helper.HelperAuditoria;
import br.com.vestris.user.domain.model.AcaoAuditoria;
import br.com.vestris.user.domain.model.EntidadeAuditoria;
```

### Em ServiceAtendimento
```java
import br.com.vestris.user.domain.model.AcaoAuditoria;
import br.com.vestris.user.domain.model.EntidadeAuditoria;
import br.com.vestris.shared.infrastructure.helper.HelperAuditoria;
```

### Em ServiceExames
```java
import br.com.vestris.user.application.ServiceAuditoria;
import br.com.vestris.user.application.ServiceUsuario;
import br.com.vestris.shared.infrastructure.helper.HelperAuditoria;
import br.com.vestris.user.domain.model.AcaoAuditoria;
import br.com.vestris.user.domain.model.EntidadeAuditoria;
import br.com.vestris.medicalrecord.domain.repository.RepositorioAtendimento;
```

### Em ServiceProtocolo
```java
import br.com.vestris.user.application.ServiceAuditoria;
import br.com.vestris.user.application.ServiceUsuario;
import br.com.vestris.shared.infrastructure.helper.HelperAuditoria;
import br.com.vestris.user.domain.model.AcaoAuditoria;
import br.com.vestris.user.domain.model.EntidadeAuditoria;
```

### Em Auditoria.java
```java
import br.com.vestris.user.domain.model.AcaoAuditoria;
import br.com.vestris.user.domain.model.EntidadeAuditoria;
```

### Em HelperAuditoria.java
```java
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
```

### Em ServiceAuditoria.java
```java
import br.com.vestris.user.domain.model.AcaoAuditoria;
import br.com.vestris.user.domain.model.EntidadeAuditoria;
import com.fasterxml.jackson.databind.ObjectMapper;
```

---

## Documentação Gerada (5 arquivos)

```
C:\Users\erick\vestris\
├─ 📘 README_AUDITORIA.md ........................ (Resumo executivo)
├─ 📗 IMPLEMENTACAO_AUDITORIA_COMPLETA.md ....... (Documentação técnica)
├─ 📙 SUMARIO_TECNICO_AUDITORIA.md .............. (Referência de arquivos)
├─ 📕 GUIA_PRATICO_AUDITORIA.md ................. (Exemplos práticos)
└─ 📓 SETUP_E_VALIDACAO.md ...................... (Build e validação)
```

---

## Próximos Passos (Pendentes)

```
❌ PDF_RECEITA_GERADO (controller de PDF)
❌ PDF_MANEJO_GERADO (controller de PDF)
❌ PDF_PRONTUARIO_GERADO (controller de PDF)
❌ VACINACAO (ServiceVacinacao)
❌ Testes de integração
❌ Dashboard frontend
```

---

## Linhas de Código Adicionadas

```
Arquivo                          Tipo        Linhas      Status
─────────────────────────────────────────────────────────────────
AcaoAuditoria.java              [NOVO]      ~45         ✅
EntidadeAuditoria.java          [NOVO]      ~30         ✅
HelperAuditoria.java            [NOVO]      ~120        ✅
Auditoria.java                  [MODIF]     ~10         ✅
ServiceAuditoria.java           [MODIF]     ~60         ✅
ApiDelegateAuditoria.java       [MODIF]     ~5          ✅
ServicePaciente.java            [MODIF]     ~50         ✅
ServiceAtendimento.java         [MODIF]     ~80         ✅
ServiceExames.java              [MODIF]     ~50         ✅
ServiceProtocolo.java           [MODIF]     ~60         ✅
─────────────────────────────────────────────────────────────────
TOTAL                                        ~510        ✅
```

---

## Status Final

```
✅ IMPLEMENTAÇÃO: 100% Completa
✅ DOCUMENTAÇÃO: 100% Completa
✅ TESTES: Pendentes (próximo sprint)
✅ PDFS: Pendentes (próximo sprint)
✅ VACINAÇÃO: Pendentes (próximo sprint)
```

---

**Data:** 17 de fevereiro de 2026
**Versão:** 1.0 (Inicial)
**Autor:** Sistema Vestris
**Status:** 🟢 PRONTO PARA VALIDAÇÃO

