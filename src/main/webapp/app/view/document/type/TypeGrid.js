Ext.define('Sam.view.document.type.TypeGrid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.documenttypegrid',
	
	//controller: Ext.create('Sam.controller.Document'),

	requires : ['Ext.grid.filters.Filters'],
	           
	store : Ext.create('Sam.store.DocumentType'),
	
	itemId: 'documenttypegrid',

	columns : [ {
		text : 'Codigo',
		dataIndex : 'id',
		flex : 1,
		sortable: true,
		filter : {
			type : 'string'
		}
	}, {
		text : 'Descrição',
		flex : 1,
		sortable : true,
		dataIndex : 'desc',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Extensão',
		flex : 1,
		sortable : true,
		dataIndex : 'extension',
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
	}]
	
	
});