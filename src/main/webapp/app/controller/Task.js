Ext.define('Sam.controller.Task', {
	extend: 'Ext.app.Controller',
	
	stores:['Task'],
	
	views: ['task.TaskGrid',
	        'task.TaskForm'],
    
	refs: [{ref: 'lookup', selector: 'popup'}],
	
	init: function() {
		
		this.control({
			'grid': {
				render: this.gridOnRender
			},
			
			/* Buttons Listeners: Task
			 *  
			 */
			'#taskform_model #submit' :{
				click: this.TaskFormModelSubmit,
			},
			
			'#taskform toolbar #btnSubmit' :{
				create: this.onTaskBtnSubmitAdd,
				read:   function(){Ext.getCmp('viewportpanel').getActiveTab().close()},
				update: this.onTaskBtnSubmitEdit,
				remove: this.onTaskBtnSubmitDelete,
				
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
			},
			'#btnAddCond':{
				click: this.btnAddCondOnClick
			}
		});
	},
	
	gridOnRender: function(me, options){
//		me.getStore().reload();
//		me.getView().refresh();
	},
	
	/*** Buttons ***/
	btnAddCondOnClick: function(){
		var grid = Ext.ComponentQuery.query('#grdConditions')[0];
		var condition = Ext.create('Sam.model.TaskCondition',{
			seq: Ext.util.Format.leftPad(grid.getStore().data.length+1,2,'0') 
		});
		
		//trava primeira condicao
		if(grid.getStore().data.length === 0){
			condition.logicOper = '-';
		}
		
		Ext.util.Format.leftPad(grid.getStore().data.length,2,'0')
		//Adiciona novo registro
		grid.getStore().add(condition);
		
	},
	
	/*********** Begin Task Controlling ***********/
	TaskFormModelSubmit: function(){
		
	var row = this.getLookup().down('grid').getSelection()[0];
		
		var activeTab = Ext.getCmp('viewportpanel').getActiveTab();
		
		if(row){
			
			Ext.ComponentQuery.query('#model_id',activeTab)[0].setValue(row.get('id'));
			Ext.ComponentQuery.query('#model_desc',activeTab)[0].setValue(row.get('desc'));
			
			this.getLookup().close();
		}
		
	},
	
	onTaskBtnShowClick: function() {
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 1 - Visualizar
			activeTab = this.activateTab(1, row.get('id'), 'taskform', null, true);
			
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
	
	onTaskBtnEditClick: function(){
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 3 - Alterar
			activeTab = this.activateTab(3, row.get('id'), 'taskform', null, true);
			
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
	
	onTaskBtnAddClick: function(){

			
		//Cria Aba: 2 - Incluir
		var activeTab = this.activateTab(2, null, 'taskform', null, false);
		
		if(activeTab){
	
			//Seta Botão Confirma: Incluir
			Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('create')});
			
			//Habilita edição do ID
//			Ext.ComponentQuery.query('#id' , activeTab)[0].setEditable(true);
		}


	},
	
	onTaskBtnDeleteClick: function(){
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 4 - Excluir
			activeTab = this.activateTab(4, row.get('id'), 'taskform', null, true);
			
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
	
	onTaskBtnSubmitAdd: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getTaskStore(),										//Store
			record		= Ext.create('Sam.model.Task');							//Registro
		
		
		
		if(form.isValid()){
			
			//Carrega dados do Formulario no registro
			record.set(values);
			
			//Carrega Objetos
			record.set(
					{	group:		Ext.create('Sam.model.TaskGroup'		,{id: values.group_id	, desc: values.group_desc	}),
						type:		Ext.create('Sam.model.TaskType'		,{id: values.type_id	, desc: values.type_desc	}),
						model: 		Ext.create('Sam.model.EquipmentModel'	,{id: values.model_id	, desc: values.model_desc	}),
						severity:	Ext.create('Sam.model.SeverityLevel'	,{id: values.severity_id, desc: values.severity_desc}),
			});
			
			if(values.task_id !== ""){
				record.set(
					{	normTask:	Ext.create('Sam.model.Task'			,{id: values.task_id	, desc: values.task_desc	})
				});
			}
			
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
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getTaskStore(),										//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			//Carrega dados do formulario na Store
			store.findRecord('id',record.get('id')).set(values);
			
			//Carrega Objetos
			store.findRecord('id',record.get('id')).set(
					{	group:		Ext.create('Sam.model.TaskGroup'		,{id: values.group_id	, desc: values.group_desc	}),
						type:		Ext.create('Sam.model.TaskType'		,{id: values.type_id	, desc: values.type_desc	}),
						model: 		Ext.create('Sam.model.EquipmentModel'	,{id: values.model_id	, desc: values.model_desc	}),
						severity:	Ext.create('Sam.model.SeverityLevel'	,{id: values.severity_id, desc: values.severity_desc}),
			});
			
			if(values.task_id !== ""){
				store.findRecord('id',record.get('id')).set(
					{	normTask:	Ext.create('Sam.model.Task'			,{id: values.task_id	, desc: values.task_desc	})
				});
			}
			
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
			store		= this.getTaskStore(),						//Store
			record		= form.getRecord();											//Registro
		
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
//				autoDestroy: false,
				iconCls: 'magnifier-zoom',
				title: title
			});
		}
		
		//Seta Aba como ativa
		mainPanel.setActiveTab(newTab);
		
		//Se for inclusao desabilita o campo Id
		if(action == 2 && lockId){
			Ext.ComponentQuery.query('#id' , newTab)[0].setVisible(false);
		}
		
		//Variavel para retornar aba ativa
		activeTab = mainPanel.getActiveTab();
		
		return activeTab;
		
	}
	
});
