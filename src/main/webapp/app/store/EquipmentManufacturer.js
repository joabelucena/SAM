Ext.define('Sam.store.EquipmentManufacturer', {
	extend: 'Ext.data.Store',
	
	requires: ['Sam.model.EquipmentManufacturer'],
	
	model: 'Sam.model.EquipmentManufacturer',
	
	autoLoad: true,
	async: true,

	
	
	/*
	proxy: {
		
		type: 'ajax',
    	
		
    	api:{
    		read: 'equipment/load/manufacturer',
    		create:  'equipment/manufacturer/add.action',
    		update: 'equipment/manufacturer/update.action',
    		destroy: 'equipment/manufacturer/delete.action',
    		
    	},
		
		
		url: 'equipment/load/manufacturer',
		reader: {
			type: 'json',
			root: 'data'
		}
	}
	*/
	
});