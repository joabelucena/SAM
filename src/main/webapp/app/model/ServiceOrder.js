Ext.define('Sam.model.ServiceOrder', {
	extend: 'Ext.data.Model',
	requires: ['Sam.model.Equipment'],
	fields:[
	        {name: 'id'				, type: 'number'},
	        {name: 'equipment_id'	, type: 'string' , reference: 'Equipment'},
	        {name: 'type'			, type: 'string'},
	        {name: 'status'			, type: 'string'},
	        {name: 'event_id'		, type: 'number'},
	        {name: 'parent_id'		, type: 'number'},
	        {name: 'technician'		, type: 'string'},
	        {name: 'priority'		, type: 'string'},
	        {name: 'start_forecast'	, type: 'date'	, dateFormat: 'time'},
	        {name: 'start'			, type: 'date'	, dateFormat: 'time'},
	        {name: 'end_forecast'	, type: 'date'	, dateFormat: 'time'},
	        {name: 'end'			, type: 'date'	, dateFormat: 'time'},
	        {name: 'equipment_stop'	, type: 'string'},
	        {name: 'remarks'		, type: 'string'}
	]
});
