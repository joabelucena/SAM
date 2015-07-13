Ext.define('Sam.model.ServiceOrderLog', {
	extend: 'Ext.data.Model',
	fields:[
	        {name: 'id'					, type: 'number'							},
	        {name: 'serviceorder_id'	, type: 'number'							},
	        {name: 'prevstatus'			, type: 'string', mapping: 'prevstatus.desc'},
	        {name: 'curstatus'			, type: 'string', mapping: 'curstatus.desc'	},
	        {name: 'user_id'			, type: 'string', mapping: 'userId'			},
	        {name: 'datetime'			, type: 'date'	, dateFormat: 'time'		},
	        {name: 'remark'				, type: 'string'							}
	]
});
