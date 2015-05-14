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
						
		        		Ext.Ajax.request({
		            		url : 'gettime',
		            		method : 'POST',
		            		
		            		success: function (result, request) {
		            			 Ext.fly('barclock').setHtml(result.responseText);
		            		},
		                    
		            		failure: function (result, request) {
		            			Ext.fly('barclock').setHtml(Ext.Date.format(new Date(), 'g:i:s A'));
		                    }	
		        		});
		        		
					};
					/**** comentado para analise de chamadas de rede. 
					 * TODO Verificar criação de objeto local e sincronização com servidor a cada 5 min
					var runner = new Ext.util.TaskRunner(),
					task = runner.start({
						run: updateClock,
						interval: 1000
					});
					*/
		    	}
			}
		}
	]
});