Ext.define('Sam.model.SiteType', {
	extend: 'Ext.data.Model',
	
	fields:[
	        
	        {name: 'id'				, type: 'string'	, mapping: 'sty_id'								},
	        {name: 'description'	, type: 'string'	, mapping: 'sty_description' 					}

	       ]
});