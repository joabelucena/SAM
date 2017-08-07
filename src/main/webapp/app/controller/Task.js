Ext.define('Sam.controller.Task', {
	extend: 'Ext.app.Controller',
	
	stores:['Task'],
	
	views: ['task.TaskGrid',
	        'task.TaskForm'],
    
	refs: [{ref: 'lookup', selector: 'popup'}],
	
	xUtils: Ext.create('Sam.lib.Util'),
	
	init: function() {
		
		this.control({
			/* Buttons Listeners: Task
			 * 
			 */
			'#taskform_equipments #submit' :{
				click: this.TaskFormEquipmentsSubmit,
			},
			
			'#taskform toolbar #btnSubmit' :{
				create: this.onTaskBtnSubmitAdd,
				read:   function(){Ext.getCmp('viewportpanel').getActiveTab().close()},
				update: this.onTaskBtnSubmitEdit,
				remove: this.onTaskBtnSubmitDelete
			},
			
			'#taskgrid toolbar #btnShow' :{
				click: this.onTaskBtnShowClick
			},
			
			'#taskgrid toolbar #btnEdit' :{
				click: this.onTaskBtnEditClick
			},
			
			'#taskgrid toolbar #btnAdd' :{
				click: this.onTaskBtnAddClick
			},
			
			'#taskgrid toolbar #btnDelete' :{
				click: this.onTaskBtnDeleteClick
			}
			
			
		});
	},
	/*** Buttons ***/
	TaskFormEquipmentsSubmit: function(button){
		var mainStore = Ext.ComponentQuery.query('#grdEquipments')[0].getStore(),
			window = button.up('window'), 
			windStore = window.down('grid').getStore();
			
		windStore.each(function(rec){
			if(rec.get('active')){
				
				if(mainStore.findBy(function(e){return e.get('equipment_id') === rec.get('equipment_id')}) < 0){
					var taskEquip = Ext.create('Sam.model.TaskEquipment');
					
					taskEquip.set(rec.getData());
					
					taskEquip.set({id: null});
					
					mainStore.add(taskEquip);
				}
			}
		});
		
		window.close();
	},
	
	/*********** Begin Task Controlling ***********/
	onTaskBtnShowClick: function() {
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		var activeTab, grdCond, grdEquip, store = null;
		
		store = this.getTaskStore();
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 1 - Visualizar
			activeTab = this.activateTab(1, row.get('id'), 'taskform', null, true);
			
			if(activeTab){
				
				//Grid de Condicoes
				grdCond = Ext.ComponentQuery.query('#grdConditions',activeTab)[0];
				
				//Grid de Equipamentos
				grdEquip = Ext.ComponentQuery.query('#grdEquipments',activeTab)[0];
			
				//Retorna Form
				var form = Ext.ComponentQuery.query('form',activeTab)[0].getForm();
				
				//Carrega dados na tela
				form.loadRecord(row);
				grdCond.setStore(row.conditions());
				grdEquip.setStore(row.equipments());
				
				//Ordena a store
				grdCond.getStore().sort('seq','ASC');
				grdEquip.getStore().sort('id','ASC');				
				
				//Campos a desabilitar
				var fields = Ext.ComponentQuery.query('form field',activeTab);
				
				//Desabilita Campos
				Ext.each(fields,function(f){f.setReadOnly(true)})
				
				//Seta Botão Confirma: 1 - Visualizar
				Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('read')});
				
				//Desabilita plugins de edição nos grids
				grdCond.removePlugin(grdCond.findPlugin('cellediting'));
				grdEquip.removePlugin(grdEquip.findPlugin('cellediting'));
				
				grdCond.headerCt.items.getAt(grdCond.headerCt.items.findIndex('xtype','actioncolumn')).setVisible(false);
				grdEquip.headerCt.items.getAt(grdEquip.headerCt.items.findIndex('xtype','actioncolumn')).setVisible(false);
				
				//Desabilita botoes
				Ext.each(Ext.ComponentQuery.query('fieldset',activeTab),function(f){
					   Ext.each(Ext.ComponentQuery.query('button', f),function(b){
					      b.setDisabled(true);
					})});
			}
		}
	},
	
	onTaskBtnEditClick: function(){
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0],
			store = Ext.getCmp('viewportpanel').getActiveTab().getStore();
		
		var activeTab, grdCond, grdEquip = null;
		
		//Linha selecionada
		
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 3 - Alterar
//			activeTab = this.activateTab(3, row.get('id'), 'taskform', null, true);
			
			var activeTab = this.xUtils.activateTab(3, null, 'taskform', null, false, store);
