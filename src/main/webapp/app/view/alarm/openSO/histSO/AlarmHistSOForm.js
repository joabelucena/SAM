var equipmentInfo = {
	xtype : 'fieldset',
	defaultType : 'textfield',
	title : 'Informações da Ordem de Serviço',
	layout : {
		type : 'vbox',
		align : 'stretch',
	},

	items : [ {
		fieldLabel : 'No. OS',
		name : 'sodetailform_id',
		readOnly : true,
	}, {
		fieldLabel : 'Status',
		id : 'sodetailform_status',
		readOnly : true,
	}, {
		fieldLabel : 'Tipo',
		id : 'sodetailform_type',
		readOnly : true,
	}, {
		fieldLabel : 'Equipamento',
		id : 'sodetailform_equipment',
		readOnly : true,
	}, {
		fieldLabel : 'Alarme',
		id : 'sodetailform_alarm',
		readOnly : true,
	} ],
};

Ext.define('Sam.view.alarm.openSO.histSO.AlarmHistSOForm', {
	extend: 'Ext.Panel',
	alias:  'widget.alarmhistsoform',

	requires: ['Sam.view.alarm.openSO.histSO.AlarmHistSOForm'],
	
	closable : false,

	layout : {
		type : 'fit',
	},

	items : [ {
		xtype : 'form',

		defaultType : 'textfield',
		id : 'SODetailForm',
		fieldDefaults : {
			labelWidth : 180
		},

		layout : {
			type : 'vbox',
			align : 'stretch'
		},
		
		scrollable: true,

		bodyPadding : 10,
		border : false,
		items : [ equipmentInfo]

	} ],

});