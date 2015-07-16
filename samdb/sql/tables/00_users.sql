/** INICIO CONFIGURACAO USUARIO **/

/*****************************/
/** Cria tabela de usuarios **/
/*****************************/
CREATE TABLE USERS (
   ID 			INT				NOT NULL
  ,USERNAME		VARCHAR(45)		NOT NULL
  ,PASSWORD		VARCHAR(100)	NOT NULL
  ,EMAIL		VARCHAR(45)
  ,STATUS		VARCHAR(45)		NOT NULL
  ,ROLEID		INT				NOT NULL
  ,PRIMARY KEY (ID)
);
COMMIT;

--Chave Unica: username
ALTER TABLE users 
ADD CONSTRAINT uk_USERS_UNIQUE1 UNIQUE (USERNAME);
COMMIT;


/*GENERATOR*/
CREATE GENERATOR GEN_USER_ID;
SET GENERATOR GEN_USER_ID TO 0;


/*TRIGGER ID*/
SET TERM ^ ;
CREATE TRIGGER TRG_USER_ID FOR USERS ACTIVE
BEFORE INSERT
AS
BEGIN
	if(new.ID IS NULL)
        then new.ID = GEN_ID(GEN_USER_ID,1);
END^
SET TERM ; ^
COMMIT;

/*****************************/



/*****************************/
/**  Cria tabela de Perfis  **/
/*****************************/
CREATE TABLE ROLE (
  ID			INTEGER		NOT NULL
  ,ROLENAME		VARCHAR(45)	NOT NULL
  ,PRIMARY KEY (ID)
);
COMMIT;

-- Chave Unica: roleName
ALTER TABLE ROLE 
ADD CONSTRAINT uk_ROLE_UNIQUE1 UNIQUE (ROLENAME);
COMMIT;

/*GENERATOR*/
CREATE GENERATOR GEN_ROLE_ID;
SET GENERATOR GEN_ROLE_ID TO 0;


/*TRIGGER ID*/
SET TERM ^ ;
CREATE TRIGGER TRG_ROLE_ID FOR ROLE ACTIVE
BEFORE INSERT
AS
BEGIN
	if(new.ID IS NULL)
        then new.ID = GEN_ID(GEN_ROLE_ID,1);
END^
SET TERM ; ^
COMMIT;

/*****************************/
/**   Cria tabela de Menus  **/
/*****************************/
CREATE TABLE MENU (
  ID			INT			NOT NULL
  ,TEXT			VARCHAR(45)	NOT NULL
  ,ICONCLS		VARCHAR(45)
  ,PARENT_ID	INT
  ,CLASSNAME	VARCHAR(45) NOT NULL
  ,PRIMARY KEY (ID)
);
COMMIT;

-- Chave Unica: id,parent_id
ALTER TABLE MENU 
ADD CONSTRAINT uk_MENU_UNIQUE1 UNIQUE (ID,PARENT_ID);
COMMIT;


/*GENERATOR*/
CREATE GENERATOR GEN_MENU_ID;
SET GENERATOR GEN_MENU_ID TO 0;


/*TRIGGER ID*/
SET TERM ^ ;
CREATE TRIGGER TRG_MENU_ID FOR MENU ACTIVE
BEFORE INSERT
AS
BEGIN
	if(new.ID IS NULL)
        then new.ID = GEN_ID(GEN_MENU_ID,1);
END^
SET TERM ; ^
COMMIT;
/*****************************/


/************************************/
/** Cria tabela de Funcionalidades **/
/************************************/
CREATE TABLE SYS_FEATURES (
  ID			INT			NOT NULL
  ,DESCRIPTION	VARCHAR(45) NOT NULL
  ,URL			VARCHAR(45)
  ,PRIMARY KEY (ID));
COMMIT;

-- Chave Unica: id,parent_id
ALTER TABLE SYS_FEATURES 
ADD CONSTRAINT uk_SYS_FEATURES_UNIQUE1 UNIQUE (DESCRIPTION);
COMMIT;


