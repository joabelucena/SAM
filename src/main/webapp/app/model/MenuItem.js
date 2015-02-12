Ext.define('Sam.model.MenuItem', {
	extend: 'Ext.data.Model',
	
	uses: [
	   'Sam.model.MenuRoot'
	],

	idProperty: 'id',
	
	fields: [
	    {
	    	name: 'text'
	    },
	    {
	    	name: 'id'
	    },
	    {
	    	name: 'menu_id'
	    },
	    {
	    	name: 'classname'
	    },
	    {
	    	name: 'iconCls'
	    }
	],
	
	belongsTo: {
		model: 'Sam.model.MenuRoot',
		foreignKey: 'menu_id'
	}

});