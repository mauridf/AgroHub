-- Tabela log_acesso
CREATE TABLE log_acesso (
    id BINARY(16) NOT NULL PRIMARY KEY,
    usuario_id BINARY(16) NOT NULL,
    data_hora DATETIME NOT NULL,
    ip VARCHAR(50),
    acao VARCHAR(50),
    CONSTRAINT fk_log_acesso_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- Tabela maquinas_agricolas
CREATE TABLE maquinas_agricolas (
    id BINARY(16) NOT NULL PRIMARY KEY,
    fazenda_id BINARY(16) NOT NULL,
    descricao VARCHAR(255),
    marca VARCHAR(100),
    modelo VARCHAR(100),
    ano INT,
    valor_aproximado DOUBLE,
    CONSTRAINT fk_maquina_fazenda FOREIGN KEY (fazenda_id) REFERENCES fazendas(id)
);

-- Tabela funcionarios
CREATE TABLE funcionarios (
    id BINARY(16) NOT NULL PRIMARY KEY,
    fazenda_id BINARY(16) NOT NULL,
    nome VARCHAR(255),
    cpf CHAR(11) UNIQUE,
    funcao VARCHAR(100),
    salario DOUBLE,
    data_admissao DATE,
    data_demissao DATE,
    CONSTRAINT fk_funcionario_fazenda FOREIGN KEY (fazenda_id) REFERENCES fazendas(id)
);

-- Tabela insumos
CREATE TABLE insumos (
    id BINARY(16) NOT NULL PRIMARY KEY,
    nome VARCHAR(255),
    tipo VARCHAR(50),
    unidade_medida VARCHAR(20),
    fornecedor VARCHAR(100)
);

-- Tabela estoque_insumo
CREATE TABLE estoque_insumo (
    id BINARY(16) NOT NULL PRIMARY KEY,
    fazenda_id BINARY(16) NOT NULL,
    insumo_id BINARY(16) NOT NULL,
    quantidade DOUBLE,
    validade DATE,
    CONSTRAINT fk_estoque_fazenda FOREIGN KEY (fazenda_id) REFERENCES fazendas(id),
    CONSTRAINT fk_estoque_insumo FOREIGN KEY (insumo_id) REFERENCES insumos(id)
);

-- Tabela compra_insumo
CREATE TABLE compra_insumo (
    id BINARY(16) NOT NULL PRIMARY KEY,
    fazenda_id BINARY(16) NOT NULL,
    insumo_id BINARY(16) NOT NULL,
    quantidade DOUBLE,
    valor_total DOUBLE,
    data_compra DATE,
    fornecedor VARCHAR(100),
    CONSTRAINT fk_compra_fazenda FOREIGN KEY (fazenda_id) REFERENCES fazendas(id),
    CONSTRAINT fk_compra_insumo FOREIGN KEY (insumo_id) REFERENCES insumos(id)
);
