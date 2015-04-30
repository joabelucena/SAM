Ext.define('Sam.model.OperationalState', {
	extend: 'Ext.data.Model',
	
	fields:[
	        
	        {name: 'id'				, type: 'number'	, mapping: 'ost_id'								},
	        {name: 'model_desc'		, type: 'string'	, mapping: 'equipmentmodel.emo_description'		},
	        {name: 'desc'			, type: 'string'	, mapping: 'ost_description' 					}

	       ]
});