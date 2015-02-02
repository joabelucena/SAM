/** INICIO CONFIGURACAO USUARIO **/
-- Cria Tabela de Perfis
CREATE TABLE role (
  id INTEGER NOT NULL ,
  roleName varchar(45) DEFAULT NULL,
  PRIMARY KEY (id)
);
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

-- Popula tabela de usuarios (Criptografia MD5)
INSERT INTO users VALUES (1,'master','440e22afa3f863cb77844777f9f386e6','ACTIVE');

-- Cria tabela de relacionamentos
CREATE TABLE usersandroles (
  user_id INTEGER NOT NULL,
  role_id INTEGER NOT NULL,
  PRIMARY KEY (user_id,role_id)
);
COMMIT;

-- Popula tabela de relacionamentos
INSERT INTO usersandroles VALUES (1,1);

/** FIM CONFIGURACAO USUARIO **/
