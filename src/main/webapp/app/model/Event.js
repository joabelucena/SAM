Ext.define('Sam.model.Event', {
	extend: 'Ext.data.Model',
	fields:[
	        
	        {name: 'id', type: 'number'},
	        {name: 'knowledge_user', type: 'boolean'},
	        {name: 'severity', type: 'string'},
	        {name: 'severity_id', type: 'string'},
	        {name: 'equipment_id', type: 'string'},
	        {name: 'equipment_model', type: 'string'},
	        {name: 'event_id', type: 'string'},
	        {name: 'event_description', type: 'string'},
	        {name: 'site_description', type: 'string'},
	        {name: 'sub_system_id', type: 'string'},
	        {name: 'sub_system_description', type: 'string'},
	        {name: 'event_datetime', type: 'date',  dateFormat: 'c'}
	]
});