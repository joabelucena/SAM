var type = {
	xtype : 'fieldset',
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
		width: '20%',
		allowBlank : false,
		inputAttrTpl: " data-qtip='Código do Tipo de Equipamento' ",
		triggers: {f3: {handler: function() {this.fireEvent('click')}}}
	}, {
		fieldLabel : 'Descrição',
		itemId: 'type_desc',
		name: 'type_desc',
		readOnly : true,
		width: '40%',
		inputAttrTpl: " data-qtip='Descrição do Tipo de Equipamento' ",
	}],
};

var manufacturer = {
	xtype : 'fieldset',
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
		width: '20%',
		allowBlank : false,
		inputAttrTpl: " data-qtip='Código do Fabricante do Equipamento' ",
		triggers: {f3: {handler: function() {this.fireEvent('click')}}}
	}, {
		fieldLabel : 'Descrição',
		itemId: 'manufacturer_desc',
		name: 'manufacturer_desc',
		readOnly : true,
		width: '40%',
		inputAttrTpl: " data-qtip='Descrição do Fabricante de Equipamento' ",
	}],
};

var model = {
	xtype : 'fieldset',
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
		width: '20%',
		allowBlank : false,
		inputAttrTpl: " data-qtip='Código do Modelo do Equipamento' ",
		triggers: {f3: {handler: function() {this.fireEvent('click')}}}
	}, {
		fieldLabel : 'Descrição',
		itemId: 'model_desc',
		name: 'model_desc',
		readOnly : true,
		width: '40%',
		inputAttrTpl: " data-qtip='Descrição do Modelo de Equipamento' ",
	}],
};

var site = {
	xtype : 'fieldset',
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
		width: '20%',
		allowBlank : false,
		inputAttrTpl: " data-qtip='Código do Local do Equipamento' ",
		triggers: {f3: {handler: function() {this.fireEvent('click')}}}
	}, {
		fieldLabel : 'Descrição',
		itemId: 'site_desc',
		name: 'site_desc',
		readOnly : true,
		width: '40%',
		inputAttrTpl: " data-qtip='Descrição do Local de Equipamento' ",
	}],
};

var system = {
	xtype : 'fieldset',
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
		width: '20%',
		allowBlank : false,
		inputAttrTpl: " data-qtip='Código do Sub-Sistema do Equipamento' ",
		triggers: {f3: {handler: function() {this.fireEvent('click')}}}
	}, {
		fieldLabel : 'Descrição',
		itemId: 'system_desc',
		name: 'system_desc',
		readOnly : true,
		width: '40%',
		inputAttrTpl: " data-qtip='Descrição do Sub-Sistema de Equipamento' ",
	}],
};

var documents = {
	xtype : 'fieldset',
	defaultType : 'textfield',
	title : 'Documentos do Equipamento',
	itemId: 'fldEquipmentDocuments',
	layout : {
		type : 'vbox',
	},

	items : [ {
		xtype: 'textfield',
		fieldLabel : 'Código',
		itemId: 'document_id',
		name: 'document_id',
		editable: false,
		width: '20%',
		allowBlank : false,
		inputAttrTpl: " data-qtip='Código do Documento do Equipamento' ",
		triggers: {f3: {handler: function() {this.fireEvent('click')}}}
	}, {
		fieldLabel : 'Descrição',
		itemId: 'document_desc',
		name: 'document_desc',
		readOnly : true,
		width: '40%',
		inputAttrTpl: " data-qtip='Descrição do Documento do Equipamento' ",
	}, {
		fieldLabel : 'Caminho do documento',
		itemId: 'document_url',
		name: 'document_url',
		readOnly : true,
		width: '40%',
		inputAttrTpl: " data-qtip='Caminho do Documento do Equipamento' ",
	}],
};

var equipment = {
	xtype : 'fieldset',
	defaultType : 'textfield',
	title : 'Equipamento',
	itemId: 'fldEquipment',
	layout : {
		type : 'vbox',
	},
	
	items : [ {
		xtype: 'textfield',
		fieldLabel : 'ID do Equipamento',
		itemId: 'id',
		name: 'id',
		editable: true,
		width: '20%',
		allowBlank : false,
		inputAttrTpl: " data-qtip='ID do Equipamento' ",
	}, {
		fieldLabel : 'Ativo Fixo',
		itemId: 'fixed_asset',
		name: 'fixed_asset',
		readOnly : false,
		allowBlank : true,
		width: '40%',
		inputAttrTpl: " data-qtip='Código do Ativo Fixo do Equipamento' ",
	}, {
		fieldLabel : 'Etiqueta de Manutenção',
		itemId: 'service_tag',
		name: 'service_tag',
		readOnly : false,
		allowBlank : true,
		width: '40%',
		inputAttrTpl: " data-qtip='Código da Etiqueta de Identificação para Equipe de Manutenção' ",
	}, {
		fieldLabel : 'IP',
		itemId: 'ip',
		name: 'ip',
		readOnly : false,
		allowBlank : true,
		width: '40%',
		inputAttrTpl: " data-qtip='Endereço IP do Equipamento' ",
	}, {
		fieldLabel : 'OID',
		itemId: 'oid',
		name: 'oid',
		readOnly : false,
		allowBlank : true,
		width: '40%',
		inputAttrTpl: " data-qtip='OID do Trap SNMP do Equipamento' ",
	}, {
		fieldLabel : 'Garantia',
		itemId: 'warranty',
		name: 'warranty',
		readOnly : false,
		allowBlank : true,
		width: '40%',
		inputAttrTpl: " data-qtip='Tempo de Garantia do Equipamento' ",
	}, {
		fieldLabel : 'OID',
		itemId: 'ip',
		name: 'ip',
		readOnly : false,
		allowBlank : true,
		width: '40%',
		inputAttrTpl: " data-qtip='Endereço IP do Equipamento' ",
	}, {
		fieldLabel : 'MTBF para Preventiva',
		itemId: 'mtbf_prev',
		name: 'mtbf_prev',
		readOnly : false,
		allowBlank : true,
		width: '40%',
		inputAttrTpl: " data-qtip='MTBF Programado para Manutenção Preventiva' ",
	}, {
		fieldLabel : 'MTBF Calculado',
		itemId: 'mtbf_calc',
		name: 'mtbf_calc',
		readOnly : false,
		allowBlank : true,
		width: '40%',
		inputAttrTpl: " data-qtip='MTBF Calculado pelo SAM' ",
	}, {
		fieldLabel : 'MTBF do Fabricante',
		itemId: 'mtbf_manf',
		name: 'mtbf_manf',
		readOnly : false,
		allowBlank : true,
		width: '40%',
		inputAttrTpl: " data-qtip='MTBF do Fabricante' ",
	}, {
		fieldLabel : 'Descrição do Equipamento',
		itemId: 'remark',
		name: 'remark',
		readOnly : false,
		allowBlank : true,
		width: '40%',
		inputAttrTpl: " data-qtip='MTBF do Fabricante' ",
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
			labelWidth : 100
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
		items : [ type, manufacturer, model, site, system, documents, equipment ],
		
		scrollable: true,
		
		dockedItems: [{
			xtype: 'formtoolbar'
		}]
	} ]
	
});