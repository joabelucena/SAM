Ext.define('Sam.controller.Home', {
	extend : 'Ext.app.Controller',
	
	init : function(application) {
		this.control({
			"grid": {
				afterrender: this.onGridAfterRender
			}
		});
	},
	
	onGridAfterRender: function( me, eOpts ){
		var store = me.getStore();
		
		if(store){
			store.load();
		}
	}
});