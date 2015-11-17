Ext.define('Sam.view.alarm.AlarmGrid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.alarmgrid',

	requires : ['Ext.grid.filters.Filters'],
	           
	store : Ext.create('Sam.store.Alarm'),
	
	itemId: 'alarmgrid',
	
	plugins: 'gridfilters',
	
	scrollable: true,
	
	columns : [ {
		text : 'Código',
		dataIndex : 'id',		
		sortable: true,
		width: 140,
		filter : {
			type : 'string'
		}
	}, {
		text : 'Descrição',		
		sortable : true,
		dataIndex : 'desc',
		width: 340,
		filter : {
			type : 'string'
		}
	}, {
		text : 'Normaliza Manual?',		
		sortable : true,
		dataIndex : 'manNorm',
		width: 110,
		renderer: function(value){
	        if (value === 'Y') {
	            return 'Sim';
	        }
	        return 'Não';
	        },
        filter : {
			type : 'string'
		}
	}, {
		text : 'É Normalização?',
		sortable : true,
		dataIndex : 'isNorm',
		width: 110,
		renderer: function(value){
	        if (value === 'Y') {
	            return 'Sim';
	        }
	        return 'Não';
		},
		filter : {
			type : 'string'
		}
	}, {
		text : 'Alm. Norm.',		
		sortable : true,
		dataIndex : 'alarm_id',
		width: 140,
		filter : {
			type : 'string'
		}
	}, {
		text : 'Grupo',		
		sortable : true,
		dataIndex : 'group_desc',
		width: 300,
		filter : {
			type : 'string'
		}
	}, {
		text : 'Tipo',		
		sortable : true,
		dataIndex : 'type_desc',
		width: 300,
		filter : {
			type : 'string'
		}
	}, {
		text : 'Severidade',
		sortable : true,
		width: 80,
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