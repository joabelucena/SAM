Ext.define('Sam.model.Document', {
	extend: 'Ext.data.Model',
	fields:[
	        {name: 'id'				, type: 'number'	, defaultValue: 0		},
	        {name: 'desc'														},
	        {name: 'url'			, type: 'string'							},
	        {name: 'model_id'		, type: 'number'							},
	        ]
});