Ext.define('Sam.model.Equipment', {
	extend: 'Ext.data.Model',
	fields:[
	        {name: 'id'					, type: 'string'},
	        {name: 'fixed_asset'		, type: 'string'},
	        {name: 'service_tag'		, type: 'string'},
	        {name: 'ip'					, type: 'string'},
	        {name: 'type'				, type: 'string'},
	        {name: 'model'				, type: 'string'},
	        {name: 'manufacturer'		, type: 'string'},
	        {name: 'site'				, type: 'string'},
	        {name: 'counter_type'		, type: 'string'},
	        {name: 'system'				, type: 'string'},
	        {name: 'warranty'			, type: 'number'},
	        {name: 'counter_qt'			, type: 'number'},
	        {name: 'oid'				, type: 'string'},
	        {name: 'mtbf_prev'			, type: 'number'},
	        {name: 'mtbf_calc'			, type: 'number'},
	        {name: 'mtbf_manf'			, type: 'number'},
	        {name: 'install_date'		, type: 'date'	, dateFormat: 'time'},
	        {name: 'manufacture_date'	, type: 'date'	, dateFormat: 'time'},
	        {name: 'acquired_date'		, type: 'date'	, dateFormat: 'time'},
	        {name: 'remark'				, type: 'string'}
	]
});