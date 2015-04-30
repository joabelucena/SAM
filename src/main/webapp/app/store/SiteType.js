Ext.define('Sam.store.SiteType', {
	extend: 'Ext.data.Store',
	
	requires: ['Sam.model.SiteType'],
	
	model: 'Sam.model.SiteType',
	
	autoLoad: true,
	async: true,

	proxy: {
		type: 'ajax',
		url: 'sitetype/load',
		reader: {
			type: 'json',
			root: 'data'
		}
	}
	
});