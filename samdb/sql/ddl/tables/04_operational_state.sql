/*
 * Variaveis:
 * OPERATIONAL_STATE:	Tabela
 * OST:	Prefixo da Tabela
 * <CAMPO_ID>:	Campo ID da Tabela
 * */

/* Table:		OPERATIONAL_STATE
 * Prefix:		OST
 * Date:		04/11/2014
 * Author:		Joabe Lucena
 * References:	none
 * Obs:
 * */

/*TABLE*/
CREATE TABLE OPERATIONAL_STATE (
  OST_ID VARCHAR(20) NOT NULL,
  OST_MODEL_ID INT NOT NULL,
  OST_DESCRIPTION VARCHAR(45) NOT NULL,
  USR_INSERT VARCHAR(45) NOT NULL,
  DTI_INSERT TIMESTAMP NOT NULL,
  USR_UPDATE VARCHAR(45) ,
  DTI_UPDATE TIMESTAMP ,
  PRIMARY KEY (OST_ID, OST_MODEL_ID));
COMMIT;

/*INDEXES*/
CREATE INDEX fk_OST_EMO_idx ON OPERATIONAL_STATE(OST_MODEL_ID);

/*REFERENCES*/
ALTER TABLE OPERATIONAL_STATE
ADD CONSTRAINT fk_OST_EMO_ref
    FOREIGN KEY (OST_MODEL_ID)
    REFERENCES EQUIPMENTS_MODEL (EMO_ID);

/*GENERATOR*/

/*TRIGGER ID*/

/*TRIGGER LOG*/
SET TERM ^ ;
CREATE TRIGGER TRG_OST_LOG  FOR OPERATIONAL_STATE ACTIVE
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


/******* END OF TABLE CONFIG *******/

