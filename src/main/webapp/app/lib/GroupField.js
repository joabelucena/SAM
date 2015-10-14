Ext.define('Sam.lib.GroupField', {
    extend: 'Ext.form.FieldSet',
    
    alias: 'widget.groupfield',
 
    constructor: function(config) {
        this.callParent(arguments);
    },
 
    loadRecord: function (record) {
    	
    	var me = this;
    	
    	Ext.each(Ext.ComponentQuery.query('field', me),function(f){
    		f.setValue(record.get(f.getName()))
    	});
    }

});