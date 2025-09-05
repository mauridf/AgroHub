-- Tabela venda_producao
CREATE TABLE venda_producao (
    id BINARY(16) NOT NULL PRIMARY KEY,
    cultura_plantada_id BINARY(16) NOT NULL,
    quantidade_vendida DOUBLE,
    preco_unitario DOUBLE,
    data_venda DATE,
    destino VARCHAR(255),
    CONSTRAINT fk_venda_cultura_plantada FOREIGN KEY (cultura_plantada_id) REFERENCES culturas_plantadas(id)
);

-- Tabela clima
CREATE TABLE clima (
    id BINARY(16) NOT NULL PRIMARY KEY,
    fazenda_id BINARY(16) NOT NULL,
    data DATE,
    temperatura DOUBLE,
    chuva_mm DOUBLE,
    umidade DOUBLE,
    CONSTRAINT fk_clima_fazenda FOREIGN KEY (fazenda_id) REFERENCES fazendas(id)
);

-- Tabela custo_operacional
CREATE TABLE custo_operacional (
    id BINARY(16) NOT NULL PRIMARY KEY,
    fazenda_id BINARY(16) NOT NULL,
    descricao VARCHAR(255),
    valor DOUBLE,
    data DATE,
    CONSTRAINT fk_custo_fazenda FOREIGN KEY (fazenda_id) REFERENCES fazendas(id)
);

-- Tabela contrato
CREATE TABLE contrato (
    id BINARY(16) NOT NULL PRIMARY KEY,
    fazenda_id BINARY(16) NOT NULL,
    tipo VARCHAR(50), -- Arrendamento, Parceria, Financiamento
    parte_contratante VARCHAR(255),
    valor DOUBLE,
    data_inicio DATE,
    data_fim DATE,
    CONSTRAINT fk_contrato_fazenda FOREIGN KEY (fazenda_id) REFERENCES fazendas(id)
);

-- Tabela certificacao
CREATE TABLE certificacao (
    id BINARY(16) NOT NULL PRIMARY KEY,
    fazenda_id BINARY(16) NOT NULL,
    tipo VARCHAR(50), -- Orgânico, FairTrade
    data_emissao DATE,
    data_validade DATE,
    CONSTRAINT fk_certificacao_fazenda FOREIGN KEY (fazenda_id) REFERENCES fazendas(id)
);

-- Tabela alerta_monitoramento
CREATE TABLE alerta_monitoramento (
    id BINARY(16) NOT NULL PRIMARY KEY,
    fazenda_id BINARY(16) NOT NULL,
    tipo VARCHAR(50), -- praga, doença, clima, maquinário
    descricao VARCHAR(255),
    nivel VARCHAR(20), -- baixo, médio, alto
    data DATETIME,
    resolvido BOOLEAN,
    CONSTRAINT fk_alerta_fazenda FOREIGN KEY (fazenda_id) REFERENCES fazendas(id)
);
