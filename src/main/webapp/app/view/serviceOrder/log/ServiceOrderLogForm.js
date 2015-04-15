var logInfo = {
	xtype : 'fieldset',
	defaultType : 'textfield',
	title : 'Detalhes do Log',
	layout : {
		type : 'vbox',
		align : 'stretch',
	},

	items : [ {
		fieldLabel : 'Ordem de Serviço',
		itemId: 'log_os',
		readOnly : true,
	},{
		fieldLabel : 'Status Anterior',
		itemId: 'log_prevstatus',
		readOnly : true,
	},{
		fieldLabel : 'Status Posterior',
		itemId: 'log_curstatus',
		readOnly : true,
	},{
		fieldLabel : 'Usuario',
		itemId: 'log_user',
		readOnly : true,
	},{
		fieldLabel : 'Data/Hora',
		itemId: 'log_datetime',
		readOnly : true,
	},{
		xtype: 'textareafield',
		fieldLabel : 'Observação',
		itemId: 'log_remark',
		readOnly : true,
	}
	
	],
};

Ext.define('Sam.view.serviceOrder.log.ServiceOrderLogForm', {
	extend: 'Ext.Panel',
	alias:  'widget.serviceorderlogform',
	
	itemId: 'formdolog',
	
	closable: true,
	
	layout:{
		type: 'fit',
	},
	
	items : [ {
		xtype : 'form',

		defaultType : 'textfield',
		
		fieldDefaults : {
			labelWidth : 180
		},
		defaults:{
			allowBlank : false
		},

		layout : {
			type : 'vbox',
			align : 'stretch'
		},
		
		scrollable: true,

		bodyPadding : 10,
		border : false,
		items : [ logInfo ],
	} ]	
});
