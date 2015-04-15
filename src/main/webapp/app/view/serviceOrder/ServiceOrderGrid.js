Ext.define('Sam.view.serviceOrder.ServiceOrderGrid' , {
	extend: 'Ext.grid.Panel',
	alias: 'widget.serviceordergrid',
	
	requires: ['Ext.grid.filters.Filters'],
		
	store: Ext.create('Sam.store.ServiceOrder'),

	plugins: 'gridfilters',
	
	scrollable: true,

	columns : [
	   {
		   text: 'Ordem de Servico',
		   dataIndex: 'id',
		   flex: 1,
		   minWidth: 200,
		   width: 200
       },
       {
			text: 'Equipamento',
			flex: 1,
			sortable: true,
			dataIndex: 'equipment_id',
			minWidth: 200,
		   width: 200,
			filter: {
				type: 'string'
			}
		},{
			text: 'Tipo',
			flex: 1,
			sortable: true,
			dataIndex: 'type',
			minWidth: 200,
			   width: 200,
			filter: {
				type: 'string'
			}
		},{
			text: 'Status',
			flex: 1,
			sortable: true,
			dataIndex: 'status',
			minWidth: 200,
			   width: 200,
			filter: {
				type: 'string'
			}
		},{
			text: 'Alarme',
			flex: 1,
			sortable: true,
			dataIndex: 'event_id',
			minWidth: 200,
			   width: 200,
			filter: {
				type: 'number'
			}
		},{
			text: 'Previsão de Inicio',
			flex: 1,
			sortable: true,
			dataIndex: 'start_forecast',
			renderer: Ext.util.Format.dateRenderer('d/m/Y - G:i:s'),
			minWidth: 200,
			   width: 200,
			filter: {
				type: 'date'
			}
		},{
			text: 'Inicio',
			flex: 1,
			sortable: true,
			dataIndex: 'start',
			renderer: Ext.util.Format.dateRenderer('d/m/Y - G:i:s'),
			minWidth: 200,
			   width: 200,
			filter: {
				type: 'date'
			}
		},{
			text: 'Previsão de Conclusao',
			flex: 1,
			sortable: true,
			dataIndex: 'end_forecast',
			renderer: Ext.util.Format.dateRenderer('d/m/Y - G:i:s'),
			minWidth: 200,
			   width: 200,
			filter: {
				type: 'date'
			}
		},{
			text: 'Conclusao Real',
			flex: 1,
			sortable: true,
			dataIndex: 'end',
			renderer: Ext.util.Format.dateRenderer('d/m/Y - G:i:s'),
			minWidth: 200,
			width: 200,
			filter: {
				type: 'date'
			}
		}],
		dockedItems: [{
		    xtype: 'toolbar',
		    dock: 'bottom',
		    
		    items: [{
		        xtype:'button',
		    	id:'btnNewSo',
		    	text:'Nova OS',
		        tooltip:'Abrir Nova Ordem de Serviço',
		        cls:'x-btn-default-small',
		        iconCls: 'tick-button'
		    },{
		        xtype:'button',
		    	id:'btnShowSo',
		    	text:'Visualizar',
		        tooltip:'Visuzalizar Ordem de Serviço',
		        cls:'x-btn-default-small',
		        iconCls: 'tick-button'
		    }]
		}]
});