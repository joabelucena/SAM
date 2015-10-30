Ext.define('Sam.view.serviceOrder.ServiceOrderGrid' , {
	extend: 'Sam.lib.AutoGrid',
	alias: 'widget.serviceordergrid',
	
	requires: ['Ext.grid.filters.Filters'],
		
	store: Ext.create('Sam.store.ServiceOrder'),

	plugins: 'gridfilters',
	
	refreshRate: 10,
	
	itemId: 'serviceordergrid',
	
	scrollable: true,
	
	columns : [
	   {
		   text: 'Código',
		   dataIndex: 'id',
		   width: 100
       },
       {
			text: 'Equipamento',
			sortable: true,
			dataIndex: 'equipment_id',
			width: 200,
			filter: {
				type: 'string'
			}
		},{
			text: 'Tipo',
			sortable: true,
			dataIndex: 'type_desc',
			width: 100,
			filter: {
				type: 'string'
			}
		},{
			text: 'Status',
			sortable: true,
			dataIndex: 'status_desc',
			width: 150,
			filter: {
				type: 'string'
			}
		},{
			text: 'Evento',
			sortable: true,
			dataIndex: 'event_id',
			width: 100,
			renderer: function( value, metadata, record ){
				if (value === 0) {
					return '';
		        }else{
		        	return value;
		        }
			},
			filter: {
				type: 'number'
			}
		},{
			text: 'Inicio',
			sortable: true,
			dataIndex: 'start',
			renderer: Ext.util.Format.dateRenderer('d/m/Y - G:i:s'),
			width: 120,
			filter: {
				type: 'date'
			}
		},{
			text: 'Conclusao Real',
			sortable: true,
			dataIndex: 'end',
			renderer: Ext.util.Format.dateRenderer('d/m/Y - G:i:s'),
			width: 120,
			filter: {
				type: 'date'
			}
		},{
			text: 'Técnico',
			flex: 1,
			sortable: true,
			dataIndex: 'technician_name',
			width: 200,
			filter: {
				type: 'string'
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
	            	text: 'Legenda',
	            	menu: [{
	            		text: 'Legenda1',
	            		hideOnClick: false
	            	},{
	            		text: 'Legenda2',
	            		hideOnClick: false
	            	}]
	            }]
	        }, '-', {
		        xtype:'button',
		    	itemId:'btnAdd',
		    	text:'Nova',
		    	width: 80,
		        tooltip:'Abrir Nova Ordem de Serviço',
		        cls:'x-btn-default-small',
		        iconCls: 'blueprint-plus'
		    },{
		        xtype:'button',
		    	itemId:'btnEdit',
		    	text:'Apontar',
		    	width: 80,
		        tooltip:'Informar Serviços Realizados',
		        cls:'x-btn-default-small',
		        iconCls: 'blueprint-pencil'
		    }, '-',{
		        xtype:'button',
		    	itemId:'btnShow',
		    	text:'Visualizar',
		    	width: 80,
		        tooltip:'Visuzalizar Ordem de Serviço',
		        cls:'x-btn-default-small',
		        iconCls: 'blueprint'
		    }]
		}],

		listeners:{
			
			beforerender: function(me, eOpts){
				
				Ext.Ajax.request({
            		url : 'so/getAllowedStatus',
            		method : 'POST',
            		
            		success: function (result, request) {
            			
            			var action = Ext.util.JSON.decode(result.responseText),
            					menu = me.down('toolbar')
            							.down('splitbutton')
            							.getMenu();
            			
            			if(action.data.length > 0) {
            				
            				//Separador
                			menu.add('-');
                			
                			//Itera array de retorno e adiciona os valores no menu
                			Ext.each(action.data,function(data){
                				menu.add({
                					text: data.feature.description,
                					handler: function(item, event){
                						this.fireEvent('change', item, event, data.id, (data.remark === "Y"));
                					},
                				});
                			});
            			}
            		}
            	});
			}
		}


});