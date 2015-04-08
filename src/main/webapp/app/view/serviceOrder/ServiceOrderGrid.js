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
		}],
		dockedItems: [{
		    xtype: 'toolbar',
		    dock: 'bottom',
		    
		    items: [{
		        xtype:'button',
		    	id:'button1',
		    	text:'Botao 1',
		        tooltip:'Dica Botao 1',
		        cls:'x-btn-default-small',
		        iconCls: 'tick-button'
		    },{
		        xtype:'button',
		    	id:'button2',
		    	text:'Botao 2',
		        tooltip:'Dica Botao 2',
		        cls:'x-btn-default-small',
		        iconCls: 'tick-button'
		    }]
		}]
});