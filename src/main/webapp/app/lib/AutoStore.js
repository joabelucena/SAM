Ext.define('Sam.lib.AutoStore', {
	extend: 'Ext.data.Store',
	
	autoLoad: false,
	
	constructor: function(config) {
		this.callParent([config]);
        
		this.proxy.on('exception', this.onProxyException, this);
		
//		this.proxy.on('rollback', this.onStoreRollback, this);
    },
	
	proxy: {
		type: 'ajax',
		
		enablePaging: false,
    	
		simpleSortMode: true,
		
        reader: {
            type: 'json',
            root: 'data',
            totalProperty: 'total',
            successProperty: 'success',
            messageProperty: 'message'
        },
        writer: {
        	type: 'associatedjson',
            writeAllFields: true,
        },
        
	},
	
	onProxyException: function(proxy, response, operation, eOpts) {
		this.rejectChanges();
		
		/*
     	 * Runs when server returns 'success: false'
     	 */
     	var message, json;
	    	
     	try {
     		
     		//Parse returning message
     		json = Ext.JSON.decode(operation._response.responseText);
         		
     		message = json.message; 

     	}
     	catch (ex) {
     		message = 'Problemas na requisição, favor entrar em contato com o administrador do sistema';
     	}
         	
     	//Exibir Mensagem
     	Ext.MessageBox.show({
     		title: 'Falha na Requisição',
     		msg: message,
     		buttons: Ext.MessageBox.OK,
     		icon: Ext.MessageBox.WARNING
     	});
	 },
	 
//	 onStoreRollback: function() {
//		 this.rejectChanges();
//	 }
});