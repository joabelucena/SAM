var equipmentInfo = {
	xtype : 'fieldset',
	defaultType : 'textfield',
	title : 'Informações do Equipamento',
	layout : {
		type : 'vbox',
		align : 'stretch',
	},

	items : [ {
		fieldLabel : 'ID',
		name : 'alarmpopup_id',
		readOnly : true,
	}, {
		fieldLabel : 'Modelo',
		id : 'alarmpopup_model',
		readOnly : true,
	}, {
		fieldLabel : 'Fabricante',
		id : 'alarmpopup_manufacturer',
		readOnly : true,
	}, {
		fieldLabel : 'Sub-Sistema',
		id : 'alarmpopup_subsystem',
		readOnly : true,
	}, {
		fieldLabel : 'Local de Instalação',
		id : 'alarmpopup_site',
		readOnly : true,
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