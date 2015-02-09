Ext.define('Sam.controller.Alarms', {
	extend: 'Ext.app.Controller',
	
	stores: ['Alarms'],
	
	models: ['Alarm'],
	
	views: ['alarm.AlarmGrid'],
	
	init: function() {
		
		this.control({
			'alarmgrid': {
				selectionchange: this.gridSelectionchange
			}
		});
	},
	
	gridSelectionChange: function(model, records) {
		
		if (records[0]) {
			//TODO Verificar Seleção de Grid
		}
	},
});