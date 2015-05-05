/*
 * Variaveis:
 * STATUS_RULES:	Tabela
 * SRU:	Prefixo da Tabela
 * SRU_ID:	Campo ID da Tabela
 * */

/* Table:		STATUS_RULES
 * Prefix:		SRU
 * Date:		04/11/2014
 * Author:		Joabe Lucena
 * References:	none
 * Obs:
 * */

/*TABLE*/
CREATE TABLE STATUS_RULES (
  SRU_ID			INT			NOT NULL PRIMARY KEY,
  SRU_ROLE_ID		INT			NOT NULL,
  SRU_CUR_STATUS_ID INT			NOT NULL,
  SRU_NXT_STATUS_ID INT			NOT NULL,
  SRU_LOG_REMARK	CHAR(1)		NOT NULL,
  USR_INSERT		VARCHAR(45) NOT NULL,
  DTI_INSERT		TIMESTAMP	NOT NULL,
  USR_UPDATE		VARCHAR(45),
  DTI_UPDATE		TIMESTAMP);
COMMIT;
  

/*INDEXES*/
CREATE INDEX fk_SRU_ROLE_idx ON STATUS_RULES(SRU_ROLE_ID);
CREATE INDEX fk_SRU_SOS_A_idx ON STATUS_RULES(SRU_CUR_STATUS_ID);
CREATE INDEX fk_SRU_SOS_B_idx ON STATUS_RULES(SRU_NXT_STATUS_ID);

/*REFERENCES*/
ALTER TABLE STATUS_RULES
ADD CONSTRAINT fk_SRU_ROLE_ref
    FOREIGN KEY (SRU_ROLE_ID)
    REFERENCES ROLE (id),

ADD CONSTRAINT fk_SRU_SOS_A_ref
    FOREIGN KEY (SRU_CUR_STATUS_ID)
    REFERENCES SERVICE_ORDER_STATUS (SOS_ID),

ADD CONSTRAINT fk_SRU_SOS_B_ref
    FOREIGN KEY (SRU_NXT_STATUS_ID)
    REFERENCES SERVICE_ORDER_STATUS (SOS_ID);
  
  
/*GENERATOR*/
CREATE GENERATOR GEN_SRU_ID;
SET GENERATOR GEN_SRU_ID TO 0;


/*TRIGGER ID*/
SET TERM ^ ;
CREATE TRIGGER TRG_SRU_ID  FOR STATUS_RULES ACTIVE
BEFORE INSERT
AS
BEGIN
	if(new.SRU_ID IS NULL)
        then new.SRU_ID = GEN_ID(GEN_SRU_ID,1);
END^
SET TERM ; ^
COMMIT;


/*TRIGGER LOG*/
SET TERM ^ ;
CREATE TRIGGER TRG_SRU_LOG  FOR STATUS_RULES ACTIVE
BEFORE INSERT OR UPDATE
AS
BEGIN
	if(inserting)
        then new.DTI_INSERT = CURRENT_TIMESTAMP;
    if(updating)
        then new.DTI_UPDATE = CURRENT_TIMESTAMP;
END^
SET TERM ; ^
COMMIT;

/*POPULATE TABLE*/
-- STATUS: 1
INSERT INTO STATUS_RULES (SRU_ROLE_ID,SRU_LOG_REMARK,SRU_CUR_STATUS_ID,SRU_NXT_STATUS_ID,USR_INSERT)
SELECT
	ID
	,'S'
	,(SELECT SOS_ID FROM SERVICE_ORDER_STATUS WHERE SOS_DESCRIPTION = 'NOVA')
	,(SELECT SOS_ID FROM SERVICE_ORDER_STATUS WHERE SOS_DESCRIPTION = 'ATRIBUIDA')
	,'SYSTEM'
FROM
	ROLE
WHERE
	ROLENAME = 'CCO'
	OR ROLENAME LIKE 'BASE%';

