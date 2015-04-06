/* Table:		SITES_TYPE
 * Prefix:		STY
 * Date:		03/11/2014
 * Author:		Joabe Lucena
 * References:	none
 * Obs:
 * */

/*TABLE*/
CREATE TABLE SITES_TYPE (
  STY_ID INT NOT NULL PRIMARY KEY,
  STY_DESCRIPTION VARCHAR(45) NOT NULL,
  USR_INSERT VARCHAR(45) NOT NULL,
  DTI_INSERT TIMESTAMP NOT NULL,
  USR_UPDATE VARCHAR(45),
  DTI_UPDATE TIMESTAMP ,
  DELETED CHAR(1) DEFAULT '');
COMMIT;

/*INDEXES*/
CREATE INDEX ix_STY_INDEX1_idx ON SITES_TYPE (STY_DESCRIPTION);

/*REFERENCES*/

/*GENERATOR*/
CREATE GENERATOR GEN_STY_ID;
SET GENERATOR GEN_STY_ID TO 0;

/*CONSTRAINTS*/
ALTER TABLE SITES_TYPE 
ADD CONSTRAINT uk_STY_UNIQUE1 UNIQUE (STY_DESCRIPTION);
COMMIT;


/*TRIGGER ID*/
SET TERM ^ ;
CREATE TRIGGER TRG_STY_ID  FOR SITES_TYPE ACTIVE
BEFORE INSERT
AS
BEGIN
	if(new.STY_ID IS NULL)
        then new.STY_ID = GEN_ID(GEN_STY_ID,1);
END^
SET TERM ; ^
COMMIT;


/*TRIGGER LOG*/
SET TERM ^ ;
CREATE TRIGGER TRG_STY_LOG  FOR SITES_TYPE ACTIVE
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
