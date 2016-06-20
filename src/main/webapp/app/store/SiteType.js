Ext.define('Sam.store.SiteType', {
	extend: 'Ext.data.Store',
	
	requires: ['Sam.model.SiteType'],
	
	model: 'Sam.model.SiteType',
	
	autoLoad: false,
	async: true,

	proxy: {
        type: 'ajax',
        
        api: {
        	read : 		'site/load/sitetype',
			create : 	'site/sitetype/add.action',
			update : 	'site/sitetype/update.action',
			destroy : 	'site/sitetype/delete.action',
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