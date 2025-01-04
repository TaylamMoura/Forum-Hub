CREATE TABLE topicos(

    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL UNIQUE,
    mensagem VARCHAR(500) NOT NULL UNIQUE,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'nao_respondido' CHECK (status IN ('nao_respondido', 'respondido')),
    autor VARCHAR(100) NOT NULL,
    curso VARCHAR(50) NOT NULL,

    PRIMARY KEY (id)
);