Ext.define('Sam.store.Equipment', {
	
	extend: 'Sam.lib.AutoStore',
	
	model: 'Sam.model.Equipment',
	
	pageSize: 5000,

	proxy: {
      
        enablePaging: true,
        
        api: {
        	read : 		'equipment/load',
			create : 	'equipment/add.action',
			update : 	'equipment/update.action',
			destroy : 	'equipment/delete.action',
        }
    }
	
});