Ext.define('Sam.store.EquipmentCounter', {
	extend: 'Ext.data.Store',
	
	requires: ['Sam.model.EquipmentCounter'],
	
	model: 'Sam.model.EquipmentCounter',
	
	autoLoad: true,
	async: true,

	proxy: {
		type: 'ajax',
		url: 'equipment/load/counter',
		reader: {
			type: 'json',
			root: 'data'
		}
	}
	
});