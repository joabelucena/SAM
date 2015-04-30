Ext.define('Sam.model.ServiceStation', {
	extend: 'Ext.data.Model',
	
	fields:[
	        
	        {name: 'id'				, type: 'number'	, mapping: 'sst_id'				},
	        {name: 'description'	, type: 'string'	, mapping: 'sst_description'	}

	       ]
});