Ext.define('Sam.view.reports.jasper.JasperReports', {
	extend: 'Ext.Panel',
	
	alias:  'widget.jasper',
	
	layout: {
        type: 'fit'
    },
	
	/***** Ajax Request Before Render *****/
	listeners: {
		beforerender:  function(me,eOpts) {
			
			var label = this.xRPT;
			
			var data = {
						"label"			: label	,
						"EquDe"			: "TAG-001-001"	,
						"EquAte"		: "TAG-001-002"	
						}
			
			var rpt = {
					xtype: 'container',
					layout: 'fit',
					autoEl: {
						tag: 'iframe',
						style: 'border: 0px; height:100% ; width:100%'
					}
			}
			
			var param = Ext.create('Ext.data.Model',data);
			
			console.log('teste');
			
//			me.add({
//				xtype: 'container',
//				layout: 'fit',
////				html: htmlText,
//				
////				autoEl: {
////					tag: 'embed',
////					src: srcPDF,
////					type: 'application/pdf',
////					style: 'border: 0px; height:100% ; width:100%'
////				}
//			
//				autoEl: {
//					tag: 'object',
//					data: 'rpt/jasperget?label=CAD_EQUIPMENTS',
//					type: 'application/pdf',
//					style: 'border: 0px; height:100% ; width:100%'
//				}
//			
//			});
			
			me.autoRender = true;
			
			Ext.Ajax.request({
	    		url : 'rpt/jasper',
	    		method : 'POST',
	    		
	    		params: {
	    			label: label
	    		},
	    		
	    		jsonData: data,
	
	    		success: function (result, request) {
	    			
	    			var htmlText = '<embed width=100% height=100%'
	                    + ' type="application/pdf"'
	                    + ' alt="pdf"' 
	                    + ' src="data:application/pdf;inline,'
	                    +  escape(result.responseText)
	                    + '"></embed>';
	    			
	    			var srcPDF = 'data:application/pdf; inline,'+ escape(result.responseText);
	    			console.log(result.responseText);
	    			me.add({
						xtype: 'container',
						layout: 'fit',
//						html: htmlText,
						
						autoEl: {
							tag: 'embed',
							src: srcPDF,
							type: 'application/pdf',
							headers: 'Transfer-Encoding: chunked',
							style: 'border: 0px; height:100% ; width:100%'
						}
	    			
//						autoEl: {
//							tag: 'object',
//							data: srcPDF,
//							type: 'application/pdf',
//							style: 'border: 0px; height:100% ; width:100%'
//						}
	    			
	    			});
	    			
	    			me.autoRender = true;
	    		}	    			
	    	});
			
			
		}
			
	}
	
});