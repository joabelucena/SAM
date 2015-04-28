Ext.define('Sam.model.Technician', {
	extend: 'Ext.data.Model',
	
	fields:[
	        
	        {name: 'id'			, type: 'string'	, mapping: 'tec_id'					},
	        {name: 'name'		, type: 'string'	, mapping: 'tec_name'				},
	        {name: 'site_desc'	, type: 'string'	, mapping: 'site.sit_description'	}

	        ]
});