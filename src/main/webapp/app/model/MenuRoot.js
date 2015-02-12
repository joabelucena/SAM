Ext.define('Sam.model.MenuRoot', {
	extend: 'Ext.data.Model',
	
	uses: [
	       'Sam.model.MenuItem'
	],
	
	idProperty: 'id',
	
	fields: [
	  {
		  name: 'title'
	  },{
		  name: 'iconCls'
	  },{
		  name: 'id'
	  }
	],
	
	hasMany: {
		model: 'Sam.model.MenuItem',
		foreignKey: 'menu_id',
		name: 'items'
	}
});