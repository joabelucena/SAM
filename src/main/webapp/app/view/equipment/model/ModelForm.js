/**
 * Protocol Fieldset
 */
var protocol = {
	xtype : 'fieldset',
	defaultType : 'textfield',
	title : 'Protocolo',
	itemId: 'fldProtocol',
	layout : {
		type : 'vbox',
	},

	items : [ {
		xtype: 'textfield',
		fieldLabel : 'Código',
		itemId: 'prot_id',
		name: 'prot_id',
		editable: false,
		width: '40%',
		allowBlank : false,
		inputAttrTpl: " data-qtip='Código do Protocolo' ",
		triggers: {f3: {handler: function() {this.fireEvent('click')}}}
	}, {
		fieldLabel : 'Descrição',
		itemId: 'prot_desc',
		name: 'prot_desc',
		readOnly : true,
		width: '50%',
		inputAttrTpl: " data-qtip='Descrição do Protocolo' ",
	}],
};

/**
 * Model FieldSet
 */
var model = {
	xtype : 'fieldset',
	defaultType : 'textfield',
	title : 'Dados do Modelo',
	itemId: 'fldModel',
	layout : {
		type : 'vbox',
	},

	items : [{
		fieldLabel : 'Código',
		itemId: 'id',
		name: 'id',
		allowBlank : true,
		editable: false,
		width: '30%',
		inputAttrTpl: " data-qtip='Código do Modelo' ",
	},{
		fieldLabel : 'Descrição',
		itemId: 'desc',
		name: 'desc',
		allowBlank : false,
		width: '60%',
		inputAttrTpl: " data-qtip='Descrição do Modelo' "
	}],
};
/**
 * PAGE HEADER
 */
var header = {
	xtype: 'container',

	layout:{
		type: 'fit',
	},
	
	items : [ {
		xtype : 'form',

		defaultType : 'textfield',

		fieldDefaults : {
			labelWidth : 180
		},
		defaults:{
			allowBlank : false
		},

		layout : {
			type : 'vbox',
			align : 'stretch'
		},

		bodyPadding : 10,
		border : false,
		items : [ protocol, model ],
		
		scrollable: true,

	}]	
};

/**
 * PAGE FOOTER
 */
var footer = {
	xtype: 'gridpanel',
	itemId : 'documentsgrid',
	width: '100%',
	height: '100%',
	border: false,
	
	columns : {
		
		defaults:{
			menuDisabled: true,
			sortable: false,
			editor: 'textfield'
		},
		
		items: [{
				text : 'Descrição',
				width: 300,
				sortable : true,
				dataIndex : 'desc',
			}, {
				text : 'URL',
				flex: 1,
				sortable : true,
				dataIndex : 'url',
			},{
				text : 'Download',
				xtype: 'actioncolumn',
				itemId: 'actionClm',
				width: 70,
				align: 'center',
				items: [{
					iconCls: 'arrow-stop-270',
					tooltip: 'Fazer Download do arquivo',
					handler: function(view, rowIndex, colIndex, item, e, record, row) {
						
						var body = Ext.getBody(),
							hidden = body.createChild({
						            tag:'iframe',
						            cls:'x-hidden',
						            name:'iframe'
							 });
						
						// Create form panel. It contains a basic form that we need for the file download.
					    var form = Ext.create('Ext.form.Panel', {
					        standardSubmit: true,
					        url: 'document/download',
					        method: 'POST'
					    });

					    // Call the submit to begin the file download.
					    form.submit({
					    	target: hidden,
					        params: {
					        	url: record.get('url')
					        }
					    });

					    // Clean-up the form after 100 milliseconds.
					    // Once the submit is called, the browser does not care anymore with the form object.
					    Ext.defer(function(){
					        form.close();
					    }, 100);
					}
				}]
			}]
	}
};

