Ext.define('Sam.view.severity.SeverityForm', {
	extend: 'Ext.Panel',
	requires:['Sam.view.components.FormToolbar'],
	
	alias:  'widget.severityform',
	
	itemId: 'severityform',
	
	closable: true,
	
	layout:{
		type: 'fit',
	},
	
	items : [ {
		xtype : 'form',

		defaultType : 'textfield',

		fieldDefaults : {
			labelWidth : 100
		},
		defaults:{
			allowBlank : false
		},

		layout : {
			type : 'vbox'
		},

		bodyPadding : 10,
		border : false,
		items : [{
			fieldLabel : 'Código',
			itemId: 'id',
			name: 'id',
			maxLength: 2,
			enforceMaxLength: true,
			allowBlank : true,
			editable: true,
			width: '20%',
			inputAttrTpl: " data-qtip='Código da Severidade' "
		},{
			fieldLabel : 'Descrição',
			itemId: 'desc',
			name: 'desc',
			allowBlank : false,
			width: '60%',
			inputAttrTpl: " data-qtip='Descrição da Severidade' "
		}],
		
		scrollable: true,
		
		dockedItems: [{
			xtype: 'formtoolbar'
		}]
	} ]
	
});
