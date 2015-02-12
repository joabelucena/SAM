Ext.define('Sam.view.ViewportFooter', {
	extend: 'Ext.toolbar.Toolbar',
	alias: 'widget.viewportfooter',
	
	height: 30,
	ui: 'footer',
	items: [
		{
			xtype: 'tbfill'
		},{
			xtype: 'tbseparator'
		},{
			xtype: 'label',
			html: "<div id='barclock'>_</div>",
			width: 70,
			
			listeners: {
		    	afterrender: function() {
		        	var updateClock = function () {
						Ext.fly('barclock').setHtml(Ext.Date.format(new Date(), 'g:i:s A'));
					};

					var runner = new Ext.util.TaskRunner(),
					task = runner.start({
						run: updateClock,
						interval: 1000
					});
		    	}
			}
		}
	]
});