Ext.define('Sam.controller.Alarms', {
	extend: 'Ext.app.Controller',
	 
	views: ['alarm.AlarmPanel','alarm.AlarmGrid'],
	
	init: function() {
		
		this.control({
			'alarmgrid': {
				render: this.onRender,
			},
			'checkcolumn': {
				checkchange: this.checkboxChanged,
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
		            		url : 'events/recognize/' + Ext.getCmp('alarmgridpanel').getStore().getAt(rowIndex).get('id'),
		            		method : 'POST'
		            	});
		            } else if(btn == 'cancel') {
		            	Ext.getCmp('alarmgridpanel').getStore().getAt(rowIndex).set('knowledge_user', "false");
		            }
		        }
			});
		 } else {
         	Ext.getCmp('alarmgridpanel').getStore().getAt(rowIndex).set('knowledge_user', "true");
		 }

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
		   
		   interval: 2000 // (1 second = 1000)
		
		};

		Ext.TaskManager.start(task);
	},
	
});