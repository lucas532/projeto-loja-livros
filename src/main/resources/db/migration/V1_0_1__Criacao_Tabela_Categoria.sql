CREATE SEQUENCE SEQ_CATEGORIA_ID START WITH 1 INCREMENT BY 1;

CREATE TABLE CATEGORIA
(
    ID        NUMBER(20) NOT NULL,
    NOME      VARCHAR2(100) NOT NULL,
    DESCRICAO VARCHAR2(500),
    CONSTRAINT PK_CATEGORIA PRIMARY KEY (ID)
);
