Ext.define('Sam.controller.Alarms', {
	extend: 'Ext.app.Controller',
	 
	views: ['alarm.AlarmPanel','alarm.AlarmGrid'],
	
	init: function() {
		
		this.control({
			'alarmgrid': {
				render: this.onRender
				//,selectionchange: this.gridSelectionchange
			}
		});
	},
	
	onRender: function(component, options) {
		component.getStore().load();
	},
	
	/*
	gridSelectionChange: function(model, records) {
		
		if (records[0]) {
			//TODO Verificar Seleção de Grid
		}
	}
	*/
});