-- STATUS: 2
INSERT INTO STATUS_RULES (SRU_ROLE_ID,SRU_LOG_REMARK,SRU_CUR_STATUS_ID,SRU_NXT_STATUS_ID,USR_INSERT)
SELECT
	ID
	,'S'
	,(SELECT SOS_ID FROM SERVICE_ORDER_STATUS WHERE SOS_DESCRIPTION = 'REATRIBUIR')
	,(SELECT SOS_ID FROM SERVICE_ORDER_STATUS WHERE SOS_DESCRIPTION = 'ATRIBUIDA')
	,'SYSTEM'
FROM
	ROLE
WHERE
	ROLENAME = 'CCO'
	OR ROLENAME LIKE 'BASE%';

-- STATUS: 3
INSERT INTO STATUS_RULES (SRU_ROLE_ID,SRU_LOG_REMARK,SRU_CUR_STATUS_ID,SRU_NXT_STATUS_ID,USR_INSERT)
SELECT
	ID
	,'S'
	,(SELECT SOS_ID FROM SERVICE_ORDER_STATUS WHERE SOS_DESCRIPTION = 'ATRIBUIDA')
	,(SELECT SOS_ID FROM SERVICE_ORDER_STATUS WHERE SOS_DESCRIPTION = 'EM ANALISE')
	,'SYSTEM'
FROM
	ROLE
WHERE
	ROLENAME LIKE 'BASE%'
	OR ROLENAME LIKE 'OPERACAO%';

-- STATUS: 4
INSERT INTO STATUS_RULES (SRU_ROLE_ID,SRU_LOG_REMARK,SRU_CUR_STATUS_ID,SRU_NXT_STATUS_ID,USR_INSERT)
SELECT
	ID
	,'S'
	,(SELECT SOS_ID FROM SERVICE_ORDER_STATUS WHERE SOS_DESCRIPTION = 'EM ANALISE')
	,(SELECT SOS_ID FROM SERVICE_ORDER_STATUS WHERE SOS_DESCRIPTION = 'REJEITADO')
	,'SYSTEM'
FROM
	ROLE
WHERE
	ROLENAME LIKE 'OPERACAO%';
	
-- STATUS: 5
INSERT INTO STATUS_RULES (SRU_ROLE_ID,SRU_LOG_REMARK,SRU_CUR_STATUS_ID,SRU_NXT_STATUS_ID,USR_INSERT)
SELECT
	ID
	,'S'
	,(SELECT SOS_ID FROM SERVICE_ORDER_STATUS WHERE SOS_DESCRIPTION = 'REJEITADO')
	,(SELECT SOS_ID FROM SERVICE_ORDER_STATUS WHERE SOS_DESCRIPTION = 'REABERTO')
	,'SYSTEM'
FROM
	ROLE
WHERE
	ROLENAME = 'CCO'
	OR ROLENAME LIKE 'BASE%';

-- STATUS: 6
INSERT INTO STATUS_RULES (SRU_ROLE_ID,SRU_LOG_REMARK,SRU_CUR_STATUS_ID,SRU_NXT_STATUS_ID,USR_INSERT)
SELECT
	ID
	,'S'
	,(SELECT SOS_ID FROM SERVICE_ORDER_STATUS WHERE SOS_DESCRIPTION = 'REABERTO')
	,(SELECT SOS_ID FROM SERVICE_ORDER_STATUS WHERE SOS_DESCRIPTION = 'ATRIBUIDA')
	,'SYSTEM'
FROM
	ROLE
WHERE
	ROLENAME = 'CCO'
	OR ROLENAME LIKE 'BASE%';

-- STATUS: 7
INSERT INTO STATUS_RULES (SRU_ROLE_ID,SRU_LOG_REMARK,SRU_CUR_STATUS_ID,SRU_NXT_STATUS_ID,USR_INSERT)
SELECT
	ID
	,'S'
	,(SELECT SOS_ID FROM SERVICE_ORDER_STATUS WHERE SOS_DESCRIPTION = 'EM ANALISE')
	,(SELECT SOS_ID FROM SERVICE_ORDER_STATUS WHERE SOS_DESCRIPTION = 'AGUARDANDO AUTORIZACAO')
	,'SYSTEM'
