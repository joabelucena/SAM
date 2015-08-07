Ext.define('Sam.model.MenuItem', {
	extend: 'Ext.data.TreeModel',
	
	uses: [
	   'Sam.model.MenuRoot'
	],

	idProperty: 'id',
	
	fields: [{
	    	name: 'text'
	    },{
	    	name: 'id'
	    },{
	    	name: 'menu_id'
	    },{
	    	name: 'classname'
	    },{
	    	name: 'iconCls'
	    },{
	    	name: 'type'
	    },{
	    	name: 'url'
	    }],
	
	belongsTo: {
		model: 'Sam.model.MenuRoot',
		foreignKey: 'menu_id'
	},

});