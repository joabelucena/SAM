Ext.define('Sam.controller.Equipment', {
	extend: 'Ext.app.Controller',
	

	views: ['Sam.view.equipment.EquipmentsGrid',
	        'Sam.view.equipment.manufacturer.ManufacturerGrid',
	        'Sam.view.equipment.manufacturer.ManufacturerForm',
	        'Sam.view.equipment.counter.CounterGrid',
	        'Sam.view.equipment.model.ModelGrid',
	        'Sam.view.equipment.type.TypeGrid'],

	init: function() {
		
		this.control({
			'equipmentmanufacturergrid': {
				render: this.onRender,
				itemdblclick: this.onManufacturerBtnShowClick
			},
			
			'#equipmentmanufacturerform toolbar #btnSubmit' :{
				click: this.onManufacturerBtnSubmit
			},
			
			'#equipmentmanufacturergrid toolbar #btnShow' :{
				click: this.onManufacturerBtnShowClick
			},
			
			'#equipmentmanufacturergrid toolbar #btnEdit' :{
				click: this.onManufacturerBtnEditClick
			},
			
			'#equipmentmanufacturergrid toolbar #btnAdd' :{
				click: this.onManufacturerBtnAddClick
			},
			
			'#equipmentmanufacturergrid toolbar #btnDelete' :{
				click: this.onManufacturerBtnDeleteClick
			},
			
			'#equipmentmodelgrid toolbar #btnShow' :{
				click: this.onModelBtnShowClick
			}
		});
	},
	
	
	//ServiceOrder > grid : onRender
	onRender: function(me, eOpts) {
		//me.getStore().reload();
		//me.getView().refresh();
	},
	
	onManufacturerBtnShowClick: function() {
		
		//Aba Objecto Pai
		var mainPanel = Ext.getCmp('viewportpanel');
		
		//Aba ativa
		var activeTab = mainPanel.getActiveTab();
		
		//Linha selecionada
		var row = activeTab.getSelection()[0];
		
		var tabId = 'show-manufacturer-'+row.get('id');
		
		//Tem Registro Selecionado
		if(typeof row !== 'undefined'){
			
			var newTab = mainPanel.items.findBy(
					function(tab){
						return tab.id === tabId;
					});
			
			if (!newTab) {
				newTab = mainPanel.add({
					id: tabId,
					xtype: 'equipmentmanufacturerform',
					closable: true,
					iconCls: 'magnifier-zoom',
					title: 'Visualizar Fabr.: '+row.get('id')
				});
			}
			
			mainPanel.setActiveTab(newTab);
			
			
			/*** 'Seta funcao do botao ***/
			activeTab = mainPanel.getActiveTab();
			
			//Campos a desabilitar
			var fields = Ext.ComponentQuery.query('form field',activeTab)
			
			//Desabilita Campos
			Ext.each(fields,function(f){f.setReadOnly(true)})
			
			
			/**** Seta Campos do Form *****/
			Ext.ComponentQuery.query('#id',activeTab)[0].setValue(row.data.id);
			Ext.ComponentQuery.query('#desc',activeTab)[0].setValue(row.data.desc);
			
			//Seta Botão Confirma: 1 - Visualizar
			Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('click',1)});
			
		}
	},
	
	onManufacturerBtnEditClick: function(){
		
		//Aba Objecto Pai
		var mainPanel = Ext.getCmp('viewportpanel');
		
		//Aba ativa
		var activeTab = mainPanel.getActiveTab();
		
		//Linha selecionada
		var row = activeTab.getSelection()[0];
		
		var tabId = 'show-manufacturer-'+row.get('id');
		
		//Tem Registro Selecionado
		if(typeof row !== 'undefined'){
			
			var newTab = mainPanel.items.findBy(
					function(tab){
						return tab.id === tabId;
					});
			
			if (!newTab) {
				newTab = mainPanel.add({
					id: tabId,
					xtype: 'equipmentmanufacturerform',
					closable: true,
					iconCls: 'magnifier-zoom',
					title: 'Alterar Fabr.: '+row.get('id')
				});
			}
			
			mainPanel.setActiveTab(newTab);
			
			/*** 'Seta funcao do botao ***/
			activeTab = mainPanel.getActiveTab();
			
			var form = Ext.ComponentQuery.query('form',activeTab)[0].getForm();
			
			form.loadRecord(row);
			
			/**** Seta Campos do Form *****/
			//Ext.ComponentQuery.query('#id',activeTab)[0].setValue(row.data.id);
			//Ext.ComponentQuery.query('#desc',activeTab)[0].setValue(row.data.desc);
			
			//Seta Botão Confirma: 3 - Alterar
			Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('click',3)});
			
		}
	},
	
	onManufacturerBtnAddClick: function(){
		
		//Aba Objecto Pai
		var mainPanel = Ext.getCmp('viewportpanel');
		
		//Aba ativa
		var activeTab = mainPanel.getActiveTab();
		
		
		var tabId = 'new-manufacturer'
		
			
		var newTab = mainPanel.items.findBy(
				function(tab){
					return tab.id === tabId;
				});
		
		if (!newTab) {
			newTab = mainPanel.add({
				id: tabId,
				xtype: 'equipmentmanufacturerform',
				closable: true,
				iconCls: 'magnifier-zoom',
				title: 'Incluir Novo fabricante'
			});
		}
		
		mainPanel.setActiveTab(newTab);
		
		/*** 'Seta funcao do botao ***/
		activeTab = mainPanel.getActiveTab();
		
		//var form = Ext.ComponentQuery.query('form',activeTab)[0].getForm();
		
		//form.loadRecord(Ext.create('Sam.model.EquipmentManufacturer'));
		
		//Seta Botão Confirma: 2 - Incluir
		Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('click',2)});
			

	},
	
	onManufacturerBtnDeleteClick: function(){
		//Aba Objecto Pai
		var mainPanel = Ext.getCmp('viewportpanel');
		
		//Aba ativa
		var activeTab = mainPanel.getActiveTab();
		
		//Linha selecionada
		var row = activeTab.getSelection()[0];
		
		var tabId = 'delete-manufacturer-'+row.get('id');
		
		//Tem Registro Selecionado
		if(typeof row !== 'undefined'){
			
			var newTab = mainPanel.items.findBy(
					function(tab){
						return tab.id === tabId;
					});
			
			if (!newTab) {
				newTab = mainPanel.add({
					id: tabId,
					xtype: 'equipmentmanufacturerform',
					closable: true,
					iconCls: 'magnifier-zoom',
					title: 'Excluir Fabr.: '+row.get('id')
				});
			}
			
			mainPanel.setActiveTab(newTab);
			
			/*** 'Seta funcao do botao ***/
			activeTab = mainPanel.getActiveTab();
			
			var form = Ext.ComponentQuery.query('form',activeTab)[0].getForm();
			
			form.loadRecord(row);
			
			//Campos a desabilitar
			var fields = Ext.ComponentQuery.query('form field',activeTab)
			
			//Desabilita Campos
			Ext.each(fields,function(f){f.setReadOnly(true)})
			
			//Seta Botão Confirma: 4 - Exlcuir
			Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('click',4)});
			
		}
	},
	
	onManufacturerBtnSubmit: function(action){
		//Aba Objecto Pai
		var mainPanel = Ext.getCmp('viewportpanel');
		
		//Aba ativa
		var activeTab = mainPanel.getActiveTab();
		
		//Formulario
		var form = Ext.ComponentQuery.query('form',activeTab)[0].getForm();
		
		//registro
		var record = form.getRecord();
		
		
		//1 - Visualiza
		if(action == 1){
			alert('Visualiza');
			
			activeTab.close();
			
		}
		
		//2 - Incluir		
		else if(action == 2){
			
			record.add();
			
		}
		
		//3 - Alterar
		else if (action == 3){
		
			
			record.save();
			
			
			activeTab.close();
		}
		
		//4 - Exlcui
		else if (action == 4){
			alert('Exclui');
			
		}else{

		}

	},
	
	onModelBtnShowClick: function() {
		console.log('Visualiza Modelo');
	}
	
});