Ext.define('Sam.model.EquipmentProtocol', {
	extend: 'Ext.data.Model',
	
	idProperty: 'id',
	
	fields:[
	        {name: 'id'			, type: 'number'},
	        {name: 'desc'		, type: 'string'}
	       ],
	       
	hasMany: {model: 'Sam.model.EquipmentModel', foreignKey: 'prot_id'}
});