var header = {
		xtype : 'groupfield',
		itemId: 'grpHistInfo',
		defaultType : 'textfield',
		title : 'Informações da Ordem de Serviço',
	
		items : [ {
			fieldLabel : 'No. Os.',
			name : 'id',
			readOnly : true,
			width: '30%',
			inputAttrTpl: " data-qtip='Número da Ordem de Serviço' "
		}, {
			fieldLabel : 'Status',
			name: 'status_desc',
			readOnly : true,
			width: '30%',
			inputAttrTpl: " data-qtip='Status da Ordem de Serviço' "
		}, {
			fieldLabel : 'Tipo',
			name: 'type_desc',
			readOnly : true,
			width: '30%',
			inputAttrTpl: " data-qtip='Tipo da Ordem de Serviço' "
		}, {
			fieldLabel : 'Equipamento',
			name: 'equipment_id',
			readOnly : true,
			width: '40%',
			inputAttrTpl: " data-qtip='Equipamento da Ordem de Serviço' "
		}, {
			fieldLabel : 'Evento',
			name: 'event_id',
			readOnly : true,
			width: '40%',
			inputAttrTpl: " data-qtip='Alarme do Equipamento da Ordem de Serviço' "
		} ],
};

var footer = Ext.create('Sam.view.serviceOrder.ServiceOrderGrid' ,{
	width: '100%',
	height: '100%',
	dockedItems:[],
	listeners:{
		itemmouseup : function( me, record, item, index, e, eOpts ){
			me.up('#eventhistso').down('groupfield')
				.loadRecord(record);
		},
		beforerender: function(me, eOpts){
			//Overrides default listener
		}
	}
});

Ext.define('Sam.view.event.openSO.EventHistSO', {
	extend: 'Ext.Panel',
	alias:  'widget.eventhistso',
	
	requires: ['Sam.view.serviceOrder.ServiceOrderGrid'],	
	
	itemId: 'eventhistso',
	
	closable: false,
	
	layout: 'border',
    width: 500,
    height: 400,
    
    bodyBorder: false,
    
    defaults: {
        collapsible: false,
        split: true
    },

    items: [
            {
                title: 'Detalhes da Ordem de Servico',
                items: [header],
                region: 'center',
                bodyPadding : 10,
                margin: '5 0 0 0',
            },{
	        	 title: 'Ordens De Serviço',
	        	 collapsible: true,
	        	 items:[{
	        		 xtype: 'serviceordergrid',
	        		 store: Ext.create('Sam.store.ServiceOrder',{
	        			 filters : {
	        				 property : 'equipment_id',
	        				 value: 'XPTO_01'
	        			 }
	        		 }),
	        		 dockedItems:[],
	        		 listeners:{
	        				itemmouseup : function( me, record, item, index, e, eOpts ){
	        					me.up('#eventhistso').down('groupfield')
	        						.loadRecord(record);
	        				},
	        				beforerender: function(me, eOpts){
	        					//Retrieves SOs from that equipment
	        					me.getStore().setFilters({
	        						property : 'equipment_id',
	        						value: me.up('#eventopenso').down('#equipment_id').getValue()
	        					});	        					
	        				}
	        			}
	        	 }],
	             region: 'south',
	             layout: 'fit',
	             margin: '5 0 0 0',
	             minHeight: 250,
	             Height: 250	             
        }
    ]
});