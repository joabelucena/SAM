var equipmentInfo = {
	xtype : 'fieldset',
	defaultType : 'textfield',
	title : 'Informações do Equipamento',
	layout : {
		type : 'vbox',
		//align : 'stretch',
	},

	items : [ {
		fieldLabel : 'Cod. Equipamento',
		name : 'eventpopup_equip_id',
		readOnly : true,
		width: '40%',
		inputAttrTpl: " data-qtip='ID do Equipamento' "
	}],
};


var eventInfo = {
	xtype : 'fieldset',
	defaultType : 'textfield',
	title : 'Informações do Alarme',
	layout : {
		type : 'vbox',
		//align : 'stretch'
	},

	items : [ {
		fieldLabel : 'Cod. Alarme',
		id : 'eventpopup_event_id',
		readOnly : true,
		width: '30%',
		inputAttrTpl: " data-qtip='ID do Alarme' "
	}, {
		fieldLabel : 'Data/Hora Alarme',
		id : 'eventpopup_time_event',
		readOnly : true,
		width: '40%',
		inputAttrTpl: " data-qtip='Data/Hora do Alarme' "
	}, 
	/** POG Field **/
	{
		id : 'eventpopup_severity_id',
		hidden: true,
		readOnly : true,
		width: '35%',
		inputAttrTpl: " data-qtip='Severidade do Alarme' "
	},
	
	{
		fieldLabel : 'Severidade',
		id : 'eventpopup_severity_desc',
		readOnly : true,
		width: '35%',
		inputAttrTpl: " data-qtip='Severidade do Alarme' "
	}, {
		fieldLabel : 'Reconhecido por',
		id : 'eventpopup_reco_user',
		readOnly : true,
		width: '40%',
		inputAttrTpl: " data-qtip='Usuário que Reconheceu o Alarme' "
	}, {
		fieldLabel : 'Data/Hora ',
		id : 'eventpopup_reco_time',
		readOnly : true,
		width: '40%',
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
	       	vtype: 'daterange',
	       	endDateField: 'end_forecast_date', // id of the end date field
	       	fieldLabel: 'Data de Início Prevista',
	       	name: 'start_forecast_date',
	       	itemId: 'start_forecast_date',
	       	id: 'eventpopup_start_date',
	       	minValue: new Date(),
	       	labelAlign: 'left',
	       	format: 'd/m/Y',
	       	margin: '0 0 0 0',
	       	inputAttrTpl: " data-qtip='Data de Início Prevista para a Ordem de Serviço' "
		},{
			xtype:'timefield',
			fieldLabel: 'Hora de Início Prevista',
			id: 'eventpopup_start_hour',
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
			vtype: 'daterange',
			startDateField: 'start_forecast_date', // id of the start date field
			fieldLabel: 'Data de Término Prevista',
			name: 'end_forecast_date',
			itemId: 'end_forecast_date',
			id: 'eventpopup_end_date',
			format: 'd/m/Y',
			labelAlign: 'left',
			margin: '0 0 0 0',
			inputAttrTpl: " data-qtip='Data de Término Prevista para a Ordem de Serviço' "
		},{
			xtype:'timefield',
			fieldLabel: 'Hora de Término Prevista',
			id: 'eventpopup_end_hour',
			labelAlign: 'right',
			format: 'H:i',
			margin: '0 0 0 0',
			inputAttrTpl: " data-qtip='Hora de Término Prevista para a Ordem de Serviço' "
		}]
	}, {
		fieldLabel : 'Tipo da OS',
		id : 'eventpopup_so_type',
		valueField: 'id',
        displayField: 'desc',
		xtype : 'combobox',
		store: Ext.data.Store({
			fields: ['id','desc'],
			autoLoad: true,
			proxy: {
		         type: 'ajax',
		         url: 'so/load/type',
		         reader: {
		             type: 'json',
		             root: 'data'
		         }
		     },
		}),
		allowBlank : false,
		editable: false,
		width: '40%',
		inputAttrTpl: " data-qtip='Tipo da Ordem de Serviço' "
	},{
		fieldLabel : 'Observação',
		id : 'eventpopup_obs_os',
		xtype : 'textareafield',
		allowBlank : false,
		width: '60%',
		inputAttrTpl: " data-qtip='Observação da Ordem de Serviço' "
	} ]
};

Ext.define('Sam.view.event.openSO.EventDataOpenSO', {
	extend : 'Ext.Panel',
	alias : 'widget.eventdataopenso',

	requires : [ 'Sam.view.event.openSO.EventDataOpenSO'],

	closable : true,

	layout : {
		type : 'fit',
	},

	items : [ {
		xtype : 'form',

		defaultType : 'textfield',
		id : 'eventdataopensoform',
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
		items : [ equipmentInfo ,eventInfo, soInfo ],

		buttons : [ {
			id: 'openSoButtom',
			text : 'Abrir Ordem de Serviço'
		} ],
	} ],

});