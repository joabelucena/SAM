Ext.define('Sam.view.components.PopUp', {
	extend : 'Ext.window.Window',
	alias : 'widget.popup',

	header : {
		titlePosition : 2,
	},

	closable : true,
	closeAction : 'destroy',
	maximizable : true,
	width : '90%',
	minWidth : 350,
	height : '90%',

	layout : 'fit',
	
	buttons : [ {
		text : 'Confirma',
		itemId: 'popupConfirma',
		tooltip:'Seleciona Equipamento',
        cls:'x-btn-default-small',
        iconCls: 'tick-button',
        handler: function() {
        	this.fireEvent('click', 1);
        }
	} ],
	
	modal: true

});