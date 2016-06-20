Ext.define('Sam.store.ServiceOrderRules', {
	extend: 'Ext.data.Store',
	
	model: 'Sam.model.ServiceOrderRules',
		
	autoLoad: false,
	
	sorters: { property: 'id'},

	proxy: {
        type: 'ajax',
        
        api: {
        	read : 		'so/load/rules',
			create : 	'so/rules/add.action',
			update : 	'so/rules/update.action',
			destroy : 	'so/rules/delete.action',
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