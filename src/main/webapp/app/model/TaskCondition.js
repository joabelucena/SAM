Ext.define('Sam.model.TaskCondition', {
	extend: 'Ext.data.Model',
	
	fields:[
	        
	        {name: 'id'				, type: 'number'	, defaultValue: 0		},
	        {name: 'seq'														},
	        {name: 'logicOper'		, type: 'string'							},
	        {name: 'type'			, type: 'string'							},
	        {name: 'field'			, type: 'string'							},
	        {name: 'relOper'		, type: 'string'							},
	        {name: 'value'														},
	        {name: 'task_id'		, type: 'number'							}
	        ],

	        idProperty: 'mockId',	// <-- This must exist for avoid ext to creating an 'id' property as string
	        belongsTo:  [{name: 'task', model: 'Sam.model.Task'		, foreignKey: 'task_id'	}],
	        
});