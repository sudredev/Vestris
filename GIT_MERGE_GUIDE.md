# 🔀 GIT MERGE GUIDE - AUDITORIA VESTRIS

## Resumo das Mudanças para Git

```bash
# Arquivos criados (3)
A vestris-user/src/main/java/br/com/vestris/user/domain/model/AcaoAuditoria.java
A vestris-user/src/main/java/br/com/vestris/user/domain/model/EntidadeAuditoria.java
A vestris-shared/src/main/java/br/com/vestris/shared/infrastructure/helper/HelperAuditoria.java

# Arquivos modificados (7)
M vestris-user/src/main/java/br/com/vestris/user/domain/model/Auditoria.java
M vestris-user/src/main/java/br/com/vestris/user/application/ServiceAuditoria.java
M vestris-user/src/main/java/br/com/vestris/user/interfaces/delegate/ApiDelegateAuditoria.java
M vestris-medical-record/src/main/java/br/com/vestris/medicalrecord/application/ServicePaciente.java
M vestris-medical-record/src/main/java/br/com/vestris/medicalrecord/application/ServiceAtendimento.java
M vestris-medical-record/src/main/java/br/com/vestris/medicalrecord/application/ServiceExames.java
M vestris-clinical/src/main/java/br/com/vestris/clinical/application/ServiceProtocolo.java

# Documentação (7)
A README_AUDITORIA.md
A IMPLEMENTACAO_AUDITORIA_COMPLETA.md
A SUMARIO_TECNICO_AUDITORIA.md
A GUIA_PRATICO_AUDITORIA.md
A SETUP_E_VALIDACAO.md
A ESTRUTURA_FINAL.md
A INDICE.md
A VISUAL_SUMMARY.md
```

---

## Preparar Feature Branch

```bash
# 1. Criar branch
git checkout -b feature/auditoria-tempo-real

# 2. Adicionar arquivos
git add -A

# 3. Commit
git commit -m "feat: implementação completa de auditoria em tempo real

- Criação de AcaoAuditoria enum com 12+ ações
- Criação de EntidadeAuditoria enum com 14 entidades
- Criação de HelperAuditoria para metadados estruturados
- Atualização de Auditoria.java com enums + JSONB
- Aprimoramento de ServiceAuditoria com 3 métodos sobrecarregados
- Atualização de ApiDelegateAuditoria para converter enums
- Instrumentação de ServicePaciente (3 logs)
- Instrumentação de ServiceAtendimento (5 logs)
- Instrumentação de ServiceExames (2 logs)
- Instrumentação de ServiceProtocolo (3 logs)
- Documentação completa (6 arquivos .md)

Total: 10 arquivos modificados/criados, ~510 LOC"

# 4. Push
git push origin feature/auditoria-tempo-real
```

---

## Merge para Main

```bash
# 1. Atualizar main
git checkout main
git pull origin main

# 2. Merge da feature
git merge feature/auditoria-tempo-real

# 3. Resolver conflitos (se houver)
# Neste caso, provavelmente não há conflitos

# 4. Push
git push origin main

# 5. Deletar branch
git branch -d feature/auditoria-tempo-real
git push origin --delete feature/auditoria-tempo-real
```

---

## Verificação Pré-Merge

```bash
# Verificar status
git status

# Verificar diff completo
git diff main..feature/auditoria-tempo-real

# Verificar apenas arquivos modificados
git diff --name-status main..feature/auditoria-tempo-real

# Verificar logs
git log main..feature/auditoria-tempo-real --oneline
```

---

## Verificação Pós-Merge

```bash
# 1. Compilar
mvn clean compile

# 2. Rodar testes
mvn test

# 3. Build completo
mvn clean package -DskipTests

# 4. Verificar JAR's
ls -lh */target/*.jar

# 5. Validar estrutura
mvn project-info-reports:dependencies
```

---

## Se Houver Conflitos

```bash
# 1. Ver conflitos
git status

# 2. Resolver manualmente
# Procure por: <<<<<<< HEAD ... ======= ... >>>>>>>

# 3. Adicionar resoluções
git add arquivo_conflitado.java

# 4. Completar merge
git commit -m "resolve: merge conflicts in auditoria implementation"

# 5. Push
git push origin main
```