/*GENERATOR*/
CREATE GENERATOR GEN_FTR_ID;
SET GENERATOR GEN_FTR_ID TO 0;


/*TRIGGER ID*/
SET TERM ^ ;
CREATE TRIGGER TRG_FTR_ID FOR SYS_FEATURES ACTIVE
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
  ROLEID		INT NOT NULL,
  STATIONID		INT NOT NULL,
  PRIMARY KEY (ROLEID, STATIONID));
COMMIT;

-- Perfis:Menus
CREATE TABLE ROLE_MENU (
  ROLEID		INT NOT NULL,
  MENUID		INT NOT NULL,
  PRIMARY KEY (ROLEID, MENUID));
COMMIT;

-- Perfis:Funcionalidades
CREATE TABLE ROLE_FEATURES (
  ROLEID		INT NOT NULL,
  FEATUREID		INT NOT NULL,
  PRIMARY KEY (ROLEID, FEATUREID));
COMMIT;


/*POPULATE TABLE*/

-- Tabela: role
INSERT INTO ROLE (ROLENAME) VALUES ('ROOT');
INSERT INTO ROLE (ROLENAME) VALUES ('ADMINISTRADOR');
INSERT INTO ROLE (ROLENAME) VALUES ('CCO');
INSERT INTO ROLE (ROLENAME) VALUES ('BASE A');
INSERT INTO ROLE (ROLENAME) VALUES ('BASE B');
INSERT INTO ROLE (ROLENAME) VALUES ('OPERACAO (BASE A)');
INSERT INTO ROLE (ROLENAME) VALUES ('OPERACAO (BASE B)');

INSERT INTO SYS_FEATURES(DESCRIPTION,URL) VALUES ('Reconhecer Eventos','/events/action/recognize');
INSERT INTO SYS_FEATURES(DESCRIPTION,URL) VALUES ('Normalizar Eventos','/events/action/normalize');

-- Tabela: users (Criptografia MD5)
INSERT INTO USERS (USERNAME,PASSWORD,STATUS,ROLEID) VALUES ('SYSTEM','440e22afa3f863cb77844777f9f386e6','ACTIVE',1);

-- Tabela: MENU
-- Sub-Menu: Alarmes
INSERT INTO MENU (TEXT,PARENT_ID,CLASSNAME)VALUES('Inicio',NULL,'');
INSERT INTO MENU (TEXT,PARENT_ID,CLASSNAME)VALUES('Alarmes',(SELECT ID FROM MENU WHERE TEXT = 'Inicio'),'eventgrid');

-- Sub-Menu: Ordem de Serviço
INSERT INTO MENU (TEXT,PARENT_ID,CLASSNAME)VALUES('Ordem de Serviço',NULL,'');
INSERT INTO MENU (TEXT,PARENT_ID,CLASSNAME)VALUES('Ordens de Serviço'	,(SELECT ID FROM MENU WHERE TEXT = 'Ordem de Serviço'),'serviceordergrid');
INSERT INTO MENU (TEXT,PARENT_ID,CLASSNAME)VALUES('Tipos de Serviços'	,(SELECT ID FROM MENU WHERE TEXT = 'Ordem de Serviço'),'serviceorderjobgrid');

