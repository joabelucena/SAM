Ext.define('Sam.view.equipment.counter.CounterGrid', {
	extend : 'Ext.grid.Panel',
	
	alias : 'widget.equipmentcountergrid',

	requires : ['Ext.grid.filters.Filters'],
	           
	store : Ext.create('Sam.store.EquipmentCounter'),

	columns : [ {
		text : 'Codigo',
		dataIndex : 'id',
		flex : 1,
		sortable: true,
		filter : {
			type : 'number'
		}
	}, {
		text : 'Descrição',
		flex : 1,
		sortable : true,
		dataIndex : 'desc',
		filter : {
			type : 'string'
		}
	}]
});