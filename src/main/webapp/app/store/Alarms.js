Ext.define('Sam.store.Alarms', {
	extend: 'Ext.data.ArrayStore',
	model: 'Sam.model.Alarm',

	proxy: {
		type: 'ajax',
		url: '/events/load',
		reader: {
			type: 'json',
			root: 'data'
		}
	}
});