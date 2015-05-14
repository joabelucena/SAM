Ext.define('Sam.store.SeverityLevel', {
	extend: 'Ext.data.Store',
	
	requires: ['Sam.model.SeverityLevel'],
	
	model: 'Sam.model.SeverityLevel',
	
	autoLoad: true,

	sorters: { property: 'id'},

	proxy: {
        type: 'ajax',
        
        api: {
        	read : 		'severity/load',
			create : 	'severity/add.action',
			update : 	'severity/update.action',
			destroy : 	'severity/delete.action',
        },
        reader: {
            type: 'json',
            successProperty: 'success',
            root: 'data',
            messageProperty: 'message'
        },
        writer: {
            type: 'json',
            writeAllFields: true,
            root: 'data'
        },
        listeners: {
        	success: function(proxy, response, operation){
        		console.log('Store Succes Operation');
        	},
            exception: function(proxy, response, operation){
            	var ErrorMessage,jResp;
            	
            	try {
            		
            		//Parseia Retorno
            		jResp = Ext.JSON.decode(response.responseText);
            		
            		ErrorMessage = jResp.message; 

                }
                catch (ex) {
                	ErrorMessage = 'Problemas na requisição, favor entrar em contato com o Adminsitrador do sistema';
                }
            	
                //Exibir Mensagem
            	Ext.MessageBox.show({
			        title: 'Falha na Requisição',
			        msg: ErrorMessage,
			        buttons: Ext.MessageBox.OK,
			        icon: Ext.MessageBox.WARNING
				});
            	
            },
            scope: this,
        }
    }
	
});