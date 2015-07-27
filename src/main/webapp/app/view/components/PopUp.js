Ext.define('Sam.view.components.PopUp', {
	extend : 'Ext.window.Window',
	alias : 'widget.popup',
	
	header : {
		titlePosition : 2,
	},

	closable : true,
	maximizable : true,
	width : '90%',
	minWidth : 350,
	height : '90%',
	
	scope: this,
	
	layout : 'fit',
	
	buttons : [ {
		text : 'Confirma',
		itemId: 'submit',
		tooltip:'Seleciona Registro',
        cls:'x-btn-default-small',
        iconCls: 'tick-button',
        /*
        handler: function() {
        	var id, window;
        	id 		= this.up('window').down('grid').getSelection()[0].get('id');
        	window 	= this.up('window');
        	
        	window.close();
        	
        	return id;
        }
        */
	} ],
	
	modal: true

});