Ext.define('Sam.view.components.store.MenuType', {
	extend: 'Ext.data.Store',
    fields: ['id', 'desc'],
    data : [
        {"id":"PN", "desc":"Rotina"},
        {"id":"SR", "desc":"Relatório do Spago"},
        {"id":"EU", "desc":"Abrir em Nova Aba do Navegador"},
        {"id":"IU", "desc":"Abrir em Nova Aba do SAM"},
    ]
});