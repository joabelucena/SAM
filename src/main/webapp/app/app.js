Ext.application({
	name : 'Sam',
	
	launch : function() 
	{
		Ext.widget('mainviewport');
	},
	
	views:['MainViewport'],

	stores: ['EquipmentManufacturer'],
   
	models: ['EquipmentManufacturer'],

	controllers : [ 
	   'Menu',
	   'Alarms',
	   'ServiceOrder',
	   'Equipment',
	   'Technician'
	   ]
});