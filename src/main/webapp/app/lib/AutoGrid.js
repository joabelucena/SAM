Ext.define('Sam.lib.AutoGrid', {
	extend: 'Ext.grid.Panel',
    
    alias: 'widget.autogrid',
 
    constructor: function(config) {
        this.callParent(arguments);
        
        /**
         * Handles all component's tasks.
         */
        this.activeTasks = [];
        
        this.selectedRecords = [];
        
        /**
         * Comentado para teste de correção do bug na atualização do estado da ordem de serviço. 
         */
//        this.getStore().on('beforeload', this.rememberSelection, this);
//        this.getView().on('refresh', this.refreshSelection, this);
    },
    
    viewConfig: {
		preserveScrollOnRefresh: true
    },
    
    /**
     * Frequency in 'seconds' to update grid Store
     */
    refreshRate: 0,
    
    /**
     * Starts a task for this component
     */
    startTask: function(task){
    	this.activeTasks.push(
    			Ext.TaskManager.start(task)
    	);
    },
    
    /**
     * Stops a task from this component
     */
    stopTask: function(task){
    	this.activeTasks.pop(
    			Ext.TaskManager.stop(task)
		);
    },
    
    /**
     * Stops all tasks nested to this component
     */
    stopAllTasks: function(){
		this.activeTasks.forEach(function(t){
			Ext.TaskManager.stop(t);
		});
		
		this.activeTasks.length = 0;
    },
    
   
    listeners: {
    	
    	render: function(component, options) {
    		
    		var start = (component.refreshRate > 0),
    			rate = component.refreshRate,
    			selection = component.getSelectionModel().getSelection()[0];
    		
    		if(start){
    			
    			var loading = false;
        		
        		var task = {
        				
        				run : function(){
        					if (loading == false){
        			   
        						loading = true
        				   
        						// Truncate validation
        						if(component.getStore()){
        							if(component.getStore().getData().length > 0){
        							
        								selection = component.getSelection();
        							
        								component.getStore().load(
        										function(records, operation, success) {
        											loading = false;
        										}
        								);
        								component.getView().setLoading(false);
        								component.refresh();
        							
        								component.setSelection(selection);
        							}
        						}
        					}
        				},
        		   
        		   interval: (rate * 1000)
        		   
        		};
        		
        		component.startTask(task);
        		
    		}
    	},
    	
    	beforeclose: function(component, options) {
    		component.stopAllTasks();
    	}
    },
    
    /**
     * Selection remember functions
     */
    rememberSelection: function( store, operation, options) {
        if (!this.rendered || Ext.isEmpty(this.el)) {
            return;
        }

        this.selectedRecords = this.getSelectionModel().getSelection();
        this.getView().saveScrollState();
    },
    
    refreshSelection: function(component, options) {
        if (0 >= this.selectedRecords.length) {
            return;
        }

        var newRecordsToSelect = [];
        for (var i = 0; i < this.selectedRecords.length; i++) {
            record = this.getStore().findRecord('id',this.selectedRecords[i].get('id'));
            if (!Ext.isEmpty(record)) {
                newRecordsToSelect.push(record);
            }
        }

        this.getSelectionModel().select(newRecordsToSelect);
//        Ext.defer(this.setScrollTop, 30, this, [this.getView().scrollState.top]);
    }

});