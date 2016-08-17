Ext.define('Sam.store.System', {
	
	extend: 'Sam.lib.AutoStore',
	
	model: 'Sam.model.System',
	
	proxy: {
        api: {
        	read : 		'equipment/load/system',
			create : 	'equipment/system/add.action',
			update : 	'equipment/system/update.action',
			destroy : 	'equipment/system/delete.action',
        }
	}	
});