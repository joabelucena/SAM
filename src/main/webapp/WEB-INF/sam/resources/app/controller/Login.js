Ext.define('Sam.controller.Login', {
	extend: 'Ext.app.Controller',

	views: [
		'Login'
	],

	init: function(application) {
		this.control({
			"login button#submit": {
				click: this.onButtonClickSubmit
			},
			"login button#cancel": {
				click: this.onButtonClickCancel			
			}	
		});
	},

	onButtonClickSubmit: function(button, e, options) {
		console.log('login submit');
	},

	onButtonClickCancel: function(button, e, options) {
		console.log('login cancel');
	}
});
