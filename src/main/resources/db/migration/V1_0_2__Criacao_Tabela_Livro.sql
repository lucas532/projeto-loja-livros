CREATE SEQUENCE SEQ_LIVRO_ID START WITH 1 INCREMENT BY 1;

CREATE TABLE LIVRO
(
    ID              NUMBER(20) NOT NULL,
    TITULO          VARCHAR2(100) NOT NULL,
    RESUMO          VARCHAR2(500) NOT NULL,
    SUMARIO         VARCHAR2(200),
    PRECO           DECIMAL(10, 5) NOT NULL,
    NUMERO_PAGINAS  NUMBER(20) NOT NULL,
    ISBN            VARCHAR2(100) NOT NULL,
    DATA_PUBLICACAO TIMESTAMP      NOT NULL,
    ID_AUTOR        NUMBER(20) NOT NULL,
    ID_CATEGORIA    NUMBER(20) NOT NULL,
    CONSTRAINT PK_LIVRO PRIMARY KEY (ID),
    CONSTRAINT FK_AUTOR FOREIGN KEY (ID_AUTOR) REFERENCES AUTOR (ID),
    CONSTRAINT FK_CATEGORIA FOREIGN KEY (ID_CATEGORIA) REFERENCES CATEGORIA (ID)
);