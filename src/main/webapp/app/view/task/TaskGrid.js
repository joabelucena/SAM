Ext.define('Sam.view.task.TaskGrid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.taskgrid',
	
	itemId : 'taskgrid',

	requires : ['Ext.grid.filters.Filters'],
	           
	store : Ext.create('Sam.store.Task'),
	
	plugins: 'gridfilters',
	
	columns : [ {
		text: 'Ativo',
		dataIndex : 'active',
		maxWidth: 42,
		minWidth: 42,
		menuDisabled: true,
		sortable: false,
		renderer: function( value, metadata, record )
		{
			if (value === 'Y') {
				metadata.tdCls = 'traffic-light-green';
	        }else{
	        	metadata.tdCls = 'traffic-light-red';
	        }
		}
	},{
		text : 'Código',
		dataIndex : 'id',
		renderer: function(value){
			return Ext.util.Format.leftPad(value,6,'0')
			},
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
		text : 'Alarme',
		flex : 1,
		sortable : true,
		dataIndex : 'alarm_desc',
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