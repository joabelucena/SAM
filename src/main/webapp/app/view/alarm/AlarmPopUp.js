Ext.define('Sam.view.alarm.AlarmPopUp', {
	extend : 'Ext.form.Panel',
	alias : 'widget.alarmpopup',

	requires : ['Ext.Ajax.request'],
	width : 800,
	height : 600,
	bodyPadding : 10,
	type : 'hbox',
	floating : true,
	closable : true,
	id: 'AlarmPopUpPanel',

	items : [ {
		layout : {
			type : 'vbox',
			align : 'stretch',
			margin : '10 10 10 10',
		},
		defaults : {
			xtype : 'label',
			anchor : '100%',
			allowBlank : false,
			minLength : 3,
			margin : '10 10 10 10'
		},
		items : [ {
            xtype: 'displayfield',
            fieldLabel: 'Element',                
            value: 'elementname'
        }, {
            xtype: 'displayfield',
            fieldLabel: 'Expression',
            value: 's.expressionName' // s.expressionName
        }, {
            xtype: 'displayfield',
            fieldLabel: 'Profile',
            value: 's.profileName'      // s.profileName
        }, {
            xtype: 'displayfield',
            fieldLabel: 'Time',
            value: 'time'
        }, {
			xtype : 'button',
			text : 'Abrir Ordem de Serviço',
			id: 'openso'
		} ]
	} ]

});

