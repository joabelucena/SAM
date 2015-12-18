Ext.define('Sam.controller.Events', {
	extend: 'Ext.app.Controller',
	
	views: ['event.EventGrid',
	        'event.EventShow',
	        'event.openSO.EventOpenSO',
	        'event.openSO.EventDataOpenSO',
	        'event.openSO.EventInfoEqto',
	        'event.openSO.EventHistSO'
	        ],
	        
	init: function() {
		
		this.control({
			'#eventgrid checkcolumn': {
				checkchange: this.checkboxChanged,
			},
			'#eventgrid #btnRecAll': {
				click: this.recognizeAll,
			},
			'#eventgrid actioncolumn' : {
				create: this.onActionColumnCreateItemClick,
				norm: this.onActionColumnNormItemClick,
				show: this.onActionColumnShowItemClick
			},
			'#eventdataopenso #btnOpenSo' : {
				click: this.onOpenSoButtomClick,
			},
		});
	},
	
	onOpenSoButtomClick : function(button){
		
		// Objeto Form
		var form = button.up('form').getForm();
		
		// Verifica se o form eh valido
		if(form.isValid()){
			var openSOTab = Ext.getCmp('viewportpanel').items.findBy(
					function(tab){
						return tab.title === 'Abertura de OS';
					});
			
			Ext.MessageBox.show({
		        title: 'Abertura de OS',
		        msg: 'Confirma a Abertura da OS?',
		        buttons: Ext.MessageBox.OKCANCEL,
		        icon: Ext.MessageBox.WARNING,
		        fn: function(btn,  knowId, knowCheck){
		            if(btn == 'ok'){
		            	
		            	var record	= Ext.create('Sam.model.ServiceOrder'),
		            		values = form.getValues();
		            	
		    			//Carrega dados do Formulario no registro
		    			record.set(values);
		    			
		    			record.set({
		    					type			: Ext.create('Sam.model.ServiceOrderType'	,{id: values.type_id})								,
		    					priority		: Ext.create('Sam.model.SeverityLevel'		,{id: values.severity_id})							,
		    					equipment		: Ext.create('Sam.model.Equipment'			,{id: values.equipment_id})							,
		    					event			: Ext.create('Sam.model.Event'				,{id: values.id})									,
		    					startForecast	: Ext.Date.parse(values.start_forecast_date + " " + values.start_forecast_time	, "d/m/Y H:i")	,
		    					endForecast		: Ext.Date.parse(values.end_forecast_date	+ " " + values.end_forecast_time	, "d/m/Y H:i")	
		    					});
		    			
		    			record.save({
							success: function(e , fn){
								
								var response = Ext.util.JSON.decode(fn.getResponse().responseText);
								
								if(typeof response.id != "undefined"){
									
									if(response.id > 0){
										//Exibir Mensagem
						            	Ext.MessageBox.show({
									        title: 'SAM | Info',
									        msg: 'Os No: ' + response.id+' gerada com sucesso!',
									        buttons: Ext.MessageBox.OK,
									        icon: Ext.MessageBox.INFO
										});
									}else{
										if(typeof response.message != "undefined"){
											
											//Exibir Mensagem
							            	Ext.MessageBox.show({
										        title: 'SAM | Error',
										        msg: 'Ocorreu o seguinte erro ao gerar a OS: ' + response.message + '. Entre em contato com o administrador do sistema.',									        
										        buttons: Ext.MessageBox.OK,
										        icon: Ext.MessageBox.ERROR
											});
										}
										
									}
									 
								}
								
								//Fecha aba de abertura de OS
		                    	if(openSOTab){
		                    		openSOTab.close();
		                    	}
		                    	
		                    	//Atualiza Stores dos grids de OS abertos
		                    	Ext.each(Ext.ComponentQuery.query('#serviceordergrid'),function(f){
		                    		f.getStore().reload();
		                    	});
								
							}
								
		    			});
		            }
		        }
			});
		}
		
	},
	
	/**
	 * Abrir Ordem de Servico
	 */
	onActionColumnCreateItemClick : function (view, rowIndex, colIndex, item, e, record, row){
		
		// Verifica se o Alarme esta reconhecido
		if(!record.get('reco')){
			Ext.Msg.alert('SAM | Info', 'Favor reconhecer o alarme antes de prosseguir com a abertura da OS.');
		}else if(record.get('alarm_desc') === "-"){
			Ext.Msg.alert('SAM | Info', 'O alarme '+ record.get('alarm_id')+' não esta cadastrado. Realize o cadastro deste para prosseguir com a abertura da OS.');
		}else if(record.get('so_id') !== '-'){
			Ext.Msg.alert('SAM | Info', 'Já existe uma Ordem de Serviço aberta para esse evento. Dados da Ordem de Serviço: <br><br>'
					+ 'Código: ' + record.get('so_id') + '<br>'
					+ 'Estado: '+record.get('so_status'));
		}else{
			
			var mainPanel = Ext.getCmp('viewportpanel');
			
			var newTab = mainPanel.items.findBy(
					function(tab){
						return tab.title === 'Abertura de OS';
					});
			
			if (!newTab) {
				newTab = mainPanel.add({
					xtype: 'eventopenso',
					closable: true,
					iconCls: 'notebook-plus-icon',
					title: 'Abertura de OS'
				});
			}
			
			mainPanel.setActiveTab(newTab);
			
			activeTab = mainPanel.getActiveTab();
			
			Ext.each(activeTab.query('groupfield'),function(g){
				if(!(g.itemId === "grpHistInfo") && !(g.itemId === "grpSoInfo")){
					g.loadRecord(record);	
				}
        	});
		}
	},
	
	/**
	 * Normalizar Alarme
	 */
	onActionColumnNormItemClick : function (view, rowIndex, colIndex, item, e, record, row){

		if(!record.get('reco')){
			Ext.Msg.alert('Alarme Não Reconhecido', 'Favor reconhecer o alarme antes de normalizar.'); 
		} else {
		
			Ext.MessageBox.show({
		        title: 'Normalização de Alarme',
		        msg: 'Deseja normalizar este alarme?',
		        buttons: Ext.MessageBox.OKCANCEL,
		        icon: Ext.MessageBox.WARNING,
		        fn: function(btn,  knowId, knowCheck){
		            if(btn == 'ok'){
		            	
		            	Ext.Ajax.request({
		            		url : 'events/action/normalize',
		            		method : 'POST',
		            		async: false,
		            		
		            		params: {
		            			normalizeId: record.get('id') 
		            		},

		            		success: function (result, request) {
		            			
		            			var message = Ext.JSON.decode(result.responseText).message
		                             
			                    if (typeof message != 'undefined') {
			                    	Ext.Msg.alert('Falha de Normalização de Alarme', message);        	 
			                    }
		            		},
		                    
		            		failure: function (result, request) {
		            			Ext.Msg.alert('Falha de Normalização de Alarme', result.responseText); 
		                    }		
		            			
		            	});
		            }
		        }
			});
		}
	},
	
	onActionColumnShowItemClick : function (view, rowIndex, colIndex, item, e, record, row){
		
		var mainPanel = Ext.getCmp('viewportpanel');
		
		var newTab = mainPanel.items.findBy(
				function(tab){
					return tab.title === 'Visualiza Alarme';
				});
		
		if (!newTab) {
			newTab = mainPanel.add({
				xtype: 'eventshow',
				closable: true,
				iconCls: 'magnifier-zoom',
				title: 'Visualiza Alarme'
			});
		}
		
		mainPanel.setActiveTab(newTab);
		
		activeTab = mainPanel.getActiveTab();
		
		Ext.each(activeTab.query('groupfield'),function(g){
			if(!(g.itemId === "grpHistInfo") && !(g.itemId === "grpSoInfo")){
				g.loadRecord(record);	
			}    		
    	});
	
	},
	
	checkboxChanged: function(me, rowIndex, checked, eOpts) {
			
		if (checked) {	
		
			Ext.MessageBox.show({
		        title: 'Reconhecimento de Alarme',
		        msg: 'Deseja reconhecer este alarme?',
		        buttons: Ext.MessageBox.OKCANCEL,
		        icon: Ext.MessageBox.WARNING,
		        fn: function(btn,  knowId, knowCheck){
		            if(btn == 'ok'){
		            	
		            	me.up('grid').getStore().getAt(rowIndex).set('reco',true)
		            	
		            	Ext.Ajax.request({
		            		url : 'events/action/recognize',
		            		method : 'POST',
		            		async: false,
		            		
		            		params: {
		            			recognizeId: me.up('grid').getStore().getAt(rowIndex).get('id')
		            		},
		            		
		            		failure: function (result, request) {
		            			Ext.Msg.alert('Falha de Reconhecimento de Alarme', "Entre em contato com o Administrador do Sistema."); 
		                    }		
		            			
		            	});
		            	
		            } else if(btn == 'cancel') {
		            	me.up('grid').getStore().getAt(rowIndex).set('reco',false)
		            }
		        }
			});
		 
		} else {
         	
			me.up('grid').getStore().getAt(rowIndex).set('reco',true);
		 
		}

	},
	
	recognizeAll: function(button) {
		
		Ext.MessageBox.show({
	        title: 'Reconhecimento de Alarmes',
	        msg: 'Deseja reconhecer todos os alarmes?',
	        buttons: Ext.MessageBox.OKCANCEL,
	        icon: Ext.MessageBox.WARNING,
	        fn: function(btn,  knowId, knowCheck){
	            if(btn == 'ok'){
	            	
	            	Ext.Ajax.request({
	            		url : 'events/action/recognize',
	            		method : 'POST',
	            		async: false,
	            		
	            		params: {
	            			recognizeId: Ext.Array.pluck(button.up('grid').getStore().getData('id').items,'id'),
	            		},

	            		failure: function (result, request) {
	            			Ext.Msg.alert('Falha de Reconhecimento de Alarme', "Entre em contato com o Administrador do Sistema.");
	                    }		
	            			
	            	});
	            }
	        }
		});
	}
	
});
