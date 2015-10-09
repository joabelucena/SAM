Ext.define('Sam.model.Document', {
	extend: 'Ext.data.Model',
	fields:[
	        
	        {name: 'id'				, type: 'number'	, defaultValue: 0		},
	        {name: 'desc'														},
	        {name: 'url'			, type: 'string'							},
	        {name: 'model_id'		, type: 'number'							},
	        ],    

//	        idProperty: 'mockId',	// <-- This must exist for avoid ext to creating an 'id' property as string
//	        belongsTo:  [{name: 'task', model: 'Sam.model.Document'		, foreignKey: 'task_id'	}],
	        
});