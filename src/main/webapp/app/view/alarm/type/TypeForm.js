Ext.define('Sam.view.alarm.type.TypeForm', {
	extend: 'Ext.Panel',
	requires:['Sam.view.components.FormToolbar'],
	
	alias:  'widget.alarmtypeform',
	
	itemId: 'alarmtypeform',
	
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
		items : [{
			fieldLabel : 'Código',
			itemId: 'id',
			name: 'id',
			allowBlank : true,
			editable: false,
			width: '20%',
			inputAttrTpl: " data-qtip='Código do Tipo' "
		},{
			fieldLabel : 'Descrição',
			itemId: 'desc',
			name: 'desc',
			allowBlank : false,
			width: '60%',
			inputAttrTpl: " data-qtip='Descrição do Tipo' "
		},{
			fieldLabel : 'Classificação',
			itemId: 'cla',
			name: 'cla',
			store: Ext.create('Ext.data.Store',{fields: ['id', 'desc'],
			    data : [
			        {"id":"C", "desc":"Comando"},
			        {"id":"A", "desc":"Alarme"}
			    ]
			}),
			queryMode: 'local',
			valueField: 'id',
	        displayField: 'desc',
			xtype : 'combobox',
			allowBlank : false,
			editable: false,
			width: '25%',
			inputAttrTpl: " data-qtip='Define se o alarme será exibido na tela de Eventos.' "
		}],
		
		scrollable: true,
		
		dockedItems: [{
			xtype: 'formtoolbar'
		}]
	} ]
	
});