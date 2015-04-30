var protocol = {
	xtype : 'fieldset',
	defaultType : 'textfield',
	title : 'Protocolo',
	itemId: 'fldProtocol',
	layout : {
		type : 'vbox',
	},

	items : [ {
		xtype: 'textfield',
		fieldLabel : 'Codigo',
		itemId: 'prot_id',
		name: 'prot_id',
		editable: false,
		width: '40%',
		allowBlank : false,
		inputAttrTpl: " data-qtip='Código do Protocolo' ",
		triggers: {
	        f3: {
	            handler: function() {
	            	 this.fireEvent('click');
	            }
	        }
		}
	}, {
		fieldLabel : 'Descrição',
		itemId: 'prot_desc',
		name: 'prot_desc',
		readOnly : true,
		width: '50%',
		inputAttrTpl: " data-qtip='Descrição do Protocolo' ",
	}],
};


var model = {
		xtype : 'fieldset',
		defaultType : 'textfield',
		title : 'Dados do Modelo',
		itemId: 'fldModel',
		layout : {
			type : 'vbox',
		},

		items : [{
			fieldLabel : 'Codigo',
			itemId: 'id',
			name: 'id',
			allowBlank : true,
			editable: false,
			width: '20%',
			inputAttrTpl: " data-qtip='Codigo do Modelo' "
		},{
			fieldLabel : 'Descrição',
			itemId: 'desc',
			name: 'desc',
			allowBlank : false,
			width: '60%',
			inputAttrTpl: " data-qtip='Descrição do Modelo' "
		}],
	};


Ext.define('Sam.view.equipment.model.ModelForm', {
	extend: 'Ext.Panel',
	
	alias:  'widget.equipmentmodelform',
	
	itemId: 'equipmentmodelform',
	
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
		items : [ protocol, model ],
		
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