Ext.define('Sam.view.equipment.model.ModelForm', {
	extend: 'Ext.Panel',
	
	alias:  'widget.equipmentmodelform',
	
	itemId: 'equipmentmodelform',
	
	closable: false,
		
	layout: 'border',
    width: 500,
    height: 400,

    bodyBorder: false,
    
    defaults: {
        collapsible: false,
        split: true
    },
    
    items: [{
		    title: 'Dados do Modelo',
		    items:[header],
		    itemId: 'center',
		    region: 'center',
		    scrollable: true,
		    margin: '5 0 0 0',
		},{
			title: 'Documentos',
			itemId: 'footer',
			items: [footer],
			collapsible: true,
			region: 'south',
			margin: '5 0 0 0',
			layout: 'fit',
			scrollable: true,
			minHeight: 250,
			height: 250,
		}],
	
		dockedItems: [{
		    xtype: 'toolbar',
		    dock: 'bottom',
		    
		    items: [{
		    	xtype: 'tbfill'
			    },{
					xtype: 'button',
					itemId: 'btnAddDoc',
					tooltip:'Inclui novo Documento',
					width: 50,
					disabled: true,
					iconCls: 'plus',
					handler: function(button){
						
						Ext.create('Ext.window.Window',{
            				autoShow: true,
            				height: 170,
            				width: 360,
            				resizable: false,
            				layout: {
            					type: 'fit'
            				},
            				title: "Enviar Documento...",
            				closable: true,
            				items: [
            					{
            						xtype: 'form',
            						frame: false,
            						bodyPadding: 15,
            						items: [{
            							xtype: 'textfield',
            							name: 'id',
            							hidden: true,
            							editable: false
            						},{
            							xtype: 'textfield',
            							fieldLabel : 'Descrição',
            							labelWidth: 60,
            							name: 'desc',
            							allowBlank : false,
            							editable: true,
            							width: '100%',
            							inputAttrTpl: " data-qtip='Descrição do Arquivo' ",
            						},{
    									xtype : 'filefield',
    									name: 'file',
    									fieldLabel: 'Arquivo',
    									labelWidth: 60,
    									msgTarget: 'side',
    									allowBlank: false,
    									anchor:'100%',
    									buttonText: 'Selecionar'
    								}]
            					}
            				],

            				dockedItems: [{
            						xtype: 'toolbar',
            						dock: 'bottom',
            						items: [
            							{
            								xtype: 'tbfill'
            							},{
            								xtype: 'button',
            								formBind: true,
            								scope: this,
            								text: 'Enviar Arquivo',
            								cls:'x-btn-default-small',
            								handler: function(button) {
            									var wind = form = button.up('window'),
            										form = wind.down('form').getForm();
            									if(form.isValid()){
            										
            										form.submit({
            					                        url: 'document/upload',
            					                        waitMsg: 'Enviando o arquivo...',
            					                        success: function(fp, o) {
            					                        	
            					                        	var record =  Ext.getCmp('viewportpanel').getActiveTab().down('form')
				                											.getForm()
				                											.getRecord(),
				                								store = record.store;
                										
	                										if(record){
	                							                //Exibir Mensagem
	                							            	Ext.MessageBox.show({
	                										        title: 'Upload',
	                										        msg: 'Arquivo enviado com sucesso.',
	                										        buttons: Ext.MessageBox.OK,
	                										        icon: Ext.MessageBox.INFO
	                											});
	                							            	
	                							            	//Atualiza Grid
	                							            	Sam.app.getController('ServiceOrder')
	                							            		.syncStore(record.store,'equipmentmodelgrid',false);
	                											
	                											wind.close();
	                										}
	                										
	                										store.sync();
	                										
	                										store.reload();
	                										/**
	                										 * atualizar grid
	                										 */
	                										console.log('para');
            					                        },
            					                        failure: function (fp, o) {
            					                        	 //Exibir Mensagem
                							            	Ext.MessageBox.show({
                										        title: 'Upload',
                										        msg: 'Erro no envio de arquivo.',
                										        buttons: Ext.MessageBox.OK,
                										        icon: Ext.MessageBox.WARNING
                											});
            					                        }
            					                    });
            									}
            									
            									
            								}
            							}
            						]
            					}],
            				listeners: {
            			        afterrender: {
            			            fn: function(me,options){
            			            		me.down('form').getForm().
            			            			setValues({
            			            						id: Ext.getCmp('viewportpanel').getActiveTab().down('form').getForm().getRecord().get("id")
            			            					});
            			            }
            			        }
            			    }
            			});
					}
				},{
			    	xtype: 'tbseparator'
			    },{
			        xtype:'button',
			    	itemId:'btnSubmit',
			    	text:'Confirma',
			        tooltip:'Confirma',
			        cls:'x-btn-default-small',
			        iconCls: 'tick-button'
			    }]
		}]
	
});

