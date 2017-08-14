Ext.define('Sam.view.reports.jasper.JasperReports', {
	extend : 'Ext.Panel',

	alias : 'widget.jasper',

	xUtils : Ext.create('Sam.lib.Util'),

	layout : {
		type : 'fit'
	},

	/** *** Ajax Request Before Render **** */
	listeners : {
		beforerender : function(me, eOpts) {

			var data = this.xReportData;
			
//			var iframeContainer = new Ext.BoxComponent({
//			    autoEl: {
//			        tag: 'iframe',
//			        frameborder: '0',
//			        src: 'http://wikipedia.org'
//			    },
//			    
//			    
//			    listeners: {
//			        afterrender: function () {
//			            console.log('rendered');
//
//			            this.getEl().on('load', function () {
//			                console.log('loaded');
//			            });
//			        }
//			    }
//			});
//			
//			me.add(iframeContainer);

			me.add({
				xtype : 'container',
				layout : 'fit',
				
				autoEl : {
					tag : 'object',
					data : 'rpt/getReport' + this.xUtils.encodeURL(data),
					type : 'application/pdf',
					style : 'border: 0px; height:100% ; width:100%'
				},
				listeners: {
			        afterrender: function () {

			            this.getEl().on('load', function () {
			                console.log('loaded');
			            });
			        }
			    }

			});

			me.autoRender = true;

		}

	}

});