Ext.define('Sam.model.ServiceOrderJob', {
	extend: 'Ext.data.Model',
	
	fields:[
	        
	        {name: 'id'		, type: 'string'	, mapping: 'soj_id'				},
	        {name: 'desc'	, type: 'string'	, mapping: 'soj_description'	}

	        ]
});