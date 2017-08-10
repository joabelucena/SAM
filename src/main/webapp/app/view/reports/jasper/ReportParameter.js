Ext.define('Sam.view.reports.jasper.ReportParameter', {
    extend: 'Ext.window.Window',
    xtype: 'form-vboxlayout',

    title: 'Resize Me',
    width: 500,
    height: 300,

    minWidth: 300,
    minHeight: 220,
    layout: 'fit',
    plain: true,
    
    items: [{
        xtype: 'form',
        fieldDefaults: {
            labelWidth: 60
        },
        
        layout: {
            type: 'vbox',
            align: 'stretch'
        },
        
        bodyPadding: 5,
        border: false,
        
        items: [{
            // Fieldset in Column 1 - collapsible via toggle button
            xtype:'fieldset',
            title: 'Parametros',
            defaultType: 'textfield',
            defaults: {
            	anchor: '100%',
            	xtype: 'textfield',
            		
            }
        }]
    }],

    buttons: [{
        text: 'Ok',
        handler: function(button){
        	
        	
        	var mainPanel = Ext.getCmp('viewportpanel'),
				menuPanel = Ext.getCmp('viewportmenu'),
				window = button.up("window");
        	
        	var data = window.down('form').getValues();
        	
        	data["label"] = window.xReportData.id;
        	
			var newTab = mainPanel.add({
				xtype: 'jasper',
				xReportData: data,
				closable: true,
				title: "Relatório: " + window.xReportData.desc
			});
			
			
			window.close()
			
			if (newTab){
				mainPanel.setActiveTab(newTab);
			}
        }
        	
    },{
        text: 'Cancelar',
        handler: function(button){
        	button.up("window").close()
        }
    }]
});