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
				select: this.onTreePanelSelect,
				itemclick: this.onTreepanelItemClick
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
							className: item.get('classname')
							
						});
					});
				});
				
				menuPanel.add(menu);
				
			});
		});
	},
	
	onTreepanelSelect: function(selModel, record, index, options) {
		
		var mainPanel = Ext.getCmp('viewportpanel');
		
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
		
		mainPanel.setActiveTab(newTab);
	},
	
	onTreepanelItemClick: function(view, record, item, index, event, options){
		this.onTreepanelSelect(view, record, index, options);
	}

});