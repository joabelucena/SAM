Ext.define('Sam.store.ServiceOrderJob', {
	extend: 'Ext.data.Store',
	
	model: 'Sam.model.ServiceOrderJob',
		
	autoLoad: false,

	proxy: {
        type: 'ajax',
        
        api: {
        	read : 		'so/load/job',
			create : 	'so/job/add.action',
			update : 	'so/job/update.action',
			destroy : 	'so/job/delete.action',
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