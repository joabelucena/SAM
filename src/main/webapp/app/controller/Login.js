Ext.define('Sam.controller.Login', {
	extend : 'Ext.app.Controller',

	views : [ 'Login' ],

	init : function(application) {
		this.control({

			"login button#submit" : {
				click : this.onButtonClickSubmit
			},
			"login button#cancel" : {
				click : this.onButtonClickCancel
			},
			'textfield': { 
		        specialkey: function(field, e) { 
		            if(e.getKey() == e.ENTER) { 
		            	this.onButtonClickSubmit(); 
		            } 
		        }
			}
		});
	},

	onButtonClickSubmit : function(button, e, options) {

		var form = Ext.getCmp('formPanel');

		if (form.isValid()) {
			form.submit({
				url : 'j_spring_security_check',
				method : 'POST',

				success : function() {
					window.location = 'index';
				},
				failure : function(form, action) {
					if (action.failureType == 'server') {
						Ext.Msg.alert('Warning', action.result.errorMessage);
					} else {
						Ext.Msg.alert('Warning!',
								'Authentication server is unreachable : '
										+ action.response.responseText);

					}
					form.reset();
				}

			});
		}
	},

	onButtonClickCancel : function(button, e, options) {
		var form = Ext.getCmp('formPanel');
		
		form.reset();
	}
});
