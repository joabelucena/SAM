Ext.define('Sam.model.Technician', {
	extend: 'Ext.data.Model',
	
	fields:[
	        
	        {name: 'id'			, type: 'string'							},
	        {name: 'name'		, type: 'string'							},

	        {name: 'site_id'	, type: 'number'	, mapping: 'site.id'	},
	        {name: 'site_desc'	, type: 'string'	, mapping: 'site.desc'	}

	        ]
});