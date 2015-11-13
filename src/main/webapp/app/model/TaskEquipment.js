Ext.define('Sam.model.TaskEquipment', {
	extend: 'Ext.data.Model',
	
	fields:[
	        
	        {name: 'id'					, type: 'number'								},
	        {name: 'proccess'			, type: 'date'		, dateFormat: 'time'		},
	        
	        /** Association Keys **/
	        {name: 'equipment_id'		, type: 'string'	, mapping: 'equipment.id'	},
	        {name: 'task_id'			, type: 'number'								},
	        
	        /** Grid Fields **/
	        {name: 'model_desc'			, type: 'string'	, mapping: 'equipment.model.desc'		},
	        {name: 'type_desc'			, type: 'string'	, mapping: 'equipment.type.desc'		},
	        {name: 'manufacturer_desc'	, type: 'string'	, mapping: 'equipment.manufacturer.desc'},
	        {name: 'site_desc'			, type: 'string'	, mapping: 'equipment.site.desc'		},
	        
        ],
        
        idProperty: 'mockId',	// <-- This must exist for avoid ext to creating an 'id' property as string
        
        belongsTo:  [{name: 'equipment'	, model: 'Sam.model.Equipment'	, foreignKey: 'equipment_id'},
                     {name: 'task'		, model: 'Sam.model.Task'		, foreignKey: 'task_id'		}
        ],
        
        validations: [
      				{field:'proccess'		, type: 'presence'}
      	]
});