//			var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0],
//			store = Ext.getCmp('viewportpanel').getActiveTab().getStore();
			
			if(activeTab){
				
				
				//Grid de Condicoes
				grdCond = Ext.ComponentQuery.query('#grdConditions',activeTab)[0];
				
				//Grid de Equipamentos
				grdEquip = Ext.ComponentQuery.query('#grdEquipments',activeTab)[0];
			
				//Retorna Form
				var form = Ext.ComponentQuery.query('form',activeTab)[0].getForm();
				
				//Carrega dados na tela
				form.loadRecord(row);
				grdCond.setStore(row.conditions());
				grdEquip.setStore(row.equipments());				
				
				//Ordena a store
				grdCond.getStore().sort('seq','ASC');
				grdEquip.getStore().sort('id','ASC');
				
				//Seta Botão Confirma: 3 - Alterar
				Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('update')});
				
			}
		}
	},
	
	onTaskBtnAddClick: function(){

			
		//Cria Aba: 2 - Incluir
		var activeTab = this.activateTab(2, null, 'taskform', null, true);
		
		var row = Ext.create('Sam.model.Task', {id: 0});
		
		var activeTab, grdCond, grdEquip, store = null;
		
		if(activeTab){
	
			//Grid de Condicoes
			grdCond = Ext.ComponentQuery.query('#grdConditions',activeTab)[0];
			
			//Grid de Equipamentos
			grdEquip = Ext.ComponentQuery.query('#grdEquipments',activeTab)[0];
		
			//Retorna Form
			var form = Ext.ComponentQuery.query('form',activeTab)[0].getForm();
			
			//Carrega dados na tela
			form.loadRecord(row);
			grdCond.setStore(row.conditions());
			grdEquip.setStore(row.equipments());
			
			//Seta Botão Confirma: Incluir
			Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('create')});
			

		}


	},
	
	onTaskBtnDeleteClick: function(){
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		var activeTab, grdCond, grdEquip, store = null;
		
		store = this.getTaskStore();
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 4 - Excluir
			activeTab = this.activateTab(4, row.get('id'), 'taskform', null, true);
			
			if(activeTab){
				
				//Grid de Condicoes
				grdCond = Ext.ComponentQuery.query('#grdConditions',activeTab)[0];
				
				//Grid de Equipamentos
				grdEquip = Ext.ComponentQuery.query('#grdEquipments',activeTab)[0];
			
				//Retorna Form
				var form = Ext.ComponentQuery.query('form',activeTab)[0].getForm();
				
				//Carrega dados na tela
				form.loadRecord(row);
				grdCond.setStore(row.conditions());
				grdEquip.setStore(row.equipments());
				
				//Ordena a store
				grdCond.getStore().sort('seq','ASC');
				grdEquip.getStore().sort('id','ASC');				
				
				//Campos a desabilitar
				var fields = Ext.ComponentQuery.query('form field',activeTab);
				
				//Desabilita Campos
				Ext.each(fields,function(f){f.setReadOnly(true)})
				
				//Desabilita plugins de edição nos grids
				grdCond.removePlugin(grdCond.findPlugin('cellediting'));
				grdEquip.removePlugin(grdEquip.findPlugin('cellediting'));
				
				grdCond.headerCt.items.getAt(grdCond.headerCt.items.findIndex('xtype','actioncolumn')).setVisible(false);
				grdEquip.headerCt.items.getAt(grdEquip.headerCt.items.findIndex('xtype','actioncolumn')).setVisible(false);
				
				//Desabilita botoes
				Ext.each(Ext.ComponentQuery.query('fieldset',activeTab),function(f){
					   Ext.each(Ext.ComponentQuery.query('button', f),function(b){
					      b.setDisabled(true);
					   })
				});
			
				//Seta Botão Confirma: 4 - Excluir
				Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('remove')});
				
			}
		}
	},
	
	onTaskBtnSubmitAdd: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),											//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),												//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),				//Formulario
			values		= form.getValues(),														//Dados do Formulario
			store		= this.getTaskStore(),													//Store
			equipSt		= Ext.ComponentQuery.query('#grdEquipments',activeTab)[0].getStore(),	//Store dos equipamentos
			condSt		= Ext.ComponentQuery.query('#grdConditions',activeTab)[0].getStore(),	//Store dos equipamentos
			record		= Ext.create('Sam.model.Task', {id: 0}),											//Registro
			lValid		= false;																//Validação dos Dados
		
		
		//Validação form 
		lValid = form.isValid();
		
		//Validação grid equipamentos
		Ext.each(equipSt.getData().items,function(item){
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
			//Carrega dados do Formulario no registro
			record.set(values);
			
			//Carrega Objetos
			record.setAlarm(Ext.create('Sam.model.Alarm',{id: values.alarm_id}));
			record.conditions().setData(condSt.getData().items);
			record.equipments().setData(equipSt.getData().items);
			
			Ext.each(record.equipments().data.items,function(item){
				item.set({
					equipment : Ext.create('Sam.model.Equipment'		,{id: item.get('equipment_id')}	)
				});
			});
			
			//Adiciona registro na store
			store.add(record);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, '#taskgrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onTaskBtnSubmitEdit: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			store		= activeTab.xStore,											//Store
			updated		= form.getRecord(),											//Dados atualizado
			record		= store.findRecord('id',updated.get('id')),					//Registro
			lValid		= false;													//Validação dos Dados
		
		//Validação form 
		lValid = form.isValid();
		
		//Validação grid equipamentos
		Ext.each(updated.equipments().data.items,function(item){
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
			
			//Carrega dados do Formulario no registro
			record.set(updated.getData());
			record.setAlarm(updated.getAlarm());
			record.conditions().setData(updated.conditions().getData());
			
			Ext.each(updated.equipments().data.items,function(item){
				item.set({
					equipment	: Ext.create('Sam.model.Equipment'		,{id: item.get('equipment_id')}	),
				});
			});
			
			record.equipments().setData(updated.equipments().getData());
			
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, '#taskgrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onTaskBtnSubmitDelete: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getTaskStore(),										//Store
			record		= store.findRecord('id',values.id);							//Registro
		
		if(form.isValid()){
			
			//Apaga registro da Store
			store.remove(record);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, '#taskgrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	/*********** End Of Task Controlling ***********/
	
	/*********** Common Methods***********/
	syncStore: function(store, comp){
		
		//Sincroniza Store
		store.sync({
			success: function(){
				
				//Recarrega Store
				store.reload();
				
				//Atualiza stores e views
				Ext.each(Ext.ComponentQuery.query(comp),function(f){
					f.getStore().reload();
				});
			},
			failure: function(){
				console.log('failure');
			},
			scope: this
		});
		
	},
	
	activateTab: function(action, id, xtype, uTitle, lockId){
		
		//Variaveis
		var title, tabId, activeTab;
		
		//Aba Objecto Pai
		mainPanel = Ext.getCmp('viewportpanel');
		
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
		
		//Se for inclusao desabilita o campo Id
		if(action == 2 && lockId){
			Ext.ComponentQuery.query('#id' , newTab)[0].setDisabled(true);
		}
		
		//Variavel para retornar aba ativa
		activeTab = mainPanel.getActiveTab();
		
		return activeTab;
		
	}
	
});
