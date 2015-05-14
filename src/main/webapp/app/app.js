Ext.application({
	name : 'Sam',
	
	launch : function() 
	{
		Ext.widget('mainviewport');
	},
	
	views:['MainViewport'],
	

	stores: ['EquipmentManufacturer',
	        'EquipmentCounter',
	        'ServiceStation',
	     	'Equipment',
	     	'EquipmentModel',
	     	'EquipmentType',
	     	'EquipmentProtocol',
	     	'ServiceOrderJob',
	     	'Technician',
	     	'OperationalState'],
   
	controllers : [ 
	   'Menu',
	   'Alarms',
	   'ServiceOrder',
	   'Equipment',
	   'Technician',
	   'Site'
	   ]
});