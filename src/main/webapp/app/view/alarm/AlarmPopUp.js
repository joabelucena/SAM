Ext.define('Sam.view.alarm.AlarmPopUp', {
	extend: 'Ext.form.Panel',
	alias: 'widget.alarmpopup',
	
	width: 800,
    height: 600,
    bodyPadding: 5,
    floating: true,
    closable : true,
	
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
				}	
			}
		]
});