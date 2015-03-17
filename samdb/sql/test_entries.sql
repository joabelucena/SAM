--USUARIOS E CONFIGURACOES DE PERFIL
INSERT INTO users VALUES (2,'usercco'		,'9d53250aabeff892edbd344a99ef2862','ACTIVE');
INSERT INTO users VALUES (3,'userbase'		,'3f9fa67083985ac80905dade98bde2b1','ACTIVE');
INSERT INTO users VALUES (4,'useroperacao'	,'55c752df4385d72f98e201d3a5ed3c6b','ACTIVE');

--facilita a vida na hora de fazer login colocando senha: aaa
UPDATE users SET password='47bce5c74f589f4867dbd57e9ca9f808' WHERE username = 'SYSTEM';

--BASES DE MANUTENCAO
INSERT INTO SERVICE_STATION(SST_ID, SST_DESCRIPTION,USR_INSERT) VALUES(1,'BASE A','JOABE');
INSERT INTO SERVICE_STATION(SST_ID, SST_DESCRIPTION,USR_INSERT) VALUES(2,'BASE B','JOABE');
SET GENERATOR GEN_SST_ID TO 2;


INSERT INTO usersandroles (user_id, role_id) VALUES (2,3);
INSERT INTO usersandroles (user_id, role_id) VALUES (3,4);
INSERT INTO usersandroles (user_id, role_id) VALUES (4,5);

INSERT INTO PERMISSIONS SELECT ID ,2 FROM MENU;
INSERT INTO PERMISSIONS SELECT ID ,3 FROM MENU;
INSERT INTO PERMISSIONS SELECT ID ,4 FROM MENU;
INSERT INTO PERMISSIONS SELECT ID ,5 FROM MENU;


INSERT INTO usersandstations(userId, stationId) SELECT ID ,1 FROM USERS;
INSERT INTO usersandstations(userId, stationId) SELECT ID ,2 FROM USERS;

-- TIPOS DE LOCAIS
INSERT INTO SITES_TYPE (STY_ID,STY_DESCRIPTION,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE,DELETED) VALUES (1,'ESTACAO','joabe',{ts '2011-01-30 01:08:08.'},null,{ts '2011-02-06 02:55:34.'},' ');
INSERT INTO SITES_TYPE (STY_ID,STY_DESCRIPTION,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE,DELETED) VALUES (2,'PLATAFORMA','joabe',{ts '2011-01-30 01:08:08.'},null,{ts '2011-02-03 00:13:53.'},' ');
INSERT INTO SITES_TYPE (STY_ID,STY_DESCRIPTION,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE,DELETED) VALUES (5,'CABINE','joabe',{ts '2011-02-22 22:26:33.'},null,null,' ');
SET GENERATOR GEN_STY_ID TO 5;

