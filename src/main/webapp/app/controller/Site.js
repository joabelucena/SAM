Ext.define('Sam.controller.Site', {
	extend: 'Ext.app.Controller',
	
	stores:['Site',
	        'ServiceStation',
	        'SiteType'
	        ],
	
	models:['Site',
	        'ServiceStation',
	        'SiteType'
	        ],
	
	views: ['Sam.view.site.SiteGrid',
	        'Sam.view.site.SiteForm',
	        'Sam.view.site.station.StationForm',
	        'Sam.view.site.station.StationGrid',
	        'Sam.view.site.type.SiteTypeForm',
	        'Sam.view.site.type.SiteTypeGrid'
	        ],
	        
	refs: [
	        {    ref: 'lookup',     selector: 'popup'   }
	      ],

	init: function() {
		
		this.control({
			'grid': {
				render: this.gridOnRender
			},
			
			'form toolbar #btnDiscard' :{
				click:   function(){Ext.getCmp('viewportpanel').getActiveTab().close()},
			},
			
			/* Buttons Listeners: Site
			 *  
			 */
			'#siteform #type_id' :{
				click:   this.onSiteTypeTrgClick
			},
			
			'#siteform #station_id' :{
				click:   this.onServiceStationTrgClick
			},
			
			'#siteform #parent_id' :{
				click:   this.onParentSiteTrgClick
			},
			
			'#siteform_sitetype #submit' :{
				click: this.onLookupSiteTypeSubmitClick
			},
			
			'#siteform_servicestation #submit' :{
				click: this.onLookupServiceStationSubmitClick
			},
			
			'#siteform_parentsite #submit' :{
				click: this.onLookupParentSiteSubmitClick
			},
			
			'#siteform toolbar #btnSubmit' :{
				create: this.onSiteBtnSubmitAdd,
				read:   function(){Ext.getCmp('viewportpanel').getActiveTab().close()},
				update: this.onSiteBtnSubmitEdit,
				remove: this.onSiteBtnSubmitDelete,
				
			},
			
			'#sitegrid toolbar #btnShow' :{
				click: this.onSiteBtnShowClick
			},
			
			'#sitegrid toolbar #btnEdit' :{
				click: this.onSiteBtnEditClick
			},
			
			'#sitegrid toolbar #btnAdd' :{
				click: this.onSiteBtnAddClick
			},
			
			'#sitegrid toolbar #btnDelete' :{
				click: this.onSiteBtnDeleteClick
			},
			

			/* Buttons Listeners: Station
			 *  
			 */
			'#stationform toolbar #btnSubmit' :{
				create: this.onStationBtnSubmitAdd,
				read:   function(){Ext.getCmp('viewportpanel').getActiveTab().close()},
				update: this.onStationBtnSubmitEdit,
				remove: this.onStationBtnSubmitDelete,
				
			},
			
			'#stationgrid toolbar #btnShow' :{
				click: this.onStationBtnShowClick
			},
			
			'#stationgrid toolbar #btnEdit' :{
				click: this.onStationBtnEditClick
			},
			
			'#stationgrid toolbar #btnAdd' :{
				click: this.onStationBtnAddClick
			},
			
			'#stationgrid toolbar #btnDelete' :{
				click: this.onStationBtnDeleteClick
			},
			

			/* Buttons Listeners: Site Type
			 *  
			 */
			'#sitetypeform toolbar #btnSubmit' :{
				create: this.onSiteTypeBtnSubmitAdd,
				read:   function(){Ext.getCmp('viewportpanel').getActiveTab().close()},
				update: this.onSiteTypeBtnSubmitEdit,
				remove: this.onSiteTypeBtnSubmitDelete,
				
			},
			
			'#sitetypegrid toolbar #btnShow' :{
				click: this.onSiteTypeBtnShowClick
			},
			
			'#sitetypegrid toolbar #btnEdit' :{
				click: this.onSiteTypeBtnEditClick
			},
			
			'#sitetypegrid toolbar #btnAdd' :{
				click: this.onSiteTypeBtnAddClick
			},
			
			'#sitetypegrid toolbar #btnDelete' :{
				click: this.onSiteTypeBtnDeleteClick
			}
			
		});
	},
	
	gridOnRender: function(me, options){
		me.getStore().reload();
		me.getView().refresh();
	},
	
	/*********** Begin Site Controlling ***********/
	
	onSiteTypeTrgClick: function(){
		var popup = Ext.create('Sam.view.components.PopUp',{itemId: 'siteform_sitetype'});
		var grid = Ext.create('Sam.view.site.type.SiteTypeGrid');
		
		var buttons = Ext.ComponentQuery.query('toolbar',grid)[0];
		
		//Remove Botoes
		grid.remove(Ext.ComponentQuery.query('toolbar',grid)[0], true);
		
		popup.setTitle('Selecionar Tipo de Local');
		popup.add(grid);
		popup.show();
	},
	
	onServiceStationTrgClick: function(){
		var popup = Ext.create('Sam.view.components.PopUp',{itemId: 'siteform_servicestation'});
		var grid = Ext.create('Sam.view.site.station.StationGrid');
		
		var buttons = Ext.ComponentQuery.query('toolbar',grid)[0];
		
		//Remove Botoes
		grid.remove(Ext.ComponentQuery.query('toolbar',grid)[0], true);
		
		popup.setTitle('Selecionar Base de Manutenção');
		popup.add(grid);
		popup.show();
	},
	
	onParentSiteTrgClick: function(){
		var popup = Ext.create('Sam.view.components.PopUp',{itemId: 'siteform_parentsite'});
		var grid = Ext.create('Sam.view.site.SiteGrid');
		
		var buttons = Ext.ComponentQuery.query('toolbar',grid)[0];
		
		//Remove Botoes
		grid.remove(Ext.ComponentQuery.query('toolbar',grid)[0], true);
		
		popup.setTitle('Selecionar Local Pai');
		popup.add(grid);
		popup.show();
	},
	
	onLookupSiteTypeSubmitClick: function(){
		
		var row = this.getLookup().down('grid').getSelection()[0];
		
		var activeTab = Ext.getCmp('viewportpanel').getActiveTab();
		
		if(row){
			
			fld = Ext.ComponentQuery.query( 'form #fldSiteType',activeTab)[0];
			
			Ext.ComponentQuery.query('#type_id',fld)[0].setValue(row.get('id'));
			Ext.ComponentQuery.query('#type_desc',fld)[0].setValue(row.get('desc'));
			
			this.getLookup().close();
		}
		
	},
	
	onLookupServiceStationSubmitClick: function(){
		
		var row = this.getLookup().down('grid').getSelection()[0];
		
		var activeTab = Ext.getCmp('viewportpanel').getActiveTab();
		
		if(row){
			
			fld = Ext.ComponentQuery.query( 'form #fldServiceStation',activeTab)[0];
			
			Ext.ComponentQuery.query('#station_id',fld)[0].setValue(row.get('id'));
			Ext.ComponentQuery.query('#station_desc',fld)[0].setValue(row.get('desc'));
			
			this.getLookup().close();
		}
		
	},
	
	onLookupParentSiteSubmitClick: function(){
		
		var row = this.getLookup().down('grid').getSelection()[0];
		
		var activeTab = Ext.getCmp('viewportpanel').getActiveTab();
		
		if(row){
			
			fld = Ext.ComponentQuery.query( 'form #fldParentSite',activeTab)[0];
			
			Ext.ComponentQuery.query('#parent_id',fld)[0].setValue(row.get('id'));
			Ext.ComponentQuery.query('#parent_desc',fld)[0].setValue(row.get('desc'));
			
			this.getLookup().close();
		}
		
	},
	
	onSiteBtnShowClick: function() {
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 1 - Visualizar
			activeTab = this.activateTab(1, row.get('id'), 'siteform', null);
			
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
	
	onSiteBtnEditClick: function(){
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 3 - Alterar
			activeTab = this.activateTab(3, row.get('id'), 'siteform', null);
			
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
	
	onSiteBtnAddClick: function(){

			
		//Cria Aba: 2 - Incluir
		var activeTab = this.activateTab(2, null, 'siteform', null);
		
		if(activeTab){
	
			//Seta Botão Confirma: Incluir
			Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('create')});
		}


	},
	
	onSiteBtnDeleteClick: function(){
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 4 - Excluir
			activeTab = this.activateTab(4, row.get('id'), 'siteform', null);
			
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
	
	onSiteBtnSubmitAdd: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getSiteStore(),										//Store
			record		= Ext.create('Sam.model.Site');								//Registro
		
		
		
		if(form.isValid()){
			
			//Carrega dados do Formulario no registro
			record.set(values);
			
			//Carrega Site Type
			record.set({model: Ext.create('Sam.model.SiteType'		,{id: values.type_id, desc: values.type_desc})})
			
			//Carrega Service Station
			record.set({model: Ext.create('Sam.model.ServiceStation',{id: values.station_id, desc: values.station_desc})})
			
			//Carrega Parent Site ?????
			//record.set({model: Ext.create('Sam.model.ServiceStation',{id: values.station_id, desc: values.station_desc})})
			
			//Adiciona registro na store
			store.add(record);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, '#sitegrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onSiteBtnSubmitEdit: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getSiteStore(),							//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			//Carrega dados do formulario na Store
			store.findRecord('id',record.get('id')).set(values);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, '#sitegrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onSiteBtnSubmitDelete: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getSiteStore(),						//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			
			//Apaga registro da Store
			store.remove(record);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, '#sitegrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	/*********** End Of Site Controlling ***********/
	
	/*********** Begin Station Controlling ***********/
	onStationBtnShowClick: function() {
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 1 - Visualizar
			activeTab = this.activateTab(1, row.get('id'), 'stationform', null);
			
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
	
	onStationBtnEditClick: function(){
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 3 - Alterar
			activeTab = this.activateTab(3, row.get('id'), 'stationform', null);
			
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
	
	onStationBtnAddClick: function(){

			
		//Cria Aba: 2 - Incluir
		var activeTab = this.activateTab(2, null, 'stationform', null);
		
		if(activeTab){
	
			//Seta Botão Confirma: Incluir
			Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('create')});
		}


	},
	
	onStationBtnDeleteClick: function(){
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 4 - Excluir
			activeTab = this.activateTab(4, row.get('id'), 'stationform', null);
			
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
	
	onStationBtnSubmitAdd: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getServiceStationStore(),						//Store
			record		= Ext.create('Sam.model.ServiceStation');			//Registro
		
		
		
		if(form.isValid()){
			
			//Carrega dados do Formulario no registro
			record.set(values);
			
			//Adiciona registro na store
			store.add(record);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, '#stationgrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onStationBtnSubmitEdit: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getServiceStationStore(),							//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			//Carrega dados do formulario na Store
			store.findRecord('id',record.get('id')).set(values);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, '#stationgrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onStationBtnSubmitDelete: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getServiceStationStore(),						//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			
			//Apaga registro da Store
			store.remove(record);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, '#stationgrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	
	/*********** End Of Station Controlling ***********/
	
	/*********** Begin Site Type Controlling ***********/
	onSiteTypeBtnShowClick: function() {
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 1 - Visualizar
			activeTab = this.activateTab(1, row.get('id'), 'sitetypeform', null);
			
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
	
	onSiteTypeBtnEditClick: function(){
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 3 - Alterar
			activeTab = this.activateTab(3, row.get('id'), 'sitetypeform', null);
			
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
	
	onSiteTypeBtnAddClick: function(){

			
		//Cria Aba: 2 - Incluir
		var activeTab = this.activateTab(2, null, 'sitetypeform', null);
		
		if(activeTab){
	
			//Seta Botão Confirma: Incluir
			Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('create')});
		}


	},
	
	onSiteTypeBtnDeleteClick: function(){
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 4 - Excluir
			activeTab = this.activateTab(4, row.get('id'), 'sitetypeform', null);
			
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
	
	onSiteTypeBtnSubmitAdd: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getSiteTypeStore(),									//Store
			record		= Ext.create('Sam.model.SiteType');							//Registro
		
		
		
		if(form.isValid()){
			
			//Carrega dados do Formulario no registro
			record.set(values);
			
			//Adiciona registro na store
			store.add(record);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, '#sitetypegrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onSiteTypeBtnSubmitEdit: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getSiteTypeStore(),									//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			//Carrega dados do formulario na Store
			store.findRecord('id',record.get('id')).set(values);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, '#sitetypegrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onSiteTypeBtnSubmitDelete: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getSiteTypeStore(),									//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			
			//Apaga registro da Store
			store.remove(record);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, '#sitetypegrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	
	/*********** End Of Site Type Controlling ***********/
	
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
		
		//Se for inclusao desabilita o campo Id
		if(action == 2){
			Ext.ComponentQuery.query('#id' , newTab)[0].setVisible(false);
		}
		
		//Variavel para retornar aba ativa
		activeTab = mainPanel.getActiveTab();
		
		return activeTab;
		
	}
	
});
