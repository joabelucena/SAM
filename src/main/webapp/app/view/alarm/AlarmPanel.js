Ext.define('Sam.view.alarm.AlarmPanel', {
	extend: 'Ext.TabPanel',
	alias:  'widget.alarmpanel',
	
	requires: ['Sam.view.alarm.AlarmGrid'],
	
	
	layout:{
		type: 'fit',
	},
	
	items: [{
		xtype: 'alarmgrid'
	}]
});