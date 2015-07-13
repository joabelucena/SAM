var grid1 = Ext.create('Ext.grid.Panel', {
	
	itemId : 'conditionsgrid',
	
	width: '100%',
	height: 150,

	store : Ext.create('Ext.data.Store'),
	
	columns : [{
		dataIndex : 'id',
		maxWidth: 30,
		minWidth: 30,
		menuDisabled: true,
		sortable: true,
		filter : {
			type : 'number'
		}
	}, {
		text : 'Op. Logico',
		flex : 1,
		sortable : true,
		dataIndex : 'desc',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Campo',
		flex : 1,
		sortable : true,
		dataIndex : 'alarm_desc',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Op. Comp.',
		flex : 1,
		sortable : true,
		dataIndex : 'alarm_desc',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Valor',
		flex : 1,
		sortable : true,
		dataIndex : 'alarm_desc',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Acao',
		flex : 1,
		sortable : true,
		dataIndex : 'alarm_desc',
		filter : {
			type : 'string'
		}
	}]
})

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
			fieldLabel: 'Codigo',
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
                    boxLabel  : 'Sim',	//TODO default
                    name      : 'active',
                    inputValue: 'm',
//                    id        : 'radio1'
                }, {
                    boxLabel  : 'Não',
                    name      : 'active',
                    inputValue: 'l',
//                    id        : 'radio2'
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
			id: 'asdfasdf',
			margin: '0 0 0 0',
			inputAttrTpl: " data-qtip='Hora de Término Prevista para a Ordem de Serviço' "
			
			/**/
			
		},{
			xtype: 'tbseparator',
			width: '35%'			
		},{
			xtype:'textfield',	//TODO trazer descrição do alarme
			fieldLabel: 'Alarme',
			id: 'eventpopup_end_hour',
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
		layout : {
			type : 'vbox',
		},
		
		items : [ h1, h2]
};


/******************** Middle: Conditions ********************/
var middle = {
	xtype : 'fieldset',
	defaultType : 'textfield',
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
//		Ext.create('Sam.view.alarm.AlarmGrid',{width: '100%', height: 150, dockedItems:[]})
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
				width: 50,
				iconCls: 'minus',
				handler: function(){
					alert('Ola');
				}
			},{
				xtype: 'tbseparator',
				width: 5
			},{
				xtype: 'button',
				width: 50,
				iconCls: 'plus',
				handler: function(){
					alert('Ola');
				}
			}]
	}]
};

/******************** Footer: Equipments ********************/
var footer = {
		xtype : 'fieldset',
		defaultType : 'textfield',
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
			Ext.create('Sam.view.equipment.EquipmentsGrid',{
				width: '100%',
				height: 150,
				dockedItems:[],
				store: Ext.create('Ext.data.Store')
//				store: Ext.create('Sam.store.Equipment',{autoLoad: false})
			})
		,{
			//TODO http://stackoverflow.com/questions/19312769/extjs-4-2-1-config-autoloadfalse-fails <-- responder se der certo
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
					width: 50,
					iconCls: 'minus',
					tooltip: 'Apaga equipamentos da Lista',
					handler: function(){
						alert('Ola');
					}
				},{
					xtype: 'tbseparator',
					width: 5
				},{
					xtype: 'button',
					width: 50,
					iconCls: 'plus',
					tooltip: 'Adiciona novo equipamento',
					handler: function(){
						
						Ext.create('Sam.view.components.PopUp',{
							title: 'Selecionar Equipamento',
							itemId: '',
							items:	[Ext.create('Sam.view.equipment.EquipmentsGrid',{dockedItems:[]})],
							
						}).show()
					}
				}]
		}]
	};

Ext.define('Sam.view.task.TaskForm', {
	extend: 'Ext.Panel',
	
	alias:  'widget.taskform',
	
	itemId: 'taskform',
	requires : [ 'Sam.view.event.openSO.EventDataOpenSO'],

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