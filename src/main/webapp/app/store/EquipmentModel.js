Ext.define('Sam.store.EquipmentModel', {
	extend: 'Ext.data.Store',
	
	requires: ['Sam.model.EquipmentModel'],
	
	model: 'Sam.model.EquipmentModel',
	
	autoLoad: true,
	async: true,

	proxy: {
		type: 'ajax',
		url: 'equipment/load/model',
		reader: {
			type: 'json',
			root: 'data'
		}
	}
	
});