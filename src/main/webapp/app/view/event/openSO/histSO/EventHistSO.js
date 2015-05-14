Ext.define('Sam.view.event.openSO.histSO.EventHistSO', {
	extend: 'Ext.Panel',
	alias:  'widget.eventhistso',
	
	requires: ['Sam.view.event.openSO.histSO.EventHistSO',
	           'Sam.view.event.openSO.histSO.EventHistSOGrid',
	           'Sam.view.event.openSO.histSO.EventHistSOForm',
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
                xtype: 'eventhistsoform',
                region: 'center',
                margin: '5 0 0 0',
            },{
	        	 title: 'Ordens De Serviço',
	        	 xtype: 'eventhistsogrid',
	        	 collapsible: true,
	             region: 'south',
	             margin: '5 0 0 0',
	             minHeight: 100,
        }
    ]
});