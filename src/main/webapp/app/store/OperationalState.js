Ext.define('Sam.store.OperationalState', {
	extend: 'Ext.data.Store',
	
	requires: ['Sam.model.OperationalState'],
	
	model: 'Sam.model.OperationalState',
	
	autoLoad: true,
	async: true,

	proxy: {
        type: 'ajax',
        
        api: {
        	read : 		'equipment/load/operationalState',
			create : 	'equipment/operationalState/add.action',
			update : 	'equipment/operationalState/update.action',
			destroy : 	'equipment/operationalState/delete.action',
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