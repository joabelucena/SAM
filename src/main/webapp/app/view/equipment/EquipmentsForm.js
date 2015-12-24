var equipment = {
	xtype : 'groupfield',
	defaultType : 'textfield',
	title : 'Equipamento',
	itemId: 'fldEquipment',
	layout : {
		type : 'vbox',
	},
	
	items : [ {
		xtype: 'textfield',
		fieldLabel : 'Código',
		itemId: 'id',
		name: 'id',
		editable: false,
		width: 200,
		allowBlank : false,
		inputAttrTpl: " data-qtip='ID do Equipamento' ",
	}, {
		xtype: 'textfield',
		fieldLabel : 'Descrição',
		itemId: 'desc',
		name: 'desc',
		editable: true,
		width: 500,
		allowBlank : false,
		inputAttrTpl: " data-qtip='ID do Equipamento' ",
	}, {
		fieldLabel : 'Ativo Fixo',
		itemId: 'fixedAsset',
		name: 'fixedAsset',
		readOnly : false,
		allowBlank : true,
		width: 300,
		inputAttrTpl: " data-qtip='Código do Ativo Fixo do Equipamento' ",
	}, {
		fieldLabel : 'Etiqueta de Manutenção',
		itemId: 'serviceTag',
		name: 'serviceTag',
		readOnly : false,
		allowBlank : true,
		width: 300,
		inputAttrTpl: " data-qtip='Código da Etiqueta de Identificação para Equipe de Manutenção' ",
	}, {
		fieldLabel : 'IP',
		itemId: 'ip',
		name: 'ip',
		readOnly : false,
		allowBlank : true,
		width: 200,
		inputAttrTpl: " data-qtip='Endereço IP do Equipamento' ",
	}, {
		xtype: 'textareafield',
		fieldLabel : 'Observações',
		itemId: 'remark',
		name: 'remark',
		readOnly : false,
		allowBlank : true,
		width: 500,
		inputAttrTpl: " data-qtip='Informações que sejam relevantes ao equipamento.' ",
	}],
};

/** Tipo do Equipamento **/
var type = {
	xtype : 'groupfield',
	defaultType : 'textfield',
	title : 'Tipo de Equipamento',
	itemId: 'fldEquipmentType',
	layout : {
		type : 'vbox',
	},

	items : [ {
		xtype: 'textfield',
		fieldLabel : 'Código',
		itemId: 'type_id',
		name: 'type_id',
		editable: false,
		width: 250,
		allowBlank : false,
		inputAttrTpl: " data-qtip='Código do Tipo de Equipamento' ",
		triggers: {f3: {handler: function() {
			Ext.create('Sam.view.components.PopUp',{
				title: 'Selecionar Tipo do Equipamento',
				groupField: this.up('fieldset').itemId,
				items:	[Ext.create('Sam.view.equipment.type.TypeGrid',{dockedItems:[]})]				
			}).show();
		}
	}
	}
	}, {
		fieldLabel : 'Descrição',
		itemId: 'type_desc',
		name: 'type_desc',
		readOnly : true,
		width: 400,
		inputAttrTpl: " data-qtip='Descrição do Tipo de Equipamento' ",
	}],
};

/** Fabricante do Equipamento **/
var manufacturer = {
	xtype : 'groupfield',
	defaultType : 'textfield',
	title : 'Fabricante do Equipamento',
	itemId: 'fldEquipmentManufacturer',
	layout : {
		type : 'vbox',
	},

	items : [ {
		xtype: 'textfield',
		fieldLabel : 'Código',
		itemId: 'manufacturer_id',
		name: 'manufacturer_id',
		editable: false,
		width: 250,
		allowBlank : false,
		inputAttrTpl: " data-qtip='Código do Fabricante do Equipamento' ",
		triggers: {f3: {handler: function() {
			Ext.create('Sam.view.components.PopUp',{
				title: 'Selecionar Fabricante do Equipamento',
				groupField: this.up('fieldset').itemId,
				items:	[Ext.create('Sam.view.equipment.manufacturer.ManufacturerGrid',{dockedItems:[]})]				
			}).show();
		}}}
	}, {
		fieldLabel : 'Descrição',
		itemId: 'manufacturer_desc',
		name: 'manufacturer_desc',
		readOnly : true,
		width: 400,
		inputAttrTpl: " data-qtip='Descrição do Fabricante de Equipamento' ",
	}],
};


