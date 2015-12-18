Ext.define('Sam.model.ServiceOrder', {
	extend: 'Ext.data.Model',
	
	fields:[
	        {name: 'id'					, type: 'number'															},
	        {name: 'datetime'			, type: 'date'										, dateFormat: 'time'	},
			{name: 'startForecast'		, type: 'date'										, dateFormat: 'time'	},
			{name: 'start'				, type: 'date'										, dateFormat: 'time'	},
	        {name: 'endForecast'		, type: 'date'										, dateFormat: 'time'	},
	        {name: 'end'				, type: 'date'										, dateFormat: 'time'	},
	        {name: 'remark'				, type: 'string'															},
	        
	        /** Aux Transient Field **/
	        {name: 'logRemark'			, type: 'string'															},
	        
	        /** Association Keys **/
	        {name: 'type_id'			, type: 'number'	, mapping: 'type.id'									},
	        {name: 'status_id'			, type: 'number'	, mapping: 'status.id'									},
	        {name: 'event_id'			, type: 'number'	, mapping: 'event.id'									},
	        {name: 'parent_id'			, type: 'number'	, mapping: 'parent.id'									},
	        {name: 'technician_id'		, type: 'string'	, mapping: 'technician.id'								},
	        {name: 'priority_id'		, type: 'string'	, mapping: 'priority.id'								},
	        {name: 'equipment_id'		, type: 'string'	, mapping: 'equipment.id'								},
	        
	        /** Grid Fields **/
	        {name: 'type_desc'			, type: 'string'	, mapping: 'type.desc'									},
	        {name: 'status_desc'		, type: 'string'	, mapping: 'status.desc'								},
	        {name: 'technician_name'	, type: 'string'	, mapping: 'technician.name'							},
	        {name: 'priority_desc'		, type: 'string'	, mapping: 'priority.desc'								},
	        
	        
	        /** Grid Fields: date & time  **/
	        {name: 'start_forecast_date', mapping: 'startForecast'		, convert: function(v,r){return Ext.Date.format(new Date(parseInt(v)), 'd/m/Y')}},
	        {name: 'start_forecast_time', mapping: 'startForecast'		, convert: function(v,r){return Ext.Date.format(new Date(parseInt(v)), 'H:i') }	},
	        {name: 'end_forecast_date'	, mapping: 'endForecast'		, convert: function(v,r){return Ext.Date.format(new Date(parseInt(v)), 'd/m/Y')}},
	        {name: 'end_forecast_time'	, mapping: 'endForecast'		, convert: function(v,r){return Ext.Date.format(new Date(parseInt(v)), 'H:i') }	},


	        /** Grid Fields: equipment  **/
	        {name: 'equipment_model'	, type: 'string'	, mapping: 'equipment.model.desc'						},
	        {name: 'equipment_manuf'	, type: 'string'	, mapping: 'equipment.manufacturer.desc'				},
	        {name: 'equipment_system'	, type: 'string'	, mapping: 'equipment.system.desc'						},
	        {name: 'equipment_site'		, type: 'string'	, mapping: 'equipment.site.desc'						}
	        
	        ],
	        
	        
	        belongsTo:  [
	                     {name: 'type'		, model: 'Sam.model.ServiceOrderType'		, foreignKey: 'type_id'			},
	                     {name: 'status'	, model: 'Sam.model.ServiceOrderStatus'		, foreignKey: 'status_id'		},
	                     {name: 'event'		, model: 'Sam.model.Event'					, foreignKey: 'event_id'		},
	                     {name: 'parent'	, model: 'Sam.model.ServiceOrder'			, foreignKey: 'parent_id'		},
	                     {name: 'technician', model: 'Sam.model.Technician'				, foreignKey: 'technician_id'	},
	                     {name: 'priority'	, model: 'Sam.model.SeverityLevel'			, foreignKey: 'priority_id'		},
	                     {name: 'equipment'	, model: 'Sam.model.Equipment'				, foreignKey: 'equipment_id'	}
	                     ],

	        
	        
	        hasMany : [
	                   {name: 'occurrences'	, model : 'Sam.model.ServiceOrderOccurrence'},
	                   {name: 'log'			, model : 'Sam.model.ServiceOrderLog'		, foreignKey: 'so_id'			}
	                   ],
	        idProperty: 'mockId',           
			proxy: {
			    type: 'ajax',
			    
			    api: {
			    	create : 	'so/add.action',
			    },
			    writer: {
			        type: 'associatedjson',
			        writeAllFields: true,
			    }
			}
});

