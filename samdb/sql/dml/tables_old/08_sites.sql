/* 
 * Table:	SITES
 */


INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'CENTRO DE CONTROLE OPERACIONAL', 
        'CCO',
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'),
        NULL,
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'CCO'),
        'SYSTEM'
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'TRECHO BARREIROS-PORTO',                                            --DESCRICAO
        'TBP',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        NULL,                                                                --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'LINHA'),         --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'TRECHO CONSELHEIRO NÉBIAS-VALONGO',                                 --DESCRICAO
        'TCV',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        NULL,                                                                --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'LINHA'),        --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'PÁTIO PORTO',                                                       --DESCRICAO
        'PPO',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        NULL,                                                                --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'TERMINAL'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );

INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'TERMINAL PORTO',                                                --DESCRICAO
        'POR',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TBP'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'ESTAÇÃO'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'CONSELHEIRO NÉBIAS',                                                --DESCRICAO
        'ABU',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TCV'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'ESTAÇÃO'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'WASHINGTON LUÍS',                                                --DESCRICAO
        'WLU',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TBP'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'ESTAÇÃO'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'ANA COSTA',                                                --DESCRICAO
        'ANA',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TBP'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'ESTAÇÃO'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'BERNADINO DE CAMPOS',                                                --DESCRICAO
        'BCA',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TBP'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'ESTAÇÃO'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'ORQUIDÁRIO',                                                --DESCRICAO
        'ORQ',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TBP'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'ESTAÇÃO'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'PINHEIRO MACHADO',                                                --DESCRICAO
        'PMA',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TBP'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'ESTAÇÃO'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'NOSSA SENHORA DE LOURDES',                                                --DESCRICAO
        'NSL',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TBP'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'ESTAÇÃO'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'JOÃO RIBEIRO',                                                --DESCRICAO
        'JRI',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TBP'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'ESTAÇÃO'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'ITARARÉ',                                                          --DESCRICAO
        'ITR',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TBP'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'ESTAÇÃO'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'JOSÉ MONTEIRO',                                                --DESCRICAO
        'JMO',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TBP'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'ESTAÇÃO'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'NOSSA SENHORA DAS GRAÇAS',                                                --DESCRICAO
        'NSG',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TBP'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'ESTAÇÃO'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'ANTÔNIO EMMERICH',                                                --DESCRICAO
        'AEM',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TBP'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'ESTAÇÃO'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'SÃO VICENTE',                                                --DESCRICAO
        'SVI',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TBP'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'ESTAÇÃO'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'MASCARENHAS DE MORAIS',                                                --DESCRICAO
        'MMO',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TBP'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'ESTAÇÃO'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'BARREIROS',                                                        --DESCRICAO
        'BAR',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TBP'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'ESTAÇÃO'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'JOÃO GUERRA',                                                        --DESCRICAO
        'JGU',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TCV'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'ESTAÇÃO'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'UNIVERSIDADES I',                                                        --DESCRICAO
        'UN1',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TCV'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'ESTAÇÃO'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'MERCADO',                                                        --DESCRICAO
        'MCD',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TCV'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'ESTAÇÃO'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'POUPATEMPO',                                                        --DESCRICAO
        'PPA',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TCV'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'ESTAÇÃO'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'MAUÁ',                                                        --DESCRICAO
        'MAU',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TCV'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'ESTAÇÃO'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'SÃO BENTO',                                                        --DESCRICAO
        'SBE',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TCV'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'ESTAÇÃO'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'VALONGO',                                                        --DESCRICAO
        'VAL',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TCV'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'ESTAÇÃO'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'JOSÉ BONIFÁCIO',                                                    --DESCRICAO
        'JBO',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TCV'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'ESTAÇÃO'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'BITTENCOURT',                                                        --DESCRICAO
        'BTC',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TCV'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'ESTAÇÃO'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'RANGEL PESTANA',                                                        --DESCRICAO
        'RPE',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TCV'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'ESTAÇÃO'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'UNIVERSIDADES II',                                                        --DESCRICAO
        'UN2',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TCV'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'ESTAÇÃO'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'TAMANDARÉ',                                                        --DESCRICAO
        'TMD',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TCV'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'ESTAÇÃO'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'SUB-ESTAÇÃO TERMINAL BARREIROS',                                                        --DESCRICAO
        'SEBAR',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TBP'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'SUB-ESTAÇÃO DE ENERGIA'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'SUB-ESTAÇÃO MASCARENHAS DE MORAIS',                                                        --DESCRICAO
        'SEMMO',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TBP'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'SUB-ESTAÇÃO DE ENERGIA'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'SUB-ESTAÇÃO ANTÔNIO EMMERICH',                                                        --DESCRICAO
        'SEAEM',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TBP'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'SUB-ESTAÇÃO DE ENERGIA'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'SUB-ESTAÇÃO JOSÉ MONTEIRO',                                                        --DESCRICAO
        'SEJMO',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TBP'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'SUB-ESTAÇÃO DE ENERGIA'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'SUB-ESTAÇÃO NOSSA SENHORA DE LOURDES',                                                        --DESCRICAO
        'SENSL',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TBP'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'SUB-ESTAÇÃO DE ENERGIA'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'SUB-ESTAÇÃO BERNADINO DE CAMPOS',                                                        --DESCRICAO
        'SEBCA',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TBP'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'SUB-ESTAÇÃO DE ENERGIA'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'SUB-ESTAÇÃO WASHINGTON LUÍS',                                                        --DESCRICAO
        'SEWLU',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TBP'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'SUB-ESTAÇÃO DE ENERGIA'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'SUB-ESTAÇÃO TERMINAL PORTO',                                                        --DESCRICAO
        'SEPPO',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TBP'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'SUB-ESTAÇÃO DE ENERGIA'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'SUB-ESTAÇÃO UNIVERSIDADE',                                                        --DESCRICAO
        'SEUN1',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TCV'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'SUB-ESTAÇÃO DE ENERGIA'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'SUB-ESTAÇÃO AMADOR BUENO',                                                        --DESCRICAO
        'SEUN1',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TCV'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'SUB-ESTAÇÃO DE ENERGIA'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'SUB-ESTAÇÃO VALONGO',                                                        --DESCRICAO
        'SEVAL',                                                               --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'TCV'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'SUB-ESTAÇÃO DE ENERGIA'),      --TIPO DE LOCAL
        'SYSTEM'                                                             --USUÁRIO
    );
    
INSERT INTO SITES (SIT_DESCRIPTION,SIT_SHORTNAME,SIT_STATION_ID,SIT_PARENT_ID,SIT_TYPE_ID,USR_INSERT)
VALUES(
        'EDIFÍCIO DE APOIO AOS OPERADORES',                                    --DESCRICAO
'EAO', --SIGLA
        (SELECT SST_ID FROM SERVICE_STATION WHERE SST_DESCRIPTION = 'BASE CCO'), --BASE DE MANUTENÇÃO
        (SELECT SIT_ID FROM SITES WHERE SIT_SHORTNAME = 'CCO'),                  --PAI/PARENT
        (SELECT STY_ID FROM SITES_TYPE WHERE STY_DESCRIPTION = 'EDIFÍCIO'),      --TIPO DE LOCAL
'SYSTEM' --USUÁRIO
    );


/** END OF TABLE POPULATION **/


