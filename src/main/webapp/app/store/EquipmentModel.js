Ext.define('Sam.store.EquipmentModel', {
	extend: 'Ext.data.Store',
	
	model: 'Sam.model.EquipmentModel',
	
	autoLoad: true,

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
            type: 'json',
            writeAllFields: false,
            root: 'data'
        },
        listeners: {
            exception: function(proxy, response, operation){
            	console.log('exception');
            },
            success: function(proxy, response, operation){
            	console.log('success');
            },
            
            failure: function(proxy, response, operation){
                console.log('failure');
            },
        }
    }
});