--LOCAIS
INSERT INTO SITES (SIT_ID,SIT_STATION_ID,SIT_DESCRIPTION,SIT_SHORTNAME,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE,DELETED) VALUES (1,1,'WASHINGTON LUIS','WLU',null,1,'joabe',{ts '2011-01-30 01:14:40.'},null,{ts '2011-02-03 00:13:53.'},' ');
INSERT INTO SITES (SIT_ID,SIT_STATION_ID,SIT_DESCRIPTION,SIT_SHORTNAME,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE,DELETED) VALUES (2,1,'SAO VICENTE','SVC',null,1,'joabe',{ts '2011-02-06 03:22:13.'},null,null,' ');
INSERT INTO SITES (SIT_ID,SIT_STATION_ID,SIT_DESCRIPTION,SIT_SHORTNAME,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE,DELETED) VALUES (3,1,'PLATAFORMA A','PLA',1,2,'joabe',{ts '2011-02-06 03:22:13.'},null,null,' ');
INSERT INTO SITES (SIT_ID,SIT_STATION_ID,SIT_DESCRIPTION,SIT_SHORTNAME,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE,DELETED) VALUES (4,1,'PLATAFORMA B','PLB',1,2,'joabe',{ts '2011-02-06 03:22:13.'},null,null,' ');
INSERT INTO SITES (SIT_ID,SIT_STATION_ID,SIT_DESCRIPTION,SIT_SHORTNAME,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE,DELETED) VALUES (5,1,'PLATAFORMA A','PLA',2,2,'joabe',{ts '2011-02-06 03:22:13.'},null,null,' ');
INSERT INTO SITES (SIT_ID,SIT_STATION_ID,SIT_DESCRIPTION,SIT_SHORTNAME,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE,DELETED) VALUES (6,1,'PLATAFORMA B','PLB',2,2,'joabe',{ts '2011-02-06 03:22:13.'},null,null,' ');
INSERT INTO SITES (SIT_ID,SIT_STATION_ID,SIT_DESCRIPTION,SIT_SHORTNAME,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE,DELETED) VALUES (7,1,'BARRERO','BAR',null,1,'master',{ts '2011-02-06 03:30:28.'},null,null,' ');
INSERT INTO SITES (SIT_ID,SIT_STATION_ID,SIT_DESCRIPTION,SIT_SHORTNAME,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE,DELETED) VALUES (8,1,'PLATAFORMA C','PLC',1,2,'master',{ts '2011-02-06 03:31:07.'},null,null,' ');
INSERT INTO SITES (SIT_ID,SIT_STATION_ID,SIT_DESCRIPTION,SIT_SHORTNAME,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE,DELETED) VALUES (9,2,'MONGAGUA','MNG',null,1,'master',{ts '2011-02-06 03:53:43.'},null,null,' ');
INSERT INTO SITES (SIT_ID,SIT_STATION_ID,SIT_DESCRIPTION,SIT_SHORTNAME,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE,DELETED) VALUES (10,2,'MONGAGUA','MNG',null,1,'master',{ts '2011-02-06 03:54:02.'},null,null,' ');
INSERT INTO SITES (SIT_ID,SIT_STATION_ID,SIT_DESCRIPTION,SIT_SHORTNAME,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE,DELETED) VALUES (13,2,'MONGAGUA','MNG',null,1,'master',{ts '2011-02-06 04:17:44.'},null,null,' ');
INSERT INTO SITES (SIT_ID,SIT_STATION_ID,SIT_DESCRIPTION,SIT_SHORTNAME,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE,DELETED) VALUES (14,2,'MONGAGUA','MNG',7,1,'master',{ts '2011-02-06 04:47:38.'},null,null,' ');
INSERT INTO SITES (SIT_ID,SIT_STATION_ID,SIT_DESCRIPTION,SIT_SHORTNAME,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE,DELETED) VALUES (15,2,'PLATAFORMA C','PLC',10,2,'master',{ts '2011-02-09 06:37:04.'},null,null,' ');
INSERT INTO SITES (SIT_ID,SIT_STATION_ID,SIT_DESCRIPTION,SIT_SHORTNAME,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE,DELETED) VALUES (17,2,'CABINE DE SEGURANCA','CAB',6,5,'joabe',{ts '2011-02-23 03:41:29.'},null,null,' ');
INSERT INTO SITES (SIT_ID,SIT_STATION_ID,SIT_DESCRIPTION,SIT_SHORTNAME,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE,DELETED) VALUES (18,2,'CABINE DE GUARITA','CAB',6,5,'joabe',{ts '2011-02-24 04:44:50.'},null,null,' ');
SET GENERATOR GEN_SIT_ID TO 18;

-- TIPOS DE CONTADORES
INSERT INTO COUNTER_TYPE (CTY_ID,CTY_DESCRIPTION,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE,DELETED) VALUES (1,'HORA','SYSTEM',{ts '2011-02-03 00:08:07.'},null,null,' ');
SET GENERATOR GEN_CTY_ID TO 1;

-- FABRICANTES DE EQUIPMANENTOS
INSERT INTO EQUIPMENTS_MANUFACTURER (EMA_ID,EMA_DESCRIPTION,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE,DELETED) VALUES (1,'WESTERSTRAND','SYSTEM',{ts '2011-02-03 00:10:22.'},null,{ts '2011-02-07 03:23:47.'},' ');
SET GENERATOR GEN_EMA_ID TO 1;

