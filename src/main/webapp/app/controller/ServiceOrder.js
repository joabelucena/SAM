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
	        'Sam.view.equipment.EquipmentsGrid',
	        'Sam.view.serviceOrder.type.TypeGrid',
	        'Sam.view.serviceOrder.type.TypeForm',
	        'Sam.view.serviceOrder.status.StatusGrid',
	        'Sam.view.serviceOrder.status.StatusForm',
	        'Sam.view.serviceOrder.rules.RulesGrid',
	        'Sam.view.serviceOrder.rules.RulesForm'
	        ],

	init: function() {
		
		this.control({
			'serviceordergrid': {
				itemdblclick: this.onBtnShowSoClick
			},
			'toolbar #btnNewSo' :{
				click: this.onBtnNewSoClick
			},
			'serviceorderloggrid':{
				itemmouseup: this.onItemMouseUp,
			},
			'toolbar #btnOk' :{
				click: this.onBtnOkClick
			},
			'toolbar #btnShowSo' :{
				click: this.onBtnShowSoClick
			},
			'toolbar #btnShowLog' :{
				click: this.onBtnShowLogClick
			},
			'toolbar #btnChangeSts' :{
				click: this.onBtnChangeStsClick
			},
			'#trg_equipment_id' :{
				click: this.onTriggerClick,
			},
			'#serviceorderform_equipment #submit' :{ 
				click: this.f3Confirm,
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

	//ServiceOrder > form > trigger:equipment_id: confirm button
	f3Confirm: function(component) {
		
		var grid = Ext.ComponentQuery.query('popup grid')[0];
		var equipmentId = grid.getSelection()[0].get('id');
		
		Ext.ComponentQuery.query('form #trg_equipment_id')[0].setValue(grid.getSelection()[0].get('id'));
		Ext.ComponentQuery.query('form #equipment_model')[0].setValue(grid.getSelection()[0].get('model'));
		Ext.ComponentQuery.query('form #equipment_manufacturer')[0].setValue(grid.getSelection()[0].get('manufacturer'));
		Ext.ComponentQuery.query('form #equipment_subsystem')[0].setValue(grid.getSelection()[0].get('system'));
		Ext.ComponentQuery.query('form #equipment_site')[0].setValue(grid.getSelection()[0].get('site'));
		Ext.ComponentQuery.query('popup')[0].close();
	
	},	
	
	//ServiceOrder > form > trigger:equipment_id: popup button
	onTriggerClick: function(){
	
		var equipmentsPopUp = Ext.create('Sam.view.components.PopUp',{itemId: 'serviceorderform_equipment'});
		var grid = Ext.create('Sam.view.equipment.EquipmentsGrid');
		
		equipmentsPopUp.setTitle('Selecionar Equipamento');
		equipmentsPopUp.add(grid);
		equipmentsPopUp.show();
		
	},
	
	//ServiceOrder > grid : btnNewSo button
	onBtnNewSoClick: function() {
		
		//Aba Objecto Pai
		var mainPanel = Ext.getCmp('viewportpanel');
		
		//Aba ativa
		var activeTab = mainPanel.getActiveTab();
		
		var newTab = mainPanel.items.findBy(
				function(tab){
					return tab.id === 'newso';
				});
		
		if (!newTab) {
			newTab = mainPanel.add({
				id: 'newso',
				xtype: 'serviceorderform',
				closable: true,
				iconCls: 'magnifier-zoom',
				title: 'Nova OS'
			});
		}
		
		mainPanel.setActiveTab(newTab);
		
		activeTab = mainPanel.getActiveTab();
		
		//Formulario
		var form = Ext.ComponentQuery.query('form',activeTab)[0];
		
		//Desabilita exibicao do log
		Ext.ComponentQuery.query('#btnShowLog',activeTab)[0].setVisible(false);
		
		//Disabilita o campo 'Codigo da OS'
		Ext.ComponentQuery.query('form #id',activeTab)[0].setVisible(false);
		
		//Desabilita Novo Status
		form.remove(Ext.ComponentQuery.query('form #fldNewStatus',activeTab)[0]);
		
		//Seta Botão Confirma: Incluir
		Ext.ComponentQuery.query('#btnOk',activeTab)[0].setHandler(function() {this.fireEvent('click',2)});
		
		// Abertura OS : type ComboBox
		Ext.ComponentQuery.query('form #type',activeTab)[0].setStore(Ext.data.Store({
			fields: ['id','desc'],
			proxy: {
		         type: 'ajax',
		         url: 'so/gettypes',
		         reader: {
		             type: 'json',
		             root: 'type'
		         }
		     },
		}));
		
		// Abertura OS : priority ComboBox
		Ext.ComponentQuery.query('form #priority',activeTab)[0].setStore(Ext.data.Store({
			fields: ['id','desc'],
			proxy: {
		         type: 'ajax',
		         url: 'severity/load',
		         reader: {
		             type: 'json',
		             root: 'data'
		         }
		     },
		}));
	},
	
	//ServiceOrder > form : btnOk button
	onBtnOkClick: function(action){
		
		//Aba Objecto Pai
		var mainPanel = Ext.getCmp('viewportpanel');
		
		//Aba ativa
		var activeTab = mainPanel.getActiveTab();
		
		//Formulario
		var form = Ext.ComponentQuery.query('form',activeTab)[0];
		
		//1 - Visualiza
		if(action == 1){
		
		//2 - Incluir
		}else if(action == 2){
			
			/** Abertura de Ordem de Serviço **/
			
			// Verifica se o form eh valido
			if(form.isValid()){
				
				Ext.MessageBox.show({
			        title: 'Abertura de OS',
			        msg: 'Confirma a Abertura da OS?',
			        buttons: Ext.MessageBox.OKCANCEL,
			        icon: Ext.MessageBox.WARNING,
			        fn: function(btn,  knowId, knowCheck){
			            if(btn == 'ok'){
			            	
			            	Ext.Ajax.request({
			            		url : 'so/newFromSo',
			            		method : 'POST',
			            		
			            		params: {
			            			equipId: Ext.ComponentQuery.query('form #trg_equipment_id',activeTab)[0].getRawValue(),
			            			startForecast: Ext.ComponentQuery.query('form #start_date',activeTab)[0].getRawValue() + " - " + Ext.ComponentQuery.query('form #start_hour',activeTab)[0].getRawValue(),
			            			endForecast: Ext.ComponentQuery.query('form #end_date',activeTab)[0].getRawValue() + " - " + Ext.ComponentQuery.query('form #end_hour',activeTab)[0].getRawValue(),
			            			type: Ext.ComponentQuery.query('form #type',activeTab)[0].getValue(),
			            			priorityId: Ext.ComponentQuery.query('form #priority',activeTab)[0].getValue(),
			            			obs: Ext.ComponentQuery.query('form #remark',activeTab)[0].getRawValue()	            			
			            			
			            		},
		
			            		success: function (result, request) {
			            			
			            			var jsonResp = Ext.util.JSON.decode(result.responseText);
		
				                    if (jsonResp.result != "SUCCESS") {
				                    	Ext.Msg.alert('Falha na Abertura da OS', jsonResp.result);        	 
				                    }else{
				                    	
				                    	Ext.Msg.alert('Nova OS', 'Os No: '+jsonResp.soId+' gerada com sucesso!');
				                    	
				                    	//Fecha aba de abertura de OS
				                    	if(activeTab){
				                    		activeTab.close();
				                    	}
				                    	
				                    	//Atualiza Stores dos grids de OS abertos
				                    	Ext.each(Ext.ComponentQuery.query('#serviceordergrid'),function(f){
				                    		f.getStore().reload();
				                    	});
				                    	
				                    }
			                             
			            		},
			                    
			            		failure: function (result, request) {
			            			Ext.Msg.alert('Falha na Abertura da OS', result.status); 
			                    }
			            			
			            	});
		            	
		            } else if(btn == 'cancel') {
		            	
		            }
		        }
				});
			}
			
			/* FIM */
			
		//3 - Alterar
		}else if (action == 3){
			alert('Altera');

		//4 - Mudar Status 
		}else if (action == 4){
			
			var nStatus = Ext.ComponentQuery.query('form #n_cmbStatus',activeTab)[0].getValue();
			var nStoped = Ext.ComponentQuery.query('form #n_cmbEquipStop',activeTab)[0].getValue();
			var nId = Ext.ComponentQuery.query('form #id',activeTab)[0].getValue();
			var cObs = Ext.ComponentQuery.query('form #n_txtRemark',activeTab)[0].getValue();
			
			Ext.Ajax.request({
        		url : 'so/changestatus',
        		method : 'POST',
        		
        		params: {
        			soId	: nId,
        			stsId	: nStatus,
        			stop	: nStoped,
        			obs		: cObs
        		},

        		success: function (result, request) {
        			
        			var jsonResp = Ext.util.JSON.decode(result.responseText);

                    if (jsonResp.result != "SUCCESS") {
                    	Ext.Msg.alert('Falha na Mudança de Estado', jsonResp.result);        	 
                    }else{
                    	
                    	Ext.Msg.alert('Mudança Gerada com Sucesso', 'Mudança Gerada com Sucesso!');
                    	
                    	//Fecha aba de abertura de OS
                    	if(activeTab){
                    		activeTab.close();
                    	}
                    	
                    	gridOs = mainPanel.getActiveTab();
                    	
                    	gridOs.getStore().load();

                    }
        		},
                
        		failure: function (result, request) {
        			Ext.Msg.alert('Falha na Mudança de Estado', result.status); 
                }
        	});
			
		}else{

		}

	},
	
	//ServiceOrder > grid : BtnChangeSts button
	onBtnChangeStsClick:function(){
		
		//Aba Objecto Pai
		var mainPanel = Ext.getCmp('viewportpanel');
		
		//Aba ativa
		var activeTab = mainPanel.getActiveTab();
		
		//Linha selecionada
		var row = activeTab.getSelection()[0];
		
		var tabId = 'cgsts-'+row.get('id');
		
		//Tem Registro Selecionado
		if(typeof row !== 'undefined'){
			
			var newTab = mainPanel.items.findBy(
					function(tab){
						return tab.id === tabId;
					});
			
			if (!newTab) {
				newTab = mainPanel.add({
					id: tabId,
					xtype: 'serviceorderform',
					closable: true,
					iconCls: 'magnifier-zoom',
					title: 'Mudar Estado OS: '+row.get('id')
				});
			}
			
			mainPanel.setActiveTab(newTab);
			
			/*** 'Seta funcao do botao ***/
			activeTab = mainPanel.getActiveTab();
			
			//Seta Botão Confirma: Inlcuir
			Ext.ComponentQuery.query('#btnOk',activeTab)[0].setHandler(function() {this.fireEvent('click',4)});
			
			
			// MudarStatus: ComboBox
			Ext.ComponentQuery.query('form #n_cmbStatus',activeTab)[0].setStore(Ext.data.Store({
				fields: ['id','desc'],
				
				proxy: {
			         type: 'ajax',
			         url: 'so/getallowedstatus',
			         
			         extraParams: {
			        	 soId: row.get('id')	            			
			         },
			         
			         reader: {
			             type: 'json',
			             root: 'rule'
			         }
			     },
			}));
			
			//Campos a desabilitar
			var fields = Ext.ComponentQuery.query('form #soInfo field, form #equipmentInfo field',activeTab)
			
			//Desabilita Campos
			Ext.each(fields,function(f){f.setReadOnly(true)})
			
			
			/**** Seta Campos do Form *****/
			
			// Visulizar OS : type ComboBox
			Ext.ComponentQuery.query('form #type',activeTab)[0].setStore(Ext.data.Store({
				fields: ['id','desc'],
				data: [{id: row.data.type.sot_id, desc: row.data.type.sot_description}]
			}));
			
			// Visulizar OS : priority ComboBox
			Ext.ComponentQuery.query('form #priority',activeTab)[0].setStore(Ext.data.Store({
				fields: ['id','desc'],
				data: [{id: row.data.priority.sle_id, desc: row.data.priority.sle_description}]
			}));
			
			Ext.ComponentQuery.query('#id',activeTab)[0].setValue(row.get('id'));
			
			Ext.ComponentQuery.query('#end_date',activeTab)[0].setValue(Ext.Date.format(row.get('end_forecast'), 'd/m/Y'));	//data termino
			Ext.ComponentQuery.query('#end_hour',activeTab)[0].setValue(Ext.Date.format(row.get('end_forecast'), 'g:i'));	//hora termino
			Ext.ComponentQuery.query('#priority',activeTab)[0].setValue(row.data.priority.sle_description);
			Ext.ComponentQuery.query('#remark',activeTab)[0].setValue(row.get('remarks'));
			Ext.ComponentQuery.query('#start_date',activeTab)[0].setValue(Ext.Date.format(row.get('start_forecast'), 'd/m/Y'));	//data termino
			Ext.ComponentQuery.query('#start_hour',activeTab)[0].setValue(Ext.Date.format(row.get('start_forecast'), 'g:i'));	//hora termino
			Ext.ComponentQuery.query('#type',activeTab)[0].setValue(row.data.type.sot_id)
			Ext.ComponentQuery.query('#trg_equipment_id',activeTab)[0].setValue(row.get('equipment_id'));
			
			Ext.ComponentQuery.query('#equipment_model',activeTab)[0].setValue(row.data.equipment.type.ety_description);
			Ext.ComponentQuery.query('#equipment_manufacturer',activeTab)[0].setValue(row.data.equipment.manufacturer.ema_description);
			Ext.ComponentQuery.query('#equipment_subsystem',activeTab)[0].setValue(row.data.equipment.system.ssy_description);
			Ext.ComponentQuery.query('#equipment_site',activeTab)[0].setValue(row.data.equipment.site.sit_description);
			
			
			/************************************ Missing Fields ************************************/
			//Ext.ComponentQuery.query('#XXXX',activeTab)[0].setValue(row.get('equipment_stop'));
			//Ext.ComponentQuery.query('#XXXX',activeTab)[0].setValue(row.get('event_id'));
			//Ext.ComponentQuery.query('#XXXX',activeTab)[0].setValue(row.get('parent_id'));
			//Ext.ComponentQuery.query('#XXXX',activeTab)[0].setValue(row.get('end'));
			//Ext.ComponentQuery.query('#XXXX',activeTab)[0].setValue(row.get('start'));
			//Ext.ComponentQuery.query('#XXXX',activeTab)[0].setValue(row.get('status'));
			//Ext.ComponentQuery.query('#XXXX',activeTab)[0].setValue(row.get('technician'));	
			/******************************/
		}
	},
	
	//ServiceOrder > grid : BtnShowSo button
	onBtnShowSoClick:function(){
		
		//Aba Objecto Pai
		var mainPanel = Ext.getCmp('viewportpanel');
		
		//Aba ativa
		var activeTab = mainPanel.getActiveTab();
		
		//Linha selecionada
		var row = activeTab.getSelection()[0];
		
		var tabId = 'show-'+row.get('id');
		
		//Tem Registro Selecionado
		if(typeof row !== 'undefined'){
			
			var newTab = mainPanel.items.findBy(
					function(tab){
						return tab.id === tabId;
					});
			
			if (!newTab) {
				newTab = mainPanel.add({
					id: tabId,
					xtype: 'serviceorderform',
					closable: true,
					iconCls: 'magnifier-zoom',
					title: 'Visualizar OS: '+row.get('id')
				});
			}
			
			mainPanel.setActiveTab(newTab);
			
			/*** 'Seta funcao do botao ***/
			activeTab = mainPanel.getActiveTab();
			
			//Seta Botão Confirma: Visualizar
			Ext.ComponentQuery.query('#btnOk',activeTab)[0].setHandler(function() {this.fireEvent('click',1)});
			
			//Desabilita Novo Status
			Ext.ComponentQuery.query('form #fldNewStatus',activeTab)[0].setVisible(false);
			
			//Campos a desabilitar
			var fields = Ext.ComponentQuery.query('form #soInfo field, form #equipmentInfo field',activeTab)
			
			//Desabilita botão 'Confirma'
			Ext.ComponentQuery.query('#btnOk',activeTab)[0].setVisible(false);
			
			//Desabilita Campos
			Ext.each(fields,function(f){f.setReadOnly(true)})
			
			/**** Seta Campos do Form *****/
			
			// Visulizar OS : type ComboBox
			Ext.ComponentQuery.query('form #type',activeTab)[0].setStore(Ext.data.Store({
				fields: ['id','desc'],
				data: [{id: row.data.type.sot_id, desc: row.data.type.sot_description}]
			}));
			
			// Visulizar OS : priority ComboBox
			Ext.ComponentQuery.query('form #priority',activeTab)[0].setStore(Ext.data.Store({
				fields: ['id','desc'],
				data: [{id: row.data.priority.sle_id, desc: row.data.priority.sle_description}]
			}));
			
			Ext.ComponentQuery.query('#id',activeTab)[0].setValue(row.get('id'));
			
			Ext.ComponentQuery.query('#end_date',activeTab)[0].setValue(Ext.Date.format(row.get('end_forecast'), 'd/m/Y'));	//data termino
			Ext.ComponentQuery.query('#end_hour',activeTab)[0].setValue(Ext.Date.format(row.get('end_forecast'), 'g:i'));	//hora termino
			Ext.ComponentQuery.query('#priority',activeTab)[0].setValue(row.data.priority.sle_description);
			Ext.ComponentQuery.query('#remark',activeTab)[0].setValue(row.get('remarks'));
			Ext.ComponentQuery.query('#start_date',activeTab)[0].setValue(Ext.Date.format(row.get('start_forecast'), 'd/m/Y'));	//data termino
			Ext.ComponentQuery.query('#start_hour',activeTab)[0].setValue(Ext.Date.format(row.get('start_forecast'), 'g:i'));	//hora termino
			Ext.ComponentQuery.query('#type',activeTab)[0].setValue(row.data.type.sot_id)
			Ext.ComponentQuery.query('#trg_equipment_id',activeTab)[0].setValue(row.get('equipment_id'));
			
			Ext.ComponentQuery.query('#equipment_model',activeTab)[0].setValue(row.data.equipment.type.ety_description);
			Ext.ComponentQuery.query('#equipment_manufacturer',activeTab)[0].setValue(row.data.equipment.manufacturer.ema_description);
			Ext.ComponentQuery.query('#equipment_subsystem',activeTab)[0].setValue(row.data.equipment.system.ssy_description);
			Ext.ComponentQuery.query('#equipment_site',activeTab)[0].setValue(row.data.equipment.site.sit_description);
			
			
			/************************************ Missing Fields ************************************/
			//Ext.ComponentQuery.query('#XXXX',activeTab)[0].setValue(row.get('equipment_stop'));
			//Ext.ComponentQuery.query('#XXXX',activeTab)[0].setValue(row.get('event_id'));
			//Ext.ComponentQuery.query('#XXXX',activeTab)[0].setValue(row.get('parent_id'));
			//Ext.ComponentQuery.query('#XXXX',activeTab)[0].setValue(row.get('end'));
			//Ext.ComponentQuery.query('#XXXX',activeTab)[0].setValue(row.get('start'));
			//Ext.ComponentQuery.query('#XXXX',activeTab)[0].setValue(row.get('status'));
			//Ext.ComponentQuery.query('#XXXX',activeTab)[0].setValue(row.get('technician'));
			/******************************/
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
		
		
		console.log('para');
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
			this.syncStore(store, 'serviceorderjobgrid');
			
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
			this.syncStore(store, 'serviceorderjobgrid');
			
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
			this.syncStore(store, 'serviceorderjobgrid');
			
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
			this.syncStore(store, 'serviceordertypegrid');
			
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
			this.syncStore(store, 'serviceordertypegrid');
			
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
			this.syncStore(store, 'serviceordertypegrid');
			
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
			this.syncStore(store, 'serviceorderstatusgrid');
			
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
			this.syncStore(store, 'serviceorderstatusgrid');
			
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
			this.syncStore(store, 'serviceorderstatusgrid');
			
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
			this.syncStore(store, 'serviceorderrulesgrid');
			
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
			this.syncStore(store, 'serviceorderrulesgrid');
			
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
			this.syncStore(store, 'serviceorderrulesgrid');
			
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
