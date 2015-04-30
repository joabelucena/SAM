Ext.define('Sam.store.Site', {
	extend: 'Ext.data.Store',
	
	requires: ['Sam.model.Site'],
	
	model: 'Sam.model.Site',
	
	autoLoad: true,
	async: true,

	proxy: {
		type: 'ajax',
		url: 'site/load',
		reader: {
			type: 'json',
			root: 'data'
		}
	}
	
});