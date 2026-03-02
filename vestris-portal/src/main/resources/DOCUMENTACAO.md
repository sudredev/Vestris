## src\main\resources

### application.properties

```properties
# src\main\resources\application.properties

spring.datasource.url=jdbc:postgresql://localhost:5432/vestris_db
spring.datasource.username=vestris_user
spring.datasource.password=vestris_password
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.defer-datasource-initialization=true
spring.sql.init.mode=always


spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

server.port=8080


springdoc.swagger-ui.path=/swagger-ui.html
springdoc.api-docs.path=/v3/api-docs
```

### schema.sql

```sql
-- src\main\resources\schema.sql
-- Recriar Schemas
CREATE SCHEMA IF NOT EXISTS species_schema;
CREATE SCHEMA IF NOT EXISTS pharmacology_schema;
CREATE SCHEMA IF NOT EXISTS clinical_schema;
CREATE SCHEMA IF NOT EXISTS vaccination_schema;
CREATE SCHEMA IF NOT EXISTS reference_schema;
CREATE SCHEMA IF NOT EXISTS medical_record_schema;
CREATE SCHEMA IF NOT EXISTS feedback_schema;
CREATE SCHEMA IF NOT EXISTS saas_schema;
CREATE SCHEMA IF NOT EXISTS users_schema; -- Garante que existe


-- =============================================================================
-- 2. CRIAÇÃO DAS TABELAS (ESTRUTURA LIMPA)
-- =============================================================================

-- --- SAAS & USERS (Base) ---
CREATE TABLE IF NOT EXISTS users_schema.clinicas (
                                                     id UUID PRIMARY KEY,
                                                     nome_fantasia VARCHAR(200) NOT NULL,
    razao_social VARCHAR(200),
    cnpj VARCHAR(20),
    endereco TEXT,
    telefone VARCHAR(50),
    email_contato VARCHAR(150),
    logo_base64 TEXT,
    criado_em TIMESTAMP DEFAULT NOW(),
    atualizado_em TIMESTAMP DEFAULT NOW()
    );

CREATE TABLE IF NOT EXISTS saas_schema.planos (
                                    id UUID PRIMARY KEY,
                                    nome VARCHAR(100) NOT NULL,
                                    descricao VARCHAR(255),
                                    preco_mensal DECIMAL(10, 2),
                                    preco_anual DECIMAL(10, 2),
                                    url_pagamento TEXT,
                                    max_veterinarios INTEGER NOT NULL,
                                    is_custom BOOLEAN DEFAULT FALSE,
                                    features_json TEXT,
                                    criado_em TIMESTAMP DEFAULT NOW(),
                                    atualizado_em TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS users_schema.usuarios (
                                                     id UUID PRIMARY KEY,
                                                     clinica_id UUID REFERENCES users_schema.clinicas(id),
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    crmv VARCHAR(20),
    perfil VARCHAR(20) NOT NULL,
    scope VARCHAR(20) DEFAULT 'TENANT',
    criado_em TIMESTAMP DEFAULT NOW(),
    atualizado_em TIMESTAMP DEFAULT NOW()
    );

-- Auditoria (Com correção do tipo TEXT)
CREATE TABLE IF NOT EXISTS users_schema.auditoria (
                                                      id UUID PRIMARY KEY,
                                                      clinica_id UUID NOT NULL,
                                                      usuario_id UUID NOT NULL,
                                                      acao VARCHAR(50) NOT NULL,
    entidade VARCHAR(50) NOT NULL,
    id_alvo UUID NOT NULL,
    descricao_curta TEXT,
    metadados TEXT,
    data_hora TIMESTAMP DEFAULT NOW()
    );

-- Assinaturas (Depende de Clínica e Plano)
CREATE TABLE IF NOT EXISTS saas_schema.assinaturas (
                                         id UUID PRIMARY KEY,
                                         clinica_id UUID NOT NULL REFERENCES users_schema.clinicas(id),
                                         plano_id UUID NOT NULL REFERENCES saas_schema.planos(id),
                                         id_externo_assinatura  VARCHAR(100),
                                         status VARCHAR(20) NOT NULL,
                                         tipo_faturamento VARCHAR(20) NOT NULL,
                                         data_inicio TIMESTAMP NOT NULL,
                                         data_fim TIMESTAMP,
                                         data_trial_fim TIMESTAMP,
                                         override_limits_json TEXT,
                                         criado_em TIMESTAMP DEFAULT NOW(),
                                         atualizado_em TIMESTAMP DEFAULT NOW()
);

-- --- SPECIES ---
CREATE TABLE IF NOT EXISTS species_schema.especies (
                                         id UUID PRIMARY KEY,
                                         nome_popular VARCHAR(150) NOT NULL,
                                         nome_cientifico VARCHAR(150) NOT NULL UNIQUE,
                                         familia_taxonomica VARCHAR(100),
                                         grupo VARCHAR(50),
                                         resumo_clinico TEXT,
                                         parametros_fisiologicos TEXT,
                                         expectativa_vida VARCHAR(100),
                                         peso_adulto VARCHAR(100),
                                         tipo_dieta VARCHAR(50),
                                         manejo_infos TEXT,
                                         alertas_clinicos TEXT,
                                         pontos_exame_fisico TEXT,
                                         habitat TEXT,
                                         distribuicao_geografica TEXT,
                                         status_conservacao VARCHAR(100),
                                         bibliografia_base TEXT,
                                         receita_manejo_padrao TEXT,
                                         criado_em TIMESTAMP DEFAULT NOW(),
                                         atualizado_em TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS species_schema.modelos_exame_fisico (
                                                     id UUID PRIMARY KEY,
                                                     especie_id UUID REFERENCES species_schema.especies(id),
                                                     texto_base TEXT NOT NULL,
                                                     criado_em TIMESTAMP DEFAULT NOW()
);

-- --- PHARMACOLOGY ---
CREATE TABLE IF NOT EXISTS pharmacology_schema.principios_ativos (
                                                       id UUID PRIMARY KEY,
                                                       nome VARCHAR(150) NOT NULL UNIQUE,
                                                       grupo_farmacologico VARCHAR(100),
                                                       descricao TEXT,
                                                       criado_em TIMESTAMP DEFAULT NOW(),
                                                       atualizado_em TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS pharmacology_schema.medicamentos (
                                                  id UUID PRIMARY KEY,
                                                  principio_ativo_id UUID REFERENCES pharmacology_schema.principios_ativos(id),
                                                  nome VARCHAR(150) NOT NULL,
                                                  concentracao VARCHAR(50),
                                                  fabricante VARCHAR(100),
                                                  forma_farmaceutica VARCHAR(100),
                                                  criado_em TIMESTAMP DEFAULT NOW(),
                                                  atualizado_em TIMESTAMP DEFAULT NOW()
);

-- Tabela de Doses (Com Constraints de Segurança)
CREATE TABLE IF NOT EXISTS pharmacology_schema.doses_referencia (
                                                      id UUID PRIMARY KEY,
                                                      medicamento_id UUID NOT NULL REFERENCES pharmacology_schema.medicamentos(id),
                                                      especie_id UUID REFERENCES species_schema.especies(id),
                                                      doenca_id UUID, -- FK será adicionada depois pois clinical ainda não existe
                                                      clinica_id UUID REFERENCES users_schema.clinicas(id),
                                                      via VARCHAR(50),
                                                      unidade VARCHAR(50) NOT NULL DEFAULT 'MG_KG',
                                                      dose_min NUMERIC(10,4),
                                                      dose_max NUMERIC(10,4),
                                                      origem VARCHAR(20) NOT NULL,
                                                      nivel_confianca VARCHAR(20) DEFAULT 'MEDIA',
                                                      fonte_bibliografica TEXT,
                                                      observacoes TEXT,
                                                      criado_em TIMESTAMP DEFAULT NOW(),
                                                      atualizado_em TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS pharmacology_schema.contraindicacoes (
                                                      id UUID PRIMARY KEY,
                                                      principio_ativo_id UUID REFERENCES pharmacology_schema.principios_ativos(id),
                                                      especie_id UUID REFERENCES species_schema.especies(id),
                                                      condicao_clinica VARCHAR(200),
                                                      gravidade VARCHAR(20) NOT NULL,
                                                      descricao TEXT NOT NULL,
                                                      referencia_texto VARCHAR(255),
                                                      criado_em TIMESTAMP DEFAULT NOW()
);

-- --- CLINICAL ---
CREATE TABLE IF NOT EXISTS clinical_schema.doencas (
                                         id UUID PRIMARY KEY,
                                         especie_id UUID REFERENCES species_schema.especies(id),
                                         nome VARCHAR(150) NOT NULL,
                                         nome_cientifico VARCHAR(150),
                                         sintomas TEXT,
                                         criado_em TIMESTAMP DEFAULT NOW(),
                                         atualizado_em TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS clinical_schema.protocolos (
                                            id UUID PRIMARY KEY,
                                            doenca_id UUID REFERENCES clinical_schema.doencas(id),
                                            doenca_texto_livre VARCHAR(255),
                                            referencia_id UUID,
                                            referencia_texto VARCHAR(255),
                                            titulo VARCHAR(200) NOT NULL,
                                            observacoes TEXT,
                                            origem VARCHAR(20) DEFAULT 'OFICIAL',
                                            autor_id UUID,
                                            clinica_id UUID,
                                            criado_em TIMESTAMP DEFAULT NOW(),
                                            atualizado_em TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS clinical_schema.dosagens (
                                          id UUID PRIMARY KEY,
                                          protocolo_id UUID REFERENCES clinical_schema.protocolos(id) ON DELETE CASCADE,
                                          medicamento_id UUID REFERENCES pharmacology_schema.medicamentos(id),
                                          medicamento_texto_livre VARCHAR(255),
                                          dose_minima DOUBLE PRECISION,
                                          dose_maxima DOUBLE PRECISION,
                                          unidade VARCHAR(20),
                                          frequencia VARCHAR(50),
                                          duracao VARCHAR(50),
                                          via VARCHAR(50),
                                          criado_em TIMESTAMP DEFAULT NOW(),
                                          atualizado_em TIMESTAMP DEFAULT NOW()
);

-- --- VACCINATION ---
CREATE TABLE IF NOT EXISTS vaccination_schema.vacinas (
                                            id UUID PRIMARY KEY,
                                            nome VARCHAR(150) NOT NULL,
                                            fabricante VARCHAR(100),
                                            tipo_vacina VARCHAR(100),
                                            descricao TEXT,
                                            doenca_alvo VARCHAR(150),
                                            criado_em TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS vaccination_schema.protocolos_vacinacao (
                                                         id UUID PRIMARY KEY,
                                                         especie_id UUID NOT NULL REFERENCES species_schema.especies(id),
                                                         vacina_id UUID REFERENCES vaccination_schema.vacinas(id),
                                                         referencia_id UUID,
                                                         idade_minima_dias INTEGER,
                                                         dias_para_reforco INTEGER,
                                                         obrigatoria BOOLEAN DEFAULT FALSE,
                                                         observacoes TEXT,
                                                         criado_em TIMESTAMP DEFAULT NOW()
);

-- --- REFERENCE ---
CREATE TABLE IF NOT EXISTS reference_schema.referencias (
                                              id UUID PRIMARY KEY,
                                              titulo VARCHAR(255) NOT NULL,
                                              autores VARCHAR(255) NOT NULL,
                                              ano INTEGER,
                                              fonte VARCHAR(150),
                                              url VARCHAR(255),
                                              criado_em TIMESTAMP DEFAULT NOW(),
                                              atualizado_em TIMESTAMP DEFAULT NOW()
);

-- --- MEDICAL RECORD ---
CREATE TABLE IF NOT EXISTS medical_record_schema.pacientes (
                                                 id UUID PRIMARY KEY,
                                                 veterinario_id UUID NOT NULL,
                                                 especie_id UUID NOT NULL REFERENCES species_schema.especies(id),
                                                 nome VARCHAR(100) NOT NULL,
                                                 dados_tutor TEXT NOT NULL,
                                                 identificacao_animal VARCHAR(50),
                                                 microchip VARCHAR(50),
                                                 pelagem_cor VARCHAR(50),
                                                 sexo VARCHAR(20),
                                                 data_nascimento DATE,
                                                 peso_atual_gramas INTEGER,
                                                 criado_em TIMESTAMP DEFAULT NOW(),
                                                 atualizado_em TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS medical_record_schema.atendimentos (
                                                    id UUID PRIMARY KEY,
                                                    paciente_id UUID REFERENCES medical_record_schema.pacientes(id),
                                                    veterinario_id UUID NOT NULL,
                                                    protocolo_id UUID,
                                                    titulo VARCHAR(150) NOT NULL,
                                                    data_hora TIMESTAMP NOT NULL,
                                                    status VARCHAR(20) NOT NULL,
                                                    queixa_principal TEXT,
                                                    historico_clinico TEXT,
                                                    exame_fisico TEXT,
                                                    diagnostico TEXT,
                                                    conduta_clinica TEXT,
                                                    orientacoes_manejo TEXT,
                                                    observacoes TEXT,
                                                    criado_em TIMESTAMP DEFAULT NOW(),
                                                    atualizado_em TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS medical_record_schema.exames_anexos (
                                                     id UUID PRIMARY KEY,
                                                     atendimento_id UUID NOT NULL REFERENCES medical_record_schema.atendimentos(id),
                                                     nome_arquivo VARCHAR(255) NOT NULL,
                                                     tipo_arquivo VARCHAR(50),
                                                     url_arquivo TEXT,
                                                     observacoes TEXT,
                                                     criado_em TIMESTAMP DEFAULT NOW(),
                                                     atualizado_em TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS medical_record_schema.aplicacoes_vacinas (
                                                          id UUID PRIMARY KEY,
                                                          paciente_id UUID REFERENCES medical_record_schema.pacientes(id),
                                                          vacina_id UUID NOT NULL REFERENCES vaccination_schema.vacinas(id),
                                                          data_aplicacao DATE NOT NULL,
                                                          data_proxima_dose DATE,
                                                          lote VARCHAR(50),
                                                          veterinario_id UUID,
                                                          observacoes TEXT,
                                                          criado_em TIMESTAMP DEFAULT NOW(),
                                                          atualizado_em TIMESTAMP DEFAULT NOW()
);

-- --- FEEDBACK ---
CREATE TABLE IF NOT EXISTS feedback_schema.sugestoes (
                                           id UUID PRIMARY KEY,
                                           usuario_id UUID NOT NULL,
                                           tipo VARCHAR(20) NOT NULL,
                                           conteudo TEXT NOT NULL,
                                           status VARCHAR(20) NOT NULL,
                                           criado_em TIMESTAMP DEFAULT NOW()
);


-- =============================================================================
-- 3. AJUSTES DE MIGRAÇÃO (SEM ERROS)
-- =============================================================================
ALTER TABLE users_schema.usuarios DROP CONSTRAINT IF EXISTS usuarios_perfil_check;


-- =============================================================================
-- 4. CARGA DE DADOS (SEEDS COM UUIDS VÁLIDOS)
-- =============================================================================

-- Planos
INSERT INTO saas_schema.planos (id, nome, preco_mensal, max_veterinarios, is_custom) VALUES
                                                                                         ('90000000-0000-0000-0000-000000000001', 'Veterinário Autônomo', 79.00, 1, false),
                                                                                         ('90000000-0000-0000-0000-000000000002', 'Clínica Pequena', 249.00, 3, false),
                                                                                         ('90000000-0000-0000-0000-000000000003', 'Clínica Média', 449.00, 6, false),
                                                                                         ('90000000-0000-0000-0000-000000000004', 'Enterprise', 0.00, 999, true)
    ON CONFLICT (id) DO NOTHING;

-- Espécies
INSERT INTO species_schema.especies (id, nome_popular, nome_cientifico, grupo, criado_em) VALUES
                                                                                              ('c1000000-0000-0000-0000-000000000001', 'Calopsita', 'Nymphicus hollandicus', 'Ave', NOW()),
                                                                                              ('c1000000-0000-0000-0000-000000000002', 'Jabuti-piranga', 'Chelonoidis carbonaria', 'Réptil', NOW())
    ON CONFLICT (nome_cientifico) DO NOTHING;

-- Princípios Ativos
INSERT INTO pharmacology_schema.principios_ativos (id, nome, grupo_farmacologico, criado_em) VALUES
                                                                                                 ('a1000000-0000-0000-0000-000000000001', 'Enrofloxacina', 'Antibiótico', NOW()),
                                                                                                 ('a1000000-0000-0000-0000-000000000002', 'Dipirona', 'Analgésico', NOW())
    ON CONFLICT (nome) DO NOTHING;

-- Medicamentos
INSERT INTO pharmacology_schema.medicamentos (id, principio_ativo_id, nome, concentracao, fabricante, forma_farmaceutica, criado_em) VALUES
                                                                                                                                         ('d1000000-0000-0000-0000-000000000001', 'a1000000-0000-0000-0000-000000000001', 'Baytril 10%', '100 mg/ml', 'Bayer', 'Injetável', NOW()),
                                                                                                                                         ('d1000000-0000-0000-0000-000000000002', 'a1000000-0000-0000-0000-000000000002', 'Dipirona Gotas', '500 mg/ml', 'Genérico', 'Oral', NOW())
    ON CONFLICT (id) DO NOTHING;

-- Doses de Referência (Seed)
INSERT INTO pharmacology_schema.doses_referencia
(id, medicamento_id, especie_id, dose_min, dose_max, unidade, via, origem, nivel_confianca, fonte_bibliografica, criado_em)
VALUES
-- Baytril Calopsita
(gen_random_uuid(), 'd1000000-0000-0000-0000-000000000001', 'c1000000-0000-0000-0000-000000000001', 15.0, 30.0, 'MG_KG', 'ORAL', 'OFICIAL', 'ALTA', 'Carpenter, 2018', NOW()),
-- Baytril Jabuti
(gen_random_uuid(), 'd1000000-0000-0000-0000-000000000001', 'c1000000-0000-0000-0000-000000000002', 5.0, 10.0, 'MG_KG', 'IM', 'OFICIAL', 'ALTA', 'Carpenter, 2018', NOW()),
-- Dipirona Geral
(gen_random_uuid(), 'd1000000-0000-0000-0000-000000000002', NULL, 25.0, 50.0, 'MG_KG', NULL, 'OFICIAL', 'MEDIA', 'Formulário Veterinário', NOW());

-- Clínica Teste e Vínculos
INSERT INTO users_schema.clinicas (id, nome_fantasia, razao_social, criado_em)
VALUES ('31101310-0000-0000-0000-000000000001', 'Clínica BPS', 'BPS Serviços Veterinários Ltda', NOW())
    ON CONFLICT (id) DO NOTHING;

-- Atualizar Usuários (Se existirem)
UPDATE users_schema.usuarios SET clinica_id = '31101310-0000-0000-0000-000000000001' WHERE email LIKE '%@clinicabps.com';
UPDATE users_schema.usuarios SET perfil = 'ADMIN_CLINICO' WHERE email = 'diretor@clinicabps.com';
UPDATE users_schema.usuarios SET perfil = 'ADMIN_GESTOR' WHERE email = 'gestor@clinicabps.com';
UPDATE users_schema.usuarios SET perfil = 'VETERINARIO' WHERE email = 'vet@clinicabps.com';
UPDATE users_schema.usuarios SET perfil = 'ADMIN_GLOBAL', scope = 'GLOBAL' WHERE email = 'admin@vestris.com';
```

