Ext.define('Sam.view.parameter.ParameterGrid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.parametergrid',

	requires : ['Ext.grid.filters.Filters'],
	           
	store : Ext.create('Sam.store.Parameter'),
	
	itemId: 'parametergrid',

	columns : [ {
		text : 'Código',
		dataIndex : 'id',
		width: 100,
		sortable: true,
		filter : {
			type : 'string'
		}
	}, {
		text : 'Nome',
		width: 150,
		sortable : true,
		dataIndex : 'name',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Tipo',
		width: 50,
		sortable : true,
		dataIndex : 'type',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Valor',
		width: 400,
		sortable : true,
		dataIndex : 'value',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Descrição',
		flex : 1,
		sortable : true,
		dataIndex : 'desc'
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