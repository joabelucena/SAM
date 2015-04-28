Ext.define('Sam.view.equipment.type.TypeGrid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.equipmenttypegrid',

	requires : ['Ext.grid.filters.Filters'],
	           
	store : Ext.create('Sam.store.EquipmentType'),

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