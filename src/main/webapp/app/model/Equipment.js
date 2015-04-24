Ext.define('Sam.model.Equipment', {
	extend: 'Ext.data.Model',

	fields:[

	        {name: 'id'					, type: 'string'	, mapping: 'equ_id'							},
			{name: 'fixed_asset'		, type: 'string'	, mapping: 'equ_fixed_asset'				},
			{name: 'service_tag'		, type: 'string'	, mapping: 'equ_service_tag'				},
			{name: 'ip'					, type: 'string'	, mapping: 'equ_ip'							},
			{name: 'type'				, type: 'string'	, mapping: 'type.ety_description'			},
	        {name: 'model'				, type: 'string'	, mapping: 'model.emo_description'			},
	        {name: 'manufacturer'		, type: 'string'	, mapping: 'manufacturer.ema_description'	},
	        {name: 'site'				, type: 'string'	, mapping: 'site.sit_description'			},
	        {name: 'counter_type'		, type: 'string'	, mapping: 'counter.cty_description'		},
	        {name: 'system'				, type: 'string'	, mapping: 'system.ssy_description'			},
			{name: 'warranty'			, type: 'number'	, mapping: 'equ_warranty'					},
			{name: 'counter_qt'			, type: 'number'	, mapping: 'equ_counter_qt'					},
			{name: 'oid'				, type: 'string'	, mapping: 'equ_oid'						},
			{name: 'mtbf_prev'			, type: 'number'	, mapping: 'equ_mtbf_prev'					},
			{name: 'mtbf_calc'			, type: 'number'	, mapping: 'equ_mtbf_calc'					},
			{name: 'mtbf_manf'			, type: 'number'	, mapping: 'equ_mtbf_manf'					},
			{name: 'install_date'		, type: 'date'		, mapping: 'equ_install_date'		, dateFormat: 'time'},
			{name: 'manufacture_date'	, type: 'date'		, mapping: 'equ_manufacture_date'	, dateFormat: 'time'},
			{name: 'acquired_date'		, type: 'date'		, mapping: 'equ_acquired_date'		, dateFormat: 'time'},
			{name: 'remark'				, type: 'string'	, mapping: 'equ_remark'						}
	]
});