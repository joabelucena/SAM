Ext.define('Sam.store.EquipmentProtocol', {
	extend: 'Ext.data.Store',
	
	requires: ['Sam.model.EquipmentProtocol'],
	
	model: 'Sam.model.EquipmentProtocol',
	
	autoLoad: true,
	async: true,

	proxy: {
        type: 'ajax',
        
        api: {
        	read : 		'equipment/load/protocol',
			create : 	'equipment/protocol/add.action',
			update : 	'equipment/protocol/update.action',
			destroy : 	'equipment/protocol/delete.action',
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