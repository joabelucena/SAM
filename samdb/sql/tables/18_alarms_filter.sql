/* Table:		ALARMS_FILTER
 * Prefix:		AFI
 * Date:		03/11/2014
 * Author:		Joabe Lucena
 * References:	none
 * Obs:
 * */

/*TABLE*/
CREATE TABLE ALARMS_FILTER (
  AFI_ID			INT NOT NULL PRIMARY KEY,
  AFI_ALARM_ID		VARCHAR(20) NOT NULL,
  AFI_EQUIPMENT_ID	VARCHAR(20) NOT NULL,
  USR_INSERT		VARCHAR(45) NOT NULL,
  DTI_INSERT		TIMESTAMP NOT NULL,
  USR_UPDATE		VARCHAR(45) ,
  DTI_UPDATE		TIMESTAMP);
COMMIT;

/*INDEXES*/
CREATE INDEX fk_AFI_ALM_idx ON ALARMS_FILTER(AFI_ALARM_ID);
CREATE INDEX fk_AFI_EQU_idx ON ALARMS_FILTER(AFI_EQUIPMENT_ID);

/*REFERENCES*/
ALTER TABLE ALARMS_FILTER
ADD CONSTRAINT fk_AFI_ALM_ref
    FOREIGN KEY (AFI_ALARM_ID)
    REFERENCES ALARMS (ALM_ID),

ADD CONSTRAINT fk_AFI_EQU_ref
    FOREIGN KEY (AFI_EQUIPMENT_ID)
    REFERENCES EQUIPMENTS (EQU_ID);
  
/*GENERATOR*/
CREATE GENERATOR GEN_AFI_ID;
SET GENERATOR GEN_AFI_ID TO 0;


/*TRIGGER ID*/
SET TERM ^ ;
CREATE TRIGGER TRG_AFI_ID  FOR ALARMS_FILTER ACTIVE
BEFORE INSERT
AS
BEGIN
	if(new.AFI_ID IS NULL)
        then new.AFI_ID = GEN_ID(GEN_AFI_ID,1);
END^
SET TERM ; ^
COMMIT;


/*TRIGGER LOG*/
SET TERM ^ ;
CREATE TRIGGER TRG_AFI_LOG  FOR ALARMS_FILTER ACTIVE
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

