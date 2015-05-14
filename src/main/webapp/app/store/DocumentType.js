Ext.define('Sam.store.DocumentType', {
	extend: 'Ext.data.Store',
	
	model: 'Sam.model.DocumentType',
	
	autoLoad: true,

	sorters: { property: 'id'},

	proxy: {
        type: 'ajax',
        
        api: {
        	read : 		'document/load/type',
			create : 	'document/type/add.action',
			update : 	'document/type/update.action',
			destroy : 	'document/type/delete.action',
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
});