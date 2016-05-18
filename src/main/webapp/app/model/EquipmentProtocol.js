Ext.define('Sam.model.EquipmentProtocol', {
	extend: 'Ext.data.Model',
	
	idProperty: 'id',
	
	fields:[
	        {name: 'id'			, type: 'number'},
	        {name: 'desc'		, type: 'string'},
	        
	        /** Association Keys **/
	        {name: 'prot_id'	, type: 'number'	, mapping: 'id'	},
	        
	        /** Grid Fields **/
	        {name: 'prot_desc'	, type: 'string'	, mapping: 'desc'	},
	        ],
	       
	        belongsTo:  [{model: 'Sam.model.EquipmentModel'		, foreignKey: 'prot_id'	}],
});