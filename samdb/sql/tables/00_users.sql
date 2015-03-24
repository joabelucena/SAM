/** INICIO CONFIGURACAO USUARIO **/

/** Cria tabela de usuarios **/ 
CREATE TABLE users (
  id 			INT				NOT NULL
  ,username		VARCHAR(45)		NOT NULL
  ,password		VARCHAR(100)	NOT NULL
  ,email		VARCHAR(45)
  ,status		VARCHAR(45)		NOT NULL
  ,roleId		INT				NOT NULL
  ,PRIMARY KEY (id)
);
COMMIT;

--Chave Unica: username
ALTER TABLE users 
ADD CONSTRAINT uk_users_UNIQUE1 UNIQUE (username);
COMMIT;

/*****************************/



/** Cria Tabela de Perfis **/
CREATE TABLE role (
  id			INTEGER		NOT NULL
  ,roleName		VARCHAR(45)	NOT NULL
  ,PRIMARY KEY (id)
);
COMMIT;

-- Chave Unica: roleName
ALTER TABLE role 
ADD CONSTRAINT uk_role_UNIQUE1 UNIQUE (roleName);
COMMIT;

/*****************************/

/** Cria Tabela de Menus **/
CREATE TABLE MENU (
  id			INT			NOT NULL
  ,text			VARCHAR(45)	NOT NULL
  ,iconCls		VARCHAR(45)
  ,parent_id	INT
  ,className	VARCHAR(45) NOT NULL
  ,PRIMARY KEY (id)
);
COMMIT;

-- Chave Unica: id,parent_id
ALTER TABLE MENU 
ADD CONSTRAINT uk_MENU_UNIQUE1 UNIQUE (id,parent_id);
COMMIT;


/*GENERATOR*/
CREATE GENERATOR GEN_MNU_ID;
SET GENERATOR GEN_MNU_ID TO 0;


/*TRIGGER ID*/
SET TERM ^ ;
CREATE TRIGGER TRG_MNU_ID  FOR MENU ACTIVE
BEFORE INSERT
AS
BEGIN
	if(new.ID IS NULL)
        then new.ID = GEN_ID(GEN_MNU_ID,1);
END^
SET TERM ; ^
COMMIT;
/*****************************/


/** Cria Tabela de Funcionalidades do Sistema **/
CREATE TABLE SYS_FEATURES (
  id			INT			NOT NULL
  ,description	VARCHAR(45) NOT NULL
  ,url			VARCHAR(45)
  ,PRIMARY KEY (id));
COMMIT;

-- Chave Unica: id,parent_id
ALTER TABLE SYS_FEATURES 
ADD CONSTRAINT uk_SYS_FEATURES_UNIQUE1 UNIQUE (description);
COMMIT;


/*GENERATOR*/
CREATE GENERATOR GEN_FTR_ID;
SET GENERATOR GEN_FTR_ID TO 0;


/*TRIGGER ID*/
SET TERM ^ ;
CREATE TRIGGER TRG_FTR_ID  FOR MENU ACTIVE
BEFORE INSERT
AS
BEGIN
	if(new.ID IS NULL)
        then new.ID = GEN_ID(GEN_FTR_ID,1);
END^
SET TERM ; ^
COMMIT;

/*****************************************/
/** Cria Tabelas de Relacionamentos n:m **/
/*****************************************/
-- Perfis:Bases de Manutencao
CREATE TABLE ROLE_SERVICES_STATIONS (
  roleId		INT NOT NULL,
  stationId		INT NOT NULL,
  PRIMARY KEY (roleId, stationId));
COMMIT;

-- Perfis:Menus
CREATE TABLE ROLE_MENU (
  roleId		INT NOT NULL,
  menuId		INT NOT NULL,
  PRIMARY KEY (roleId, menuId));
COMMIT;

-- Perfis:Funcionalidades
CREATE TABLE ROLE_FEATURES (
  roleId		INT NOT NULL,
  featureId		INT NOT NULL,
  PRIMARY KEY (roleId, featureId));
COMMIT;


/*POPULATE TABLE*/

-- Tabela: role
INSERT INTO role VALUES (1,'ROOT');
INSERT INTO role VALUES (2,'ADMINISTRADOR');
INSERT INTO role VALUES (3,'CCO');
INSERT INTO role VALUES (4,'BASE A');
INSERT INTO role VALUES (5,'BASE B');
INSERT INTO role VALUES (6,'OPERACAO (BASE A)');
INSERT INTO role VALUES (7,'OPERACAO (BASE B)');

-- Tabela: users (Criptografia MD5)
INSERT INTO users VALUES (1,'SYSTEM','440e22afa3f863cb77844777f9f386e6',null,'ACTIVE',1);

-- Tabela: MENU
INSERT INTO MENU (TEXT,PARENT_ID,CLASSNAME)VALUES('Inicio',NULL,'');
INSERT INTO MENU (TEXT,PARENT_ID,CLASSNAME)VALUES('Alarmes',(SELECT ID FROM MENU WHERE TEXT = 'Inicio'),'alarmgrid');

-- Tabela: ROLE_MENU
INSERT INTO ROLE_MENU (MENUID, ROLEID) SELECT ID ,1 FROM MENU;

/** FIM CONFIGURACAO USUARIO **/