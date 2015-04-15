Ext.define('Sam.view.serviceOrder.log.ServiceOrderLog', {
	extend: 'Ext.Panel',
	alias:  'widget.serviceorderlog',

	requires: ['Sam.view.serviceOrder.log.ServiceOrderLog',
	           'Sam.view.serviceOrder.log.ServiceOrderLogGrid',
	           'Sam.view.serviceOrder.log.ServiceOrderLogForm',
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
                title: 'Detalhes do Histórico',
                xtype: 'serviceorderlogform',
                region: 'center',
                margin: '5 0 0 0',
            },{
				title: 'Histórico da Ordem de Serviço',
				xtype: 'serviceorderloggrid',
				collapsible: true,
				region: 'south',
				margin: '5 0 0 0',
				minHeight: 200,
            }
            ]
});