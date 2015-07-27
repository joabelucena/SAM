Ext.define('Sam.view.task.ConditionType', {
	extend: 'Ext.data.Store',
    fields: ['id', 'desc'],
    data : [
        {"id":"MT"	, "desc":"MTBF"},
        {"id":"AL"	, "desc":"Alarme"},
        {"id":"AT"	, "desc":"Tipo de Alarme"},
    ]
});