FROM
	ROLE
WHERE
	ROLENAME LIKE 'OPERACAO%';

-- STATUS: 8
INSERT INTO STATUS_RULES (SRU_ROLE_ID,SRU_LOG_REMARK,SRU_CUR_STATUS_ID,SRU_NXT_STATUS_ID,USR_INSERT)
SELECT
	ID
	,'S'
	,(SELECT SOS_ID FROM SERVICE_ORDER_STATUS WHERE SOS_DESCRIPTION = 'AGUARDANDO AUTORIZACAO')
	,(SELECT SOS_ID FROM SERVICE_ORDER_STATUS WHERE SOS_DESCRIPTION = 'ACESSO LIBERADO')
	,'SYSTEM'
FROM
	ROLE
WHERE
	ROLENAME = 'CCO'
	OR ROLENAME LIKE 'BASE%';


-- STATUS: 9
INSERT INTO STATUS_RULES (SRU_ROLE_ID,SRU_LOG_REMARK,SRU_CUR_STATUS_ID,SRU_NXT_STATUS_ID,USR_INSERT)
SELECT
	ID
	,'S'
	,(SELECT SOS_ID FROM SERVICE_ORDER_STATUS WHERE SOS_DESCRIPTION = 'ACESSO LIBERADO')
	,(SELECT SOS_ID FROM SERVICE_ORDER_STATUS WHERE SOS_DESCRIPTION = 'EM ANDAMENTO')
	,'SYSTEM'
FROM
	ROLE
WHERE
	ROLENAME LIKE 'OPERACAO%';

-- STATUS: 10
INSERT INTO STATUS_RULES (SRU_ROLE_ID,SRU_LOG_REMARK,SRU_CUR_STATUS_ID,SRU_NXT_STATUS_ID,USR_INSERT)
SELECT
	ID
	,'S'
	,(SELECT SOS_ID FROM SERVICE_ORDER_STATUS WHERE SOS_DESCRIPTION = 'EM ANDAMENTO')
	,(SELECT SOS_ID FROM SERVICE_ORDER_STATUS WHERE SOS_DESCRIPTION = 'EM ESPERA')
	,'SYSTEM'
FROM
	ROLE
WHERE
	ROLENAME LIKE 'BASE%'
	OR ROLENAME LIKE 'OPERACAO%';

-- STATUS: 10.1
INSERT INTO STATUS_RULES (SRU_ROLE_ID,SRU_LOG_REMARK,SRU_CUR_STATUS_ID,SRU_NXT_STATUS_ID,USR_INSERT)
SELECT
	ID
	,'S'
	,(SELECT SOS_ID FROM SERVICE_ORDER_STATUS WHERE SOS_DESCRIPTION = 'EM ESPERA')
	,(SELECT SOS_ID FROM SERVICE_ORDER_STATUS WHERE SOS_DESCRIPTION = 'EM ANDAMENTO')
	,'SYSTEM'
FROM
	ROLE
WHERE
	ROLENAME LIKE 'BASE%'
	OR ROLENAME LIKE 'OPERACAO%';

-- STATUS: 11
INSERT INTO STATUS_RULES (SRU_ROLE_ID,SRU_LOG_REMARK,SRU_CUR_STATUS_ID,SRU_NXT_STATUS_ID,USR_INSERT)
SELECT
	ID
	,'S'
	,(SELECT SOS_ID FROM SERVICE_ORDER_STATUS WHERE SOS_DESCRIPTION = 'EM ESPERA')
	,(SELECT SOS_ID FROM SERVICE_ORDER_STATUS WHERE SOS_DESCRIPTION = 'REATRIBUIR')
	,'SYSTEM'
