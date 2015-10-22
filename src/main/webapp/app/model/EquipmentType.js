Ext.define('Sam.model.EquipmentType', {
	extend: 'Ext.data.Model',
	
	fields:[
	        {name: 'id'			, type: 'number'									},
	        {name: 'desc'		, type: 'string'									},
	        
	        /** Form Aux Mappings **/
			{name: 'type_id'	, type: 'number'	, mapping: 'id'					},
			{name: 'type_desc'	, type: 'string'	, mapping: 'desc'				}
			
			]

});