Ext.define('Sam.model.Site', {
	extend: 'Ext.data.Model',
	
	fields:[
	        
	        {name: 'id'				, type: 'string'	, mapping: 'sit_id'								},
	        {name: 'description'	, type: 'string'	, mapping: 'sit_description' 					},
	        {name: 'short_name'		, type: 'string'	, mapping: 'sit_shortname'	 					},
	        {name: 'station_desc'	, type: 'string'	, mapping: 'site'}

	       ]
});