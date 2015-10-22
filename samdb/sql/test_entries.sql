--USUARIOS E CONFIGURACOES DE PERFIL
INSERT INTO USERS (USERNAME,PASSWORD,STATUS,ROLEID) VALUES ('USER_CCO'		,'47bce5c74f589f4867dbd57e9ca9f808','ACTIVE',3);
INSERT INTO USERS (USERNAME,PASSWORD,STATUS,ROLEID) VALUES ('USER_BASE_A'	,'47bce5c74f589f4867dbd57e9ca9f808','ACTIVE',4);
INSERT INTO USERS (USERNAME,PASSWORD,STATUS,ROLEID) VALUES ('USER_BASE_B'	,'47bce5c74f589f4867dbd57e9ca9f808','ACTIVE',5);
INSERT INTO USERS (USERNAME,PASSWORD,STATUS,ROLEID) VALUES ('USER_OPER_A'	,'47bce5c74f589f4867dbd57e9ca9f808','ACTIVE',6);
INSERT INTO USERS (USERNAME,PASSWORD,STATUS,ROLEID) VALUES ('USER_OPER_B'	,'47bce5c74f589f4867dbd57e9ca9f808','ACTIVE',7);


-- ACESSOS POR PERFIL
INSERT INTO ROLE_FEATURES(ROLEID,FEATUREID) SELECT ID,1 FROM ROLE; --RECONHECER
INSERT INTO ROLE_FEATURES(ROLEID,FEATUREID) SELECT ID,2 FROM ROLE; --NORMALIZAR

--facilita a vida na hora de fazer login colocando senha: aaa
UPDATE users SET password='47bce5c74f589f4867dbd57e9ca9f808' WHERE username = 'SYSTEM';

--BASES DE MANUTENCAO
INSERT INTO SERVICE_STATION(SST_ID, SST_DESCRIPTION,USR_INSERT) VALUES(1,'BASE A','JOABE');
INSERT INTO SERVICE_STATION(SST_ID, SST_DESCRIPTION,USR_INSERT) VALUES(2,'BASE B','JOABE');
SET GENERATOR GEN_SST_ID TO 2;



INSERT INTO ROLE_MENU (MENUID, ROLEID) SELECT ID ,2 FROM MENU;
INSERT INTO ROLE_MENU (MENUID, ROLEID) SELECT ID ,3 FROM MENU;
INSERT INTO ROLE_MENU (MENUID, ROLEID) SELECT ID ,4 FROM MENU;
INSERT INTO ROLE_MENU (MENUID, ROLEID) SELECT ID ,5 FROM MENU;
INSERT INTO ROLE_MENU (MENUID, ROLEID) SELECT ID ,6 FROM MENU;
INSERT INTO ROLE_MENU (MENUID, ROLEID) SELECT ID ,7 FROM MENU;


INSERT INTO ROLE_SERVICES_STATIONS(roleId, stationId) SELECT ID ,1 FROM ROLE;
INSERT INTO ROLE_SERVICES_STATIONS(roleId, stationId) SELECT ID ,2 FROM ROLE;

