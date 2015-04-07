Ext.define('Sam.view.alarm.AlarmGrid' , {
	extend: 'Ext.grid.Panel',
	alias: 'widget.alarmgrid',
	
	requires: ['Ext.grid.column.Check',
	           'Ext.grid.filters.Filters'],
		
	id: 'alarmgridpanel',

	store: Ext.create('Sam.store.Alarms'),
	
	viewConfig: {		
		preserveScrollOnRefresh: true,

        getRowClass: function(record, index) {
            var s = record.get('severity_id');
            var r = record.get('knowledge_user');
            
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
    
    listeners : {
        beforeclose: function() {
        	Ext.TaskManager.stopAll();
        }
    },
	
    plugins: 'gridfilters',
    
	columns : [
	   {
		   text: 'ID',
		   dataIndex: 'id',
		   flex: 1,
       },{
		   xtype: 'checkcolumn',
		   id: 'knowledge_check',
		   text: 'Reconhece',
		   flex: 1,
		   dataIndex: 'knowledge_user',
	   },{
			text: 'Data/Hora',
			flex: 1,
			sortable: true,
			dataIndex: 'alarm_datetime',
			renderer: Ext.util.Format.dateRenderer('d/m/Y - G:i:s'),
			filter: {
				type: 'date'
			}
		},{
			text: 'ID Alarme',
			flex: 1,
			sortable: true,
			dataIndex: 'alarm_id',
			filter: {
				type: 'string'
			}
		},{
			text: 'Alarme',
			flex: 1,
			sortable: true,
			dataIndex: 'alarm_description',
			filter: {
				type: 'string'
			}
		},{
			text: 'ID Equipamento',
			flex: 1,
			sortable: true,
			dataIndex: 'equipment_id',
			filter: {
				type: 'string'
			}
		},{
			text: 'Modelo Equipamento',
			flex: 1,
			sortable: true,
			dataIndex: 'equipment_model',
			filter: {
				type: 'string'
			}
		},{
			text: 'Local',
			flex: 1,
			sortable: true,
			dataIndex: 'site_description',
			filter:{
				type: 'string'
			}
		},{
			text: 'Sistema',
			flex: 1,
			sortable: true,
			dataIndex: 'sub_system_id',
			filter: {
				type: 'string'
			}
		},{
			text: 'Severidade',
			flex: 1,
			sortable: true,
			dataIndex: 'severity',
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
			items: [{
					iconCls: 'notebook-plus-icon',
					tooltip: 'Abrir O.S.',
					handler: function(view, rowIndex, colIndex, item, e, record, row) {
						 this.fireEvent('itemClick', view, rowIndex, colIndex, item, e, record, row, 1);
					}
			},{
					xtype: 'tbfill'
			},{
    				iconCls: 'tick-shield',
    				tooltip: 'Normalizar',
    				handler: function(view, rowIndex, colIndex, item, e, record, row) {
						 this.fireEvent('itemClick', view, rowIndex, colIndex, item, e, record, row, 2);
					}
			}]
		}],
		
		dockedItems: [{
            xtype: 'toolbar',
            dock: 'bottom',
            
            items: [{
                xtype:'button',
            	id:'recognizeallbutton',
            	text:'Reconhecer Todos',
                tooltip:'Reconhece todos os Alarmes',
                cls:'x-btn-default-small',
                iconCls: 'tick-button'
            }]
        }]
});