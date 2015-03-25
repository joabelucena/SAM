Ext.define('Sam.controller.Alarms', {
	extend: 'Ext.app.Controller',
	 
	views: ['alarm.AlarmPanel','alarm.AlarmGrid','alarm.AlarmPopUp'],
	
	eventID: 'null',
	
	init: function() {
		
		this.control({
			'alarmgrid': {
				render: this.onRender,
			},
			'alarmpopup': {
				render: this.openSO,
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
			 }
		});
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
		            		url : 'events/recognize',
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
	            		url : 'events/recognize',
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
	
	openPopUp: function(dv, record, item, index, e) {
		
		Ext.Msg.alert('', 'É necessário reconhecer o alarme primeiro');
    	
	},

	openSO: function() {		
		Ext.Ajax.request({
    		url : 'events/getinfo',
    		method : 'POST',
    		
    		params: {
    			eveId: this.eventID 
    		},
    		
    		success: function (result, request) {
                
    			 var jsonResp = Ext.util.JSON.decode(result.responseText);

    			 Ext.getCmp('alarmpopup_form').getForm().findField('alarmpopup_id').setValue(jsonResp.id);
    			 Ext.getCmp('alarmpopup_form').getForm().findField('alarmpopup_model').setValue(jsonResp.model);
    			 Ext.getCmp('alarmpopup_form').getForm().findField('alarmpopup_manufacturer').setValue(jsonResp.manufacturer);
    			 Ext.getCmp('alarmpopup_form').getForm().findField('alarmpopup_subsystem').setValue(jsonResp.subsystem);
    			 Ext.getCmp('alarmpopup_form').getForm().findField('alarmpopup_site').setValue(jsonResp.site);
    			 Ext.getCmp('alarmpopup_form').getForm().findField('alarmpopup_time_alarm').setValue(jsonResp.datetime);
    			 Ext.getCmp('alarmpopup_form').getForm().findField('alarmpopup_severity').setValue(jsonResp.severity);
    			 Ext.getCmp('alarmpopup_form').getForm().findField('alarmpopup_reco_user').setValue(jsonResp.reco_user);
    			 Ext.getCmp('alarmpopup_form').getForm().findField('alarmpopup_reco_time').setValue(jsonResp.reco_time);
    			 Ext.getCmp('alarmpopup_form').getForm().findField('alarmpopup_so_type').setStore(jsonResp.so_type);
                     
    		},
            
    		failure: function (result, request) {
    			Ext.Msg.alert('Falha de Reconhecimento de Alarme', result.status); 
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