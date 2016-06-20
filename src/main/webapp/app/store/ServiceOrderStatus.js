Ext.define('Sam.store.ServiceOrderStatus', {
	extend: 'Ext.data.Store',
	
	model: 'Sam.model.ServiceOrderStatus',
		
	autoLoad: false,

	proxy: {
        type: 'ajax',
        
        api: {
        	read : 		'so/load/status',
			create : 	'so/status/add.action',
			update : 	'so/status/update.action',
			destroy : 	'so/status/delete.action',
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
            root: 'data',
        },
    }	
});