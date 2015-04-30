Ext.define('Sam.controller.Equipment', {
	extend: 'Ext.app.Controller',
	
	
	stores: ['EquipmentManufacturer',
		        'EquipmentCounter',
		     	'EquipmentManufacturer',
		     	'Equipment',
		     	'EquipmentModel',
		     	'EquipmentType'],
	
	models: ['EquipmentManufacturer',
		        'EquipmentCounter',
		     	'EquipmentManufacturer',
		     	'Equipment',
		     	'EquipmentModel',
		     	'EquipmentType'],
	
	views: ['Sam.view.equipment.EquipmentsGrid',
	        'Sam.view.equipment.manufacturer.ManufacturerGrid',
	        'Sam.view.equipment.manufacturer.ManufacturerForm',
	        'Sam.view.equipment.counter.CounterGrid',
	        'Sam.view.equipment.counter.CounterForm',
	        'Sam.view.equipment.model.ModelGrid',
	        //'Sam.view.equipment.model.ModelForm',
	        'Sam.view.equipment.type.TypeGrid',
	        'Sam.view.equipment.type.TypeForm'
	        ],

	init: function() {
		
		this.control({
			/*
			 * Grid Listeners
			 */
			'equipmentmanufacturergrid': {
				render: this.onRender,
				itemdblclick: this.onManufacturerBtnShowClick
			},
			
			'equipmentsgrid': {
				render: this.onRender,
				itemdblclick: function(){}
			},
			
			'equipmentscountergrid': {
				render: this.onRender,
				itemdblclick: function(){}
			},
			
			'equipmentsmodelgrid': {
				render: this.onRender,
				itemdblclick: function(){}
			},
			
			'equipmentstypegrid': {
				render: this.onRender,
				itemdblclick: function(){}
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
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 1 - Visualizar
			activeTab = activateTab(1, row.get('id'), 'equipmentmanufacturerform', null);
			
			if(activeTab){
			
				//Retorna Form
				var form = Ext.ComponentQuery.query('form',activeTab)[0].getForm();
				
				//Carrega registro no form
				form.loadRecord(row);
				
				//Campos a desabilitar
				var fields = Ext.ComponentQuery.query('form field',activeTab)
				
				//Desabilita Campos
				Ext.each(fields,function(f){f.setReadOnly(true)})
				
				//Seta Botão Confirma: 1 - Visualizar
				Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('read')});
				
			}			
		}
	},
	
	onManufacturerBtnEditClick: function(){
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 2 - Alterar
			activeTab = activateTab(2, row.get('id'), 'equipmentmanufacturerform', null);
			
			if(activeTab){
				
				//Retorna Form
				var form = Ext.ComponentQuery.query('form',activeTab)[0].getForm();
				
				//Carrega registro no form
				form.loadRecord(row);
				
				//Seta Botão Confirma: Alterar
				Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('update')});
			}
		}
	},
	
	onManufacturerBtnAddClick: function(){
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 3 - Incluir
			activeTab = activateTab(3, row.get('id'), 'equipmentmanufacturerform', null);
			
			if(activeTab){
		
				//Seta Botão Confirma: Incluir
				Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('create')});
			}
		}

	},
	
	onManufacturerBtnDeleteClick: function(){
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 4 - Excluir
			activeTab = activateTab(4, row.get('id'), 'equipmentmanufacturerform', null);
			
			if(activeTab){
			
				//Retorna Form
				var form = Ext.ComponentQuery.query('form',activeTab)[0].getForm();
				
				//Carrega registro no form
				form.loadRecord(row);
				
				//Campos a desabilitar
				var fields = Ext.ComponentQuery.query('form field',activeTab)
				
				//Desabilita Campos
				Ext.each(fields,function(f){f.setReadOnly(true)})
				
				//Seta Botão Confirma: Exlcuir
				Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('remove')});
			}
		}
	},
	
	onManufacturerBtnSubmitAdd: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getEquipmentManufacturerStore(),						//Store
			record		= Ext.create('Sam.model.EquipmentManufacturer');			//Registro
		
		
		
		if(form.isValid()){
			
			//Carrega dados do Formulario no registro
			record.set(values);
			
			//Adiciona registro na store
			store.add(record);
			
			//Sincroniza e Atualiza Store
			syncStore(store, 'equipmentmanufacturergrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onManufacturerBtnSubmitEdit: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getEquipmentManufacturerStore(),						//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			//Carrega dados do formulario na Store
			store.findRecord('id',record.get('id')).set(values);
			
			//Sincroniza e Atualiza Store
			syncStore(store, 'equipmentmanufacturergrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onManufacturerBtnSubmitDelete: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getEquipmentManufacturerStore(),						//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
		
			//Apaga registro da Store
			store.remove(record);
			
			//Sincroniza e Atualiza Store
			syncStore(store, 'equipmentmanufacturergrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onModelBtnShowClick: function() {
		console.log('Visualiza Modelo');
	}
	
});

function syncStore(store, component){
	
	//Sincroniza Store
	store.sync({
        success: function(batch, options)
        {
        	
        	//Atualiza stores e views
        	Ext.each(Ext.ComponentQuery.query('#'+component),function(f){
        		f.getStore().reload();
        		f.getView().refresh();
        	});
        	
        },
        failure: function(batch, options)
        {
            console.log("failed...");
        }
    });
}

function activateTab(action, id, xtype, uTitle){
	
	//Variaveis
	var title, tabId, activeTab;
	
	//Aba Objecto Pai
	var mainPanel = Ext.getCmp('viewportpanel');
	
	switch(action){
		
		//Visualizar
		case 1:
			title = 'Visualizar Cod: ' + id;
			tabId = 'show-' + xtype + '-' + id;
			break;
		
		//Incluir
		case 2:
			title = 'Incluir Novo Registro';
			tabId = 'add-' + xtype + '-' + id;
			break;
		
		//Alterar
		case 3:
			title = 'Alterar Cod: ' + id;
			tabId = 'edit-' + xtype + '-' + id;
			break;
		
		//Excluir
		case 4:
			title = 'Excluir Cod: ' + id;
			tabId = 'delete-' + xtype + '-' + id;
			break;
		default:
			title = uTitle;
	
	}
	
	var newTab = mainPanel.items.findBy(
			function(tab){
				return tab.id === tabId;
			});
	
	if (!newTab) {
		newTab = mainPanel.add({
			id: tabId,
			xtype: xtype,
			closable: true,
			iconCls: 'magnifier-zoom',
			title: title
		});
	}
	
	//Seta Aba como ativa
	mainPanel.setActiveTab(newTab);
	
	activeTab = mainPanel.getActiveTab();
	
	return activeTab;
	
}
