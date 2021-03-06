/* Table:		SERVICE_STATION
 * Prefix:		SST
 * Date:		17/03/2015
 * Author:		Joabe Lucena
 * References:	none
 * Obs:
 * */

/*TABLE*/
CREATE TABLE SERVICE_STATION (
  SST_ID			INT			PRIMARY KEY,
  SST_DESCRIPTION	VARCHAR(45),
  USR_INSERT		VARCHAR(45) NOT NULL,
  DTI_INSERT		TIMESTAMP	NOT NULL,
  USR_UPDATE		VARCHAR(45),
  DTI_UPDATE		TIMESTAMP);
COMMIT;
/*INDEXES*/
  
/*REFERENCES*/

/*GENERATOR*/
CREATE GENERATOR GEN_SST_ID;
SET GENERATOR GEN_SST_ID TO 0;


/*TRIGGER ID*/
SET TERM ^ ;
CREATE TRIGGER TRG_SST_ID  FOR SERVICE_STATION ACTIVE
BEFORE INSERT
AS
BEGIN
	if(new.SST_ID IS NULL)
        then new.SST_ID = GEN_ID(GEN_SST_ID,1);
END^
SET TERM ; ^
COMMIT;


/*TRIGGER LOG*/
SET TERM ^ ;
CREATE TRIGGER TRG_SST_LOG  FOR SERVICE_STATION ACTIVE
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

