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
		width: '40%',
		inputAttrTpl: " data-qtip='Código do Equipamento' "
	}, {
		fieldLabel : 'Modelo',
		itemId: 'equipment_model',
		name: 'equipment_model',
		readOnly : true,
		width: '50%',
		inputAttrTpl: " data-qtip='Modelo do Equipamento' "

	}, {
		fieldLabel : 'Fabricante',
		itemId: 'equipment_manufacturer',
		name: 'equipment_manufacturer',
		readOnly : true,
		width: '50%',
		inputAttrTpl: " data-qtip='Fabricante do Equipamento' "
	}, {
		fieldLabel : 'Sub-Sistema',
		itemId: 'system_desc',
		name: 'system_desc',
		readOnly : true,
		width: '60%',
		inputAttrTpl: " data-qtip='Sub-Sistema do Equipamento' "
	}, {
		fieldLabel : 'Local de Instalação',
		itemId: 'site_desc',
		name: 'site_desc',
		readOnly : true,
		width: '60%',
		inputAttrTpl: " data-qtip='Local de Instalação do Equipamento' "
	} ],
};

Ext.define('Sam.view.event.openSO.EventInfoEqto', {
	extend: 'Ext.Panel',
	alias:  'widget.eventinfoeqto',
	
	requires: ['Sam.view.event.openSO.EventInfoEqto'],
	
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

		layout : {
			type : 'vbox',
			align : 'stretch',
		},

		bodyPadding : 10,
		border : false,
		items : [ equipmentInfo ],

	} ],

});