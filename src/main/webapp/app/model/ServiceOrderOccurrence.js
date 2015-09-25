Ext.define('Sam.model.ServiceOrderOccurrence', {
	extend: 'Ext.data.Model',
	fields:[
	        {name: 'id'					, type: 'number'								},
	        {name: 'start'				, type: 'date'		, dateFormat: 'time'		},
	        {name: 'end'				, type: 'date'		, dateFormat: 'time'		},
	        
	        /** Association Keys **/
	        {name: 'service_id'			, type: 'string'	, mapping: 'service.id'		},
	        {name: 'technician_id'		, type: 'string'	, mapping: 'technician.id'	},
	        {name: 'so_id'				, type: 'number'								}
	],
	
	
	belongsTo: [
				{name: 'service'		, model: 'Sam.model.ServiceOrderType'	, foreignKey: 'service_id'			},
				{name: 'technician'		, model: 'Sam.model.Technician'			, foreignKey: 'technician_id'		},
	            ]
});