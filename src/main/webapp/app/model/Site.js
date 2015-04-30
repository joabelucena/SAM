Ext.define('Sam.model.Site', {
	extend: 'Ext.data.Model',
	
	fields:[
	        
	        {name: 'id'				, type: 'number'	, mapping: 'sit_id'								},
	        {name: 'description'	, type: 'string'	, mapping: 'sit_description' 					},
	        {name: 'short_name'		, type: 'string'	, mapping: 'sit_shortname'	 					},
	        {name: 'station_desc'	, type: 'string'	, mapping: 'servicestation.sst_description'		},
	        {name: 'parent_id'		, type: 'string'	, mapping: 'site.sit_id'						},
	        {name: 'type'			, type: 'string'	, mapping: 'sitetype.sty_description'			}			

	       ]
});