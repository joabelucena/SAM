/**** Creates Stores ****/
logicOperatorStore = Ext.create('Sam.view.components.store.LogicOperator'),
	relationalOperatorStore = Ext.create('Sam.view.components.store.RelationalOperator'),
	conditionType = Ext.create('Sam.view.task.ConditionType');

/**** Creates editors ****/
edtAlarm = {
			xtype:'textfield',
			triggers: {f3: {handler: function() { Ext.create('Sam.view.components.PopUp',{
				title: 'Selecionar Alarme',
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
			        		var row = Ext.ComponentQuery.query('#grdConditions',activeTab)[0].getSelection()[0];
			        		
			        		row.set({'field': record.get('id')});
			        		
			        		window.close();
			        		
			        	}
			        }
			        
				} ],
				items:	[Ext.create('Sam.view.alarm.AlarmGrid',{
					dockedItems:[],
				})],

			}).show()}}}
		};

edtType = {
		xtype:'textfield',
		triggers: {f3: {handler: function() { Ext.create('Sam.view.components.PopUp',{
			title: 'Selecionar Tipo de Alarme',
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
		        		var row = Ext.ComponentQuery.query('#grdConditions',activeTab)[0].getSelection()[0];
		        		
		        		row.set({'field': record.get('id')});
		        		
		        		window.close();
		        		
		        	}
		        }
		        
			} ],
			items:	[Ext.create('Sam.view.alarm.type.TypeGrid',{dockedItems:[]})],
			
		}).show()}}}
	};

/**** Creates Grid Objects that is going to be used in this page ****/

//Conditions Grid
grid1 = {
	xtype: 'gridpanel',
	
	itemId : 'grdConditions',
	
	plugins: [
	          Ext.create('Ext.grid.plugin.CellEditing', {
	              clicksToEdit: 2,
	              listeners: {
	                  beforeedit: function(e, editor){
	                	  
	                	  /**** Trava primeira celula da primeira linha ****/
	                	  if (editor.rowIdx == 0 && editor.colIdx == 1){
	                    	  return false;
	                      };
	                      
	                      
	                      /**** Trava demais campos quando tipo for MT ****/
	                      if (editor.store.getAt(editor.rowIdx).data.type == "MT"){
	                    	  if(editor.colIdx != 2 && editor.colIdx != 1){
	                    		  Ext.Msg.alert('MTBF', 'Tipo MTBF Selecionado. Não é necessario preencher os demais campos pois, os valores são gerados automaticamente.')
	                    		  return false;  
	                    	  }
	                      };
	                      
	                      /**** Muda editor do campo 'Campo' de acordo com o 'Tipo' selecionado ****/
	                      if(editor.colIdx == 3){
	                    	  
		                      if (editor.store.getAt(editor.rowIdx).data.type == ""){
		                    	
		                    	  //Exige que seja preenchido primeido o Campo 'Tipo'
		                    	  return false;
		                      
		                      }else if(editor.store.getAt(editor.rowIdx).data.type == "AT"){
		                    	  //TIPO DE ALARME
		                    	  editor.column.setEditor(edtType);
		                    	  
		                      }else if(editor.store.getAt(editor.rowIdx).data.type == "AL"){
		                    	  //ALARM
		                    	  editor.column.setEditor(edtAlarm);
		                      }
	                      	
	                      };
	                  }
	              }
	          })
	      ],
	
	width: '100%',
	height: 150,

	store : Ext.create('Ext.data.Store'),
	
	
	
	columns : {
		defaults: {
			menuDisabled: true,
			sortable: false,
		},
		
		items:[{
			dataIndex : 'seq',
			maxWidth: 32,
			minWidth: 32,
			menuDisabled: true,
			sortable: true,
		}, {
			text : 'Op. Logico',
			flex : 1,
			dataIndex : 'logicOper',
			editor: {
				store: logicOperatorStore,
				queryMode: 'local',
				valueField: 'id',
		        displayField: 'desc',
				xtype : 'combobox',
				editable: false,
				allowBlank: false,
	            
			}
		}, {
			text : 'Tipo',
			flex : 1,
			dataIndex : 'type',
			renderer: function(value){
				index = conditionType.findExact('id',value); 
	            if (index != -1){
	                rs = conditionType.getAt(index).data; 
	                return rs.desc; 
	            }
			},
			editor: {
				store: conditionType,
				queryMode: 'local',
				valueField: 'id',
		        displayField: 'desc',
				xtype : 'combobox',
				editable: false,
				allowBlank: false
			}
		}, {
			text : 'Campo',
			flex : 1,
			dataIndex : 'field',
			editor: 'textfield'
		}, {
			text : 'Op. Comp.',
			flex : 1,
			dataIndex : 'relOper',
			renderer: function(value){
				index = relationalOperatorStore.findExact('id',value); 
	            if (index != -1){
	                rs = relationalOperatorStore.getAt(index).data; 
	                return rs.desc; 
	            }
			},
			editor: {
				xtype : 'combobox',
				store: relationalOperatorStore,
				queryMode: 'local',
				valueField: 'id',
		        displayField: 'desc',
				editable: false,
				allowBlank: false			
			}
		}, {
			text : 'Valor',
			flex : 1,
			dataIndex : 'value',
			editor:{
				xtype: 'numberfield',
				minValue: 0
			}
			
		}, {
			text : 'Ação',
			xtype: 'actioncolumn',
			width: 70,
			align: 'center',
			menuDisabled: true,
			items: [{
				iconCls: 'minus-circle',
				tooltip: 'Excluir Linha',
				handler: function(view, rowIndex, colIndex, item, e, record, row) {
					 this.fireEvent('itemClick', view, rowIndex, colIndex, item, e, record, row);
				}
			}]
		}]
	}
};

