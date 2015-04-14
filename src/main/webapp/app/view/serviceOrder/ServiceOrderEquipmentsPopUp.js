Ext.define('Sam.view.serviceOrder.ServiceOrderEquipmentsPopUp', {
	extend : 'Ext.window.Window',
	alias : 'widget.soequipmentspopup',

	title : 'Selecionar Equipamento',

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
		tooltip:'Seleciona Equipamento',
        cls:'x-btn-default-small',
        iconCls: 'tick-button',
        handler: function() {
        	this.fireEvent('confirmClick', 1);
        }
	} ],
	
	modal: true

});