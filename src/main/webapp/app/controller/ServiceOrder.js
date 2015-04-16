Ext.define('Sam.controller.ServiceOrder', {
	extend: 'Ext.app.Controller',
	

	views: ['serviceOrder.ServiceOrderGrid',
	        'serviceOrder.ServiceOrderForm',
	        'serviceOrder.log.ServiceOrderLog',
			'serviceOrder.log.ServiceOrderLogGrid',
	        'serviceOrder.log.ServiceOrderLogForm',
	        'Sam.view.equipment.EquipmentsGrid',
	        'Sam.view.components.PopUp'],

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
			'toolbar #btnChangeSts' :{
				click: this.onBtnChangeStsClick
			},
			'#trg_equipment_id' :{
				click: this.onTriggerClick,
			},
			'toolbar #popupConfirma' :{ 
				click: this.f3Confirm,
			}				
		});
	},
	
	//ServiceOrder > Log > grid: itemMouseUp
	onItemMouseUp : function( me, record, item, index, e, eOpts ){
		
		//Aba Objecto Pai
		var mainPanel = Ext.getCmp('viewportpanel');
		
		//Aba ativa
		var activeTab = mainPanel.getActiveTab();
		
		//Seta Valores do grid no Form
		Ext.ComponentQuery.query('form #log_os',activeTab)[0].setValue(record.get('serviceorder_id'));
		Ext.ComponentQuery.query('form #log_prevstatus',activeTab)[0].setValue(record.get('prevstatus'));
		Ext.ComponentQuery.query('form #log_curstatus',activeTab)[0].setValue(record.get('curstatus'));
		Ext.ComponentQuery.query('form #log_user',activeTab)[0].setValue(record.get('user_id'));
		Ext.ComponentQuery.query('form #log_datetime',activeTab)[0].setValue(Ext.Date.format(record.get('datetime'), 'm-d-Y g:i A'));
		Ext.ComponentQuery.query('form #log_remark',activeTab)[0].setValue(record.get('remarks'));
		
	},

	//ServiceOrder > form > trigger:equipment_id: confirm button
	f3Confirm: function(component) {
		
		var grid = Ext.ComponentQuery.query('popup grid')[0];
		var equipmentId = grid.getSelection()[0].get('id');
		
		Ext.ComponentQuery.query('form #trg_equipment_id')[0].setValue(grid.getSelection()[0].get('id'));
		Ext.ComponentQuery.query('form #equipment_model')[0].setValue(grid.getSelection()[0].get('model'));
		Ext.ComponentQuery.query('form #equipment_manufacturer')[0].setValue(grid.getSelection()[0].get('manufacturer'));
		Ext.ComponentQuery.query('form #equipment_subsystem')[0].setValue(grid.getSelection()[0].get('site'));
		Ext.ComponentQuery.query('form #equipment_site')[0].setValue(grid.getSelection()[0].get('system'));
		Ext.ComponentQuery.query('popup')[0].close();
	
	},	
	
	//ServiceOrder > form > trigger:equipment_id: popup button
	onTriggerClick: function(){
	
		var equipmentsPopUp = Ext.create('Sam.view.components.PopUp');
		var grid = Ext.create('Sam.view.equipment.EquipmentsGrid');
		
		equipmentsPopUp.setTitle('Selecionar Equipamento');
		equipmentsPopUp.add(grid);
		equipmentsPopUp.show();
		
	},
	
	//ServiceOrder > grid : onRender
	onRender: function(component, options) {

	},
	
	//ServiceOrder > grid : btnNewSo button
	onBtnNewSoClick: function() {
		
		//Aba Objecto Pai
		var mainPanel = Ext.getCmp('viewportpanel');
		
		//Aba ativa
		var activeTab = mainPanel.getActiveTab();
		
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
		
		activeTab = mainPanel.getActiveTab();
		
		//Desabilita exibicao do log
		Ext.ComponentQuery.query('#btnShowLog',activeTab)[0].setVisible(false);
		
		//Disabilia o campo 'Codigo da OS'
		Ext.ComponentQuery.query('form #id',activeTab)[0].setDisabled(true);
		
		//Desabilita Novo Status
		Ext.ComponentQuery.query('form #fldNewStatus',activeTab)[0].setVisible(false);
		
		//Seta Botão Confirma: Incluir
		Ext.ComponentQuery.query('#btnOk',activeTab)[0].setHandler(function() {this.fireEvent('click',2)});
		
		// Abertura OS: Dados(OS) : ComboBox
		Ext.ComponentQuery.query('form #type',activeTab)[0].setStore(Ext.data.Store({
			fields: ['type'],
			proxy: {
		         type: 'ajax',
		         url: 'so/gettypes',
		         reader: {
		             type: 'json',
		             root: 'type'
		         }
		     },
		}));
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

		}

	},
	
	//ServiceOrder > grid : BtnChangeSts button
	onBtnChangeStsClick:function(){
		
		//Aba Objecto Pai
		var mainPanel = Ext.getCmp('viewportpanel');
		
		//Aba ativa
		var activeTab = mainPanel.getActiveTab();
		
		//Linha selecionada
		var row = activeTab.getSelection()[0];
		
		var tabId = 'cgsts-'+row.get('id');
		
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
					title: 'Mudar Estado OS: '+row.get('id')
				});
			}
			
			mainPanel.setActiveTab(newTab);
			
			/*** 'Seta funcao do botao ***/
			activeTab = mainPanel.getActiveTab();
			
			//Seta Botão Confirma: Inlcuir
			Ext.ComponentQuery.query('#btnOk',activeTab)[0].setHandler(function() {this.fireEvent('click',4)});
			
			
			// MudarStatus: ComboBox
			Ext.ComponentQuery.query('form #n_cmbStatus',activeTab)[0].setStore(Ext.data.Store({
				fields: ['rule'],
				
				proxy: {
			         type: 'ajax',
			         url: 'so/getallowedstatus',
			         
			         extraParams: {
			        	 curstatus: 'NOVA'	            			
			         },
			         
			         reader: {
			             type: 'json',
			             root: 'rule'
			         }
			     },
			}));
			
			//Campos a desabilitar
			var fields = Ext.ComponentQuery.query('form #soInfo field, form #equipmentInfo field',activeTab)
			
			//Desabilita Campos
			Ext.each(fields,function(f){f.setReadOnly(true)})
			
			
			/**** Seta Campos do Form *****/
			Ext.ComponentQuery.query('#id',activeTab)[0].setValue(row.get('id'));
			
			
			
			
			
			
			
			/******************************/
		}
	},
	
	//ServiceOrder > grid : BtnShowSo button
	onBtnShowSoClick:function(){
		
		//Aba Objecto Pai
		var mainPanel = Ext.getCmp('viewportpanel');
		
		//Aba ativa
		var activeTab = mainPanel.getActiveTab();
		
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
			activeTab = mainPanel.getActiveTab();
			
			//Seta Botão Confirma: Visualizar
			Ext.ComponentQuery.query('#btnOk',activeTab)[0].setHandler(function() {this.fireEvent('click',1)});
			
			//Desabilita Novo Status
			Ext.ComponentQuery.query('form #fldNewStatus',activeTab)[0].setVisible(false);
			
			//Campos a desabilitar
			var fields = Ext.ComponentQuery.query('form #soInfo field, form #equipmentInfo field',activeTab)
			
			//Desabilita Campos
			Ext.each(fields,function(f){f.setReadOnly(true)})
			
			/**** Seta Campos do Form *****/
			Ext.ComponentQuery.query('#id',activeTab)[0].setValue(row.get('id'));
			
			
			
			
			
			
			
			/******************************/
		}
	},
	
	//ServiceOrder > form : BtnShowLog button
	onBtnShowLogClick: function(action) {
	
		var mainPanel = Ext.getCmp('viewportpanel');
		
		var activeTab = mainPanel.getActiveTab();
		
		var fieldId = Ext.ComponentQuery.query('#id',activeTab)[0];
		
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
