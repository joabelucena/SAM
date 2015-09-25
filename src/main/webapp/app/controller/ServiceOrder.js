Ext.define('Sam.controller.ServiceOrder', {
	extend: 'Ext.app.Controller',
	
	stores:['ServiceOrderJob',
	        'ServiceOrderType',
	        'ServiceOrderRules',
	        'ServiceOrderStatus',
	        'ServiceOrder'
	        ],
	
    refs: [
           {    ref: 'lookup',     selector: 'popup'   }
       ],

	views: ['Sam.view.serviceOrder.ServiceOrderGrid',
	        'Sam.view.serviceOrder.ServiceOrderForm',
	        'Sam.view.serviceOrder.log.ServiceOrderLog',
			'Sam.view.serviceOrder.log.ServiceOrderLogGrid',
	        'Sam.view.serviceOrder.log.ServiceOrderLogForm',
	        'Sam.view.serviceOrder.job.JobGrid',
	        'Sam.view.serviceOrder.job.JobForm',
	        'Sam.view.serviceOrder.type.TypeGrid',
	        'Sam.view.serviceOrder.type.TypeForm',
	        'Sam.view.serviceOrder.status.StatusGrid',
	        'Sam.view.serviceOrder.status.StatusForm',
	        'Sam.view.serviceOrder.rules.RulesGrid',
	        'Sam.view.serviceOrder.rules.RulesForm'
	        ],

	init: function() {
		
		this.control({
			
			'#serviceorderform toolbar #btnSubmit' :{
				create: this.onSoBtnSubmitAdd,
				read:   function(){Ext.getCmp('viewportpanel').getActiveTab().close()},
				update: this.onSoBtnSubmitEdit
			},
			
			'#serviceordergrid toolbar #btnShow' :{
				click: this.onSoBtnShowClick
			},
			
			'#serviceordergrid toolbar #btnEdit' :{
				click: this.onSoBtnEditClick
			},
			
			'#serviceordergrid toolbar #btnAdd' :{
				click: this.onSoBtnAddClick
			},
			
			'#serviceordergrid toolbar splitbutton menuitem':{
				change: this.onToolbarItemSelect
			},
			
			/****************************************/
			'serviceorderloggrid':{
				itemmouseup: this.onItemMouseUp,
			},
			'toolbar #btnShowLog' :{
				click: this.onBtnShowLogClick
			},
			
			/* Buttons Listeners: Job
			 *  
			 */
			'#serviceorderjobform toolbar #btnSubmit' :{
				create: this.onJobBtnSubmitAdd,
				read:   function(){Ext.getCmp('viewportpanel').getActiveTab().close()},
				update: this.onJobBtnSubmitEdit,
				remove: this.onJobBtnSubmitDelete,
			},
			
			'#serviceorderjobgrid toolbar #btnShow' :{
				click: this.onJobBtnShowClick
			},
			
			'#serviceorderjobgrid toolbar #btnEdit' :{
				click: this.onJobBtnEditClick
			},
			
			'#serviceorderjobgrid toolbar #btnAdd' :{
				click: this.onJobBtnAddClick
			},
			
			'#serviceorderjobgrid toolbar #btnDelete' :{
				click: this.onJobBtnDeleteClick
			},
			
			/* Buttons Listeners: Service Order Type
			 *  
			 */
			'#serviceordertypeform toolbar #btnSubmit' :{
				create: this.onServiceOrderBtnSubmitAdd,
				read:   function(){Ext.getCmp('viewportpanel').getActiveTab().close()},
				update: this.onServiceOrderTypeBtnSubmitEdit,
				remove: this.onServiceOrderTypeBtnSubmitDelete,
			},
			
			'#serviceordertypegrid toolbar #btnShow' :{
				click: this.onServiceOrderTypeBtnShowClick
			},
			
			'#serviceordertypegrid toolbar #btnEdit' :{
				click: this.onServiceOrderTypeBtnEditClick
			},
			
			'#serviceordertypegrid toolbar #btnAdd' :{
				click: this.onServiceOrderTypeBtnAddClick
			},
			
			'#serviceordertypegrid toolbar #btnDelete' :{
				click: this.onServiceOrderTypeBtnDeleteClick
			},
			

			/* Buttons Listeners: Service Order Status
			 *  
			 */
			'#serviceorderstatusform toolbar #btnSubmit' :{
				create: this.onServiceOrderStatusBtnSubmitAdd,
				read:   function(){Ext.getCmp('viewportpanel').getActiveTab().close()},
				update: this.onServiceOrderStatusBtnSubmitEdit,
				remove: this.onServiceOrderStatusBtnSubmitDelete,
			},
			
			'#serviceorderstatusgrid toolbar #btnShow' :{
				click: this.onServiceOrderStautsBtnShowClick
			},
			
			'#serviceorderstatusgrid toolbar #btnEdit' :{
				click: this.onServiceOrderStatusBtnEditClick
			},
			
			'#serviceorderstatusgrid toolbar #btnAdd' :{
				click: this.onServiceOrderStatusBtnAddClick
			},
			
			'#serviceorderstatusgrid toolbar #btnDelete' :{
				click: this.onServiceOrderStatusBtnDeleteClick
			},
			
			/* Buttons Listeners: Service Order Rules
			 *  
			 */
			'#serviceorderrulesform #role_id' :{
				click: this.onSORulesTriggerClick
			},
			
			'#serviceorderrules_userrole #submit' :{
				click: this.onUsrRoleSubmitClick
			},
			
			'#serviceorderrulesform #curstatus_id' :{
				click: this.onSOCurrentStatusTriggerClick
			},
			
			'#serviceorderrules_currentstatusgrid #submit' :{
				click: this.onCurrrentStatusRuleSubmitClick				
			},
			
			'#serviceorderrulesform #nxtstatus_id' :{
				click: this.onSONextStatusTriggerClick
			},
			
			'#serviceorderrules_nextstatusgrid #submit' :{
				click: this.onNextStatusRuleSubmitClick				
			},
			
			'#serviceorderrulesform toolbar #btnSubmit' :{
				create: this.onServiceOrderRulesBtnSubmitAdd,
				read:   function(){Ext.getCmp('viewportpanel').getActiveTab().close()},
				update: this.onServiceOrderRulesBtnSubmitEdit,
				remove: this.onServiceOrderRulesBtnSubmitDelete,
			},
			
			'#ServiceOrderRulesgrid toolbar #btnShow' :{
				click: this.onServiceOrderStatusBtnShowClick
			},
			
			'#serviceorderrulesgrid toolbar #btnEdit' :{
				click: this.onServiceOrderRulesBtnEditClick
			},
			
			'#serviceorderrulesgrid toolbar #btnAdd' :{
				click: this.onServiceOrderRulesBtnAddClick
			},
			
			'#serviceorderrulesgrid toolbar #btnDelete' :{
				click: this.onServiceOrderRulesBtnDeleteClick
			},
			
		});
	},
	
	onSoBtnSubmitAdd: function(button, event){
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
		activeTab	= mainPanel.getActiveTab(),									//Aba ativa
		form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
		values		= form.getValues(),											//Dados do Formulario
		store		= this.getServiceOrderStore(),								//Store
		record		= Ext.create('Sam.model.ServiceOrder');
		
		if(form.isValid()){
			
			//Carrega dados do Formulario no registro
			record.set(values);
			
			record.set({
					type			: Ext.create('Sam.model.ServiceOrderType'	,{id: values.type_id})								,
					priority		: Ext.create('Sam.model.SeverityLevel'		,{id: values.priority_id})							,
					equipment		: Ext.create('Sam.model.Equipment'			,{id: values.equipment_id})							,
					startForecast	: Ext.Date.parse(values.start_forecast_date + " " + values.start_forecast_time	, "d/m/Y H:i")	,
					endForecast		: Ext.Date.parse(values.end_forecast_date	+ " " + values.end_forecast_time	, "d/m/Y H:i")	
					});
			
			//Adiciona registro na store
			store.add(record);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, 'serviceordergrid',false);
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onSoBtnSubmitEdit: function(button, event){
		
	},
	
	onSoBtnShowClick: function(button, event){
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 1 - Visualizar
			activeTab = this.activateTab(1, row.get('id'), 'serviceorderform', null, false);
			
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
	
	onSoBtnAddClick: function(item, event){
			
		//Cria Aba: 2 - Incluir
		var activeTab = this.activateTab(2, null, 'serviceorderform', null, true);
		
		if(activeTab){
			
			//'Minimiza' seção de apontamentos
			activeTab.down('#footer').collapse(Ext.Component.DIRECTION_BOTTOM,false)
	
			//Seta Botão Confirma: Incluir
			Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('create')});
		}
	},
	
	
	//ServiceOrder > Log > grid: itemMouseUp
	onItemMouseUp : function( me, record, item, index, e, eOpts ){
		
		//Aba Objecto Pai
		var mainPanel = Ext.getCmp('viewportpanel');
		
		//Aba ativa
		var activeTab = mainPanel.getActiveTab();
		
		//Seta Valores do grid no Form
		Ext.ComponentQuery.query('form #log_os',activeTab)[0].setValue(record.get('serviceorder_id'));
		Ext.ComponentQuery.query('form #log_prevstatus',activeTab)[0].setValue(record.get('prevstatus'));
		Ext.ComponentQuery.query('form #log_curstatus',activeTab)[0].setValue(record.get('curstatus'));
		Ext.ComponentQuery.query('form #log_user',activeTab)[0].setValue(record.get('user_id'));
		Ext.ComponentQuery.query('form #log_datetime',activeTab)[0].setValue(Ext.Date.format(record.get('datetime'), 'm-d-Y g:i A'));
		Ext.ComponentQuery.query('form #log_remark',activeTab)[0].setValue(record.get('remarks'));
		
	},
	
	onToolbarItemSelect: function(item, event, nStatus, lRemark) {
		
		var me		= this									// Controller
			store	= me.getServiceOrderStore(),			// Store
			row		= item.up('grid').getSelection()[0]		// Record
			lChange = false;
		
		
		//Verifica se existe algum item selecionado
		if(row){
			
			//Atualiza Status na Store
			store.findRecord('id',row.get('id'))
				.setStatus(Ext.create('Sam.model.ServiceOrderStatus', {id: nStatus}));
			
			/***********************
			 * 1	NOVA
			 * 2	ATRIBUIDA
			 * 3	EM ANALISE
			 * 4	REATRIBUIR
			 * 5	REJEITADO
			 * 6	AGUARDANDO AUTORIZACAO
			 * 7	ACESSO LIBERADO
			 * 8	EM ANDAMENTO
			 * 9	EM ESPERA
			 * 10	CONCLUIDO
			 * 11	FINALIZADO
			 * 12	REABERTO
			 ***********************/
			
			//Verifica qual a seleção
			switch(nStatus){
			
			case 2:
				
				Ext.create('Sam.view.components.PopUp',{
								title: 'Escolha um Técnico para Atribuir à OS',
								scope: me,
								buttons : [ {
									text : 'Confirma',
									itemId: 'submit',
									scope: me,
							        cls:'x-btn-default-small',
							        iconCls: 'tick-button',
							        handler: function(button) {
							        	
							        	//Aba Objecto Pai
						        		var activeTab = Ext.getCmp('viewportpanel').getActiveTab(),
						        			window = button.up('window'),
						        			record = button.up('window').down('grid').getSelection()[0];
						        		
						        		if(record){
						        			
							        		//Atualiza tecnico na store
							        		store.findRecord('id',row.get('id'))
							        			.setTechnician(Ext.create('Sam.model.Technician',{id: record.get('id')}));
							    			
							        		//Sincroniza e Atualiza Store
							    			this.syncStore(store, 'serviceordergrid',true);
							        		
							    			//Fecha janela
							        		window.close();
							        	}
							        }
							        
								} ],
								items:	[Ext.create('Sam.view.technician.TechnicianGrid',{
									dockedItems:[],
								})],
			
							}).show();
				
				break;

				
			default:
				
				// DEMAIS ESTADOS
				if(lRemark){
					
					var win = Ext.create('Sam.view.components.PopUp',{
								resizable: false,
								scope: me,
								maximizable: false,
								width : 440,
								height : 220,
								title: 'Digite o motivo',
								buttons : [ {
									text : 'Confirma',
									itemId: 'submit',
							        cls:'x-btn-default-small',
							        iconCls: 'tick-button',
							        handler: function(button) {
							        	
							        	var box = button.up('window').down('textareafield'),
							        		window = button.up('window');
							        	
							        	if(box.isValid()){
							        		
							        		//Atualiza valores na store
							        		store.findRecord('id',row.get('id')).set({
						    					logRemark	: box.getValue()
						    				});
							        		
							        		//Sincroniza e Atualiza Store
							    			this.syncStore(store, 'serviceordergrid',true);
							        		
							        		//Fecha janela
							        		window.close();
							        	}
							        }
							        
								}],
								items:	[{
										xtype : 'fieldset',
										title : 'Motivo',
										margin: '5 5 5 5',
										items : [{
											xtype : 'textareafield',
											width: '100%',
											height: '90%',
											allowBlank : false,
											inputAttrTpl: " data-qtip='Motivo da troca' "
										}]
									}],
							})
					
					win.show();
					
				}else{
					
					//Sincroniza e Atualiza Store
	    			this.syncStore(store, 'serviceordergrid',true);
				}
				
				break;
			}
		}		
	},
	
	
	
	//ServiceOrder > form : BtnShowLog button
	onBtnShowLogClick: function(action) {
	
		var mainPanel = Ext.getCmp('viewportpanel');
		
		var activeTab = mainPanel.getActiveTab();
		
		var fieldId = Ext.ComponentQuery.query('#id',activeTab)[0];
		
		var tabId = 'solog-'+fieldId.getRawValue();
		
		var newTab = mainPanel.items.findBy(
				function(tab){
					return tab.id === tabId;
				});
		
		if (!newTab) {
			newTab = mainPanel.add({
				id: tabId,
				xtype: 'serviceorderlog',
				closable: true,
				iconCls: 'magnifier-zoom',
				title: 'Historico da OS: '+fieldId.getValue()
			});
		}
		
		
		mainPanel.setActiveTab(newTab);
		
		activeTab = mainPanel.getActiveTab();
		
		var gridLog = Ext.ComponentQuery.query('grid',activeTab)[0]
		/*
		//Filtra Store
		gridLog.getStore().setFilters([{
			exactMatch: true,
			property: 'serviceorder_id',
			value: parseInt(fieldId.getValue())
			}
		]);
		*/
		var gStore = gridLog.getStore();
		var sStore = this.getServiceOrderStore();
		
		gStore.setData(sStore.getById(fieldId.getValue()).data.log);
		
	},
	
	/*********** Begin Job Controlling ***********/
	onJobBtnShowClick: function() {
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 1 - Visualizar
			activeTab = this.activateTab(1, row.get('id'), 'serviceorderjobform', null, false);
			
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
	
	onJobBtnEditClick: function(){
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 3 - Alterar
			activeTab = this.activateTab(3, row.get('id'), 'serviceorderjobform', null, false);
			
			if(activeTab){
				
				//Retorna Form
				var form = Ext.ComponentQuery.query('form',activeTab)[0].getForm();
				
				//Carrega registro no form
				form.loadRecord(row);
				
				//Desabilita Codigo
				form.findField('id').setEditable(false)
				
				//Seta Botão Confirma: Alterar
				Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('update')});
			}
		}
	},
	
	onJobBtnAddClick: function(){

			
		//Cria Aba: 2 - Incluir
		var activeTab = this.activateTab(2, null, 'serviceorderjobform', null, true);
		
		if(activeTab){
	
			//Seta Botão Confirma: Incluir
			Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('create')});
		}
	},
	
	onJobBtnDeleteClick: function(){
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 4 - Excluir
			activeTab = this.activateTab(4, row.get('id'), 'serviceorderjobform', null, false);
			
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
	
	onJobBtnSubmitAdd: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getServiceOrderJobStore(),							//Store
			record		= Ext.create('Sam.model.ServiceOrderJob');					//Registro
		
		if(form.isValid()){
			
			//Carrega dados do Formulario no registro
			record.set(values);
			
			//Adiciona registro na store
			store.add(record);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, 'serviceorderjobgrid',false);
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onJobBtnSubmitEdit: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getServiceOrderJobStore(),							//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			//Carrega dados do formulario na Store
			store.findRecord('id',record.get('id')).set(values);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, 'serviceorderjobgrid',false);
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onJobBtnSubmitDelete: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getServiceOrderJobStore(),						//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			
			//Apaga registro da Store
			store.remove(record);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, 'serviceorderjobgrid',false);
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	/*********** End Of Job Controlling ***********/

	/*********** Begin Service Order Type Controlling ***********/
	
	onServiceOrderTypeBtnShowClick: function() {
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 1 - Visualizar
			activeTab = this.activateTab(1, row.get('id'), 'serviceordertypeform', null, false);
			
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
	
	onServiceOrderTypeBtnEditClick: function(){
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 3 - Alterar
			activeTab = this.activateTab(3, row.get('id'), 'serviceordertypeform', null, false);
			
			if(activeTab){
				
				//Retorna Form
				var form = Ext.ComponentQuery.query('form',activeTab)[0].getForm();
				
				//Carrega registro no form
				form.loadRecord(row);
				
				//Desabilita Codigo
				form.findField('id').setEditable(false)
				
				//Seta Botão Confirma: Alterar
				Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('update')});
			}
		}
	},
	
	onServiceOrderTypeBtnAddClick: function(){

			
		//Cria Aba: 2 - Incluir
		var activeTab = this.activateTab(2, null, 'serviceordertypeform', null, true);
		
		if(activeTab){
	
			//Seta Botão Confirma: Incluir
			Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('create')});
		}


	},
	
	onServiceOrderTypeBtnDeleteClick: function(){
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 4 - Excluir
			activeTab = this.activateTab(4, row.get('id'), 'serviceordertypeform', null, false);
			
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
	
	onServiceOrderBtnSubmitAdd: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getServiceOrderTypeStore(),							//Store
			record		= Ext.create('Sam.model.ServiceOrderType');					//Registro
		
		
		
		if(form.isValid()){
			
			//Carrega dados do Formulario no registro
			record.set(values);
			
			//Adiciona registro na store
			store.add(record);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, 'serviceordertypegrid',false);
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onServiceOrderTypeBtnSubmitEdit: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getServiceOrderTypeStore(),							//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			//Carrega dados do formulario na Store
			store.findRecord('id',record.get('id')).set(values);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, 'serviceordertypegrid',false);
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onServiceOrderTypeBtnSubmitDelete: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getServiceOrderTypeStore(),							//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			
			//Apaga registro da Store
			store.remove(record);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, 'serviceordertypegrid',false);
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	/*********** End Of ServiceOrderType Controlling ***********/
	
	/*********** Begin Service Order Status Controlling ***********/
	
	onServiceOrderStatusBtnShowClick: function() {
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 1 - Visualizar
			activeTab = this.activateTab(1, row.get('id'), 'serviceorderstatusform', null, false);
			
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
	
	onServiceOrderStatusBtnEditClick: function(){
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 3 - Alterar
			activeTab = this.activateTab(3, row.get('id'), 'serviceorderstatusform', null, false);
			
			if(activeTab){
				
				//Retorna Form
				var form = Ext.ComponentQuery.query('form',activeTab)[0].getForm();
				
				//Carrega registro no form
				form.loadRecord(row);
				
				//Desabilita Codigo
				form.findField('id').setEditable(false)
				
				//Seta Botão Confirma: Alterar
				Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('update')});
			}
		}
	},
	
	onServiceOrderStatusBtnAddClick: function(){

			
		//Cria Aba: 2 - Incluir
		var activeTab = this.activateTab(2, null, 'serviceorderstatusform', null, true);
		
		if(activeTab){
	
			//Seta Botão Confirma: Incluir
			Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('create')});
		}


	},
	
	onServiceOrderStatusBtnDeleteClick: function(){
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 4 - Excluir
			activeTab = this.activateTab(4, row.get('id'), 'serviceorderstatusform', null, false);
			
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
	
	onServiceOrderStatusBtnSubmitAdd: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getServiceOrderStatusStore(),							//Store
			record		= Ext.create('Sam.model.ServiceOrderStatus');					//Registro
		
		
		
		if(form.isValid()){
			
			//Carrega dados do Formulario no registro
			record.set(values);
			
			//Adiciona registro na store
			store.add(record);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, 'serviceorderstatusgrid',false);
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onServiceOrderStatusBtnSubmitEdit: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getServiceOrderStatusStore(),						//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			//Carrega dados do formulario na Store
			store.findRecord('id',record.get('id')).set(values);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, 'serviceorderstatusgrid',false);
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onServiceOrderStatusBtnSubmitDelete: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getServiceOrderStatusStore(),							//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			
			//Apaga registro da Store
			store.remove(record);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, 'serviceorderstatusgrid',false);
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	/*********** End Of Service Order Status Controlling ***********/
	
	/*********** Begin Service Order Rules Controlling ***********/
	
	onSORulesTriggerClick: function(){
	
		var equipmentsPopUp = Ext.create('Sam.view.components.PopUp',{itemId: 'serviceorderrules_userrole'});
		var grid = Ext.create('Sam.view.user.role.RoleGrid');
		
		//Remove Botoes
		grid.remove(Ext.ComponentQuery.query('toolbar',grid)[0], true);
		
		equipmentsPopUp.setTitle('Selecionar Papel de Usuário');
		equipmentsPopUp.add(grid);
		equipmentsPopUp.show();
		
	},

	onUsrRoleSubmitClick: function() {
		
		var row = this.getLookup().down('grid').getSelection()[0];
		
		var activeTab = Ext.getCmp('viewportpanel').getActiveTab();
		
		if(row){
			
			fld = Ext.ComponentQuery.query( 'form #serviceorderrulesform',activeTab)[0];
			
			Ext.ComponentQuery.query('#role_id',fld)[0].setValue(row.get('id'));
			Ext.ComponentQuery.query('#role_desc',fld)[0].setValue(row.get('roleName'));
			
			this.getLookup().close();
		}
		
	},
	
	onSORulesTriggerClick: function(){
		
		var equipmentsPopUp = Ext.create('Sam.view.components.PopUp',{itemId: 'serviceorderrules_userrole'});
		var grid = Ext.create('Sam.view.user.role.RoleGrid');
		
		//Remove Botoes
		grid.remove(Ext.ComponentQuery.query('toolbar',grid)[0], true);
		
		equipmentsPopUp.setTitle('Selecionar Papel de Usuário');
		equipmentsPopUp.add(grid);
		equipmentsPopUp.show();
		
	},
	
	onCurrrentStatusRuleSubmitClick: function() {
		
		var row = this.getLookup().down('grid').getSelection()[0];
		
		var activeTab = Ext.getCmp('viewportpanel').getActiveTab();
		
		if(row){
			
			fld = Ext.ComponentQuery.query( 'form #serviceorderrulesform',activeTab)[0];
			
			Ext.ComponentQuery.query('#curstatus_id',fld)[0].setValue(row.get('id'));
			Ext.ComponentQuery.query('#curstatus_desc',fld)[0].setValue(row.get('desc'));
			
			this.getLookup().close();
		}
		
	},
	
	onSOCurrentStatusTriggerClick: function(){
		
		var equipmentsPopUp = Ext.create('Sam.view.components.PopUp',{itemId: 'serviceorderrules_currentstatusgrid'});
		var grid = Ext.create('Sam.view.serviceOrder.status.StatusGrid');
		
		//Remove Botoes
		grid.remove(Ext.ComponentQuery.query('toolbar',grid)[0], true);
		
		equipmentsPopUp.setTitle('Selecionar Status Atual da Ordem de Serviço');
		equipmentsPopUp.add(grid);
		equipmentsPopUp.show();
		
	},
	
	onNextStatusRuleSubmitClick: function() {
		
		var row = this.getLookup().down('grid').getSelection()[0];
		
		var activeTab = Ext.getCmp('viewportpanel').getActiveTab();
		
		if(row){
			
			fld = Ext.ComponentQuery.query( 'form #serviceorderrulesform',activeTab)[0];
			
			Ext.ComponentQuery.query('#nxtstatus_id',fld)[0].setValue(row.get('id'));
			Ext.ComponentQuery.query('#nxtstatus_desc',fld)[0].setValue(row.get('desc'));
			
			this.getLookup().close();
		}
		
	},
	
	onSONextStatusTriggerClick: function(){
		
		var equipmentsPopUp = Ext.create('Sam.view.components.PopUp',{itemId: 'serviceorderrules_nextstatusgrid'});
		var grid = Ext.create('Sam.view.serviceOrder.status.StatusGrid');
		
		//Remove Botoes
		grid.remove(Ext.ComponentQuery.query('toolbar',grid)[0], true);
		
		equipmentsPopUp.setTitle('Selecionar Próximo Status da Ordem de Serviço');
		equipmentsPopUp.add(grid);
		equipmentsPopUp.show();
		
	},

	
	onServiceOrderRulesBtnShowClick: function() {
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 1 - Visualizar
			activeTab = this.activateTab(1, row.get('id'), 'serviceorderrulesform', null, false);
			
			if(activeTab){
			
				//Retorna Form
				var form = Ext.ComponentQuery.query('form',activeTab)[0].getForm();
				
				//Carrega registro no form
				form.loadRecord(row);
				
				//Campos a desabilitar
				var fields = Ext.ComponentQuery.query('form field',activeTab);
				
				//Desabilita Campos
				Ext.each(fields,function(f){f.setReadOnly(true)});
				
				//Seta Botão Confirma: 1 - Visualizar
				Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('read')});
				
			}
		}
	},
	
	onServiceOrderRulesBtnEditClick: function(){
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 3 - Alterar
			activeTab = this.activateTab(3, row.get('id'), 'serviceorderrulesform', null, false);
			
			if(activeTab){
				
				//Retorna Form
				var form = Ext.ComponentQuery.query('form',activeTab)[0].getForm();
				
				//Carrega registro no form
				form.loadRecord(row);
				
				//Desabilita Codigo
				form.findField('id').setEditable(false);
				
				//Seta Botão Confirma: Alterar
				Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('update')});
			}
		}
	},
	
	onServiceOrderRulesBtnAddClick: function(){

			
		//Cria Aba: 2 - Incluir
		var activeTab = this.activateTab(2, null, 'serviceorderrulesform', null, true);
		
		if(activeTab){
	
			//Seta Botão Confirma: Incluir
			Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('create')});
		}


	},
	
	onServiceOrderRulesBtnDeleteClick: function(){
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 4 - Excluir
			activeTab = this.activateTab(4, row.get('id'), 'serviceorderrulesform', null, false);
			
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
	
	onServiceOrderRulesBtnSubmitAdd: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getServiceOrderRulesStore(),							//Store
			record		= Ext.create('Sam.model.ServiceOrderRules');					//Registro

		if(form.isValid()){
			
			//Carrega dados do Formulario no registro
			record.set(values);
			
			//Carrega User Role
			record.set({role: Ext.create('Sam.model.UserRole',{id: values.role_id, roleName: values.role_desc})})
			
			//Carrega Current Status
			record.set({curstatus: Ext.create('Sam.model.ServiceOrderStatus',{id: values.curstatus_id, desc: values.curstatus_desc})})
			
			//Carrega Next Status
			record.set({nxtstatus: Ext.create('Sam.model.ServiceOrderStatus',{id: values.nxtstatus_id, desc: values.nxtstatus_desc})})
			
			//Adiciona registro na store
			store.add(record);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, 'serviceorderrulesgrid',false);
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onServiceOrderRulesBtnSubmitEdit: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getServiceOrderRulesStore(),							//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			//Carrega dados do formulario na Store
			store.findRecord('id',record.get('id')).set(values);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, 'serviceorderrulesgrid',false);
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onServiceOrderRulesBtnSubmitDelete: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getServiceOrderRulesStore(),							//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			
			//Apaga registro da Store
			store.remove(record);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, 'serviceorderrulesgrid',false);
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	/*********** End Of Service Order Rules Controlling ***********/
		
	
	/*********** Common Methods***********/
	activateTab : function(action, id, xtype, uTitle, lockId){
		
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
		if(action == 2 && lockId){
			Ext.ComponentQuery.query('#id', newTab)[0].setVisible(false);
			
		} else if (action == 2 && lockId == false) {
			Ext.ComponentQuery.query('#id', newTab)[0].setEditable(true);
			
		}
		
		//Variavel para retornar aba ativa
		activeTab = mainPanel.getActiveTab();
		
		return activeTab;
		
	},
	
	syncStore: function(store, comp, lConfirm){
		
		var lConfirm = typeof lConfirm != 'undefined' ? lConfirm : false;

		if(lConfirm){
			Ext.Msg.show({
			    title:'Confirma troca?',
			    message: 'Voce está prestes a mudar o estado da OS. Confirma operação?',
			    buttons: Ext.Msg.YESNO,
			    icon: Ext.Msg.QUESTION,
			    fn: function(btn) {
			        if (btn === 'yes') {

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
						
			        } else if (btn === 'no') {
			            console.log('No pressed');
			        } else {
			            console.log('Cancel pressed');
			        } 
			    }
			});
			
		}else{
			
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
		
	}
	
});
