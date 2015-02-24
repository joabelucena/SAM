Ext.define('Sam.view.ViewportHeader', {
	extend: 'Ext.toolbar.Toolbar',
	alias: 'widget.viewportheader',
	
	height: 60,
	ui: 'footer',
	items: [
		{
			xtype: 'label'
		},{
			xtype: 'tbfill',
			html: "<img border='0' width='15%' height='15%' src='./resources/resources/img/logo-vlt-emtu.png' />"
		},{
			xtype: 'tbseparator'
		},{
			xtype: 'button',
			itemId: 'logout',
			handler: function() {
				window.location = 'logout';
		    },
			text: 'Sair'
		}
	]
});