---

## Rollback (Se Necessário)

```bash
# Opção 1: Desfazer último commit (se ainda local)
git reset --soft HEAD~1

# Opção 2: Reverter merge (se já em produção)
git revert -m 1 <commit-hash>

# Opção 3: Hard reset (CUIDADO!)
git reset --hard HEAD~1
```

---

## Etapas de Validação Antes de Fazer Merge

### 1. Build Local
```bash
mvn clean package -DskipTests
```
Resultado esperado: ✅ BUILD SUCCESS

### 2. Testes
```bash
mvn test
```
Resultado esperado: ✅ ALL TESTS PASSED

### 3. Verificar Imports
```bash
grep -r "import.*AcaoAuditoria" vestris-*/src/main/java
grep -r "import.*EntidadeAuditoria" vestris-*/src/main/java
grep -r "import.*HelperAuditoria" vestris-*/src/main/java
```
Resultado esperado: ✅ Imports corretos em todos os services

### 4. Validar Enums
```bash
javap -classpath vestris-user/target/classes br.com.vestris.user.domain.model.AcaoAuditoria
javap -classpath vestris-user/target/classes br.com.vestris.user.domain.model.EntidadeAuditoria
```
Resultado esperado: ✅ Enums compilados

### 5. Verificar Banco
```bash
psql -h localhost -U vestris_user -d vestris_db -c "\d users_schema.auditoria"
```
Resultado esperado: ✅ Tabela com campos corretos (acao e entidade como VARCHAR)

---

## Checklist Final

```
ANTES DO MERGE
──────────────────────────────────
[ ] Todos os arquivos criados
[ ] Todos os arquivos modificados
[ ] Sem arquivos deletados acidentalmente
[ ] Sem conflitos aparentes
[ ] Build passa sem erros
[ ] Testes passam
[ ] Documentação completada
[ ] Code review aprovado

DEPOIS DO MERGE
──────────────────────────────────
[ ] Branch deletado
[ ] Compilação em main OK
[ ] Tag de versão criada (opcional)
[ ] Build pipeline acionado
[ ] Notificação do time enviada
[ ] Documentação atualizada (wiki, etc)
[ ] Deploy marcado para próxima janela
```

---

## Exemplo Completo de Merge

```bash
# 1. Status inicial
$ git status
On branch feature/auditoria-tempo-real
nothing to commit, working tree clean

# 2. Preparar
$ git checkout main
$ git pull origin main
Already up to date.

# 3. Merge
$ git merge feature/auditoria-tempo-real
Updating abc1234..def5678
Fast-forward
 vestris-user/src/main/java/.../AcaoAuditoria.java       |  45 ++++++
 vestris-user/src/main/java/.../EntidadeAuditoria.java   |  30 ++++
 vestris-shared/src/main/java/.../HelperAuditoria.java   | 120 +++++++++++++++
 vestris-user/src/main/java/.../Auditoria.java           |  10 +-
 vestris-user/src/main/java/.../ServiceAuditoria.java    |  60 ++++++
 vestris-user/src/main/java/.../ApiDelegateAuditoria.java|   5 +-
 vestris-medical-record/src/main/java/.../ServicePaciente.java      |  50 ++++-
 vestris-medical-record/src/main/java/.../ServiceAtendimento.java   |  80 +++++++++
 vestris-medical-record/src/main/java/.../ServiceExames.java        |  50 ++++-
 vestris-clinical/src/main/java/.../ServiceProtocolo.java   |  60 +++++-
 README_AUDITORIA.md                                     |  80 ++++++++
 IMPLEMENTACAO_AUDITORIA_COMPLETA.md                     | 150 +++++++++++++++
 SUMARIO_TECNICO_AUDITORIA.md                            | 120 +++++++++++++
 GUIA_PRATICO_AUDITORIA.md                               | 200 ++++++++++++++++++++
 SETUP_E_VALIDACAO.md                                    | 160 +++++++++++++++
 ESTRUTURA_FINAL.md                                      | 130 ++++++++++++++
 INDICE.md                                               |  80 ++++++++
 VISUAL_SUMMARY.md                                       | 120 +++++++++++
 17 files changed, 1300 insertions(+)
 create mode 100644 ...

# 4. Validar
$ mvn clean compile
[INFO] BUILD SUCCESS

# 5. Push
$ git push origin main
Total 0 (delta 0), reused 0 (delta 0)
To github.com:vestris/backend.git
   abc1234..def5678  main -> main

# 6. Limpar
$ git branch -d feature/auditoria-tempo-real
$ git push origin --delete feature/auditoria-tempo-real
```

