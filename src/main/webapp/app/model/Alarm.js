Ext.define('Sam.model.Alarm', {
	extend: 'Ext.data.Model',
	fields:[
	        
	        {name: 'id', type: 'number'},
	        {name: 'severity', type: 'string'},
	        {name: 'severity_color', type: 'string'},
	        {name: 'equipment_id', type: 'string'},
	        {name: 'equipment_model', type: 'string'},
	        {name: 'alarm_id', type: 'string'},
	        {name: 'alarm_description', type: 'string'},
	        {name: 'site_description', type: 'string'},
	        {name: 'sub_system_id', type: 'string'},
	        {name: 'sub_system_description', type: 'string'},
	        {name: 'alarm_datetime', type: 'date'}
	]
	
});