Ext.define('Sam.store.Equipment', {
	extend: 'Ext.data.Store',
	
	requires: ['Sam.model.Equipment'],
	
	model: 'Sam.model.Equipment',
	
	pageSize: 30,
	
	autoLoad: true,

	proxy: {
        type: 'ajax',
        
        enablePaging: true,
        
        api: {
        	read : 		'equipment/load',
			create : 	'equipment/add.action',
			update : 	'equipment/update.action',
			destroy : 	'equipment/delete.action',
        },
        reader: {
            type: 'json',
            successProperty: 'success',
            root: 'data',
            messageProperty: 'message',
            totalProperty: 'total'
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
	
});