Ext.define('Sam.model.ServiceOrderLog', {
	extend: 'Ext.data.Model',
	requires: ['Sam.model.ServiceOrderLog'],
	fields:[
	        {name: 'id'					, type: 'number'},
	        {name: 'serviceorder_id'	, type: 'number'},
	        {name: 'prevstatus'			, type: 'string'},
	        {name: 'curstatus'			, type: 'string'},
	        {name: 'user_id'			, type: 'string'},
	        {name: 'datetime'			, type: 'date'	, dateFormat: 'time'},
	        {name: 'remarks'			, type: 'string'}
	]
});
