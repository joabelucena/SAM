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
			'#taskform_equipments #submit' :{
				click: this.TaskFormEquipmentsSubmit,
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
			},
			
			'#btnDelAllCond':{
				click: this.btnDelAllCondClick
			},
			
			'#grdConditions actioncolumn':{
				itemClick: this.onGrdCondActClmItemClick,
			},
			
			'#grdEquipments actioncolumn':{
				itemClick: this.onGrdEquipActClmItemClick,
			}
			
			
		});
	},
	
	gridOnRender: function(me, options){
//		me.getStore().reload();
//		me.getView().refresh();
	},
	
	/*** Buttons ***/
	btnAddCondOnClick: function(button){
		var store = Ext.ComponentQuery.query('#grdConditions')[0].getStore(),
			record = store.getAt(store.data.length-1),
			lAdd = false;
		
		
		//Se nao for primeiro registro
		if(!record){
			lAdd = true;
		}else{
			
			//Validações
			if(record.get('type') != "MT"){
				
				if(record.get('logicOper') == "" || record.get('type') == "" || + 
					record.get('field') == "" || record.get('relOper') == "" || record.get('value') == 0   ){
					lAdd = false;
				}else{
					lAdd = true;
				}
			}else{
				lAdd = !record.get('logicOper') == "";
			}
		}
		
		//Se passou pela validação adiciona registro
		if(lAdd){
			var condition = Ext.create('Sam.model.TaskCondition',{
				seq: Ext.util.Format.leftPad(store.data.length+1,2,'0') 
			});
			
			//trava primeira condicao
			if(store.data.length === 0){
				condition.set('logicOper','-');
			}
			
			//Adiciona novo registro
			store.add(condition);
		}else{
			Ext.Msg.alert('Atenção', 'Preencha os campos corretamente.')
		}
	},
	
	btnDelAllCondClick:function(button){
		
		var store = Ext.ComponentQuery.query('#grdConditions')[0].getStore();
		
		if(store.data.length > 0){
			
			Ext.MessageBox.show({
		        title: 'Atenção',
		        msg: 'Deseja normalizar apagar todas as condições?',
		        buttons: Ext.MessageBox.OKCANCEL,
		        icon: Ext.MessageBox.WARNING,
		        fn: function(btn,  knowId, knowCheck){
		            if(btn == 'ok'){
		            	
		            	store.removeAll();
		            	
		            } else if(btn == 'cancel') {
		            	
		            }
		            	
		            }
		        });
		}
		
	},
	
	onGrdCondActClmItemClick : function(view, rowIndex, colIndex, item, e, record, row) {
		
		var store = Ext.ComponentQuery.query('#grdConditions')[0].getStore();
		
		Ext.MessageBox.show({
	        title: 'Atenção',
	        msg: 'Confirma exclusão da linha?',
	        buttons: Ext.MessageBox.OKCANCEL,
	        icon: Ext.MessageBox.WARNING,
	        fn: function(btn,  knowId, knowCheck){
	            if(btn == 'ok'){
	            	
	            	store.remove(record);

	            	//Renumera as Sequencias
                	Ext.each(store.data.items,function(item,index){
                		item.set('seq',Ext.util.Format.leftPad(index+1,2,'0'));
                	});
	            	
	            } else if(btn == 'cancel') {
	            	
	            }
	            	
	            }
	        });
		
	},
	
	onGrdEquipActClmItemClick : function(view, rowIndex, colIndex, item, e, record, row) {
		
		var store = Ext.ComponentQuery.query('#grdEquipments')[0].getStore();
		
		Ext.MessageBox.show({
	        title: 'Atenção',
	        msg: 'Confirma exclusão da linha?',
	        buttons: Ext.MessageBox.OKCANCEL,
	        icon: Ext.MessageBox.WARNING,
	        fn: function(btn,  knowId, knowCheck){
	            if(btn == 'ok'){
	            	
	            	store.remove(record);

	            } else if(btn == 'cancel') {
	            	
	            }
	            	
	            }
	        });
		
	},
	
	TaskFormEquipmentsSubmit: function(button){
		var mainStore = Ext.ComponentQuery.query('#grdEquipments')[0].getStore(),
			window = button.up('window'), 
			windStore = window.down('grid').getStore();
			
		
		windStore.each(function(rec){
			if(rec.get('active')){
				mainStore.add(rec);
			}
		});
		
		window.close();
		
		
			
	},
	
	/*********** Begin Task Controlling ***********/
	
	
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
