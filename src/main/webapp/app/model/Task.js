Ext.define('Sam.model.Task', {
	extend: 'Ext.data.Model',
	
	fields:[
	        
	        {name: 'id'				, type: 'string'								},
	        {name: 'desc'			, type: 'string'								},
	        {name: 'active'			, type: 'string'								},
	        {name: 'equipments'														},
	        {name: 'items'															},
	        
	        /** Association Keys **/
	        {name: 'alarm_id'		, type: 'string'	, mapping: 'alarm.id'		},
	        
	        
	        /** Grid Fields **/
	        {name: 'alarm_desc'		, type: 'string'	, mapping: 'alarm.desc'		}
	        
        ],
	
    belongsTo:  [{model: 'Sam.model.Alarm'			, foreignKey: 'alarm_id'	}]

});