Ext.define('Sam.store.TaskCondition', {
	extend: 'Ext.data.Store',
	
	model: 'Sam.model.TaskCondition',
	
	sorters: { property: 'seq'},
	
});