CREATE SCHEMA IF NOT EXISTS users_schema;
CREATE SCHEMA IF NOT EXISTS species_schema;
CREATE SCHEMA IF NOT EXISTS clinical_schema;
CREATE SCHEMA IF NOT EXISTS pharmacology_schema;
CREATE SCHEMA IF NOT EXISTS vaccination_schema;
CREATE SCHEMA IF NOT EXISTS reference_schema;

-- Conceder permissões (se necessário em prod, aqui é local)
GRANT ALL PRIVILEGES ON DATABASE silvet_db TO silvet_user;