Ext.define('Sam.view.equipment.EquipmentsGrid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.equipmentsgrid',

	requires : [ 'Ext.grid.filters.Filters'],
	           
	scope: this,
	
	plugins: 'gridfilters',
	
	itemId: 'equipmentsgrid',

	columns : [ {
		text : 'Código',
		dataIndex : 'id',
		width: 120,
		filter : {
			type : 'string'
		}
	}, {
		text : 'Número de Ativo Fixo',
		width: 150,
		sortable : true,
		dataIndex : 'fixedAsset',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Descrição',
		dataIndex : 'desc',
		width: 400,
		filter : {
			type : 'string'
		}
	}, {
		text : 'Modelo',
		sortable : true,
		width: 200,
		dataIndex : 'model_desc',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Tipo',
		sortable : true,
		width: 200,
		dataIndex : 'type_desc',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Fabricante',
		
		sortable : true,
		dataIndex : 'manufacturer_desc',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Local',
		
		sortable : true,
		dataIndex : 'site_desc',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Sub-Sistema',		
		sortable : true,
		width: 200,
		dataIndex : 'system_desc',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Etiqueta de Serviço',
		sortable : true,
		width: 150,
		dataIndex : 'serviceTag',
		filter : {
			type : 'string'
		}
	}, {
		text : 'IP',		
		sortable : true,
		width: 150,
		dataIndex : 'ip',
		filter : {
			type : 'string'
		}
	}],
	
	dockedItems: [{
	    xtype: 'toolbar',
	    dock: 'bottom',
	    
	    items: [{
	        xtype:'button',
	        itemId:'btnShow',
	    	text:'Visualizar',
	        tooltip:'Visualizar Registro',
	        cls:'x-btn-default-small',
	        iconCls: 'tick-button'
	    },{
	    	xtype: 'tbseparator'
	    },{
	        xtype:'button',
	        itemId:'btnAdd',
	    	text:'Incluir',
	        tooltip:'Incluir Novo Registro',
	        cls:'x-btn-default-small',
	        iconCls: 'tick-button'
	    },{
	        xtype:'button',
	    	itemId:'btnEdit',
	    	text:'Alterar',
	        tooltip:'Editar Registro Selecionado',
	        cls:'x-btn-default-small',
	        iconCls: 'tick-button'
	    },{
	        xtype:'button',
	        itemId:'btnDelete',
	    	text:'Excluir',
	        tooltip:'Excluir Registro Selecionado',
	        cls:'x-btn-default-small',
	        iconCls: 'tick-button'
	    }]
	}],

	/** Inits Component **/
	initComponent: function() {
		this.callParent(arguments);
		
		var store = Ext.create('Sam.store.Equipment'),
			page = Ext.ComponentQuery.query('pagingtoolbar', this)[0];
		
		this.setStore(store);
		
		if (typeof page != "undefined"){
			page.setStore(store);
		}
		
	},
	

});