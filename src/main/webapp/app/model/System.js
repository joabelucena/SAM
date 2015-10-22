Ext.define('Sam.model.System', {
	extend: 'Ext.data.Model',
	
	fields:[
	        {name: 'id'				, type: 'string'						},
	        {name: 'desc'			, type: 'string'						},
	        
			/** Form Aux Mappings **/
			{name: 'system_id'		, type: 'string'	, mapping: 'id'		},
			{name: 'system_desc'	, type: 'string'	, mapping: 'desc'	}
	       ]
});