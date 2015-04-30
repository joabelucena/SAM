Ext.define('Sam.controller.Equipment', {
	extend: 'Ext.app.Controller',
	
	
	stores: ['EquipmentManufacturer',
		        'EquipmentCounter',
		     	'Equipment',
		     	'EquipmentModel',
		     	'EquipmentType',
		     	'EquipmentProtocol'],
	
	models: ['EquipmentManufacturer',
		        'EquipmentCounter',
		     	'Equipment',
		     	'EquipmentModel',
		     	'EquipmentType',
		     	'EquipmentProtocol'],
	
	views: ['Sam.view.equipment.EquipmentsGrid',
	        'Sam.view.equipment.manufacturer.ManufacturerGrid',
	        'Sam.view.equipment.manufacturer.ManufacturerForm',
	        'Sam.view.equipment.protocol.ProtocolGrid',
	        'Sam.view.equipment.protocol.ProtocolForm',
	        'Sam.view.equipment.counter.CounterGrid',
	        'Sam.view.equipment.counter.CounterForm',
	        'Sam.view.equipment.model.ModelGrid',
	        'Sam.view.equipment.model.ModelForm',
	        'Sam.view.equipment.type.TypeGrid',
	        'Sam.view.equipment.type.TypeForm'
	        ],

	init: function() {
		
		this.control({
			/*
			 * Grid Listeners
			 */
			'equipmentmanufacturergrid': {
				itemdblclick: this.onManufacturerBtnShowClick
			},
			
			'equipmentscountergrid': {
				itemdblclick: function(){}
			},
			
			'equipmentsmodelgrid': {
				itemdblclick: function(){}
			},
			
			'equipmentstypegrid': {
				itemdblclick: function(){}
			},
			
			'equipmentsmodelgrid': {
				itemdblclick: function(){}
			},
			
			/* Buttons Listeners: Manufacturer
			 *  
			 */
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
			
			
			/* Buttons Listeners: Type
			 * 
			 */
			'#equipmenttypeform toolbar #btnSubmit' :{
				create: this.onTypeBtnSubmitAdd,
				read:   function(){Ext.getCmp('viewportpanel').getActiveTab().close()},
				update: this.onTypeBtnSubmitEdit,
				remove: this.onTypeBtnSubmitDelete,
				
			},
			
			'#equipmenttypeform toolbar #btnDiscard' :{
				click:   function(){Ext.getCmp('viewportpanel').getActiveTab().close()},
			},
			
			'#equipmenttypegrid toolbar #btnShow' :{
				click: this.onTypeBtnShowClick
			},
			
			'#equipmenttypegrid toolbar #btnEdit' :{
				click: this.onTypeBtnEditClick
			},
			
			'#equipmenttypegrid toolbar #btnAdd' :{
				click: this.onTypeBtnAddClick
			},
			
			'#equipmenttypegrid toolbar #btnDelete' :{
				click: this.onTypeBtnDeleteClick
			},
			
			/* Buttons Listeners: Counter
			 * 
			 */
			'#equipmentcounterform toolbar #btnSubmit' :{
				create: this.onCounterBtnSubmitAdd,
				read:   function(){Ext.getCmp('viewportpanel').getActiveTab().close()},
				update: this.onCounterBtnSubmitEdit,
				remove: this.onCounterBtnSubmitDelete,
				
			},
			
			'#equipmentcounterform toolbar #btnDiscard' :{
				click:   function(){Ext.getCmp('viewportpanel').getActiveTab().close()},
			},
			
			'#equipmentcountergrid toolbar #btnShow' :{
				click: this.onCounterBtnShowClick
			},
			
			'#equipmentcountergrid toolbar #btnEdit' :{
				click: this.onCounterBtnEditClick
			},
			
			'#equipmentcountergrid toolbar #btnAdd' :{
				click: this.onCounterBtnAddClick
			},
			
			'#equipmentcountergrid toolbar #btnDelete' :{
				click: this.onCounterBtnDeleteClick
			},
			
			/* Buttons Listeners: Model
			 * 
			 */
			'#equipmentmodelform #prot_id' :{
				click:   this.onModelTrgProtClick
			},
			
			'#lookup #submit' :{
				click: this.onLookupSubmitClick
			},
			
			'#equipmentmodelform toolbar #btnSubmit' :{
				create: this.onModelBtnSubmitAdd,
				read:   function(){Ext.getCmp('viewportpanel').getActiveTab().close()},
				update: this.onModelBtnSubmitEdit,
				remove: this.onModelBtnSubmitDelete,
				
			},
			
			'#equipmentmodelform toolbar #btnDiscard' :{
				click:   function(){Ext.getCmp('viewportpanel').getActiveTab().close()},
			},
			
			'#equipmentmodelgrid toolbar #btnShow' :{
				click: this.onModelBtnShowClick
			},
			
			'#equipmentmodelgrid toolbar #btnEdit' :{
				click: this.onModelBtnEditClick
			},
			
			'#equipmentmodelgrid toolbar #btnAdd' :{
				click: this.onModelBtnAddClick
			},
			
			'#equipmentmodelgrid toolbar #btnDelete' :{
				click: this.onModelBtnDeleteClick
			},
			

		});
	},
	
	
	/*********** Begin Manufacturer Controlling ***********/
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
			
			//Cria Aba: 3 - Alterar
			activeTab = activateTab(3, row.get('id'), 'equipmentmanufacturerform', null);
			
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

			
		//Cria Aba: 2 - Incluir
		var activeTab = activateTab(2, null, 'equipmentmanufacturerform', null);
		
		if(activeTab){
	
			//Seta Botão Confirma: Incluir
			Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('create')});
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
	
	
	/*********** End Of Manufacturer Controlling ***********/
	
	/*********** Begin Type Controlling ***********/
	onTypeBtnShowClick: function() {
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 1 - Visualizar
			activeTab = activateTab(1, row.get('id'), 'equipmenttypeform', null);
			
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
	
	onTypeBtnEditClick: function(){
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 3 - Alterar
			activeTab = activateTab(3, row.get('id'), 'equipmenttypeform', null);
			
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
	
	onTypeBtnAddClick: function(){
		
		//Cria Aba: 2 - Incluir
		var activeTab = activateTab(2, null, 'equipmenttypeform', null);
		
		if(activeTab){
	
			//Seta Botão Confirma: Incluir
			Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('create')});
		}

	},
	
	onTypeBtnDeleteClick: function(){
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 4 - Excluir
			activeTab = activateTab(4, row.get('id'), 'equipmenttypeform', null);
			
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
	
	onTypeBtnSubmitAdd: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getEquipmentTypeStore(),								//Store
			record		= Ext.create('Sam.model.EquipmentType');					//Registro
		
		
		
		if(form.isValid()){
			
			//Carrega dados do Formulario no registro
			record.set(values);
			
			//Adiciona registro na store
			store.add(record);
			
			//Sincroniza e Atualiza Store
			syncStore(store, 'equipmenttypegrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onTypeBtnSubmitEdit: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getEquipmentTypeStore(),								//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			//Carrega dados do formulario na Store
			store.findRecord('id',record.get('id')).set(values);
			
			//Sincroniza e Atualiza Store
			syncStore(store, 'equipmenttypegrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onTypeBtnSubmitDelete: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getEquipmentTypeStore(),								//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
		
			//Apaga registro da Store
			store.remove(record);
			
			//Sincroniza e Atualiza Store
			syncStore(store, 'equipmenttypegrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	/*********** End Type Controlling ***********/
	
	
	/*********** Begin Counter Controlling ***********/
	onCounterBtnShowClick: function() {
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 1 - Visualizar
			activeTab = activateTab(1, row.get('id'), 'equipmentcounterform', null);
			
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
	
	onCounterBtnEditClick: function(){
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 3 - Alterar
			activeTab = activateTab(3, row.get('id'), 'equipmentcounterform', null);
			
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
	
	onCounterBtnAddClick: function(){
		
		//Cria Aba: 2 - Incluir
		var activeTab = activateTab(2, null, 'equipmentcounterform', null);
		
		if(activeTab){
	
			//Seta Botão Confirma: Incluir
			Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('create')});
		}

	},
	
	onCounterBtnDeleteClick: function(){
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 4 - Excluir
			activeTab = activateTab(4, row.get('id'), 'equipmentcounterform', null);
			
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
	
	onCounterBtnSubmitAdd: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getEquipmentCounterStore(),						//Store
			record		= Ext.create('Sam.model.EquipmentCounter');			//Registro
		
		
		
		if(form.isValid()){
			
			//Carrega dados do Formulario no registro
			record.set(values);
			
			//Adiciona registro na store
			store.add(record);
			
			//Sincroniza e Atualiza Store
			syncStore(store, 'equipmentcountergrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onCounterBtnSubmitEdit: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getEquipmentCounterStore(),						//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			//Carrega dados do formulario na Store
			store.findRecord('id',record.get('id')).set(values);
			
			//Sincroniza e Atualiza Store
			syncStore(store, 'equipmentcountergrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onCounterBtnSubmitDelete: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getEquipmentCounterStore(),						//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
		
			//Apaga registro da Store
			store.remove(record);
			
			//Sincroniza e Atualiza Store
			syncStore(store, 'equipmentcountergrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	/*********** End Counter Controlling ***********/
	
	/*********** Begin Protocol Controlling ***********/
	onProtocolBtnShowClick: function() {
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 1 - Visualizar
			activeTab = activateTab(1, row.get('id'), 'equipmentprotocolform', null);
			
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
	
	onProtocolBtnEditClick: function(){
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 3 - Alterar
			activeTab = activateTab(3, row.get('id'), 'equipmentprotocolform', null);
			
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
	
	onProtocolBtnAddClick: function(){
		
		//Cria Aba: 2 - Incluir
		var activeTab = activateTab(2, null, 'equipmentprotocolform', null);
		
		if(activeTab){
	
			//Seta Botão Confirma: Incluir
			Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('create')});
		}

	},
	
	onProtocolBtnDeleteClick: function(){
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 4 - Excluir
			activeTab = activateTab(4, row.get('id'), 'equipmentprotocolform', null);
			
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
	
	onProtocolBtnSubmitAdd: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getEquipmentProtocolStore(),						//Store
			record		= Ext.create('Sam.model.EquipmentProtocol');			//Registro
		
		
		
		if(form.isValid()){
			
			//Carrega dados do Formulario no registro
			record.set(values);
			
			//Adiciona registro na store
			store.add(record);
			
			//Sincroniza e Atualiza Store
			syncStore(store, 'equipmentprotocolgrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onProtocolBtnSubmitEdit: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getEquipmentProtocolStore(),						//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			//Carrega dados do formulario na Store
			store.findRecord('id',record.get('id')).set(values);
			
			//Sincroniza e Atualiza Store
			syncStore(store, 'equipmentprotocolgrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onProtocolBtnSubmitDelete: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getEquipmentProtocolStore(),						//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
		
			//Apaga registro da Store
			store.remove(record);
			
			//Sincroniza e Atualiza Store
			syncStore(store, 'equipmentprotocolgrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	/*********** End Protocol Controlling ***********/
	
	/*********** Begin Model Controlling ***********/
	onModelTrgProtClick: function(){
		var popup = Ext.create('Sam.view.components.PopUp');
		var grid = Ext.create('Sam.view.equipment.protocol.ProtocolGrid');
		
		var buttons = Ext.ComponentQuery.query('toolbar',grid)[0];
		
		//Remove Botoes
		grid.remove(Ext.ComponentQuery.query('toolbar',grid)[0], true);
		
		popup.setTitle('Selecionar Protocolo');
		popup.add(grid);
		popup.show();
	},
	
	onLookupSubmitClick: function(){
		
		var row = Ext.ComponentQuery.query('#lookup grid')[0].getSelection()[0];
		
		var activeTab = Ext.getCmp('viewportpanel').getActiveTab();
		
		if(row){
			
			fld = Ext.ComponentQuery.query( 'form #fldProtocol',activeTab)[0];
			
			fld.setData(row.getData());
		}
		
	},
	
	onModelBtnShowClick: function() {
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 1 - Visualizar
			activeTab = activateTab(1, row.get('id'), 'equipmentmodelform', null);
			
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
	
	onModelBtnEditClick: function(){
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 3 - Alterar
			activeTab = activateTab(3, row.get('id'), 'equipmentmodelform', null);
			
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
	
	onModelBtnAddClick: function(){
		
		//Cria Aba: 2 - Incluir
		var activeTab = activateTab(2, null, 'equipmentmodelform', null);
		
		if(activeTab){
	
			//Seta Botão Confirma: Incluir
			Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('create')});
		}

	},
	
	onModelBtnDeleteClick: function(){
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 4 - Excluir
			activeTab = activateTab(4, row.get('id'), 'equipmentmodelform', null);
			
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
	
	onModelBtnSubmitAdd: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getEquipmentModelStore(),						//Store
			record		= Ext.create('Sam.model.EquipmentModel');			//Registro
		
		
		
		if(form.isValid()){
			
			//Carrega dados do Formulario no registro
			record.set(values);
			
			//Adiciona registro na store
			store.add(record);
			
			//Sincroniza e Atualiza Store
			syncStore(store, 'equipmentmodelgrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onModelBtnSubmitEdit: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getEquipmentModelStore(),						//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			//Carrega dados do formulario na Store
			store.findRecord('id',record.get('id')).set(values);
			
			//Sincroniza e Atualiza Store
			syncStore(store, 'equipmentmodelgrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onModelBtnSubmitDelete: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getEquipmentModelStore(),						//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
		
			//Apaga registro da Store
			store.remove(record);
			
			//Sincroniza e Atualiza Store
			syncStore(store, 'equipmentmodelgrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	/*********** End Model Controlling ***********/
	

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
			tabId = 'add-' + xtype
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
