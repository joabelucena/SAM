/* Table:		EQUIPMENTS_TYPE
 * Prefix:		ETY
 * Date:		31/10/2014       
 * Author:		Joabe Lucena
 * References:	none
 * Obs:
 * */

/*TABLE*/
CREATE TABLE EQUIPMENTS_TYPE (
  ETY_ID INT NOT NULL PRIMARY KEY,
  ETY_DESCRIPTION VARCHAR(45) NOT NULL,
  ETY_DAILY_HOURS INT,
  USR_INSERT VARCHAR(45) NOT NULL,
  DTI_INSERT TIMESTAMP NOT NULL,
  USR_UPDATE VARCHAR(45) ,
  DTI_UPDATE TIMESTAMP);
COMMIT;


/*INDEXES*/
CREATE INDEX ix_ETY_INDEX1_idx ON EQUIPMENTS_TYPE(ETY_DESCRIPTION);


/*REFERENCES*/

/*GENERATOR*/
CREATE GENERATOR GEN_ETY_ID;
SET GENERATOR GEN_ETY_ID TO 0;


/*TRIGGER ID*/
SET TERM ^ ;
CREATE TRIGGER TRG_ETY_ID  FOR EQUIPMENTS_TYPE ACTIVE
BEFORE INSERT
AS
BEGIN
	if(new.ETY_ID IS NULL)
        then new.ETY_ID = GEN_ID(GEN_ETY_ID,1);
END^
SET TERM ; ^
COMMIT;


/*TRIGGER LOG*/
SET TERM ^ ;
CREATE TRIGGER TRG_ETY_LOG  FOR EQUIPMENTS_TYPE ACTIVE
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

