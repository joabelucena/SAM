var fldId = {
	xtype : 'fieldset',
	defaultType : 'textfield',
	title : 'Dados Regra',
	layout : {
		type : 'vbox'
	},

	items : [ {
		fieldLabel : 'Código',
		itemId: 'id',
		name: 'id',
		width: '30%',
		inputAttrTpl: " data-qtip='Id da Regra da Ordem de Serviço' "
	}]		
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
		name: 'role_id',
		allowBlank : false,
		width: '40%',
		inputAttrTpl: " data-qtip='Id da Permissão da Regra da Ordem de Serviço' ",
		triggers: {f3: {handler: function() {this.fireEvent('click')}}}
	},{
		fieldLabel : 'Descrição da Permissão',
		itemId: 'role_desc',
		name: 'role_desc',
		readOnly : true,
		width: '60%',
		inputAttrTpl: " data-qtip='Descrição da Permissão da Regra da Ordem de Serviço' "
	}],
};

var currentStatus = {
		xtype : 'fieldset',
		defaultType : 'textfield',
		title : 'Status Atual da OS',
		layout : {
			type : 'vbox'
		},

		items : [{
			fieldLabel : 'Id do Status Atual',
			itemId: 'curstatus_id',
			name: 'curstatus_id',
			allowBlank : false,
			width: '40%',
			inputAttrTpl: " data-qtip='Id do Status Atual da Regra da Ordem de Serviço' ",
			triggers: {f3: {handler: function() {this.fireEvent('click')}}}
		},{
			fieldLabel : 'Descrição do Status Atual',
			itemId: 'curstatus_desc',
			name: 'curstatus_desc',
			readOnly : true,
			width: '60%',
			inputAttrTpl: " data-qtip='Descrição do Status Atual da Regra da Ordem de Serviço' "
		}],
};

var nextStatus = {
		xtype : 'fieldset',
		defaultType : 'textfield',
		title : 'Status Anterior da OS',
		layout : {
			type : 'vbox'
		},

		items : [ {
			fieldLabel : 'Ordem de Serviço',
			itemId: 'nxtstatus_id',
			name: 'nxtstatus_id',
			allowBlank : false,
			width: '40%',
			inputAttrTpl: " data-qtip='Id do Próximo Status da Regra da Ordem de Serviço' ",
			triggers: {f3: {handler: function() {this.fireEvent('click')}}}
		},{
			fieldLabel : 'Descrição do Status Anterior',
			itemId: 'nxtstatus_desc',
			name: 'nxtstatus_desc',
			readOnly : true,
			width: '60%',
			inputAttrTpl: " data-qtip='Descrição do Próximo Status da Regra da Ordem de Serviço' "
		}],
};

Ext.define('Sam.view.serviceOrder.rules.RulesForm', {
	extend: 'Ext.Panel',
	requires:['Sam.view.components.FormToolbar'],
	
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
		
		scrollable: true,

		bodyPadding : 10,
		border : false,
		items : [ fldId , rules, currentStatus, nextStatus ],
		
		dockedItems: [{
			xtype: 'formtoolbar'
		}]
	}]	
});
