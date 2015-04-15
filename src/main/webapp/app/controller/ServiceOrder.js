Ext.define('Sam.controller.ServiceOrder', {
	extend: 'Ext.app.Controller',
	

	views: ['serviceOrder.ServiceOrderGrid',
	        'serviceOrder.ServiceOrderForm',
	        'serviceOrder.log.ServiceOrderLog',
			'serviceOrder.log.ServiceOrderLogGrid',
	        'serviceOrder.log.ServiceOrderLogForm',
	        'Sam.view.equipment.EquipmentsGrid'],

	init: function() {
		
		this.control({
			'serviceordergrid': {
				render: this.onRender,
			},
			'toolbar #btnNewSo' :{
				click: this.onBtnNewSoClick
			},
			'serviceorderloggrid':{
				itemmouseup: this.onItemMouseUp,
			},
			'toolbar #btnOk' :{
				click: this.onBtnOkClick
			},
			'toolbar #btnShowSo' :{
				click: this.onBtnShowSoClick
			},
			'toolbar #btnShowLog' :{
				click: this.onBtnShowLogClick
			},
			'#trg_equipment_id' :{
				click: this.onTriggerClick,
				confirmClick: this.f3Confirm,
			}
				
		});
	},
	
	//ServiceOrder > Log > grid: itemMouseUp
	onItemMouseUp : function( me, record, item, index, e, eOpts ){

		/*
		var form = Ext.ComponentQuery.query('serviceorderlogform')[0].down('form').getForm();
		
		Ext.ComponentQuery.query('#log_prevstatus')[0].setValue(record.get('prevstatus'));
		Ext.ComponentQuery.query('#log_curstatus')[0].setValue(record.get('curstatus'));
		
		//Retorna Form
		var form = Ext.ComponentQuery.query('form',tablala)[0]
		*/

		//Retorna Campo do Form: mod1
		//Ext.ComponentQuery.query('#log_prevstatus',form)[0]
		//Retorna Campo do Form: mod2
		//Ext.ComponentQuery.query('form #log_prevstatus',tablala)

		/*
		var mainPanel = Ext.getCmp('viewportpanel');
		
		var activeTab = mainPanel.getActiveTab()
		var gridLog = Ext.ComponentQuery.query('grid',activeTab)[0]
		//Filters Store
		gridLog.getStore().setFilters([{
			property: 'id',
			value: 16
		}
		]);
		*/
	},

	//ServiceOrder > form > trigger:equipment_id: confirm button
	f3Confirm: function() {
		
	},	
	
	//ServiceOrder > form > trigger:equipment_id: popup button
	onTriggerClick: function(){
		console.log('trigger clicked');
	},
	
	//ServiceOrder > grid : onRender
	onRender: function(component, options) {

	},
	
	//ServiceOrder > grid : btnNewSo button
	onBtnNewSoClick: function() {
		
		var mainPanel = Ext.getCmp('viewportpanel');
		
		var newTab = mainPanel.items.findBy(
				function(tab){
					return tab.id === 'newso';
				});
		
		if (!newTab) {
			newTab = mainPanel.add({
				id: 'newso',
				xtype: 'serviceorderform',
				closable: true,
				iconCls: 'magnifier-zoom',
				title: 'Nova OS'
			});
		}
		
		mainPanel.setActiveTab(newTab);
		
	},
	
	//ServiceOrder > form : btnOk button
	onBtnOkClick: function(action){
		
		//1 - Visualiza
		if(action == 1){
			alert('Visualiza');
		
		//2 - Incluir
		}else if(action == 2){
			alert('Inclui');
			
		//3 - Alterar
		}else if (action == 3){
			alert('Altera');

		//4 - Mudar Status 
		}else if (action == 4){
			alert('Muda Status');
		}else{
			alert('Sem Acao selecionada');
		}

	},
	
	//ServiceOrder > grid : BtnShowSo button
	onBtnShowSoClick:function(){
		
		//Aba Objecto Pai
		var mainPanel = Ext.getCmp('viewportpanel');
		
		//Aba ativa
		var activeTab = mainPanel.getActiveTab()
		
		//Linha selecionada
		var row = activeTab.getSelection()[0];
		
		var tabId = 'show-'+row.get('id');
		
		//Tem Registro Selecionado
		if(typeof row !== 'undefined'){
			
			var newTab = mainPanel.items.findBy(
					function(tab){
						return tab.id === tabId;
					});
			
			if (!newTab) {
				newTab = mainPanel.add({
					id: tabId,
					xtype: 'serviceorderform',
					closable: true,
					iconCls: 'magnifier-zoom',
					title: 'Visualizar OS: '+row.get('id')
				});
			}
			
			mainPanel.setActiveTab(newTab);
			
			/*** 'Seta funcao do botao ***/
			activeTab = mainPanel.getActiveTab()
			
			//Seta Botão Confirma: Inlcuir
			Ext.ComponentQuery.query('#btnOk',activeTab)[0].setHandler(function() {this.fireEvent('click',1)});
			
			//Seta para nao exibir log
			Ext.ComponentQuery.query('#btnShowLog',activeTab)[0].setHandler(function() {this.fireEvent('click',0)});
			
			
			//Seta Campos do Form
			Ext.ComponentQuery.query('#id',activeTab)[0].setValue(row.get('id'));
			
			/*
			 * Fields
			 * Ext.getCmp('serviceordernewform').getForm().findField('serviceordernew_id').setReadOnly(true)
			 * grid = algumacoisa.dowsn()
			 *
			 * button
			 * Ext.getCmp('serviceordernewform').query('#openNewSoButton')
			 *
			 * Ext.getCmp('serviceordernewform').query('#openNewSoButton')[0].setHandler(function() {this.fireEvent('click',1)});
			 * 
			 */
		}
	},
	
	onBtnShowLogClick: function(action) {
		
		var mainPanel = Ext.getCmp('viewportpanel');
		
		var activeTab = mainPanel.getActiveTab()
		
		var fieldId = Ext.ComponentQuery.query('#id',activeTab)[0]
		
		var tabId = 'solog-'+fieldId.getRawValue();
		
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
				title: 'Historico da OS: '+fieldId.getValue()
			});
		}
		
		
		mainPanel.setActiveTab(newTab);
		
		activeTab = mainPanel.getActiveTab();
		
		var gridLog = Ext.ComponentQuery.query('grid',activeTab)[0]
		
		//Filtra Store
		gridLog.getStore().setFilters([{
			property: 'serviceorder_id',
			value: fieldId.getValue()
		}
	]);
		
		
		
	}
});
