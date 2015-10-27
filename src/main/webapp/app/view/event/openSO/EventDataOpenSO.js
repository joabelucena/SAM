var eventInfo = {
	xtype : 'groupfield',
	itemId: 'grpEveInfo',
	defaultType : 'textfield',
	title : 'Informações do Alarme',
	
	layout : {
		type : 'vbox'
	},

	items : [{
		fieldLabel : 'Cod. Evento',
		itemId: 'id',
		name: 'id',
		readOnly : true,
		width: 250,
		inputAttrTpl: " data-qtip='Código do Evento recebido' "
	}, {
		fieldLabel : 'Cód. Equipamento',
		itemId: 'equipment_id',
		name: 'equipment_id',
		readOnly : true,
		width: 275,
		inputAttrTpl: " data-qtip='Código do Equipamento' "
	}, {
		fieldLabel : 'Cod. Alarme',
		itemId: 'alarm_id',
		name: 'alarm_id',
		readOnly : true,
		width: 250,
		inputAttrTpl: " data-qtip='Código do Alarme' "
	}, {
		fieldLabel : 'Desc. Alarme',
		itemId: 'alarm_desc',
		name: 'alarm_desc',
		readOnly : true,
		width: 600,
		inputAttrTpl: " data-qtip='Descrição do Alarme' "
	}, {
		fieldLabel : 'Data/Hora Alarme',
		xtype: 'datefield',
		format: 'd/m/Y H:i',
		itemId: 'datetime',
		name: 'datetime',
		readOnly : true,
		width: 300,
		inputAttrTpl: " data-qtip='Data/Hora do Alarme' "
	},{
		xtype : 'combobox',
		fieldLabel : 'Severidade',
		itemId: 'severity_id',
		name: 'severity_id',
		readOnly : true,
		valueField: 'id',
        displayField: 'desc',
        store: Ext.data.Store({
			fields: ['id','desc'],
			autoLoad: true,
			proxy: {
		         type: 'ajax',
		         url: 'severity/load',
		         reader: {
		             type: 'json',
		             root: 'data'
		         }
		     },
		}),
		width: 300,
		inputAttrTpl: " data-qtip='Severidade do Alarme' "			
	}, {
		fieldLabel : 'Reconhecido por',
		itemId: 'reco_user',
		name: 'reco_user',
		readOnly : true,
		width: 350,
		inputAttrTpl: " data-qtip='Usuário que Reconheceu o Alarme' "
	}, {
		fieldLabel : 'Data/Hora',
		xtype: 'datefield',
		format: 'd/m/Y H:i',
		itemId: 'reco_date',
		name: 'reco_date',
		readOnly : true,
		width: 300,
		inputAttrTpl: " data-qtip='Data/Hora do Reconhecimento do Alarme' "
	} ]
};

var soInfo = {
	xtype : 'groupfield',
	defaultType : 'textfield',
	title : 'Informação da OS',
	itemId: 'grpSoInfo',
	layout : {
		type : 'vbox',
		//align : 'stretch',
	},

	items : [{
		xtype: 'container',
		defaults:{
			allowBlank : false
		},
		layout: {
			   type: 'hbox',
			   padding: '0 0 5 0',
			   margin: '0 0 0 0'
		},
		items : [
		         {
		        	 xtype:'datefield',
		        	 vtype: 'daterange',
		        	 endDateField: 'end_forecast_date', // id of the end date field
		        	 fieldLabel: 'Data de Início Prevista',
		        	 name: 'start_forecast_date',
		        	 itemId: 'start_forecast_date',
		        	 labelAlign: 'left',
		        	 format: 'd/m/Y',
		        	 minValue: new Date(),
		        	 margin: '0 0 0 0',
		        	 editable: false,
		        	 inputAttrTpl: " data-qtip='Data de Início Prevista da Ordem de Serviço' "
		         },{
		        	 xtype:'timefield',
		        	 fieldLabel: 'Hora de Início Prevista',
		        	 itemId: 'start_forecast_time',
		        	 name: 'start_forecast_time',
		        	 labelAlign: 'right',
		        	 format: 'H:i',
		        	 margin: '0 0 0 0',
		        	 editable: false,
		        	 inputAttrTpl: " data-qtip='Hora de Início Prevista da Ordem de Serviço' "
		         }			
			]
	
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
			format: 'd/m/Y',
			labelAlign: 'left',
			margin: '0 0 0 0',
			editable: false,
			inputAttrTpl: " data-qtip='Data de Término Prevista da Ordem de Serviço' "
		},{
			xtype:'timefield',
			fieldLabel: 'Hora de Término Prevista',
			itemId: 'end_forecast_time',
			name: 'end_forecast_time',
			labelAlign: 'right',
			format: 'H:i',
			margin: '0 0 0 0',
			editable: false,
			inputAttrTpl: " data-qtip='Hora de Término Prevista da Ordem de Serviço' "
		}]
	}, {
		fieldLabel : 'Tipo',
		itemId: 'type_id',
		name: 'type_id',
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
		itemId: 'remark',
		name: 'remark',
		xtype : 'textareafield',
		allowBlank : false,
		width: '60%',
		inputAttrTpl: " data-qtip='Observação da Ordem de Serviço' "
	} ]
};

Ext.define('Sam.view.event.openSO.EventDataOpenSO', {
	extend : 'Ext.Panel',
	alias : 'widget.eventdataopenso',
	
	itemId: 'eventdataopenso',

	closable : true,

	layout : {
		type : 'fit',
	},

	items : [ {
		xtype : 'form',

		defaultType : 'textfield',
		fieldDefaults : {
			labelWidth : 150
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
			itemId: 'btnOpenSo',
			text : 'Abrir Ordem de Serviço'
		} ],
	} ],

});