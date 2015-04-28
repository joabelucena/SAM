Ext.define('Sam.model.EquipmentCounter', {
	extend: 'Ext.data.Model',
	
	fields:[
	        
	        {name: 'id'		, type: 'number'	, mapping: 'cty_id'				},
	        {name: 'desc'	, type: 'string'	, mapping: 'cty_description'	}

	        ]
});