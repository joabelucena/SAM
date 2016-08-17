Ext.define('Sam.store.EquipmentType', {
	
	extend: 'Sam.lib.AutoStore',
	
	model: 'Sam.model.EquipmentType',
	
	proxy: {
        api: {
        	read : 		'equipment/load/type',
			create : 	'equipment/type/add.action',
			update : 	'equipment/type/update.action',
			destroy : 	'equipment/type/delete.action',
        }
    }
	
});