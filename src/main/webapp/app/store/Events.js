Ext.define('Sam.store.Events', {
	extend: 'Ext.data.Store',
	
	requires: ['Sam.model.Event'],
	
	model: 'Sam.model.Event',
	
	pageSize: 30,
	
	autoLoad: true,
	
	async: true,
	
	proxy: {
		type: 'ajax',
		url: 'events/load',
		
		enablePaging: true,
		
		reader: {
			type: 'json',
			root: 'data',
			totalProperty: 'total'				
		}
	},
	
	sortOnLoad: true,
	sorters: { property: 'severity', direction: 'DESC'}
	
});