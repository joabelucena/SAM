Ext.define('Sam.model.EquipmentModel', {
	extend: 'Ext.data.Model',
	
	fields:[
	        
	        {name: 'id'				, type: 'number'											},
	        {name: 'desc'			, type: 'string'											},
	        {name: 'prot_id'		, type: 'number'	, reference: 'EquipmentProtocol'		},
	        //{name: 'prot_desc'		, type: 'string'	, mapping: 'protocol.epr_description'	}

	        ]
});