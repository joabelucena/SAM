Ext.define('Sam.controller.SeverityLevel', {
	extend: 'Ext.app.Controller',
	
	stores: ['SeverityLevel'],
	
	views: ['Sam.view.severity.SeverityForm',
	        'Sam.view.severity.SeverityGrid'],

	init: function() {
		
		this.control({
			'grid': {
				render: this.gridOnRender
			},
			
			'form toolbar #btnDiscard' :{
				click:   function(){Ext.getCmp('viewportpanel').getActiveTab().close()},
			},
			
			/* Buttons Listeners: Severity
			 *  
			 */
			'#severityform toolbar #btnSubmit' :{
				create: this.onSeverityBtnSubmitAdd,
				read:   function(){Ext.getCmp('viewportpanel').getActiveTab().close()},
				update: this.onSeverityBtnSubmitEdit,
				remove: this.onSeverityBtnSubmitDelete,
				
			},
			
			'#severitygrid toolbar #btnShow' :{
				click: this.onSeverityBtnShowClick
			},
			
			'#severitygrid toolbar #btnEdit' :{
				click: this.onSeverityBtnEditClick
			},
			
			'#severitygrid toolbar #btnAdd' :{
				click: this.onSeverityBtnAddClick
			},
			
			'#severitygrid toolbar #btnDelete' :{
				click: this.onSeverityBtnDeleteClick
			}
			
		});
	},
	
	gridOnRender: function(me, options){
		me.getStore().reload();
		me.getView().refresh();
	},
	
	
	/*********** Begin Severity Controlling ***********/
	onSeverityBtnShowClick: function() {
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 1 - Visualizar
			activeTab = this.activateTab(1, row.get('id'), 'severityform', null);
			
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
	
	onSeverityBtnEditClick: function(){
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 3 - Alterar
			activeTab = this.activateTab(3, row.get('id'), 'severityform', null);
			
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
	
	onSeverityBtnAddClick: function(){

			
		//Cria Aba: 2 - Incluir
		var activeTab = this.activateTab(2, null, 'severityform', null);
		
		if(activeTab){
	
			//Seta Botão Confirma: Incluir
			Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('create')});
		}


	},
	
	onSeverityBtnDeleteClick: function(){
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 4 - Excluir
			activeTab = this.activateTab(4, row.get('id'), 'severityform', null);
			
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
	
	onSeverityBtnSubmitAdd: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getSeverityLevelStore(),						//Store
			record		= Ext.create('Sam.model.SeverityLevel');			//Registro
		
		
		
		if(form.isValid()){
			
			//Carrega dados do Formulario no registro
			record.set(values);
			
			//Adiciona registro na store
			store.add(record);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, '#severitygrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onSeverityBtnSubmitEdit: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getSeverityLevelStore(),						//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			//Carrega dados do formulario na Store
			store.findRecord('id',record.get('id')).set(values);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, '#severitygrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onSeverityBtnSubmitDelete: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getSeverityLevelStore(),						//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			
			//Apaga registro da Store
			store.remove(record);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, '#severitygrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	
	/*********** End Of Severity Controlling ***********/
	
	
	
	/*********** Common Methods***********/
	syncStore: function(store, comp){
		
		//Sincroniza Store
		store.sync();
		
		//Recarrega Store
		store.reload();
		
		//Atualiza stores e views
		Ext.each(Ext.ComponentQuery.query(comp),function(f){
			f.getStore().reload();
		});
		
	},
	
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
			scope: this
		});
		
	},
	
	activateTab: function(action, id, xtype, uTitle){
		
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
		
		//Variavel para retornar aba ativa
		activeTab = mainPanel.getActiveTab();
		
		return activeTab;
		
	}
	
});
