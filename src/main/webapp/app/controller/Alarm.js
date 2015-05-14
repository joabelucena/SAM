Ext.define('Sam.controller.Alarm', {
	extend: 'Ext.app.Controller',
	
	stores:['Alarm',
	     	'AlarmGroup',
	     	'AlarmType',
	     	'AlarmFilter'],
	
	views: ['alarm.AlarmGrid',
	        'alarm.AlarmForm',
	        'alarm.group.GroupGrid',
	        'alarm.group.GroupForm',
	        'alarm.filter.FilterGrid',
	        'alarm.filter.FilterForm',
	        'alarm.type.TypeGrid',
	        'alarm.type.TypeForm'],
    
	refs: [{ref: 'lookup', selector: 'popup'}],
	
	init: function() {
		
		this.control({
			'grid': {
				render: this.gridOnRender
			},
			
			'form toolbar #btnDiscard' :{
				click:   function(){Ext.getCmp('viewportpanel').getActiveTab().close()},
			},
			
			/* Buttons Listeners: Alarm
			 *  
			 */
			'#alarmform_alarm #submit' :{
				click: this.AlarmFormAlarmSubmit,
			},
			
			'#alarmform_model #submit' :{
				click: this.AlarmFormModelSubmit,
			},
			
			'#alarmform_type #submit' :{
				click: this.AlarmFormTypeSubmit,
			},
			
			'#alarmform_group #submit' :{
				click: this.AlarmFormGroupSubmit,
			},
			
			'#alarmform_severity #submit' :{
				click: this.AlarmFormSeveritySubmit,
			},
			
			'#alarmform toolbar #btnSubmit' :{
				create: this.onAlarmBtnSubmitAdd,
				read:   function(){Ext.getCmp('viewportpanel').getActiveTab().close()},
				update: this.onAlarmBtnSubmitEdit,
				remove: this.onAlarmBtnSubmitDelete,
				
			},
			
			'#alarmgrid toolbar #btnShow' :{
				click: this.onAlarmBtnShowClick
			},
			
			'#alarmgrid toolbar #btnEdit' :{
				click: this.onAlarmBtnEditClick
			},
			
			'#alarmgrid toolbar #btnAdd' :{
				click: this.onAlarmBtnAddClick
			},
			
			'#alarmgrid toolbar #btnDelete' :{
				click: this.onAlarmBtnDeleteClick
			},

			/* Buttons Listeners: Group
			 *  
			 */
			'#alarmgroupform toolbar #btnSubmit' :{
				create: this.onGroupBtnSubmitAdd,
				read:   function(){Ext.getCmp('viewportpanel').getActiveTab().close()},
				update: this.onGroupBtnSubmitEdit,
				remove: this.onGroupBtnSubmitDelete,
				
			},
			
			'#alarmgroupgrid toolbar #btnShow' :{
				click: this.onGroupBtnShowClick
			},
			
			'#alarmgroupgrid toolbar #btnEdit' :{
				click: this.onGroupBtnEditClick
			},
			
			'#alarmgroupgrid toolbar #btnAdd' :{
				click: this.onGroupBtnAddClick
			},
			
			'#alarmgroupgrid toolbar #btnDelete' :{
				click: this.onGroupBtnDeleteClick
			},
			
			
			/* Buttons Listeners: Type
			 *  
			 */
			'#alarmtypeform toolbar #btnSubmit' :{
				create: this.onTypeBtnSubmitAdd,
				read:   function(){Ext.getCmp('viewportpanel').getActiveTab().close()},
				update: this.onTypeBtnSubmitEdit,
				remove: this.onTypeBtnSubmitDelete,
				
			},
			
			'#alarmtypegrid toolbar #btnShow' :{
				click: this.onTypeBtnShowClick
			},
			
			'#alarmtypegrid toolbar #btnEdit' :{
				click: this.onTypeBtnEditClick
			},
			
			'#alarmtypegrid toolbar #btnAdd' :{
				click: this.onTypeBtnAddClick
			},
			
			'#alarmtypegrid toolbar #btnDelete' :{
				click: this.onTypeBtnDeleteClick
			},
			
			/* Buttons Listeners: Filter
			 *  
			 */
			'#alarmfilterform_equipment #submit' :{
				click: this.FilterFormEquipSubmit,
			},
			
			'#alarmfilterform_alarm #submit' :{
				click: this.FilterFormAlarmSubmit,
			},
			
			'#alarmfilterform toolbar #btnSubmit' :{
				create: this.onFilterBtnSubmitAdd,
				read:   function(){Ext.getCmp('viewportpanel').getActiveTab().close()},
				update: this.onFilterBtnSubmitEdit,
				remove: this.onFilterBtnSubmitDelete,
				
			},
			
			'#alarmfiltergrid toolbar #btnShow' :{
				click: this.onFilterBtnShowClick
			},
			
			'#alarmfiltergrid toolbar #btnEdit' :{
				click: this.onFilterBtnEditClick
			},
			
			'#alarmfiltergrid toolbar #btnAdd' :{
				click: this.onFilterBtnAddClick
			},
			
			'#alarmfiltergrid toolbar #btnDelete' :{
				click: this.onFilterBtnDeleteClick
			}			
			
		});
	},
	
	gridOnRender: function(me, options){
		me.getStore().reload();
		me.getView().refresh();
	},
	
	/*********** Begin Alarm Controlling ***********/
	AlarmFormAlarmSubmit: function(){
		
		var row = this.getLookup().down('grid').getSelection()[0];
		
		var activeTab = Ext.getCmp('viewportpanel').getActiveTab();
		
		if(row){
			
			Ext.ComponentQuery.query('#alarm_id',activeTab)[0].setValue(row.get('id'));
			Ext.ComponentQuery.query('#alarm_desc',activeTab)[0].setValue(row.get('desc'));
			
			this.getLookup().close();
		}
	},

	AlarmFormModelSubmit: function(){
		
	var row = this.getLookup().down('grid').getSelection()[0];
		
		var activeTab = Ext.getCmp('viewportpanel').getActiveTab();
		
		if(row){
			
			Ext.ComponentQuery.query('#model_id',activeTab)[0].setValue(row.get('id'));
			Ext.ComponentQuery.query('#model_desc',activeTab)[0].setValue(row.get('desc'));
			
			this.getLookup().close();
		}
		
	},
	
	AlarmFormGroupSubmit: function(){
		
		var row = this.getLookup().down('grid').getSelection()[0];
			
			var activeTab = Ext.getCmp('viewportpanel').getActiveTab();
			
			if(row){
				
				Ext.ComponentQuery.query('#group_id',activeTab)[0].setValue(row.get('id'));
				Ext.ComponentQuery.query('#group_desc',activeTab)[0].setValue(row.get('desc'));
				
				this.getLookup().close();
			}
			
		},
			
		
	AlarmFormTypeSubmit: function(){
	var row = this.getLookup().down('grid').getSelection()[0];
		
		var activeTab = Ext.getCmp('viewportpanel').getActiveTab();
		
		if(row){
			
			Ext.ComponentQuery.query('#type_id',activeTab)[0].setValue(row.get('id'));
			Ext.ComponentQuery.query('#type_desc',activeTab)[0].setValue(row.get('desc'));
			
			this.getLookup().close();
		}
	},

	AlarmFormSeveritySubmit: function(){
	
		var row = this.getLookup().down('grid').getSelection()[0];
		
		var activeTab = Ext.getCmp('viewportpanel').getActiveTab();
		
		if(row){
			
			Ext.ComponentQuery.query('#severity_id',activeTab)[0].setValue(row.get('id'));
			Ext.ComponentQuery.query('#severity_desc',activeTab)[0].setValue(row.get('desc'));
			
			this.getLookup().close();
		}
		
	},
	
	onAlarmBtnShowClick: function() {
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 1 - Visualizar
			activeTab = this.activateTab(1, row.get('id'), 'alarmform', null, true);
			
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
	
	onAlarmBtnEditClick: function(){
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 3 - Alterar
			activeTab = this.activateTab(3, row.get('id'), 'alarmform', null, true);
			
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
	
	onAlarmBtnAddClick: function(){

			
		//Cria Aba: 2 - Incluir
		var activeTab = this.activateTab(2, null, 'alarmform', null, false);
		
		if(activeTab){
	
			//Seta Botão Confirma: Incluir
			Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('create')});
			
			//Habilita edição do ID
			Ext.ComponentQuery.query('#id' , activeTab)[0].setEditable(true);
		}


	},
	
	onAlarmBtnDeleteClick: function(){
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 4 - Excluir
			activeTab = this.activateTab(4, row.get('id'), 'alarmform', null, true);
			
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
	
	onAlarmBtnSubmitAdd: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getAlarmStore(),										//Store
			record		= Ext.create('Sam.model.Alarm');							//Registro
		
		
		
		if(form.isValid()){
			
			//Carrega dados do Formulario no registro
			record.set(values);
			
			//Carrega Objetos
			record.set(
					{	group:		Ext.create('Sam.model.AlarmGroup'		,{id: values.group_id	, desc: values.group_desc	}),
						normAlarm:	Ext.create('Sam.model.Alarm'			,{id: values.alarm_id	, desc: values.alarm_desc	}),
						type:		Ext.create('Sam.model.AlarmType'		,{id: values.type_id	, desc: values.type_desc	}),
						model: 		Ext.create('Sam.model.EquipmentModel'	,{id: values.model_id	, desc: values.model_desc	}),
						severity:	Ext.create('Sam.model.SeverityLevel'	,{id: values.severity_id, desc: values.severity_desc}),
			});
			
			
			//Adiciona registro na store
			store.add(record);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, '#alarmgrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onAlarmBtnSubmitEdit: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getAlarmStore(),										//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			//Carrega dados do formulario na Store
			store.findRecord('id',record.get('id')).set(values);
			
			//Carrega Objetos
			store.findRecord('id',record.get('id')).set(
					{	group:		Ext.create('Sam.model.AlarmGroup'		,{id: values.group_id	, desc: values.group_desc	}),
						normAlarm:	Ext.create('Sam.model.Alarm'			,{id: values.alarm_id	, desc: values.alarm_desc	}),
						type:		Ext.create('Sam.model.AlarmType'		,{id: values.type_id	, desc: values.type_desc	}),
						model: 		Ext.create('Sam.model.EquipmentModel'	,{id: values.model_id	, desc: values.model_desc	}),
						severity:	Ext.create('Sam.model.SeverityLevel'	,{id: values.severity_id, desc: values.severity_desc}),
			});
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, '#alarmgrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onAlarmBtnSubmitDelete: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getAlarmStore(),						//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			
			//Apaga registro da Store
			store.remove(record);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, '#alarmgrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	/*********** End Of Alarm Controlling ***********/
	
	
	
	/*********** Begin Group Controlling ***********/
	onGroupBtnShowClick: function() {
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 1 - Visualizar
			activeTab = this.activateTab(1, row.get('id'), 'alarmgroupform', null, true);
			
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
	
	onGroupBtnEditClick: function(){
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 3 - Alterar
			activeTab = this.activateTab(3, row.get('id'), 'alarmgroupform', null, true);
			
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
	
	onGroupBtnAddClick: function(){

			
		//Cria Aba: 2 - Incluir
		var activeTab = this.activateTab(2, null, 'alarmgroupform', null, true);
		
		if(activeTab){
	
			//Seta Botão Confirma: Incluir
			Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('create')});
		}


	},
	
	onGroupBtnDeleteClick: function(){
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 4 - Excluir
			activeTab = this.activateTab(4, row.get('id'), 'alarmgroupform', null, true);
			
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
	
	onGroupBtnSubmitAdd: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getAlarmGroupStore(),						//Store
			record		= Ext.create('Sam.model.AlarmGroup');			//Registro
		
		
		
		if(form.isValid()){
			
			//Carrega dados do Formulario no registro
			record.set(values);
			
			//Adiciona registro na store
			store.add(record);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, '#alarmgroupgrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onGroupBtnSubmitEdit: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getAlarmGroupStore(),						//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			//Carrega dados do formulario na Store
			store.findRecord('id',record.get('id')).set(values);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, '#alarmgroupgrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onGroupBtnSubmitDelete: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getAlarmGroupStore(),						//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			
			//Apaga registro da Store
			store.remove(record);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, '#alarmgroupgrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	
	/*********** End Of Group Controlling ***********/
	
	/*********** Begin Type Controlling ***********/
	onTypeBtnShowClick: function() {
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 1 - Visualizar
			activeTab = this.activateTab(1, row.get('id'), 'alarmtypeform', null, true);
			
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
			activeTab = this.activateTab(3, row.get('id'), 'alarmtypeform', null, true);
			
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
		var activeTab = this.activateTab(2, null, 'alarmtypeform', null, true);
		
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
			activeTab = this.activateTab(4, row.get('id'), 'alarmtypeform', null, true);
			
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
			store		= this.getAlarmTypeStore(),						//Store
			record		= Ext.create('Sam.model.AlarmType');			//Registro
		
		
		
		if(form.isValid()){
			
			//Carrega dados do Formulario no registro
			record.set(values);
			
			//Adiciona registro na store
			store.add(record);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, '#alarmtypegrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onTypeBtnSubmitEdit: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getAlarmTypeStore(),						//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			//Carrega dados do formulario na Store
			store.findRecord('id',record.get('id')).set(values);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, '#alarmtypegrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onTypeBtnSubmitDelete: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getAlarmTypeStore(),						//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			
			//Apaga registro da Store
			store.remove(record);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, '#alarmtypegrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	
	/*********** End Of Type Controlling ***********/
	
	
	/*********** Begin Filter Controlling ***********/
	FilterFormEquipSubmit: function(){
		
		var row = this.getLookup().down('grid').getSelection()[0];
		
		var activeTab = Ext.getCmp('viewportpanel').getActiveTab();
		
		if(row){
			
			Ext.ComponentQuery.query('#equip_id',activeTab)[0].setValue(row.get('id'));
			
			this.getLookup().close();
		}
	},
	
	FilterFormAlarmSubmit: function(){
		
		var row = this.getLookup().down('grid').getSelection()[0];
		
		var activeTab = Ext.getCmp('viewportpanel').getActiveTab();
		
		if(row){
			
			Ext.ComponentQuery.query('#alarm_id',activeTab)[0].setValue(row.get('id'));
			
			this.getLookup().close();
		}
	},

	onFilterBtnShowClick: function() {
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 1 - Visualizar
			activeTab = this.activateTab(1, row.get('id'), 'alarmfilterform', null, true);
			
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
	
	onFilterBtnEditClick: function(){
		
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 3 - Alterar
			activeTab = this.activateTab(3, row.get('id'), 'alarmfilterform', null, true);
			
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
	
	onFilterBtnAddClick: function(){

			
		//Cria Aba: 2 - Incluir
		var activeTab = this.activateTab(2, null, 'alarmfilterform', null, true);
		
		if(activeTab){
	
			//Seta Botão Confirma: Incluir
			Ext.ComponentQuery.query('#btnSubmit',activeTab)[0].setHandler(function() {this.fireEvent('create')});
		}


	},
	
	onFilterBtnDeleteClick: function(){
		//Linha selecionada
		var row = Ext.getCmp('viewportpanel').getActiveTab().getSelection()[0];
		
		//Tem Registro Selecionado
		if(row){
			
			//Cria Aba: 4 - Excluir
			activeTab = this.activateTab(4, row.get('id'), 'alarmfilterform', null, true);
			
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
	
	onFilterBtnSubmitAdd: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getAlarmFilterStore(),						//Store
			record		= Ext.create('Sam.model.AlarmFilter');			//Registro
		
		
		
		if(form.isValid()){
			
			//Carrega dados do Formulario no registro
			record.set(values);
			
			//Carrega Objetos
			record.set(
					{	equipment:	Ext.create('Sam.model.Equipment'		,{id: values.equip_id }),
						alarm:		Ext.create('Sam.model.Alarm'			,{id: values.alarm_id }),
			});
			
			//Adiciona registro na store
			store.add(record);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, '#alarmfiltergrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onFilterBtnSubmitEdit: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getAlarmFilterStore(),								//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			//Carrega dados do formulario na Store
			store.findRecord('id',record.get('id')).set(values);
			
			//Carrega Objetos
			store.findRecord('id',record.get('id')).set(
					{	equipment:	Ext.create('Sam.model.Equipment'		,{id: values.equip_id }),
						alarm:		Ext.create('Sam.model.Alarm'			,{id: values.alarm_id }),
			});
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, '#alarmfiltergrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	onFilterBtnSubmitDelete: function(){
		
		var mainPanel	= Ext.getCmp('viewportpanel'),								//Aba Objecto Pai
			activeTab	= mainPanel.getActiveTab(),									//Aba ativa
			form		= Ext.ComponentQuery.query('form',activeTab)[0].getForm(),	//Formulario	
			values		= form.getValues(),											//Dados do Formulario
			store		= this.getAlarmFilterStore(),						//Store
			record		= form.getRecord();											//Registro
		
		if(form.isValid()){
			
			//Apaga registro da Store
			store.remove(record);
			
			//Sincroniza e Atualiza Store
			this.syncStore(store, '#alarmfiltergrid');
			
			//Fecha Aba
			activeTab.close();
		}
	},
	
	
	/*********** End Of Filter Controlling ***********/
	
	
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
			Ext.ComponentQuery.query('#id' , newTab)[0].setVisible(false);
		}
		
		//Variavel para retornar aba ativa
		activeTab = mainPanel.getActiveTab();
		
		return activeTab;
		
	}
	
});
