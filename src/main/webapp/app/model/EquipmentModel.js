Ext.define('Sam.model.EquipmentModel', {
	extend: 'Ext.data.Model',
	/*
	 * http://www.loiane.com/2013/11/ext-js-4-salvar-modelstore-que-tenha-associations-hasmany-e-hasone/
	 * 
	 */
	
//	idProperty: 'id',
	
	fields:[
	        
	        {name: 'id'				, type: 'number'									},
	        {name: 'desc'			, type: 'string'									},
	        {name: 'prot_id'		, type: 'number'		, mapping: 'protocol.id'	},
	        {name: 'prot_desc'		, type: 'string'		, mapping: 'protocol.desc'	},
        ],
	
    belongsTo:  [{name: 'protocol', model: 'Sam.model.EquipmentProtocol', foreignKey: 'prot_id'}],
    
    hasMany: [{name: 'documents', model: 'Sam.model.Document'}],
    
    proxy: {
        type: 'ajax',
        
        api: {
        	read : 		'equipment/load/model',
			create : 	'equipment/model/add.action',
			update : 	'equipment/model/update.action',
			destroy : 	'equipment/model/delete.action',
        },
        reader: {
            type: 'json',
            successProperty: 'success',
            root: 'data',
            messageProperty: 'message'
        },
        writer: {
            type: 'json',
            writeAllFields: true,
            root: 'data'
        }
    }


});