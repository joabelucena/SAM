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
	     	'ServiceOrderType',
	     	'DocumentType',
	     	'ServiceOrderRules',
	     	'ServiceOrderStatus',
	     	'UserRole',
	     	'Task'],
   
	controllers : [ 
	   'Menu',
	   'Events',
	   'ServiceOrder',
	   'Equipment',
	   'Technician',
	   'Site',
	   'SeverityLevel',
	   'Alarm',
	   'Document',
	   'User',
	   'Task']
});