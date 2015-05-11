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
	     	'Technician'],
   
	controllers : [ 
	   'Menu',
	   'Alarms',
	   'ServiceOrder',
	   'Equipment',
	   'Technician',
	   'Site'
	   ]
});