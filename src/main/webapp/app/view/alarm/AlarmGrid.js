Ext.define('Sam.view.alarm.AlarmGrid' ,{
	extend: 'Ext.grid.Panel',
	alias: 'widget.alarmgrid',
	
	requires: ['Ext.ux.CheckColumn'],
	
	id: 'alarmgrid',

	store: Ext.create('Sam.store.Alarms'),
	
	viewConfig: {
		preserveScrollOnRefresh: true,
		
        getRowClass: function(record, index) {
            var c = record.get('severity_id');
            if (c == '3') {
                return 'severity3';
            } else if (c == '2') {
            	return 'severity2';
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
        }
    },
	
	columns : [
		{
			xtype: 'checkcolumn',
			text: 'Reconhecer',
			dataIndex: 'check',
			sortable: false,
			// TODO  Retornar Boolean do Reconhecimento e Normalização na consulta 
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