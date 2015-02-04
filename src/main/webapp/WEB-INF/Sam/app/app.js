Ext.application({
	name: 'Sam',

	launch: function() {
		Ext.widget('login');
	},

	requires: [
		'Sam.view.Login'
	],

	views: [
		'Login'
	],

	controllers: [
		'Login'
	]

});
