Ext.define('Sam.model.EquipmentModel', {
	extend: 'Ext.data.Model',
	/*
	 * http://www.loiane.com/2013/11/ext-js-4-salvar-modelstore-que-tenha-associations-hasmany-e-hasone/
	 * 
	 */
	fields:[
	        
	        {name: 'id'				, type: 'number'								},
	        {name: 'desc'			, type: 'string'								},
	        
	        /** Form Aux Mappings **/
			{name: 'model_id'		, type: 'number'	, mapping: 'id'				},
			{name: 'model_desc'		, type: 'string'	, mapping: 'desc'			},
			{name: 'prot_id'		, type: 'number'	, mapping: 'protocol.id'	},
	        {name: 'prot_desc'		, type: 'string'	, mapping: 'protocol.desc'	},
        ],
	
    belongsTo:  [{name: 'protocol', model: 'Sam.model.EquipmentProtocol', foreignKey: 'prot_id'}],
    
    hasMany: [
              {name: 'documents', model: 'Sam.model.Document'	, foreignKey: 'model_id'},
              {name: 'oids'		, model: 'Sam.model.OID'		, foreignKey: 'model_id'}
    ]
});