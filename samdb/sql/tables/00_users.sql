/** INICIO CONFIGURACAO USUARIO **/
-- Cria Tabela de Perfis
CREATE TABLE role (
  id INTEGER NOT NULL ,
  roleName varchar(45) DEFAULT NULL,
  PRIMARY KEY (id)
);
COMMIT;

-- Constraint
ALTER TABLE role 
ADD CONSTRAINT uk_role_UNIQUE1 UNIQUE (roleName);
COMMIT;

-- Popula tabela de perfis
INSERT INTO role VALUES (1,'ROOT');
INSERT INTO role VALUES (2,'ADMINISTRADOR');
INSERT INTO role VALUES (3,'CCO');
INSERT INTO role VALUES (4,'BASE');
INSERT INTO role VALUES (5,'OPERACAO');

-- Cria tabela de usuarios 
CREATE TABLE users (
  id INTEGER NOT NULL ,
  username varchar(45) DEFAULT NULL,
  password varchar(100) DEFAULT NULL,
  status varchar(45) DEFAULT NULL,
  PRIMARY KEY (id)
);
COMMIT;

-- Constraint
ALTER TABLE users 
ADD CONSTRAINT uk_users_UNIQUE1 UNIQUE (username);
COMMIT;

-- Popula tabela de usuarios (Criptografia MD5)
INSERT INTO users VALUES (1,'SYSTEM','440e22afa3f863cb77844777f9f386e6','ACTIVE');

-- Cria tabelas de relacionamentos
CREATE TABLE usersandroles (
  user_id INTEGER NOT NULL,
  role_id INTEGER NOT NULL,
  PRIMARY KEY (user_id,role_id)
);
COMMIT;

-- Popula tabela de relacionamentos
INSERT INTO usersandroles VALUES (1,1);

CREATE TABLE usersandstations (
  userId	INT NOT NULL,
  stationId	INT NOT NULL);
COMMIT;


CREATE TABLE MENU (
  id INT NOT NULL,
  text VARCHAR(45) NOT NULL,
  iconCls VARCHAR(45),
  parent_id INT,
  className VARCHAR(45) NOT NULL,
  PRIMARY KEY (id)
);
COMMIT;

-- Constraint
ALTER TABLE MENU 
ADD CONSTRAINT uk_MENU_UNIQUE1 UNIQUE (id,parent_id);
COMMIT;

CREATE TABLE PERMISSIONS (
  menu_id INT NOT NULL,
  role_id INT NOT NULL,
  PRIMARY KEY (menu_id, role_id)
);
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

/*POPULATE TABLE*/

--MENU
INSERT INTO MENU (TEXT,PARENT_ID,CLASSNAME)VALUES('Inicio',NULL,'');
INSERT INTO MENU (TEXT,PARENT_ID,CLASSNAME)VALUES('Alarmes',(SELECT ID FROM MENU WHERE TEXT = 'Inicio'),'alarmgrid');

--PERMISSIONS
INSERT INTO PERMISSIONS SELECT ID ,1 FROM MENU;

/** FIM CONFIGURACAO USUARIO **/
