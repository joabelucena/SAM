/**
 * 
 */

Ext.application({
	name : 'Sam',
	
	launch : function() 
	{
		Ext.widget('viewport');
	},
	
	views:[
	       'Viewport'
	   ],

	controllers : [ 
	   'Alarms' 
	   ]
})