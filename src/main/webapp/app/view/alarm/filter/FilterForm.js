Ext.define('Sam.view.alarm.filter.FilterForm', {
	extend: 'Ext.Panel',
	
	alias:  'widget.alarmfilterform',
	
	itemId: 'alarmfilterform',
	
	closable: true,
	
	layout:{
		type: 'fit',
	},
	
	items : [ {
		xtype : 'form',

		defaultType : 'textfield',

		fieldDefaults : {
			labelWidth : 100
		},
		defaults:{
			allowBlank : false
		},

		layout : {
			type : 'vbox'
		},

		bodyPadding : 10,
		border : false,
		items:[{
			fieldLabel : 'Codigo',
			itemId: 'id',
			name: 'id',
			allowBlank : true,
			editable: false,
			width: '30%',
			inputAttrTpl: " data-qtip='Codigo do Filtro' "
		},{
			xtype: 'textfield',
			fieldLabel : 'Alarme',
			itemId: 'alarm_id',
			name: 'alarm_id',
			editable: false,
			width: '30%',
			allowBlank : true,
			inputAttrTpl: " data-qtip='Codigo do Alarme a ser filtrado' ",
			triggers: {f3: {handler: function() { Ext.create('Sam.view.components.PopUp',{
				title: 'Selecionar Alarme a ser filtrado',
				itemId: 'alarmfilterform_alarm',
				items:	[Ext.create('Sam.view.alarm.AlarmGrid',{dockedItems:[]})],
				
			}).show()}}}
		
		},{
			xtype: 'textfield',
			fieldLabel : 'Equipamento',
			itemId: 'equip_id',
			name: 'equip_id',
			editable: false,
			width: '30%',
			allowBlank : true,
			inputAttrTpl: " data-qtip='Codigo do Equipamento a ser filtrado' ",
			triggers: {f3: {handler: function() { Ext.create('Sam.view.components.PopUp',{
				title: 'Selecionar Equipamento a ser filtrado',
				itemId: 'alarmfilterform_equipment',
				items:	[Ext.create('Sam.view.equipment.EquipmentsGrid',{dockedItems:[]})],
				
			}).show()}}}
		
		}],
		
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