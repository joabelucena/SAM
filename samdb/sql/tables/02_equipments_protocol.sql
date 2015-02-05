/* Table:		EQUIPMENTS_PROTOCOL
 * Prefix:		EPR
 * Date:		09/12/2014
 * Author:		Joabe Lucena
 * References:	none
 * Obs:
 * */

/*TABLE*/
CREATE TABLE EQUIPMENTS_PROTOCOL (
  EPR_ID INT NOT NULL PRIMARY KEY,
  EPR_DESCRIPTION VARCHAR(45) NOT NULL,
  USR_INSERT VARCHAR(45) NOT NULL,
  DTI_INSERT TIMESTAMP NOT NULL,
  USR_UPDATE VARCHAR(45) ,
  DTI_UPDATE TIMESTAMP,
  DELETED CHAR(1) DEFAULT '');
COMMIT;

/*INDEXES*/
  
/*REFERENCES*/

/*GENERATOR*/
CREATE GENERATOR GEN_EPR_ID;
SET GENERATOR GEN_EPR_ID TO 0;


/*TRIGGER ID*/
SET TERM ^ ;
CREATE TRIGGER TRG_EPR_ID  FOR EQUIPMENTS_PROTOCOL ACTIVE
BEFORE INSERT
AS
BEGIN
	if(new.EPR_ID IS NULL)
        then new.EPR_ID = GEN_ID(GEN_EPR_ID,1);
END^
SET TERM ; ^
COMMIT;


/*TRIGGER LOG*/
SET TERM ^ ;
CREATE TRIGGER TRG_EPR_LOG  FOR EQUIPMENTS_PROTOCOL ACTIVE
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

