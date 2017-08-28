Ext.define('Sam.lib.SmartCombo', {
	extend : 'Ext.form.field.ComboBox',

	alias : 'widget.smartcombo',

	queryMode : 'local',
	emptyText : 'Digite aqui...',
	hideTrigger : 'true',
	valueField : 'id',
	displayField : 'desc',
	triggerAction : 'all',

	constructor : function(config) {
		this.callParent(arguments);
		
		//Seta URL vinda da configuração do objeto
		if(config.ajaxUrl){
			this.setStore(Ext.data.Store({
				fields : [ 'id', 'desc' ],
				autoLoad : true,
				proxy : {
					type : 'ajax',
					url : config.ajaxUrl,
					reader : {
						type : 'json',
						root : 'data'
					}
				},
			}))
		}
	},

});