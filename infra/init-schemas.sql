CREATE SCHEMA IF NOT EXISTS species_schema;
CREATE SCHEMA IF NOT EXISTS pharmacology_schema;
CREATE SCHEMA IF NOT EXISTS clinical_schema;
CREATE SCHEMA IF NOT EXISTS vaccination_schema;
CREATE SCHEMA IF NOT EXISTS reference_schema;
CREATE SCHEMA IF NOT EXISTS medical_record_schema;
CREATE SCHEMA IF NOT EXISTS feedback_schema;
CREATE SCHEMA IF NOT EXISTS saas_schema;
CREATE SCHEMA IF NOT EXISTS users_schema; -- Garante que existe
-- Conceder permissões (se necessário em prod, aqui é local)
GRANT ALL PRIVILEGES ON DATABASE vestris_db TO vestris_user;