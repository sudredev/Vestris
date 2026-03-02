BACKEND CONTRACT RULES

0) Regras Globais (Obrigatório)

Objetivo do Drop 1

Fechar o produto para operação clínica + institucional e deixar pronto para Drop do Banco.

Proibido no Drop 1
•	pagamentos
•	financeiro
•	estoque
•	RH
•	BI avançado
•	app mobile
•	integrações externas (WhatsApp, email etc)

Regra de Ouro

O sistema:
•	sugere
•	alerta
•	documenta
e nunca bloqueia o veterinário.

⸻

1) MÓDULO — GOVERNANÇA INSTITUCIONAL (PRIORIDADE 1)

Escopo

Implementar controle institucional real baseado em:

Princípio-base
•	Paciente pertence à instituição
•	Atendimento pertence ao veterinário
•	Governança pertence ao administrador

Entidades / Dados
•	usuario precisa suportar perfis:
•	ADMIN_GLOBAL
•	ADMIN_GESTOR
•	ADMIN_CLINICO
•	VETERINARIO
•	(manter ESTUDANTE se já existir, mas ele não entra no Drop 1 institucional)
•	Toda entidade clínica relevante deve ter:
•	clinica_id

Regras obrigatórias
•	Vets da mesma clínica veem pacientes e histórico
•	Protocolos próprios não vazam
•	Admin gestor não pode acessar campos clínicos
•	Admin clínico pode alternar modo Vet/Admin

⸻

DOD (Definition of Done)
•	Perfil Enum refatorado e aplicado no backend
•	Regras de visibilidade por clinica_id aplicadas em:
•	pacientes
•	atendimentos
•	anexos
•	PDFs
•	Admin gestor recebe resposta sanitizada ao tentar acessar atendimento
•	Admin clínico possui permissão total (modo Vet)
•	Nenhum endpoint retorna dado clínico para Admin gestor

⸻

Perguntas obrigatórias antes de começar
1.	O que define “clínica” no banco hoje? (clinica_id existe ou precisa criar?)
2.	Existe usuário sem clínica? O que ele pode fazer?
3.	O estudante terá acesso a quê? (recomendação: somente biblioteca)

⸻

⸻

2) MÓDULO — PROTOCOLOS (3 NÍVEIS + FILTROS)

Escopo

Protocolos precisam ter origem:
•	OFICIAL (global)
•	INSTITUCIONAL (por clínica)
•	PROPRIO (por usuário)

Regras obrigatórias
•	Oficial: leitura pra todos
•	Institucional:
•	visível pra todos da clínica
•	editável apenas por ADMIN_CLINICO
•	Próprio:
•	visível apenas pro autor

Queries obrigatórias

Todo usuário deve listar:
•	Oficiais
•	Institucionais da sua clínica
•	Próprios dele

⸻

DOD
•	Enum origem atualizado
•	clinica_id aplicado em protocolos institucionais
•	Endpoint de listagem retorna corretamente os 3 blocos
•	Nenhum protocolo próprio aparece para outro vet
•	Filtros por espécie e doença funcionando no backend

⸻

Perguntas obrigatórias
1.	Espécie e doença já existem como FK ou são strings?
2.	Protocolo suporta múltiplas espécies/doenças? (recomendação: NÃO no Drop 1)

⸻

⸻

3) MÓDULO — AUDITORIA E GOVERNANÇA (LOGS)

Escopo

Criar auditoria mínima para governança institucional.

Entidade

Tabela auditoria com campos mínimos:
•	id
•	clinica_id
•	usuario_id
•	perfil_usuario
•	acao
•	entidade
•	alvo_id
•	metadata_json (opcional)
•	data_hora

Ações mínimas obrigatórias (Drop 1)

PACIENTE
•	CRIOU
•	EDITOU

ATENDIMENTO
•	CRIOU
•	INICIOU
•	FINALIZOU
•	CANCELOU

AGENDA
•	AGENDOU
•	REMARCOU
•	CANCELOU

ANEXO
•	ANEXOU
•	REMOVEU (se existir)

PDF
•	GEROU_RECEITA
•	GEROU_MANEJO
•	GEROU_PRONTUARIO

PROTOCOLO
•	CRIOU_PROTOCOLO
•	EDITOU_PROTOCOLO
•	PUBLICOU_INSTITUCIONAL (se existir)

⸻

Regras obrigatórias
•	Não pode depender do Front para logar
•	Logs são gerados no backend
•	Logs sempre vinculados a:
•	clínica
•	usuário
•	entidade-alvo

⸻

DOD
•	Tabela criada
•	Service central de auditoria criado
•	Cada evento mínimo gera log real
•	Endpoint de listagem por clínica (Admin gestor e admin clínico)
•	Logs ordenados por data desc
•	Logs com paginação (mesmo simples)

