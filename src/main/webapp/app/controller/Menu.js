Ext.define('Sam.controller.Menu', {
	extend : 'Ext.app.Controller',
	
	models : ['MenuRoot','MenuItem'],
	
	stores : ['Menu'],
	
	views : ['ViewportMenu','ViewportMenuItem'],
	
	refs : [{
			ref : 'ViewportPanel',
			selector : 'viewportpanel'
		}],
		
	init : function(application) {
		this.control({
			"viewportmenu": {
				render: this.onPanelRender
			},
			"viewportmenuitem": {
				itemclick: this.onTreePanelItemClick
			} 
		});
	},

	onPanelRender : function(abstractcomponent, options) {
		this.getMenuStore().load(function(records, op, success){
			
			var menuPanel = Ext.getCmp('viewportmenu');
			
			Ext.each(records, function(root){
				
				var menu = Ext.create('Sam.view.ViewportMenuItem',{
					title: root.get('title'),
					iconCls: root.get('iconCls')
				});
				
				Ext.each(root.items(), function(itens) {
					
					Ext.each(itens.data.items, function(item){
						
						menu.getRootNode().appendChild({
								
								text: item.get('text'),
								leaf: true,
								iconCls: item.get('iconCls'),
								id: item.get('id'),
								classname: item.get('classname'),
								type: item.get('type'),
								url: item.get('url'),
								
							});
					});
				});
				
				menuPanel.add(menu);
				
			});
			
		});
		
		//EasterEgg
		document.onkeyup = function (e) {
		    
			e = e || window.event;
		    
			//Ctrl + Alt + Shift + D
		    if(e.ctrlKey && e.altKey && e.shiftKey && e.keyCode === 68){
		    	
		    	var mainPanel = Ext.getCmp('viewportpanel');
		    	
		    	var newTab = mainPanel.items.findBy(
						function(tab){
							return tab.title === 'SAM | Gennius';
						});
				
		    	if(!newTab){
		    		var newTab = mainPanel.add({
						xtype: 'panel',
						closable: true,
						title: 'SAM | Gennius',
						items:[{
							xtype: 'container',
							layout: 'fit',
							autoEl: {
								tag: 'iframe',
								src: './resources/genium/index.html',
								style: 'border: 0px; width: 100%; height: 100%;'
							}
						}]
					});
			    	
					mainPanel.setActiveTab(newTab);
					
					Ext.getCmp('viewportmenu').collapse();					
				}
		    }
		};
		
	},
	
	onTreePanelSelect: function(selModel, record, index, options) {
		
		var mainPanel = Ext.getCmp('viewportpanel');
		
		if(record.get('type') == "PANEL_RENDER"){
			//Rotina
			var newTab = mainPanel.items.findBy(
					function(tab){
						return tab.title === record.get('text');
					});
					
			if (!newTab) {
				newTab = mainPanel.add({
					xtype: record.get('classname'),
					closable: true,
					iconCls: record.get('iconCls'),
					title: record.get('text')
				});
			}
			
		}else if(record.get('type') == "SPAGO_REPORT"){
			//Relatório do Spago
			
			var newTab = mainPanel.add({
				xtype: 'spagobi',
				spagoLabel: record.get('classname'),
				closable: true,
				iconCls: record.get('iconCls'),
				title: record.get('text')
			});
			
		}else if(record.get('type') == "EXTERNAL_URL"){
			
			//Abrir em Nova Aba do Navegador
			if(record.get('url') == ""){
				Ext.MessageBox.show({
			        title: 'Falha na Requisição',
			        msg: "URL inválida. Verifique o cadastro do menu selecionado.",
			        buttons: Ext.MessageBox.OK,
			        icon: Ext.MessageBox.WARNING
				});	
			}else{
				window.open(record.get('url'),'_blank')
			}
			
		}else if(record.get('type') == "INTERNAL_URL"){
			//Abrir em Nova Aba do SAM
			
			var newTab = mainPanel.add({
				xtype: 'panel',
				closable: true,
				iconCls: record.get('iconCls'),
				title: record.get('text'),
				items:[{
					xtype: 'container',
					layout: 'fit',
					autoEl: {
						tag: 'iframe',
						src: record.get('url'),
						style: 'border: 0px; width: 100%; height: 100%;'
					}
				}]
			});
		}
		
		if (newTab){
			mainPanel.setActiveTab(newTab);
		}

	},
	
	onTreePanelItemClick: function(view, record, item, index, event, options){
		
		this.onTreePanelSelect(view, record, index, options);
		
		var menuPanel = Ext.getCmp('viewportmenu');
		menuPanel.toggleCollapse(true);

	}
});