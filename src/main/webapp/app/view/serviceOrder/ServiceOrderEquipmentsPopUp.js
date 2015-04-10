var EquipmentsGrid = Ext.create('Ext.grid.Panel', {
	
	store: Ext.create('Sam.store.Equipment'),
	
	requires: ['Ext.grid.filters.Filters'],
	
	plugins: 'gridfilters',
	
	id: 'equipmentspopupgridpanel',
		
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
	}]
		
});

Ext.define('Sam.view.serviceOrder.ServiceOrderEquipmentsPopUp', {
	extend : 'Ext.window.Window',
	alias : 'widget.soequipmentspopup',

	title : 'Selecionar Equipamento',

	header : {
		titlePosition : 2,
	},

	closable : true,
	closeAction : 'hide',
	maximizable : true,
	width : '90%',
	minWidth : 350,
	height : '90%',

	layout : 'fit',
	
	items : [ EquipmentsGrid ],
	
	buttons : [ {
		id: 'selectEquipmentNewSO',
		text : 'Confirma',
		tooltip:'Seleciona Equipamento',
        cls:'x-btn-default-small',
        iconCls: 'tick-button'
	} ],
	
	modal: true

});