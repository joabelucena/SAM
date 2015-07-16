Ext.define('Sam.view.equipment.operationalState.OperationalStateForm', {
	extend: 'Ext.Panel',
	requires:['Sam.view.components.FormToolbar'],
	
	alias:  'widget.operationalstateform',
	
	itemId: 'operationalstateform',
	
	closable: true,
	
	layout:{
		type: 'fit',
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
			type : 'vbox'
		},

		bodyPadding : 10,
		border : false,
		items : [{
			fieldLabel : 'Código',
			itemId: 'id',
			name: 'id',
			allowBlank : false,
			editable: false,
			width: '40%',
			inputAttrTpl: " data-qtip='Código do Estado Operacional' "
		},{
			fieldLabel : 'ID do Equipamento',
			itemId: 'model_id',
			name: 'model_id',
			allowBlank : false,
			width: '60%',
			inputAttrTpl: " data-qtip='ID do Equipamento do Estado Operacional' ",
			triggers: {f3: {handler: function() {this.fireEvent('click')}}}
		},{
			fieldLabel : 'Modelo do Equipamento',
			itemId: 'model_desc',
			name: 'model_desc',
			allowBlank : false,
			width: '60%',
			inputAttrTpl: " data-qtip='Modelo do Equipamento do Estado Operacional' ",
		},{
			fieldLabel : 'Descrição',
			itemId: 'desc',
			name: 'desc',
			allowBlank : false,
			width: '60%',
			inputAttrTpl: " data-qtip='Descrição do Estado Operacional' "
		}],
		
		scrollable: true,
		
		dockedItems: [{
			xtype: 'formtoolbar'
		}]
	} ]
	
});
