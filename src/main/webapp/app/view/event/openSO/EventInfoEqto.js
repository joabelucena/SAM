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
		name : 'eventpopup_id',
		readOnly : true,
		width: '40%',
		inputAttrTpl: " data-qtip='ID do Equipamento' "
	}, {
		fieldLabel : 'Modelo',
		id : 'eventpopup_model',
		readOnly : true,
		width: '50%',
		inputAttrTpl: " data-qtip='Modelo do Equipamento' "

	}, {
		fieldLabel : 'Fabricante',
		id : 'eventpopup_manufacturer',
		readOnly : true,
		width: '50%',
		inputAttrTpl: " data-qtip='Fabricante do Equipamento' "
	}, {
		fieldLabel : 'Sub-Sistema',
		id : 'eventpopup_subsystem',
		readOnly : true,
		width: '60%',
		inputAttrTpl: " data-qtip='Sub-Sistema do Equipamento' "
	}, {
		fieldLabel : 'Local de Instalação',
		id : 'eventpopup_site',
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
		id : 'eventinfoeqtoform',
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