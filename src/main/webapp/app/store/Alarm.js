Ext.define('Sam.store.Alarm', {
	
	extend : 'Sam.lib.AutoStore',

	model : 'Sam.model.Alarm',

	proxy : {
		api : {
			read : 'alarm/load',
			create : 'alarm/add.action',
			update : 'alarm/update.action',
			destroy : 'alarm/delete.action',
		},
	}
});