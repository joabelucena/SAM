Ext.define('Sam.store.ServiceOrder', {
	extend: 'Ext.data.Store',
	
	requires: ['Sam.model.ServiceOrder'],
	
	model: 'Sam.model.ServiceOrder',
	
	autoLoad: false,

	sorters: { property: 'id'},

	proxy: {
        type: 'ajax',
        
        api: {
        	read : 		'so/load',
			create : 	'so/add.action',
			update : 	'so/update.action'
        },
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
        	success: function(proxy, response, operation){
        		console.log('Success on Store!');
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