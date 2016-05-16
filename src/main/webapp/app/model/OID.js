Ext.define('Sam.model.OID', {
	extend: 'Ext.data.Model',
	fields:[
	        {name: 'id'			, type: 'number'	},
	        {name: 'oid'		, type: 'string'	},
	        {name: 'alarm'		, type: 'string'	},
	        {name: 'model_id'	, type: 'number'	}
	],
				
	validations: [
				{field:'oid'		, type: 'presence'},
				{field:'alarm'		, type: 'presence'}
	],
	
	idProperty: 'mockId',	// <-- This must exist for avoid ext to creating an 'id' property as string
		
	belongsTo:  [{name: 'model', model: 'Sam.model.EquipmentModel'		, foreignKey: 'model_id'	}],

});