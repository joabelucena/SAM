Ext.define('Sam.view.serviceOrder.serviceOrderLog.ServiceOrderLogGrid' , {
	extend: 'Ext.grid.Panel',
	alias: 'widget.serviceorderloggrid',
	
	requires: ['Ext.grid.filters.Filters'],
	
	itemId: 'serviceorderloggrid',
	
	store: Ext.create('Sam.store.ServiceOrderLog'),
	
	plugins: 'gridfilters',
    
	columns : [
	   {
		   text: 'Ordem de Servico',
		   dataIndex: 'serviceorder_id',
		   flex: 1,
       },{
			text: 'Status Anterior',
			flex: 1,
			sortable: true,
			dataIndex: 'prevstatus',
			filter: {
				type: 'string'
			}
		},{
			text: 'Status Posterior',
			flex: 1,
			sortable: true,
			dataIndex: 'curstatus',
			filter: {
				type: 'string'
			}
		},{
			text: 'Usuario',
			flex: 1,
			sortable: true,
			dataIndex: 'user_id',
			filter: {
				type: 'string'
			}
		},{
			text: 'Data/Hora',
			flex: 1,
			sortable: true,
			dataIndex: 'datetime',
			renderer: Ext.util.Format.dateRenderer('d/m/Y - G:i:s'),
			filter: {
				type: 'date'
			}
		},{
			text: 'Observações',
			flex: 1,
			sortable: true,
			dataIndex: 'remarks',
		}]
});