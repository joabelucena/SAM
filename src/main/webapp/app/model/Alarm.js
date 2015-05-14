Ext.define('Sam.model.Alarm', {
	extend: 'Ext.data.Model',
	
	fields:[
	        
	        {name: 'id'				, type: 'string'									},
	        {name: 'desc'			, type: 'string'									},
	        {name: 'counterInc'		, type: 'number'									},
	        {name: 'manNorm'		, type: 'number'									},
	        
	        /** Association Keys **/
	        {name: 'alarm_id'		, type: 'string'		, mapping: 'normAlarm.id'	},
	        {name: 'group_id'		, type: 'number'		, mapping: 'group.id'		},
	        {name: 'type_id'		, type: 'number'		, mapping: 'type.id'		},
	        {name: 'model_id'		, type: 'number'		, mapping: 'model.id'		},
	        {name: 'severity_id'	, type: 'number'		, mapping: 'severity.id'	},
	        
	        /** Grid Fields **/
	        {name: 'group_desc'		, type: 'string'		, mapping: 'group.desc'		},
	        {name: 'type_desc'		, type: 'string'		, mapping: 'type.desc'		},
	        {name: 'severity_desc'	, type: 'string'		, mapping: 'severity.desc'	},
	        {name: 'model_desc'		, type: 'string'		, mapping: 'model.desc'		},
	        {name: 'alarm_desc'		, type: 'string'		, mapping: 'normAlarm.desc'	}
	        
        ],
	
    belongsTo:  [{model: 'Sam.model.Alarm'			, foreignKey: 'alarm_id'	},
                 {model: 'Sam.model.AlarmGroup'		, foreignKey: 'group_id'	},
                 {model: 'Sam.model.AlarmType'		, foreignKey: 'type_id'		},
                 {model: 'Sam.model.EquipmentModel'	, foreignKey: 'model_id'	},
                 {model: 'Sam.model.SeverityLevel'	, foreignKey: 'severity_id'	}]

});