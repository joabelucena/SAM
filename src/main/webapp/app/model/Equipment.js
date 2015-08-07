Ext.define('Sam.model.Equipment', {
	extend: 'Ext.data.Model',

	fields:	[

		{name: 'id'					,type: 'string'	},
		{name: 'fixed_asset'		,type: 'string'		,mapping: 'fixedAsset'},
		{name: 'service_tag'		,type: 'string'		,mapping: 'serviceTag'},
		{name: 'ip'					,type: 'string'	},
		{name: 'warranty'			,type: 'number'	},
		{name: 'oid'				,type: 'string'	},
		{name: 'mtbf_prev'			,type: 'number'		,mapping: 'mtbfPrev'},
		{name: 'mtbf_calc'			,type: 'number'		,mapping: 'mtbfCalc'},
		{name: 'mtbf_manf'			,type: 'number'		,mapping: 'mtbfManf'},
		{name: 'install_date'		,type: 'date'		,mapping: 'installDate'},
		{name: 'manufacture_date'	,type: 'date'		,mapping: 'manufactureDate'},
		{name: 'acquired_date'		,type: 'date'		,mapping: 'acquiredDate'},
		{name: 'remark'				,type: 'string'	},

		//Equipment Type Aux Fields
		{name: 'type_id'			,type: 'number', mapping: 'type.id'		},
		{name: 'type_desc'			,type: 'string', mapping: 'type.desc'	},
		
		//Equipment Model Aux Fields
		{name: 'model_id'			,type: 'number', mapping: 'model.id'	},
		{name: 'model_desc'			,type: 'string', mapping: 'model.desc'	},
		
		//Equipment Manufacturer Aux Fields
		{name: 'manufacturer_id'	,type: 'number', mapping: 'manufacturer.id'		},		
		{name: 'manufacturer_desc'	,type: 'string', mapping: 'manufacturer.desc'	},
		
		//Equipment Site Aux Fields
		{name: 'site_id'			,type: 'number', mapping: 'site.id'		},
		{name: 'site_desc'			,type: 'string', mapping: 'site.desc'	},
		
		//Equipment System Aux Fields
		{name: 'system_id'			,type: 'number', mapping: 'system.id'	},		
		{name: 'system_desc'		,type: 'string', mapping: 'system.desc'	},
		
		//Equipment Document Aux Fields
		{name: 'document_id'			,type: 'number', mapping: 'documents.id'  },		
		{name: 'document_desc'			,type: 'string', mapping: 'documents.desc'},
		{name: 'document_url'			,type: 'string', mapping: 'documents.url' }
		
	],

	belongsTo: [

		{model: 'Sam.model.EquipmentType'			,foreignKey: 'type_id'	},
		{model: 'Sam.model.EquipmentModel'			,foreignKey: 'model_id'	},
		{model: 'Sam.model.EquipmentManufacturer' 	,foreignKey: 'manufacturer_id' },
		{model: 'Sam.model.Site' 					,foreignKey: 'site_id'	},
		{model: 'Sam.model.SubSystem' 				,foreignKey: 'system_id'}

	]

});