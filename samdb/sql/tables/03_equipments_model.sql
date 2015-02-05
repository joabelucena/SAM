/* Table:		EQUIPMENTS_MODEL
 * Prefix:		EMO
 * Date:		31/10/2014       
 * Author:		Joabe Lucena
 * References:	none
 * Obs:
 * */

/*TABLE*/
CREATE TABLE EQUIPMENTS_MODEL (
  EMO_ID INT NOT NULL PRIMARY KEY,
  EMO_DESCRIPTION VARCHAR(45) NOT NULL,
  EMO_PROTOCOL_ID INT NOT NULL,
  USR_INSERT VARCHAR(45) NOT NULL,
  DTI_INSERT TIMESTAMP NOT NULL,
  USR_UPDATE VARCHAR(45),
  DTI_UPDATE TIMESTAMP,
  DELETED CHAR(1) DEFAULT '');
COMMIT;
  
/*INDEXES*/
CREATE INDEX ix_EMO_INDEX1_idx ON EQUIPMENTS_MODEL(EMO_DESCRIPTION);
CREATE INDEX fk_EMO_EPR_idx ON EQUIPMENTS_MODEL(EMO_PROTOCOL_ID);

/*REFERENCES*/
ALTER TABLE EQUIPMENTS_MODEL
ADD CONSTRAINT fk_EMO_EPR_ref
    FOREIGN KEY (EMO_PROTOCOL_ID)
    REFERENCES EQUIPMENTS_PROTOCOL (EPR_ID);

/*GENERATOR*/
CREATE GENERATOR GEN_EMO_ID;
SET GENERATOR GEN_EMO_ID TO 0;


/*TRIGGER ID*/
SET TERM ^ ;
CREATE TRIGGER TRG_EMO_ID  FOR EQUIPMENTS_MODEL ACTIVE
BEFORE INSERT
AS
BEGIN
	if(new.EMO_ID IS NULL)
        then new.EMO_ID = GEN_ID(GEN_EMO_ID,1);
END^
SET TERM ; ^
COMMIT;


/*TRIGGER LOG*/
SET TERM ^ ;
CREATE TRIGGER TRG_EMO_LOG  FOR EQUIPMENTS_MODEL ACTIVE
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

