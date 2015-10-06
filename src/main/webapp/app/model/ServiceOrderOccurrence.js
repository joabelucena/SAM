Ext.define('Sam.model.ServiceOrderOccurrence', {
	extend: 'Ext.data.Model',
	fields:[
	        {name: 'id'					, type: 'number'												},
	        {name: 'start'				, type: 'date'		, dateFormat: 'time'	, serialize: null	},
	        {name: 'end'				, type: 'date'		, dateFormat: 'time'	, serialize: null	},
	        {name: 'remark'				, type: 'string'												},
	        
	        /** Association Keys **/
	        {name: 'service_id'			, type: 'string'	, mapping: 'service.id'		, persist: false},
	        {name: 'technician_id'		, type: 'string'	, mapping: 'technician.id'	, persist: false},
	        
	        /** Grid Fields: date & time  **/
	        {name: 'start_date'			, type: 'date'		, mapping: 'start'	, dateFormat: 'time'	},
	        {name: 'start_time'			, type: 'date'		, mapping: 'start'	, dateFormat: 'time'	},
	        {name: 'end_date'			, type: 'date'		, mapping: 'end'	, dateFormat: 'time'	},
	        {name: 'end_time'			, type: 'date'		, mapping: 'end'	, dateFormat: 'time'	},
	        
	        /*
	        {name: 'start_date'			, mapping: 'start'	, convert: function(v,r){return Ext.Date.format(new Date(parseInt(v)), 'd/m/Y')}},
	        {name: 'start_time'			, mapping: 'start'	, convert: function(v,r){return Ext.Date.format(new Date(parseInt(v)), 'H:i') }	},
	        {name: 'end_date'			, mapping: 'end'	, convert: function(v,r){return Ext.Date.format(new Date(parseInt(v)), 'd/m/Y')}},
	        {name: 'end_time'			, mapping: 'end'	, convert: function(v,r){return Ext.Date.format(new Date(parseInt(v)), 'H:i') }	}
	        */
	],
	
	
	belongsTo: [{name: 'service'		, model: 'Sam.model.ServiceOrderJob'	, foreignKey: 'service_id'			},
				{name: 'technician'		, model: 'Sam.model.Technician'			, foreignKey: 'technician_id'		}],
				
	validations: [
				{field:'service_id'		, type: 'presence'},
				{field:'technician_id'	, type: 'presence'},
				{field:'start_date'		, type: 'presence'},
				{field:'start_time'		, type: 'presence'},
				{field:'end_date'		, type: 'presence'},
				{field:'end_time'		, type: 'presence'}
	],
	
	idProperty: 'mockId'	// <-- This must exist for avoid ext to creating an 'id' property as string

});