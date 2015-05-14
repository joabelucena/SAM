Ext.define('Sam.model.AlarmFilter', {
	extend: 'Ext.data.Model',
	
	fields:[
	        
	        {name: 'id'				, type: 'number'									},
	        
	        {name: 'equip_id'		, type: 'string'		, mapping: 'equipment.id'	},
	        {name: 'alarm_id'		, type: 'string'		, mapping: 'alarm.id'		},
        ],
	
    belongsTo:  [{model: 'Sam.model.Equipment'	, foreignKey: 'equip_id'},
                 {model: 'Sam.model.Alarm'		, foreignKey: 'alarm_id'}]

});