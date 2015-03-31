Ext.define('Sam.view.alarm.AlarmOpenSO', {
	extend: 'Ext.TabPanel',
	alias:  'widget.alarmopenso',
	
	requires: ['Sam.view.alarm.AlarmOpenSO'],
	
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
		title: 'Histórico da OS',
		closable: false
	}]

});