/** Modelo do Equipamento **/
var model = {
	xtype : 'groupfield',
	defaultType : 'textfield',
	title : 'Modelo do Equipamento',
	itemId: 'fldEquipmentModel',
	layout : {
		type : 'vbox',
	},

	items : [ {
		xtype: 'textfield',
		fieldLabel : 'Código',
		itemId: 'model_id',
		name: 'model_id',
		editable: false,
		width: 250,
		allowBlank : false,
		inputAttrTpl: " data-qtip='Código do Modelo do Equipamento' ",
		triggers: {f3: {handler: function() {
			Ext.create('Sam.view.components.PopUp',{
				title: 'Selecionar Modelo do Equipamento',
				groupField: this.up('fieldset').itemId,
				items:	[Ext.create('Sam.view.equipment.model.ModelGrid',{dockedItems:[]})]				
			}).show();
		}}}
	}, {
		fieldLabel : 'Descrição',
		itemId: 'model_desc',
		name: 'model_desc',
		readOnly : true,
		width: 400,
		inputAttrTpl: " data-qtip='Descrição do Modelo de Equipamento' ",
	}],
};

/** Local do Equipamento **/
var site = {
	xtype : 'groupfield',
	defaultType : 'textfield',
	title : 'Local do Equipamento',
	itemId: 'fldEquipmentSite',
	layout : {
		type : 'vbox',
	},

	items : [ {
		xtype: 'textfield',
		fieldLabel : 'Código',
		itemId: 'site_id',
		name: 'site_id',
		editable: false,
		width: 250,
		allowBlank : false,
		inputAttrTpl: " data-qtip='Código do Local do Equipamento' ",
		triggers: {f3: {handler: function(){
			Ext.create('Sam.view.components.PopUp',{
				title: 'Selecionar Local do Equipamento',
				groupField: this.up('fieldset').itemId,
				items:	[Ext.create('Sam.view.site.SiteGrid',{dockedItems:[]})]				
			}).show();
		}}}
	}, {
		fieldLabel : 'Descrição',
		itemId: 'site_desc',
		name: 'site_desc',
		readOnly : true,
		width: 400,
		inputAttrTpl: " data-qtip='Descrição do Local de Equipamento' ",
	}],
};

/** Sub-Sistema do Equipamento **/
var system = {
	xtype : 'groupfield',
	defaultType : 'textfield',
	title : 'Sub-Sistema do Equipamento',
	itemId: 'fldEquipmentSystem',
	layout : {
		type : 'vbox',
	},

	items : [ {
		xtype: 'textfield',
		fieldLabel : 'Código',
		itemId: 'system_id',
		name: 'system_id',
		editable: false,
		width: 250,
		allowBlank : false,
		inputAttrTpl: " data-qtip='Código do Sub-Sistema do Equipamento' ",
		triggers: {f3: {handler: function() {
			Ext.create('Sam.view.components.PopUp',{
				title: 'Selecionar Sub-Sistema do Equipamento',
				groupField: this.up('fieldset').itemId,
				items:	[Ext.create('Sam.view.equipment.system.SystemGrid',{dockedItems:[]})]				
			}).show();
		}}}
	}, {
		fieldLabel : 'Descrição',
		itemId: 'system_desc',
		name: 'system_desc',
		readOnly : true,
		width: 400,
		inputAttrTpl: " data-qtip='Descrição do Sub-Sistema de Equipamento' ",
	}],
};

var maintenance = {
	xtype : 'groupfield',
	title : 'Manutenção',
	itemId: 'fldMaintenance',
	layout : {
		type : 'vbox',
	},
	
	defaults:{
		xtype: 'numberfield',
		minValue: 0,
	},
	
	items : [{
		xtype: 'datefield',
		format: 'd/m/Y',
		fieldLabel : 'Dt. Instalação',
		itemId: 'installDate',
		name: 'installDate',
		allowBlank : true,
		width: 250,
		inputAttrTpl: " data-qtip='MTBF Programado para Manutenção Preventiva' ",
	},{
		fieldLabel : 'MTBF Calculado (h)',
		itemId: 'mtbfCalc',
		name: 'mtbfCalc',
		readOnly : true,
		disabled: true,
		width: 200,
		inputAttrTpl: " data-qtip='MTBF Calculado pelo SAM' ",
	},{
		fieldLabel : 'MTBF do Fabricante (h)',
		itemId: 'mtbfManf',
		name: 'mtbfManf',
		readOnly : false,
		allowBlank : true,
		width: 200,
		inputAttrTpl: " data-qtip='MTBF informado pelo fabricante do equipamento' ",
	}],
};

Ext.define('Sam.view.equipment.EquipmentsForm', {
	extend: 'Ext.Panel',
	requires:['Sam.view.components.FormToolbar'],
	
	alias:  'widget.equipmentsform',
	
	itemId: 'equipmentsform',
	
	closable: true,
	
	layout:{
		type: 'fit',
	},
	
	items : [{
		xtype : 'form',

		defaultType : 'textfield',

		fieldDefaults : {
			labelWidth : 110
		},
		defaults:{
			allowBlank : false
		},

		layout : {
			type : 'vbox',
			align : 'stretch'
		},

		bodyPadding : 10,
		border : false,
		items : [ equipment, type, manufacturer, model, site, system, maintenance ],
		
		scrollable: true,
		
		dockedItems: [{
			xtype: 'formtoolbar'
		}]
	} ]
	
});