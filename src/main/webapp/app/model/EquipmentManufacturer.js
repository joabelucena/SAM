Ext.define('Sam.model.EquipmentManufacturer', {
	extend : 'Ext.data.Model',

	fields:[
	        
	        {name: 'id'					, type: 'number'									},
	        {name: 'desc'				, type: 'string'									},
	        
	        /** Form Aux Mappings **/
			{name: 'manufacturer_id'	, type: 'number'	, mapping: 'id'					},
			{name: 'manufacturer_desc'	, type: 'string'	, mapping: 'desc'				}
			
			]
	
});