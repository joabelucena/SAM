Ext.define('Sam.view.parameter.ParameterForm', {
	extend: 'Ext.Panel',
	requires:['Sam.view.components.FormToolbar'],
	
	alias:  'widget.parameterform',
	
	itemId: 'parameterform',
	
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
			allowBlank : true,
			editable: false,
			width: 200,
			inputAttrTpl: " data-qtip='Código do Parâmetro' "
		},{
			fieldLabel : 'Nome',
			itemId: 'name',
			name: 'name',
			allowBlank : false,
			width: 300,
			inputAttrTpl: " data-qtip='Nome do parâmetro. Os parâmetros de configuração do sistema deve ser iniciados por SYS_. Os " +
					" parâmetros de processo devem ser iniciados por SAM_.' "
		},{
			fieldLabel : 'Tipo',
			xtype: 'combobox',
			itemId: 'type',
			name: 'type',
			store: Ext.create('Ext.data.Store',{fields: ['id', 'desc'],
			    data : [
			        {"id":"C", "desc":"Caracter"	},
			        {"id":"D", "desc":"Data"		},
			        {"id":"N", "desc":"Numérico"	},
			        {"id":"S", "desc":"Senha"		},
			    ]
			}),
			queryMode: 'local',
			valueField: 'id',
	        displayField: 'desc',
			xtype : 'combobox',
			allowBlank : false,
			editable: false,
			width: 200,
			inputAttrTpl: " data-qtip='Tipo do Parâmetro. (C=Carater; D=Data; N=Numérico; S=Senha)' "
		},{
			fieldLabel : 'Valor',
			itemId: 'value',
			name: 'value',
			allowBlank : false,
			width: 500,
			inputAttrTpl: " data-qtip='Valor do parâmetro.' "
		},{
			fieldLabel : 'Descrição',
			itemId: 'desc',
			name: 'desc',
			allowBlank : false,
			width: 650,
			inputAttrTpl: " data-qtip='Breve descrição contendo a finalidade do parâmetro.' "
		}],
		
		scrollable: true,
		
		dockedItems: [{
			xtype: 'formtoolbar'
		}]
	} ]
	
});