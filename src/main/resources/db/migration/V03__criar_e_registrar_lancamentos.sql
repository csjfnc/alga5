CREATE TABLE lancamento(
	
	codigo	BIGSERIAL,
	descricao VARCHAR(100) NOT NULL,
	data_vencimento DATE NOT NULL,
	data_pagamento DATE,
	valor DECIMAL(10,2),
	tipo VARCHAR(20),
	codigo_categoria BIGINT NOT NULL,
	codigo_pessoa BIGINT NOT NULL,
	
	PRIMARY KEY(codigo),
	FOREIGN KEY(codigo_categoria) REFERENCES categoria(codigo),
	FOREIGN KEY(codigo_pessoa) REFERENCES pessoa(codigo)
);

INSERT INTO lancamento(descricao, data_vencimento, data_pagamento, valor, tipo, codigo_categoria, codigo_pessoa)
VALUES('Pagamnto de Despesas', '2018-08-05', '2018-08-01',  1400.00, 'DESPESA', 1, 1);

INSERT INTO lancamento(descricao, data_vencimento, data_pagamento, valor, tipo, codigo_categoria, codigo_pessoa)
VALUES('Salario', '2018-08-05', '2018-08-01', 3000.00, 'RECEITA', 4, 1);

INSERT INTO lancamento(descricao, data_vencimento, data_pagamento, valor, tipo, codigo_categoria, codigo_pessoa)
VALUES('Receita extra', '2018-08-05', '2018-08-01', 1400.00, 'RECEITA', 1, 1);

INSERT INTO lancamento(descricao, data_vencimento, data_pagamento, valor, tipo, codigo_categoria, codigo_pessoa)
VALUES('Carro Gastos', '2018-08-05', '2018-08-01', 1400.00, 'DESPESA', 1, 1);