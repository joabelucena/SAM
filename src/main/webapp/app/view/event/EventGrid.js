Ext.define('Sam.view.event.EventGrid' , {
	extend: 'Sam.lib.AutoGrid',
	alias: 'widget.eventgrid',
	
	requires: [
	           'Ext.grid.column.Check',
	           'Ext.grid.filters.Filters'
	],
	
	itemId: 'eventgrid',
	
	refreshRate: 5,
	
	scrollable: true,

	store: Ext.create('Sam.store.Events'),
	
	viewConfig: {		
		preserveScrollOnRefresh: true,

        getRowClass: function(record, index) {
            var s = record.get('severity_id');
            var r = record.get('reco');
            
            if (s == '3' && r == false) {
            
            	return 'severity3';
            
            } else if (s == '2' && r == false) {
            
            	return 'severity2';
            
            } else if (s == '3' && r == true) {
            	
            	return 'knowledged3';
            	
            } else if ( s == '2' && r == true) {
            	
            	return 'knowledged2';
            }
        }
    },
    
    plugins: 'gridfilters',
    
	columns : [
	   {
		   xtype: 'checkcolumn',
		   text: 'Reconhece',
		   maxWidth: 70,
		   minWidth: 70,
		   dataIndex: 'reco',
	   },{
		   text: 'Código',
		   dataIndex: 'id',
		   width: 100
       },{
			text: 'Data/Hora',
			width: 130,
			sortable: true,
			dataIndex: 'datetime',
			renderer: Ext.util.Format.dateRenderer('d/m/Y - G:i:s'),
			filter: {
				type: 'date'
			}
		},{
		text: 'Alarme',
		width: 170,
		sortable: true,
		dataIndex: 'alarm_desc',
		filter: {
			type: 'string'
		}
	   },{
			text: 'Equipamento',
			width: 160,
			sortable: true,
			dataIndex: 'equipment_id',
			filter: {
				type: 'string'
			}
		},{
			text: 'Modelo Equipamento',
			width: 130,
			sortable: true,
			dataIndex: 'equipment_model',
			filter: {
				type: 'string'
			}
		},{
			text: 'Local',
			flex: 1,
			sortable: true,
			dataIndex: 'site_desc',
			filter:{
				type: 'string'
			}
		},{
			text: 'Sistema',
			width: 75,
			sortable: true,
			dataIndex: 'system_id',
			filter: {
				type: 'string'
			}
		},{
			text: 'Severidade',
			width: 80,
			sortable: true,
			dataIndex: 'severity_desc',
			filter: {
				type: 'string'
			}
		},{
			text: 'Ord. Serviço',
			width: 80,
			sortable: true,
			dataIndex: 'so_id',
			filter: {
				type: 'string'
			}
		},{
			xtype: 'actioncolumn',
			width: 100,
			text: 'Ações',
			align: 'center',
			sortable: false,
			//1 - Abrir OS
			//2 - Normalizar
			//3 - Visualizar Alarme
			items: [{
					iconCls: 'notebook-plus-icon',
					tooltip: 'Abrir O.S.',
					handler: function(view, rowIndex, colIndex, item, e, record, row) {
						 this.fireEvent('create', view, rowIndex, colIndex, item, e, record, row, 1);
					}
			},{
					xtype: 'tbfill'
			},{
    				iconCls: 'tick-shield',
    				tooltip: 'Normalizar',
    				handler: function(view, rowIndex, colIndex, item, e, record, row) {
						 this.fireEvent('norm', view, rowIndex, colIndex, item, e, record, row, 2);
					}
			},{
					xtype: 'tbfill'
			},{
				iconCls: 'magnifier-zoom',
				tooltip: 'Visualizar Alarme',
				handler: function(view, rowIndex, colIndex, item, e, record, row) {
					 this.fireEvent('show', view, rowIndex, colIndex, item, e, record, row, 3);
				}
			}]
		}],
		
		dockedItems: [{
            xtype: 'toolbar',
            dock: 'bottom',
            
            items: [{
                xtype:'button',
            	itemId:'btnRecAll',
            	text:'Reconhecer Todos',
                tooltip:'Reconhece todos os Alarmes',
                cls:'x-btn-default-small',
                iconCls: 'tick-button'
            }]
        }]
});