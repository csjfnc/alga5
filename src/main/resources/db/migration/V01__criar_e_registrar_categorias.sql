CREATE TABLE categoria(
codigo BIGSERIAL,
descricao varchar(50),

PRIMARY KEY(codigo)
);

INSERT INTO categoria(descricao) VALUES('Lazer');
INSERT INTO categoria(descricao) VALUES('Alimentação');
INSERT INTO categoria(descricao) VALUES('Farmacia');
INSERT INTO categoria(descricao) VALUES('Carro');
INSERT INTO categoria(descricao) VALUES('Despesas Extraordinárias');
