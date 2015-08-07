Ext.define('Sam.store.ServiceOrderLog', {
	extend: 'Ext.data.Store',
	
	model: 'Sam.model.ServiceOrderLog',
	
	autoLoad: true,
	async: true,

	proxy: {
		type: 'ajax',
		url: 'so/load/log',
		reader: {
			type: 'json',
			root: 'data'
		}
	},
	
});