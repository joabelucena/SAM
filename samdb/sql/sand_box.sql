/****************************************************/
-- Log da OS
SELECT
	A.SOL_ID
	,A.SOL_SERVICE_ORDER_ID
	,DE.SOS_DESCRIPTION
	,PARA.SOS_DESCRIPTION
FROM
	SERVICE_ORDER_LOG AS A
LEFT JOIN
	SERVICE_ORDER_STATUS AS DE
ON
	A.SOL_PRE_STATUS_ID = DE.SOS_ID
	AND
	DE.DELETED <> '*'
LEFT JOIN
	SERVICE_ORDER_STATUS AS PARA
ON
	A.SOL_CUR_STATUS_ID = PARA.SOS_ID
	AND
	DE.DELETED <> '*'
WHERE
	A.SOL_SERVICE_ORDER_ID = 1;

--Consulta Regras de mudança de Status
SELECT
	A.SRU_ID
	,ROLE.ROLENAME
	,A.SRU_CUR_STATUS_ID ||'-'|| DE.SOS_DESCRIPTION
	,A.SRU_NXT_STATUS_ID ||'-'|| PARA.SOS_DESCRIPTION
	,A.SRU_LOG_REMARK
FROM
	STATUS_RULES AS A
LEFT JOIN
	SERVICE_ORDER_STATUS AS DE
ON
	A.SRU_CUR_STATUS_ID = DE.SOS_ID
	AND
	DE.DELETED <> '*'
LEFT JOIN
	SERVICE_ORDER_STATUS AS PARA
ON
	A.SRU_NXT_STATUS_ID = PARA.SOS_ID
	AND
	DE.DELETED <> '*'
LEFT JOIN
	ROLE AS ROLE
ON
	A.SRU_ROLE_ID = ROLE.ID
WHERE
	SRU_CUR_STATUS_ID = 3
	AND
	SRU_NXT_STATUS_ID = 5;
/****************************************************/
--POSIÇÃO
ALTER TABLE SERVICE_ORDER ALTER SOR_EQUIPMENT_STOP POSITION 13;

-- ALTERANDO DE NOT NULL PRA NULL
ALTER TABLE SERVICE_ORDER
DROP SOR_EQUIPMENT_STOP ;

ALTER TABLE SERVICE_ORDER
ADD SOR_EQUIPMENT_STOP CHAR(1);


--ALTERANDO TAMANHO DE COLUNAS
ALTER TABLE PARAMETERS
ALTER COLUMN PAR_DESC TYPE VARCHAR(255)
/****************************************************/



/****************************************************/
/*INSERINDO ALARMES*/
INSERT INTO ALARMS (ALM_ID,ALM_DESCRIPTION,ALM_GROUP_ID, ALM_TYPE_ID, ALM_MODEL_ID, ALM_SEVERITY_ID, ALM_COUNTER_INC, USR_INSERT)
VALUES
('XPTOSEM','ALARME SEVERIDADE: -',1,1,1,0,1,'JOABRE');

INSERT INTO ALARMS (ALM_ID,ALM_DESCRIPTION,ALM_GROUP_ID, ALM_TYPE_ID, ALM_MODEL_ID, ALM_SEVERITY_ID, ALM_COUNTER_INC, USR_INSERT)
VALUES
('XPTOLEVE','ALARME SEVERIDADE: LEVE',1,1,1,1,1,'JOABRE');

INSERT INTO ALARMS (ALM_ID,ALM_DESCRIPTION,ALM_GROUP_ID, ALM_TYPE_ID, ALM_MODEL_ID, ALM_SEVERITY_ID, ALM_COUNTER_INC, USR_INSERT)
VALUES
('XPTONORMAL','ALARME SEVERIDADE: NORMAL',1,1,1,2,1,'JOABRE');

INSERT INTO ALARMS (ALM_ID,ALM_DESCRIPTION,ALM_GROUP_ID, ALM_TYPE_ID, ALM_MODEL_ID, ALM_SEVERITY_ID, ALM_COUNTER_INC, USR_INSERT)
VALUES
('XPTOGRAVE','ALARME SEVERIDADE: GRAVE',1,1,1,3,1,'JOABRE');



/****************************************************/
/**
 * adicionar colunas
 */
ALTER TABLE EQUIPMENTS_TYPE
ADD ETY_DAILY_HOURS INT NOT NULL;

-- Alterando posição de coluna
ALTER TABLE EQUIPMENTS_TYPE ALTER ETY_DAILY_HOURS POSITION 3;


/****************************************************/
/* EXTRAINDO INFORMACOES DE UM TIMESTAMP 
 *
 */
SELECT
	 SOR_ID
	 ,SOR_START
	,(SOR_END-SOR_START)*24 --RESULTADO EM DIAS CONVERTIDO PRA HORAS
	,EXTRACT(WEEKDAY FROM SOR_START)
	,EXTRACT(YEAR FROM SOR_START)
	,EXTRACT(MONTH FROM SOR_START)
	,EXTRACT(DAY FROM SOR_START)
	,EXTRACT(HOUR FROM SOR_START)
	,EXTRACT(MINUTE FROM SOR_START)
	,EXTRACT(SECOND FROM SOR_START)