---

## Commits Individuais (Se Preferir Squash)

Se quiser fazer squash antes de merge:

```bash
# 1. Rebase interativo
git rebase -i main

# 2. Editor abre - marcar squash/fixup para consolidar
# pick abc1234 feat: create AcaoAuditoria
# squash def5678 feat: create EntidadeAuditoria
# squash ghi9012 feat: create HelperAuditoria
# ...

# 3. Salvar e confirmar

# 4. Force push (apenas em feature branch!)
git push origin feature/auditoria-tempo-real -f

# 5. Fazer merge normalmente
git checkout main
git merge feature/auditoria-tempo-real
```

---

## Configuração de Merge

```bash
# Estratégia de merge (se desejar mudar)
git merge --ff-only feature/auditoria-tempo-real  # Fast-forward only
git merge --no-ff feature/auditoria-tempo-real    # Sempre criar merge commit

# Verificar estratégia padrão
git config --get merge.ff
```

---

## Release Tag

Após merge bem-sucedido:

```bash
# 1. Criar tag
git tag -a v1.0.0-auditoria -m "Release: Auditoria em tempo real"

# 2. Visualizar
git tag -l
git show v1.0.0-auditoria

# 3. Push tag
git push origin v1.0.0-auditoria

# 4. Criar release no GitHub (opcional)
# Ir para: github.com/vestris/backend/releases
# Selecionar tag e criar release note
```

---

## Revertendo Merge (Se Necessário)

```bash
# 1. Ver histórico
git log --oneline | head -5

# 2. Revert do merge
git revert -m 1 <merge-commit-hash>

# 3. Resolver conflitos (se houver)
git status
# ... resolver ...
git add .
git commit

# 4. Push
git push origin main
```

---

## Sincronizar Branches

```bash
# Manter feature branch atualizado
git checkout feature/auditoria-tempo-real
git fetch origin
git rebase origin/main

# Se houver conflitos
git status
# ... resolver ...
git add .
git rebase --continue
git push origin feature/auditoria-tempo-real -f
```

---

## Dicas Extras

```bash
# Ver diferença entre branches
git diff main feature/auditoria-tempo-real

# Copiar arquivo de outro branch
git checkout feature/auditoria-tempo-real -- path/to/file.java

# Ver quem criou cada linha (blame)
git blame vestris-user/src/main/java/.../ServiceAuditoria.java

# Histório de um arquivo
git log -p vestris-user/src/main/java/.../Auditoria.java

# Procurar por commit
git log --grep="auditoria" --oneline
```

---

## Checklist Git Final

```
✅ Branch criado com nome apropriado
✅ Commits com mensagens descritivas
✅ Sem commits desnecessários
✅ Sem arquivos não rastreados
✅ Todos os arquivos adicionados
✅ Merge sem conflitos
✅ Build passa após merge
✅ Testes passam após merge
✅ Tag criada (opcional)
✅ Documentação no README
✅ Release notes criadas
```

---

## Timeline Sugerido

```
17 FEB (Hoje)
├─ 17:00 - Finalizar implementação
└─ 17:30 - Preparar PR

18 FEV (Amanhã)
├─ 09:00 - Code review
├─ 10:00 - Aprovar/Merge
├─ 10:30 - Build & test
├─ 11:00 - Deploy em staging
└─ 12:00 - Validação final

19 FEV
├─ 09:00 - Deploy em produção
└─ 10:00 - Monitoring

20-23 FEV
└─ Próximas features (PDFs, Vacinação)
```

---

**Status:** ✅ Pronto para Merge  
**Branches:** main, feature/auditoria-tempo-real  
**Conflitos Esperados:** Nenhum

