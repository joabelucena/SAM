Ext.define('Sam.view.site.SiteGrid', {
	extend : 'Ext.grid.Panel',
	
	alias : 'widget.sitegrid',
	
	itemId : 'sitegrid',

	requires : ['Ext.grid.filters.Filters'],
	           
	store : Ext.create('Sam.store.Site'),

	columns : [ {
		text : 'Código',
		dataIndex : 'id',
		width: 100,
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
		text : 'Sigla',
		flex : 1,
		sortable : true,
		dataIndex : 'shortname',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Tipo',
		flex : 1,
		sortable : true,
		dataIndex : 'type_desc',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Base de Manutenção',
		flex : 1,
		sortable : true,
		dataIndex : 'station_desc',
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