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
		fieldLabel : 'Codigo',
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
			fieldLabel : 'Codigo',
			itemId: 'id',
			name: 'id',
			allowBlank : true,
			editable: true,
			width: '20%',
			inputAttrTpl: " data-qtip='Codigo do Tecnico' "
		},{
			fieldLabel : 'Descrição',
			itemId: 'desc',
			name: 'desc',
			allowBlank : false,
			width: '60%',
			inputAttrTpl: " data-qtip='Descrição do Tecnico' "
		}],
	};


Ext.define('Sam.view.technician.TechnicianForm', {
	extend: 'Ext.Panel',
	
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