//Equipments Grid
grid2 = {
	xtype: 'gridpanel',
	width: '100%',
	itemId: 'grdEquipments',
	scrollable: true,
	height: 150,
	dockedItems:[],
	store: Ext.create('Ext.data.Store'),
	columns: [{
		text : 'Código',
		dataIndex : 'id',
		flex : 1,
		filter : {
			type : 'string'
		}
	}, {
		text : 'Modelo',
		flex : 1,
		sortable : true,
		dataIndex : 'model',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Tipo',
		flex : 1,
		sortable : true,
		dataIndex : 'type',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Fabricante',
		flex : 1,
		sortable : true,
		dataIndex : 'manufacturer',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Local',
		flex : 1,
		sortable : true,
		dataIndex : 'site',
		filter : {
			type : 'string'
		}
	},{
		text : 'Ação',
		xtype: 'actioncolumn',
		width: 70,
		align: 'center',
		menuDisabled: true,
		sortable: false,
		items: [{
			iconCls: 'minus-circle',
			tooltip: 'Excluir Linha',
			handler: function(view, rowIndex, colIndex, item, e, record, row) {
				 this.fireEvent('itemClick', view, rowIndex, colIndex, item, e, record, row, 1);
			}
		}]
	}]
};

/****** End of grid creation ******/

/****** Header Items ******/ 
var h1 = {
		xtype: 'container',
		height: 60,
		width: '100%',
		defaults:{
			allowBlank : false,
			labelWidth : 55,
			padding: '0 5 0 5',
			labelAlign: 'right'
		},
		layout: {
			   type: 'hbox',
		},
		items : [{
			xtype:'textfield',
			fieldLabel: 'Código',
			padding: '10 5 0 5',
			width: '15%',
			inputAttrTpl: " data-qtip='Data de Início Prevista para a Ordem de Serviço' "
		},{
			xtype: 'tbseparator',
			width: '50%'
		},{
			xtype      : 'fieldcontainer',
            fieldLabel : 'Ativa',
            defaultType: 'radiofield',
            defaults: {
                flex: 1
            },
            layout: 'vbox',
            items: [
                {
                    boxLabel  : 'Sim',
                    name      : 'active',
                    checked	  : true,
                    inputValue: 'Y',
                }, {
                    boxLabel  : 'Não',
                    name      : 'active',
                    inputValue: 'N',
                }
            ]
		}]
	
};

var h2 = {

		xtype: 'container',
		height: 40,
		width: '100%',
		defaults:{
			allowBlank : false,
			labelWidth : 55,
			padding: '0 5 0 5',
			labelAlign: 'right'
		},
		layout: {
			   type: 'hbox',
			   padding: '0 5 0 5'
		},
		items : [{
			/**/
			
			xtype:'textfield',
			fieldLabel: 'Descrição',
			width: '30%',
//			id: 'asdfasdf',
			margin: '0 0 0 0',
			inputAttrTpl: " data-qtip='Hora de Término Prevista para a Ordem de Serviço' "
			
			/**/
			
		},{
			xtype: 'tbseparator',
			width: '35%'			
		},{
			xtype:'textfield',
			fieldLabel: 'Alarme',
			itemId: 'alarm',
			name: 'alarm',
			margin: '0 0 0 0',
			inputAttrTpl: " data-qtip='Evento que será criado quando a a regra for disparada.' ",
			triggers: {f3: {handler: function() { Ext.create('Sam.view.components.PopUp',{
				title: 'Selecionar Alarme',
				buttons : [{
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
			        		var field = Ext.ComponentQuery.query('#alarm',activeTab)[0];
			        		
			        		field.setValue(record.get('id'));
			        		
			        		window.close();
			        		
			        	}
			        }
			        
				} ],
				items:	[Ext.create('Sam.view.alarm.AlarmGrid',{dockedItems:[]})],
				
			}).show()}}}
		}]
		
};

