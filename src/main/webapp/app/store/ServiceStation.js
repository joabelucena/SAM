Ext.define('Sam.store.ServiceStation', {
	extend: 'Ext.data.Store',
	
	requires: ['Sam.model.ServiceStation'],
	
	model: 'Sam.model.ServiceStation',
	
	autoLoad: true,
	async: true,

	proxy: {
		type: 'ajax',
		url: 'servicestation/load',
		reader: {
			type: 'json',
			root: 'data'
		}
	}
	
});