 Ext.application({
	name : 'Sam',
	
	requires: [ 'Sam.lib.AssociatedWriter'
	           ,'Sam.lib.AdvancedVType'
	           ,'Sam.lib.DateTimePicker'
	           ,'Sam.lib.DateTimeField'
	           ,'Sam.lib.GroupField'
	           ,'Sam.lib.AutoGrid'
	           ,'Sam.lib.AutoStore'
	           ,'Sam.lib.Util'],
	
	launch : function()
	{
		Ext.widget('mainviewport');
	},
	
	views: [
	        'MainViewport'	,'reports.spagobi.SpagoBi', 'reports.jasper.JasperReports', 'reports.jasper.ReportParameter'
	        ],

	stores: [
	         'EquipmentManufacturer'	,'Task'
	        ,'ServiceStation'			,'SeverityLevel'
	     	,'Equipment'				,'EquipmentModel'
	     	,'EquipmentType'			,'EquipmentProtocol'
	     	,'ServiceOrderJob'			,'Technician'
	     	,'Alarm'					,'AlarmGroup'
	     	,'AlarmType'				,'AlarmFilter'
	     	,'OperationalState'			,'SiteType'
	     	,'Site'						,'ServiceOrderType'
	     	,'ServiceOrderRules'		,'TaskEquipment'
	     	,'ServiceOrderStatus'		,'UserRole'
	     	,'Sam.store.TaskCondition'	,'ServiceOrderOccurrence'
	     	,'System'					,'Parameter'
	     	],
   
	controllers: [
	              	'Menu'				,'Events'
	               ,'ServiceOrder'		,'Equipment'
	               ,'Technician'		,'Site'
	               ,'SeverityLevel'		,'Alarm'
	               ,'Task'				,'User'
	               ,'Home'				,'Parameter']
});
