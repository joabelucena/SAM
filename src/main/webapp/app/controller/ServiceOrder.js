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
			},
			'serviceordernew_id' :{
				click: this.onTriggerClick
			}
				
		});
	},
	
	
	onTriggerClick: function(action){
		
		var equipmentsPopUp = Ext.create('Sam.view.serviceOrder.ServiceOrderEquipmentsPopUp');         	
    	equipmentsPopUp.show();
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
					return tab.id === 'showsoid: '+Ext.getCmp('serviceordergridpanel').getSelection()[0].get('id');
				});
		
		if (!newTab) {
			newTab = mainPanel.add({
				id: 'showsoid: '+Ext.getCmp('serviceordergridpanel').getSelection()[0].get('id'),
				xtype: 'serviceordernew',
				closable: true,
				iconCls: 'magnifier-zoom',
				title: 'Nova OS'
			});
		}
		
		mainPanel.setActiveTab(newTab);
		
		Ext.getCmp('serviceordernewform').query('#openNewSoButton')[0].setHandler(function() {this.fireEvent('click',2)});
		/*
		eventID = record.get('id');
		
		Ext.getCmp('alarmshowform').getForm().findField('alarmshow_site').setValue(jsonResp.site);
		*/
		
	},
	
	onopenNewSoButtonClick: function(action){
		
		//Visualiza
		if(action == 1){
			alert('Encerra Visualizacao da OS')
		
		//Confirma Abertura de OS
		}else if(action == 2){
			alert('Confirma Abertura de OS')
		}else if (action == 3){
			
		}else{
			
		}
		console.log('acao: '+action);
		
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
						return tab.id === 'sotabid';
					});
			
			if (!newTab) {
				newTab = mainPanel.add({
					id: 'sotabid',
					xtype: 'serviceordernew',
					closable: true,
					iconCls: 'magnifier-zoom',
					title: 'Visualizar OS'
				});
			}
			
			mainPanel.setActiveTab(newTab);
			/*
			 * Fields
			 * Ext.getCmp('serviceordernewform').getForm().findField('serviceordernew_id').setReadOnly(true)
			 * 
			 * 
			 * button
			 * Ext.getCmp('serviceordernewform').query('#openNewSoButton')
			 * 
			 */
			Ext.getCmp('serviceordernewform').query('#openNewSoButton')[0].setHandler(function() {this.fireEvent('click',1)});
			
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
