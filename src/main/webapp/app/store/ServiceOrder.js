Ext.define('Sam.store.ServiceOrder', {
	extend: 'Ext.data.Store',
	
	requires: ['Sam.model.ServiceOrder'],
	
	model: 'Sam.model.ServiceOrder',
	
	autoLoad: true,
	async: true,

	proxy: {
		type: 'ajax',
		url: 'so/load',
		reader: {
			type: 'json',
			root: 'data'
		}
	},
	
});