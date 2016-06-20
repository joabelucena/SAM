Ext.define('Sam.store.ServiceOrderType', {
	extend: 'Ext.data.Store',
	
	model: 'Sam.model.ServiceOrderType',
		
	autoLoad: false,

	proxy: {
        type: 'ajax',
        
        api: {
        	read : 		'so/load/type',
			create : 	'so/type/add.action',
			update : 	'so/type/update.action',
			destroy : 	'so/type/delete.action',
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
        }
    }	
});