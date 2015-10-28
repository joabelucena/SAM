Ext.define('Sam.model.Event', {
	extend: 'Ext.data.Model',
	fields:[
	        
	        {name: 'id'						, type: 'number'						},
	        {name: 'reco'					, type: 'boolean'						},
	        {name: 'reco_user'				, type: 'string'						},
	        {name: 'reco_date'				, type: 'date'		, dateFormat: 'c'	},
	        {name: 'severity_desc'			, type: 'string'						},
	        {name: 'severity_id'			, type: 'string'						},
	        {name: 'equipment_id'			, type: 'string'						},
	        {name: 'equipment_model'		, type: 'string'						},
	        {name: 'equipment_manufacturer'	, type: 'string'						},
	        {name: 'alarm_id'				, type: 'string'						},
	        {name: 'alarm_desc'				, type: 'string'						},
	        {name: 'site_desc'				, type: 'string'						},
	        {name: 'system_id'				, type: 'string'						},
	        {name: 'system_desc'			, type: 'string'						},
	        
	        {name: 'so_id'					, type: 'string'						},
	        {name: 'so_status'				, type: 'string'						},
	        
	        {name: 'datetime'				, type: 'date'		, dateFormat: 'c'	}
	]
});