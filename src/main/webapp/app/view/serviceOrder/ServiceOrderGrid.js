Ext.define('Sam.view.serviceOrder.ServiceOrderGrid' , {
	extend: 'Ext.grid.Panel',
	alias: 'widget.serviceordergrid',
	
	requires: ['Ext.grid.filters.Filters'],
		
	store: Ext.create('Sam.store.ServiceOrder'),

	plugins: 'gridfilters',
	
	itemId: 'serviceordergrid',
	
	scrollable: true,

	columns : [
	   {
		   text: 'Código',
		   dataIndex: 'id',
		   width: 100
       },
       {
			text: 'Equipamento',
			sortable: true,
			dataIndex: 'equipment_id',
			width: 200,
			filter: {
				type: 'string'
			}
		},{
			text: 'Tipo',
			sortable: true,
			dataIndex: 'type_desc',
			width: 100,
			filter: {
				type: 'string'
			}
		},{
			text: 'Status',
			sortable: true,
			dataIndex: 'status_desc',
			width: 100,
			filter: {
				type: 'string'
			}
		},{
			text: 'Alarme',
			sortable: true,
			dataIndex: 'event_id',
			width: 100,
			filter: {
				type: 'number'
			}
		},{
			text: 'Inicio',
			sortable: true,
			dataIndex: 'start',
			renderer: Ext.util.Format.dateRenderer('d/m/Y - G:i:s'),
			width: 120,
			filter: {
				type: 'date'
			}
		},{
			text: 'Conclusao Real',
			flex: 1,
			sortable: true,
			dataIndex: 'end',
			renderer: Ext.util.Format.dateRenderer('d/m/Y - G:i:s'),
			width: 120,
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
		    	id:'btnChangeSts',
		    	text:'Mudar Estado',
		        tooltip:'Mudar Estado da Ordem de Serviço',
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