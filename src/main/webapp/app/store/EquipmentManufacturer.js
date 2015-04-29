Ext.define('Sam.store.EquipmentManufacturer', {
	extend: 'Ext.data.Store',
	
	model: 'Sam.model.EquipmentManufacturer',
	
	autoLoad: true,

	proxy: {
        type: 'ajax',
        
        api: {
        	read : 		'equipment/load/manufacturer',
			create : 	'equipment/manufacturer/add.action',
			update : 	'equipment/manufacturer/update.action',
			destroy : 	'equipment/manufacturer/delete.action',
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
        },
        listeners: {
            exception: function(proxy, response, operation){
                Ext.MessageBox.show({
                    title: 'Falha na Requisição',
                    msg: operation.getError(),
                    icon: Ext.MessageBox.ERROR,
                    buttons: Ext.Msg.OK
                });
            }
        }
    }
});