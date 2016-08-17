Ext.define('Sam.controller.Equipment', {
	extend: 'Ext.app.Controller',
	
	
	stores: ['EquipmentManufacturer',
		     	'Equipment',
		     	'EquipmentModel',
		     	'EquipmentType',
		     	'EquipmentProtocol',
		     	'OperationalState',
		     	'System',
		     	'OID'
		     ],
	
	models: ['EquipmentManufacturer',
		     	'Equipment',
		     	'EquipmentModel',
		     	'EquipmentType',
		     	'EquipmentProtocol',
		     	'OperationalState',
		     	'System',
		     	'Document',
		     	'OID'],
	
	views: ['Sam.view.equipment.EquipmentsGrid',
	        'Sam.view.equipment.EquipmentsForm',
	        'Sam.view.equipment.manufacturer.ManufacturerGrid',
	        'Sam.view.equipment.manufacturer.ManufacturerForm',
	        'Sam.view.equipment.protocol.ProtocolGrid',
	        'Sam.view.equipment.protocol.ProtocolForm',
	        'Sam.view.equipment.model.ModelGrid',
	        'Sam.view.equipment.model.ModelForm',
	        'Sam.view.equipment.type.TypeGrid',
	        'Sam.view.equipment.type.TypeForm',
	        'Sam.view.equipment.operationalState.OperationalStateForm',
	        'Sam.view.equipment.operationalState.OperationalStateGrid',
	        'Sam.view.equipment.system.SystemGrid',
	        'Sam.view.equipment.system.SystemForm'],
	        
    refs: [{ ref: 'lookup', selector: 'popup'   }],
       
    xUtils: Ext.create('Sam.lib.Util'),

	init: function() {
		
		this.control({
			/* Buttons Listeners: Manufacturer
			 *  
			 */
			'#equipmentmanufacturerform toolbar #btnSubmit' :{
				create: this.onManufacturerBtnSubmitAdd,
				read:   function(){Ext.getCmp('viewportpanel').getActiveTab().close()},
				update: this.onManufacturerBtnSubmitEdit,
				remove: this.onManufacturerBtnSubmitDelete,
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
			
			/* Buttons Listeners: Model
			 * 
			 */
			'#equipmentmodelform #prot_id' :{
				click: this.onModelTrgProtClick
			},
			
			'#equipmentmodelform_protocol #submit' :{
				click: this.onLookupSubmitClick
			},
			
			'#equipmentmodelform toolbar #btnSubmit' :{
				create: this.onModelBtnSubmitAdd,
				read:   function(){Ext.getCmp('viewportpanel').getActiveTab().close()},
				update: this.onModelBtnSubmitEdit,
				remove: this.onModelBtnSubmitDelete,
				
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
			
			/* Buttons Listeners: System
			 * 
			 */
			'#systemform #prot_id' :{
				click: this.onSystemTrgProtClick
			},
			
			'#systemform_protocol #submit' :{
				click: this.onLookupSubmitClick
			},
			
			'#systemform toolbar #btnSubmit' :{
				create: this.onSystemBtnSubmitAdd,
				read:   function(){Ext.getCmp('viewportpanel').getActiveTab().close()},
				update: this.onSystemBtnSubmitEdit,
				remove: this.onSystemBtnSubmitDelete,
				
			},
			
			'#systemgrid toolbar #btnShow' :{
				click: this.onSystemBtnShowClick
			},
			
			'#systemgrid toolbar #btnEdit' :{
				click: this.onSystemBtnEditClick
			},
			
			'#systemgrid toolbar #btnAdd' :{
				click: this.onSystemBtnAddClick
			},
			
			'#systemgrid toolbar #btnDelete' :{
				click: this.onSystemBtnDeleteClick
			},
			
			/* Buttons Listeners: Protocol
			 * 
			 */
			
			'#equipmentprotocolform toolbar #btnSubmit' :{
				create: this.onProtocolBtnSubmitAdd,
				read:   function(){Ext.getCmp('viewportpanel').getActiveTab().close()},
				update: this.onProtocolBtnSubmitEdit,
				remove: this.onProtocolBtnSubmitDelete,
				
			},
			
			'#equipmentprotocolgrid toolbar #btnShow' :{
				click: this.onProtocolBtnShowClick
			},
			
			'#equipmentprotocolgrid toolbar #btnEdit' :{
				click: this.onProtocolBtnEditClick
			},
			
			'#equipmentprotocolgrid toolbar #btnAdd' :{
				click: this.onProtocolBtnAddClick
			},
			
			'#equipmentprotocolgrid toolbar #btnDelete' :{
				click: this.onProtocolBtnDeleteClick
			},
			
			
			/* Buttons Listeners: Operational State
			 * 
			 */
			'#operationalstateform #model_id' :{
				click:   this.onOperationalStateTrgModelClick
			},
			
			'#operationalstateform_model #submit' :{
				click: this.onOperationalStateModelSubmitClick
			},
			
			'#operationalstateform toolbar #btnSubmit' :{
				create: this.onOperationalStateBtnSubmitAdd,
				read:   function(){Ext.getCmp('viewportpanel').getActiveTab().close()},
				update: this.onOperationalStateBtnSubmitEdit,
				remove: this.onOperationalStateBtnSubmitDelete,
				
			},
			
			'#operationalstategrid toolbar #btnShow' :{
				click: this.onOperationalStateBtnShowClick
			},
			
			'#operationalstategrid toolbar #btnEdit' :{
				click: this.onOperationalStateBtnEditClick
			},
			
			'#operationalstategrid toolbar #btnAdd' :{
				click: this.onOperationalStateBtnAddClick
			},
			
			'#operationalstategrid toolbar #btnDelete' :{
				click: this.onOperationalStateBtnDeleteClick
			},
			
			/* Buttons Listeners: Equipment
			 * 
			 */
			'#equipmentsform toolbar #btnSubmit' :{
				create: this.onEquipmentBtnSubmitAdd,
				read:   function(){Ext.getCmp('viewportpanel').getActiveTab().close()},
				update: this.onEquipmentBtnSubmitEdit,
				remove: this.onEquipmentBtnSubmitDelete,
			},
			
			'#equipmentsgrid toolbar #btnShow' :{
				click: this.onEquipmentBtnShowClick
			},
			
			'#equipmentsgrid toolbar #btnEdit' :{
				click: this.onEquipmentBtnEditClick
			},
			
			'#equipmentsgrid toolbar #btnAdd' :{
				click: this.onEquipmentBtnAddClick
			},
			
			'#equipmentsgrid toolbar #btnDelete' :{
				click: this.onEquipmentBtnDeleteClick
			},
		});
	},
	
	/*********** Begin Manufacturer Controlling ***********/
	onManufacturerBtnShowClick: function() {
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0],
			store = Ext.getCmp('viewportpanel').getActiveTab().getStore();
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 1 - Visualizar
			activeTab = this.xUtils.activateTab(1, row.get('id'), 'equipmentmanufacturerform', null, true, store);
			
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
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0],
			store = Ext.getCmp('viewportpanel').getActiveTab().getStore();
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 3 - Alterar
			activeTab = this.xUtils.activateTab(3, row.get('id'), 'equipmentmanufacturerform', null, true, store);
			
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
		
		var store = Ext.getCmp('viewportpanel').getActiveTab().getStore();
			
		//Cria Aba: 2 - Incluir
		var activeTab = this.xUtils.activateTab(2, null, 'equipmentmanufacturerform', null, true, store);
		
		if(activeTab){
	
			//Seta Botão Confirma: Incluir
			Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('create')});
		}


	},
	
	onManufacturerBtnDeleteClick: function(){
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0],
			store = Ext.getCmp('viewportpanel').getActiveTab().getStore();
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 4 - Excluir
			activeTab = this.xUtils.activateTab(4, row.get('id'), 'equipmentmanufacturerform', null, true, store);
			
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
			store		= activeTab.xStore,											//Store
			record		= Ext.create('Sam.model.EquipmentManufacturer');			//Registro
		
		if(form.isValid()){
			
			//Carrega dados do Formulario no registro
			record.set(values);
			
			//Adiciona registro na store
			store.add(record);
			
			//Sincroniza e Atualiza Store
			this.xUtils.sync(store, '#equipmentmanufacturergrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onManufacturerBtnSubmitEdit: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= activeTab.xStore,											//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			//Carrega dados do formulario na Store
			store.findRecord('id',record.get('id')).set(values);
			
			//Sincroniza e Atualiza Store
			this.xUtils.sync(store, '#equipmentmanufacturergrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onManufacturerBtnSubmitDelete: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= activeTab.xStore,											//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			
			//Apaga registro da Store
			store.remove(record);
			
			//Sincroniza e Atualiza Store
			this.xUtils.sync(store, '#equipmentmanufacturergrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	
	/*********** End Of Manufacturer Controlling ***********/
	
	/*********** Begin Type Controlling ***********/
	onTypeBtnShowClick: function() {
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0],
			store = Ext.getCmp('viewportpanel').getActiveTab().getStore();
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 1 - Visualizar
			activeTab = this.xUtils.activateTab(1, row.get('id'), 'equipmenttypeform', null, true, store);
			
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
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0],
			store = Ext.getCmp('viewportpanel').getActiveTab().getStore();
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 3 - Alterar
			activeTab = this.xUtils.activateTab(3, row.get('id'), 'equipmenttypeform', null, true, store);
			
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
		
		var store = Ext.getCmp('viewportpanel').getActiveTab().getStore();
		
		//Cria Aba: 2 - Incluir
		var activeTab = this.xUtils.activateTab(2, null, 'equipmenttypeform', null, true, store);
		
		if(activeTab){
	
			//Seta Botão Confirma: Incluir
			Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('create')});
		}

	},
	
	onTypeBtnDeleteClick: function(){
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0],
			store = Ext.getCmp('viewportpanel').getActiveTab().getStore();
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 4 - Excluir
			activeTab = this.xUtils.activateTab(4, row.get('id'), 'equipmenttypeform', null, true, store);
			
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
			store		= activeTab.xStore,											//Store
			record		= Ext.create('Sam.model.EquipmentType');					//Registro
		
		
		
		if(form.isValid()){
			
			//Carrega dados do Formulario no registro
			record.set(values);
			
			//Adiciona registro na store
			store.add(record);
			
			//Sincroniza e Atualiza Store
			this.xUtils.sync(store, '#equipmenttypegrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onTypeBtnSubmitEdit: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= activeTab.xStore,											//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			//Carrega dados do formulario na Store
			store.findRecord('id',record.get('id')).set(values);
			
			//Sincroniza e Atualiza Store
			this.xUtils.sync(store, '#equipmenttypegrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onTypeBtnSubmitDelete: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= activeTab.xStore,											//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
		
			//Apaga registro da Store
			store.remove(record);
			
			//Sincroniza e Atualiza Store
			this.xUtils.sync(store, '#equipmenttypegrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	/*********** End Type Controlling ***********/
	
	/*********** Begin Protocol Controlling ***********/
	onProtocolBtnShowClick: function() {
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0],
			store = Ext.getCmp('viewportpanel').getActiveTab().getStore();
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 1 - Visualizar
			activeTab = this.xUtils.activateTab(1, row.get('id'), 'equipmentprotocolform', null, true, store);
			
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
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0],
			store = Ext.getCmp('viewportpanel').getActiveTab().getStore();
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 3 - Alterar
			activeTab = this.xUtils.activateTab(3, row.get('id'), 'equipmentprotocolform', null, true, store);
			
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
		
		var store = Ext.getCmp('viewportpanel').getActiveTab().getStore();
		
		//Cria Aba: 2 - Incluir
		var activeTab = this.xUtils.activateTab(2, null, 'equipmentprotocolform', null, true, store);
		
		if(activeTab){
	
			//Seta Botão Confirma: Incluir
			Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('create')});
		}

	},
	
	onProtocolBtnDeleteClick: function(){

		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0],
			store = Ext.getCmp('viewportpanel').getActiveTab().getStore();
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 4 - Excluir
			activeTab = this.xUtils.activateTab(4, row.get('id'), 'equipmentprotocolform', null, true, store);
			
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
			store		= activeTab.xStore											//Store
			record		= Ext.create('Sam.model.EquipmentProtocol');				//Registro
		
		
		
		if(form.isValid()){
			
			//Carrega dados do Formulario no registro
			record.set(values);
			
			//Adiciona registro na store
			store.add(record);
			
			//Sincroniza e Atualiza Store
			this.xUtils.sync(store, '#equipmentprotocolgrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onProtocolBtnSubmitEdit: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= activeTab.xStore											//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			//Carrega dados do formulario na Store
			store.findRecord('id',record.get('id')).set(values);
			
			//Sincroniza e Atualiza Store
			this.xUtils.sync(store, '#equipmentprotocolgrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onProtocolBtnSubmitDelete: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= activeTab.xStore											//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
		
			//Apaga registro da Store
			store.remove(record);
			
			//Sincroniza e Atualiza Store
			this.xUtils.sync(store, '#equipmentprotocolgrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	/*********** End Protocol Controlling ***********/
	
	/*********** Begin Model Controlling ***********/
	onModelTrgProtClick: function(){
		var popup = Ext.create('Sam.view.components.PopUp',{itemId: 'equipmentmodelform_protocol'});
		var grid = Ext.create('Sam.view.equipment.protocol.ProtocolGrid');
		
		var buttons = Ext.ComponentQuery.query('toolbar',grid)[0];
		
		//Remove Botoes
		grid.remove(Ext.ComponentQuery.query('toolbar',grid)[0], true);
		
		popup.setTitle('Selecionar Protocolo');
		popup.add(grid);
		popup.show();
	},
	
	onLookupSubmitClick: function(){
		
		var row = this.getLookup().down('grid').getSelection()[0];
		
		var activeTab = Ext.getCmp('viewportpanel').getActiveTab();
		
		if(row){
			
			fld = Ext.ComponentQuery.query( 'form #fldProtocol',activeTab)[0];
			
			Ext.ComponentQuery.query('#prot_id',fld)[0].setValue(row.get('id'));
			Ext.ComponentQuery.query('#prot_desc',fld)[0].setValue(row.get('desc'));
			
			this.getLookup().close();
		}
		
	},
	
	onModelBtnShowClick: function() {
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0],
			store = Ext.getCmp('viewportpanel').getActiveTab().getStore();
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 1 - Visualizar
			activeTab = this.xUtils.activateTab(1, row.get('id'), 'equipmentmodelform', null, true, store);
			
			if(activeTab){
			
				//Retorna Form
				var form = Ext.ComponentQuery.query('form',activeTab)[0].getForm();
				
				//Carrega registro no form
				form.loadRecord(row);
				
				//Carrega Store
				activeTab.down('#documentsgrid').setStore(row.documents());
				activeTab.down('#oidgrid').setStore(row.oids());
				
				activeTab.down('#oidgrid').removePlugin(activeTab.down('#oidgrid').findPlugin('cellediting'));
				activeTab.down('#oidgrid').headerCt.items.getAt(activeTab.down('#oidgrid').headerCt.items.findIndex('xtype','actioncolumn')).setVisible(false);
				
				
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
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0],
			store = Ext.getCmp('viewportpanel').getActiveTab().getStore();
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 3 - Alterar
			activeTab = this.xUtils.activateTab(3, row.get('id'), 'equipmentmodelform', null, true, store);
			
			if(activeTab){
				
				//Retorna Form
				var form = Ext.ComponentQuery.query('form',activeTab)[0].getForm();
				
				//Carrega registro no form
				form.loadRecord(row);
				
				//Carrega Store
				activeTab.down('grid').setStore(row.documents());
				activeTab.down('#oidgrid').setStore(row.oids());
				
				//Habilita envio de Documentos
				Ext.ComponentQuery.query('#btnAddDoc',activeTab)[0].setDisabled(false);
				Ext.ComponentQuery.query('#btnAddOID',activeTab)[0].setDisabled(false);
				
				//Seta Botão Confirma: Alterar
				Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('update')});
			}
		}
	},
	
	onModelBtnAddClick: function(){
		
		var store = Ext.getCmp('viewportpanel').getActiveTab().getStore();
		
		//Cria Aba: 2 - Incluir
		var activeTab = this.xUtils.activateTab(2, null, 'equipmentmodelform', null, true, store);
		
		if(activeTab){
			
			activeTab.down('#oidgrid').removePlugin(activeTab.down('#oidgrid').findPlugin('cellediting'));
			activeTab.down('#oidgrid').headerCt.items.getAt(activeTab.down('#oidgrid').headerCt.items.findIndex('xtype','actioncolumn')).setVisible(false);	
			
			//Seta Botão Confirma: Incluir
			Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('create')});
		}

	},
	
	onModelBtnDeleteClick: function(){
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0],
			store = Ext.getCmp('viewportpanel').getActiveTab().getStore();
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 4 - Excluir
			activeTab = this.xUtils.activateTab(4, row.get('id'), 'equipmentmodelform', null, true, store);
			
			if(activeTab){
			
				//Retorna Form
				var form = Ext.ComponentQuery.query('form',activeTab)[0].getForm();
				
				//Carrega registro no form
				form.loadRecord(row);
				
				//Carrega Store
				activeTab.down('grid').setStore(row.documents());
				activeTab.down('#oidgrid').removePlugin(activeTab.down('#oidgrid').findPlugin('cellediting'));
				activeTab.down('#oidgrid').headerCt.items.getAt(activeTab.down('#oidgrid').headerCt.items.findIndex('xtype','actioncolumn')).setVisible(false);
				
				
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
			store		= activeTab.xStore											//Store
			record		= Ext.create('Sam.model.EquipmentModel');					//Registro
		
		if(form.isValid()){
			
			
			//Carrega dados do Formulario no registro
			record.set(values);
			
			//Carrega Protocolo
			record.set({protocol: Ext.create('Sam.model.EquipmentProtocol',{id: values.prot_id, desc: values.prot_desc})})
			
			//Adiciona registro na store
			store.add(record);
			
			//Sincroniza e Atualiza Store
			this.xUtils.sync(store, '#equipmentmodelgrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onModelBtnSubmitEdit: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= activeTab.xStore											//Store
			updated		= form.getRecord(),											//Dados atualizados
			record		= store.findRecord('id',updated.get('id'));					//Registro
		

		//Validação form 
		lValid = form.isValid();
		
		//Validação grid equipamentos
		Ext.each(updated.oids().data.items,function(item){
			lValid = lValid && item.isValid();
		});
		
		if(!lValid){
			Ext.MessageBox.show({
		        title: 'SAM | Info',
		        msg:  'Existem campos que não foram preenchidos. Preencha todos os campos corretamente',
		        buttons: Ext.MessageBox.OK,
		        icon: Ext.MessageBox.WARNING
			});
		}else{
			
			form.updateRecord();

			//Comando especial para garantir a
			//integridade e consistencia dos dados
			record.set({pogAttr: '1'});
			
			//Carrega dados do formulario na Store
			record.set(updated.getData());
			
			//Carrega dados do formulario na Store
			record.setProtocol(updated.getProtocol());
			
			//Carrega OIDs
			record.oids().setData(updated.oids().getData());
			
			//Sincroniza e Atualiza Store
			this.xUtils.sync(store, '#equipmentmodelgrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onModelBtnSubmitDelete: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= activeTab.xStore											//Store
			record		= form.getRecord();											//Registro
		
		
		//Apaga registro da Store
		store.remove(record);
		
		//Sincroniza e Atualiza Store
		this.xUtils.sync(store, '#equipmentmodelgrid');
		
		//Fecha Aba
		activeTab.close();
	},
	
	/*********** End Model Controlling ***********/
	
	/*********** Begin System Controlling ***********/
	onSystemBtnShowClick: function() {
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0],
			store = Ext.getCmp('viewportpanel').getActiveTab().getStore();
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 1 - Visualizar
			activeTab = this.xUtils.activateTab(1, row.get('id'), 'systemform', null, false, store);
			
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
	
	onSystemBtnEditClick: function(){
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0],
			store = Ext.getCmp('viewportpanel').getActiveTab().getStore();
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 3 - Alterar
			activeTab = this.xUtils.activateTab(3, row.get('id'), 'systemform', null, true, store);
			
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
	
	onSystemBtnAddClick: function(){
		
		var store = Ext.getCmp('viewportpanel').getActiveTab().getStore();
		
		//Cria Aba: 2 - Incluir
		var activeTab = this.xUtils.activateTab(2, null, 'systemform', null, false, store);
		
		if(activeTab){
	
			//Seta Botão Confirma: Incluir
			Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('create')});
		}

	},
	
	onSystemBtnDeleteClick: function(){
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0],
			store = Ext.getCmp('viewportpanel').getActiveTab().getStore();
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 4 - Excluir
			activeTab = this.xUtils.activateTab(4, row.get('id'), 'systemform', null, false, store);
			
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
	
	onSystemBtnSubmitAdd: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= activeTab.xStore											//Store
			record		= Ext.create('Sam.model.System');							//Registro
		
		
		
		if(form.isValid()){
			
			//Carrega dados do Formulario no registro
			record.set(values);
			
			//Adiciona registro na store
			store.add(record);
			
			//Sincroniza e Atualiza Store
			this.xUtils.sync(store, '#systemgrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onSystemBtnSubmitEdit: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= activeTab.xStore											//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			//Carrega dados do formulario na Store
			store.findRecord('id',record.get('id')).set(values);
			
			//Sincroniza e Atualiza Store
			this.xUtils.sync(store, '#systemgrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onSystemBtnSubmitDelete: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= activeTab.xStore											//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
		
			//Apaga registro da Store
			store.remove(record);
			
			//Sincroniza e Atualiza Store
			this.xUtils.sync(store, '#systemgrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	/*********** End System Controlling ***********/
	
	/*********** Begin Protocol Controlling ***********/
	onProtocolBtnShowClick: function() {
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 1 - Visualizar
			activeTab = this.xUtils.activateTab(1, row.get('id'), 'equipmentprotocolform', null, true);
			
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
			activeTab = this.xUtils.activateTab(3, row.get('id'), 'equipmentprotocolform', null, true);
			
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
		var activeTab = this.xUtils.activateTab(2, null, 'equipmentprotocolform', null, true);
		
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
			activeTab = this.xUtils.activateTab(4, row.get('id'), 'equipmentprotocolform', null, true);
			
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
			this.xUtils.sync(store, '#equipmentprotocolgrid');
			
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
			this.xUtils.sync(store, '#equipmentprotocolgrid');
			
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
			this.xUtils.sync(store, '#equipmentprotocolgrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	/*********** End Protocol Controlling ***********/
	
	/*********** Begin Operational State Controlling ***********/
	
	onOperationalStateTrgModelClick: function(){
		var popup = Ext.create('Sam.view.components.PopUp',{itemId: 'operationalstateform_model'});
		var grid = Ext.create('Sam.view.equipment.model.ModelGrid');
		
		var buttons = Ext.ComponentQuery.query('toolbar',grid)[0];
		
		//Remove Botoes
		grid.remove(Ext.ComponentQuery.query('toolbar',grid)[0], true);
		
		popup.setTitle('Selecionar Modelo de Equipamento');
		popup.add(grid);
		popup.show();
	},
	
	onOperationalStateModelSubmitClick: function(){
		
		var row = this.getLookup().down('grid').getSelection()[0];
		
		var activeTab = Ext.getCmp('viewportpanel').getActiveTab();
		
		if(row){
						
			Ext.ComponentQuery.query('#model_id',activeTab)[0].setValue(row.get('id'));
			Ext.ComponentQuery.query('#model_desc',activeTab)[0].setValue(row.get('desc'));
			
			this.getLookup().close();
		}
		
	},
	
	onOperationalStateBtnShowClick: function() {
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0],
			store = Ext.getCmp('viewportpanel').getActiveTab().getStore();
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 1 - Visualizar
			activeTab = this.xUtils.activateTab(1, row.get('id'), 'operationalstateform', null, true, store);
			
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
	
	onOperationalStateBtnEditClick: function(){
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0],
			store = Ext.getCmp('viewportpanel').getActiveTab().getStore();
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 3 - Alterar
			activeTab = this.xUtils.activateTab(3, row.get('id'), 'operationalstateform', null, true, store);
			
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
	
	onOperationalStateBtnAddClick: function(){
		
		var store = Ext.getCmp('viewportpanel').getActiveTab().getStore();
		
		//Cria Aba: 2 - Incluir
		var activeTab = this.xUtils.activateTab(2, null, 'operationalstateform', null, false, store);
		
		if(activeTab){
	
			//Seta Botão Confirma: Incluir
			Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('create')});
		}

	},
	
	onOperationalStateBtnDeleteClick: function(){
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0],
			store = Ext.getCmp('viewportpanel').getActiveTab().getStore();
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 4 - Excluir
			activeTab = this.xUtils.activateTab(4, row.get('id'), 'operationalstateform', null, true, store);
			
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
	
	onOperationalStateBtnSubmitAdd: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= activeTab.xStore,											//Store
			record		= Ext.create('Sam.model.OperationalState');					//Registro
		
		if(form.isValid()){
			
			//Carrega dados do Formulario no registro
			record.set(values);
			
			//Carrega Modelo
			record.set({model: Ext.create('Sam.model.EquipmentModel',{id: values.model_id, desc: values.model_desc})});
			
			//Adiciona registro na store
			store.add(record);
			
			//Sincroniza e Atualiza Store
			this.xUtils.sync(store, '#operationalstategrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onOperationalStateBtnSubmitEdit: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= activeTab.xStore,											//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			//Carrega dados do formulario na Store
			store.findRecord('id',record.get('id')).set(values);
			
			//Carrega Objetos
			store.findRecord('id',record.get('id')).set({model: Ext.create('Sam.model.EquipmentModel',{id: values.model_id, desc: values.model_desc})})
			
			//Sincroniza e Atualiza Store
			this.xUtils.sync(store, '#operationalstategrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onOperationalStateBtnSubmitDelete: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= activeTab.xStore,											//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
		
			//Apaga registro da Store
			store.remove(record);
			
			//Sincroniza e Atualiza Store
			this.xUtils.sync(store, '#operationalstategrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	/*********** End Operational State Controlling ***********/
	
	/*********** Begin Equipment Controlling ***********/
	onEquipmentBtnShowClick: function() {
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0],
			store = Ext.getCmp('viewportpanel').getActiveTab().getStore();
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 1 - Visualizar
			activeTab = this.xUtils.activateTab(1, row.get('id'), 'equipmentsform', null, true, store);
			
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
	
	onEquipmentBtnEditClick: function(){
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0],
			store = Ext.getCmp('viewportpanel').getActiveTab().getStore();
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 3 - Alterar
			activeTab = this.xUtils.activateTab(3, row.get('id'), 'equipmentsform', null, true, store);
			
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
	
	onEquipmentBtnAddClick: function(){
		var store = Ext.getCmp('viewportpanel').getActiveTab().getStore();
		
		//Cria Aba: 2 - Incluir
		var activeTab = this.xUtils.activateTab(2, null, 'equipmentsform', null, false, store);
		
		if(activeTab){
	
			//Seta Botão Confirma: Incluir
			Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('create')});
		}

	},
	
	onEquipmentBtnDeleteClick: function(){

		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0],
			store = Ext.getCmp('viewportpanel').getActiveTab().getStore();
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 4 - Excluir
			activeTab = this.xUtils.activateTab(4, row.get('id'), 'equipmentsform', null, true, store);
			
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
	
	onEquipmentBtnSubmitAdd: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= activeTab.xStore,											//Store
			record		= Ext.create('Sam.model.Equipment');						//Registro

		
		if(form.isValid()){
			
			//Carrega dados do Formulario no registro
			record.set(values);
			
			//Carrega dados do Formulario na Store
			record.set({installDate:	Ext.Date.parse(values.installDate	, "d/m/Y"),
						type:			Ext.create('Sam.model.EquipmentType', {id: values.type_id}),
						manufacturer:	Ext.create('Sam.model.EquipmentManufacturer', {id: values.manufacturer_id}),
						model:			Ext.create('Sam.model.EquipmentModel', {id: values.model_id}),
						site:			Ext.create('Sam.model.Site', {id: values.site_id}),
						system:			Ext.create('Sam.model.System', {id: values.system_id, desc: values.system_desc})
			});
			
			//Adiciona registro na store
			store.add(record);
			
			//Sincroniza e Atualiza Store
			this.xUtils.sync(store, '#equipmentsgrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onEquipmentBtnSubmitEdit: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= activeTab.xStore,											//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			//Carrega dados do formulario na Store
			store.findRecord('id',record.get('id')).set(values);
			
			store.findRecord('id',record.get('id')).set({
				installDate:	Ext.Date.parse(values.installDate	, "d/m/Y"),
				type:			Ext.create('Sam.model.EquipmentType', {id: values.type_id}),
				manufacturer:	Ext.create('Sam.model.EquipmentManufacturer', {id: values.manufacturer_id}),
				model:			Ext.create('Sam.model.EquipmentModel', {id: values.model_id}),
				site:			Ext.create('Sam.model.Site', {id: values.site_id}),
				system:			Ext.create('Sam.model.System',		{id: values.system_id, desc: values.system_desc})
			});
			
			//Sincroniza e Atualiza Store
			this.xUtils.sync(store, '#equipmentsgrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onEquipmentBtnSubmitDelete: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= activeTab.xStore,											//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
		
			//Apaga registro da Store
			store.remove(record);
			
			//Sincroniza e Atualiza Store
			this.xUtils.sync(store, '#equipmentsgrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	/*********** End Equipment Controlling ***********/
});
