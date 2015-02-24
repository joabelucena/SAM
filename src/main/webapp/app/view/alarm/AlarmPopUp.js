Ext.define('Sam.view.alarm.AlarmPopUp', {
	extend : 'Ext.form.Panel',
	alias : 'widget.alarmpopup',

	requires : [ 'Ext.Ajax.request' ],
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
			text : 'OK',
		}, {

			text : 'My Awesome Field',
		}, {
			xtype : 'button',
			text : 'Reconhecer Alarme',
			handler : ajaxButton1Function
		} ]
	} ]

});

function ajaxButton1Function() {
	
	Ext.Ajax.request({
		url : 'events/recognize/',
		method : 'POST'
	});
}