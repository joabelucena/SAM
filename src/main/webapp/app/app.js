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
	     	'Alarm',
	     	'AlarmGroup',
	     	'AlarmType',
	     	'AlarmFilter',
	     	'OperationalState',
	     	'SiteType',
	     	'Site',
	     	'ServiceOrderType'
	     	],
   
	controllers : [ 
	   'Menu',
	   'Events',
	   'ServiceOrder',
	   'Equipment',
	   'Technician',
	   'Site',
	   'SeverityLevel',
	   'Alarm'
	   ]
});