Ext.define('Sam.model.ServiceStation', {
	extend: 'Ext.data.Model',
	
	fields:[

	        {name: 'id'			, type: 'number'	},
	        {name: 'desc'		, type: 'string'	}

	       ],
	       
	hasMany: {model: 'Sam.model.Site', foreignKey: 'station_id'}

});