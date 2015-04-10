Ext.define('Sam.controller.ServiceOrder', {
	extend: 'Ext.app.Controller',
	
	views: ['serviceOrder.ServiceOrderGrid',
	        'serviceOrder.ServiceOrderPanel',
	        'serviceOrder.ServiceOrderNew',
	        'serviceOrder.ServiceOrderNew',
	        'serviceOrder.ServiceOrderEquipmentsPopUp'],
	
	init: function() {
		
		this.control({
			'serviceordergrid': {
				render: this.onRender,
			},
			'toolbar #newSoButton' :{
				click: this.onnewSoButtonClick
			},
			
			'toolbar #openNewSoButton' :{
				click: this.onopenNewSoButtonClick
			},
			'toolbar #showSoButton' :{
				click: this.onshowSoButtonClick
			},
			'toolbar #button2' :{
				click: this.onButtonBClick
			},
			'toolbar #logShowButton' :{
				click: this.onlogShowButtonClick
			}
		});
	},
	
	onRender: function(component, options) {
		component.getStore().load();
	},
	
	onnewSoButtonClick: function() {
		
		//Id
		//Ext.getCmp('serviceordergridpanel').getSelection()[0].get('id');
		
		var mainPanel = Ext.getCmp('viewportpanel');
		
		var newTab = mainPanel.items.findBy(
				function(tab){
					return tab.title === 'Nova OS';
				});
		
		if (!newTab) {
			newTab = mainPanel.add({
				xtype: 'serviceordernew',
				closable: true,
				iconCls: 'magnifier-zoom',
				title: 'Nova OS'
			});
		}
		
		mainPanel.setActiveTab(newTab);
		/*
		eventID = record.get('id');
		
		Ext.getCmp('alarmshowform').getForm().findField('alarmshow_site').setValue(jsonResp.site);
		*/
		
	},
	
	onopenNewSoButtonClick: function(){
		Ext.Msg.alert('Confirma Abertura de OS', 'Confirma Abertura de OS');
	},
	
	//Botao Visualizar OS
	onshowSoButtonClick:function(){
		
		//Linha selecionada
		var row = Ext.getCmp('serviceordergridpanel').getSelection()[0];
		
		//Tem Registro Selecionado
		if(typeof row !== 'undefined'){
			var mainPanel = Ext.getCmp('viewportpanel');
			
			var newTab = mainPanel.items.findBy(
					function(tab){
						return tab.title === 'Visualizar OS';
					});
			
			if (!newTab) {
				newTab = mainPanel.add({
					xtype: 'serviceordernew',
					closable: true,
					iconCls: 'magnifier-zoom',
					title: 'Visualizar OS'
				});
			}
			
			mainPanel.setActiveTab(newTab);
			
			
			
			console.log('para');
		}
		
	},
	
	onButtonBClick: function() {
		Ext.Msg.alert('botao2', 'botao2');    
	},
	onlogShowButtonClick: function() {
		Ext.Msg.alert('Exibir Log da Os', 'botao2');    
	}
	
	
});
