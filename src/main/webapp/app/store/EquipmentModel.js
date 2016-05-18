Ext.define('Sam.store.EquipmentModel', {
	extend: 'Ext.data.Store',
	
	model: 'Sam.model.EquipmentModel',
	
	autoLoad: true,

	sorters: { property: 'id'},

	proxy: {
        type: 'ajax',
        
        api: {
        	read : 		'equipment/load/model',
			create : 	'equipment/model/add.action',
			update : 	'equipment/model/update.action',
			destroy : 	'equipment/model/delete.action',
        },
        reader: {
            type: 'json',
            successProperty: 'success',
            root: 'data',
            messageProperty: 'message'
        },
        writer: {
            type: 'associatedjson',
            dateFormat: 'time',
            writeAllFields: true
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