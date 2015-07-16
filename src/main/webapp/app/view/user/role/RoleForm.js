Ext.define('Sam.view.user.role.RoleForm', {
	extend: 'Ext.Panel',
	requires:['Sam.view.components.FormToolbar'],
	
	alias:  'widget.userroleform',
	
	itemId: 'userroleform',
	
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
			inputAttrTpl: " data-qtip='Código do Role' "
		},{
			fieldLabel : 'Descrição',
			itemId: 'roleName',
			name: 'roleName',
			allowBlank : false,
			width: '60%',
			inputAttrTpl: " data-qtip='Descrição do Role' "
		}],
		
		scrollable: true,
		
		dockedItems: [{
			xtype: 'formtoolbar'
		}]
	} ]
	
});
