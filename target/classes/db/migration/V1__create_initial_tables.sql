-- Tabela usuarios
CREATE TABLE usuarios (
    id BINARY(16) NOT NULL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha_hash VARCHAR(255) NOT NULL,
    role VARCHAR(10) NOT NULL,
    data_cadastro DATETIME NOT NULL,
    ultimo_login DATETIME
);

-- Tabela produtores
CREATE TABLE produtores (
    id BINARY(16) NOT NULL PRIMARY KEY,
    usuario_id BINARY(16) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    cpf CHAR(11) NOT NULL UNIQUE,
    rg VARCHAR(50),
    inscricao_estadual VARCHAR(50),
    data_nascimento DATE,
    telefone VARCHAR(20),
    endereco VARCHAR(255),
    cidade VARCHAR(100),
    estado VARCHAR(50),
    dados_bancarios VARCHAR(255),
    car VARCHAR(50),
    CONSTRAINT fk_produtor_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- Tabela fazendas
CREATE TABLE fazendas (
    id BINARY(16) NOT NULL PRIMARY KEY,
    produtor_id BINARY(16) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    endereco VARCHAR(255),
    cidade VARCHAR(100),
    estado VARCHAR(50),
    latitude DECIMAL(10,6),
    longitude DECIMAL(10,6),
    area_total_ha DOUBLE NOT NULL,
    area_agricultavel_ha DOUBLE,
    area_vegetacao_ha DOUBLE,
    area_construida_ha DOUBLE,
    inscricao_estadual VARCHAR(50),
    codigo_car VARCHAR(50),
    ccir VARCHAR(50),
    fonte_agua VARCHAR(50),
    CONSTRAINT fk_fazenda_produtor FOREIGN KEY (produtor_id) REFERENCES produtores(id)
);

-- Tabela culturas
CREATE TABLE culturas (
    id BINARY(16) NOT NULL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    ciclo VARCHAR(50),
    variedade VARCHAR(100)
);

-- Tabela culturas_plantadas
CREATE TABLE culturas_plantadas (
    id BINARY(16) NOT NULL PRIMARY KEY,
    fazenda_id BINARY(16) NOT NULL,
    cultura_id BINARY(16) NOT NULL,
    safra VARCHAR(20),
    area_plantada_ha DOUBLE,
    data_plantio DATE,
    data_colheita_prevista DATE,
    data_colheita_real DATE,
    produtividade_esperada_sacas_ha DOUBLE,
    produtividade_obtida_sacas_ha DOUBLE,
    custo_total DOUBLE,
    receita_total DOUBLE,
    CONSTRAINT fk_cultura_plantada_fazenda FOREIGN KEY (fazenda_id) REFERENCES fazendas(id),
    CONSTRAINT fk_cultura_plantada_cultura FOREIGN KEY (cultura_id) REFERENCES culturas(id)
);
