Ext.define('Sam.view.Login', {
	extend: 'Ext.window.Window',
	alias: 'widget.login',

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
					//iconCls: 'cancel',
					text: 'Cancelar'
				},{
					xtype: 'button',
					itemId: 'submit',
					//iconCls: 'key-go',
					formBind: true,
					text: 'Login'
				}
			]
		}
	]
});
