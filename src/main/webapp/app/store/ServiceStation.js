Ext.define('Sam.store.ServiceStation', {
	extend: 'Ext.data.Store',
	
	requires: ['Sam.model.ServiceStation'],
	
	model: 'Sam.model.ServiceStation',
	
	autoLoad: false,

	sorters: { property: 'id'},

	proxy: {
        type: 'ajax',
        
        api: {
        	read : 		'site/load/station',
			create : 	'site/station/add.action',
			update : 	'site/station/update.action',
			destroy : 	'site/station/delete.action',
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
        		console.log('Store Success Operation');
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