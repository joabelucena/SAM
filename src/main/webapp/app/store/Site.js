Ext.define('Sam.store.Site', {
	extend: 'Ext.data.Store',
	
	model: 'Sam.model.Site',
	
	autoLoad: true,

	proxy: {
        type: 'ajax',
        
        api: {
        	read : 		'site/load',
			create : 	'site/add.action',
			update : 	'site/update.action',
			destroy : 	'site/delete.action',
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