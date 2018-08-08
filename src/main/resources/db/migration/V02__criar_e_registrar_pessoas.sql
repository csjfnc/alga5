CREATE TABLE pessoa(
	codigo BIGSERIAL,
	nome VARCHAR(50) NOT NULL,
	ativo BOOLEAN,
	lagradouro VARCHAR(100),
	numero VARCHAR(10),
	complemento VARCHAR(100),
	bairro VARCHAR(50),
	cep VARCHAR(10),
	cidade VARCHAR(50),
	estado VARCHAR(10),
	
	PRIMARY KEY(codigo)
);

INSERT INTO pessoa(nome, ativo, lagradouro, numero, complemento, bairro, cep, cidade, estado) 
VALUES('Francisco Ricardo', true, 'Rua 13 de agosto', '150', 'Nao tem', 'JD. Dom Juan', '13015301', 'Campinas', 'SP');

INSERT INTO pessoa(nome, ativo, lagradouro, numero, complemento, bairro, cep, cidade, estado) 
VALUES('Mauricio Ricardo', true, 'Rua 13 de agosto', '150', 'Nao tem', 'JD. Dom Juan', '13015301', 'Campinas', 'SP');


INSERT INTO pessoa(nome, ativo, lagradouro, numero, complemento, bairro, cep, cidade, estado) 
VALUES('Aura Vortex', true, 'Rua 13 de agosto', '150', 'Nao tem', 'JD. Dom Juan', '13015301', 'Campinas', 'SP');

INSERT INTO pessoa(nome, ativo, lagradouro, numero, complemento, bairro, cep, cidade, estado) 
VALUES('Daydim Gordim', true, 'Rua 13 de agosto', '150', 'Nao tem', 'JD. Dom Juan', '13015301', 'Campinas', 'SP');