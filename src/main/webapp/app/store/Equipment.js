Ext.define('Sam.store.Equipment', {
	extend: 'Ext.data.Store',
	
	requires: ['Sam.model.Equipment'],
	
	model: 'Sam.model.Equipment',
	
	autoLoad: true,
	async: true,

	proxy: {
		type: 'ajax',
		url: 'equipment/load',
		reader: {
			type: 'json',
			root: 'data'
		}
	}
	
});