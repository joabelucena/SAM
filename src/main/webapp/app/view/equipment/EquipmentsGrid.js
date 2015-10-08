Ext.define('Sam.view.equipment.EquipmentsGrid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.equipmentsgrid',

	requires : [ 'Ext.grid.filters.Filters'],
	           
	store : Ext.create('Sam.store.Equipment'),
	
	itemId: 'equipmentsgrid',

	columns : [ {
		text : 'Código',
		dataIndex : 'id',
		width: 100,
		filter : {
			type : 'string'
		}
	}, {
		text : 'Modelo',
		
		sortable : true,
		dataIndex : 'model_desc',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Tipo',
		
		sortable : true,
		dataIndex : 'type_desc',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Fabricante',
		
		sortable : true,
		dataIndex : 'manufacturer_desc',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Local',
		
		sortable : true,
		dataIndex : 'site_desc',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Sub-Sistema',
		
		sortable : true,
		dataIndex : 'system_desc',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Número de Ativo Fixo',
		
		sortable : true,
		dataIndex : 'fixed_asset',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Etiqueta de Serviço',
		
		sortable : true,
		dataIndex : 'service_tag',
		filter : {
			type : 'string'
		}
	}, {
		text : 'IP',
		
		sortable : true,
		dataIndex : 'ip',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Número de Ativo Fixo',
		
		sortable : true,
		dataIndex : 'fixed_asset',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Garantia',
		
		sortable : true,
		dataIndex : 'warranty',
		filter : {
			type : 'string'
		}
	}, {
		text : 'OID SNMP',
		
		sortable : true,
		dataIndex : 'oid',
		filter : {
			type : 'string'
		}
	}, {
		text : 'MTBF Previsto',
		
		sortable : true,
		dataIndex : 'mtbf_prev',
		filter : {
			type : 'string'
		}
	}, {
		text : 'MTBF Calculado',
		
		sortable : true,
		dataIndex : 'mtbf_calc',
		filter : {
			type : 'string'
		}
	}, {
		text : 'MTBF Fabricante',
		
		sortable : true,
		dataIndex : 'mtbf_manf',
		filter : {
			type : 'string'
		}
	}, {
		text : 'Data de Instalação',
		
		sortable : true,
		dataIndex : 'install_date',
		filter : {
			type : 'date'
		}
	}, {
		text : 'Data de Fabricação',
		
		sortable : true,
		dataIndex : 'manufacture_date',
		filter : {
			type : 'date'
		}
	}, {
		text : 'Data de Aquisição',
		
		sortable : true,
		dataIndex : 'acquired_date',
		filter : {
			type : 'date'
		}
	}, {
		text : 'Observação',
		
		sortable : true,
		dataIndex : 'remark',
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
            		text: 'Enviar Arquivo...',
            		handler: function(item,event){
            			Ext.create('Ext.window.Window',{
            				
            				autoShow: true,
            				height: 170,
            				width: 360,
            				resizable: false,
            				layout: {
            					type: 'fit'
            				},
            				//iconCls: 'key',
            				title: "Enviar Documento",
            				closeAction: 'hide',
            				closable: false,			
            				items: [
            					{
            						xtype: 'form',
            						frame: false,
            						bodyPadding: 15,
            						defaults: {
            							xtype: 'textfield',
            							anchor: '100%',
            							labelWidth: 60,
            							allowBlank :false,
            							minLength: 3,
            							msgTarget: 'under',
            							enableKeyEvents: true,
            						    listeners: {
            						        keyup: function(form, e) {
            						        	//Enter
            						            if(e.getKey() == 13){
//            						            	submit();
            						            //Esc
            						            }else if(e.getKey() == 27){
//            						            	fnResetForm(Ext.getCmp('formPanel'))
            						            }
            						        }
            						    }
            						},
            						items: [{
    									xtype : 'filefield',
    									name: 'image',
    									fieldLabel: 'Arquivo',
    									labelWidth: 50,
    									msgTarget: 'side',
    									allowBlank: false,
    									anchor:'100%',
    									buttonText: 'Selecione um arquivo....'
    								}]
            					}
            				],

            				dockedItems: [
            					{
            						xtype: 'toolbar',
            						dock: 'bottom',
            						items: [
            							{
            								xtype: 'tbfill'
            							},{
            								xtype: 'button',
            								itemId: 'submit',
            								formBind: true,
            								text: 'Enviar Arquivo',
            								cls:'x-btn-default-small',
            								handler: function(button) {
            									var form = button.up('window').down('form').getForm();
            									if(form.isValid()){
            					                    form.submit({
            					                        url: 'document/upload/uploadfile',
            					                        waitMsg: 'Enviando o arquivo...',
            					                        success: function(fp, o) {
            					                            Ext.Msg.alert('Success', 'Seu arquivo foi enviado.');
            					                        }
            					                    });
            									}
            								}
            							}
            						]
            					}
            				]
            			});
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