-- MODELOS DE EQUIPMANENTOS
INSERT INTO EQUIPMENTS_MODEL (EMO_ID,EMO_DESCRIPTION,EMO_PROTOCOL_ID,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE,DELETED) VALUES (1,'QW-TIME III',3,'SYSTEM',{ts '2011-02-03 00:53:53.'},null,null,' ');
SET GENERATOR GEN_EMO_ID TO 1;

--SUB SISTEMAS
INSERT INTO SUB_SYSTEM (SSY_ID,SSY_DESCRIPTION,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE,DELETED) VALUES ('SMM','SISTEMA MULTIMIDIA','SYSTEM',{ts '2011-02-03 00:42:59.'},null,null,' ');
INSERT INTO SUB_SYSTEM (SSY_ID,SSY_DESCRIPTION,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE,DELETED) VALUES ('SSH','SISTEMA DE SINCRONISMO HORARIO','SYSTEM',{ts '2011-02-03 00:41:04.'},null,null,' ');

-- PREVISOES
INSERT INTO SERVICE_ORDER_FORECAST (SOF_SEVERITY_ID,SOF_SUB_SYSTEM_ID,SOF_START_FORECAST,SOF_END_FORECAST,USR_INSERT)
VALUES('1','SSH',30,120,'TESTE');
INSERT INTO SERVICE_ORDER_FORECAST (SOF_SEVERITY_ID,SOF_SUB_SYSTEM_ID,SOF_START_FORECAST,SOF_END_FORECAST,USR_INSERT)
VALUES('2','SSH',30,120,'TESTE');
INSERT INTO SERVICE_ORDER_FORECAST (SOF_SEVERITY_ID,SOF_SUB_SYSTEM_ID,SOF_START_FORECAST,SOF_END_FORECAST,USR_INSERT)
VALUES('3','SSH',30,120,'TESTE');

INSERT INTO SERVICE_ORDER_FORECAST (SOF_SEVERITY_ID,SOF_SUB_SYSTEM_ID,SOF_START_FORECAST,SOF_END_FORECAST,USR_INSERT)
VALUES('1','SMM',30,120,'TESTE');
INSERT INTO SERVICE_ORDER_FORECAST (SOF_SEVERITY_ID,SOF_SUB_SYSTEM_ID,SOF_START_FORECAST,SOF_END_FORECAST,USR_INSERT)
VALUES('2','SMM',30,120,'TESTE');
INSERT INTO SERVICE_ORDER_FORECAST (SOF_SEVERITY_ID,SOF_SUB_SYSTEM_ID,SOF_START_FORECAST,SOF_END_FORECAST,USR_INSERT)
VALUES('3','SMM',30,120,'TESTE');


-- TIPOS DE EQUIPMANENTOS
INSERT INTO EQUIPMENTS_TYPE (ETY_ID,ETY_DESCRIPTION,ETY_DAILY_HOURS,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE,DELETED) VALUES (1,'RELOGIO MESTRE',10,'SYSTEM',{ts '2011-02-03 00:32:07.'},null,{ts '2011-02-22 03:31:37.'},' ');
SET GENERATOR GEN_ETY_ID TO 1;

-- EQUIPAMENTOS
INSERT INTO EQUIPMENTS (EQU_ID,EQU_FIXED_ASSET,EQU_SERVICE_TAG,EQU_IP,EQU_TYPE_ID,EQU_MODEL_ID,EQU_MANUFACTURER_ID,EQU_SITE_ID,EQU_COUNTER_TYPE_ID,EQU_SYSTEM_ID,EQU_SOLV_TYPE,EQU_WARRANTY,EQU_COUNTER_QT,EQU_OID,EQU_MTBF_PREV,EQU_MTBF_CALC,EQU_MTBF_MANF,EQU_INSTALL_DATE,EQU_MANUFACTURE_DATE,EQU_ACQUIRED_DATE,USR_INSERT,EQU_REMARK,DTI_INSERT,USR_UPDATE,DTI_UPDATE,DELETED) VALUES ('10.114.0.150',null,null,'10.114.0.150',1,1,1,1,1,'SSH','0',0,0,'1.3.6.1.4.1.25281.1025.0',0,0,0,{ts '2015-01-09 00:00:00.'},{ts '2014-08-01 00:00:00.'},{ts '2014-12-01 00:00:00.'},'SYSTEM',null,{ts '2011-02-03 01:12:17.'},null,{ts '2011-02-15 00:17:39.'},' ');

