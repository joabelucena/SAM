Ext.define('Sam.view.Home', {
	extend: 'Ext.window.Window',
	alias: 'widget.home',
	

        layout: 'border',
		id: 'viewport',
        items: [{
            region: 'north',
	    	title: "<img border='0' width='15%' height='15%' align='right' src='./resources/img/logo-vlt-emtu.png' />",
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

		var storeEvent = Ext.create('Ext.data.TreeStore', {
        
		root: {
            expanded: true,
            children: [
                { id: 'EventsMenu', text: "Eventos", leaf: true },
                { id: 'AlarmsMenu', text: "Alarmes", leaf: true },
				{ id: 'EquipmentsMenu', text: "Equipamentos", leaf: true}
			]
		}
    });

    Ext.create('Ext.tree.Panel', {
        title: 'Simple Tree',
        id: 'treeEvents',
        header: false,
		border: false,
        store: storeEvent,
        rootVisible: false,
        renderTo: Ext.getBody(),
		listeners: {
        itemclick: function(s,r) {
                // alert(s.data.text);

				if(r.get('id')=='EventsMenu') {

					Ext.define('User', {
    				extend: 'Ext.data.Model',
    				fields: [ 'name', 'email', 'phone' ]
					});

					var userStore = Ext.create('Ext.data.Store', {
    				model: 'User',
    data: [
        { name: 'Lisa', email: 'lisa@simpsons.com', phone: '555-111-1224' },
        { name: 'Bart', email: 'bart@simpsons.com', phone: '555-222-1234' },
        { name: 'Homer', email: 'homer@simpsons.com', phone: '555-222-1244' },
        { name: 'Marge', email: 'marge@simpsons.com', phone: '555-222-1254' }
    ]
});

	var contentEl = Ext.create('Ext.grid.Panel', {
		store: userStore,
		width: 400,
		height: 200,
		title: 'Eventos',
		closable: true,
		plugins: 'gridfilters',
		columns: [
		    {
		        text: 'Name',
		        width: 100,
		        sortable: false,
		        hideable: false,
		        dataIndex: 'name',
				filter: {
		         type: 'string'
		     	}
		    },
		    {
		        text: 'Email Address',
		        width: 150,
		        dataIndex: 'email',
		        hidden: false
		    },
		    {
		        text: 'Phone Number',
		        flex: 1,
		        dataIndex: 'phone',
				filter: {
					type: 'number'
				}
		    }
    	]
	});
					
				var viewportcenter = Ext.getCmp('centerPanel');
					viewportcenter.add(contentEl);			
   				 }

				
				if(r.get('id')=='AlarmsMenu') {
					alert(r.data.text);
				}

				if(r.get('id')=='EquipmentsMenu') {
					alert(r.data.text);
				}        
        	}
    	}
    });

    var panel = Ext.getCmp('panelInicio');
    var tree = Ext.getCmp('treeEvents');
    panel.add(tree);
		
	}

});