-- TIPOS DE LOCAIS
INSERT INTO SITES_TYPE (STY_ID,STY_DESCRIPTION,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES (1,'ESTACAO','joabe',{ts '2011-01-30 01:08:08.'},null,{ts '2011-02-06 02:55:34.'});
INSERT INTO SITES_TYPE (STY_ID,STY_DESCRIPTION,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES (2,'PLATAFORMA','joabe',{ts '2011-01-30 01:08:08.'},null,{ts '2011-02-03 00:13:53.'});
INSERT INTO SITES_TYPE (STY_ID,STY_DESCRIPTION,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES (5,'CABINE','joabe',{ts '2011-02-22 22:26:33.'},null,null);
SET GENERATOR GEN_STY_ID TO 5;

--LOCAIS
INSERT INTO SITES (SIT_ID,SIT_STATION_ID,SIT_DESCRIPTION,SIT_SHORTNAME,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES (1,1,'WASHINGTON LUIS','WLU',null,1,'joabe',{ts '2011-01-30 01:14:40.'},null,{ts '2011-02-03 00:13:53.'});
INSERT INTO SITES (SIT_ID,SIT_STATION_ID,SIT_DESCRIPTION,SIT_SHORTNAME,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES (2,1,'SAO VICENTE','SVC',null,1,'joabe',{ts '2011-02-06 03:22:13.'},null,null);
INSERT INTO SITES (SIT_ID,SIT_STATION_ID,SIT_DESCRIPTION,SIT_SHORTNAME,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES (3,1,'PLATAFORMA A','PLA',1,2,'joabe',{ts '2011-02-06 03:22:13.'},null,null);
INSERT INTO SITES (SIT_ID,SIT_STATION_ID,SIT_DESCRIPTION,SIT_SHORTNAME,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES (4,1,'PLATAFORMA B','PLB',1,2,'joabe',{ts '2011-02-06 03:22:13.'},null,null);
INSERT INTO SITES (SIT_ID,SIT_STATION_ID,SIT_DESCRIPTION,SIT_SHORTNAME,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES (5,1,'PLATAFORMA A','PLA',2,2,'joabe',{ts '2011-02-06 03:22:13.'},null,null);
INSERT INTO SITES (SIT_ID,SIT_STATION_ID,SIT_DESCRIPTION,SIT_SHORTNAME,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES (6,1,'PLATAFORMA B','PLB',2,2,'joabe',{ts '2011-02-06 03:22:13.'},null,null);
INSERT INTO SITES (SIT_ID,SIT_STATION_ID,SIT_DESCRIPTION,SIT_SHORTNAME,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES (7,1,'BARRERO','BAR',null,1,'master',{ts '2011-02-06 03:30:28.'},null,null);
INSERT INTO SITES (SIT_ID,SIT_STATION_ID,SIT_DESCRIPTION,SIT_SHORTNAME,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES (8,1,'PLATAFORMA C','PLC',1,2,'master',{ts '2011-02-06 03:31:07.'},null,null);
INSERT INTO SITES (SIT_ID,SIT_STATION_ID,SIT_DESCRIPTION,SIT_SHORTNAME,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES (9,2,'MONGAGUA','MNG',null,1,'master',{ts '2011-02-06 03:53:43.'},null,null);
INSERT INTO SITES (SIT_ID,SIT_STATION_ID,SIT_DESCRIPTION,SIT_SHORTNAME,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES (10,2,'MONGAGUA','MNG',null,1,'master',{ts '2011-02-06 03:54:02.'},null,null);
INSERT INTO SITES (SIT_ID,SIT_STATION_ID,SIT_DESCRIPTION,SIT_SHORTNAME,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES (13,2,'MONGAGUA','MNG',null,1,'master',{ts '2011-02-06 04:17:44.'},null,null);
INSERT INTO SITES (SIT_ID,SIT_STATION_ID,SIT_DESCRIPTION,SIT_SHORTNAME,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES (14,2,'MONGAGUA','MNG',7,1,'master',{ts '2011-02-06 04:47:38.'},null,null);
INSERT INTO SITES (SIT_ID,SIT_STATION_ID,SIT_DESCRIPTION,SIT_SHORTNAME,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES (15,2,'PLATAFORMA C','PLC',10,2,'master',{ts '2011-02-09 06:37:04.'},null,null);
INSERT INTO SITES (SIT_ID,SIT_STATION_ID,SIT_DESCRIPTION,SIT_SHORTNAME,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES (17,2,'CABINE DE SEGURANCA','CAB',6,5,'joabe',{ts '2011-02-23 03:41:29.'},null,null);
INSERT INTO SITES (SIT_ID,SIT_STATION_ID,SIT_DESCRIPTION,SIT_SHORTNAME,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES (18,2,'CABINE DE GUARITA','CAB',6,5,'joabe',{ts '2011-02-24 04:44:50.'},null,null);
SET GENERATOR GEN_SIT_ID TO 18;

-- FABRICANTES DE EQUIPMANENTOS
INSERT INTO EQUIPMENTS_MANUFACTURER (EMA_ID,EMA_DESCRIPTION,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES (1,'WESTERSTRAND','SYSTEM',{ts '2011-02-03 00:10:22.'},null,{ts '2011-02-07 03:23:47.'});
SET GENERATOR GEN_EMA_ID TO 1;

-- PROTOCOLOS
INSERT INTO EQUIPMENTS_PROTOCOL (EPR_DESCRIPTION,USR_INSERT)VALUES('SNMPv1','SYSTEM');
INSERT INTO EQUIPMENTS_PROTOCOL (EPR_DESCRIPTION,USR_INSERT)VALUES('SNMPv2c','SYSTEM');
INSERT INTO EQUIPMENTS_PROTOCOL (EPR_DESCRIPTION,USR_INSERT)VALUES('SNMPv3','SYSTEM');
INSERT INTO EQUIPMENTS_PROTOCOL (EPR_DESCRIPTION,USR_INSERT)VALUES('WEBSERVICES SOAP 1.1','SYSTEM');
INSERT INTO EQUIPMENTS_PROTOCOL (EPR_DESCRIPTION,USR_INSERT)VALUES('WEBSERVICES JSON','SYSTEM');
INSERT INTO EQUIPMENTS_PROTOCOL (EPR_DESCRIPTION,USR_INSERT)VALUES('WEBSERVICES SOAP MAESTRO','SYSTEM');


-- MODELOS DE EQUIPMANENTOS
INSERT INTO EQUIPMENTS_MODEL (EMO_ID,EMO_DESCRIPTION,EMO_PROTOCOL_ID,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES (1,'QW-TIME III',3,'SYSTEM',{ts '2011-02-03 00:53:53.'},null,null);
SET GENERATOR GEN_EMO_ID TO 1;

--SUB SISTEMAS
INSERT INTO SUB_SYSTEM (SSY_ID,SSY_DESCRIPTION,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES ('SMM','SISTEMA MULTIMIDIA','SYSTEM',{ts '2011-02-03 00:42:59.'},null,null);
INSERT INTO SUB_SYSTEM (SSY_ID,SSY_DESCRIPTION,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES ('SSH','SISTEMA DE SINCRONISMO HORARIO','SYSTEM',{ts '2011-02-03 00:41:04.'},null,null);
INSERT INTO SUB_SYSTEM (SSY_ID,SSY_DESCRIPTION,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES ('SCE','SISTEMA DE CONTROLE DE ENERGIA','SYSTEM',{ts '2011-02-03 00:41:04.'},null,null);

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
INSERT INTO EQUIPMENTS_TYPE (ETY_ID,ETY_DESCRIPTION,ETY_DAILY_HOURS,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES (1,'RELOGIO MESTRE',10,'SYSTEM',{ts '2011-02-03 00:32:07.'},null,{ts '2011-02-22 03:31:37.'});
SET GENERATOR GEN_ETY_ID TO 1;

-- EQUIPAMENTOS
INSERT INTO EQUIPMENTS (EQU_ID,EQU_OID,EQU_IP,EQU_TYPE_ID,EQU_MODEL_ID,EQU_MANUFACTURER_ID,EQU_SITE_ID,EQU_SYSTEM_ID,EQU_INSTALL_DATE,USR_INSERT) VALUES('10.114.0.150'	,'1.3.6.1.4.1.25281.1025.0','10.114.0.150'	,1,1,1,1,'SSH',(SELECT CURRENT_TIMESTAMP FROM RDB$DATABASE),'TESTE');
INSERT INTO EQUIPMENTS (EQU_ID,EQU_OID,EQU_IP,EQU_TYPE_ID,EQU_MODEL_ID,EQU_MANUFACTURER_ID,EQU_SITE_ID,EQU_SYSTEM_ID,EQU_INSTALL_DATE,USR_INSERT) VALUES('XPTO_01'		,'1.3.6.1.4.1.25281.1025.0',null			,1,1,1,1,'SSH',(SELECT CURRENT_TIMESTAMP FROM RDB$DATABASE),'TESTE');
INSERT INTO EQUIPMENTS (EQU_ID,EQU_OID,EQU_IP,EQU_TYPE_ID,EQU_MODEL_ID,EQU_MANUFACTURER_ID,EQU_SITE_ID,EQU_SYSTEM_ID,EQU_INSTALL_DATE,USR_INSERT) VALUES('XPTO_02'		,'1.3.6.1.4.1.25281.1025.0',null			,1,1,1,1,'SSH',(SELECT CURRENT_TIMESTAMP FROM RDB$DATABASE),'TESTE');
INSERT INTO EQUIPMENTS (EQU_ID,EQU_OID,EQU_IP,EQU_TYPE_ID,EQU_MODEL_ID,EQU_MANUFACTURER_ID,EQU_SITE_ID,EQU_SYSTEM_ID,EQU_INSTALL_DATE,USR_INSERT) VALUES('10.114.0.151'	,'1.3.6.1.4.1.25281.1025.0','10.114.0.151'	,1,1,1,1,'SSH',(SELECT CURRENT_TIMESTAMP FROM RDB$DATABASE),'TESTE');

--ESTADO OPERACIONAL
INSERT INTO OPERATIONAL_STATE (OST_ID,OST_MODEL_ID,OST_DESCRIPTION, USR_INSERT) VALUES ('AB001',1,'ENTRADA', 'TESTE');

-- DOCUMENTOS
INSERT INTO DOCUMENTS(DOC_MODEL_ID,DOC_DESCRIPTION,DOC_URL,USR_INSERT)VALUES(1,'VIDEO DE OPERACAO DO EQUIPAMENTO'	,'/src/videos/lalala.mp4'		,'TESTE');
INSERT INTO DOCUMENTS(DOC_MODEL_ID,DOC_DESCRIPTION,DOC_URL,USR_INSERT)VALUES(1,'MANUAL TECNICO DO EQUIPAMENTO'		,'/src/pdf/manua_equip_x.pdf'	,'TESTE');
INSERT INTO DOCUMENTS(DOC_MODEL_ID,DOC_DESCRIPTION,DOC_URL,USR_INSERT)VALUES(1,'CHECK LIST DE MANUTENCAO'			,'/src/docs/check_list_x.pdf'	,'TESTE');

-- GRUPO DE ALARMES
INSERT INTO ALARMS_GROUP (AGR_ID,AGR_DESCRIPTION,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES (1,'ALARMES DE ESTACAO','SYSTEM',{ts '2011-02-17 00:49:06.'},null,null);
SET GENERATOR GEN_AGR_ID TO 1;

-- TIPOS DE ALARMES
INSERT INTO ALARMS_TYPE (ATY_ID,ATY_DESCRIPTION,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES (1,'ALARME DE FALHA DE EQUIPAMENTO','SYSTEM',{ts '2011-02-17 00:50:24.'},null,null);
INSERT INTO ALARMS_TYPE (ATY_ID,ATY_DESCRIPTION,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES (2,'ALARME DE FALHA DE COMUNICAÇÃO','SYSTEM',{ts '2011-02-17 00:50:24.'},null,null);
INSERT INTO ALARMS_TYPE (ATY_ID,ATY_DESCRIPTION,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES (3,'ALARME DE NORMALIZAÇÃO','SYSTEM',{ts '2011-02-17 00:50:24.'},null,null);
SET GENERATOR GEN_ATY_ID TO 3;

-- ALARMES
-- Normalização
INSERT INTO ALARMS (ALM_ID,ALM_DESCRIPTION,ALM_NORM_ID,ALM_NORM_MAN,ALM_NORM_ALM,ALM_GROUP_ID,ALM_TYPE_ID,ALM_MODEL_ID,ALM_SEVERITY_ID,ALM_COUNTER_INC,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES ('BLQNORMAL'		,'NORMALIZACAO FALHA BLOQUEIO'		,null,'Y','Y',1,3,1,'0','Y','SYSTEM',{ts '2015-05-22 11:51:31.'},null,null);
INSERT INTO ALARMS (ALM_ID,ALM_DESCRIPTION,ALM_NORM_ID,ALM_NORM_MAN,ALM_NORM_ALM,ALM_GROUP_ID,ALM_TYPE_ID,ALM_MODEL_ID,ALM_SEVERITY_ID,ALM_COUNTER_INC,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES ('BLQNORMALCOM'	,'NORMALIZACAO FALHA COM BLOQUEIO'	,null,'Y','Y',1,3,1,'0','Y','SYSTEM',{ts '2015-05-22 11:57:17.'},null,null);
INSERT INTO ALARMS (ALM_ID,ALM_DESCRIPTION,ALM_NORM_ID,ALM_NORM_MAN,ALM_NORM_ALM,ALM_GROUP_ID,ALM_TYPE_ID,ALM_MODEL_ID,ALM_SEVERITY_ID,ALM_COUNTER_INC,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES ('BMNORMAL'		,'NORMALIZACAO FALHA'				,null,'Y','Y',1,3,1,'0','Y','SYSTEM',{ts '2015-05-22 11:57:18.'},null,null);
INSERT INTO ALARMS (ALM_ID,ALM_DESCRIPTION,ALM_NORM_ID,ALM_NORM_MAN,ALM_NORM_ALM,ALM_GROUP_ID,ALM_TYPE_ID,ALM_MODEL_ID,ALM_SEVERITY_ID,ALM_COUNTER_INC,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES ('BMNORMALCOM'	,'NORMALIZACAO FALHA COM'			,null,'Y','Y',1,3,1,'0','Y','SYSTEM',{ts '2015-05-22 11:57:18.'},null,null);
INSERT INTO ALARMS (ALM_ID,ALM_DESCRIPTION,ALM_NORM_ID,ALM_NORM_MAN,ALM_NORM_ALM,ALM_GROUP_ID,ALM_TYPE_ID,ALM_MODEL_ID,ALM_SEVERITY_ID,ALM_COUNTER_INC,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES ('DINORMAL'		,'NORMALIZACAO FALHA DET INC'		,null,'Y','Y',1,3,1,'0','Y','SYSTEM',{ts '2015-05-22 11:57:18.'},null,null);
INSERT INTO ALARMS (ALM_ID,ALM_DESCRIPTION,ALM_NORM_ID,ALM_NORM_MAN,ALM_NORM_ALM,ALM_GROUP_ID,ALM_TYPE_ID,ALM_MODEL_ID,ALM_SEVERITY_ID,ALM_COUNTER_INC,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES ('DINORMALCOM'	,'NORMALIZACAO FALHA COM DET INC'	,null,'Y','Y',1,3,1,'0','Y','SYSTEM',{ts '2015-05-22 11:57:18.'},null,null);
INSERT INTO ALARMS (ALM_ID,ALM_DESCRIPTION,ALM_NORM_ID,ALM_NORM_MAN,ALM_NORM_ALM,ALM_GROUP_ID,ALM_TYPE_ID,ALM_MODEL_ID,ALM_SEVERITY_ID,ALM_COUNTER_INC,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES ('NORMAL'		,'NORMALIZACAO FALHA'				,null,'Y','Y',1,3,1,'0','Y','SYSTEM',{ts '2015-05-22 11:57:18.'},null,null);
INSERT INTO ALARMS (ALM_ID,ALM_DESCRIPTION,ALM_NORM_ID,ALM_NORM_MAN,ALM_NORM_ALM,ALM_GROUP_ID,ALM_TYPE_ID,ALM_MODEL_ID,ALM_SEVERITY_ID,ALM_COUNTER_INC,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES ('VTNORMAL'		,'NORMALIZACAO FALHA VENT'			,null,'Y','Y',1,3,1,'0','Y','SYSTEM',{ts '2015-05-22 11:57:19.'},null,null);
INSERT INTO ALARMS (ALM_ID,ALM_DESCRIPTION,ALM_NORM_ID,ALM_NORM_MAN,ALM_NORM_ALM,ALM_GROUP_ID,ALM_TYPE_ID,ALM_MODEL_ID,ALM_SEVERITY_ID,ALM_COUNTER_INC,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES ('VTNORMALCOM'	,'NORMALIZA FALHA DE COMUNICACAO'	,null,'Y','Y',1,2,1,'0','Y','SYSTEM',{ts '2015-05-22 14:35:48.'},null,null);
INSERT INTO ALARMS (ALM_ID,ALM_DESCRIPTION,ALM_NORM_ID,ALM_NORM_MAN,ALM_NORM_ALM,ALM_GROUP_ID,ALM_TYPE_ID,ALM_MODEL_ID,ALM_SEVERITY_ID,ALM_COUNTER_INC,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES ('PPNORMALCOM'	,'NORMALIZACAO FALHA COM PP'		,null,'Y','Y',1,3,1,'0','Y','SYSTEM',{ts '2015-05-22 11:57:18.'},null,null);
INSERT INTO ALARMS (ALM_ID,ALM_DESCRIPTION,ALM_NORM_ID,ALM_NORM_MAN,ALM_NORM_ALM,ALM_GROUP_ID,ALM_TYPE_ID,ALM_MODEL_ID,ALM_SEVERITY_ID,ALM_COUNTER_INC,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES ('XPTONRM'		,'ALARMEE NORMALIZADOR'				,null,'Y','Y',1,1,1,'3','Y','JOABRE',{ts '2015-05-21 17:11:41.'},null,null);

--Alarmes
INSERT INTO ALARMS (ALM_ID,ALM_DESCRIPTION,ALM_NORM_ID,ALM_NORM_MAN,ALM_NORM_ALM,ALM_GROUP_ID,ALM_TYPE_ID,ALM_MODEL_ID,ALM_SEVERITY_ID,ALM_COUNTER_INC,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES ('BLQFALHACOM','FALHA DE COMUNICACAO COM O BLOQUEIO'			,'BLQNORMALCOM'	,'Y','N',1,2,1,'2','N','SYSTEM',{ts '2015-05-22 11:36:09.'},'SYSTEM',{ts '2015-05-22 11:58:37.'});
INSERT INTO ALARMS (ALM_ID,ALM_DESCRIPTION,ALM_NORM_ID,ALM_NORM_MAN,ALM_NORM_ALM,ALM_GROUP_ID,ALM_TYPE_ID,ALM_MODEL_ID,ALM_SEVERITY_ID,ALM_COUNTER_INC,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES ('BMFALHACOM',' FALHA DE COMUNICACAO COM A BOMBA'			,'BMNORMALCOM'	,'Y','N',1,2,1,'3','N','SYSTEM',{ts '2015-05-22 11:38:38.'},'SYSTEM',{ts '2015-05-22 14:18:54.'});
INSERT INTO ALARMS (ALM_ID,ALM_DESCRIPTION,ALM_NORM_ID,ALM_NORM_MAN,ALM_NORM_ALM,ALM_GROUP_ID,ALM_TYPE_ID,ALM_MODEL_ID,ALM_SEVERITY_ID,ALM_COUNTER_INC,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES ('DIFALHACOM',' FALHA DE COMUNICACAO COM O SIST DE DET-INCEN','DINORMALCOM'	,'Y','N',1,2,1,'1','N','SYSTEM',{ts '2015-05-22 11:47:30.'},'SYSTEM',{ts '2015-05-22 14:19:07.'});
INSERT INTO ALARMS (ALM_ID,ALM_DESCRIPTION,ALM_NORM_ID,ALM_NORM_MAN,ALM_NORM_ALM,ALM_GROUP_ID,ALM_TYPE_ID,ALM_MODEL_ID,ALM_SEVERITY_ID,ALM_COUNTER_INC,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES ('FALHACOM','FALHA DE COMUNICACAO'							,'NORMAL'		,'Y','N',1,2,1,'1','N','SYSTEM',{ts '2015-05-22 11:48:14.'},'SYSTEM',{ts '2015-05-22 14:19:24.'});
INSERT INTO ALARMS (ALM_ID,ALM_DESCRIPTION,ALM_NORM_ID,ALM_NORM_MAN,ALM_NORM_ALM,ALM_GROUP_ID,ALM_TYPE_ID,ALM_MODEL_ID,ALM_SEVERITY_ID,ALM_COUNTER_INC,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES ('PPFALHACOM','FALHA DE COMUNICACAO COM A PORTA DE PLAT'		,'PPNORMALCOM'	,'Y','N',1,2,1,'3','N','SYSTEM',{ts '2015-05-22 11:49:05.'},'SYSTEM',{ts '2015-05-22 14:19:45.'});
INSERT INTO ALARMS (ALM_ID,ALM_DESCRIPTION,ALM_NORM_ID,ALM_NORM_MAN,ALM_NORM_ALM,ALM_GROUP_ID,ALM_TYPE_ID,ALM_MODEL_ID,ALM_SEVERITY_ID,ALM_COUNTER_INC,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES ('VTFALHACOM','FALHA DE COMUNICACAO COM O SIS DE VENTIL'		,'VTNORMALCOM'	,'Y','N',1,2,1,'1','N','SYSTEM',{ts '2015-05-22 11:49:57.'},'SYSTEM',{ts '2015-05-22 14:37:20.'});
INSERT INTO ALARMS (ALM_ID,ALM_DESCRIPTION,ALM_NORM_ID,ALM_NORM_MAN,ALM_NORM_ALM,ALM_GROUP_ID,ALM_TYPE_ID,ALM_MODEL_ID,ALM_SEVERITY_ID,ALM_COUNTER_INC,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES ('XPTOALM','ALARME SEVERIDADE: GRAVE'						,'XPTONRM'		,'Y','N',1,1,1,'2','Y','JOABRE',{ts '2015-05-21 17:11:42.'},null,null);
INSERT INTO ALARMS (ALM_ID,ALM_DESCRIPTION,ALM_NORM_ID,ALM_NORM_MAN,ALM_NORM_ALM,ALM_GROUP_ID,ALM_TYPE_ID,ALM_MODEL_ID,ALM_SEVERITY_ID,ALM_COUNTER_INC,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES ('XPTOGRAVE','ALARME SEVERIDADE: GRAVE'						,null			,'Y','N',1,1,1,'3','Y','JOABRE',{ts '2015-05-21 17:11:41.'},null,null);
INSERT INTO ALARMS (ALM_ID,ALM_DESCRIPTION,ALM_NORM_ID,ALM_NORM_MAN,ALM_NORM_ALM,ALM_GROUP_ID,ALM_TYPE_ID,ALM_MODEL_ID,ALM_SEVERITY_ID,ALM_COUNTER_INC,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES ('XPTOLEVE','ALARME SEVERIDADE: LEVE'						,'XPTONRM'		,'Y','N',1,1,1,'2','Y','JOABRE',{ts '2015-05-21 17:11:41.'},'SYSTEM',{ts '2015-05-22 09:55:27.'});
INSERT INTO ALARMS (ALM_ID,ALM_DESCRIPTION,ALM_NORM_ID,ALM_NORM_MAN,ALM_NORM_ALM,ALM_GROUP_ID,ALM_TYPE_ID,ALM_MODEL_ID,ALM_SEVERITY_ID,ALM_COUNTER_INC,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES ('XPTONORMAL','ALARME SEVERIDADE: NORMAL'					,null			,'Y','N',1,1,1,'1','Y','JOABRE',{ts '2015-05-21 17:11:41.'},null,null);
INSERT INTO ALARMS (ALM_ID,ALM_DESCRIPTION,ALM_NORM_ID,ALM_NORM_MAN,ALM_NORM_ALM,ALM_GROUP_ID,ALM_TYPE_ID,ALM_MODEL_ID,ALM_SEVERITY_ID,ALM_COUNTER_INC,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES ('XPTOSEM','ALARME SEVERIDADE: SEM SEVERIDADE'				,null			,'Y','N',1,1,1,'0','Y','JOABRE',{ts '2015-05-21 17:11:41.'},null,null);
INSERT INTO ALARMS (ALM_ID,ALM_DESCRIPTION,ALM_NORM_ID,ALM_NORM_MAN,ALM_NORM_ALM,ALM_GROUP_ID,ALM_TYPE_ID,ALM_MODEL_ID,ALM_SEVERITY_ID,ALM_COUNTER_INC,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES ('TSK001'	,'ALARME GERADO PELA REGRA'						,null			,'Y','N',1,1,1,'0','Y','JOABRE',{ts '2015-05-21 17:11:41.'},null,null);

-- TIPOS DE SERVICO
INSERT INTO SERVICE_ORDER_JOBS (SOJ_ID,SOJ_DESCRIPTION,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES ('01','TROCA DE EQUIPAMENTO','TESTE',{ts '2011-02-20 04:04:58.'},null,null);

-- TECNICOS
INSERT INTO TECHNICIAN (TEC_ID,TEC_NAME,TEC_SITE_ID,USR_INSERT,DTI_INSERT,USR_UPDATE,DTI_UPDATE) VALUES ('T00001','RIDELSON',1,'TESTE',{ts '2011-02-20 03:54:08.'},null,null);


--EVENTOS
INSERT INTO EVENTS (EVE_EQUIPMENT_ID,EVE_ALARM_ID,EVE_DATETIME,USR_INSERT) VALUES ('XPTO_01','XPTONORMAL',(SELECT CURRENT_TIMESTAMP FROM RDB$DATABASE),'TESTE');
INSERT INTO EVENTS (EVE_EQUIPMENT_ID,EVE_ALARM_ID,EVE_DATETIME,USR_INSERT) VALUES ('XPTO_01','XPTOLEVE',(SELECT CURRENT_TIMESTAMP FROM RDB$DATABASE),'TESTE');
INSERT INTO EVENTS (EVE_EQUIPMENT_ID,EVE_ALARM_ID,EVE_DATETIME,USR_INSERT) VALUES ('XPTO_01','XPTOGRAVE',(SELECT CURRENT_TIMESTAMP FROM RDB$DATABASE),'TESTE');
INSERT INTO EVENTS (EVE_EQUIPMENT_ID,EVE_ALARM_ID,EVE_DATETIME,USR_INSERT) VALUES ('XPTO_02','XPTONORMAL',(SELECT CURRENT_TIMESTAMP FROM RDB$DATABASE),'TESTE');
INSERT INTO EVENTS (EVE_EQUIPMENT_ID,EVE_ALARM_ID,EVE_DATETIME,USR_INSERT) VALUES ('XPTO_02','XPTOLEVE',(SELECT CURRENT_TIMESTAMP FROM RDB$DATABASE),'TESTE');
INSERT INTO EVENTS (EVE_EQUIPMENT_ID,EVE_ALARM_ID,EVE_DATETIME,USR_INSERT) VALUES ('XPTO_02','XPTOGRAVE',(SELECT CURRENT_TIMESTAMP FROM RDB$DATABASE),'TESTE');
INSERT INTO EVENTS (EVE_EQUIPMENT_ID,EVE_ALARM_ID,EVE_DATETIME,USR_INSERT) VALUES ('10.114.0.150','XPTONORMAL',(SELECT CURRENT_TIMESTAMP FROM RDB$DATABASE),'TESTE');
INSERT INTO EVENTS (EVE_EQUIPMENT_ID,EVE_ALARM_ID,EVE_DATETIME,USR_INSERT) VALUES ('10.114.0.150','XPTOLEVE',(SELECT CURRENT_TIMESTAMP FROM RDB$DATABASE),'TESTE');
INSERT INTO EVENTS (EVE_EQUIPMENT_ID,EVE_ALARM_ID,EVE_DATETIME,USR_INSERT) VALUES ('10.114.0.150','XPTOGRAVE',(SELECT CURRENT_TIMESTAMP FROM RDB$DATABASE),'TESTE');

--FILTROS DE ALARME
INSERT INTO ALARMS_FILTER (AFI_ALARM_ID,AFI_EQUIPMENT_ID,USR_INSERT)VALUES('XPTONORMAL','XPTO_01','TESTE');

--REGRAS DE MONITORAMENTO
INSERT INTO TASK_MONITOR_HEADER(TMH_DESC,TMH_ACTIVE, TMH_ALARM_ID, USR_INSERT) VALUES ('REGRA PARA SUPERVISAO DE TIPO DE CONT','Y','XPTOLEVE','TESTE');

INSERT INTO TASK_MONITOR_ITEMS(TMI_TASK_ID, TMI_SEQ, TMI_LOGIC_OP, TMI_TYPE, TMI_FIELD, TMI_RELATIONAL_OP, TMI_VALUE, USR_INSERT)
VALUES
(1,'01','-','AL','XPTOGRAVE','>',3,'TESTE');

INSERT INTO TASK_MONITOR_ITEMS(TMI_TASK_ID, TMI_SEQ, TMI_LOGIC_OP, TMI_TYPE, TMI_FIELD, TMI_RELATIONAL_OP, TMI_VALUE, USR_INSERT)
VALUES
(1,'02','OU','AL','XPTONORMAL','>',3,'TESTE');

INSERT INTO TASK_MONITOR_ITEMS(TMI_TASK_ID, TMI_SEQ, TMI_LOGIC_OP, TMI_TYPE, TMI_FIELD, TMI_RELATIONAL_OP, TMI_VALUE, USR_INSERT)
VALUES
(1,'03','OU','AL','XPTOSEM','>',3,'TESTE');

INSERT INTO TASK_EQUIPMENT(TASK_ID, EQUIPMENT_ID,CUTOFF_DATE) VALUES (1,'XPTO_01',(SELECT CURRENT_TIMESTAMP FROM RDB$DATABASE));
INSERT INTO TASK_EQUIPMENT(TASK_ID, EQUIPMENT_ID,CUTOFF_DATE) VALUES (1,'XPTO_02',(SELECT CURRENT_TIMESTAMP FROM RDB$DATABASE));


/*** Tipo de Alarme: Normalização ***/
INSERT INTO TASK_MONITOR_HEADER(TMH_DESC,TMH_ACTIVE, TMH_ALARM_ID, USR_INSERT) VALUES ('SUPERVISAO DE TIPO DE ALARME: NORMALIZAÇÃO','Y','XPTONORMAL','TESTE');

INSERT INTO TASK_MONITOR_ITEMS(TMI_TASK_ID, TMI_SEQ, TMI_LOGIC_OP, TMI_TYPE, TMI_FIELD, TMI_RELATIONAL_OP, TMI_VALUE, USR_INSERT)
VALUES
(2,'01','-','AT','3','>',3,'TESTE');

INSERT INTO TASK_EQUIPMENT(TASK_ID, EQUIPMENT_ID) VALUES (2,'XPTO_01');
INSERT INTO TASK_EQUIPMENT(TASK_ID, EQUIPMENT_ID) VALUES (2,'XPTO_02');


SELECT 'CONCLUIDO' FROM RDB$DATABASE;
