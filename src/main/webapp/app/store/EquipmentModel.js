Ext.define('Sam.store.EquipmentModel', {
	
	extend: 'Sam.lib.AutoStore',
	
	model: 'Sam.model.EquipmentModel',
	
	proxy: {
        api: {
        	read : 		'equipment/load/model',
			create : 	'equipment/model/add.action',
			update : 	'equipment/model/update.action',
			destroy : 	'equipment/model/delete.action',
        }
	}
});