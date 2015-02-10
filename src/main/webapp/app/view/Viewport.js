Ext.define('Sam.view.Viewport', {
	extend: 'Ext.container.Viewport',
	alias: 'widget.viewport',
	

    layout: 'border',
	id: 'viewport',
    items: [{
        region: 'north',
    	title: "<img border='0' width='15%' height='15%' align='right' src='./resources/resources/img/logo-vlt-emtu.png' />",
        margins: '5 5 5 5'
    }, {
        region: 'west',
    	layout: 'accordion',
   	 	activetab: 0,
        collapsible: true,
    	split: true,
        title: 'Menu',
        width: 200,
    items: [{
		title: 'Início',
		id: 'panelInicio'		
	    },{
		title: 'Manutenções',
		id: 'panelManutenções',
		html: ''
	    
	    },{
		title: 'Relatórios',
		id: 'panelRelatorios',
		html: ''
	    },{

		title: 'Configurações',
		id: 'panelConfiguracoes',
		html: ''
	    }]
  	}, {
		region: 'south',
		layout: 'fit',
  		html: "<div id='barclock' align='right'>_</div>",
		bodyPadding: '5 12 5 5',
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
  	}, {
        
    	region: 'center',
		id: 'centerPanel',
    	xtype: 'tabpanel'
       
    	}]
});

