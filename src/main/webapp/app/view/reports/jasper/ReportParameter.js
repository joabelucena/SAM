Ext.define('Sam.view.reports.jasper.ReportParameter', {
    extend: 'Ext.window.Window',
    xtype: 'form-vboxlayout',
    
    requires: ['Sam.lib.SmartCombo'],
        
    title: 'Parametros',
    width: 500,
    height: 300,
    
    minWidth: 300,
    minHeight: 220,
    layout: 'fit',
    plain: true,
    closeAction : 'hide',
	closable : false,
    
	scrollable: true,
	
    items: [{
        xtype: 'form',
        layout: {
            type: 'vbox',
            align: 'stretch'
        },
        defaults: {
        	labelWidth : 60
        },
        
        bodyPadding: 5,
        border: false,
        
        items: [{
            xtype:'fieldset',
            title: 'Parametros',
            defaultType: 'textfield',
            defaults: {
            	xtype: 'textfield',
            	anchor: '100%',
            	
            	allowBlank : false,
            	msgTarget : 'under',
            	enableKeyEvents : true,
        		
            	listeners : {
    				keyup : function(field, e) {
    					// Enter
    					if (e.getKey() == 13) {
    						
    						sumbitReport(field);
    						// Esc
    					} else if (e.getKey() == 27) {
    						
    						resetForm(field);
    					}
    				}
    			}
            }
        }]
    }],

    buttons: [{
        text: 'Ok',
        handler: function(button){
        	
        	sumbitReport(button);
        }
        	
    },{
        text: 'Cancelar',
        handler: function(button){
        	button.up("window").close()
        }
    }]
});

function sumbitReport(component) {
	var mainPanel = Ext.getCmp('viewportpanel'),
		menuPanel = Ext.getCmp('viewportmenu'),
		window = component.up("window"),
		form = window.down('form');
	
	data = form.getValues();
	
	if(form.isValid()){
		
		Ext.MessageBox.show({
	        title: 'Confirma Relatório',
	        msg: 'Confirma geração do relatório com os parametros escolhidos?',
	        buttons: Ext.MessageBox.OKCANCEL,
	        icon: Ext.MessageBox.INFO,
	        fn: function(btn,  knowId, knowCheck){
	            if(btn == 'ok'){
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
	        }
		});
	
	}
		

		
		
	
}

function resetForm(field){
	form = field.up('form')
	
	Ext.MessageBox.show({
        title: 'Limpa parâmetros',
        msg: 'Deseja limpar os parâmetros?',
        buttons: Ext.MessageBox.OKCANCEL,
        icon: Ext.MessageBox.WARNING,
        fn: function(btn,  knowId, knowCheck){
            if(btn == 'ok'){
            	form.reset();
            	form.down('field').focus();
            }
        }
	});
}