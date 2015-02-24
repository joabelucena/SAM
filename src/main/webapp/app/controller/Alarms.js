Ext.define('Sam.controller.Alarms', {
	extend: 'Ext.app.Controller',
	 
	views: ['alarm.AlarmPanel','alarm.AlarmGrid'],
	
	init: function() {
		
		this.control({
			'alarmgrid': {
				render: this.onRender
				// ,selectionchange: this.gridSelectionchange
			}
		});
	},
	
	onRender: function(component, options) {
		var task = 
		{
		   run : function() 
		   {
			   component.getStore().load();
			   component.getView().setLoading(false);
			   component.refresh();
		   },
		   interval: 2000000 //(1 second = 1000)
		};

		Ext.TaskManager.start(task);
	}
	
	/*
	 * gridSelectionChange: function(model, records) {
	 * 
	 * if (records[0]) { //TODO Verificar Seleção de Grid } }
	 */
});