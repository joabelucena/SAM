Ext.define('Sam.view.components.PopUp', {
	extend : 'Ext.window.Window',
	alias : 'widget.popup',
	
	header : {
		titlePosition : 2,
	},
	
	groupField: '',

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
        
        handler: function(button) {
        	
        	//Aba Objecto Pai
    		var activeTab = Ext.getCmp('viewportpanel').getActiveTab(),
    			window = button.up('window'),
    			record = button.up('window').down('grid').getSelection()[0],
    			form = Ext.ComponentQuery.query('form',activeTab)[0].getForm(),
    			groupField = Ext.ComponentQuery.query('#'+window.groupField, activeTab)[0];
    		
        	if(record){
        		
        		groupField.loadRecord(record);
        		
        		window.close();
        		
        	}
        }
        
	} ],
	
	modal: true

});