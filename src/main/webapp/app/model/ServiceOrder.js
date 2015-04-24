Ext.define('Sam.model.ServiceOrder', {
	extend: 'Ext.data.Model',
	
	fields:[
	        
	        {name: 'id'					, type: 'number'	, mapping: 'sor_id'						},
	        {name: 'equipment_id'		, type: 'string'	, mapping: 'equipment.equ_id'			},
	        {name: 'type_desc'			, type: 'string'	, mapping: 'type.sot_description'		},
	        {name: 'status_desc'		, type: 'string'	, mapping: 'status.sos_description'		},
	        {name: 'event_id'			, type: 'number'	, mapping: 'event.eve_id'				},
	        {name: 'parent_id'			, type: 'number'	, mapping: 'parent.sor_id'				},
	        {name: 'technician_desc'	, type: 'string'	, mapping: 'technician.tec_description'	},
	        {name: 'priority_desc'		, type: 'string'	, mapping: 'priority.sle_description'	},
	        {name: 'start_forecast'		, type: 'date'		, mapping: 'sor_start_forecast'			, dateFormat: 'time'},
	        {name: 'start'				, type: 'date'		, mapping: 'sor_start'					, dateFormat: 'time'},
	        {name: 'end_forecast'		, type: 'date'		, mapping: 'sor_end_forecast'			, dateFormat: 'time'},
	        {name: 'end'				, type: 'date'		, mapping: 'sor_end'					, dateFormat: 'time'},
	        {name: 'equipment_stop'		, type: 'string'	, mapping: 'sor_equipment_stop'			},
	        {name: 'remarks'			, type: 'string'	, mapping: 'sor_remarks'				}
	]
});