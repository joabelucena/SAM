Ext.define('Sam.controller.ServiceOrder', {
	extend: 'Ext.app.Controller',
	
	views: ['serviceOrder.ServiceOrderGrid',
	        'serviceOrder.ServiceOrderPanel'],
	
	init: function() {
		
		this.control({
			'serviceordergrid': {
				render: this.onRender,
			},
			'toolbar #button1' :{
				click: this.onButtonAClick
			},
			'toolbar #button2' :{
				click: this.onButtonBClick
			}
		});
	},
	
	onRender: function(component, options) {
		component.getStore().load();
	},
	
	onButtonAClick: function() {
		
		Ext.Msg.alert('botao1', 'botao1');
		
	},
	onButtonBClick: function() {
		Ext.Msg.alert('botao2', 'botao2');    
	}
	
	
});
