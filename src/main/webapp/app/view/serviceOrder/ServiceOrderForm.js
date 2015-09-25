/******* Equipment 'container' *******/
var equipmentInfo = {
	xtype : 'fieldset',
	defaultType : 'textfield',
	title : 'Informações do Equipamento',
	itemId: 'equipmentInfo',
	layout : {
		type : 'vbox',
	},

	items : [ {
		xtype: 'textfield',
		fieldLabel : 'Código do Equipamento',
		itemId: 'equipment_id',
		name: 'equipment_id',
		editable: false,
		width: '40%',
		allowBlank : false,
		inputAttrTpl: " data-qtip='Código do Equipamento da Ordem de Serviço' ",
		triggers: {f3: {handler: function() {
			
			Ext.create('Sam.view.components.PopUp',{
					title: 'Selecionar Equipamento',
					buttons : [ {
						text : 'Confirma',
						itemId: 'submit',
				        cls:'x-btn-default-small',
				        iconCls: 'tick-button',
				        handler: function(button) {
				        	
				        	//Aba Objecto Pai
			        		var activeTab = Ext.getCmp('viewportpanel').getActiveTab(),
			        			window = button.up('window'),
			        			record = button.up('window').down('grid').getSelection()[0],
			        			form = Ext.ComponentQuery.query('form',activeTab)[0].getForm();
			        		
				        	if(record){
				        		
				        		//Loads Equipment record
				        		form.loadRecord(record)
				        		
				        		window.close();
				        		
				        	}
				        }
				        
					} ],
					items:	[Ext.create('Sam.view.equipment.EquipmentsGrid',{dockedItems:[]})],
					
				}).show()
			}}}
	}, {
		fieldLabel : 'Modelo',
		itemId: 'equipment_model',
		name: 'equipment_model',
		readOnly : true,
		width: '50%',
		inputAttrTpl: " data-qtip='Modelo do Equipamento da Ordem de Serviço' ",
	}, {
		fieldLabel : 'Fabricante',
		itemId: 'equipment_manuf',
		name: 'equipment_manuf',
		readOnly : true,
		width: '50%',
		inputAttrTpl: " data-qtip='Fabricante do Equipamento da Ordem de Serviço' ",
	}, {
		fieldLabel : 'Sub-Sistema',
		itemId: 'equipment_system',
		name: 'equipment_system',
		readOnly : true,
		width: '60%',
		inputAttrTpl: " data-qtip='Sub-Sistema do Equipamento da Ordem de Serviço' ",
	}, {
		fieldLabel : 'Local de Instalação',
		itemId: 'equipment_site',
		name: 'equipment_site',
		readOnly : true,
		width: '60%',
		inputAttrTpl: " data-qtip='Local do Equipamento da Ordem de Serviço' ",
	} ],
};

