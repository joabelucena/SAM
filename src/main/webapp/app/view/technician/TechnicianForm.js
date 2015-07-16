var site = {
	xtype : 'fieldset',
	defaultType : 'textfield',
	title : 'Local',
	itemId: 'fldSite',
	layout : {
		type : 'vbox',
	},

	items : [ {
		xtype: 'textfield',
		fieldLabel : 'Código',
		itemId: 'site_id',
		name: 'site_id',
		editable: false,
		width: '40%',
		allowBlank : false,
		inputAttrTpl: " data-qtip='Código do Local' ",
		triggers: {f3: {handler: function() {this.fireEvent('click')}}}
	}, {
		fieldLabel : 'Descrição',
		itemId: 'site_desc',
		name: 'site_desc',
		readOnly : true,
		width: '50%',
		inputAttrTpl: " data-qtip='Descrição do Local' ",
	}],
};


var tec = {
		xtype : 'fieldset',
		defaultType : 'textfield',
		title : 'Dados do Tecnico',
		itemId: 'fldTechnician',
		layout : {
			type : 'vbox',
		},

		items : [{
			fieldLabel : 'Código',
			itemId: 'id',
			name: 'id',
			maxLength: 6,
			enforceMaxLength: true,
			allowBlank : true,
			editable: true,
			width: '20%',
			inputAttrTpl: " data-qtip='Código do Tecnico' "
		},{
			fieldLabel : 'Nome',
			itemId: 'name',
			name: 'name',
			allowBlank : false,
			width: '60%',
			inputAttrTpl: " data-qtip='Nome do Tecnico' "
		}],
	};


Ext.define('Sam.view.technician.TechnicianForm', {
	extend: 'Ext.Panel',
	requires:['Sam.view.components.FormToolbar'],
	
	alias:  'widget.technicianform',
	
	itemId: 'technicianform',
	
	closable: true,
	
	layout:{
		type: 'fit',
	},
	
	items : [{
		xtype : 'form',

		defaultType : 'textfield',

		fieldDefaults : {
			labelWidth : 100
		},
		defaults:{
			allowBlank : false
		},

		layout : {
			type : 'vbox',
			align : 'stretch'
		},

		bodyPadding : 10,
		border : false,
		items : [ site, tec],
		
		scrollable: true,
		
		dockedItems: [{
			xtype: 'formtoolbar'
		}]
	} ]
	
});