FROM
	ROLE
WHERE
	ROLENAME LIKE 'BASE%'
	OR ROLENAME LIKE 'OPERACAO%';

-- STATUS: 12
INSERT INTO STATUS_RULES (SRU_ROLE_ID,SRU_LOG_REMARK,SRU_CUR_STATUS_ID,SRU_NXT_STATUS_ID,USR_INSERT)
SELECT
	ID
	,'S'
	,(SELECT SOS_ID FROM SERVICE_ORDER_STATUS WHERE SOS_DESCRIPTION = 'EM ANDAMENTO')
	,(SELECT SOS_ID FROM SERVICE_ORDER_STATUS WHERE SOS_DESCRIPTION = 'CONCLUIDO')
	,'SYSTEM'
FROM
	ROLE
WHERE
	ROLENAME LIKE 'BASE%'
	OR ROLENAME LIKE 'OPERACAO%';

-- STATUS: 13
INSERT INTO STATUS_RULES (SRU_ROLE_ID,SRU_LOG_REMARK,SRU_CUR_STATUS_ID,SRU_NXT_STATUS_ID,USR_INSERT)
SELECT
	ID
	,'S'
	,(SELECT SOS_ID FROM SERVICE_ORDER_STATUS WHERE SOS_DESCRIPTION = 'CONCLUIDO')
	,(SELECT SOS_ID FROM SERVICE_ORDER_STATUS WHERE SOS_DESCRIPTION = 'REABERTO')
	,'SYSTEM'
FROM
	ROLE
WHERE
	ROLENAME = 'CCO'
	OR ROLENAME LIKE 'BASE%';

-- STATUS: 14
INSERT INTO STATUS_RULES (SRU_ROLE_ID,SRU_LOG_REMARK,SRU_CUR_STATUS_ID,SRU_NXT_STATUS_ID,USR_INSERT)
SELECT
	ID
	,'S'
	,(SELECT SOS_ID FROM SERVICE_ORDER_STATUS WHERE SOS_DESCRIPTION = 'CONCLUIDO')
	,(SELECT SOS_ID FROM SERVICE_ORDER_STATUS WHERE SOS_DESCRIPTION = 'FINALIZADO')
	,'SYSTEM'
FROM
	ROLE
WHERE
	ROLENAME = 'CCO'
	OR ROLENAME LIKE 'BASE%';

-- STATUS: 15
INSERT INTO STATUS_RULES (SRU_ROLE_ID,SRU_LOG_REMARK,SRU_CUR_STATUS_ID,SRU_NXT_STATUS_ID,USR_INSERT)
SELECT
	ID
	,'S'
	,(SELECT SOS_ID FROM SERVICE_ORDER_STATUS WHERE SOS_DESCRIPTION = 'REJEITADO')
	,(SELECT SOS_ID FROM SERVICE_ORDER_STATUS WHERE SOS_DESCRIPTION = 'FINALIZADO')
	,'SYSTEM'
FROM
	ROLE
WHERE
	ROLENAME = 'CCO'
	OR ROLENAME LIKE 'BASE%';

-- STATUS: 16
INSERT INTO STATUS_RULES (SRU_ROLE_ID,SRU_LOG_REMARK,SRU_CUR_STATUS_ID,SRU_NXT_STATUS_ID,USR_INSERT)
SELECT
	ID
	,'S'
	,(SELECT SOS_ID FROM SERVICE_ORDER_STATUS WHERE SOS_DESCRIPTION = 'EM ANALISE')
	,(SELECT SOS_ID FROM SERVICE_ORDER_STATUS WHERE SOS_DESCRIPTION = 'REATRIBUIR')
	,'SYSTEM'
FROM
	ROLE
WHERE
	ROLENAME LIKE 'BASE%'
	OR ROLENAME LIKE 'OPERACAO%';



/******* END OF TABLE CONFIG *******/

