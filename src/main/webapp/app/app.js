 Ext.application({
	name : 'Sam',
	
	launch : function() 
	{
		Ext.widget('mainviewport');
	},
	
	views:['MainViewport'],
	

	stores: ['EquipmentManufacturer'	,'Task'
	        ,'ServiceStation'			,'SeverityLevel'
	     	,'Equipment'				,'EquipmentModel'
	     	,'EquipmentType'			,'EquipmentProtocol'
	     	,'ServiceOrderJob'			,'Technician'
	     	,'Alarm'					,'AlarmGroup'
	     	,'AlarmType'				,'AlarmFilter'
	     	,'OperationalState'			,'SiteType'
	     	,'Site'						,'ServiceOrderType'
	     	,'DocumentType'				,'ServiceOrderRules'
	     	,'ServiceOrderStatus'		,'UserRole'],
   
	controllers : [ 'Menu'			,'Events'
	               ,'ServiceOrder'	,'Equipment'
	               ,'Technician'	,'Site'
	               ,'SeverityLevel'	,'Alarm'
	               ,'Document'		,'User'
	               ,'Task'			,'Report']
});