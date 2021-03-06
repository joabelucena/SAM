/* Table:		COUNTER_TYPE
 * Prefix:		CTY
 * Date:		03/11/2014
 * Author:		Joabe Lucena
 * References:	none
 * Obs:
 * */


/*
CREATE TABLE COUNTER_TYPE (
  CTY_ID INT NOT NULL PRIMARY KEY,
  CTY_DESCRIPTION VARCHAR(45) NOT NULL,
  USR_INSERT VARCHAR(45) NOT NULL,
  DTI_INSERT TIMESTAMP NOT NULL,
  USR_UPDATE VARCHAR(45) ,
  DTI_UPDATE TIMESTAMP);
COMMIT;


CREATE INDEX ix_CTY_INDEX1_idx ON COUNTER_TYPE (CTY_DESCRIPTION);


CREATE GENERATOR GEN_CTY_ID;
SET GENERATOR GEN_CTY_ID TO 0;



SET TERM ^ ;
CREATE TRIGGER TRG_CTY_ID  FOR COUNTER_TYPE ACTIVE
BEFORE INSERT
AS
BEGIN
	if(new.CTY_ID IS NULL)
        then new.CTY_ID = GEN_ID(GEN_CTY_ID,1);
END^
SET TERM ; ^
COMMIT;



SET TERM ^ ;
CREATE TRIGGER TRG_CTY_LOG  FOR COUNTER_TYPE ACTIVE
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
*/

/******* END OF TABLE CONFIG *******/

