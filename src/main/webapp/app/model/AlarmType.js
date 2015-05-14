Ext.define('Sam.model.AlarmType', {
	extend: 'Ext.data.Model',
	
	fields:['id', 'desc'],
	
	hasMany: {model: 'Sam.model.Alarm', foreignKey: 'alarm_id'}

});