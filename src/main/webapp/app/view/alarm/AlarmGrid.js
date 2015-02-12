Ext.define('Sam.view.alarm.AlarmGrid' ,{
	extend: 'Ext.grid.Panel',
	alias: 'widget.alarmgrid',
	
	title: 'Alarmes',
	
	viewConfig: {
		stripeRows: true	
	},
	
	initComponent: function() {
		this.store = 'Alarms';
		
		this.columns = [
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
		}
			
		
		];
	}
});