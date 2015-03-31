Ext.define('Sam.view.alarm.AlarmPanel', {
	extend: 'Ext.TabPanel',
	alias:  'widget.alarmpanel',
	
	requires: ['Sam.view.alarm.AlarmGrid'],
	
	closable: true,
	
	layout:{
		type: 'fit',
	}
	
});