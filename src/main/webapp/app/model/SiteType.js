Ext.define('Sam.model.SiteType', {
	extend: 'Ext.data.Model',
	
	fields:[
	        
	        {name: 'id'				, type: 'number'	, mapping: 'sty_id'								},
	        {name: 'description'	, type: 'string'	, mapping: 'sty_description' 					}

	       ]
});