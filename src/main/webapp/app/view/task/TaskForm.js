/**** Creates Stores ****/
logicOperatorStore = Ext.create('Sam.view.components.store.LogicOperator'),
	relationalOperatorStore = Ext.create('Sam.view.components.store.RelationalOperator'),
	conditionType = Ext.create('Sam.view.task.ConditionType');

/**** Creates Grid Objects that is going to be used in this page ****/

//Conditions Grid
grid1 = {
	xtype: 'gridpanel',
	
	itemId : 'grdConditions',
	
	plugins: {
		ptype: 'cellediting',
		clicksToEdit: 1
	},
	
	width: '100%',
	height: 150,

	store : Ext.create('Ext.data.Store'),
	
	columns : [
	{
//		text: 'Seq',
		dataIndex : 'seq',
		maxWidth: 32,
		minWidth: 32,
		menuDisabled: true,
		sortable: true,
	}, {
		text : 'Op. Logico',
		flex : 1,
		sortable : true,
		dataIndex : 'logicOper',
		renderer: function(value){
			index = logicOperatorStore.findExact('id',value); 
            if (index != -1){
                rs = logicOperatorStore.getAt(index).data; 
                return rs.desc; 
            }
		},
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
		sortable : true,
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
			allowBlank: false,
		}
	}, {
		text : 'Campo',
		flex : 1,
		sortable : true,
		dataIndex : 'field',
		editor: 'textfield'
	}, {
		text : 'Op. Comp.',
		flex : 1,
		sortable : true,
		dataIndex : 'relOper',
		renderer: function(value){
			index = relationalOperatorStore.findExact('id',value); 
            if (index != -1){
                rs = relationalOperatorStore.getAt(index).data; 
                return rs.desc; 
            }
		},
		editor: {
			store: relationalOperatorStore,
			queryMode: 'local',
			valueField: 'id',
	        displayField: 'desc',
			xtype : 'combobox',
			editable: false,
			allowBlank: false,
			
		}
	}, {
		text : 'Valor',
		flex : 1,
		sortable : true,
		dataIndex : 'value',
		editor: 'textfield'
	}, {
		text : 'Ação',
		xtype: 'actioncolumn',
		width: 70,
		align: 'center',
		items: [{
			iconCls: 'minus-circle',
			tooltip: 'Excluir Linha',
			handler: function(view, rowIndex, colIndex, item, e, record, row) {
				 this.fireEvent('itemClick', view, rowIndex, colIndex, item, e, record, row, 1);
			}
		}]
	}]
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
		sortable : true,
		dataIndex : 'alarm_desc',
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
//			id: 'eventpopup_end_hour',
			margin: '0 0 0 0',
			inputAttrTpl: " data-qtip='Hora de Término Prevista para a Ordem de Serviço' ",
			triggers: {f3: {handler: function() { Ext.create('Sam.view.components.PopUp',{
				title: 'Selecionar Alarme',
				itemId: 'alarmform_alarm',
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
							itemId: '',
							items:	[Ext.create('Sam.view.equipment.EquipmentsGrid',{
								dockedItems:[],
								columns:[{
									xtype: 'checkcolumn',
									dataIndex: 'active',
									text: 'Selecione'
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

	items : [ {
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