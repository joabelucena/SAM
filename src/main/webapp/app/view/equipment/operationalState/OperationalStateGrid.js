Ext.define('Sam.view.equipment.operationalState.OperationalStateGrid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.operationalstategrid',

	requires : ['Ext.grid.filters.Filters'],
	           
	store : Ext.create('Sam.store.OperationalState'),
	
	itemId: 'operationalstategrid',

	columns : [ {
		text : 'Codigo',
		dataIndex : 'id',
		flex : 1,
		sortable: true,
		filter : {
			type : 'number'
		}
	}, {
		text : 'Modelo do Equipamento',
		flex : 1,
		sortable : true,
		dataIndex : 'model_desc',
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
	    	id:'btnEdit',
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