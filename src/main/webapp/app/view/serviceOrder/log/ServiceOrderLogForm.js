var logInfo = {
	xtype : 'fieldset',
	defaultType : 'textfield',
	title : 'Detalhes do Log',
	layout : {
		type : 'vbox',
		//align : 'stretch',
	},

	items : [{
		fieldLabel : 'Ordem de Serviço',
		itemId: 'so_id',
		name: 'so_id',
		readOnly : true,
		width: '30%',
		inputAttrTpl: " data-qtip='Número da Ordem de Serviço' "
	},{
		fieldLabel : 'Status Anterior',
		itemId: 'prevstatus_desc',
		name: 'prevstatus_desc',
		readOnly : true,
		width: '45%',
		inputAttrTpl: " data-qtip='Status Anterior da Ordem de Serviço' "
	},{
		fieldLabel : 'Status Posterior',
		itemId: 'curstatus_desc',
		name: 'curstatus_desc',
		readOnly : true,
		width: '45%',
		inputAttrTpl: " data-qtip='Status Posterior da Ordem de Serviço' "
	},{
		fieldLabel : 'Usuario',
		itemId: 'user_id',
		name: 'user_id',
		readOnly : true,
		width: '35%',
		inputAttrTpl: " data-qtip='Usuário que Abriu a Ordem de Serviço' "
	},{
		fieldLabel : 'Data/Hora',
		xtype: 'datefield',
		format: 'd/m/Y - G:i:s',
		itemId: 'datetime',
		name: 'datetime',
		readOnly : true,
		width: '40%',
		inputAttrTpl: " data-qtip='Data/Hora da Abertura da Ordem de Serviço' "
	},{
		xtype: 'textareafield',
		fieldLabel : 'Observação',
		itemId: 'remark',
		name: 'remark',
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
