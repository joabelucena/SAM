Ext.define('Sam.lib.AssociatedWriter', {
    extend: 'Ext.data.writer.Json',
    alias: 'writer.associatedjson',
    
    dateFormat: 'time',
 
    constructor: function(config) {
        this.callParent(arguments);
    },
 
    getRecordData: function (record, operation) {
    	var data = record.getData(),
    		associatedData = record.getAssociatedData();
        
    	this.xFormatData(associatedData);
    	
    	Ext.apply(data, associatedData);
    	
    	return data;
    },
    
    /**
     * Recursively function with deep control, 
     */
    xFormatData: function(obj, deep){
    	
    	var deep = typeof deep !== 'undefined' ? deep : 1,
    		me = this;
    	
    	if (deep >= 4) return;
    	
    	for(key in obj){
    		
    		if(typeof obj[key] == "object" && obj[key] != null && !Ext.isDate(obj[key])){
    			this.xFormatData(obj[key], deep + 1);
    		}else{
    			
    			/** Format Data **/
    			if(Ext.isDate(obj[key])){
    				obj[key] = Ext.Date.format(obj[key], this._dateFormat);
    			}
    		}
    	}
    	
    	return;
    	
    }

});