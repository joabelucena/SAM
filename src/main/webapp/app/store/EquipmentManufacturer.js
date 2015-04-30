Ext.define('Sam.store.EquipmentManufacturer', {
	extend: 'Ext.data.Store',
	
	model: 'Sam.model.EquipmentManufacturer',
	
	autoLoad: true,

	proxy: {
        type: 'ajax',
        
        api: {
        	read : 		'equipment/load/manufacturer',
			create : 	'equipment/manufacturer/add.action',
			update : 	'equipment/manufacturer/update.action',
			destroy : 	'equipment/manufacturer/delete.action',
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