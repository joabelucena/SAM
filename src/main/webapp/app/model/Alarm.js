/**
 * 
 */

Ext.define('Sam.model.Alarm', {
	extend: 'Ext.data.Model',
	fields:[
	        
	        {name: 'id'},
	        {name: 'severity'},
	        {name: 'severity_color'},
	        {name: 'equipment_id'},
	        {name: 'equipment_model'},
	        {name: 'alarm_id'},
	        {name: 'alarm_description'},
	        {name: 'site_description'},
	        {name: 'sub_system_id'},
	        {name: 'sub_system_description'},
	        {name: 'alarm_datetime'}
	]
	
});