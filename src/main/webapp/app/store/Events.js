Ext.define('Sam.store.Events', {
	extend: 'Ext.data.Store',
	
	requires: ['Sam.model.Event'],
	
	model: 'Sam.model.Event',
	
	autoLoad: true,
	async: true,

	proxy: {
		type: 'ajax',
		url: 'events/load',
		reader: {
			type: 'json',
			root: 'data'
		}
	},
	
	sortOnLoad: true,
	sorters: { property: 'severity', direction: 'DESC'}
	
});