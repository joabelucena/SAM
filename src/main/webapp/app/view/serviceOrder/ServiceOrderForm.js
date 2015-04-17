var newStatus = {
	xtype : 'fieldset',
	itemId: 'fldNewStatus',
	defaultType : 'textfield',
	title : 'Novo Estado',
	layout : {
		type : 'vbox',
		align : 'stretch',
	},

	items : [{
		fieldLabel : 'Estado',
		itemId: 'n_cmbStatus',
		valueField: 'id',
        displayField: 'desc',
		xtype : 'combobox',
		allowBlank : false,
		editable: false
	},{
		fieldLabel : 'Equipamento Parado',
		itemId: 'n_cmbEquipStop',
		store:  Ext.create('Sam.view.components.store.ComboBox'),
		queryMode: 'local',
		valueField: 'id',
        displayField: 'desc',
		xtype : 'combobox',
		allowBlank : false,
		editable: false
	},{
		fieldLabel : 'Observação',
		itemId: 'n_txtRemark',
		xtype : 'textareafield',
		allowBlank : false
	}],
};

var equipmentInfo = {
	xtype : 'fieldset',
	defaultType : 'textfield',
	title : 'Informações do Equipamento',
	itemId: 'equipmentInfo',
	layout : {
		type : 'vbox',
		//align : 'stretch',
	},

	items : [ {
		xtype: 'textfield',
		fieldLabel : 'Codigo do Equipamento',
		itemId: 'trg_equipment_id',
		editable: false,
		width: '40%',
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
		width: '50%',
	}, {
		fieldLabel : 'Fabricante',
		itemId: 'equipment_manufacturer',
		readOnly : true,
		width: '50%',
	}, {
		fieldLabel : 'Sub-Sistema',
		itemId: 'equipment_subsystem',
		readOnly : true,
		width: '60%',
	}, {
		fieldLabel : 'Local de Instalação',
		itemId: 'equipment_site',
		readOnly : true,
		width: '60%',
	} ],
};

var soInfo = {
	xtype : 'fieldset',
	defaultType : 'textfield',
	title : 'Informação da OS',
	itemId: 'soInfo',
	layout : {
		type : 'vbox',
		//align : 'stretch',
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
			editable: false
		},{
			xtype:'timefield',
			fieldLabel: 'Hora de Início Prevista',
			itemId: 'start_hour',
			labelAlign: 'right',
			format: 'H:i',
			margin: '0 0 0 0',
			editable: false
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
			margin: '0 0 0 0',
			editable: false
		},{
			xtype:'timefield',
			fieldLabel: 'Hora de Término Prevista',
			itemId: 'end_hour',
			labelAlign: 'right',
			format: 'H:i',
			margin: '0 0 0 0',
			editable: false
		}]
	}, {
		fieldLabel : 'Tipo da OS',
		itemId: 'type',
		valueField: 'id',
        displayField: 'desc',
		xtype : 'combobox',
		allowBlank : false,
		editable: false,
		width: '30%'		
	},{
		fieldLabel : 'Observação',
		itemId: 'remark',
		xtype : 'textareafield',
		allowBlank : false,
		width: '60%'
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
		items : [ newStatus, equipmentInfo, soInfo ],
		
		scrollable: true,
		
		dockedItems: [{
		    xtype: 'toolbar',
		    dock: 'bottom',
		    
		    items: [{
		        xtype:'button',
		        itemId:'btnShowLog',
		    	text:'Visualizar LOG',
		        tooltip:'Vizualiza Log da Ordem de Serviço',
		        cls:'x-btn-default-small',
		        iconCls: 'tick-button'
		    },{
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
