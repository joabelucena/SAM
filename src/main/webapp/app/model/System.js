Ext.define('Sam.model.SubSystem', {
	extend: 'Ext.data.Model',
	
	fields:[
	        
	        {name: 'id'				, type: 'number'	, mapping: 'ssy_id'				},
	        {name: 'desc'	, type: 'string'	, mapping: 'ssy_description'	}

	       ]
});