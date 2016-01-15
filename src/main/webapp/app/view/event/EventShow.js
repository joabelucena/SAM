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
		fieldLabel : 'Cod. Alarme',
		itemId: 'alarm_id',
		name: 'alarm_id',
		readOnly : true,
		width: 350,
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

var equipmentInfo = {
		xtype : 'groupfield',
		itemId: 'grpEquInfo',
		defaultType : 'textfield',
		title : 'Informações do Equipamento',
		layout : {
			type : 'vbox',
			//align : 'stretch',
		},

		items : [ {
			fieldLabel : 'Cód. Equipamento',
			itemId: 'equipment_id',
			name: 'equipment_id',
			readOnly : true,
			width: 300,
			inputAttrTpl: " data-qtip='Código do Equipamento' "
		}, {
			fieldLabel : 'Modelo',
			itemId: 'equipment_model',
			name: 'equipment_model',
			readOnly : true,
			width: 400,
			inputAttrTpl: " data-qtip='Modelo do Equipamento' "

		}, {
			fieldLabel : 'Fabricante',
			itemId: 'equipment_manufacturer',
			name: 'equipment_manufacturer',
			readOnly : true,
			width: 400,
			inputAttrTpl: " data-qtip='Fabricante do Equipamento' "
		}, {
			fieldLabel : 'Sub-Sistema',
			itemId: 'system_desc',
			name: 'system_desc',
			readOnly : true,
			width: 400,
			inputAttrTpl: " data-qtip='Sub-Sistema do Equipamento' "
		}, {
			fieldLabel : 'Local de Instalação',
			itemId: 'site_desc',
			name: 'site_desc',
			readOnly : true,
			width: 400,
			inputAttrTpl: " data-qtip='Local de Instalação do Equipamento' "
		} ],
	};



var soInfo = {
		xtype : 'groupfield',
		itemId: 'grpOpenSo',
		defaultType : 'textfield',
		title : 'Ordem de Serviço Aberta',
		layout : {
			type : 'vbox'
		},

		items : [ {
			fieldLabel : 'Cód. da Os',
			itemId: 'so_id',
			name: 'so_id',
			readOnly : true,
			width: 250,
			inputAttrTpl: " data-qtip='Código da Ordem de Serviço' "
		}, {
			fieldLabel : 'Estado',
			itemId: 'so_status',
			name: 'so_status',
			readOnly : true,
			width: 300,
			inputAttrTpl: " data-qtip='Estado da Ordem de Serviço' "
		}],
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
		items : [ eventInfo, equipmentInfo, soInfo]
		
	} ],
	
});