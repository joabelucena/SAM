var equipmentInfo = {
	xtype : 'fieldset',
	defaultType : 'textfield',
	title : 'Informações da Ordem de Serviço',
	layout : {
		type : 'vbox',
		//align : 'stretch',
	},

	items : [ {
		fieldLabel : 'No. OS',
		name : 'sodetailform_id',
		readOnly : true,
		width: '30%',
		inputAttrTpl: " data-qtip='Número da Ordem de Serviço' "
	}, {
		fieldLabel : 'Status',
		id : 'sodetailform_status',
		readOnly : true,
		width: '30%',
		inputAttrTpl: " data-qtip='Status da Ordem de Serviço' "
	}, {
		fieldLabel : 'Tipo',
		id : 'sodetailform_type',
		readOnly : true,
		width: '30%',
		inputAttrTpl: " data-qtip='Tipo da Ordem de Serviço' "
	}, {
		fieldLabel : 'Equipamento',
		id : 'sodetailform_equipment',
		readOnly : true,
		width: '40%',
		inputAttrTpl: " data-qtip='Equipamento da Ordem de Serviço' "
	}, {
		fieldLabel : 'Alarme',
		id : 'sodetailform_alarm',
		readOnly : true,
		width: '40%',
		inputAttrTpl: " data-qtip='Alarme do Equipamento da Ordem de Serviço' "
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