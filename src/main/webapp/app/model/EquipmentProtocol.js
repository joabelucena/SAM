Ext.define('Sam.model.EquipmentProtocol', {
	extend: 'Ext.data.Model',
	
	fields:[
	        
	        {name: 'id'								, type: 'string'	, mapping: 'epr_id'					},
	        {name: 'equipment_protocol_desc'		, type: 'string'	, mapping: 'epr_description'		},

	       ]
});