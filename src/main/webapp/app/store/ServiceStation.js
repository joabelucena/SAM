Ext.define('Sam.store.ServiceStation', {
	extend: 'Ext.data.Store',
	
	requires: ['Sam.model.ServiceStation'],
	
	model: 'Sam.model.ServiceStation',
	
	autoLoad: true,

	proxy: {
		type: 'ajax',
		url: 'site/load/station',
		reader: {
			type: 'json',
			root: 'data'
		}
	}
	
});