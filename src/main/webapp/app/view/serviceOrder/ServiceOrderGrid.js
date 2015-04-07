Ext.define('Sam.view.serviceOrder.ServiceOrderGrid' , {
	extend: 'Ext.grid.Panel',
	alias: 'widget.serviceordergrid',
	
	requires: ['Ext.grid.filters.Filters'],
		
	id: 'serviceordergridpanel',
	
	store: Ext.create('Sam.store.ServiceOrder'),
	
	plugins: 'gridfilters',
    
	columns : [
	   {
		   text: 'Ordem de Servico',
		   dataIndex: 'id',
		   flex: 1,
       },{
			text: 'Equipamento',
			flex: 1,
			sortable: true,
			dataIndex: 'equipment_id',
			filter: {
				type: 'string'
			}
		},{
			text: 'Tipo',
			flex: 1,
			sortable: true,
			dataIndex: 'type',
			filter: {
				type: 'string'
			}
		},{
			text: 'Status',
			flex: 1,
			sortable: true,
			dataIndex: 'status',
			filter: {
				type: 'string'
			}
		},{
			text: 'Alarme',
			flex: 1,
			sortable: true,
			dataIndex: 'event_id',
			filter: {
				type: 'number'
			}
		}]
});