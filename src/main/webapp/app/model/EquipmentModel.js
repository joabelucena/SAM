Ext.define('Sam.model.EquipmentModel', {
	extend: 'Ext.data.Model',
	
	fields:[
	        
	        {name: 'id'			, type: 'number'	, mapping: 'emo_id'				},
	        {name: 'desc'		, type: 'string'	, mapping: 'emo_description'	},
	        {name: 'prot_desc'	, type: 'string'	, mapping: 'protocol.epr_description'	}

	        ]
});