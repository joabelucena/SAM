Ext.define('Sam.view.components.store.ComboBox', {
	extend: 'Ext.data.Store',
    fields: ['id', 'desc'],
    data : [
        {"id":"Y", "desc":"Sim"},
        {"id":"N", "desc":"Não"}
    ]
});