FROM SERVICE_ORDER;



--MTTR MTBF MTTF
--fonte: http://professorph.com.br/word/?p=78
SELECT
	THIS.SOR_EQUIPMENT_ID
	,(CAST('2014-12-15 13:00:00.0'AS TIMESTAMP) - CAST('2014-12-01 13:00:00.0'AS TIMESTAMP))
													AS "TEMPO DISP.(DIAS)"
	,SUM((THIS.SOR_END - THIS.SOR_START))					AS "TEMPO PARADAS (DIAS)"
	,(CAST('2014-12-15 13:00:00.0'AS TIMESTAMP) - CAST('2014-12-01 13:00:00.0'AS TIMESTAMP))*B.ETY_DAILY_HOURS
													AS "TEMPO DISP.(HORAS)"
	,SUM((THIS.SOR_END - THIS.SOR_START))*B.ETY_DAILY_HOURS	AS "TEMPO PARADAS (HORAS)"
	,B.ETY_DAILY_HOURS									AS "HORAS DISP/DIA"
	,COUNT(*)											AS "QTD DE OCORR"
	
	,((CAST('2014-12-15 13:00:00.0'AS TIMESTAMP) - CAST('2014-12-01 13:00:00.0'AS TIMESTAMP))*B.ETY_DAILY_HOURS)/COUNT(*)
													AS "MTTF"
	,(SUM((THIS.SOR_END - THIS.SOR_START))*B.ETY_DAILY_HOURS)/COUNT(*)
													AS "MTTR"
	,((CAST('2014-12-15 13:00:00.0'AS TIMESTAMP) - CAST('2014-12-01 13:00:00.0'AS TIMESTAMP))*B.ETY_DAILY_HOURS)-((SUM((THIS.SOR_END - THIS.SOR_START))*B.ETY_DAILY_HOURS)/COUNT(*))
													AS "MTBF"
FROM
	SERVICE_ORDER THIS
LEFT JOIN
	EQUIPMENTS A
ON
	THIS.SOR_EQUIPMENT_ID = A.EQU_ID
	AND A.DELETED <> '*'
LEFT JOIN
	EQUIPMENTS_TYPE B
ON
	A.EQU_TYPE_ID = B.ETY_ID
	AND B.DELETED <> '*'
WHERE
	EXTRACT(WEEKDAY FROM SOR_START) NOT IN (0,6)
	AND THIS.DELETED <> '*'
	AND SOR_START >= CAST('2014-12-01 13:00:00.0'AS TIMESTAMP)
	AND SOR_END <= CAST('2014-12-15 13:00:00.0'AS TIMESTAMP)
GROUP BY
	THIS.SOR_EQUIPMENT_ID
	,(CAST('2014-12-15 13:00:00.0'AS TIMESTAMP) - CAST('2014-12-01 13:00:00.0'AS TIMESTAMP))
	,B.ETY_DAILY_HOURS


/****************************************************/
--inserir nova ordem de servico
INSERT INTO SERVICE_ORDER(SOR_TYPE_ID
,SOR_STATUS_ID
,SOR_TECHNICIAN_ID
,SOR_START_FORECAST
,SOR_END_FORECAST
,SOR_PRIORITY_ID
,SOR_EQUIPMENT_ID
,SOR_EQUIPMENT_STOP
,USR_INSERT)
VALUES
(1,1,'T00001'
,CAST('2015-01-22 17:30:00.0' AS TIMESTAMP)
,CAST('2015-01-22 17:30:00.0' AS TIMESTAMP),1,'10.114.0.150','S','TESTE')



--inserir nova ocorrencia da ordem de serviço
INSERT INTO SERVICE_ORDER_OCCURRENCES(
SOO_SERVICE_ORDER_ID
,SOO_JOB_ID
,SOO_TECHNICIAN_ID
,SOO_EVENT_TYPE
,SOO_START
,SOO_END
,USR_INSERT)
VALUES(
1
,'01'
,'T00001'
,1
,(SELECT CURRENT_TIMESTAMP FROM RDB$DATABASE)
,(SELECT CURRENT_TIMESTAMP FROM RDB$DATABASE)
,'TESTE')


--inserir novo 'tipo de servico'
INSERT INTO SERVICE_ORDER_JOBS (SOJ_ID,SOJ_DESCRIPTION,USR_INSERT) VALUES ('01','TROCA DE EQUIPAMENTO','TESTE');

--inserir novo tecnico
INSERT INTO TECHNICIAN (TEC_ID,TEC_NAME,TEC_SITE_ID,USR_INSERT) VALUES ('T00001','RIDELSON',1,'TESTE');
/****************************************************/

--asdasdasd

SELECT * FROM SITES WHERE SIT_PARENT_ID = 2;
SELECT * FROM SITES_TYPE;

SELECT
	SIT_ID
	,SIT_DESCRIPTION
	,SIT_SHORTNAME
	,SIT_PARENT_ID
FROM
	SITES
WHERE
SIT_ID IN (2,6,17,18);

SELECT * FROM EQUIPMENTS;

INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT) VALUES ('CABINE DE GUARITA','CAB',6,5,'joabe');


