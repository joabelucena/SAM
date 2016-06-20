Ext.define('Sam.store.EquipmentType', {
	extend: 'Ext.data.Store',
	
	model: 'Sam.model.EquipmentType',
	
	autoLoad: false,

	proxy: {
        type: 'ajax',
        
        api: {
        	read : 		'equipment/load/type',
			create : 	'equipment/type/add.action',
			update : 	'equipment/type/update.action',
			destroy : 	'equipment/type/delete.action',
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