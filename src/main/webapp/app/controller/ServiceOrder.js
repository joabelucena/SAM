Ext.define('Sam.controller.ServiceOrder', {
	extend: 'Ext.app.Controller',
	

	views: ['serviceOrder.ServiceOrderGrid',
	        'serviceOrder.ServiceOrderPanel',
	        'serviceOrder.ServiceOrderNew',
			'serviceOrder.serviceOrderLog.ServiceOrderLog',
			'serviceOrder.serviceOrderLog.ServiceOrderLogGrid',
	        'serviceOrder.serviceOrderLog.ServiceOrderLogForm',
	        'Sam.view.components.PopUp',
	        'Sam.view.equipments.EquipmentsGrid'],

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
			'#serviceordernew_id' :{ 
				click: this.onTriggerClick,
			},
			'toolbar #popupConfirma' :{ 
				click: this.f3Confirm,
			}
				
		});
	},
	
	f3Confirm: function(component) {
		
		var grid = Ext.ComponentQuery.query('popup grid')[0];
		var equipmentId = grid.getSelection()[0].get('id');
		
		
		Ext.ComponentQuery.query('form #serviceordernew_id')[0].setValue(grid.getSelection()[0].get('id'));
		Ext.ComponentQuery.query('form #serviceordernew_model')[0].setValue(grid.getSelection()[0].get('model'));
		Ext.ComponentQuery.query('form #serviceordernew_manufacturer')[0].setValue(grid.getSelection()[0].get('manufacturer'));
		Ext.ComponentQuery.query('form #serviceordernew_subsystem')[0].setValue(grid.getSelection()[0].get('site'));
		Ext.ComponentQuery.query('form #serviceordernew_site')[0].setValue(grid.getSelection()[0].get('system'));
		
		Ext.ComponentQuery.query('popup')[0].close();
	
	},	
	
	onTriggerClick: function(){
		
		var equipmentsPopUp = Ext.create('Sam.view.components.PopUp');
		var grid = Ext.create('Sam.view.equipments.EquipmentsGrid');
		
		equipmentsPopUp.setTitle('Selecionar Equipamento');
		equipmentsPopUp.add(grid);
		equipmentsPopUp.show();
		
	},
	
	onRender: function(component, options) {
		//component.getStore().load();
	},
	
	onnewSoButtonClick: function() {
				
		var mainPanel = Ext.getCmp('viewportpanel');
		
		var newTab = mainPanel.items.findBy(
				function(tab){
					return tab.id === 'newso';
				});
		
		if (!newTab) {
			newTab = mainPanel.add({
				id: 'newso',
				xtype: 'serviceordernew',
				closable: true,
				iconCls: 'magnifier-zoom',
				title: 'Nova OS'
			});
		}
		
		mainPanel.setActiveTab(newTab);
		
		//Ext.getCmp('serviceordernewform').query('#openNewSoButton')[0].setHandler(function() {this.fireEvent('click',2)});
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
		
		var tabId = 'sotabid-'+row.get('id');
		
		//Tem Registro Selecionado
		if(typeof row !== 'undefined'){
			var mainPanel = Ext.getCmp('viewportpanel');
			
			var newTab = mainPanel.items.findBy(
					function(tab){
						return tab.id === tabId;
					});
			
			if (!newTab) {
				newTab = mainPanel.add({
					id: tabId,
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
			 * 
			 * grid = algumacoisa.dowsn()
			 * 
			 * itemId: 'btnCancel'
			 * 
			 * grid.component('btnCancel')
			 * 
			 * button
			 * Ext.getCmp('serviceordernewform').query('#openNewSoButton')
			 *
			 * Ext.getCmp('serviceordernewform').query('#openNewSoButton')[0].setHandler(function() {this.fireEvent('click',1)});
			 * 
			 */
		}
	},
	
	onButtonBClick: function() {
		var store = Ext.create('Sam.store.ServiceOrder');
		
		store.load();
		
		Ext.Msg.alert('botao2', 'botao2');    
	},
	
	onlogShowButtonClick: function() {
		
		var tabId = 'sologtabid-';
		
		var mainPanel = Ext.getCmp('viewportpanel');
		
		var newTab = mainPanel.items.findBy(
				function(tab){
					return tab.id === tabId;
				});
		
		if (!newTab) {
			newTab = mainPanel.add({
				id: tabId,
				xtype: 'serviceorderlog',
				closable: true,
				iconCls: 'magnifier-zoom',
				title: 'Historico da OS: XXXXXX'
			});
		}
		
		mainPanel.setActiveTab(newTab);
		
	}
});
