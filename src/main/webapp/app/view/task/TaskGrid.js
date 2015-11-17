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
	    	xtype:'splitbutton',
	    	text:'Ações',
	    	width: 80,
	    	iconCls: 'toolbox',
            menu:[{
            	text: 'Executar',
            	itemId: 'splExec',
            	iconCls: 'control',
            	handler: function(item, event){
            		var record = item.up('grid').getSelection()[0];
            			store = item.up('grid').getStore(),
            			writer = Ext.create('Sam.lib.AssociatedWriter');
            			
            		
            		if(record){
            			
            			if(record.get('active') !== "Y"){
            				Ext.MessageBox.show({
						        title: 'SAM | Info',
						        msg:  'A regra não está ativa. Ative-a antes de executar.',
						        buttons: Ext.MessageBox.OK,
						        icon: Ext.MessageBox.WARNING
							});
            			}else{
            				Ext.Ajax.request({
                        		url : 'task/run.action',
                        		method : 'POST',
                        		scope: this,
                        		jsonData: writer.getRecordData(record),
                        		success: function (result, request) {
                        			
                        			Ext.MessageBox.show({
        						        title: 'SAM | Info',
        						        msg:  'Regra executada com sucesso. Verifique na tela de Eventos se as condições foram atendidas gerando o alarme.',
        						        buttons: Ext.MessageBox.OK,
        						        icon: Ext.MessageBox.INFO
        							});
                        			
                        			/**
                        			 * Atualiza store para obter as novas datas caso a regra tenha sido ativada
                        			 */
                        			if(store  && store.getData().length > 0){
                        				store.reload();
                        			}
                        		}
                			});	
            			}
            		}
            	}
            }]
	    }, '-', {
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