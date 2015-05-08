Ext.define('Sam.model.Equipment', {
	extend: 'Ext.data.Model',

	fields:[

	        {name: 'id'					, type: 'string'														},
			{name: 'fixed_asset'		, type: 'string'	, mapping: 'fixedAsset'								},
			{name: 'service_tag'		, type: 'string'	, mapping: 'serviceTag'								},
			{name: 'ip'					, type: 'string'														},
			{name: 'type'				, type: 'string'	, mapping: 'type.desc'								},
	        {name: 'model'				, type: 'string'	, mapping: 'model.desc'								},
	        {name: 'manufacturer'		, type: 'string'	, mapping: 'manufacturer.desc'						},
	        {name: 'site'				, type: 'string'	, mapping: 'site.desc'								},
	        {name: 'counter_type'		, type: 'string'	, mapping: 'counter.desc'							},
	        {name: 'system'				, type: 'string'	, mapping: 'system.desc'							},
			{name: 'warranty'			, type: 'number'														},
			{name: 'counter_qt'			, type: 'number'	, mapping: 'counterQt'								},
			{name: 'oid'				, type: 'string'														},
			{name: 'mtbf_prev'			, type: 'number'	, mapping: 'mtbfPrev'								},
			{name: 'mtbf_calc'			, type: 'number'	, mapping: 'mtbfCalc'								},
			{name: 'mtbf_manf'			, type: 'number'	, mapping: 'mtbfManf'								},
			{name: 'install_date'		, type: 'date'		, mapping: 'installDate'		, dateFormat: 'time'},
			{name: 'manufacture_date'	, type: 'date'		, mapping: 'manufactureDate'	, dateFormat: 'time'},
			{name: 'acquired_date'		, type: 'date'		, mapping: 'acquiredDate'		, dateFormat: 'time'},
			{name: 'remark'				, type: 'string'	, mapping: 'remark'									}
	]
});