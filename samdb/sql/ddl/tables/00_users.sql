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
ALTER TABLE USERS 
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
  ,TYPE			VARCHAR(02)	-- Colocar como rquired para su-menus
  ,URL			VARCHAR(45)
  ,CLASSNAME	VARCHAR(45)
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
INSERT INTO ROLE (ROLENAME) VALUES ('BASE BARREIROS');
INSERT INTO ROLE (ROLENAME) VALUES ('BASE VALONGO');
INSERT INTO ROLE (ROLENAME) VALUES ('OPERACAO (BARREIROS)');
INSERT INTO ROLE (ROLENAME) VALUES ('OPERACAO (VALONGO)');

INSERT INTO SYS_FEATURES(DESCRIPTION,URL) VALUES ('Reconhecer Eventos','/events/action/recognize');
INSERT INTO SYS_FEATURES(DESCRIPTION,URL) VALUES ('Normalizar Eventos','/events/action/normalize');

-- Tabela: users (Criptografia MD5)
INSERT INTO USERS (USERNAME,PASSWORD,STATUS,ROLEID) VALUES ('SYSTEM','440e22afa3f863cb77844777f9f386e6','ACTIVE',1);

-- Tabela: MENU

/********************************************************************************
 ********************************* TYPES ****************************************
 ********************************************************************************
 * PN | Panel Render (Ext Views)
 * SR | Spago Report (Spago Report for rendering in 'report.jsp' file)
 * EU | External Url (Render in a new TAB. Requires URL field) 
 * IU | Internal Url (Render inside SAM. Requires URL field)
 ********************************************************************************/ 


-- Sub-Menu: Alarmes
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES(NULL,NULL,'Inicio',NULL,'');
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES('PN',NULL,'Eventos e Alarmes'						,(SELECT ID FROM MENU WHERE TEXT = 'Inicio' AND PARENT_ID IS NULL),'eventgrid');

-- Sub-Menu: Ordem de Serviço
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES(NULL,NULL,'Ordem de Serviço',NULL,'');
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES('PN',NULL,'Ordens de Serviço'						,(SELECT ID FROM MENU WHERE TEXT = 'Ordem de Serviço' AND PARENT_ID IS NULL),'serviceordergrid');
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES('PN',NULL,'Tipos de Serviços'						,(SELECT ID FROM MENU WHERE TEXT = 'Ordem de Serviço' AND PARENT_ID IS NULL),'serviceorderjobgrid');

-- Sub-Menu: Cadastros
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES(NULL,NULL,'Cadastros',NULL,'');
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES('PN',NULL,'Regras de Mud. de Est.'					,(SELECT ID FROM MENU WHERE TEXT = 'Cadastros' AND PARENT_ID IS NULL),'serviceorderrulesgrid');
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES('PN',NULL,'Fabricante de Equipamentos'				,(SELECT ID FROM MENU WHERE TEXT = 'Cadastros' AND PARENT_ID IS NULL),'equipmentmanufacturergrid');
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES('PN',NULL,'Modelos de Equipamentos'					,(SELECT ID FROM MENU WHERE TEXT = 'Cadastros' AND PARENT_ID IS NULL),'equipmentmodelgrid');
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES('PN',NULL,'Tipos de Equipamentos'					,(SELECT ID FROM MENU WHERE TEXT = 'Cadastros' AND PARENT_ID IS NULL),'equipmenttypegrid');
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES('PN',NULL,'Técnicos'									,(SELECT ID FROM MENU WHERE TEXT = 'Cadastros' AND PARENT_ID IS NULL),'techniciangrid');
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES('PN',NULL,'Estado Operacional'						,(SELECT ID FROM MENU WHERE TEXT = 'Cadastros' AND PARENT_ID IS NULL),'operationalstategrid');
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES('PN',NULL,'Alarmes'									,(SELECT ID FROM MENU WHERE TEXT = 'Cadastros' AND PARENT_ID IS NULL),'alarmgrid');
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES('PN',NULL,'Tipos de Alarmes'							,(SELECT ID FROM MENU WHERE TEXT = 'Cadastros' AND PARENT_ID IS NULL),'alarmtypegrid');
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES('PN',NULL,'Grupos de Alarmes'						,(SELECT ID FROM MENU WHERE TEXT = 'Cadastros' AND PARENT_ID IS NULL),'alarmgroupgrid');
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES('PN',NULL,'Tipos de Locais'							,(SELECT ID FROM MENU WHERE TEXT = 'Cadastros' AND PARENT_ID IS NULL),'sitetypegrid');
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES('PN',NULL,'Locais'									,(SELECT ID FROM MENU WHERE TEXT = 'Cadastros' AND PARENT_ID IS NULL),'sitegrid');
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES('PN',NULL,'Status de OS'								,(SELECT ID FROM MENU WHERE TEXT = 'Cadastros' AND PARENT_ID IS NULL),'serviceorderstatusgrid');
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES('PN',NULL,'Equipamentos'								,(SELECT ID FROM MENU WHERE TEXT = 'Cadastros' AND PARENT_ID IS NULL),'equipmentsgrid');

