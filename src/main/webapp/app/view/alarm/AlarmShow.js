var eventInfo = {
	xtype : 'fieldset',
	defaultType : 'textfield',
	title : 'Informações do Alarme',
	layout : {
		type : 'vbox',
		align : 'stretch'
	},

	items : [ {
		fieldLabel : 'ID Alarme',
		id : 'alarmshow_event_id',
		readOnly : true,
	}, {
		fieldLabel : 'Data/Hora Alarme',
		id : 'alarmshow_time_alarm',
		readOnly : true,
	}, {
		fieldLabel : 'Severidade',
		id : 'alarmshow_severity',
		readOnly : true,
	}, {
		fieldLabel : 'Reconhecido por',
		id : 'alarmshow_reco_user',
		readOnly : true,
	}, {
		fieldLabel : 'Data/Hora ',
		id : 'alarmshow_reco_time',
		readOnly : true,
	} ]
};

var equipmentInfo = {
		xtype : 'fieldset',
		defaultType : 'textfield',
		title : 'Informações do Equipamento Alarmado',
		layout : {
			type : 'vbox',
			align : 'stretch',
		},

		items : [ {
			fieldLabel : 'ID',
			name : 'alarmshow_id',
			readOnly : true,
		}, {
			fieldLabel : 'Modelo',
			id : 'alarmshow_model',
			readOnly : true,
		}, {
			fieldLabel : 'Fabricante',
			id : 'alarmshow_manufacturer',
			readOnly : true,
		}, {
			fieldLabel : 'Sub-Sistema',
			id : 'alarmshow_subsystem',
			readOnly : true,
		}, {
			fieldLabel : 'Local de Instalação',
			id : 'alarmshow_site',
			readOnly : true,
		} ],
	};

Ext.define('Sam.view.alarm.AlarmShow', {
	extend: 'Ext.Panel',
	alias:  'widget.alarmshow',
		
	closable: true,
	
	layout:{
		type: 'fit',
	},
	
	items : [ {
		xtype : 'form',

		defaultType : 'textfield',
		id : 'alarmshowform',
		fieldDefaults : {
			labelWidth : 180
		},
		defaults:{
			allowBlank : false
		},

		layout : {
			type : 'vbox',
			align : 'stretch'
		},

		bodyPadding : 10,
		border : false,
		items : [ eventInfo, equipmentInfo ]
		
	} ],
	
});