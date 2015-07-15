Ext.define('Sam.view.ViewportFooter', {
	extend: 'Ext.toolbar.Toolbar',
	alias: 'widget.viewportfooter',
	
	height: 30,
	ui: 'footer',
	items: [
		{
			xtype: 'label',
			html: "<div id='username'>_</div>",
			width: 300,
		},{
			xtype: 'tbfill'
		},{
			xtype: 'tbseparator'
		},{
			xtype: 'label',
			html: "<div id='barclock'>_</div>",
			width: 80,
			
			listeners: {
		    	afterrender: function() {
		    		
		    		//Carrega Usuario | Perfil
		    		Ext.Ajax.request({
	            		url : 'getuser',
	            		method : 'POST',
	            		
	            		success: function (result, request) {
	            			 Ext.fly('username').setHtml(result.responseText);
	            		},
	                    
	            		failure: function (result, request) {
	            			Ext.fly('username').setHtml('Error!');
	                    }	
	        		});
		    		
		    		
		    		//Componente para retornar hora do servidor
		        	var updateClock = function () {
		        		
		        		var syncFreq = 1;				//Update on each X minutes
		        		
		        		if(this.date.getMinutes() % syncFreq === 0 && this.date.getSeconds() === 0){
		        			
		        			Ext.Ajax.request({
			            		url : 'gettime',
			            		method : 'POST',
			            		scope: this,
			            		
			            		success: function (result, request) {
			            			//Returns server time (milisseconds)
			            			this.date = new Date(parseInt(result.responseText));
			            		}
			                    
			        		});
		        			
		        		}else{
		        			this.date.setMilliseconds(this.interval);
		        		}
		        		
		        		Ext.fly('barclock').setHtml(Ext.Date.format(this.date, 'g:i:s A'));
						
					};
					 
					
					var runner = new Ext.util.TaskRunner(),
					task = runner.start({
						run: updateClock,
						interval: 1000,
						date: new Date()
					});					
		    	}
			}
		}
	]
});