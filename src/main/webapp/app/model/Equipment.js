Ext.define('Sam.model.Equipment', {
	extend: 'Ext.data.Model',

	fields:	[

		{name: 'id'					,type: 'string'										},
		{name: 'desc'				,type: 'string'										},
		{name: 'fixedAsset'			,type: 'string'										},
		{name: 'serviceTag'			,type: 'string'										},
		{name: 'ip'					,type: 'string'										},
		{name: 'warranty'			,type: 'number'										},
		{name: 'oid'				,type: 'string'										},
		{name: 'mtbfPrev'			,type: 'number'										},
		{name: 'mtbfCalc'			,type: 'number'										},
		{name: 'mtbfManf'			,type: 'number'										},
		{name: 'installDate'		,type: 'date'			, dateFormat: 'time'		},
		{name: 'manufacture_date'	,type: 'date'		,mapping: 'manufactureDate'		},
		{name: 'acquired_date'		,type: 'date'		,mapping: 'acquiredDate'		},
		{name: 'remark'				,type: 'string'										},

		/** Equipment Type Aux Mappings **/
		{name: 'type_id'			,type: 'number'		, mapping: 'type.id'			},
		{name: 'type_desc'			,type: 'string'		, mapping: 'type.desc'			},
		
		/** Equipment Model Aux Mappings **/
		{name: 'model_id'			,type: 'number'		, mapping: 'model.id'			},
		{name: 'model_desc'			,type: 'string'		, mapping: 'model.desc'			},
		
		/** Equipment Manufacturer Aux Mappings **/
		{name: 'manufacturer_id'	,type: 'number'		, mapping: 'manufacturer.id'	},		
		{name: 'manufacturer_desc'	,type: 'string'		, mapping: 'manufacturer.desc'	},
		
		/** Equipment Site Aux Mappings **/
		{name: 'site_id'			,type: 'number'		, mapping: 'site.id'			},
		{name: 'site_desc'			,type: 'string'		, mapping: 'site.desc'			},
		
		/** Equipment System Aux Mappings  **/
		{name: 'system_id'			,type: 'string'		, mapping: 'system.id'			},		
		{name: 'system_desc'		,type: 'string'		, mapping: 'system.desc'		},
		
		/** ServiceOrder Aux Mappings **/
		{name: 'equipment_id'		, type: 'string'	, mapping: 'id'					},
		{name: 'equipment_model'	, type: 'string'	, mapping: 'model.desc'			},
        {name: 'equipment_manuf'	, type: 'string'	, mapping: 'manufacturer.desc'	},
        {name: 'equipment_system'	, type: 'string'	, mapping: 'system.desc'		},
        {name: 'equipment_site'		, type: 'string'	, mapping: 'site.desc'			},
		
	],

	belongsTo: [

		{model: 'Sam.model.EquipmentType'			,foreignKey: 'type_id'	},
		{model: 'Sam.model.EquipmentModel'			,foreignKey: 'model_id'	},
		{model: 'Sam.model.EquipmentManufacturer' 	,foreignKey: 'manufacturer_id' },
		{model: 'Sam.model.Site' 					,foreignKey: 'site_id'	},
		{model: 'Sam.model.SubSystem' 				,foreignKey: 'system_id'}

	]

});
