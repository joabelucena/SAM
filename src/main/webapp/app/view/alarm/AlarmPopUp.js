Ext.define('Sam.view.alarm.AlarmPopUp', {
	extend : 'Ext.form.Panel',
	alias : 'widget.alarmpopup',

	requires : ['Ext.Ajax.request'],
	tpl: '{event_id}',
	width : 800,
	height : 600,
	bodyPadding : 10,
	type : 'hbox',
	floating : true,
	closable : true,
	id: 'AlarmPopUpPanel',

	items : [ {
		layout : {
			type : 'vbox',
			align : 'stretch',
			margin : '10 10 10 10',
		},
		defaults : {
			xtype : 'label',
			anchor : '100%',
			allowBlank : false,
			minLength : 3,
			margin : '10 10 10 10'
		},
		items : [ {
			xtype : 'button',
			text : 'Abrir Ordem de Serviço',
			id: 'openso'
		} ]
	} ]

});

