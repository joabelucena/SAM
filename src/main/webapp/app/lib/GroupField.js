Ext.define('Sam.lib.GroupField', {
    extend: 'Ext.form.FieldSet',
    
    alias: 'widget.groupfield',
 
    constructor: function(config) {
        this.callParent(arguments);
    },
    
    /**
     * Loads a record data on a fields mathcing by 'name' property.
     * 
     */
    loadRecord: function (record) {
    	
    	var me = this;
    	
    	Ext.each(Ext.ComponentQuery.query('field', me),function(f){
    		f.setValue(record.get(f.getName()))
    	});
    },
    
    /**
     * Sets all fieldset fields 'disabled'property 
     * 
     */
    setDisabled: function(option){
    	var me = this;
    	
    	Ext.each(Ext.ComponentQuery.query('field', me),function(f){
    		f.setDisabled(option);
    	});
    	
    },
    
    /**
     * Sets all fieldset fields 'disabled'property 
     * 
     */
    setReadOnly: function(option){
    	var me = this;
    	
    	Ext.each(Ext.ComponentQuery.query('field', me),function(f){
    		f.setReadOnly(option);
    	});
    	
    }

});