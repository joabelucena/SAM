var type = {
	xtype : 'fieldset',
	defaultType : 'textfield',
	title : 'Tipo de Local',
	itemId: 'fldSiteType',
	layout : {
		type : 'vbox',
	},

	items : [ {
		xtype: 'textfield',
		fieldLabel : 'Codigo',
		itemId: 'type_id',
		name: 'type_id',
		editable: false,
		width: '20%',
		allowBlank : false,
		inputAttrTpl: " data-qtip='Código do Tipo de Local' ",
		triggers: {f3: {handler: function() {this.fireEvent('click')}}}
	}, {
		fieldLabel : 'Descrição',
		itemId: 'type_desc',
		name: 'type_desc',
		readOnly : true,
		width: '40%',
		inputAttrTpl: " data-qtip='Descrição do Tipo de Local' ",
	}],
};


var station = {
		xtype : 'fieldset',
		defaultType : 'textfield',
		title : 'Base de Manutenção',
		itemId: 'fldServiceStation',
		layout : {
			type : 'vbox',
		},

		items : [{
			fieldLabel : 'Codigo',
			itemId: 'station_id',
			name: 'station_id',
			allowBlank : true,
			editable: false,
			width: '20%',
			inputAttrTpl: " data-qtip='Codigo da Base de Manutenção' ",
			triggers: {f3: {handler: function() {this.fireEvent('click')}}}
		},{
			fieldLabel : 'Descrição',
			itemId: 'station_desc',
			name: 'station_desc',
			allowBlank : false,
			width: '40%',
			inputAttrTpl: " data-qtip='Descrição da Base de Manutenção' "
		}],
};

var parent = {
		xtype : 'fieldset',
		defaultType : 'textfield',
		title : 'Local Pai',
		itemId: 'fldParentSite',
		layout : {
			type : 'vbox',
		},

		items : [{
			fieldLabel : 'Codigo',
			itemId: 'parent_id',
			name: 'parent_id',
			allowBlank : true,
			editable: false,
			width: '20%',
			inputAttrTpl: " data-qtip='Codigo do Local Pai' ",
			triggers: {f3: {handler: function() {this.fireEvent('click')}}}
		},{
			fieldLabel : 'Descrição',
			itemId: 'parent_desc',
			name: 'parent_desc',
			allowBlank : false,
			width: '40%',
			inputAttrTpl: " data-qtip='Descrição do Local Pai' "
		}],
};

var site = {
		xtype : 'fieldset',
		defaultType : 'textfield',
		title : 'Dados do Local',
		itemId: 'fldSite',
		layout : {
			type : 'vbox',
		},

		items : [{
			fieldLabel : 'Codigo',
			itemId: 'id',
			name: 'id',
			allowBlank : true,
			editable: false,
			width: '20%',
			inputAttrTpl: " data-qtip='Codigo do Local' "
		},{
			fieldLabel : 'Descrição',
			itemId: 'desc',
			name: 'desc',
			allowBlank : false,
			width: '60%',
			inputAttrTpl: " data-qtip='Descrição do Local' "
		},{
			fieldLabel : 'Sigla',
			itemId: 'shortname',
			name: 'shortname',
			allowBlank : false,
			width: '60%',
			inputAttrTpl: " data-qtip='Sigla (nome abreviado) do Local' "
		}],
};


Ext.define('Sam.view.site.SiteForm', {
	extend: 'Ext.Panel',
	
	alias:  'widget.siteform',
	
	itemId: 'siteform',
	
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
		items : [ type, station, parent, site ],
		
		scrollable: true,
		
		dockedItems: [{
		    xtype: 'toolbar',
		    dock: 'bottom',
		    
		    items: [{
		    	xtype: 'tbfill'
		    },{
		        xtype:'button',
		    	itemId:'btnSubmit',
		    	text:'Confirma',
		        tooltip:'Confirmar Operação',
		        cls:'x-btn-default-small',
		        iconCls: 'tick-button'
		    },{
		        xtype:'button',
		    	itemId:'btnDiscard',
		    	text:'Cancela',
		        tooltip:'Cancelar Operação',
		        cls:'x-btn-default-small',
		        iconCls: 'tick-button'
		    }]
		}]
	} ]
	
});