Ext.define('Sam.store.UserRole', {
	extend: 'Ext.data.Store',
	
	model: 'Sam.model.UserRole',
		
	autoLoad: false,
	
	sorters: { property: 'id'},

	proxy: {
        type: 'ajax',
        
        api: {
        	read : 		'user/role',
			create : 	'user/role/add.action',
			update : 	'user/role/update.action',
			destroy : 	'user/role/delete.action',
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