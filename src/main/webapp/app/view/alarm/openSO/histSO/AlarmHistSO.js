Ext.define('Sam.view.alarm.openSO.histSO.AlarmHistSO', {
	extend: 'Ext.Panel',
	alias:  'widget.alarmhistso',
	
	requires: ['Sam.view.alarm.openSO.histSO.AlarmHistSO',
	           'Sam.view.alarm.openSO.histSO.AlarmHistSOGrid',
	           'Sam.view.alarm.openSO.histSO.AlarmHistSOForm',
	           'Ext.layout.container.Border'],
	
	closable: false,
	
	layout: 'border',
    width: 500,
    height: 400,

    bodyBorder: false,
    
    defaults: {
        collapsible: false,
        split: true
    },

    items: [
            {
                title: 'Detalhes da Ordem de Servico',
                xtype: 'alarmhistsoform',
                region: 'center',
                margin: '5 0 0 0',
            },{
	        	 title: 'Ordens De Serviço',
	        	 xtype: 'alarmhistsogrid',
	        	 collapsible: true,
	             region: 'south',
	             margin: '5 0 0 0',
	             minHeight: 100,
        }
    ]
});