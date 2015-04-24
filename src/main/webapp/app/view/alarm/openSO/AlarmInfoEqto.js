var equipmentInfo = {
	xtype : 'fieldset',
	defaultType : 'textfield',
	title : 'Informações do Equipamento',
	layout : {
		type : 'vbox',
		//align : 'stretch',
	},

	items : [ {
		fieldLabel : 'ID',
		name : 'alarmpopup_id',
		readOnly : true,
		width: '40%',
		inputAttrTpl: " data-qtip='ID do Equipamento' "
	}, {
		fieldLabel : 'Modelo',
		id : 'alarmpopup_model',
		readOnly : true,
		width: '50%',
		inputAttrTpl: " data-qtip='Modelo do Equipamento' "

	}, {
		fieldLabel : 'Fabricante',
		id : 'alarmpopup_manufacturer',
		readOnly : true,
		width: '50%',
		inputAttrTpl: " data-qtip='Fabricante do Equipamento' "
	}, {
		fieldLabel : 'Sub-Sistema',
		id : 'alarmpopup_subsystem',
		readOnly : true,
		width: '60%',
		inputAttrTpl: " data-qtip='Sub-Sistema do Equipamento' "
	}, {
		fieldLabel : 'Local de Instalação',
		id : 'alarmpopup_site',
		readOnly : true,
		width: '60%',
		inputAttrTpl: " data-qtip='Local de Instalação do Equipamento' "
	} ],
};

Ext.define('Sam.view.alarm.openSO.AlarmInfoEqto', {
	extend: 'Ext.Panel',
	alias:  'widget.alarminfoeqto',
	
	requires: ['Sam.view.alarm.openSO.AlarmInfoEqto'],
	
	closable: true,
	
	layout:{
		type: 'fit',
	},
	
	items : [ {
		xtype : 'form',

		defaultType : 'textfield',
		id : 'alarminfoeqtoform',
		fieldDefaults : {
			labelWidth : 180
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