⸻

Perguntas obrigatórias
1.	Logs devem armazenar IP? (recomendação: NÃO no Drop 1)
2.	Logs devem armazenar snapshot do atendimento? (recomendação: NÃO)
3.	Logs podem ser deletados? (recomendação: NÃO)

⸻

⸻

4) MÓDULO — CALCULADORA LIVRE (DOSE MIN/MAX)

Escopo

Validação leve quando existir base.

Regras
•	Calculadora continua livre
•	Se usuário informar espécie e existir faixa:
•	validar
•	alertar
•	logar evento

Saídas
•	Dentro do intervalo
•	Abaixo
•	Acima
•	Sem referência

⸻

DOD
•	Base suporta dose_min/dose_max por espécie
•	Calculadora retorna status
•	Logs criados quando fora da faixa
•	Nunca bloqueia

⸻

Perguntas obrigatórias
1.	Dose min/max ficará em qual entidade? (medicamento? espécie? ambos?)
2.	Unidade mg/kg é padrão? Quais exceções?

⸻

⸻

5) MÓDULO — VACINAÇÃO HUB

Escopo

Criar HUB de vacinação separado do paciente.

Dados
•	vacina
•	vacina_especie
•	vacina_indicacao (doença)

Regras
•	Não inventar calendários
•	Apenas armazenar o que for inserido

⸻

DOD
•	CRUD base de vacinas
•	Relação com espécie e doença
•	Filtro por espécie e doença funcionando

⸻

Perguntas obrigatórias
1.	Vacina será global (admin global) ou institucional?
(recomendação: global no Drop 1)

⸻

⸻

6) MÓDULO — ONG/ZOO (PACIENTE SEM TUTOR + STATUS)

Escopo

Permitir operação real de ONG e Zoo sem virar ERP.

Campos mínimos no Paciente
•	tem_tutor boolean
•	origem_do_caso
•	instituicao_responsavel
•	status_linha_de_vida enum

Enum status
•	Resgatado
•	Triagem
•	Em tratamento
•	Estável
•	Reabilitação
•	Soltura
•	Óbito
•	Cativeiro permanente

⸻

DOD
•	Campos adicionados
•	Validações mínimas
•	Relatórios simples por status

⸻

Perguntas obrigatórias
1.	Status é obrigatório? (recomendação: NÃO)
2.	Qual status default? (recomendação: TRIAGEM)

⸻

⸻

7) MÓDULO — RELATÓRIOS SIMPLES (IMPACTO ONG/ZOO)

Escopo

Apenas agregados.

Exemplos:
•	atendimentos no período
•	atendimentos por espécie
•	por status
•	solturas
•	óbitos

⸻

DOD
•	Endpoint com filtros por data
•	Retorno agregado simples
•	Admin gestor e admin clínico conseguem acessar

⸻

Perguntas obrigatórias
1.	Qual período default? (recomendação: últimos 30 dias)

⸻

⸻

8) MÓDULO — UPGRADE DE ANEXOS (SEM VIRAR MÓDULO)

Escopo

Adicionar 3 campos mínimos:
1.	tipo do anexo
2.	data do exame
3.	flag “relevante”

Regras
•	Não criar módulo de laboratório
•	Não criar módulo de imagem

⸻

DOD
•	Campos adicionados na entidade anexo
•	Endpoint suporta filtros por “relevante”
•	Anexo mostra tipo e data

⸻

Perguntas obrigatórias
1.	Quem pode editar um anexo?
(recomendação: vet criador ou admin clínico)

⸻

⸻

9) MÓDULO — MODO CAMPANHA (ONG)

Escopo

Criar um fluxo rápido de atendimento padronizado.

Campos mínimos (registro rápido)
•	Nome tutor
•	Nome animal
•	Sexo
•	Peso
•	Vacina raiva (sim/não)
•	Observação livre
•	Vet responsável pela campanha (fixo)

Regra importante
•	Campanha não é “consulta completa”
•	Não exigir:
•	anamnese completa
•	exame físico completo
•	protocolos complexos
•	biblioteca

Saída obrigatória
•	Documento final (PDF campanha) padronizado
•	com CRMV do vet responsável
•	com posologia calculada por peso

📌 (No Drop 1: pode ser apenas geração de PDF simples com template)

⸻

DOD
•	Endpoint para criar “Atendimento Campanha”
•	Atendimento campanha não quebra o modelo de prontuário
•	Gera PDF simples
•	Loga tudo na auditoria

⸻

Perguntas obrigatórias
1.	Campanha é por clínica? (SIM)
2.	Vet responsável é fixo por campanha? (SIM)
3.	Campanha precisa de assinatura digital? (NÃO no Drop 1)