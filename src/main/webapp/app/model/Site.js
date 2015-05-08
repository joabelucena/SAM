Ext.define('Sam.model.Site', {
	extend: 'Ext.data.Model',
	
	fields:[
	        
	        {name: 'id'				, type: 'number'	},
	        {name: 'desc'			, type: 'string'	},
	        {name: 'shortname'		, type: 'string'	},
	        
	        //Station Aux Fields
	        {name: 'station_id'		, type: 'number'	, mapping: 'station.id'		},
	        {name: 'station_desc'	, type: 'string'	, mapping: 'station.desc'	},
	        
	        //Type Aux Fields
	        {name: 'type_id'		, type: 'number'	, mapping: 'type.id'		},
	        {name: 'type_desc'		, type: 'string'	, mapping: 'type.desc'		},			

	       ],
	       
	       /*
	        * Comentado por Joabe. Implementar operacao vice-versa com hasMany nas classes abaixo.
	       belongsTo:  [{model: 'Sam.model.SiteType', foreignKey: 'type_id'},
	                    {model: 'Sam.model.ServiceStation', foreignKey: 'station_id'}]
	                    
	                    */
});