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
		id : 'eventshow_event_id',
		readOnly : true,
		width: '40%',
		inputAttrTpl: " data-qtip='ID do Alarme' "
	}, {
		fieldLabel : 'Data/Hora Alarme',
		id : 'eventshow_time_event',
		readOnly : true,
		width: '40%',
		inputAttrTpl: " data-qtip='Data/Hora do Alarme' "

	}, {
		fieldLabel : 'Severidade',
		id : 'eventshow_severity',
		readOnly : true,
		width: '40%',
		inputAttrTpl: " data-qtip='Severidade do Alarme' "
		
	}, {
		fieldLabel : 'Reconhecido por',
		id : 'eventshow_reco_user',
		readOnly : true,
		width: '40%',
		inputAttrTpl: " data-qtip='Usuário que Reconheceu o Alarme' "
	}, {
		fieldLabel : 'Data/Hora ',
		id : 'eventshow_reco_time',
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
			name : 'eventshow_id',
			readOnly : true,
			width: '40%',
			inputAttrTpl: " data-qtip='ID do Equipamento' "
		}, {
			fieldLabel : 'Modelo',
			id : 'eventshow_model',
			readOnly : true,
			width: '40%',
			inputAttrTpl: " data-qtip='Modelo do Equipamento' "
		}, {
			fieldLabel : 'Fabricante',
			id : 'eventshow_manufacturer',
			readOnly : true,
			width: '40%',
			inputAttrTpl: " data-qtip='Fabricante do Equipamento' "
		}, {
			fieldLabel : 'Sub-Sistema',
			id : 'eventshow_subsystem',
			readOnly : true,
			width: '40%',
			inputAttrTpl: " data-qtip='Sub-Sistema do Equipamento' "
		}, {
			fieldLabel : 'Local de Instalação',
			id : 'eventshow_site',
			readOnly : true,
			width: '40%',
			inputAttrTpl: " data-qtip='Local de Instalação do Equipamento' "
		} ],
	};

Ext.define('Sam.view.event.EventShow', {
	extend: 'Ext.Panel',
	alias:  'widget.eventshow',
		
	closable: true,
	
	layout:{
		type: 'fit',
	},
	
	items : [ {
		xtype : 'form',

		defaultType : 'textfield',
		id : 'eventshowform',
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