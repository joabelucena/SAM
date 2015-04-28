Ext.define('Sam.store.Technician', {
	extend: 'Ext.data.Store',
	
	requires: ['Sam.model.Technician'],
	
	model: 'Sam.model.Technician',
	
	autoLoad: true,
	async: true,

	proxy: {
		type: 'ajax',
		url: 'technician/load',
		reader: {
			type: 'json',
			root: 'data'
		}
	}
	
});