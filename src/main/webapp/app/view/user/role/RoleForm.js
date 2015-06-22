Ext.define('Sam.view.user.role.RoleForm', {
	extend: 'Ext.Panel',
	
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
			fieldLabel : 'Codigo',
			itemId: 'id',
			name: 'id',
			maxLength: 2,
			enforceMaxLength: true,
			allowBlank : true,
			editable: true,
			width: '20%',
			inputAttrTpl: " data-qtip='Codigo do Role' "
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
	} ]
	
});
