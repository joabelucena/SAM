Ext.define('Sam.store.ServiceOrderJob', {
	extend: 'Ext.data.Store',
	
	requires: ['Sam.model.ServiceOrderJob'],
	
	model: 'Sam.model.ServiceOrderJob',
	
	autoLoad: true,
	async: true,

	proxy: {
		type: 'ajax',
		url: 'so/load/job',
		reader: {
			type: 'json',
			root: 'data'
		}
	}
	
});