-- Sub-Menu: Manutenção
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES(NULL,NULL,'Manutenção',NULL,'');
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES('PN',NULL,'Regras de Supervisao'						,(SELECT ID FROM MENU WHERE TEXT = 'Manutenção' AND PARENT_ID IS NULL),'taskgrid');
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES('PN',NULL,'Filtro de Alarmes'						,(SELECT ID FROM MENU WHERE TEXT = 'Manutenção' AND PARENT_ID IS NULL),'alarmfiltergrid');

-- Sub-Menu: Configuração
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES(NULL,NULL,'Configuração',NULL,'');
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES('PN',NULL,'Parâmetros'								,(SELECT ID FROM MENU WHERE TEXT = 'Configuração' AND PARENT_ID IS NULL),'parametergrid');
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES('PN',NULL,'Tipos de OS'								,(SELECT ID FROM MENU WHERE TEXT = 'Configuração' AND PARENT_ID IS NULL),'serviceordertypegrid');
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES('PN',NULL,'Niveis de Severidade'						,(SELECT ID FROM MENU WHERE TEXT = 'Configuração' AND PARENT_ID IS NULL),'severitygrid');
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES('PN',NULL,'Base de Manutenção'						,(SELECT ID FROM MENU WHERE TEXT = 'Configuração' AND PARENT_ID IS NULL),'stationgrid');
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES('PN',NULL,'Protocolos de Equipamentos'				,(SELECT ID FROM MENU WHERE TEXT = 'Configuração' AND PARENT_ID IS NULL),'equipmentprotocolgrid');
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES('PN',NULL,'Sub-Sistemas'								,(SELECT ID FROM MENU WHERE TEXT = 'Configuração' AND PARENT_ID IS NULL),'systemgrid');

-- Sub-Menu: Usuários
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES(NULL,NULL,'Usuários',NULL,'');
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES('PN',NULL,'Perfis de Usuário'						,(SELECT ID FROM MENU WHERE TEXT = 'Usuários' AND PARENT_ID IS NULL),'userrolegrid');
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES('PN',NULL,'Usuários'									,(SELECT ID FROM MENU WHERE TEXT = 'Usuários' AND PARENT_ID IS NULL),'usergrid');

-- Sub-Menu: Relatórios
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES(NULL,NULL,'Relatórios',NULL,'');

-- Não será liberado para os usuários
--INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES('EU','http://localhost:8180/SpagoBI','Analises'	,(SELECT ID FROM MENU WHERE TEXT = 'Relatórios' AND PARENT_ID IS NULL),NULL);

INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES('SR',NULL,'Comandos e Alarmes'						,(SELECT ID FROM MENU WHERE TEXT = 'Relatórios' AND PARENT_ID IS NULL),'ws__725415464');
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES('SR',NULL,'Manut. Preventivas'						,(SELECT ID FROM MENU WHERE TEXT = 'Relatórios' AND PARENT_ID IS NULL),'ws__647338078');
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES('SR',NULL,'Manut. Corretivas'						,(SELECT ID FROM MENU WHERE TEXT = 'Relatórios' AND PARENT_ID IS NULL),'ws__170415728');
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES('SR',NULL,'Manut. Preditivas'						,(SELECT ID FROM MENU WHERE TEXT = 'Relatórios' AND PARENT_ID IS NULL),'ws__933450806');
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES('SR',NULL,'Manut. Pendentes'							,(SELECT ID FROM MENU WHERE TEXT = 'Relatórios' AND PARENT_ID IS NULL),'ws__326002917');
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES('SR',NULL,'Ocorr. Operacionais'						,(SELECT ID FROM MENU WHERE TEXT = 'Relatórios' AND PARENT_ID IS NULL),'ws__483489033');
INSERT INTO MENU (TYPE,URL,TEXT,PARENT_ID,CLASSNAME)VALUES('SR',NULL,'Log do Sistema'							,(SELECT ID FROM MENU WHERE TEXT = 'Relatórios' AND PARENT_ID IS NULL),'ws__223028168');

-- Tabela: ROLE_MENU
INSERT INTO ROLE_MENU (MENUID, ROLEID) SELECT ID ,1 FROM MENU;

COMMIT;
/** FIM CONFIGURACAO USUARIO **/
