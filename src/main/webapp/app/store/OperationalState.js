Ext.define('Sam.store.OperationalState', {
	
	extend: 'Sam.lib.AutoStore',
	
	model: 'Sam.model.OperationalState',
	
	proxy: {
        api: {
        	read : 		'equipment/load/operationalState',
			create : 	'equipment/operationalState/add.action',
			update : 	'equipment/operationalState/update.action',
			destroy : 	'equipment/operationalState/delete.action',
        }
    }	
});