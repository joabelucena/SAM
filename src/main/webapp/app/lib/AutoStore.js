Ext.define('Sam.lib.AutoStore', {
	extend: 'Ext.data.Store',
    
    alias: 'widget.autostore',
 
    constructor: function(config) {
        this.callParent(arguments);
    },
    
    proxy: {
        type: 'ajax',
        
        reader: {
            type: 'json',
            successProperty: 'success',
            root: 'data',
            messageProperty: 'message'
        },
        
        writer: {
            type: 'associatedjson',
            writeAllFields: true,
        },
        
        listeners: {
        	
            exception: function(proxy, response, operation){
            	var message, json;
    	    	
            	try {
            		
            		//Parseia Retorno
            		json = Ext.JSON.decode(operation._response.responseText);
                		
            		message = json.message; 
    
            	}
            	catch (ex) {
            		message = 'Problemas na requisição, favor entrar em contato com o Adminsitrador do sistema';
            	}
                	
            	//Exibir Mensagem
            	Ext.MessageBox.show({
            		title: 'Falha na Requisição',
            		msg: message,
            		buttons: Ext.MessageBox.OK,
            		icon: Ext.MessageBox.WARNING
            	});
            
            },
        }
        	
    },
    
    listeners: {
    	 
    	    write: function(store, operation, eOpts) {
    	    	var message, json;
    	    	
    	    	if(!operation.success){
    	    		try {
                		
                		//Parseia Retorno
    	    			json = Ext.JSON.decode(operation._response.responseText);
                		
    	    			message = json.message; 
    
                    }
                    catch (ex) {
                    	message = 'Problemas na requisição, favor entrar em contato com o Adminsitrador do sistema';
                    }
                	
                    //Exibir Mensagem
                	Ext.MessageBox.show({
    			        title: 'Falha na Requisição',
    			        msg: ErrorMessage,
    			        buttons: Ext.MessageBox.OK,
    			        icon: Ext.MessageBox.WARNING
    				});
    	    	}
    	    	
    	    },
    	    load: function(a, e, i, o, u) {
    	     	console.log("load");
    	    },
    	    read: function(a, e, i, o, u) {
    	     	console.log("read");
    	    }
    }
    

});