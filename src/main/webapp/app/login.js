Ext.application({
	name: 'login',

	launch: function() {
		
			Ext.create('Ext.window.Window',{
			

			autoShow: true,
			height: 170,
			width: 360,
			layout: {
				type: 'fit'
			},
			//iconCls: 'key',
			title: "SAM - Login",
			closeAction: 'hide',
			closable: false,
			
			items: [
				{
					id: 'formPanel',
					name: 'formPanel',
					xtype: 'form',
					frame: false,
					bodyPadding: 15,
					defaults: {
						xtype: 'textfield',
						anchor: '100%',
						labelWidth: 60,
						allowBlank :false,
						minLength: 3,
						msgTarget: 'under'			
					},
					items: [
						{
							id: 'username',
							name: 'username',
							fieldLabel: "Usuário",
							vtype: 'alphanum',
							maxLength: 45
						},			
						{
							id: 'password',
							name: 'password',
							inputType: 'password',
							fieldLabel: "Senha",
							maxLength: 45
						}		
					]		
				}
			],

			dockedItems: [
				{
					xtype: 'toolbar',
					dock: 'bottom',
					items: [
						{
							xtype: 'tbfill'
						},{
							xtype: 'button',
							itemId: 'cancel',
							text: 'Cancelar'
						},{
							xtype: 'button',
							itemId: 'submit',
							formBind: true,
							text: 'Login',
							handler: function() {
								submit();
							}
								
						}
					]
				}
			]
		});
	}

});


function submit(){
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
}

function fnResetForm(theForm) {
	theForm.getForm().reset();
} // end fnResetForm
