Ext.define('Sam.view.technician.TechnicianGrid', {
	extend : 'Ext.grid.Panel',
	
	alias : 'widget.techniciangrid',

	requires : ['Ext.grid.filters.Filters'],
	           
	store : Ext.create('Sam.store.Technician'),

	columns : [ {
		text : 'Codigo',
		dataIndex : 'id',
		flex : 1,
		sortable: true,
		filter : {
			type : 'string'
		}
	}, {
		text : 'Nome',
		flex : 1,
		sortable : true,
		dataIndex : 'name',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Local de Trabalho',
		flex : 1,
		sortable : true,
		dataIndex : 'site_desc',
		filter : {
			type : 'string'
		}
	}]
});