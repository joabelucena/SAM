Ext.define('Sam.view.alarm.AlarmPanel', {
	extend: 'Ext.form.Panel',
	alias:  'widget.alarmpanel',
	
	frame: true,
	title: 'Alarmes',
	bodyPadding: 5,
	layout: 'column',
	
	fieldDefaults: {
		labelAlign: 'left',
		msgTarget: 'side'
	}
});