-- Sub-Menu: Cadastros
INSERT INTO MENU (TEXT,PARENT_ID,CLASSNAME)VALUES('Cadastros',NULL,'');
INSERT INTO MENU (TEXT,PARENT_ID,CLASSNAME)VALUES('Fabricante de Equipamentos'	,(SELECT ID FROM MENU WHERE TEXT = 'Cadastros'),'equipmentmanufacturergrid');
INSERT INTO MENU (TEXT,PARENT_ID,CLASSNAME)VALUES('Modelos de Equipamentos'		,(SELECT ID FROM MENU WHERE TEXT = 'Cadastros'),'equipmentmodelgrid');
INSERT INTO MENU (TEXT,PARENT_ID,CLASSNAME)VALUES('Tipos de Equipamentos'		,(SELECT ID FROM MENU WHERE TEXT = 'Cadastros'),'equipmenttypegrid');
INSERT INTO MENU (TEXT,PARENT_ID,CLASSNAME)VALUES('Técnicos'					,(SELECT ID FROM MENU WHERE TEXT = 'Cadastros'),'techniciangrid');
INSERT INTO MENU (TEXT,PARENT_ID,CLASSNAME)VALUES('Protocolos de Equipamentos'	,(SELECT ID FROM MENU WHERE TEXT = 'Cadastros'),'equipmentprotocolgrid');
INSERT INTO MENU (TEXT,PARENT_ID,CLASSNAME)VALUES('Estado Operacional'			,(SELECT ID FROM MENU WHERE TEXT = 'Cadastros'),'operationalstategrid');
INSERT INTO MENU (TEXT,PARENT_ID,CLASSNAME)VALUES('Sub-Sistemas'				,(SELECT ID FROM MENU WHERE TEXT = 'Cadastros'),'subsystemgrid');
INSERT INTO MENU (TEXT,PARENT_ID,CLASSNAME)VALUES('Base de Manutenção'			,(SELECT ID FROM MENU WHERE TEXT = 'Cadastros'),'stationgrid');
INSERT INTO MENU (TEXT,PARENT_ID,CLASSNAME)VALUES('Niveis de Severidade'		,(SELECT ID FROM MENU WHERE TEXT = 'Cadastros'),'severitygrid');
INSERT INTO MENU (TEXT,PARENT_ID,CLASSNAME)VALUES('Alarmes'						,(SELECT ID FROM MENU WHERE TEXT = 'Cadastros'),'alarmgrid');
INSERT INTO MENU (TEXT,PARENT_ID,CLASSNAME)VALUES('Tipos de Alarmes'			,(SELECT ID FROM MENU WHERE TEXT = 'Cadastros'),'alarmtypegrid');
INSERT INTO MENU (TEXT,PARENT_ID,CLASSNAME)VALUES('Grupos de Alarmes'			,(SELECT ID FROM MENU WHERE TEXT = 'Cadastros'),'alarmgroupgrid');
INSERT INTO MENU (TEXT,PARENT_ID,CLASSNAME)VALUES('Filtro de Alarmes'			,(SELECT ID FROM MENU WHERE TEXT = 'Cadastros'),'alarmfiltergrid');
INSERT INTO MENU (TEXT,PARENT_ID,CLASSNAME)VALUES('Tipos de Documentos'			,(SELECT ID FROM MENU WHERE TEXT = 'Cadastros'),'documenttypegrid');
INSERT INTO MENU (TEXT,PARENT_ID,CLASSNAME)VALUES('Tipos de Locais'				,(SELECT ID FROM MENU WHERE TEXT = 'Cadastros'),'sitetypegrid');
INSERT INTO MENU (TEXT,PARENT_ID,CLASSNAME)VALUES('Locais'						,(SELECT ID FROM MENU WHERE TEXT = 'Cadastros'),'sitegrid');
INSERT INTO MENU (TEXT,PARENT_ID,CLASSNAME)VALUES('Tipos de OS'					,(SELECT ID FROM MENU WHERE TEXT = 'Cadastros'),'serviceordertypegrid');
INSERT INTO MENU (TEXT,PARENT_ID,CLASSNAME)VALUES('Status de OS'				,(SELECT ID FROM MENU WHERE TEXT = 'Cadastros'),'serviceorderstatusgrid');
INSERT INTO MENU (TEXT,PARENT_ID,CLASSNAME)VALUES('Regras de Supervisao'		,(SELECT ID FROM MENU WHERE TEXT = 'Cadastros'),'taskgrid');


-- Tabela: ROLE_MENU
INSERT INTO ROLE_MENU (MENUID, ROLEID) SELECT ID ,1 FROM MENU;

/** FIM CONFIGURACAO USUARIO **/