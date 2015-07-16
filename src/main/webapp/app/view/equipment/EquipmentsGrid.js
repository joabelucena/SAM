Ext.define('Sam.view.equipment.EquipmentsGrid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.equipmentsgrid',

	requires : [ 'Ext.grid.filters.Filters'],
	           
	store : Ext.create('Sam.store.Equipment'),

	columns : [ {
		text : 'Código',
		dataIndex : 'id',
		width: 100,
		filter : {
			type : 'string'
		}
	}, {
		text : 'Modelo',
		flex : 1,
		sortable : true,
		dataIndex : 'model',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Tipo',
		flex : 1,
		sortable : true,
		dataIndex : 'type',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Fabricante',
		flex : 1,
		sortable : true,
		dataIndex : 'manufacturer',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Local',
		flex : 1,
		sortable : true,
		dataIndex : 'site',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Sub-Sistema',
		flex : 1,
		sortable : true,
		dataIndex : 'system',
		filter : {
			type : 'string'
		}
	} ]

});