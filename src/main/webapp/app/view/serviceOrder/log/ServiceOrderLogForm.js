var logInfo = {
	xtype : 'fieldset',
	defaultType : 'textfield',
	title : 'Detalhes do Log',
	layout : {
		type : 'vbox',
		//align : 'stretch',
	},

	items : [ {
		fieldLabel : 'Ordem de Serviço',
		itemId: 'log_os',
		readOnly : true,
		width: '40%',
		inputAttrTpl: " data-qtip='Número da Ordem de Serviço' "
	},{
		fieldLabel : 'Status Anterior',
		itemId: 'log_prevstatus',
		readOnly : true,
		width: '40%',
		inputAttrTpl: " data-qtip='Status Anterior da Ordem de Serviço' "
	},{
		fieldLabel : 'Status Posterior',
		itemId: 'log_curstatus',
		readOnly : true,
		width: '40%',
		inputAttrTpl: " data-qtip='Status Posterior da Ordem de Serviço' "
	},{
		fieldLabel : 'Usuario',
		itemId: 'log_user',
		readOnly : true,
		width: '40%',
		inputAttrTpl: " data-qtip='Usuário que Abriu a Ordem de Serviço' "
	},{
		fieldLabel : 'Data/Hora',
		itemId: 'log_datetime',
		readOnly : true,
		width: '30%',
		inputAttrTpl: " data-qtip='Data/Hora da Abertura da Ordem de Serviço' "
	},{
		xtype: 'textareafield',
		fieldLabel : 'Observação',
		itemId: 'log_remark',
		readOnly : true,
		width: '60%',
		inputAttrTpl: " data-qtip='Observação da Ordem de Serviço' "
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
