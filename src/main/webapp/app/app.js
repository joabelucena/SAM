Ext.application({
	name : 'Sam',
	
	launch : function() 
	{
		Ext.widget('mainviewport');
	},
	
	views:[
	       'MainViewport'
	       ],

	controllers : [ 
	   'Menu',
	   'Alarms',
	   'ServiceOrder',
	   'Equipment',
	   'Technician'
	   ]
});