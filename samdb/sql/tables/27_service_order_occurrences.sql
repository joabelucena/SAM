/* Table:		SERVICE_ORDER_OCCURRENCES
 * Prefix:		SOO
 * Date:		04/11/2014
 * Author:		Joabe Lucena
 * References:	none
 * Obs:
 * */

/*TABLE*/
CREATE TABLE SERVICE_ORDER_OCCURRENCES (
  SOO_ID				INT			NOT NULL
  ,SOO_SERVICE_ORDER_ID	INT
  ,SOO_SERVICE_ID		VARCHAR(2)	NOT NULL
  ,SOO_TECHNICIAN_ID	VARCHAR(6)	NOT NULL
  ,SOO_EVENT_TYPE		INT			NOT NULL
  ,SOO_START			TIMESTAMP	NOT NULL
  ,SOO_END				TIMESTAMP	NOT NULL
  ,SOO_REMARK			CHAR(120)
  ,USR_INSERT			VARCHAR(45)	NOT NULL
  ,DTI_INSERT			TIMESTAMP	NOT NULL
  ,USR_UPDATE			VARCHAR(45) 
  ,DTI_UPDATE			TIMESTAMP
  ,PRIMARY KEY (SOO_ID, SOO_SERVICE_ORDER_ID));
COMMIT;

/*INDEXES*/
CREATE INDEX fk_SOO_TEC_idx ON SERVICE_ORDER_OCCURRENCES(SOO_TECHNICIAN_ID);
CREATE INDEX fk_SOO_SOJ_idx ON SERVICE_ORDER_OCCURRENCES(SOO_SERVICE_ID);
CREATE INDEX fk_SOO_SOR_idx ON SERVICE_ORDER_OCCURRENCES(SOO_SERVICE_ORDER_ID);

  
/*REFERENCES*/
ALTER TABLE SERVICE_ORDER_OCCURRENCES
ADD CONSTRAINT fk_SOO_SOJ_ref
    FOREIGN KEY (SOO_SERVICE_ID)
    REFERENCES SERVICE_ORDER_JOBS (SOJ_ID),

ADD CONSTRAINT fk_SOO_TEC_ref
    FOREIGN KEY (SOO_TECHNICIAN_ID)
    REFERENCES TECHNICIAN (TEC_ID),

ADD CONSTRAINT fk_SOO_SOR_ref
    FOREIGN KEY (SOO_SERVICE_ORDER_ID)
    REFERENCES SERVICE_ORDER (SOR_ID);

/*GENERATOR*/
CREATE GENERATOR GEN_SOO_ID;
SET GENERATOR GEN_SOO_ID TO 0;


/*TRIGGER ID*/
SET TERM ^ ;
CREATE TRIGGER TRG_SOO_ID  FOR SERVICE_ORDER_OCCURRENCES ACTIVE
BEFORE INSERT
AS
BEGIN
	if(new.SOO_ID IS NULL)
        then new.SOO_ID = GEN_ID(GEN_SOO_ID,1);
END^
SET TERM ; ^
COMMIT;


/*TRIGGER LOG*/
SET TERM ^ ;
CREATE TRIGGER TRG_SOO_LOG  FOR SERVICE_ORDER_OCCURRENCES ACTIVE
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

