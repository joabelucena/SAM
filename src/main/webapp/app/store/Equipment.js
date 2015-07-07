Ext.define('Sam.store.Equipment', {
	extend: 'Ext.data.Store',
	
	requires: ['Sam.model.Equipment'],
	
	model: 'Sam.model.Equipment',
	
	autoLoad: false,
	async: true,

	proxy: {
        type: 'ajax',
        
        api: {
        	read : 		'equipment/load',
			create : 	'',
			update : 	'',
			destroy : 	'',
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
	
	/*
	proxy: {
		type: 'ajax',
		url: 'equipment/load',
		reader: {
			type: 'json',
			root: 'data'
		}
	}
	*/
	
});