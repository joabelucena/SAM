Ext.define('Sam.store.System', {
	extend: 'Ext.data.Store',
	
	requires: ['Sam.model.System'],
	
	model: 'Sam.model.System',
	
	autoLoad: true,
	async: true,

	proxy: {
        type: 'ajax',
        
        api: {
        	read : 		'equipment/load/system',
			create : 	'equipment/system/add.action',
			update : 	'equipment/system/update.action',
			destroy : 	'equipment/system/delete.action',
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