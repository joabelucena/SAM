Ext.define('Sam.controller.ServiceOrder', {
	extend: 'Ext.app.Controller',
	
	views: ['serviceOrder.ServiceOrderGrid',
	        'serviceOrder.ServiceOrderPanel'],
	
	init: function() {
		
		this.control({
			'serviceordergrid': {
				render: this.onRender,
			},
			'toolbar #button1' :{
				click: this.onButtonAClick
			},
			'toolbar #button2' :{
				click: this.onButtonBClick
			}
		});
	},
	
	onRender: function(component, options) {
		component.getStore().load();
	},
	
	onButtonAClick: function() {
		
		//Id
		Ext.getCmp('serviceordergridpanel').getSelection()[0].get('id');
		
		var mainPanel = Ext.getCmp('viewportpanel');
		
		var newTab = mainPanel.items.findBy(
				function(tab){
					return tab.title === 'LOG da Os';
				});
		
		if (!newTab) {
			newTab = mainPanel.add({
				/*
				xtype: 'alarmshow',
				closable: true,
				iconCls: 'magnifier-zoom',
				title: 'Visualiza Alarme'
				*/
			});
		}
		/*
		mainPanel.setActiveTab(newTab);
		
		eventID = record.get('id');
		
		Ext.getCmp('alarmshowform').getForm().findField('alarmshow_site').setValue(jsonResp.site);
		*/
		
	},
	onButtonBClick: function() {
		Ext.Msg.alert('botao2', 'botao2');    
	}
	
	
});
