Ext.define('Sam.view.alarm.AlarmGrid' ,{
	extend: 'Ext.grid.Panel',
	alias: 'widget.alarmgrid',
	
	requires: ['Ext.grid.column.Check'],
		
	id: 'alarmgridpanel',
	
	store: Ext.create('Sam.store.Alarms'),
	
	viewConfig: {
		preserveScrollOnRefresh: true,

        getRowClass: function(record, index) {
            var s = record.get('severity_id');
            var r = record.get('knowledge_user')
            
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
      	
    	itemdblclick: function(dv, record, item, index, e) {
        	var alarmPopUp = Ext.create('Sam.view.alarm.AlarmPopUp');
        	
        	alarmPopUp.title = record.get('equipment_model') + " - " + record.get('sub_system_description');
        	
        	alarmPopUp.setData({
        	    event_id : record.get('id')
        	});
        	
        	alarmPopUp.show();
        },
        
        beforeclose: function() {
        	Ext.TaskManager.stopAll();
        }
    },
	
	columns : [
	   {
		   text: 'id',
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
			sortable:true,
			dataIndex: 'severity',
			filter: {
				type: 'string'
			}
		}]
});