-- DROP DATABASE IF EXISTS "ONEGATEDB";
DROP USER IF EXISTS ONEGATEADMIN;
CREATE USER ONEGATEADMIN WITH PASSWORD 'password';
CREATE DATABASE ONEGATEDB WITH template = template0 OWNER = ONEGATEADMIN;
\C ONEGATEDB;

ALTER DEFAULT PRIVILEGES GRANT ALL ON TABLES TO ONEGATEADMIN;
ALTER DEFAULT PRIVILEGES GRANT ALL ON SEQUENCES TO ONEGATEADMIN;

CREATE TABLE IF NOT EXISTS OG_ROLE
(
    ID          SERIAL
    CONSTRAINT ROLE_PK PRIMARY KEY,
    NAME        VARCHAR(250) NOT NULL UNIQUE,
    DESCRIPTION VARCHAR(250),
    STATUS      CHAR(3)      NOT NULL,
    CREATED_AT  TIMESTAMP    NOT NULL,
    CREATED_BY  VARCHAR(50)
    );

CREATE TABLE IF NOT EXISTS OG_ACCOUNT
(
    ID         SERIAL
    CONSTRAINT ACCOUNT_PK PRIMARY KEY,
    USERNAME   VARCHAR(250) NOT NULL UNIQUE,
    EMAIL      VARCHAR(250) NOT NULL UNIQUE,
    PASSWORD   VARCHAR(250) NOT NULL,
    STATUS     CHAR(3)      NOT NULL,
    ROLE_ID    INTEGER      NOT NULL,
    CREATED_AT TIMESTAMP    NOT NULL,
    CREATED_BY VARCHAR(50),
    CONSTRAINT ACCOUNT_ROLE_FK FOREIGN KEY (ROLE_ID) REFERENCES OG_ROLE (ID)
    );

CREATE TABLE IF NOT EXISTS OG_RESPONSE_CODE
(
    ID          SERIAL
    CONSTRAINT RESPONSE_CODE_PK PRIMARY KEY,
    CODE        VARCHAR(250) NOT NULL UNIQUE,
    DESCRIPTION VARCHAR(250),
    NAME_KH     VARCHAR(250),
    NAME_EN     VARCHAR(250),
    HTTP_STATUS VARCHAR(3),
    STATUS      CHAR(3)      NOT NULL,
    CREATED_AT  TIMESTAMP    NOT NULL,
    CREATED_BY  VARCHAR(50)
    );