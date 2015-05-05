/* Table:		SERVICE_ORDER_JOBS
 * Prefix:		SOJ
 * Date:		04/11/2014
 * Author:		Joabe Lucena
 * References:	none
 * Obs:			Not Incremental ID
 * */

/*TABLE*/
CREATE TABLE SERVICE_ORDER_JOBS (
  SOJ_ID VARCHAR(2) NOT NULL PRIMARY KEY,
  SOJ_DESCRIPTION VARCHAR(45) NOT NULL,
  USR_INSERT VARCHAR(45) NOT NULL,
  DTI_INSERT TIMESTAMP NOT NULL,
  USR_UPDATE VARCHAR(45),
  DTI_UPDATE TIMESTAMP);
COMMIT;

/*INDEXES*/
CREATE INDEX ix_SOJ_INDEX1_idx ON SERVICE_ORDER_JOBS(SOJ_DESCRIPTION);

/*REFERENCES*/

/*GENERATOR*/

/*TRIGGER ID*/

/*TRIGGER LOG*/
SET TERM ^ ;
CREATE TRIGGER TRG_SOJ_LOG  FOR SERVICE_ORDER_JOBS ACTIVE
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

