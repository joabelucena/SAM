Ext.define('Sam.lib.Util', {

	/**
	 * Synchronizes store and refreshes all current opened objects that uses it
	 */
	sync: function(store, comp) {
		
		store.sync({
			success: function(batch, options){
				
				//Recarrega Store
				store.reload();
				
				//Atualiza stores e views
				Ext.each(Ext.ComponentQuery.query(comp),function(f){
					f.getStore().reload();
				});
			}
		});

	},

	/**
	 * Handles window's behavior
	 */
	activateTab: function(action, id, xtype, uTitle, lockId, xStore) {
		
		//Variaveis
		var title, tabId, activeTab;
		
		//Aba Objecto Pai
		var mainPanel = Ext.getCmp('viewportpanel');
		
		switch(action){
			
			//Visualizar
			case 1:
				title = 'Visualizar Cod: ' + id;
				tabId = 'show-' + xtype + '-' + id;
				break;
			
			//Incluir
			case 2:
				title = 'Incluir Novo Registro';
				tabId = 'add-' + xtype
				break;
			
			//Alterar
			case 3:
				title = 'Alterar Cod: ' + id;
				tabId = 'edit-' + xtype + '-' + id;
				break;
			
			//Excluir
			case 4:
				title = 'Excluir Cod: ' + id;
				tabId = 'delete-' + xtype + '-' + id;
				break;
			default:
				title = uTitle;
		
		}
		
		var newTab = mainPanel.items.findBy(
				function(tab){
					return tab.id === tabId;
				});
		
		if (!newTab) {
			newTab = mainPanel.add({
				id: tabId,
				xtype: xtype,
				closable: true,
				iconCls: 'magnifier-zoom',
				title: title,
				xStore: xStore
			});
		}
		
		//Seta Aba como ativa
		mainPanel.setActiveTab(newTab);
		
		//Se for inclusao desabilita o campo Id
		if(action == 2 && lockId){
			Ext.ComponentQuery.query('#id', newTab)[0].setVisible(false);
			
		} else if (action == 2 && lockId == false) {
			Ext.ComponentQuery.query('#id', newTab)[0].setEditable(true);
			
		}
		
		//Variavel para retornar aba ativa
		activeTab = mainPanel.getActiveTab();
		
		return activeTab;
		
	},

	/**
	 * Closes current activeTab
	 */
	closeActiveTab : function() {

		Ext.getCmp('viewportpanel').getActiveTab().close();

		return;
	}

});