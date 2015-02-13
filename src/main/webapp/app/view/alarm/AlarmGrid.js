Ext.define('Sam.view.alarm.AlarmGrid' ,{
	extend: 'Ext.grid.Panel',
	alias: 'widget.alarmgrid',
	
	id: 'alarmgrid',

	store: Ext.create('Sam.store.Alarms'),
	
	viewConfig: {
		stripeRows: true	
	},
	
	viewConfig: {
        //Return CSS class to apply to rows depending upon data values
        getRowClass: function(record, index) {
            var c = record.get('severity_id');
            if (c == '3') {
                return 'severity3';
            } else if (c == 2) {
            	return 'severity2';
            }
        }
    },
    
    listeners : {
        itemdblclick: function(dv, record, item, index, e) {
        	var alarmPopUp = Ext.create('Sam.view.alarm.AlarmPopUp');
        	
        	alarmPopUp.title = record.get('equipment_model') + " - " + record.get('site_description');
        	
        	alarmPopUp.setData({
        	    equipment : record.get('equipment_model')
        	});
        	
        	alarmPopUp.show();
        }
    },
	
	columns : [
		{
			text: '#',
			flex: 1,
			sortable: true,
			dataIndex: 'id',
			
			filter: {
				type: 'number'
			}
		},{
			text: 'Severidade',
			flex: 1,
			sortable:true,
			dataIndex: 'severity',
			filter: {
				type: 'string'
			}
		},{
			text: 'Data/Hora',
			flex: 1,
			sortable: true,
			dataIndex: 'alarm_datetime',
			filter: {
				type: 'date'
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
			text: 'Sistema',
			flex: 1,
			sortable: true,
			dataIndex: 'sub_system_id',
			filter: {
				type: 'string'
			}
		}]
});