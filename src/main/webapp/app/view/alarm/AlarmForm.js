Ext.define('Sam.view.alarm.AlarmForm', {
	extend: 'Ext.Panel',
	requires:['Sam.view.components.FormToolbar'],
	
	alias:  'widget.alarmform',
	
	itemId: 'alarmform',
	
	closable: true,
	
	layout:{
		type: 'fit'
	},
	
	items : [ {
		xtype : 'form',

		defaultType : 'textfield',

		fieldDefaults : {
			labelWidth : 100
		},
		
		defaults:{
			allowBlank : false
		},
		
		layout : {
			type : 'vbox',
			align: 'stretch'
		},

		bodyPadding : 10,
		border : false,
		items : [{
			xtype : 'fieldset',
			defaultType : 'textfield',
			title : 'Dados do Alarme',
			itemId: 'fldAlarm',
			width: '60%',
			
			/*********** Alarm Information ***********/
			items:[{
				fieldLabel : 'Código',
				itemId: 'id',
				name: 'id',
				allowBlank : true,
				editable: false,
				width: '30%',
				inputAttrTpl: " data-qtip='Código do Alarme' "
			},{
				fieldLabel : 'Descrição',
				itemId: 'desc',
				name: 'desc',
				maxLength: 45,
				enforceMaxLength: true,
				allowBlank : false,
				width: '60%',
				inputAttrTpl: " data-qtip='Descrição do Alarme' "
			},{
				fieldLabel : 'Norm. Manual?',
				itemId: 'manNorm',
				name: 'manNorm',
				store:  Ext.create('Sam.view.components.store.ComboBox'),
				queryMode: 'local',
				valueField: 'id',
		        displayField: 'desc',
				xtype : 'combobox',
				allowBlank : false,
				editable: false,
				width: '25%',
				inputAttrTpl: " data-qtip='Define se o alarme poderá ser normalizado manualmente pela tela de eventos.' "
			},{
				fieldLabel : 'Inc. Cont.?',
				itemId: 'counterInc',
				name: 'counterInc',
				store:  Ext.create('Sam.view.components.store.ComboBox'),
				queryMode: 'local',
				valueField: 'id',
		        displayField: 'desc',
				xtype : 'combobox',
				allowBlank : false,
				editable: false,
				width: '25%',
				inputAttrTpl: " data-qtip='Define se o alarme incrementará o contador do equipamento.' "
			},{
				fieldLabel : 'É Norm.?',
				itemId: 'isNorm',
				name: 'isNorm',
				store:  Ext.create('Sam.view.components.store.ComboBox'),
				queryMode: 'local',
				valueField: 'id',
		        displayField: 'desc',
				xtype : 'combobox',
				allowBlank : false,
				editable: false,
				width: '25%',
				inputAttrTpl: " data-qtip='Define se o alarme será um alarme de Normalização.' "
			}]
			
		
		/*********** Alarm Normalization Information ***********/
		},{
			xtype : 'fieldset',
			defaultType : 'textfield',
			title : 'Alarme de Normalização',
			itemId: 'fldNorm',
			width: '60%',
			
			items:[{
				xtype: 'textfield',
				fieldLabel : 'Código',
				itemId: 'alarm_id',
				name: 'alarm_id',
				editable: false,
				width: '30%',
				allowBlank : true,
				inputAttrTpl: " data-qtip='Código do Alarme de Normalização' ",
				triggers: {f3: {handler: function() { Ext.create('Sam.view.components.PopUp',{
					title: 'Selecionar Alarme de Normalização',
					itemId: 'alarmform_alarm',
					items:	[Ext.create('Sam.view.alarm.AlarmGrid',{dockedItems:[]})],
					
				}).show()}}}
			
				}, {
					fieldLabel : 'Descrição',
					itemId: 'alarm_desc',
					name: 'alarm_desc',
					readOnly : true,
					width: '50%',
					inputAttrTpl: " data-qtip='Descrição do Alarme de Normalização' ",
				}]
			
		
		/*********** EquipmentModel Information ***********/
		},{
			xtype : 'fieldset',
			defaultType : 'textfield',
			title : 'Modelo de Equipamento',
			itemId: 'fldModel',
			
			
			items:[{
				xtype: 'textfield',
				fieldLabel : 'Código',
				itemId: 'model_id',
				name: 'model_id',
				editable: false,
				width: '25%',
				allowBlank : false,
				inputAttrTpl: " data-qtip='Código do Modelo de Equipamento' ",
				triggers: {f3: {handler: function() {Ext.create('Sam.view.components.PopUp',{
					title: 'Modelo do Equipamento',
					itemId: 'alarmform_model',
					items:	[Ext.create('Sam.view.equipment.model.ModelGrid',{dockedItems:[]})],
				}).show()}}}
			
			}, {
				fieldLabel : 'Descrição',
				itemId: 'model_desc',
				name: 'model_desc',
				readOnly : true,
				width: '50%',
				inputAttrTpl: " data-qtip='Descrição do Modelo de Equipamento' ",
			}]
			
		
		/*********** AlarmGroup Information ***********/
		},{
			xtype : 'fieldset',
			defaultType : 'textfield',
			title : 'Grupo de Alarmes',
			itemId: 'fldGroup',
						
			
			items:[{
				xtype: 'textfield',
				fieldLabel : 'Código',
				itemId: 'group_id',
				name: 'group_id',
				editable: false,
				width: '25%',
				allowBlank : false,
				inputAttrTpl: " data-qtip='Código do Grupo de Alarme' ",
				triggers: {f3: {handler: function() {Ext.create('Sam.view.components.PopUp',{
					title: 'Grupo do Alarme',
					itemId: 'alarmform_group',
					items:	[Ext.create('Sam.view.alarm.group.GroupGrid',{dockedItems:[]})],
				}).show()}}}
			
			}, {
				fieldLabel : 'Descrição',
				itemId: 'group_desc',
				name: 'group_desc',
				readOnly : true,
				width: '50%',
				inputAttrTpl: " data-qtip='Descrição do Grupo de Alarme' ",
			}]
			
		
		/*********** AlarmType Information ***********/
		}, {
			xtype : 'fieldset',
			defaultType : 'textfield',
			title : 'Tipo de Alarme',
			itemId: 'fldType',
			
			
			items:[{
				xtype: 'textfield',
				fieldLabel : 'Código',
				itemId: 'type_id',
				name: 'type_id',
				editable: false,
				width: '25%',
				allowBlank : false,
				inputAttrTpl: " data-qtip='Código do Tipo de Alarme' ",
				triggers: {f3: {handler: function() {Ext.create('Sam.view.components.PopUp',{
					title: 'Tipo do Alarme',
					itemId: 'alarmform_type',
					items:	[Ext.create('Sam.view.alarm.type.TypeGrid',{dockedItems:[]})],
				}).show()}}}
			
			}, {
				fieldLabel : 'Descrição',
				itemId: 'type_desc',
				name: 'type_desc',
				readOnly : true,
				width: '50%',
				inputAttrTpl: " data-qtip='Descrição do Tipo de Alarme' ",
			}]
			
		
		/*********** SeverityLevel Information ***********/
		},{
			xtype : 'fieldset',
			defaultType : 'textfield',
			title : 'Severidade do Alarme',
			itemId: 'fldSeverity',
			width: '60%',
			
			
			items:[{
				xtype: 'textfield',
				fieldLabel : 'Código',
				itemId: 'severity_id',
				name: 'severity_id',
				editable: false,
				width: '25%',
				allowBlank : false,
				inputAttrTpl: " data-qtip='Código da Severidade do Alarme' ",
				triggers: {f3: {handler: function() {Ext.create('Sam.view.components.PopUp',{
					title: 'Severidade do Alarme',
					itemId: 'alarmform_severity',
					items:	[Ext.create('Sam.view.severity.SeverityGrid',{dockedItems:[]})],
				}).show()}}}
			
			}, {
				fieldLabel : 'Descrição',
				itemId: 'severity_desc',
				name: 'severity_desc',
				readOnly : true,
				width: '50%',
				inputAttrTpl: " data-qtip='Descrição da Severidade do Alarme' ",
			}]
			
		}],
		
		
		scrollable: true,
		
		dockedItems: [{
			xtype: 'formtoolbar'
		}]
	} ]
	
});