Ext.define('Sam.view.serviceOrder.ServiceOrderPanel', {
	extend: 'Ext.TabPanel',
	alias:  'widget.serviceorderpanel',
	
	requires: ['Sam.view.serviceOrder.ServiceOrderGrid'],
	
	closable: true,
	
	layout:{
		type: 'fit',
	}
	
});