/*
 * Variaveis:
 * <TABELA>:	Tabela
 * <PREFIXO>:	Prefixo da Tabela
 * <CAMPO_ID>:	Campo ID da Tabela
 * */

/* Table:		<TABELA>
 * Prefix:		<PREFIXO>
 * Date:		04/11/2014
 * Author:		Joabe Lucena
 * References:	none
 * Obs:
 * */

/*TABLE*/

/*INDEXES*/
  
/*REFERENCES*/

/*GENERATOR*/
CREATE GENERATOR GEN_<PREFIXO>_ID;
SET GENERATOR GEN_<PREFIXO>_ID TO 0;


/*TRIGGER ID*/
SET TERM ^ ;
CREATE TRIGGER TRG_<PREFIXO>_ID  FOR <TABELA> ACTIVE
BEFORE INSERT
AS
BEGIN
	if(new.<CAMPO_ID> IS NULL)
        then new.<CAMPO_ID> = GEN_ID(GEN_<PREFIXO>_ID,1);
END^
SET TERM ; ^
COMMIT;


/*TRIGGER LOG*/
SET TERM ^ ;
CREATE TRIGGER TRG_<PREFIXO>_LOG  FOR <TABELA> ACTIVE
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

