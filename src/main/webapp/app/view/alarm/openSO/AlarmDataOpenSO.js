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
		id : 'alarmpopup_event_id',
		readOnly : true,
		width: '30%',
		inputAttrTpl: " data-qtip='ID do Alarme' "
	}, {
		fieldLabel : 'Data/Hora Alarme',
		id : 'alarmpopup_time_alarm',
		readOnly : true,
		width: '30%',
		inputAttrTpl: " data-qtip='Data/Hora do Alarme' "
	}, {
		fieldLabel : 'Severidade',
		id : 'alarmpopup_severity',
		readOnly : true,
		width: '30%',
		inputAttrTpl: " data-qtip='Severidade do Alarme' "
	}, {
		fieldLabel : 'Reconhecido por',
		id : 'alarmpopup_reco_user',
		readOnly : true,
		width: '30%',
		inputAttrTpl: " data-qtip='Usuário que Reconheceu o Alarme' "
	}, {
		fieldLabel : 'Data/Hora ',
		id : 'alarmpopup_reco_time',
		readOnly : true,
		width: '30%',
		inputAttrTpl: " data-qtip='Data/Hora do Reconhecimento do Alarme' "
	} ]
};

var soInfo = {
	xtype : 'fieldset',
	defaultType : 'textfield',
	title : 'Informação da OS',
	layout : {
		type : 'vbox',
		//align : 'stretch',
	},

	
	items : [ {
		xtype: 'container',
		defaults:{
			allowBlank : false
		},
		layout: {
			   type: 'hbox',
			   padding: '0 0 5 0',
			   margin: '0 0 0 0'
		},
		items : [{
			xtype:'datefield',
			fieldLabel: 'Data de Início Prevista',
			id: 'alarmpopup_start_date',
			labelAlign: 'left',
			format: 'd/m/Y',
			margin: '0 0 0 0',
			inputAttrTpl: " data-qtip='Data de Início Prevista para a Ordem de Serviço' "
		},{
			xtype:'timefield',
			fieldLabel: 'Hora de Início Prevista',
			id: 'alarmpopup_start_hour',
			labelAlign: 'right',
			format: 'H:i',
			margin: '0 0 0 0',
			inputAttrTpl: " data-qtip='Hora de Início Prevista para a Ordem de Serviço' "
		}]
	
	}, {
		xtype: 'container',
		defaults:{
			allowBlank : false
		},
		layout: {
			   type: 'hbox',
			   padding: '0 0 5 0',
			   margin: '0 0 0 0',
		},
		items : [{
			
			
			xtype:'datefield',
			fieldLabel: 'Data de Término Prevista',
			id: 'alarmpopup_end_date',
			format: 'd/m/Y',
			labelAlign: 'left',
			margin: '0 0 0 0',
			inputAttrTpl: " data-qtip='Data de Término Prevista para a Ordem de Serviço' "
		},{
			xtype:'timefield',
			fieldLabel: 'Hora de Término Prevista',
			id: 'alarmpopup_end_hour',
			labelAlign: 'right',
			format: 'H:i',
			margin: '0 0 0 0',
			inputAttrTpl: " data-qtip='Hora de Término Prevista para a Ordem de Serviço' "
		}]
	}, {
		fieldLabel : 'Tipo da OS',
		id : 'alarmpopup_so_type',
		valueField: 'type',
        displayField: 'type',
		xtype : 'combobox',
		allowBlank : false,
		width: '30%',
		inputAttrTpl: " data-qtip='Tipo da Ordem de Serviço' "
	},{
		fieldLabel : 'Observação',
		id : 'alarmpopup_obs_os',
		xtype : 'textareafield',
		allowBlank : false,
		width: '60%',
		inputAttrTpl: " data-qtip='Observação da Ordem de Serviço' "
	} ]
};

Ext.define('Sam.view.alarm.openSO.AlarmDataOpenSO', {
	extend : 'Ext.Panel',
	alias : 'widget.alarmdataopenso',

	requires : [ 'Sam.view.alarm.openSO.AlarmDataOpenSO'],

	closable : true,

	layout : {
		type : 'fit',
	},

	items : [ {
		xtype : 'form',

		defaultType : 'textfield',
		id : 'alarmdataopensoform',
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
		items : [ eventInfo, soInfo ],

		buttons : [ {
			id: 'openSoButtom',
			text : 'Abrir Ordem de Serviço'
		} ],
	} ],

});