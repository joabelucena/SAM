Ext.define('Sam.view.equipments.EquipmentsGrid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.equipmentsgrid',

	itemId: 'equipmentsGrid',
	
	requires : [ 'Ext.grid.column.Check', 
	             'Ext.grid.filters.Filters'
	           ],
	           
	plugins: 'gridfilters',

	store : Ext.create('Sam.store.Equipment'),

	columns : [ {
		text : 'Id',
		dataIndex : 'id',
		flex : 1,
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