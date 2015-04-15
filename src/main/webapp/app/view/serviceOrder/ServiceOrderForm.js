	var equipmentInfo = {
	xtype : 'fieldset',
	defaultType : 'textfield',
	title : 'Informações do Equipamento',
	layout : {
		type : 'vbox',
		align : 'stretch',
	},

	items : [ {
		xtype: 'textfield',
		fieldLabel : 'Codigo do Equipamento',
		itemId: 'trg_equipment_id',
		editable: false,
		triggers: {
	        f3: {
	            handler: function() {
	            	 this.fireEvent('click', 1);
	            }
	        }
		}
	}, {
		fieldLabel : 'Modelo',
		itemId: 'equipment_model',
		readOnly : true,
	}, {
		fieldLabel : 'Fabricante',
		itemId: 'equipment_manufacturer',
		readOnly : true,
	}, {
		fieldLabel : 'Sub-Sistema',
		itemId: 'equipment_subsystem',
		readOnly : true,
	}, {
		fieldLabel : 'Local de Instalação',
		itemId: 'equipment_site',
		readOnly : true,
	} ],
};

var soInfo = {
	xtype : 'fieldset',
	defaultType : 'textfield',
	title : 'Informação da OS',
	layout : {
		type : 'vbox',
		align : 'stretch',
	},

	items : [ {
		xtype: 'container',
		defaults:{
			allowBlank : false
		},
		layout: {
			   type: 'hbox',
			   padding: '0 0 5 0',
			   margin: '0 0 0 0'
		},
		items : [{
			xtype:'textfield',
			fieldLabel: 'Codigo da Ordem de Servico',
			itemId: 'id',
			labelAlign: 'left',
			format: 'd/m/Y',
			margin: '0 0 0 0',
		}]
	
	}, {
		xtype: 'container',
		defaults:{
			allowBlank : false
		},
		layout: {
			   type: 'hbox',
			   padding: '0 0 5 0',
			   margin: '0 0 0 0'
		},
		items : [{
			xtype:'datefield',
			fieldLabel: 'Data de Início Prevista',
			itemId: 'start_date',
			labelAlign: 'left',
			format: 'd/m/Y',
			margin: '0 0 0 0',
		},{
			xtype:'timefield',
			fieldLabel: 'Hora de Início Prevista',
			itemId: 'start_hour',
			labelAlign: 'right',
			format: 'H:i',
			margin: '0 0 0 0'		
		}]
	
	}, {
		xtype: 'container',
		defaults:{
			allowBlank : false
		},
		layout: {
			   type: 'hbox',
			   padding: '0 0 5 0',
			   margin: '0 0 0 0',
		},
		items : [{
			
			
			xtype:'datefield',
			fieldLabel: 'Data de Término Prevista',
			itemId: 'end_date',
			format: 'd/m/Y',
			labelAlign: 'left',
			margin: '0 0 0 0'
		},{
			xtype:'timefield',
			fieldLabel: 'Hora de Término Prevista',
			itemId: 'end_hour',
			labelAlign: 'right',
			format: 'H:i',
			margin: '0 0 0 0'
		}]
	}, {
		fieldLabel : 'Tipo da OS',
		itemId: 'type',
		valueField: 'type',
        displayField: 'type',
		xtype : 'combobox',
		allowBlank : false
		
	},{
		fieldLabel : 'Observação',
		itemId: 'remark',
		xtype : 'textareafield',
		allowBlank : false
	} ]
};

Ext.define('Sam.view.serviceOrder.ServiceOrderForm', {
	extend: 'Ext.Panel',
	alias:  'widget.serviceorderform',
	
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

		bodyPadding : 10,
		border : false,
		items : [ equipmentInfo, soInfo ],
		
		dockedItems: [{
		    xtype: 'toolbar',
		    dock: 'bottom',
		    
		    items: [
		    {
		    	xtype: 'tbfill'
		    },{
		        xtype:'button',
		    	itemId:'btnOk',
		    	text:'Confirma',
		        tooltip:'Confirma',
		        cls:'x-btn-default-small',
		        iconCls: 'tick-button'
		    }]
		}]
	} ]
	
});
