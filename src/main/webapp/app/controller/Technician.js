Ext.define('Sam.controller.Technician', {
	extend: 'Ext.app.Controller',
	
	stores:['Technician'],
	
	views: ['Sam.view.technician.TechnicianGrid',
	        'Sam.view.technician.TechnicianForm'],
	        
    refs: [
           {    ref: 'lookup',     selector: 'popup'   }
       ],


	init: function() {
		
		this.control({
			/* Buttons Listeners: Technician
			 * 
			 */
			'#technicianform #site_id' :{
				click:   this.onTechnicianTrgClick
			},
			
			'#technician_site #submit' :{
				click: this.onLookupSubmitClick
			},
			
			'#technicianform toolbar #btnSubmit' :{
				create: this.onTechnicianBtnSubmitAdd,
				read:   function(){Ext.getCmp('viewportpanel').getActiveTab().close()},
				update: this.onTechnicianBtnSubmitEdit,
				remove: this.onTechnicianBtnSubmitDelete,
				
			},
			
			'#techniciangrid toolbar #btnShow' :{
				click: this.onTechnicianBtnShowClick
			},
			
			'#techniciangrid toolbar #btnEdit' :{
				click: this.onTechnicianBtnEditClick
			},
			
			'#techniciangrid toolbar #btnAdd' :{
				click: this.onTechnicianBtnAddClick
			},
			
			'#techniciangrid toolbar #btnDelete' :{
				click: this.onTechnicianBtnDeleteClick
			},
			
		});
	},
	/*********** Begin Technician Controlling ***********/
	onTechnicianTrgClick: function(){
		var popup = Ext.create('Sam.view.components.PopUp',{itemId: 'technician_site'});
		var grid = Ext.create('Sam.view.site.SiteGrid');
		
		var buttons = Ext.ComponentQuery.query('toolbar',grid)[0];
		
		//Remove Botoes
		grid.remove(Ext.ComponentQuery.query('toolbar',grid)[0], true);
		
		popup.setTitle('Selecionar Local');
		popup.add(grid);
		popup.show();
	},
	
	onLookupSubmitClick: function(){
		
		var row = this.getLookup().down('grid').getSelection()[0];
		
		var activeTab = Ext.getCmp('viewportpanel').getActiveTab();
		
		if(row){
			
			fld = Ext.ComponentQuery.query( 'form #fldSite',activeTab)[0];
			
			Ext.ComponentQuery.query('#site_id',fld)[0].setValue(row.get('id'));
			Ext.ComponentQuery.query('#site_desc',fld)[0].setValue(row.get('desc'));
			
			this.getLookup().close();
		}
		
	},
	
	onTechnicianBtnShowClick: function() {
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 1 - Visualizar
			activeTab = this.activateTab(1, row.get('id'), 'technicianform', null);
			
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
	
	onTechnicianBtnEditClick: function(){
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 3 - Alterar
			activeTab = this.activateTab(3, row.get('id'), 'technicianform', null);
			
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
	
	onTechnicianBtnAddClick: function(){
		
		//Cria Aba: 2 - Incluir
		var activeTab = this.activateTab(2, null, 'technicianform', null);
		
		if(activeTab){
	
			//Seta Botão Confirma: Incluir
			Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('create')});
		}

	},
	
	onTechnicianBtnDeleteClick: function(){
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 4 - Excluir
			activeTab = this.activateTab(4, row.get('id'), 'technicianform', null);
			
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
	
	onTechnicianBtnSubmitAdd: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getTechnicianStore(),								//Store
			record		= Ext.create('Sam.model.Technician');						//Registro
		
		if(form.isValid()){
			
			//Carrega dados do Formulario no registro
			record.set(values);
			
			//Carrega Site
			record.set({site: Ext.create('Sam.model.Site',{id: values.site_id, desc: values.site_desc})})
			
			//Adiciona registro na store
			store.add(record);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, '#techniciangrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onTechnicianBtnSubmitEdit: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getTechnicianStore(),								//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			//Carrega dados do formulario na Store
			store.findRecord('id',record.get('id')).set(values);
			
			//Carrega dados do formulario na Store
			store.findRecord('id',record.get('id')).set({site: Ext.create('Sam.model.Site',{id: values.site_id, desc: values.site_desc})});
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, '#techniciangrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onTechnicianBtnSubmitDelete: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getTechnicianStore(),								//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
		
			//Apaga registro da Store
			store.remove(record);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, '#techniciangrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	/*********** End Technician Controlling ***********/
	
	/*********** Common Methods***********/
	activateTab : function(action, id, xtype, uTitle){
		
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
		
	}
});
