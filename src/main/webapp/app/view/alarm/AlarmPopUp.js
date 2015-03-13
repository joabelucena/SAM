var equipmentInfo = {
			xtype: 'fieldset',
		    defaultType: 'textfield',
		    title: 'Informações do Equipamento',
		    layout: {
	            type: 'vbox',
	            align: 'stretch'
	        },
		    
	        items: [{
	            fieldLabel: 'Código',
	            name: 'alarmpopup_id',
	            readOnly: true,
	        }, {
	            fieldLabel: 'Modelo',
	            id: 'alarmpopup_model',
	            readOnly: true,
	        }, {
	            fieldLabel: 'Fabricante',
	            id: 'alarmpopup_manufacturer',
	            readOnly: true,
	        }, {
	            fieldLabel: 'Sub-Sistema',
	            id: 'alarmpopup_subsystem',
	            readOnly: true,
	        }, {
	            fieldLabel: 'Local de Instalação',
	            id: 'alarmpopup_site',
	            readOnly: true
	        }],
	};

var eventInfo = {
		xtype: 'fieldset',
	    defaultType: 'textfield',
	    title: 'Informações do Alarme',
	    layout: {
            type: 'vbox',
            align: 'stretch'
        },
        
        items: [{
	    	fieldLabel: 'Data/Hora Alarme',
	    	id: 'alarmpopup_time_alarm',
	        readOnly: true,
	    }, {
	    	fieldLabel: 'Severidade',
	    	id: 'alarmpopup_severity',
	    	readOnly: true,
	    }, {
	    	fieldLabel: 'Reconhecido por',
	    	id: 'alarmpopup_reco_user',
	    	readOnly: true,
	    }, {
	    	fieldLabel: 'Data/Hora do Reconhecimento',
	    	id: 'alarmpopup_reco_time',
	    	readOnly: true,
	    }]
};

var soInfo = {
		xtype: 'fieldset',
	    defaultType: 'textfield',
	    title: 'Informação da OS',
	    layout: {
            type: 'vbox',
            align: 'stretch'
        },
	    items: [{
	    	fieldLabel: 'Data de Previsão Início ',
		    id: 'alarmpopup_time_alarmaa',
		}, {
		    fieldLabel: 'Data de Previsão ',
		    id: 'alarmpopup_time_alarmab',	
	    },{
	    	fieldLabel: 'Tipo da OS',
	    	id: 'alarmpopup_so_type',
	    	xtype: 'combobox',
	    	allowBlank: false
	    },{
	    	fieldLabel: 'Observação',
	    	id: 'alarmpopup_obs_os',
	    	xtype: 'textareafield'
	    }]
};

Ext.define('Sam.view.alarm.AlarmPopUp', {
	extend: 'Ext.tab.Panel',
	alias : 'widget.alarmpopup',

	    xtype: 'form-vboxlayout',
	    
	    title: 'Abrir Ordem de Serviço',
	    width: 700,
	    height: 600,
	    minWidth: 700,
	    minHeight: 600,
	    plain: true,
	    layout: 'fit',
	    
	    items: [{
	        xtype: 'form',

	        defaultType: 'textfield',
	        id: 'alarmpopup_form',
	        fieldDefaults: {
	            labelWidth: 180
	        },
	        
	        layout: {
	            type: 'vbox',
	            align: 'stretch'
	        },
	        
	        bodyPadding: 10,
	        border: false,
	        items: [ equipmentInfo, eventInfo , soInfo],
	      
		    buttons: [{
		        text: 'Abrir Ordem de Serviço'
		    }],
	    }],
	            
	});