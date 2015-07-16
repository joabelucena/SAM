Ext.define('Sam.view.technician.TechnicianGrid', {
	extend : 'Ext.grid.Panel',
	
	alias : 'widget.techniciangrid',
	
	itemId : 'techniciangrid',

	requires : ['Ext.grid.filters.Filters'],
	           
	store : Ext.create('Sam.store.Technician'),

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
		flex : 1,
		sortable : true,
		dataIndex : 'name',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Local de Trabalho',
		flex : 1,
		sortable : true,
		dataIndex : 'site_desc',
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