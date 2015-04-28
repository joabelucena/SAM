Ext.define('Sam.model.EquipmentManufacturer', {
	extend : 'Ext.data.Model',

	idProperty : 'id',

	fields : [

	{
		name : 'id',
		type : 'number',
		mapping : 'ema_id'
	}, {
		name : 'desc',
		type : 'string',
		mapping : 'ema_description'
	}

	],
	
	proxy: {
        type: 'ajax',
        
        api: {
        	read : 'equipment/load/manufacturer',
			create : 'equipment/manufacturer/add.action',
			update : 'equipment/manufacturer/update.action',
			destroy : 'equipment/manufacturer/delete.action',
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
                    title: 'REMOTE EXCEPTION',
                    msg: operation.getError(),
                    icon: Ext.MessageBox.ERROR,
                    buttons: Ext.Msg.OK
                });
            }
        }
    }

	/*
	proxy : {

		type : 'ajax',

		api : {
			read : 'equipment/load/manufacturer',
			create : 'equipment/manufacturer/add.action',
			update : 'equipment/manufacturer/update.action',
			destroy : 'equipment/manufacturer/delete.action',

		}
	}
	*/

});