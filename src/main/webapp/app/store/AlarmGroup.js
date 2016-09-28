Ext.define('Sam.store.AlarmGroup', {
	extend : 'Sam.lib.AutoStore',

	model : 'Sam.model.AlarmGroup',

	proxy : {
		api : {
			read : 'alarm/load/group',
			create : 'alarm/group/add.action',
			update : 'alarm/group/update.action',
			destroy : 'alarm/group/delete.action',
		}
	}
});