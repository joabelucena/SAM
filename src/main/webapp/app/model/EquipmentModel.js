Ext.define('Sam.model.EquipmentModel', {
	extend: 'Ext.data.Model',
	
	requires:['Sam.model.EquipmentProtocol'],
	
	idProperty: 'id',
	
	fields:[
	        
	        {name: 'id'				, type: 'number'									},
	        {name: 'desc'			, type: 'string'									},
	        {name: 'prot_id'		, type: 'number'		, mapping: 'protocol.id'	},
	        {name: 'prot_desc'		, type: 'string'		, mapping: 'protocol.desc'	},

	        ],
	
    belongsTo: [{
            name:'protocol',
            instanceName:'protocol',
            model: 'Sam.model.EquipmentProtocol',
            getterName:'getProtocol',
            setterName:'setProtocol',
            associationKey:'protocol'
        }]
});