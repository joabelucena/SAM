var eventInfo = {
	xtype : 'fieldset',
	defaultType : 'textfield',
	title : 'Informações do Alarme',
	layout : {
		type : 'vbox',
		align : 'stretch'
	},

	items : [ {
		fieldLabel : 'Data/Hora Alarme',
		id : 'alarmpopup_time_alarm',
		readOnly : true,
	}, {
		fieldLabel : 'Severidade',
		id : 'alarmpopup_severity',
		readOnly : true,
	}, {
		fieldLabel : 'Reconhecido por',
		id : 'alarmpopup_reco_user',
		readOnly : true,
	}, {
		fieldLabel : 'Data/Hora ',
		id : 'alarmpopup_reco_time',
		readOnly : true,
	} ]
};

var soInfo = {
	xtype : 'fieldset',
	defaultType : 'textfield',
	title : 'Informação da OS',
	layout : {
		type : 'vbox',
		align : 'stretch',
	},
	items : [ {
		xtype: 'container',
		
		layout: {
			   type: 'hbox',
			   padding: '0 0 5 0',
			   margin: '0 0 0 0'
		},
		items : [{
			xtype:'datefield',
			fieldLabel: 'Data de Início Prevista',
			labelAlign: 'left',
			margin: '0 0 0 0'
		},{
			xtype:'timefield',
			fieldLabel: 'Hora de Início Prevista',
			labelAlign: 'right',
			margin: '0 0 0 0'		
		}]
	
	}, {
		xtype: 'container',
		
		layout: {
			   type: 'hbox',
			   padding: '0 0 5 0',
			   margin: '0 0 0 0'
		},
		items : [{
			xtype:'datefield',
			fieldLabel: 'Data de Término Prevista',
			labelAlign: 'left',
			margin: '0 0 0 0'
		},{
			xtype:'timefield',
			fieldLabel: 'Hora de Término Prevista',
			labelAlign: 'right',
			margin: '0 0 0 0'		
		}]
	}, {
		fieldLabel : 'Tipo da OS',
		id : 'alarmpopup_so_type',
		valueField: 'values',
		autocomplete: true,
		xtype : 'combobox',
		allowBlank : false
	}, {
		fieldLabel : 'Observação',
		id : 'alarmpopup_obs_os',
		xtype : 'textareafield',
		allowBlank : false
	} ]
};

Ext.define('Sam.view.alarm.AlarmDataOpenSO', {
	extend : 'Ext.Panel',
	alias : 'widget.alarmdataopenso',

	requires : [ 'Sam.view.alarm.AlarmDataOpenSO',
	             'Sam.view.components.DateTimePicker'],

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

		layout : {
			type : 'vbox',
			align : 'stretch'
		},

		bodyPadding : 10,
		border : false,
		items : [ eventInfo, soInfo ],

		buttons : [ {
			text : 'Abrir Ordem de Serviço'
		} ],
	} ],

});