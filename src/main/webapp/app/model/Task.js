Ext.define('Sam.model.Task', {
	extend: 'Ext.data.Model',
	
	fields:[
	        
	        {name: 'id'				, type: 'number'		, defaultValue: 0		},
	        {name: 'desc'			, type: 'string'								},
	        {name: 'active'			, type: 'string'								},
	        {name: 'equipments'														},
	        {name: 'items'															},
	        
	        /** Association Keys **/
	        {name: 'alarm_id'		, type: 'string'	, mapping: 'alarm.id'		},
	        
	        /** Grid Fields **/
	        {name: 'alarm_desc'		, type: 'string'	, mapping: 'alarm.desc'		}
	        
        ],
        
        idProperty: 'mockId',	// <-- This must exist for avoid ext to creating an 'id' property as string
	
        belongsTo:  [{name: 'alarm', model: 'Sam.model.Alarm'	, foreignKey: 'alarm_id'	}],
    
        hasMany: [{name: 'conditions', model: 'Sam.model.TaskCondition'	, foreignKey: 'task_id'},
                  {name: 'equipments', model: 'Sam.model.Equipment'		}]

});