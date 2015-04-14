Ext.define('Sam.view.MainViewport', {
	extend: 'Ext.container.Viewport', 
	alias: 'widget.mainviewport',

	requires: [
	           'Sam.view.ViewportHeader',
	           'Sam.view.ViewportFooter',
	           'Sam.view.ViewportMenu',
	           'Sam.view.ViewportPanel',
	           'Sam.view.alarm.AlarmGrid',
	           'Sam.view.alarm.AlarmShow'
	],
	
	layout: 'border',
		
	items: [
		{
			xtype: 'viewportmenu',
			region: 'west'
		},{
			xtype: 'viewportheader',
			region: 'north'
		},{
			xtype: 'viewportpanel',
			region: 'center'
		},{
			xtype: 'viewportfooter',
			region: 'south'
		}
	]
});