/******* Service Order Details 'container' *******/
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
			fieldLabel: 'Código da Ordem de Servico',
			itemId: 'id',
			name: 'id',
			labelAlign: 'left',
			format: 'd/m/Y',
			margin: '0 0 0 0',
			allowBlank : true,
			inputAttrTpl: " data-qtip='Código da Ordem de Serviço' "
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
		items : [
		         {
		        	 xtype:'datefield',
		        	 vtype: 'daterange',
		        	 endDateField: 'end_forecast_date', // id of the end date field
		        	 fieldLabel: 'Data de Início Prevista',
		        	 name: 'start_forecast_date',
		        	 itemId: 'start_forecast_date',
		        	 labelAlign: 'left',
		        	 format: 'd/m/Y',
		        	 minValue: new Date(),
		        	 margin: '0 0 0 0',
		        	 editable: false,
		        	 inputAttrTpl: " data-qtip='Data de Início Prevista da Ordem de Serviço' "
		         },{
		        	 xtype:'timefield',
		        	 fieldLabel: 'Hora de Início Prevista',
		        	 itemId: 'start_forecast_time',
		        	 name: 'start_forecast_time',
		        	 labelAlign: 'right',
		        	 format: 'H:i',
		        	 margin: '0 0 0 0',
		        	 editable: false,
		        	 inputAttrTpl: " data-qtip='Hora de Início Prevista da Ordem de Serviço' "
		         }			
			]
	
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
			vtype: 'daterange',
			startDateField: 'start_forecast_date', // id of the start date field
			fieldLabel: 'Data de Término Prevista',
			name: 'end_forecast_date',
			itemId: 'end_forecast_date',
			format: 'd/m/Y',
			labelAlign: 'left',
			margin: '0 0 0 0',
			editable: false,
			inputAttrTpl: " data-qtip='Data de Término Prevista da Ordem de Serviço' "
		},{
			xtype:'timefield',
			fieldLabel: 'Hora de Término Prevista',
			itemId: 'end_forecast_time',
			name: 'end_forecast_time',
			labelAlign: 'right',
			format: 'H:i',
			margin: '0 0 0 0',
			editable: false,
			inputAttrTpl: " data-qtip='Hora de Término Prevista da Ordem de Serviço' "
		}]
	}, {
		fieldLabel : 'Tipo da OS',
		itemId: 'type_id',
		name: 'type_id',
		valueField: 'id',
        displayField: 'desc',
		xtype : 'combobox',
		store: Ext.data.Store({
			fields: ['id','desc'],
			autoLoad: true,
			proxy: {
		         type: 'ajax',
		         url: 'so/load/type',
		         reader: {
		             type: 'json',
		             root: 'data'
		         }
		     },
		}),
		allowBlank : false,
		editable: false,
		width: '40%',
		inputAttrTpl: " data-qtip='Tipo da Ordem de Serviço' "
	}, {
		fieldLabel : 'Prioridade de Execução',
		itemId: 'priority_id',
		name: 'priority_id',
		valueField: 'id',
        displayField: 'desc',
		xtype : 'combobox',
		store: Ext.data.Store({
			fields: ['id','desc'],
			autoLoad: true,
			proxy: {
		         type: 'ajax',
		         url: 'severity/load',
		         reader: {
		             type: 'json',
		             root: 'data'
		         }
		     },
		}),
		allowBlank : false,
		editable: false,
		width: '40%',
		inputAttrTpl: " data-qtip='Prioridade de Execução da Ordem de Serviço' "
	},{
		fieldLabel : 'Observação',
		itemId: 'remark',
		name: 'remark',
		xtype : 'textareafield',
		allowBlank : false,
		width: '60%',
		inputAttrTpl: " data-qtip='Observação da Ordem de Serviço' "
	} ]
};

/******* HEADER *******/
var header = {
	xtype: 'container',

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
		
		scrollable: true,

	} ]	
};

/******* FOOTER *******/
var footer = {
	xtype: 'gridpanel',
	
	itemId : 'conditionsgrid',
	
	width: '100%',
	height: '90%',

	store : Ext.create('Ext.data.Store'),
	
	columns : [{
		text : 'Cod. Serv.',
		flex : 1,
		sortable : true,
		dataIndex : 'desc'
	}, {
		text : 'Tipo',
		flex : 1,
		sortable : true,
		dataIndex : 'desc'
	}, {
		text : 'Técnico',
		flex : 1,
		sortable : true,
		dataIndex : 'alarm_desc'
	}, {
		text : 'Inicio',
		flex : 1,
		sortable : true,
		dataIndex : 'alarm_desc'
	}, {
		text : 'Término',
		flex : 1,
		sortable : true,
		dataIndex : 'alarm_desc'
	}, {
		text : 'Observação',
		flex : 1,
		sortable : true,
		dataIndex : 'alarm_desc'
	}, {
		text : 'Ação',
		flex : 1,
		sortable : true,
		dataIndex : 'alarm_desc'
	}]
};


/******* Service Order Main Page *******/
Ext.define('Sam.view.serviceOrder.ServiceOrderForm', {
	extend: 'Ext.Panel',
	alias:  'widget.serviceorderform',
	
	itemId: 'serviceorderform',

    closable: false,
	
	layout: 'border',
    width: 500,
    height: 400,

    bodyBorder: false,
    
    defaults: {
        collapsible: false,
        split: true
    },

    items: [
            {
                title: 'Ordem de Serviço',
                items:[header],
                itemId: 'center',
                region: 'center',
                scrollable: true,
                margin: '5 0 0 0',
            },{
				title: 'Apontamentos',
				itemId: 'footer',
				items: [footer],
				collapsible: true,
				region: 'south',
				margin: '5 0 0 0',
				minHeight: 250,
				Height: 250,
            }],
            
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
			xtype: 'button',
			width: 50,
			disabled: true,
			iconCls: 'plus',
			handler: function(){
				alert('Ola');
			}
		},{
	    	xtype: 'tbseparator'
	    },{
	        xtype:'button',
	    	itemId:'btnSubmit',
	    	text:'Confirma',
	        tooltip:'Confirma',
	        cls:'x-btn-default-small',
	        iconCls: 'tick-button'
	    }]
	}]
});