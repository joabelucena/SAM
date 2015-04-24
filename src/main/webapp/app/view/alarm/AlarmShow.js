var eventInfo = {
	xtype : 'fieldset',
	defaultType : 'textfield',
	title : 'Informações do Alarme',
	layout : {
		type : 'vbox',
		//align : 'stretch'
	},

	items : [ {
		fieldLabel : 'ID Alarme',
		id : 'alarmshow_event_id',
		readOnly : true,
		width: '40%',
		inputAttrTpl: " data-qtip='ID do Alarme' "
	}, {
		fieldLabel : 'Data/Hora Alarme',
		id : 'alarmshow_time_alarm',
		readOnly : true,
		width: '40%',
		inputAttrTpl: " data-qtip='Data/Hora do Alarme' "

	}, {
		fieldLabel : 'Severidade',
		id : 'alarmshow_severity',
		readOnly : true,
		width: '40%',
		inputAttrTpl: " data-qtip='Severidade do Alarme' "
		
	}, {
		fieldLabel : 'Reconhecido por',
		id : 'alarmshow_reco_user',
		readOnly : true,
		width: '40%',
		inputAttrTpl: " data-qtip='Usuário que Reconheceu o Alarme' "
	}, {
		fieldLabel : 'Data/Hora ',
		id : 'alarmshow_reco_time',
		readOnly : true,
		width: '40%',
		inputAttrTpl: " data-qtip='Data/Hora do Reconhecimento do Alarme' "
	} ]
};

var equipmentInfo = {
		xtype : 'fieldset',
		defaultType : 'textfield',
		title : 'Informações do Equipamento Alarmado',
		layout : {
			type : 'vbox',
			//align : 'stretch',
		},

		items : [ {
			fieldLabel : 'ID',
			name : 'alarmshow_id',
			readOnly : true,
			width: '40%',
			inputAttrTpl: " data-qtip='ID do Equipamento' "
		}, {
			fieldLabel : 'Modelo',
			id : 'alarmshow_model',
			readOnly : true,
			width: '40%',
			inputAttrTpl: " data-qtip='Modelo do Equipamento' "
		}, {
			fieldLabel : 'Fabricante',
			id : 'alarmshow_manufacturer',
			readOnly : true,
			width: '40%',
			inputAttrTpl: " data-qtip='Fabricante do Equipamento' "
		}, {
			fieldLabel : 'Sub-Sistema',
			id : 'alarmshow_subsystem',
			readOnly : true,
			width: '40%',
			inputAttrTpl: " data-qtip='Sub-Sistema do Equipamento' "
		}, {
			fieldLabel : 'Local de Instalação',
			id : 'alarmshow_site',
			readOnly : true,
			width: '40%',
			inputAttrTpl: " data-qtip='Local de Instalação do Equipamento' "
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