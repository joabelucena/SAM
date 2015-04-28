Ext.define('Sam.controller.Technician', {
	extend: 'Ext.app.Controller',
	

	views: ['Sam.view.technician.TechnicianGrid'],

	init: function() {
		
		this.control({
			'techniciangrid': {
				render: this.onRender,
			}
		});
	},
	
	
	//ServiceOrder > grid : onRender
	onRender: function(me, eOpts) {
		//me.getStore().reload();
		//me.getView().refresh();
	}
	
});
