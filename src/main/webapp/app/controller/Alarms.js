Ext.define('Sam.controller.Alarms', {
	extend: 'Ext.app.Controller',
	
	views: ['alarm.AlarmPanel',
	        'alarm.AlarmGrid',
	        'alarm.openSO.AlarmOpenSO',
	        'alarm.openSO.AlarmDataOpenSO',
	        'alarm.openSO.AlarmInfoEqto',
	        'alarm.openSO.histSO.AlarmHistSO',
	        'Sam.view.alarm.openSO.histSO.AlarmHistSOGrid',
	        'Sam.view.alarm.openSO.histSO.AlarmHistSOForm'
	        ],
	
	eventID: 'null',
	
	init: function() {
		
		this.control({
			'alarmgrid': {
				render: this.onRender,
			},
			'checkcolumn': {
				checkchange: this.checkboxChanged,
			 },
			 'toolbar #recognizeallbutton': {
				 click: this.recognizeAll,
			 },
			 '#openso': {
				 click: this.openSO,
			 },
			 '#alarmgridpanel' : {
				 itemdblclick: this.openPopUp,
			 },
			 'actioncolumn' : {
				 itemClick: this.onActionColumnItemClick,
			 },
			 'alarmhistsogrid' : {
				 itemmouseup: this.onItemMouseUp,
			 },
			 'toolbar #openSoButtom' : {
				 click: this.onOpenSoButtomClick,
			 },
			 
		});
	},
	
	onOpenSoButtomClick : function(  ){
		
		var mainPanel = Ext.getCmp('viewportpanel');
		
		alert('O Homem Nao chora');
	},
	
	onItemMouseUp : function( me, record, item, index, e, eOpts ){

		Ext.getCmp('SODetailForm').getForm().findField('sodetailform_id').setValue(record.get('id'));
		Ext.getCmp('SODetailForm').getForm().findField('sodetailform_status').setValue(record.get('status'));
		Ext.getCmp('SODetailForm').getForm().findField('sodetailform_type').setValue(record.get('type'));
		Ext.getCmp('SODetailForm').getForm().findField('sodetailform_equipment').setValue(record.get('equipment_id'));
		Ext.getCmp('SODetailForm').getForm().findField('sodetailform_alarm').setValue(record.get('alarm'));
		
	},

	onActionColumnItemClick : function(view, rowIndex, colIndex, item, e, record, row, action) {
		//Abrir OS
		if(action == 1){
			
			var mainPanel = Ext.getCmp('viewportpanel');
			
			var newTab = mainPanel.items.findBy(
					function(tab){
						return tab.title === 'Abertura de OS';
					});
			
			if (!newTab) {
				newTab = mainPanel.add({
					xtype: 'alarmopenso',
					closable: true,
					iconCls: 'notebook-plus-icon',
					title: 'Abertura de OS'
				});
			}
			
			mainPanel.setActiveTab(newTab);
			
			eventID = record.get('id');
			
			Ext.Ajax.request({
	    		url : 'events/getinfo',
	    		method : 'POST',
	    		
	    		params: {
	    			eveId: eventID
	    		},
	    		
	    		success: function (result, request) {
	                
	    			 var jsonResp = Ext.util.JSON.decode(result.responseText);
	
	    			 Ext.getCmp('alarminfoeqtoform').getForm().findField('alarmpopup_id').setValue(jsonResp.id);
	    			 Ext.getCmp('alarminfoeqtoform').getForm().findField('alarmpopup_model').setValue(jsonResp.model);
	    			 Ext.getCmp('alarminfoeqtoform').getForm().findField('alarmpopup_manufacturer').setValue(jsonResp.manufacturer);
	    			 Ext.getCmp('alarminfoeqtoform').getForm().findField('alarmpopup_subsystem').setValue(jsonResp.subsystem);
	    			 Ext.getCmp('alarminfoeqtoform').getForm().findField('alarmpopup_site').setValue(jsonResp.site);
	    			 Ext.getCmp('alarmdataopensoform').getForm().findField('alarmpopup_time_alarm').setValue(jsonResp.datetime);
	    			 Ext.getCmp('alarmdataopensoform').getForm().findField('alarmpopup_severity').setValue(jsonResp.severity);
	    			 Ext.getCmp('alarmdataopensoform').getForm().findField('alarmpopup_reco_user').setValue(jsonResp.reco_user);
	    			 Ext.getCmp('alarmdataopensoform').getForm().findField('alarmpopup_reco_time').setValue(jsonResp.reco_time);
	    			 Ext.getCmp('alarmdataopensoform').getForm().findField('alarmpopup_so_type').setStore(Ext.data.Store({
	    			        fields: ['type'],
	    			        data : jsonResp.so_type
	    			    }));    
	    			 
	    			 
	    			 
	    		},
	            
	    		failure: function (result, request) {
	    			Ext.Msg.alert('Falha de Reconhecimento de Alarme', result.status); 
	            }	
			});
		//Normalizar alarme
		}else if(action ==2){
			Ext.MessageBox.show({
		        title: 'Normalizçaão de Alarme',
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
		                             
			                    if (result.responseText != "SUCCESS") {
			                    	Ext.Msg.alert('Falha de Normalização de Alarme', result.responseText);        	 
			                    }
		                             
		            		},
		                    
		            		failure: function (result, request) {
		            			Ext.Msg.alert('Falha de Normalização de Alarme', result.status); 
		                    }		
		            			
		            	});
		            	
		            } else if(btn == 'cancel') {
		            	
		            }
		        }
			});
		}

	},
	
	checkboxChanged: function(me, rowIndex, checked, eOpts) {
			
		knowCheck = Ext.getCmp('alarmgridpanel').getStore().getAt(rowIndex).get('knowledge_user');
		
		if (knowCheck) {
		
			Ext.MessageBox.show({
		        title: 'Reconhecimento de Alarme',
		        msg: 'Deseja reconhecer este alarme?',
		        buttons: Ext.MessageBox.OKCANCEL,
		        icon: Ext.MessageBox.WARNING,
		        fn: function(btn,  knowId, knowCheck){
		            if(btn == 'ok'){
		            	Ext.getCmp('alarmgridpanel').getStore().getAt(rowIndex).set('knowledge_user', "true");
		            	
		            	Ext.Ajax.request({
		            		url : 'events/action/recognize',
		            		method : 'POST',
		            		async: false,
		            		
		            		params: {
		            			recognizeId: Ext.getCmp('alarmgridpanel').getStore().getAt(rowIndex).get('id'),
		            		},

		            		success: function (result, request) {
		                             
			                    if (result.responseText != "SUCCESS") {
			                    	Ext.Msg.alert('Falha de Reconhecimento de Alarme', result.responseText);        	 
			                    }
		                             
		            		},
		                    
		            		failure: function (result, request) {
		            			Ext.Msg.alert('Falha de Reconhecimento de Alarme', result.status); 
		                    }		
		            			
		            	});
		            	
		            } else if(btn == 'cancel') {
		            	Ext.getCmp('alarmgridpanel').getStore().getAt(rowIndex).set('knowledge_user', 'false');
		            }
		        }
			});
		 
		} else {
         	
			Ext.getCmp('alarmgridpanel').getStore().getAt(rowIndex).set('knowledge_user', 'true');
		 
		}

	},
	
	recognizeAll: function() {
		
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
	            			recognizeId: Ext.pluck(Ext.getCmp('alarmgridpanel').getStore().getData('id').items,'id'),
	            		},

	            		success: function (result, request) {
	                             
		                    if (result.responseText != "SUCCESS") {
		                    	Ext.Msg.alert('Falha de Reconhecimento de Alarme', result.responseText);        	 
		                    }
	                             
	            		},
	                    
	            		failure: function (result, request) {
	            			Ext.Msg.alert('Falha de Reconhecimento de Alarme', result.status); 
	                    }		
	            			
	            	});
	            	
	            } else if(btn == 'cancel') {
	            	Ext.getCmp('alarmgridpanel').getStore().getAt(rowIndex).set('knowledge_user', "false");
	            }
	        }
		});
	},

	onRender: function(component, options) {
	
		var loading = false;
		
		var task = 
		{
		   run : function() 
		   {
			   if (loading == false){
			   loading = true
			   component.getStore().load(
			      function(records, operation, success) {
			    	  loading = false;
				  }
			   );
			   component.getView().setLoading(false);
			   component.refresh();
			   }
		   },
		   
		   interval: 2000 //(1 second = 1000)
		
		};

		Ext.TaskManager.start(task);
	}
	
});