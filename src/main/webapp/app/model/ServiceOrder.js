Ext.define('Sam.model.ServiceOrder', {
	extend: 'Ext.data.Model',
	fields:[
	        {name: 'id'				, type: 'number'},
	        {name: 'equipment_id'	, type: 'string'},
	        {name: 'type'			, type: 'string'},
	        {name: 'status'			, type: 'string'},
	        {name: 'event_id'		, type: 'number'},
	        {name: 'parent_id'		, type: 'number'},
	        {name: 'technician'		, type: 'string'},
	        {name: 'priority'		, type: 'string'},
	        {name: 'start_forecast'	, type: 'date'	, dateFormat: 'c'},
	        {name: 'start'			, type: 'date'	, dateFormat: 'n'},
	        {name: 'end_forecast'	, type: 'date'	, dateFormat: 'c'},
	        {name: 'end'			, type: 'date'	, dateFormat: 'n'},
	        {name: 'equipment_stop'	, type: 'string'},
	        {name: 'remarks'		, type: 'string'}
	]
});
