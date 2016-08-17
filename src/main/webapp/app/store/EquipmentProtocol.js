Ext.define('Sam.store.EquipmentProtocol', {
	
	extend: 'Sam.lib.AutoStore',
	
	model: 'Sam.model.EquipmentProtocol',
	
	proxy: {
        api: {
        	read : 		'equipment/load/protocol',
			create : 	'equipment/protocol/add.action',
			update : 	'equipment/protocol/update.action',
			destroy : 	'equipment/protocol/delete.action',
        }
    }	
});