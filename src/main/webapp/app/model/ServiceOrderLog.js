Ext.define('Sam.model.ServiceOrderLog', {
	extend: 'Ext.data.Model',
	fields:[
	        {name: 'id'					, type: 'number'							},
	        {name: 'serviceorder_id'	, type: 'number'							},
	        
	        {name: 'user_id'			, type: 'string'	, mapping: 'userId'		},
	        {name: 'datetime'			, type: 'date'		, dateFormat: 'time'	},
	        {name: 'remark'				, type: 'string'							},
	        
	        /** Association Keys **/
	        {name: 'so_id'				, type: 'number'									},
	        
	        /** Association Keys **/
	        {name: 'prevstatus_id'		, type: 'number'	, mapping: 'prevstatus.desc'	},
	        {name: 'curstatus_id'		, type: 'number'	, mapping: 'curstatus.desc'		},
	        
	        /** Grid Fields **/
	        {name: 'prevstatus_desc'	, type: 'string'	, mapping: 'prevstatus.desc'	},
	        {name: 'curstatus_desc'		, type: 'string'	, mapping: 'curstatus.desc'		},
	        
	        ],
	        
	        
	        belongsTo:  [
	                     {name: 'prevstatus', model: 'Sam.model.ServiceOrderStatus'		, foreignKey: 'prevstatus_id'	},
	                     {name: 'curstatus'	, model: 'Sam.model.ServiceOrderStatus'		, foreignKey: 'curstatus_id'	},
	                     ],
});
