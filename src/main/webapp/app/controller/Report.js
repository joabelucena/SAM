Ext.define('Sam.controller.Report', {
	extend: 'Ext.app.Controller',
	
	views: ['Sam.view.reports.spagobi.SpagoBi'],

	init: function() {
		
		this.control({

			'#spagobi' :{
//				beforerender: this.onSpagoBiRender
			}
			
		});
	},
	
	
	onSpagoBiRender: function(me,eOpts) {
		
//		//Aba Objecto Pai
		var mainPanel = Ext.getCmp('viewportpanel');
//		
//		//Variavel para retornar aba ativa
		var activeTab = mainPanel.getActiveTab();
		
		
		
		Ext.Ajax.request({
    		url : 'rpt/1',
    		method : 'POST',
    		
    		params: {
    			str1: 'str1',
    			str2: 'str2',
    			int1: 1,
    			int2: 2,
    			
    		},

    		success: function (result, request) {

                     
    		},
            
    		failure: function (result, request) {
    			Ext.Msg.alert('Falha na Abertura da OS', result.status); 
            }
    			
    	});
		
		
		
//		
//		console.log('Antes de renderizar');
//		
//		
		/**
		Sbi.sdk.services.setBaseUrl({
	        protocol: 'http'     
	        , host: '10.114.0.130'
	        , port: '8180'
	        , contextPath: 'SpagoBI'
	        , controllerPath: 'servlet/AdapterHTTP'  
	    });
	    */
		
		/*
	    * authentication function
	    *
		* @param params the list of parameters to pass to the authentication servics (i.e. user & password)
		* @param callback the callback definition (i.e. fn: the function to call; scope: the scope of invocation; 
		*        args: parameters to append to the callback invocation)
	    */
		/**
	    Sbi.sdk.api.authenticate({
			params: {
				user: 'biuser'
				, password: 'biuser'
			}
			
			, callback: {
				fn: function(result, args, success) {
			        
					if(success === true) {
						console.log('Autenticado com sucesso');
//						window.open('http://10.114.0.130:8180/SpagoBI/','_blank');
					} else {
						alert('Não foi possivel autenticar');
					}
			    },
				scope: this
			}
		});
		*/
		
	    
//	    window.open('http://www.uol.com.br','_blank');
	    
	    
//	    var url = Sbi.sdk.api.getDocumentUrl({
//			documentLabel: 'ws__439663929'
//			, executionRole: '/spagobi/user'
//			, displayToolbar: false
//			, displaySliders: false
//			, iframe: {
//				style: 'border: 0px;'
//			}
//		});
//		    
//		    
		    var rpt = {
		    		layout: 'fit',
			        xtype: 'box',
			        autoEl: {
			            tag: 'iframe',
			            src: 'http://www.uol.com.br'
			        }
		    };
	    
//	    activeTab.close();
//	    console.log('Escondee');
//	    me.close();
//	    console.log(url);
//		    
//	    var frame = Ext.ComponentQuery.query('#frame',me)[0];
	    
//	    frame.autoEl.src = url;
	    
	    
	
	}
	
});

