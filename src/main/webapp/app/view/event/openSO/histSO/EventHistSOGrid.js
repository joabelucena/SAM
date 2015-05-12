Ext.define('Sam.view.event.openSO.histSO.EventHistSOGrid' , {
	extend: 'Ext.grid.Panel',
	alias: 'widget.eventhistsogrid',
	
	requires: ['Ext.grid.filters.Filters'],
	
	id: 'eventhistsogrid',
	
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
			text: 'Inicio',
			flex: 1,
			sortable: true,
			dataIndex: 'start',
			renderer: Ext.util.Format.dateRenderer('d/m/Y - G:i:s'),
			filter: {
				type: 'date'
			}
		},{
			text: 'Termino',
			flex: 1,
			sortable: true,
			dataIndex: 'end',
			renderer: Ext.util.Format.dateRenderer('d/m/Y - G:i:s'),
			filter: {
				type: 'date'
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