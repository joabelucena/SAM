/* Table:		EQUIPMENTS
 * Prefix:		EQU
 * Date:		03/11/2014
 * Author:		Joabe Lucena
 * References:	none
 * Obs:			Not Incremental ID
 * */

/*TABLE*/
CREATE TABLE EQUIPMENTS (
  EQU_ID				VARCHAR(20)	NOT NULL PRIMARY KEY
  ,EQU_DESCRIPTION		VARCHAR(100)
  ,EQU_FIXED_ASSET		VARCHAR(20)
  ,EQU_SERVICE_TAG		VARCHAR(20)
  ,EQU_IP				VARCHAR(15)
  ,EQU_PARENT_ID		VARCHAR(20)
  ,EQU_TYPE_ID			INT			NOT NULL
  ,EQU_MODEL_ID			INT			NOT NULL
  ,EQU_MANUFACTURER_ID	INT			NOT NULL
  ,EQU_SITE_ID			INT			NOT NULL
  ,EQU_SYSTEM_ID		VARCHAR(10) NOT NULL
  ,EQU_MTBF_CALC		INT			DEFAULT 0
  ,EQU_MTBF_MANF		INT			DEFAULT 0
  ,EQU_INSTALL_DATE		TIMESTAMP	NOT NULL
  ,EQU_REMARK			VARCHAR(100)
  ,USR_INSERT			VARCHAR(45)	NOT NULL
  ,DTI_INSERT			TIMESTAMP	NOT NULL
  ,USR_UPDATE			VARCHAR(45)
  ,DTI_UPDATE			TIMESTAMP);
COMMIT;

/*INDEXES*/
CREATE INDEX fk_EQU_EMO_idx		ON EQUIPMENTS(EQU_MODEL_ID);
CREATE INDEX fk_EQU_ETY_idx		ON EQUIPMENTS(EQU_TYPE_ID);
CREATE INDEX fk_EQU_EMA_idx		ON EQUIPMENTS(EQU_MANUFACTURER_ID);
CREATE INDEX fk_EQU_SIT_idx		ON EQUIPMENTS(EQU_SITE_ID);
CREATE INDEX fk_EQU_SSY_idx		ON EQUIPMENTS(EQU_SYSTEM_ID);
CREATE INDEX fk_EQU_EQU_idx		ON EQUIPMENTS(EQU_PARENT_ID);
CREATE INDEX ix_EQU_INDEX1_idx	ON EQUIPMENTS(EQU_FIXED_ASSET);
CREATE INDEX ix_EQU_INDEX2_idx	ON EQUIPMENTS(EQU_SERVICE_TAG);
  
/*REFERENCES*/
ALTER TABLE EQUIPMENTS
ADD CONSTRAINT fk_EQU_EMA_ref
    FOREIGN KEY (EQU_MANUFACTURER_ID)
    REFERENCES EQUIPMENTS_MANUFACTURER (EMA_ID),
ADD CONSTRAINT fk_EQU_EMO_ref
    FOREIGN KEY (EQU_MODEL_ID)
    REFERENCES EQUIPMENTS_MODEL (EMO_ID),
ADD CONSTRAINT fk_EQU_ETY_ref
    FOREIGN KEY (EQU_TYPE_ID)
    REFERENCES EQUIPMENTS_TYPE (ETY_ID),
ADD CONSTRAINT fk_EQU_SIT_ref
    FOREIGN KEY (EQU_SITE_ID)
    REFERENCES SITES (SIT_ID),
ADD CONSTRAINT fk_EQU_SSY_ref
    FOREIGN KEY (EQU_SYSTEM_ID)
    REFERENCES SUB_SYSTEM (SSY_ID),
ADD CONSTRAINT fk_EQU_EQU_ref
    FOREIGN KEY (EQU_PARENT_ID)
    REFERENCES EQUIPMENTS (EQU_ID);

    
/*GENERATOR*/

/*TRIGGER ID*/

/*TRIGGER LOG*/
SET TERM ^ ;
CREATE TRIGGER TRG_EQU_LOG  FOR EQUIPMENTS ACTIVE
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
