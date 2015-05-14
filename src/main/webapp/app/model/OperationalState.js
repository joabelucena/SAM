Ext.define('Sam.model.OperationalState', {
	extend: 'Ext.data.Model',
	
	idProperty: 'id',
	
	fields:[
	        
	        {name: 'id'				, type: 'string'							},
	        {name: 'desc'			, type: 'string'							},
	        {name: 'model_id'		, type: 'number'	, mapping: 'model.id'	},
	        {name: 'model_desc'		, type: 'string'	, mapping: 'model.desc'	}

	       ],

	belongsTo:  {model: 'Sam.model.EquipmentModel', foreignKey: 'model_id'}

});