-- GRUPO DE ALARMES
INSERT INTO ALARMS_GROUP (AGR_ID,AGR_DESCRIPTION,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE,DELETED) VALUES (1,'ALARMES DE ESTACAO','SYSTEM',{ts '2011-02-17 00:49:06.'},null,null,' ');
SET GENERATOR GEN_AGR_ID TO 1;

-- TIPOS DE ALARMES
INSERT INTO ALARMS_TYPE (ATY_ID,ATY_DESCRIPTION,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE,DELETED) VALUES (1,'ALARME DE FALHA DE EQUIPAMENTO','SYSTEM',{ts '2011-02-17 00:50:24.'},null,null,' ');
INSERT INTO ALARMS_TYPE (ATY_ID,ATY_DESCRIPTION,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE,DELETED) VALUES (2,'ALARME DE FALHA DE COMUNICAÇÃO','SYSTEM',{ts '2011-02-17 00:50:24.'},null,null,' ');
INSERT INTO ALARMS_TYPE (ATY_ID,ATY_DESCRIPTION,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE,DELETED) VALUES (3,'ALARME DE NORMALIZAÇÃO','SYSTEM',{ts '2011-02-17 00:50:24.'},null,null,' ');
SET GENERATOR GEN_ATY_ID TO 3;

-- ALARMES
INSERT INTO ALARMS (ALM_ID,ALM_DESCRIPTION,ALM_PARENT_ID,ALM_GROUP_ID,ALM_TYPE_ID,ALM_MODEL_ID,ALM_SEVERITY_ID,ALM_COUNTER_INC,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE,DELETED) VALUES ('XPTOSEM','ALARME SEVERIDADE: SEM SEVERIDADE'	,null,1,1,1,'0',1,'JOABRE',{ts '2011-01-30 23:32:28.'},null,null,' ');
INSERT INTO ALARMS (ALM_ID,ALM_DESCRIPTION,ALM_PARENT_ID,ALM_GROUP_ID,ALM_TYPE_ID,ALM_MODEL_ID,ALM_SEVERITY_ID,ALM_COUNTER_INC,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE,DELETED) VALUES ('XPTONORMAL','ALARME SEVERIDADE: NORMAL'		,null,1,1,1,'1',1,'JOABRE',{ts '2011-01-30 23:32:28.'},null,null,' ');
INSERT INTO ALARMS (ALM_ID,ALM_DESCRIPTION,ALM_PARENT_ID,ALM_GROUP_ID,ALM_TYPE_ID,ALM_MODEL_ID,ALM_SEVERITY_ID,ALM_COUNTER_INC,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE,DELETED) VALUES ('XPTOLEVE','ALARME SEVERIDADE: LEVE'			,null,1,1,1,'2',1,'JOABRE',{ts '2011-01-30 23:32:28.'},null,null,' ');
INSERT INTO ALARMS (ALM_ID,ALM_DESCRIPTION,ALM_PARENT_ID,ALM_GROUP_ID,ALM_TYPE_ID,ALM_MODEL_ID,ALM_SEVERITY_ID,ALM_COUNTER_INC,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE,DELETED) VALUES ('XPTOGRAVE','ALARME SEVERIDADE: GRAVE'			,null,1,1,1,'3',1,'JOABRE',{ts '2011-01-30 23:32:28.'},null,null,' ');

-- TIPOS DE SERVICO
INSERT INTO SERVICE_ORDER_JOBS (SOJ_ID,SOJ_DESCRIPTION,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE,DELETED) VALUES ('01','TROCA DE EQUIPAMENTO','TESTE',{ts '2011-02-20 04:04:58.'},null,null,' ');

-- TECNICOS
INSERT INTO TECHNICIAN (TEC_ID,TEC_NAME,TEC_SITE_ID,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE,DELETED) VALUES ('T00001','RIDELSON',1,'TESTE',{ts '2011-02-20 03:54:08.'},null,null,' ');

SELECT 'CONCLUIDO' FROM RDB$DATABASE;