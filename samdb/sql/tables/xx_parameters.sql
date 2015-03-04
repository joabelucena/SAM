/* Table:		PARAMETERS
 * Prefix:		PAR
 * Date:		31/10/2014       
 * Author:		Joabe Lucena
 * References:	none
 * Obs:
 * */

/*TABLE*/
CREATE TABLE PARAMETERS (
  PAR_ID		INT			NOT NULL PRIMARY KEY
  ,PAR_NAME		VARCHAR(15) NOT NULL
  ,PAR_TYPE		VARCHAR(45) NOT NULL
  ,PAR_VALUE	VARCHAR(45) NOT NULL
  ,PAR_DESC		VARCHAR(45) NOT NULL
  ,USR_INSERT	VARCHAR(45) NOT NULL
  ,DTI_INSERT	TIMESTAMP   NOT NULL
  ,USR_UPDATE	VARCHAR(45)
  ,DTI_UPDATE	TIMESTAMP
  ,DELETED		CHAR(1) DEFAULT '');
COMMIT;


/*INDEXES*/
CREATE INDEX ix_PAR_INDEX1_idx ON PARAMETERS(PAR_NAME,PAR_TYPE,PAR_DESC);
CREATE INDEX ix_PAR_INDEX2_idx ON PARAMETERS(PAR_NAME,PAR_TYPE,PAR_VALUE);  


/*GENERATOR*/
CREATE GENERATOR GEN_PAR_ID;
SET GENERATOR GEN_PAR_ID TO 0;


/*TRIGGER ID*/
SET TERM ^ ;
CREATE TRIGGER TRG_PAR_ID  FOR PARAMETERS ACTIVE
BEFORE INSERT
AS
BEGIN
	if(new.PAR_ID IS NULL)
        then new.PAR_ID = GEN_ID(GEN_PAR_ID,1);
END^
SET TERM ; ^
COMMIT;



/*TRIGGER LOG*/
SET TERM ^ ;
CREATE TRIGGER TRG_PAR_LOG  FOR PARAMETERS ACTIVE
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
-- SYSTEM TECHNICAL PARAMETERS LIKE IP ADDRESSES, PORT NUMBERS, ETC. STARTS WITH: SYS
INSERT INTO PARAMETERS (PAR_NAME,PAR_TYPE,PAR_VALUE,PAR_DESC,USR_INSERT) VALUES ('SYS_IPSNMP'	,'C','10.114.0.150'	,'IP DO SERVIDOR SNMP DO SAM'		,'SYSTEM');
INSERT INTO PARAMETERS (PAR_NAME,PAR_TYPE,PAR_VALUE,PAR_DESC,USR_INSERT) VALUES ('SYS_PORTSNMP'	,'C','8888'			,'PORTA DO SERVIDOR SNMP DO SAM'	,'SYSTEM');

-- SYSTEM FUNCTIONALITY OR CUSTOM USERS SETTINGS LIKE FIRST SERVICE ORDER STATUS. STARTS WITH: SAM
INSERT INTO PARAMETERS (PAR_NAME,PAR_TYPE,PAR_VALUE,PAR_DESC,USR_INSERT) VALUES ('SAM_SOSTATUS'	,'C','NOVA'			,'PRIMEIRO STATUS DA OS'			,'SYSTEM');
COMMIT;

/******* END OF TABLE CONFIG *******/

