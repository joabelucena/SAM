Ext.define('Sam.view.reports.spagobi.SpagoBi', {
	extend: 'Ext.Panel',
	
	alias:  'widget.spagobi',
	
	itemId: 'spagobi',
	
	layout: 'fit',
	
	items:[{
		xtype: 'container',
		layout: 'fit',
		itemId:'frame',
		autoEl: {
			tag: 'iframe',
			src: 'report.jsp',
//			src: 'http://www.uol.com.br',
			style: 'border: 0px; width: 100%; height: 100%;'
		}
	}]
});