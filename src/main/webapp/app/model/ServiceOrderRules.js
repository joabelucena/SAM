Ext.define('Sam.model.ServiceOrderRules', {
	extend: 'Ext.data.Model',
	
	fields:[
	        
	        {name: 'id'				, type: 'number' ,								},
	        {name: 'role_id'		, type: 'number' ,	mapping: 'role.id'			},
	        {name: 'role_desc'		, type: 'string' ,	mapping: 'role.roleName'	},
	        {name: 'curstatus_id'	, type: 'number' ,	mapping: 'curstatus.id'		},
	        {name: 'curstatus_desc'	, type: 'string' ,	mapping: 'curstatus.desc'	},
	        {name: 'nxtstatus_id'	, type: 'number' ,	mapping: 'nxtstatus.id'		},
	        {name: 'nxtstatus_desc'	, type: 'string' ,	mapping: 'nxtstatus.desc'	},
	        {name: 'remark'			, type: 'string' 								}

	       ],    
});