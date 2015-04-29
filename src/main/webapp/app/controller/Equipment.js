Ext.define('Sam.controller.Equipment', {
	extend: 'Ext.app.Controller',
	
	
	stores: ['EquipmentManufacturer'],
	
	models: ['EquipmentManufacturer'],
	
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
				create: this.onManufacturerBtnSubmitAdd,
				read:   function(){Ext.getCmp('viewportpanel').getActiveTab().close()},
				update: this.onManufacturerBtnSubmitEdit,
				remove: this.onManufacturerBtnSubmitDelete,
				
			},
			
			'#equipmentmanufacturerform toolbar #btnDiscard' :{
				click:   function(){Ext.getCmp('viewportpanel').getActiveTab().close()},
				
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
			Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('read')});
			
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
			
			
			//Seta Botão Confirma: Alterar
			Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('update')});
			
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
		
		//Seta Botão Confirma: Incluir
		Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('create')});
			

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
			
			activeTab = mainPanel.getActiveTab();
			
			var form = Ext.ComponentQuery.query('form',activeTab)[0].getForm();
			
			form.loadRecord(row);
			
			//Campos a desabilitar
			var fields = Ext.ComponentQuery.query('form field',activeTab)
			
			//Desabilita Campos
			Ext.each(fields,function(f){f.setReadOnly(true)})
			
			//Seta Botão Confirma: Exlcuir
			Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('remove')});
			
		}
	},
	
	onManufacturerBtnSubmitAdd: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getEquipmentManufacturerStore(),						//Store
			record		= Ext.create('Sam.model.EquipmentManufacturer');			//Registro
		
		
		//Carrega dados do Formulario no registro
		record.set(values);
		
		//Adiciona registro na store
		store.add(record);
		
		//Sincroniza e Atualiza Store
		syncStore(store);
		
		//Fecha Aba
		activeTab.close();
	},
	
	onManufacturerBtnSubmitEdit: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getEquipmentManufacturerStore(),						//Store
			record		= form.getRecord();											//Registro
		
		//Carrega dados do formulario na Store
		store.findRecord('id',record.get('id')).set(values);
		
		//Sincroniza e Atualiza Store
		syncStore(store);
		
		//Fecha Aba
		activeTab.close();
		
	},
	
	onManufacturerBtnSubmitDelete: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getEquipmentManufacturerStore(),						//Store
			record		= form.getRecord();											//Registro
		
		//Apaga registro da Store
		store.remove(record);
		
		//Sincroniza e Atualiza Store
		syncStore(store);
		
		//Fecha Aba
		activeTab.close();
		
	},
	
	onModelBtnShowClick: function() {
		console.log('Visualiza Modelo');
	}
	
});

function syncStore(store){
	
	//Sincroniza Store
	store.sync({
        success: function()
        {
            console.log("success!!");
        },
        failure: function()
        {
            console.log("failed...");
        },
        callback: function()
        {
            console.log("calling callback");
        },
        scope: this
    });
	
	//Recarrega a store
	store.reload();
	
	Ext.each(Ext.ComponentQuery.query('#equipmentmanufacturergrid'),function(f){
		f.getStore().reload();
		f.getView().refresh();
	});
	
	
}