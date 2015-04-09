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
	        {name: 'system'				, type: 'date'	, dateFormat: 'c'},
	        {name: 'verifyyyyy'			, type: 'date'	, dateFormat: 'c'},
	        {name: 'warranty'			, type: 'date'	, dateFormat: 'c'},
	        {name: 'counter_qt'			, type: 'date'	, dateFormat: 'c'},
	        {name: 'oid'				, type: 'string'},
	        {name: 'mtbf_prev'			, type: 'string'},
	        {name: 'mtbf_calc'			, type: 'string'},
	        {name: 'mtbf_manf'			, type: 'string'},
	        {name: 'install_date'		, type: 'string'},
	        {name: 'manufacture_date'	, type: 'string'},
	        {name: 'acquired_date'		, type: 'string'},
	        {name: 'remark'				, type: 'string'}
	]
});