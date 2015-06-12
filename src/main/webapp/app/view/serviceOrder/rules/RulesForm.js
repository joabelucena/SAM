var rulesSO = {
	xtype : 'fieldset',
	defaultType : 'textfield',
	title : 'Id e Descrição',
	layout : {
		type : 'vbox'
	},

	items : [ {
		fieldLabel : 'Id',
		itemId: 'id',
		readOnly : true,
		width: '40%',
		inputAttrTpl: " data-qtip='Id da Regra da Ordem de Serviço' "
	},{
		fieldLabel : 'Descrição da Regra',
		itemId: 'role_desc',
		readOnly : true,
		width: '60%',
		inputAttrTpl: " data-qtip='Descrição da Regra da Ordem de Serviço' "
	}],
};

var rules = {
	xtype : 'fieldset',
	defaultType : 'textfield',
	title : 'Permissão',
	layout : {
		type : 'vbox'
	},

	items : [ {
		fieldLabel : 'Id da Permissão',
		itemId: 'role_id',
		readOnly : true,
		width: '40%',
		inputAttrTpl: " data-qtip='Id da Permissão da Regra da Ordem de Serviço' "
	},{
		fieldLabel : 'Descrição da Permissão',
		itemId: 'role_desc',
		readOnly : true,
		width: '60%',
		inputAttrTpl: " data-qtip='Descrição da Permissão da Regra da Ordem de Serviço' "
	}],
};

var currentStatus = {
		xtype : 'fieldset',
		defaultType : 'textfield',
		title : 'Status da OS Atual',
		layout : {
			type : 'vbox'
		},

		items : [{
			fieldLabel : 'Id do Status Atual',
			itemId: 'curstatus_id',
			readOnly : true,
			width: '40%',
			inputAttrTpl: " data-qtip='Id do Status Atual da Regra da Ordem de Serviço' "
		},{
			fieldLabel : 'Descrição do Status Atual',
			itemId: 'curstatus_desc',
			readOnly : true,
			width: '60%',
			inputAttrTpl: " data-qtip='Descrição do Status Atual da Regra da Ordem de Serviço' "
		}],
};

var nextStatus = {
		xtype : 'fieldset',
		defaultType : 'textfield',
		title : 'Status da OS Atual',
		layout : {
			type : 'vbox'
		},

		items : [ {
			fieldLabel : 'Ordem de Serviço',
			itemId: 'nxtstatus_id',
			readOnly : true,
			width: '40%',
			inputAttrTpl: " data-qtip='Id do Próximo Status da Regra da Ordem de Serviço' "
		},{
			fieldLabel : 'Status Anterior',
			itemId: 'nxtstatus_desc',
			readOnly : true,
			width: '60%',
			inputAttrTpl: " data-qtip='Descrição do Próximo Status da Regra da Ordem de Serviço' "
		}],
};

Ext.define('Sam.view.serviceOrder.rules.RulesForm', {
	extend: 'Ext.Panel',
	alias:  'widget.serviceorderrulesform',
	
	itemId: 'serviceorderrulesform',
	
	closable: true,
	
	layout:{
		type: 'fit',
	},
	
	items : [{
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
		items : [ rulesSO, rules, currentStatus, nextStatus ],
		
		dockedItems: [{
		    xtype: 'toolbar',
		    dock: 'bottom',
		    
		    items: [{
		    	xtype: 'tbfill'
		    },{
		        xtype:'button',
		    	itemId:'btnSubmit',
		    	text:'Confirma',
		        tooltip:'Confirmar Operação',
		        cls:'x-btn-default-small',
		        iconCls: 'tick-button'
		    },{
		        xtype:'button',
		    	itemId:'btnDiscard',
		    	text:'Cancela',
		        tooltip:'Cancelar Operação',
		        cls:'x-btn-default-small',
		        iconCls: 'tick-button'
		    }]
		}]
	}]	
});
