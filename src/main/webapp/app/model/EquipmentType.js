Ext.define('Sam.model.EquipmentType', {
	extend: 'Ext.data.Model',
	
	fields:[
	        
	        {name: 'id'		, type: 'number'	, mapping: 'ety_id'				},
	        {name: 'desc'	, type: 'string'	, mapping: 'ety_description'	}

	        ]
});