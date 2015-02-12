Ext.define('Sam.store.Alarms', {
	extend: 'Ext.data.Store',
	
	requires: ['Sam.model.Alarm'],
	
	model: 'Sam.model.Alarm',
	
	autoLoad: true,
	async: true,

	proxy: {
		type: 'ajax',
		url: 'events/load',
		reader: {
			type: 'json',
			root: 'data'
		}
	}
});