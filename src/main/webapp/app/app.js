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
	        'SeverityLevel',
	     	'Equipment',
	     	'EquipmentModel',
	     	'EquipmentType',
	     	'EquipmentProtocol',
	     	'ServiceOrderJob',
	     	'Technician',
	     	'OperationalState'],
   
	controllers : [ 
	   'Menu',
	   'Events',
	   'ServiceOrder',
	   'Equipment',
	   'Technician',
	   'Site',
	   'SeverityLevel'
	   ]
});