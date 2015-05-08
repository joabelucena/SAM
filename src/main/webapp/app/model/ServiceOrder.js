Ext.define('Sam.model.ServiceOrder', {
	extend: 'Ext.data.Model',
	
	fields:[
	        
	        {name: 'id'					, type: 'number'														},
	        {name: 'equipment_id'		, type: 'string'	, mapping: 'equipment.id'							},
	        {name: 'type_desc'			, type: 'string'	, mapping: 'type.desc'								},
	        {name: 'status_desc'		, type: 'string'	, mapping: 'status.desc'							},
	        {name: 'event_id'			, type: 'number'	, mapping: 'event.id'								},
	        {name: 'parent_id'			, type: 'number'	, mapping: 'parent.id'								},
	        {name: 'technician_desc'	, type: 'string'	, mapping: 'technician.name'						},
	        {name: 'priority_desc'		, type: 'string'	, mapping: 'priority.desc'							},
	        {name: 'start_forecast'		, type: 'date'		, mapping: 'startForecast'		, dateFormat: 'time'},
	        {name: 'start'				, type: 'date'		, mapping: 'start'				, dateFormat: 'time'},
	        {name: 'end_forecast'		, type: 'date'		, mapping: 'endForecast'		, dateFormat: 'time'},
	        {name: 'end'				, type: 'date'		, mapping: 'end'				, dateFormat: 'time'},
	        {name: 'equipment_stop'		, type: 'string'	, mapping: 'stoped'									},
	        {name: 'remarks'			, type: 'string'	, mapping: 'remark'									}
	]
});