var header = {
		xtype : 'container',
		defaultType : 'textfield',
		itemId: 'fldHeader',
		layout : {
			type : 'vbox',
		},
		
		items : [ h1, h2]
};


/******************** Middle: Conditions ********************/
var middle = {
	xtype : 'fieldset',
	defaultType : 'textfield',
	itemId: 'fldMiddle',
	height: 200,
	width: '100%',
	title : 'Condições',
	layout : {
		type : 'vbox'
	},
	defaults:{
		padding: '5 5 5 5'
	},
	
	items : [
	         grid1
	,{
		
			xtype : 'container',
			width: '100%',
			defaultType : 'textfield',
			layout : {
				type : 'hbox'
			},
			
			items : [{
				xtype: 'tbfill',
			},{
				xtype: 'button',
				itemId: 'btnDelAllCond',
				width: 50,
				iconCls: 'minus',
				tooltip: 'Apaga Todas as condições',
			},{
				xtype: 'tbseparator',
				width: 5
			},{
				xtype: 'button',
				itemId: 'btnAddCond',
				width: 50,
				iconCls: 'plus',
				tooltip: 'Adiciona nova condição',
			}]
	}]
};

/******************** Footer: Equipments ********************/
var footer = {
		xtype : 'fieldset',
		defaultType : 'textfield',
		itemId: 'fldfooter',
		height: 200,
		width: '100%',
		title : 'Equipamentos',
		layout : {
			type : 'vbox'
		},
		defaults:{
			padding: '5 5 5 5'
		},
		items : [
			grid2
		,{
				xtype : 'container',
				width: '100%',
				defaultType : 'textfield',
				layout : {
					type : 'hbox'
				},

				items : [{
					xtype: 'tbfill',
				},{
					xtype: 'button',
					itemId: 'btnDelAllEquip',
					width: 50,
					iconCls: 'minus',
					tooltip: 'Apaga todos equipamentos'
				},{
					xtype: 'tbseparator',
					width: 5
				},{
					xtype: 'button',
					itemId: 'btnAddEquip',
					width: 50,
					iconCls: 'plus',
					tooltip: 'Adiciona novo equipamento',
					handler: function(){
						
						Ext.create('Sam.view.components.PopUp',{
							title: 'Selecionar Equipamento',
							scope: this,
							itemId: 'taskform_equipments',
							items:	[Ext.create('Sam.view.equipment.EquipmentsGrid',{
								dockedItems:[{
									
									xtype: 'toolbar',
									dock: 'top',
									scope: this,								    
								    items: [{
						            	xtype:'button',
								    	itemId:'btnSlcAll',
								    	text:'Selecionar Todos',
								        tooltip:'Seleciona todos registros exibidos',
								        cls:'x-btn-default-small',
								        iconCls: 'tick-button',
								        handler: function(button){
								        	this.up('grid').getStore().each(function(rec){ rec.set('active', !rec.get('active')) })
								        }
						            },{
								    	xtype: 'tbfill'
								    }]
								}],
								columns:[{
									xtype: 'checkcolumn',
									dataIndex: 'active',
									text: 'Selecione',
									width: 100,
								},{
									text : 'Código',
									dataIndex : 'id',
									flex : 1,
									filter : {
										type : 'string'
									}
								}, {
									text : 'Modelo',
									flex : 1,
									sortable : true,
									dataIndex : 'model',
									filter : {
										type : 'string'
									}
								}, {
									text : 'Tipo',
									flex : 1,
									sortable : true,
									dataIndex : 'type',
									filter : {
										type : 'string'
									}
								}, {
									text : 'Fabricante',
									flex : 1,
									sortable : true,
									dataIndex : 'manufacturer',
									filter : {
										type : 'string'
									}
								}, {
									text : 'Local',
									flex : 1,
									sortable : true,
									dataIndex : 'site',
									filter : {
										type : 'string'
									}
								}]
							})],
							
						}).show()
					}
				}]
		}]
};

Ext.define('Sam.view.task.TaskForm', {
	extend: 'Ext.Panel',
	requires:['Sam.view.components.FormToolbar'],
	
	alias:  'widget.taskform',
	
	itemId: 'taskform',

	closable : true,
	
	layout : {
		type : 'fit',
	},

	items : [{
		xtype : 'form',

		defaultType : 'textfield',
		defaults:{
			allowBlank : false
		},

		layout : {
			type : 'vbox',
			align : 'stretch'
		},

		bodyPadding : 10,
		border : false,
		items : [ header , middle , footer],

		scrollable: true,
		
		dockedItems: [{
			xtype: 'formtoolbar'
		}]
	}]
});