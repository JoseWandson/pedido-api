CREATE TABLE pedido
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    numero_controle BIGINT NOT NULL,
    data_cadastro DATE NOT NULL,
    nome_produto VARCHAR(20) NOT NULL,
    valor_produto DECIMAL(10,2) NOT NULL,
    quantidade_produto INT NOT NULL,
    codigo_cliente BIGINT NOT NULL,
    valor_total DECIMAL(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;