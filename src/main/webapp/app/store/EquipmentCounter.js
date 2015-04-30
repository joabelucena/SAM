Ext.define('Sam.store.EquipmentCounter', {
	extend: 'Ext.data.Store',
	
	model: 'Sam.model.EquipmentCounter',
	
	autoLoad: false,

	proxy: {
        type: 'ajax',
        
        api: {
        	read : 		'equipment/load/counter',
			create : 	'equipment/counter/add.action',
			update : 	'equipment/counter/update.action',
			destroy : 	'equipment/counter/delete.action',
        },
        reader: {
            type: 'json',
            successProperty: 'success',
            root: 'data',
            messageProperty: 'message'
        },
        writer: {
            type: 'json',
            writeAllFields: false,
            root: 'data'
        }
    }
	
});