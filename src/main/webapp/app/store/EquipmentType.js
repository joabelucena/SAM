Ext.define('Sam.store.EquipmentType', {
	extend: 'Ext.data.Store',
	
	requires: ['Sam.model.EquipmentType'],
	
	model: 'Sam.model.EquipmentType',
	
	autoLoad: true,
	async: true,

	proxy: {
		type: 'ajax',
		url: 'equipment/load/type',
		reader: {
			type: 'json',
			root: 'data'
		}
	}
	
});