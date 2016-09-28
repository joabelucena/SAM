Ext.define('Sam.store.EquipmentManufacturer', {

	extend : 'Sam.lib.AutoStore',

	model : 'Sam.model.EquipmentManufacturer',

	proxy : {
		api : {
			read : 'equipment/load/manufacturer',
			create : 'equipment/manufacturer/add.action',
			update : 'equipment/manufacturer/update.action',
			destroy : 'equipment/manufacturer/delete.action',
		}
	}
});