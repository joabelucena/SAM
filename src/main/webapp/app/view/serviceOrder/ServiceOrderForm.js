/**** Creates editors ****/
var edtJobs = {
			xtype:'textfield',
			allowBlank : false,
			triggers: {f3: {handler: function() { Ext.create('Sam.view.components.PopUp',{
				title: 'Selecionar Tipo de Serviço',
				buttons : [ {
					text : 'Confirma',
					itemId: 'submit',
			        cls:'x-btn-default-small',
			        iconCls: 'tick-button',
			        handler: function(button) {
			        	
			        	//Aba Objecto Pai
		        		var activeTab = Ext.getCmp('viewportpanel').getActiveTab(),
		        			window = button.up('window'),
		        			record = button.up('window').down('grid').getSelection()[0];
		        		
			        	if(record){

			        		//Conditions grid selection
			        		var row = Ext.ComponentQuery.query('#occurrencesGrid',activeTab)[0].getSelection()[0];
			        		
			        		row.set({'service_id': record.get('id')});
			        		
			        		window.close();
			        		
			        	}
			        }
			        
				} ],
				items:	[Ext.create('Sam.view.serviceOrder.job.JobGrid',{
					dockedItems:[],
				})],

			}).show()}}}
};

var edtTec = {
		xtype:'textfield',
		allowBlank : false,
		triggers: {f3: {handler: function() { Ext.create('Sam.view.components.PopUp',{
			title: 'Selecionar Tecnico',
			buttons : [ {
				text : 'Confirma',
				itemId: 'submit',
		        cls:'x-btn-default-small',
		        iconCls: 'tick-button',
		        handler: function(button) {
		        	
		        	//Aba Objecto Pai
	        		var activeTab = Ext.getCmp('viewportpanel').getActiveTab(),
	        			window = button.up('window'),
	        			record = button.up('window').down('grid').getSelection()[0];
	        		
		        	if(record){

		        		//Conditions grid selection
		        		var row = Ext.ComponentQuery.query('#occurrencesGrid',activeTab)[0].getSelection()[0];
		        		
		        		row.set({'technician_id': record.get('id')});
		        		
		        		window.close();
		        		
		        	}
		        }
		        
			} ],
			items:	[Ext.create('Sam.view.technician.TechnicianGrid',{
				dockedItems:[],
			})],

		}).show()}}}
};


/******* Equipment 'container' *******/
var equipmentInfo = {
	xtype : 'groupfield',
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
			        			form = Ext.ComponentQuery.query('form',activeTab)[0].getForm(),
			        			groupField = Ext.ComponentQuery.query('#equipmentInfo',activeTab)[0];
			        		
				        	if(record){
				        		
				        		groupField.loadRecord(record);
				        		
				        		//Loads Equipment record
//				        		form.loadRecord(record)
				        		
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
		fieldLabel : 'Tipo',
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
	itemId : 'occurrencesGrid',
	width: '100%',
	height: '100%',
	border: false,
	
	plugins : [ {
		ptype : 'cellediting',
		clicksToEdit : 2	
	} ],
	
	columns : {
		
		defaults:{
			menuDisabled: true,
			sortable: false,
			editor: 'textfield'
		},
		
		items: [
			{
				text : 'Serv.',
				width: 70,
				sortable : true,
				dataIndex : 'service_id',
				editor: edtJobs
			}, {
				text : 'Técnico',
				width: 100,
				sortable : true,
				dataIndex : 'technician_id',
				editor: edtTec
			}, 
			
			
			/* DateTimeField
			{
				text : 'Inicio',
				xtype: 'datecolumn',
				format: 'd/m/Y H:i',
				width: 150,
				sortable : true,
				dataIndex : 'start',
                editor: {
                    xtype: 'datetimefield',
                    allowBlank : false,
                    format: 'd/m/Y H:i',
                }
			},{
				text : 'Término',
				xtype: 'datecolumn',
				format: 'd/m/Y H:i',
				width: 150,
				sortable : true,
				dataIndex : 'end',
                editor: {
                    xtype: 'datetimefield',
                    allowBlank : false,
                    format: 'd/m/Y H:i',
                }
			},
			*******************/
			
			
			{
				text : 'Dt. Inicio',
				xtype: 'datecolumn',
				format: 'd/m/Y',
				width: 100,
				sortable : true,
				dataIndex : 'start_date',
                editor: {
                    xtype: 'datefield',
                    allowBlank : false,
                    format: 'd/m/Y',
                }
			}, {
				text : 'Hr. Inicio',
				xtype: 'datecolumn',
				format: 'H:i',
				width: 100,
				sortable : true,
				dataIndex : 'start_time',
                editor: {
                    xtype: 'timefield',
                    allowBlank : false,
                    format: 'H:i',
                }
			}, {
				text : 'Dt. Término',
				xtype: 'datecolumn',
				format: 'd/m/Y',
				width: 100,
				sortable : true,
				dataIndex : 'end_date',
                editor: {
                    xtype: 'datefield',
                    allowBlank : false,
                    format: 'd/m/Y',
                }
			}, {
				text : 'Hr. Término',
				xtype: 'datecolumn',
				format: 'H:i',
				width: 100,
				sortable : true,
				dataIndex : 'end_time',
                editor: {
                    xtype: 'timefield',
                    allowBlank : false,
                    format: 'H:i',
                }
			},{
				text : 'Observação',
				flex : 1,
				sortable : true,
				dataIndex : 'remark'
			}, {
				text : 'Ação',
				xtype: 'actioncolumn',
				itemId: 'actionClm',
				width: 70,
				align: 'center',
				items: [{
					iconCls: 'minus-circle',
					tooltip: 'Excluir Linha',
					handler: function(view, rowIndex, colIndex, item, e, record, row) {
		
						Ext.MessageBox.show({
					        title: 'Atenção',
					        msg: 'Confirma exclusão da linha?',
					        buttons: Ext.MessageBox.OKCANCEL,
					        icon: Ext.MessageBox.WARNING,
					        fn: function(btn,  knowId, knowCheck){
					        	if(btn == 'ok'){
					            	view.getStore().remove(record);
					            }			            	
					        }
						});
						
					
					}
				}]
			}
	]
	
	}
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
				layout: 'fit',
				scrollable: true,
				minHeight: 250,
				height: 250,
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
			itemId: 'btnAddJob',
			tooltip:'Inclui novo apontamento',
			width: 50,
			disabled: true,
			iconCls: 'plus',
			handler: function(button){

				var store = button.up('panel').down('grid').getStore(),	// grid Store
					record = store.getAt(store.data.length-1),			// last record
					lAdd = false;										// boolean variable for validating last record
				
				//Se nao for primeiro registro
				if(!record){
					lAdd = true;
				}else{
					lAdd = record.isValid();
				}
				
				//Se passou pela validação adiciona registro
				if(lAdd){
					
					//Adiciona novo registro
					store.add(Ext.create('Sam.model.ServiceOrderOccurrence'));
					
				}else{
					
					Ext.MessageBox.show({
				        title: 'SAM | Info',
				        msg:  'Existem campos que não foram preenchidos. Preencha todos os campos corretamente',
				        buttons: Ext.MessageBox.OK,
				        icon: Ext.MessageBox.WARNING
					});
					
				}
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