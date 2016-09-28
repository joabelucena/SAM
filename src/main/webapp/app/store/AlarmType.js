Ext.define('Sam.store.AlarmType', {
	extend : 'Sam.lib.AutoStore',

	model : 'Sam.model.AlarmType',

	proxy : {
		api : {
			read : 'alarm/load/type',
			create : 'alarm/type/add.action',
			update : 'alarm/type/update.action',
			destroy : 'alarm/type/delete.action',
		}
	}
});