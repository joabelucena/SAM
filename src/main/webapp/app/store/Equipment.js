Ext.define('Sam.store.Equipment', {
	
	extend: 'Sam.lib.AutoStore',
	
	model: 'Sam.model.Equipment',
	
	pageSize: 30,

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