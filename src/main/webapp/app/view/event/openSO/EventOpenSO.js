Ext.define('Sam.view.event.openSO.EventOpenSO', {
	extend: 'Ext.TabPanel',
	alias:  'widget.eventopenso',
	
	itemId: 'eventopenso',
	
	requires: ['Sam.view.event.openSO.EventDataOpenSO',
	           'Sam.view.event.openSO.EventInfoEqto',
	           'Sam.view.event.openSO.EventHistSO'],
	
	closable: true,
	
	layout:{
		type: 'fit',
	},
	
	items:[{
		xtype: 'eventdataopenso',
		title: 'Dados de Abertura da OS',
		closable: false
	},{
		xtype: 'eventinfoeqto',
		title: 'Informações do Equipamento',
		closable: false	
	},{
		xtype: 'eventhistso',
		title: 'Histórico de OSs',
		closable: false
	}]

});