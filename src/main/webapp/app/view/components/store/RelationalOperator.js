Ext.define('Sam.view.components.store.RelationalOperator', {
	extend: 'Ext.data.Store',
    fields: ['id', 'desc'],
    data : [
        {"id":">"	, "desc":"Maior que"},
        {"id":"<"	, "desc":"Menor que"},
        {"id":">="	, "desc":"Maior ou igual a"},
        {"id":"<="	, "desc":"Menor ou igual a"},
        {"id":"=="	, "desc":"igual a"}
    ]
});