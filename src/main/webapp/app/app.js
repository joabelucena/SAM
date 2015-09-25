 Ext.application({
	name : 'Sam',
	
	requires: ['Sam.lib.AssociatedWriter','Sam.lib.AdvancedVType'],
	
	launch : function() 
	{
		Ext.widget('mainviewport');
	},
	
	views:['MainViewport', 'reports.spagobi.SpagoBi'],
	

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
	     	,'ServiceOrderStatus'		,'UserRole'
	     	,'Sam.store.TaskCondition'],
   
	controllers : [ 'Menu'				,'Events'
	               ,'ServiceOrder'		,'Equipment'
	               ,'Technician'		,'Site'
	               ,'SeverityLevel'		,'Alarm'
	               ,'Document'			,'User'
	               ,'Task'				]
});