Ext.application({
	name : 'Sam',
	
	launch : function() 
	{
		Ext.widget('mainviewport');
	},
	
	views:['MainViewport'],
	
	stores: ['EquipmentManufacturer',
	        'EquipmentCounter',
	     	'EquipmentManufacturer',
	     	'Equipment',
	     	'EquipmentModel',
	     	'EquipmentType'],
   
	models: ['EquipmentManufacturer',
		        'EquipmentCounter',
		     	'EquipmentManufacturer',
		     	'Equipment',
		     	'EquipmentModel',
		     	'EquipmentType'],

	controllers : [ 
	   'Menu',
	   'Alarms',
	   'ServiceOrder',
	   'Equipment',
	   'Technician'
	   ]
});