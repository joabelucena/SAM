Ext.define('Sam.model.TaskCondition', {
	extend: 'Ext.data.Model',
	
	fields:[
	        
	        {name: 'seq'									},
	        {name: 'logicOper'		, type: 'string'		},
	        {name: 'type'			, type: 'string'		},
	        {name: 'field'			, type: 'string'		},
	        {name: 'relOper'		, type: 'string'		},
	        {name: 'value'									}]
	        
});