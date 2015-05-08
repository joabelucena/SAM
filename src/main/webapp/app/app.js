Ext.application({
	name : 'Sam',
	
	launch : function() 
	{
		Ext.widget('mainviewport');
	},
	
	views:['MainViewport'],
	
	stores: ['EquipmentManufacturer',
	        'EquipmentCounter',
	     	'Equipment',
	     	'EquipmentModel',
	     	'EquipmentType',
	     	'EquipmentProtocol',
	     	'ServiceOrderJob',
	     	'Technician'],
   /*
	models: ['EquipmentManufacturer',
		        'EquipmentCounter',
		     	'Equipment',
		     	'EquipmentModel',
		     	'EquipmentType',
		     	'EquipmentProtocol'],
	*/
	controllers : [ 
	   'Menu',
	   'Alarms',
	   'ServiceOrder',
	   'Equipment',
	   'Technician'
	   ]
});