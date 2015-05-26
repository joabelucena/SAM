Ext.define('Sam.view.alarm.AlarmGrid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.alarmgrid',

	requires : ['Ext.grid.filters.Filters'],
	           
	store : Ext.create('Sam.store.Alarm'),
	
	itemId: 'alarmgrid',
	
	plugins: 'gridfilters',
	
	scrollable: true,
	
	defaults:{
		columnWidth: 100,
	},

	columns : [ {
		text : 'Codigo',
		dataIndex : 'id',
		flex : 1,
		sortable: true,
	   minWidth: 200,
	   width: 200,
		filter : {
			type : 'string'
		}
	}, {
		text : 'Descrição',
		flex : 1,
		sortable : true,
		dataIndex : 'desc',
		minWidth: 200,
		   width: 200,
		filter : {
			type : 'string'
		}
	}, {
		text : 'Normaliza Manual?',
		flex : 1,
		sortable : true,
		dataIndex : 'manNorm',
		minWidth: 200,
		   width: 200,
		renderer: function(value){
	        if (value === 1) {
	            return 'Sim';
	        }
	        return 'Não';
	        },
        filter : {
			type : 'string'
		}
	}, {
		text : 'Incrementa Contador?',
		flex : 1,
		sortable : true,
		dataIndex : 'counterInc',
		minWidth: 200,
		   width: 200,
		renderer: function(value){
	        if (value === 1) {
	            return 'Sim';
	        }
	        return 'Não';
	        },
		filter : {
			type : 'string'
		}
	}, {
		text : 'Eh Normalização?',
		flex : 1,
		sortable : true,
		dataIndex : 'isNorm',
		minWidth: 200,
		   width: 200,
		renderer: function(value){
	        if (value === 1) {
	            return 'Sim';
	        }
	        return 'Não';
	        },
		filter : {
			type : 'string'
		}
	}, {
		text : 'Normalização',
		flex : 1,
		sortable : true,
		dataIndex : 'alarm_id',
		minWidth: 200,
		   width: 200,
		filter : {
			type : 'string'
		}
	}, {
		text : 'Grupo',
		flex : 1,
		sortable : true,
		dataIndex : 'group_desc',
		minWidth: 200,
		   width: 200,
		filter : {
			type : 'string'
		}
	}, {
		text : 'Tipo',
		flex : 1,
		sortable : true,
		dataIndex : 'type_desc',
		minWidth: 200,
		   width: 200,
		filter : {
			type : 'string'
		}
	}, {
		text : 'Severidade',
		flex : 1,
		sortable : true,
		minWidth: 200,
		   width: 200,
		dataIndex : 'severity_desc',
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