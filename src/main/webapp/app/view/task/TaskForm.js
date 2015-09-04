{
/**** Creates Stores ****/
var logicOperatorStore = Ext.create('Sam.view.components.store.LogicOperator'),
	relationalOperatorStore = Ext.create('Sam.view.components.store.RelationalOperator'),
	conditionType = Ext.create('Sam.view.task.ConditionType');

/**** Creates editors ****/
var edtAlarm = {
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

var edtType = {
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
var grid1 = {
	xtype: 'gridpanel',
	
	itemId : 'grdConditions',
	

			plugins : [ {
			ptype : 'cellediting',
			clicksToEdit : 2,
			listeners : {
				beforeedit : function(e, editor) {

					/** ** Trava primeira celula da primeira linha *** */
					if (editor.rowIdx == 0 && editor.colIdx == 1) {
						return false;
					};

					/** ** Trava demais campos quando tipo for MT *** */
					if (editor.store.getAt(editor.rowIdx).data.type == "MT") {
						if (editor.colIdx != 2 && editor.colIdx != 1) {
							Ext.Msg.alert('MTBF','Tipo MTBF Selecionado. Não é necessario preencher os demais campos pois, os valores são gerados automaticamente.');
							return false;
						}
					};

					/**
					 * ** Muda editor do campo 'Campo' de acordo com o 'Tipo'
					 * selecionado ***
					 */
					if (editor.colIdx == 3) {

						if (editor.store.getAt(editor.rowIdx).data.type == "") {

							// Exige que seja preenchido primeiro o Campo 'Tipo'
							return false;

						} else if (editor.store.getAt(editor.rowIdx).data.type == "AT") {
							// TIPO DE ALARME
							editor.column.setEditor(edtType);

						} else if (editor.store.getAt(editor.rowIdx).data.type == "AL") {
							// ALARM
							editor.column.setEditor(edtAlarm);
						}

					};
				}
			}

		} ],
	
	width: '100%',
	height: 150,

//	store : 'Sam.store.TaskCondition',
	
	
	columns : {
		defaults: {
			menuDisabled: true,
			sortable: false,
		},
		
		items: [{
			dataIndex : 'seq',
			maxWidth: 32,
			minWidth: 32,
			menuDisabled: true,
			sortable: true,
		}, {
			text : 'Op. Logico',
			flex : 1,
			dataIndex : 'logicOper',
			renderer: function(value){
				index = logicOperatorStore.findExact('id',value); 
	            if (index != -1){
	                rs = logicOperatorStore.getAt(index).data; 
	                return rs.desc; 
	            }
			},
			editor: {
				xtype : 'combobox',
				editable: false,
				store: logicOperatorStore,
				queryMode: 'local',
				valueField: 'id',
		        displayField: 'desc',								
				allowBlank: false	            
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
			itemId: 'actionClm',
			width: 70,
			align: 'center',
			items: [{
				iconCls: 'minus-circle',
				tooltip: 'Excluir Linha',
				handler: function(view, rowIndex, colIndex, item, e, record, row) {

					
					var store = Ext.ComponentQuery.query('#grdConditions')[0].getStore();
					
					Ext.MessageBox.show({
				        title: 'Atenção',
				        msg: 'Confirma exclusão da linha?',
				        buttons: Ext.MessageBox.OKCANCEL,
				        icon: Ext.MessageBox.WARNING,
				        fn: function(btn,  knowId, knowCheck){
				            if(btn == 'ok'){
				            	
				            	store.remove(record);

				            	//Renumera as Sequencias
			                	Ext.each(store.data.items,function(item,index){
			                		item.set('seq',Ext.util.Format.leftPad(index+1,2,'0'));
			                	});
				            	
				            } 
				            	
				        }
					});
				}
			}]
		}]
	}
};

//Equipments Grid
var grid2 = {
	xtype: 'gridpanel',
	width: '100%',
	itemId: 'grdEquipments',
	scrollable: true,
	height: 150,
	dockedItems:[],
//	store: Ext.create('Sam.store.Equipment',{
//		autoLoad: false
//	}),
	columns: {
	defaults: {
			menuDisabled: true,
			sortable: false,
		},
		items:[
		{
		text : 'Código',
		dataIndex : 'id',
		flex : 1,
		filter : {
			type : 'string'
		}
	}, {
		text : 'Modelo',
		flex : 1,
		dataIndex : 'model_desc',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Tipo',
		flex : 1,
		dataIndex : 'type_desc',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Fabricante',
		flex : 1,
		dataIndex : 'manufacturer_desc',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Local',
		flex : 1,
		dataIndex : 'site_desc',
		filter : {
			type : 'string'
		}
	},{
		text : 'Ação',
		xtype: 'actioncolumn',
		itemId: 'actionClm',
		width: 70,
		align: 'center',
		items: [{
			iconCls: 'minus-circle',
			tooltip: 'Excluir Linha',
			handler: function(view, rowIndex, colIndex, item, e, record, row) {

				
				var store = Ext.ComponentQuery.query('#grdEquipments')[0].getStore();
				
				Ext.MessageBox.show({
			        title: 'Atenção',
			        msg: 'Confirma exclusão da linha?',
			        buttons: Ext.MessageBox.OKCANCEL,
			        icon: Ext.MessageBox.WARNING,
			        fn: function(btn,  knowId, knowCheck){
			            if(btn == 'ok'){
			            	
			            	store.remove(record);

			            } else if(btn == 'cancel') {
			            	
			            }
			            	
			            }
			        });
				
			
			}
		}]
	}]
		}
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
			itemId: 'id',
			name: 'id',
			padding: '10 5 0 5',
			width: '15%',
			inputAttrTpl: " data-qtip='Código da regra' "
		},{
			xtype: 'tbseparator',
			width: '50%'
		},{
			xtype      : 'fieldcontainer',
            fieldLabel : 'Ativa',
            itemId: 'active',
            name: 'active',
            defaultType: 'radiofield',
            inputAttrTpl: " data-qtip='Indica se a regra deve ser executada ou não.' ",
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
			itemId: 'desc',
			name: 'desc',
			width: '30%',
			margin: '0 0 0 0',
			inputAttrTpl: " data-qtip='Breve descrição do que a regra faz.' "
			
			/**/
			
		},{
			xtype: 'tbseparator',
			width: '35%'			
		},{
			xtype:'textfield',
			fieldLabel: 'Alarme',
			itemId: 'alarm',
			name: 'alarm_id',
			margin: '0 0 0 0',
			inputAttrTpl: " data-qtip='Evento que será criado quando a regra for disparada.' ",
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
			        
				}],
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
				handler: function(button){
					
					var store = Ext.ComponentQuery.query('#grdConditions')[0].getStore();
					
					if(store.data.length > 0){
						
						Ext.MessageBox.show({
					        title: 'Atenção',
					        msg: 'Deseja apagar todas as condições?',
					        buttons: Ext.MessageBox.OKCANCEL,
					        icon: Ext.MessageBox.WARNING,
					        fn: function(btn,  knowId, knowCheck){
					            if(btn == 'ok'){
					            	
					            	store.removeAll();
					            	
					            } else if(btn == 'cancel') {
					            	
					            }
					            	
					            }
					        });
					}
				}
			},{
				xtype: 'tbseparator',
				width: 5
			},{
				xtype: 'button',
				itemId: 'btnAddCond',
				width: 50,
				iconCls: 'plus',
				tooltip: 'Adiciona nova condição',
				handler: function(button){

					var store = Ext.ComponentQuery.query('#grdConditions')[0].getStore(),
						record = store.getAt(store.data.length-1),
						lAdd = false;
					
					
					//Se nao for primeiro registro
					if(!record){
						lAdd = true;
					}else{
						
						//Validações
						if(record.get('type') != "MT"){
							
							if(record.get('logicOper') == "" || record.get('type') == "" || + 
								record.get('field') == "" || record.get('relOper') == "" || record.get('value') == 0   ){
								lAdd = false;
							}else{
								lAdd = true;
							}
						}else{
							lAdd = !record.get('logicOper') == "";
						}
					}
					
					//Se passou pela validação adiciona registro
					if(lAdd){
						var condition = Ext.create('Sam.model.TaskCondition',{
							seq: Ext.util.Format.leftPad(store.data.length+1,2,'0') 
						});
						
						//trava primeira condicao
						if(store.data.length === 0){
							condition.set('logicOper','-');
						}
						
						//Adiciona novo registro
						store.add(condition);
					}else{
						Ext.Msg.alert('Atenção', 'Preencha os campos corretamente.')
					}
				}
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
					tooltip: 'Apaga todos equipamentos',
					handler: function(button){

						
						var store = Ext.ComponentQuery.query('#grdEquipments')[0].getStore();
						
						if(store.data.length > 0){
							
							Ext.MessageBox.show({
						        title: 'Atenção',
						        msg: 'Deseja apagar todas os equipamentos?',
						        buttons: Ext.MessageBox.OKCANCEL,
						        icon: Ext.MessageBox.WARNING,
						        fn: function(btn,  knowId, knowCheck){
						            if(btn == 'ok'){
						            	
						            	store.removeAll();
						            	
						            } else if(btn == 'cancel') {
						            	
						            }
						            	
						            }
						        });
						}
					
					}
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
									dataIndex : 'model_desc',
									filter : {
										type : 'string'
									}
								}, {
									text : 'Tipo',
									flex : 1,
									sortable : true,
									dataIndex : 'type_desc',
									filter : {
										type : 'string'
									}
								}, {
									text : 'Fabricante',
									flex : 1,
									sortable : true,
									dataIndex : 'manufacturer_desc',
									filter : {
										type : 'string'
									}
								}, {
									text : 'Local',
									flex : 1,
									sortable : true,
									dataIndex : 'site_desc',
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
	
	scope: this,
	
	layout : {
		type : 'fit',
	},
	
	listeners:{
		beforerender: function(me, eOpts){
			// Clear all grid's stores
			var grids = Ext.ComponentQuery.query('grid',me);
			
			Ext.each(grids,function(g){g.getStore().clearData()});
		}
	},
	
//	initComponent: function(){
//		Ext.apply(this,{
//			
//			
//		});
//		
//		this.callParent(arguments);
//	},

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
}