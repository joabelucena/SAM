Ext.define('Sam.view.serviceOrder.status.StatusForm', {
	extend: 'Ext.Panel',
	
	alias:  'widget.serviceorderstatusform',
	
	itemId: 'serviceorderstatusform',
	
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
			inputAttrTpl: " data-qtip='Codigo do Tipo de Ordem de Serviço' "
		},{
			fieldLabel : 'Descrição',
			itemId: 'desc',
			name: 'desc',
			allowBlank : false,
			width: '60%',
			inputAttrTpl: " data-qtip='Descrição do Tipo de Ordem de Serviço' "
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
	}]
});
