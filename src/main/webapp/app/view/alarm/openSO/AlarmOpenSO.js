Ext.define('Sam.view.alarm.openSO.AlarmOpenSO', {
	extend: 'Ext.TabPanel',
	alias:  'widget.alarmopenso',
	
	requires: ['Sam.view.alarm.openSO.AlarmDataOpenSO',
	           'Sam.view.alarm.openSO.AlarmInfoEqto',
	           'Sam.view.alarm.openSO.histSO.AlarmHistSO'],
	
	closable: true,
	
	layout:{
		type: 'fit',
	},
	
	items:[{
		xtype: 'alarmdataopenso',
		title: 'Dados de Abertura da OS',
		closable: false
	},{
		xtype: 'alarminfoeqto',
		title: 'Informações do Equipamento',
		closable: false	
	},{
		xtype: 'alarmhistso',
		title: 'Histórico de OSs',
		closable: false
	}]

});