Ext.define('Sam.view.components.store.ComboBox', {
	extend: 'Ext.data.Store',
    fields: ['id', 'desc'],
    data : [
        {"id":1, "desc":"Sim"},
        {"id":2, "desc":"Não"}
    ]
});