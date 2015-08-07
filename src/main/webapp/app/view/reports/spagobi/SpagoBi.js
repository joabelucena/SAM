Ext.define('Sam.view.reports.spagobi.SpagoBi', {
	extend: 'Ext.Panel',
	
	alias:  'widget.spagobi',
	
	layout: {
        type: 'fit'
    },
	
	/***** Ajax Request Before Render *****/
	listeners: {
		beforerender:  function(me,eOpts) {
			
			var label = this.spagoLabel;
			
			var rpt = {
					xtype: 'container',
					layout: 'fit',
					autoEl: {
						tag: 'iframe',
						src: 'report.jsp',
						style: 'border: 0px; height:100% ; width:100%'
					}
			}
			
			Ext.Ajax.request({
	    		url : 'rpt/setReportInfo',
	    		method : 'POST',
	    		
	    		params: {
	    			label: label	    			
	    		},
	
	    		success: function (result, request) {
	                me.add(rpt);     
	    		}	    			
	    	});
			
			
		}
			
	}
	
});